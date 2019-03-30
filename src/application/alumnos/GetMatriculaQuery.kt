package mx.cetys.arambula.angel.application.alumnos

import mx.cetys.arambula.angel.application.Request

data class GetMatriculaQuery(
    val matricula: String,
    val contrasena: String
) : Request