package mx.cetys.arambula.angel.application.products

import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.exposed.CetysKartFacade
import mx.cetys.arambula.angel.exposed.CetysKartFacadeImpl

class SaveProductCommandHandler : RequestHandler<SaveProductCommand, SaveProductCommandResponse> {
    private val ckf: CetysKartFacade = CetysKartFacadeImpl()
    override fun handle(message: SaveProductCommand): SaveProductCommandResponse {
        require(message.name.isNotBlank())

        val product = ckf.addProduct(message.name, message.description)
        return SaveProductCommandResponse(product.id, product.name, product.description)
    }
}