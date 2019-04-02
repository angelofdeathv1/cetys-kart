package mx.cetys.arambula.angel.exposed

import mx.cetys.arambula.angel.dto.ProductDTO

interface CetysKartFacade {
    fun addProduct(name:String, description:String): ProductDTO
}