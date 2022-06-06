package ar.edu.unahur.obj2.encomiendas

abstract class Transporte() {
    abstract val pesoMaximo: Int
    abstract val volumen: Int
    abstract val carga: MutableList<Articulo>
    abstract val llevaArticulosPeligroso : Boolean
    abstract val velocidadPromedio : Int
    abstract val tipoDesplazamiento : Zona
    abstract fun precioKm(envio: Envio) : Int

    fun coincideDestino(articulo: Articulo): Boolean {
        return carga.size == 0 || carga[0].destino == articulo.destino
    }
}

class Camion(override val pesoMaximo: Int, override val volumen: Int): Transporte() {

    override val carga: MutableList<Articulo> = mutableListOf()
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 90
    override val tipoDesplazamiento = Continental
    override fun precioKm(envio: Envio) = 300
}

class Moto(override val pesoMaximo: Int): Transporte() {

    override val volumen: Int = 1
    override val carga: MutableList<Articulo> = mutableListOf()
    override val llevaArticulosPeligroso = false
    override val velocidadPromedio = 110
    override val tipoDesplazamiento = Continental
    fun viajeMaximo() = 400
    override fun precioKm(envio: Envio): Int{
        var total = 0
        if (envio.peso() * 80 > 500){
            total = envio.peso() * 80
        }
        else {total = 500}

        return total
    }


}

class Avion(): Transporte() {
    override val pesoMaximo = 10000
    override val volumen = 5000
    override val carga: MutableList<Articulo> = mutableListOf()
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 700
    override val tipoDesplazamiento = Insular
    override fun precioKm(envio: Envio): Int{
        var precio :Int
        if (envio.peso() <= 1500 ){
            precio = 2000
        }
        else{precio = 2500}
        return precio
    }
}

class Barco(): Transporte() {
    override val pesoMaximo = 100000
    override val volumen = 50000
    override val carga: MutableList<Articulo> = mutableListOf()
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 60
    override val tipoDesplazamiento = Insular
    override fun precioKm(envio: Envio): Int{
        var precio :Int
        if (envio.peso() <= 1500){
            precio = 200
        }
        else{precio = 250}
        return precio
    }
}