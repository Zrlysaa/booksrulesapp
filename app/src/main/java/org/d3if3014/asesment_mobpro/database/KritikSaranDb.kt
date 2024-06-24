package org.d3if3014.asesment_mobpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3014.asesment_mobpro.model.KritikSaran


@Database(entities = [KritikSaran::class], version = 1, exportSchema = false)
abstract class KritikSaranDb : RoomDatabase() {

    abstract val dao: KritikSaranDao

    companion object {
        @Volatile
        private var INSTANCE: KritikSaranDb? = null

        fun getInstance(context: Context): KritikSaranDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KritikSaranDb::class.java,
                        "kritikSaran.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
