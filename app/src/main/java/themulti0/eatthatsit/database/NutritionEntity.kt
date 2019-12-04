package themulti0.eatthatsit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // unset ID
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "value") var value: Double?
)