package ar.edu.unahur.obj2.encomiendas

import kotlin.math.*

class Sucursal(val zona: Zona, val volumenDelDeposito: Int) {
    val posicion: MutableList<Int> = mutableListOf()
    val transportes: MutableSet<Transporte> = mutableSetOf()
    val articulosEnDeposito: MutableList<Articulo> = mutableListOf()
    val articulosEnViaje: MutableList<Articulo> = mutableListOf()

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

    fun transporteApto(articulos: List<Articulo>): Transporte? {
        return transportes.find { (it.pesoMaximo) > (it.carga.sumBy { article -> article.peso }) &&
                                it.volumen > (it.carga.sumBy { article -> article.volumen } * 1.05) &&
                                articulos.all { article -> it.coincideDestino(article) } }
    }

    fun hayEspacio(articulos: List<Articulo>): Boolean {
        return (volumenDelDeposito - articulosEnDeposito.sumOf { it.volumen } - articulosEnViaje.sumOf { it.volumen }) > articulos.sumOf { it.volumen }
    }
}

interface Zona {
}

object Continental: Zona {
}

object Insular: Zona {
}
