package ar.edu.unahur.obj2.encomiendas

import kotlin.math.roundToInt

class Cliente(val nombre: String, val dni: Int) {
}

object GestorEncomiendas {
    fun distanciaDelEnvio(envio: Envio): Double {
        return if (envio.sucursalOrigen.zona == envio.sucursalDestino.zona) {
            envio.sucursalOrigen.distanciaTierra(envio.sucursalDestino)
        } else {
            envio.sucursalOrigen.distanciaDirecta(envio.sucursalDestino)
        }
    }

    fun precioDelEnvio(envio: Envio, transporte: Transporte): Double {
        var precio = distanciaDelEnvio(envio) * transporte.precioKm(envio)
        return if (envio.tipoDeEncomienda == EncomiendaEstandar) precio else precio * 1.5
    }

    fun tiempoDeEnvio(envio: Envio, transporte: Transporte): Int {
        return (envio.sucursalOrigen.distanciaTierra(envio.sucursalDestino) / transporte.velocidadPromedio).roundToInt()
    }

    fun esTransporteApto(envio: Envio, transporte: Transporte): Boolean {
        return tieneCapacidadFisica(envio, transporte) && esEnvioCoherente(envio, transporte)
    }

    fun tieneCapacidadFisica(envio: Envio, transporte: Transporte): Boolean {
        val puedeLlevarArticulosPeligrosos = if (envio.articulos.any { it.esPeligroso }) transporte.maximoDeArticulosPeligrosos(envio) else true
        return (puedeLlevarArticulosPeligrosos &&
                (transporte.pesoMaximo) > (transporte.carga.sumBy { article -> article.peso }) &&
                transporte.volumen > (transporte.carga.sumBy { article -> article.volumen } * 1.05))
    }

    fun esEnvioCoherente(envio: Envio, transporte: Transporte): Boolean {
        val coincidenRegiones = if (envio.sucursalOrigen.zona == envio.sucursalDestino.zona) envio.sucursalOrigen.zona == transporte.tipoDesplazamiento else false
        return coincidenRegiones && envio.articulos.all { article -> transporte.coincideDestino(article) }
    }

    fun registrarEnvio(envio: Envio) {
        envio.sucursalOrigen.enviosRegistrados.add(envio)

    }
}


interface Encomienda

object EncomiendaEstandar: Encomienda

object EncomiendaPrioritaria: Encomienda