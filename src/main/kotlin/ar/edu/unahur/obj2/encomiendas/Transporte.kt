package ar.edu.unahur.obj2.encomiendas

abstract class Transporte() {
    abstract val pesoMaximo: Int
    abstract val volumen: Int
    abstract val llevaArticulosPeligroso : Boolean
    abstract val velocidadPromedio : Int
    abstract val tipoDesplazamiento : Zona
    var kilometraje: Int = 0
    val enviosDesignados: MutableList<Envio> = mutableListOf()
    val carga: MutableList<Articulo> = mutableListOf()

    fun tieneCapacidadFisica(envio: Envio): Boolean {
        val puedeLlevarArticulosPeligrosos = if (envio.articulos.any { it.esPeligroso }) this.maximoDeArticulosPeligrosos(envio) else true
        return (puedeLlevarArticulosPeligrosos &&
                (this.pesoMaximo) > (this.carga.sumBy { article -> article.peso }) &&
                this.volumen > (this.carga.sumBy { article -> article.volumen } * 1.05))
    }

    fun sirvePara(envio: Envio) = this.tipoDesplazamiento == envio.sucursalDestino.zona || this.tipoDesplazamiento == Regional

    fun esEnvioCoherente(envio: Envio): Boolean {

        return this.sirvePara(envio) && envio.articulos.all { article -> this.coincideDestino(article) }
    }

    fun esTransporteApto(envio: Envio): Boolean {
        return this.tieneCapacidadFisica(envio) && this.esEnvioCoherente(envio)
    }



    fun cargar(envio: Envio){
        if(this.esTransporteApto(envio)){
            enviosDesignados.add(envio)
        }
        else{Error("NO PUEDE CARGAR ESE ENVIO EN EL TRANSPORTE")}
    }

    abstract fun precioKm(envio: Envio) : Int

    abstract fun maximoDeArticulosPeligrosos(envio: Envio): Boolean

    fun sumarKilometraje(metros: Int) { kilometraje += metros }

    fun coincideDestino(articulo: Articulo): Boolean {
        return carga.size == 0 || carga[0].destino == articulo.destino
    }

    fun sumarEnvio(envio: Envio) {
        //Dentro del envío tenemos los datos del cliente, los artículos a incluir, la sucursal destino y
        //la persona que retirará el envío en la sucursal destino.
        enviosDesignados.add(envio)
        envio.articulos.forEach { carga.add(it) }
    }
}

class Camion(override val volumen: Int): Transporte() {
    override val pesoMaximo: Int = maxOf(500, (3000 - this.desgastePorKilometraje()))
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 90
    override val tipoDesplazamiento = Continental

    override fun precioKm(envio: Envio) = 300

    override fun maximoDeArticulosPeligrosos(envio: Envio): Boolean = true

    fun desgastePorKilometraje() = kilometraje / 100
}

class Moto(override val pesoMaximo: Int): Transporte() {
    override val volumen: Int = 1
    override val llevaArticulosPeligroso = false
    override val velocidadPromedio = 110
    override val tipoDesplazamiento = Continental

    fun viajeMaximo() = 400

    override fun maximoDeArticulosPeligrosos(envio: Envio): Boolean = false

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
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 700
    override val tipoDesplazamiento = Regional

    override fun precioKm(envio: Envio): Int {
        var precio :Int
        if (envio.peso() <= 1500 ){
            precio = 2000
        }
        else{precio = 2500}
        return precio
    }

    override fun maximoDeArticulosPeligrosos(envio: Envio): Boolean {
        return (carga.filter { it.esPeligroso }.size + envio.articulos.filter { it.esPeligroso }.size) <= 10
    }
}

class Barco(): Transporte() {
    override val pesoMaximo = 100000
    override val volumen = 50000
    override val llevaArticulosPeligroso = true
    override val velocidadPromedio = 60
    override val tipoDesplazamiento = Regional

    override fun precioKm(envio: Envio): Int{
        var precio :Int
        if (envio.peso() <= 1500){
            precio = 200
        }
        else{precio = 250}
        return precio
    }

    override fun maximoDeArticulosPeligrosos(envio: Envio): Boolean {
        return (carga.filter { it.esPeligroso }.size + envio.articulos.filter { it.esPeligroso }.size) <= 100
    }
}