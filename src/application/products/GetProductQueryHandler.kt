package mx.cetys.arambula.angel.application.products

import application.products.GetProductQuery
import application.products.GetProductQueryResponse
import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.exposed.CetysKartFacade

class GetProductQueryHandler(private val ckf: CetysKartFacade) :
    RequestHandler<GetProductQuery, GetProductQueryResponse> {
    override fun handle(message: GetProductQuery): GetProductQueryResponse {
        val response = ckf.getProduct(message.id)
        return GetProductQueryResponse(response)
    }
}