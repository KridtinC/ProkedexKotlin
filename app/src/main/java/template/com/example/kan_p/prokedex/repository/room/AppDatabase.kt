package template.com.example.kan_p.prokedex.repository.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImagePathEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        fun getAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "db_app").build()
    }

    abstract fun imagePathDao(): ImagePathDao
}