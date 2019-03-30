package mx.cetys.arambula.angel.exposed

interface StoredProceduresCalls {
    fun callBuscarAlumnoSP(user: String, password: String): String
}