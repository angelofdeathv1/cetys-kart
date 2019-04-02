package mx.cetys.arambula.angel.impl

import mx.cetys.arambula.angel.application.products.SaveProductCommand
import mx.cetys.arambula.angel.application.products.SaveProductCommandHandler

class ProductApiImpl {
    private val saveProductCommandHandler = SaveProductCommandHandler()
    fun addProduct(request: AddProductRequest): AddProductResponse {
        val response = saveProductCommandHandler.handle(SaveProductCommand(request.name, request.description))
        return AddProductResponse(response.id, response.name, response.description)
    }
}

data class AddProductRequest(
    val name: String,
    val description: String
)

data class AddProductResponse(
    val id: Int,
    val name: String,
    val description: String
)