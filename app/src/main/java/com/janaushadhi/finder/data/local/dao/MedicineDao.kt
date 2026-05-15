package com.janaushadhi.finder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.janaushadhi.finder.data.local.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineEntity>)

    @Query("SELECT * FROM medicines")
    suspend fun getAllMedicines(): List<MedicineEntity>

    @Query("SELECT COUNT(*) FROM medicines")
    suspend fun getCount(): Int

    @Query("""
        SELECT * FROM medicines 
        WHERE 
            brandName LIKE '%' || :query || '%' OR
            genericName LIKE '%' || :query || '%' OR
            saltComposition LIKE '%' || :query || '%'
        ORDER BY brandName ASC
        LIMIT 50
    """)
    suspend fun searchMedicines(query: String): List<MedicineEntity>

    @Query("SELECT * FROM medicines ORDER BY brandName ASC LIMIT 20")
    fun getTopMedicines(): Flow<List<MedicineEntity>>

    @Query("DELETE FROM medicines")
    suspend fun clearAll()
}
