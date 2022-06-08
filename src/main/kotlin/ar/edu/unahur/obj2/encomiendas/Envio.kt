package ar.edu.unahur.obj2.encomiendas

class Envio(val cliente: Cliente, val articulos: List<Articulo>, val sucursalOrigen: Sucursal,
            val sucursalDestino: Sucursal, val receptor: Cliente, val tipoDeEncomienda: Encomienda)  {

    private val transporteDesignado = sucursalOrigen.transporteApto(this)

    fun puedeRealizarse(): Boolean {
        return (transporteDesignado != null) && sucursalDestino.hayEspacio(articulos)
    }

    fun peso() = articulos.sumOf { it.peso }
}