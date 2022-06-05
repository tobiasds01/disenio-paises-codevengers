package ar.edu.unahur.obj2.encomiendas

import kotlin.math.*

class Sucursal(val zona: Zona) {
    val conjuntoDeTransportes: MutableSet<Transporte> = mutableSetOf()
    val posicion: MutableList<Int> = mutableListOf()

    fun definirPosicion(x: Int, y: Int) {
        posicion.add(x)
        posicion.add(y)
    }

    fun distanciaTierra(sucursalDestino: Sucursal): Double {
        val distancia = abs(posicion[0] - sucursalDestino.posicion[0]) + abs(this.posicion[1] - sucursalDestino.posicion[1]).toDouble()
        return (distancia * 1000.0).toInt() / 1000.0
    }

    fun distanciaDirecta(sucursalDestino: Sucursal): Double {
        val distancia = sqrt((posicion[0] - sucursalDestino.posicion[0]).toDouble().pow(2.0) + (posicion[1] - sucursalDestino.posicion[1]).toDouble().pow(2.0))
        return (distancia * 1000.0).toInt() / 1000.0
    }
}

interface Zona {
}

object Continental: Zona {
}

object Insular: Zona {
}
