package com.janaushadhi.finder.di

import android.content.Context
import androidx.room.Room
import com.janaushadhi.finder.data.local.JanAushadhiDatabase
import com.janaushadhi.finder.data.local.dao.MedicineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): JanAushadhiDatabase {
        return Room.databaseBuilder(
            context,
            JanAushadhiDatabase::class.java,
            "jan_aushadhi_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: JanAushadhiDatabase): MedicineDao {
        return database.medicineDao()
    }
}
