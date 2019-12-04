package themulti0.eatthatsit.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NutritionEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nutrtionDao(): NutritionDao
}