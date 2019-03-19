package mx.cetys.arambula.angel

import mx.cetys.arambula.angel.exposed.callBuscarAlumnoSP
import mx.cetys.arambula.angel.exposed.callBuscarAlumnoSPMultiple

fun main(args: Array<String>) {
    val user = "021204"
    val password = "123456"
    val resultList = callBuscarAlumnoSPMultiple(user, password)

    resultList.forEach {
        println(it.matricula)
    }

    val resultObject = callBuscarAlumnoSP(user, password)
    println(resultObject)

}


