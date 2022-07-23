package ar.edu.unahur.obj2.paises

import kotlin.math.roundToInt

class Pais(
    var nombre: String = "",
    var codigoIso3: String = "",
    var poblacion: Int? = null,
    var superficie: Double? = null,
    var continente: String = "",
    var codigoMoneda: String = "",
    var cotizacionDolar: Double? = null,
    var paisesLimitrofes: List<Pais> = mutableListOf(),
    var bloquesRegionales: List<String> = mutableListOf(),
    var idiomasOficiales: List<String> = mutableListOf()) {

    fun esPlurinacional(): Boolean {
        return idiomasOficiales.size > 1
    }

    fun esUnaIsla(): Boolean {
        return paisesLimitrofes.isEmpty()
    }

    fun densidadPoblacional(): Int {
        return (poblacion!! / superficie!!).roundToInt()
    }

    fun vecinoMasPoblado(): Pais {
        val limitrofeMasPoblado =  paisesLimitrofes.maxByOrNull{ it.poblacion!! }
        return if (limitrofeMasPoblado!!.poblacion!! > poblacion!!) limitrofeMasPoblado else this
    }

    fun esLimitrofeCon(pais: Pais): Boolean {
        return paisesLimitrofes.contains(pais)
    }

    fun necesitanTraduccion(pais: Pais): Boolean {
        return !(idiomasOficiales.contains { pais.idiomasOficiales.forEach { it } })
    }

    fun sonPotencialesAliados(pais: Pais): Boolean {
        return !necesitanTraduccion(pais) && compartenBloqueRegional(pais)
    }

    fun convieneIrDeCompras(pais: Pais): Boolean {
        return pais.cotizacionDolar!! > cotizacionDolar!!
    }

    fun aCuantoEquivale(valor: Double, pais: Pais): Double {
        return pais.dolarAMonedaLocal(this.monedaLocalADolar(valor))
    }


    fun compartenBloqueRegional(pais: Pais): Boolean {
        return bloquesRegionales.contains { pais.bloquesRegionales.forEach { it } }
    }

    fun monedaLocalADolar(valor: Double): Double {
        return valor / this.cotizacionDolar!!
    }

    fun dolarAMonedaLocal(valor: Double): Double {
        return valor * this.cotizacionDolar!!
    }
}