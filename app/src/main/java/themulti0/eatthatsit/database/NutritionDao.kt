package themulti0.eatthatsit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao // Data access object
interface NutritionDao {

    @Query("SELECT * FROM nutritionentity")
    fun getAll(): List<NutritionEntity>

    @Query("SELECT * FROM nutritionentity " +
            "WHERE id = :id")
    fun findById(id: Long): NutritionEntity

    @Update
    fun update(nutritionEntity: NutritionEntity)

    @Insert
    fun insert(nutritionEntity: NutritionEntity): Long
}