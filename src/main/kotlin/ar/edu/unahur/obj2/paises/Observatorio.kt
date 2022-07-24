package ar.edu.unahur.obj2.paises

object Observatorio {
    val conjuntoPaises = mutableSetOf<Pais>()

    fun sonLimitrofes(paisA: String, paisB: String): Boolean {
        val primerPais = conjuntoPaises.find { it.nombre == paisA }
        val segundoPais = conjuntoPaises.find { it.nombre == paisB }
        return if (primerPais!=null && segundoPais!=null) primerPais.esLimitrofeCon(segundoPais) else false
    }

    fun necesitanTraduccion(paisA: String, paisB: String): Boolean {
        val primerPais = conjuntoPaises.find { it.nombre == paisA }
        val segundoPais = conjuntoPaises.find { it.nombre == paisB }
        return if (primerPais!=null && segundoPais!=null) primerPais.necesitanTraduccion(segundoPais) else false
    }

    fun sonPotencialesAliados(paisA: String, paisB: String): Boolean {
        val primerPais = conjuntoPaises.find { it.nombre == paisA }
        val segundoPais = conjuntoPaises.find { it.nombre == paisB }
        return if (primerPais!=null && segundoPais!=null) primerPais.sonPotencialesAliados(segundoPais) else false
    }

    fun convieneIrDeCompras(paisA: String, paisB: String): Boolean {
        val primerPais = conjuntoPaises.find { it.nombre == paisA }
        val segundoPais = conjuntoPaises.find { it.nombre == paisB }
        return if (primerPais!=null && segundoPais!=null) primerPais.convieneIrDeCompras(segundoPais) else false
    }

    fun aCuantoEquivale(valor: Double, paisA: String, paisB: String): Double {
        val primerPais = conjuntoPaises.find { it.nombre == paisA }
        val segundoPais = conjuntoPaises.find { it.nombre == paisB }
        return if (primerPais!=null && segundoPais!=null) primerPais.aCuantoEquivale(valor, segundoPais) else 0.0
    }
}