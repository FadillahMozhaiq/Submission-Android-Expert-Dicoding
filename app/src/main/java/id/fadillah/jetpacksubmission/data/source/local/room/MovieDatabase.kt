package id.fadillah.jetpacksubmission.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.local.model.TvDatabaseEntity
import id.fadillah.jetpacksubmission.utils.converter.DatabaseConverter

@Database(entities = [MovieDatabaseEntity::class, TvDatabaseEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}