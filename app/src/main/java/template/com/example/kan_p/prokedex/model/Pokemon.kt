package template.com.example.kan_p.prokedex.model

data class Pokemon(
    val url: String,
    var id: Int,
    val name: String,
    val type: ArrayList<TypeEnum>,
    val baseStats: BaseStats
)