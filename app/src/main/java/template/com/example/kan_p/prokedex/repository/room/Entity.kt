package template.com.example.kan_p.prokedex.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_path")
data class ImagePathEntity (@PrimaryKey(autoGenerate = false) var id: Int,
                            var path: String
)