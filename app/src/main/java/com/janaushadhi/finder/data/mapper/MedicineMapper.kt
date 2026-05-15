package com.janaushadhi.finder.data.mapper

import com.janaushadhi.finder.data.local.entity.MedicineEntity
import com.janaushadhi.finder.domain.model.Medicine

fun MedicineEntity.toDomain(): Medicine = Medicine(
    id = id,
    brandName = brandName,
    genericName = genericName,
    saltComposition = saltComposition,
    brandPrice = brandPrice,
    genericPrice = genericPrice,
    category = category,
    manufacturer = manufacturer
)

fun List<MedicineEntity>.toDomain(): List<Medicine> = map { it.toDomain() }
