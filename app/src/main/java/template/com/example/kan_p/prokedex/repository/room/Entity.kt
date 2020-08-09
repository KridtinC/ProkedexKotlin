package template.com.example.kan_p.prokedex.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import template.com.example.kan_p.prokedex.model.BaseStats
import template.com.example.kan_p.prokedex.model.TypeEnum

@Entity(tableName = "image_path")
data class ImagePathEntity (@PrimaryKey(autoGenerate = false) var id: Int,
                            var path: String
)

@Entity(tableName = "pokemon")
data class PokemonEntity (@PrimaryKey(autoGenerate = false) var id: Int,
                          var url: String,
                          var name: String,
                          var baseStats: BaseStats,
                          var type: Array<TypeEnum>)