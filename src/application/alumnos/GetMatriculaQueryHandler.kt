package mx.cetys.arambula.angel.application.alumnos

import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.exposed.StoredProceduresCalls
import mx.cetys.arambula.angel.exposed.StoredProceduresCallsImpl

class GetMatriculaQueryHandler : RequestHandler<GetMatriculaQuery, GetMatriculaQueryResponse> {
    private val spc: StoredProceduresCalls = StoredProceduresCallsImpl()

    override fun handle(message: GetMatriculaQuery): GetMatriculaQueryResponse {
        require(message.matricula.isNotBlank())
        require(message.contrasena.isNotBlank())

        val alumno = spc.callBuscarAlumnoSP(message.matricula, message.contrasena)

        return GetMatriculaQueryResponse(alumno)
    }
}