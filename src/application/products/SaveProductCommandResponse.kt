package mx.cetys.arambula.angel.application.products

data class SaveProductCommandResponse(
    val id: Int,
    val name: String,
    val description: String
)