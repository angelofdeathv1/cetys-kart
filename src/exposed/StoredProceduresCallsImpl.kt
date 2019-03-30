package mx.cetys.arambula.angel.exposed

import io.ktor.features.NotFoundException
import mx.cetys.arambula.angel.execSp
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class StoredProceduresCallsImpl : StoredProceduresCalls {
    override fun callBuscarAlumnoSP(user: String, password: String): String {
        val storedProcedureRawSQL = "exec dbo.buscar_alumno '$user','$password'"
        var matricula = ""
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
                        404 -> throw NotFoundException()
                    }
                    matricula = it.getString("Matricula")
                }
            }
        }
        return matricula
    }
}