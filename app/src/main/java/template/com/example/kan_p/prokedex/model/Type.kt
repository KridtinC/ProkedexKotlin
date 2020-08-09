package template.com.example.kan_p.prokedex.model

data class Type (
    var slot: Int,
    var type: TypeInfo
)

data class TypeInfo(
    var name: TypeEnum,
    var url: String
)