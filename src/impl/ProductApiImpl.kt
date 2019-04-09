package mx.cetys.arambula.angel.impl

import application.products.GetProductQuery
import application.products.GetProductQueryResponse
import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.application.products.SaveProductCommand
import mx.cetys.arambula.angel.application.products.SaveProductCommandResponse
import mx.cetys.arambula.angel.dto.ProductDTO

class ProductApiImpl(
    private val saveProductCommandHandler:
    RequestHandler<SaveProductCommand, SaveProductCommandResponse>,
    private val getProductQueryHandler: RequestHandler<GetProductQuery, GetProductQueryResponse>
) {
    fun addProduct(request: AddProductRequest): AddProductResponse {
        val response = saveProductCommandHandler.handle(SaveProductCommand(request.name, request.description))
        return AddProductResponse(response.id, response.name, response.description)
    }

    fun getProduct(request: GetProductRequest): GetProductResponse {
        val response = getProductQueryHandler.handle(GetProductQuery(request.id))
        return GetProductResponse(response.products)
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

data class GetProductRequest(
    val id: Int?
)

data class GetProductResponse(
    val products: List<ProductDTO>
)
