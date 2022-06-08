package ar.edu.unahur.obj2.encomiendas

import kotlin.math.*

class Sucursal(val zona: Zona, val volumenDelDeposito: Int) {
    val posicion: MutableList<Int> = mutableListOf()
    var transportes = mutableListOf<Transporte>()
    val enviosRegistrados: MutableList<Envio> = mutableListOf()
    val articulosEnDeposito: MutableList<Articulo> = mutableListOf()
    val articulosEnViaje: MutableList<Articulo> = mutableListOf()


    fun agregarTransporte(transporte: Transporte){
        if(transportes.contains(transporte)){
            throw Exception("YA TIENE ESE TRANSPORTE")
        }
        else{transportes.add(transporte)}
    }


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

    fun transporteApto(envio: Envio): Transporte? {
        return transportes.find { it.esTransporteApto(envio) }
    }

    fun hayEspacio(articulos: List<Articulo>): Boolean {
        return (volumenDelDeposito - articulosEnDeposito.sumOf { it.volumen } - articulosEnViaje.sumOf { it.volumen }) > articulos.sumOf { it.volumen }
    }

    fun listaDeTransportesAptos(envio: Envio): List<Transporte> {
        return transportes.filter { it.esTransporteApto(envio) }
    }

    fun designarTransporte(envio: Envio) {
        try {
            listaDeTransportesAptos(envio).minByOrNull{ GestorEncomiendas.precioDelEnvio(envio, it) }?.sumarEnvio(envio)
        } catch (ex: Exception) {
            throw Exception("NO HAY TRANSPORTES DISPONIBLES PARA REALIZAR ESE ENVIO.")
        }
    }
}

interface Zona

object Continental: Zona
object Insular: Zona
object Regional : Zona