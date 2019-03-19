package mx.cetys.arambula.angel

data class MiObjeto(val name: String, val age: Int)

fun saludarYa(): String {
    return "saludar..........YA!"
}

fun regresarMapa(): ArrayList<MiObjeto> {
    return arrayListOf(
        MiObjeto("saludante", 10),
        MiObjeto("saludante2", 10)
    )
}

fun regresarMapaAntiEstandar(): ArrayList<MiObjeto> {
    return arrayListOf(
        MiObjeto("Personilla1", 15),
        MiObjeto("Personirijilla2", 20)
    )
}