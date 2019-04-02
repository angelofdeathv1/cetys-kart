package mx.cetys.arambula.angel.application.products

import mx.cetys.arambula.angel.application.Request

data class SaveProductCommand(
    val name: String,
    val description: String
) : Request