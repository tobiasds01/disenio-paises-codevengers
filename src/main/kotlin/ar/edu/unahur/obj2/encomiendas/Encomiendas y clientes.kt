package ar.edu.unahur.obj2.encomiendas

import kotlin.math.roundToInt

class Cliente(val nombre: String, val dni: Int)

object GestorEncomiendas {

    fun distanciaDelEnvio(envio: Envio): Double {
        return if (envio.sucursalOrigen.zona == envio.sucursalDestino.zona) {
            envio.sucursalOrigen.distanciaTierra(envio.sucursalDestino)
        } else {
            envio.sucursalOrigen.distanciaDirecta(envio.sucursalDestino)
        }
    }

    fun precioDelEnvio(envio: Envio, transporte: Transporte): Double {
        val precio = distanciaDelEnvio(envio) * transporte.precioKm(envio)
        return if (envio.tipoDeEncomienda == EncomiendaEstandar) precio else precio * 1.5
    }

    fun tiempoDeEnvio(envio: Envio, transporte: Transporte): Int {
        return (envio.sucursalOrigen.distanciaTierra(envio.sucursalDestino) / transporte.velocidadPromedio).roundToInt()
    }

    fun registrarEnvio(envio: Envio) {
        envio.sucursalOrigen.enviosRegistrados.add(envio)
    }
}


interface Encomienda

object EncomiendaEstandar: Encomienda

object EncomiendaPrioritaria: Encomienda