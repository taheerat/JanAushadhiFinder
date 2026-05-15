package com.janaushadhi.finder.data.repository

import com.janaushadhi.finder.data.local.dao.MedicineDao
import com.janaushadhi.finder.data.local.MedicineSeedData
import com.janaushadhi.finder.data.mapper.toDomain
import com.janaushadhi.finder.domain.model.Medicine
import com.janaushadhi.finder.util.FuzzySearch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineRepository @Inject constructor(
    private val dao: MedicineDao
) {
    suspend fun seedIfEmpty() {
        if (dao.getCount() == 0) {
            dao.insertAll(MedicineSeedData.getSampleMedicines())
        }
    }

    suspend fun searchMedicines(query: String): List<Medicine> {
        val trimmed = query.trim()
        if (trimmed.isBlank()) {
            // Return top medicines when query is empty
            return dao.getAllMedicines().take(20).toDomain()
        }

        // First try SQL LIKE search
        val sqlResults = dao.searchMedicines(trimmed)

        // Also get all medicines for fuzzy matching
        val allMedicines = dao.getAllMedicines()

        // Apply fuzzy search
        val fuzzyResults = allMedicines.filter { entity ->
            FuzzySearch.fuzzyContains(trimmed, entity.brandName) ||
            FuzzySearch.fuzzyContains(trimmed, entity.genericName) ||
            FuzzySearch.fuzzyContains(trimmed, entity.saltComposition)
        }

        // Merge SQL + fuzzy results, deduplicate by id
        val merged = (sqlResults + fuzzyResults)
            .distinctBy { it.id }

        // Score and sort
        val scored = merged.map { entity ->
            val score = FuzzySearch.scoreMatch(
                trimmed,
                entity.brandName,
                entity.genericName,
                entity.saltComposition
            )
            Pair(entity, score)
        }

        return scored
            .filter { it.second > 0.5 }
            .sortedByDescending { it.second }
            .map { it.first.toDomain() }
            .take(50)
    }

    suspend fun getAllMedicines(): List<Medicine> {
        return dao.getAllMedicines().toDomain()
    }
}
