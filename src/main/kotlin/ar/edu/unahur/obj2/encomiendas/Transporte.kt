package ar.edu.unahur.obj2.encomiendas

abstract class Transporte() {
    abstract val pesoMaximo: Int
    abstract val volumen: Int
    abstract val carga: MutableList<Articulo>

    fun coincideDestino(articulo: Articulo): Boolean {
        return carga.size == 0 || carga[0].destino == articulo.destino
    }
}

class Camion(override val pesoMaximo: Int): Transporte() {
    override val volumen: Int = 0
    override val carga: MutableList<Articulo> = mutableListOf()
}

class Moto(): Transporte() {
    override val pesoMaximo: Int = 0
    override val volumen: Int = 0
    override val carga: MutableList<Articulo> = mutableListOf()
}

class Avion(): Transporte() {
    override val pesoMaximo: Int = 0
    override val volumen: Int = 0
    override val carga: MutableList<Articulo> = mutableListOf()
}

class Barco(): Transporte() {
    override val pesoMaximo: Int = 0
    override val volumen: Int = 0
    override val carga: MutableList<Articulo> = mutableListOf()
}