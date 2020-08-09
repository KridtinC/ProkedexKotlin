package template.com.example.kan_p.prokedex.model

data class Ability (
    val ability: AbilityInfo,
    val isHidden: Boolean,
    val slot: Int
)

data class AbilityInfo (
    val name: String,
    val url: String
)