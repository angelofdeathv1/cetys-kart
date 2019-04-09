package mx.cetys.arambula.angel.exposed

import mx.cetys.arambula.angel.dto.ProductDTO
import mx.cetys.arambula.angel.execSp
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class CetysKartFacadeImpl : CetysKartFacade {
    override fun getProduct(id: Int?): List<ProductDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addProduct(name: String, description: String): ProductDTO {
        val storedProcedureRawSQL = "exec cetyskart.add_product '$name','$description'"
        var product = ProductDTO(0, "", "")

        Database.connect(
            EXPOSED_CONNECTION_STRING,
            EXPOSED_DRIVER,
            EXPOSED_USER,
            EXPOSED_PASSWORD
        )

        transaction {
            execSp(storedProcedureRawSQL) {
                if (it.next()) {
                    val statusCode = it.getInt("StatusCode")
                    when (statusCode) {
                        500 -> throw Exception("FAIL")
                    }
                    product = ProductDTO(
                        it.getInt("product_id"),
                        it.getString("product_name"),
                        it.getString("product_description")
                    )
                }
            }
        }
        return product
    }
}