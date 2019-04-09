package mx.cetys.arambula.angel.application.products

import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.exposed.CetysKartFacade

class SaveProductCommandHandler(private val ckf: CetysKartFacade) :
    RequestHandler<SaveProductCommand, SaveProductCommandResponse> {

    override fun handle(message: SaveProductCommand): SaveProductCommandResponse {
        require(message.name.isNotBlank())

        val product = ckf.addProduct(message.name, message.description)
        return SaveProductCommandResponse(product.id, product.name, product.description)
    }
}