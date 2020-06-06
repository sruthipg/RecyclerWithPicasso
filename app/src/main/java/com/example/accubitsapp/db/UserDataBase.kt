package com.example.accubitsapp.db


import android.content.Context


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accubitsapp.db.model.movie.ResultsMovie

//AddressData::class,
//CompanyData::class,
//GeoData::class
@Database(
    entities = [ResultsMovie::class],
    version = 1
)

abstract class UserDataBase : RoomDatabase() {

    abstract fun saveuserdata(): MovieDao

    companion object {

        @Volatile
        private var instance: UserDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java,
                "MyAppDatabase.db"
            ).build()
    }
}