package com.janaushadhi.finder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.janaushadhi.finder.data.local.dao.MedicineDao
import com.janaushadhi.finder.data.local.entity.MedicineEntity

@Database(
    entities = [MedicineEntity::class],
    version = 1,
    exportSchema = false
)
abstract class JanAushadhiDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}
