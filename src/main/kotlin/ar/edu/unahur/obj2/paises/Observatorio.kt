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

    fun cincoElementosConMayorDensPob(): Set<String> {
        val listaPaises = conjuntoPaises.sortedByDescending { it.densidadPoblacional() }.toMutableList()
        while (listaPaises.size > 5) {
            listaPaises.removeLast()
        }
        return  listaPaises.map{ it.codigoIso3 }.toSet()
    }

    fun continenteConMasPaisesPlurinacionales(): String {
        var resultado = ""
        var numReferencia = 0
        val continentes = listOf("America", "Europa", "Asia", "Africa", "Oceania")
        continentes.forEach { cont ->
            val numPaisesPlurinacionales = conjuntoPaises.count { pais -> pais.continente == cont && pais.esPlurinacional() }
            if ( numPaisesPlurinacionales > numReferencia ) {
                resultado = cont
                numReferencia = numPaisesPlurinacionales
            }
        }
        return resultado
    }

    fun promedioDensPobPaisesInsulares(): Int {
        var totalDensPob = conjuntoPaises.sumBy { if (it.esUnaIsla()) it.densidadPoblacional() else 0 }
        var numPaisesInsulares = conjuntoPaises.count { it.esUnaIsla() }

        return totalDensPob / numPaisesInsulares
    }
}