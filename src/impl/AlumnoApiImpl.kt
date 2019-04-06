package mx.cetys.arambula.angel.impl

import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQuery
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQueryResponse

class AlumnoApiImpl(
    private val getMatriculaQueryHandler: RequestHandler<GetMatriculaQuery, GetMatriculaQueryResponse>
) {

    fun getMatricula(matricula: String, password: String): GetMatriculaResponse {
        val response = getMatriculaQueryHandler.handle(GetMatriculaQuery(matricula, password))
        return GetMatriculaResponse(response.matricula)
    }

    data class GetMatriculaResponse(val matricula: String)
}