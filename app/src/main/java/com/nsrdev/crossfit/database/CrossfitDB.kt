package com.nsrdev.crossfit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nsrdev.crossfit.model.Exercise

@Database(entities = [Exercise::class], version = 1)
abstract class CrossfitDB: RoomDatabase() {
    abstract fun exerciseDAO(): ExerciseDAO

    companion object {
        @Volatile
        private var INSTANCE: CrossfitDB? = null

        fun getCrossfitDB(context: Context): CrossfitDB {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CrossfitDB::class.java,
                    "CrossfitDB"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}