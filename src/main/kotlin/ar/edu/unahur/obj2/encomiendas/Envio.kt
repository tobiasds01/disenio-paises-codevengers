package ar.edu.unahur.obj2.encomiendas

class Envio(val cliente: Cliente, val articulos: List<Articulo>, val sucursalOrigen: Sucursal,
            val sucursalDestino: Sucursal, val receptor: Cliente)  {
    val transporte = sucursalOrigen.transporteApto(articulos)

    fun puedeRealizarse(): Boolean {
        return (transporte != null) && sucursalDestino.hayEspacio(articulos)
    }
}
