package mx.cetys.arambula.angel.impl

import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQuery
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQueryHandler

class AlumnoApiImpl {
    private val getMatriculaQueryHandler = GetMatriculaQueryHandler()

    fun getMatricula(matricula: String, password: String): GetMatriculaResponse {
        val response = getMatriculaQueryHandler.handle(GetMatriculaQuery(matricula, password))
        return GetMatriculaResponse(response.matricula)
    }

    data class GetMatriculaResponse(val matricula: String)
}