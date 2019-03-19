package mx.cetys.arambula.angel.exposed

import dto.AlumnoDTO
import mx.cetys.arambula.angel.execSp
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun callBuscarAlumnoSPMultiple(user: String, password: String): List<AlumnoDTO> {
    val storedProcedureRawSQL = "exec dbo.buscar_alumno '$user','$password'"
    val resultList = ArrayList<AlumnoDTO>()

    Database.connect(
        EXPOSED_CONNECTION_STRING,
        EXPOSED_DRIVER,
        EXPOSED_USER,
        EXPOSED_PASSWORD
    )

    transaction {
        execSp(storedProcedureRawSQL) {
            while (it.next()) {
                resultList.add(AlumnoDTO(it.getString("Matricula")))
            }
        }
    }
    return resultList
}

fun callBuscarAlumnoSP(user: String, password: String): Map<String, AlumnoDTO> {
    val storedProcedureRawSQL = "exec dbo.buscar_alumno '$user','$password'"
    var alumno = hashMapOf<String, AlumnoDTO>()

    Database.connect(
        EXPOSED_CONNECTION_STRING,
        EXPOSED_DRIVER,
        EXPOSED_USER,
        EXPOSED_PASSWORD
    )

    transaction {
        execSp(storedProcedureRawSQL) {
            if (it.next()) {
                alumno["alumno"] = AlumnoDTO(it.getString("Matricula"))
            }
        }
    }
    return alumno
}