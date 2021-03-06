package mx.cetys.arambula.angel.application.alumnos

import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.exposed.StoredProceduresCalls

class GetMatriculaQueryHandler(private val spc: StoredProceduresCalls) :
    RequestHandler<GetMatriculaQuery, GetMatriculaQueryResponse> {

    override fun handle(message: GetMatriculaQuery): GetMatriculaQueryResponse {
        require(message.matricula.isNotBlank())
        require(message.contrasena.isNotBlank())

        var messageA = message.matricula.toUpperCase()
        when (messageA[0]) {
            'T', 'M', 'E' -> messageA = messageA.substring(1)
        }

        val alumno = spc.callBuscarAlumnoSP(messageA, message.contrasena)

        return GetMatriculaQueryResponse(alumno)
    }
}