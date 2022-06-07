package ar.edu.unahur.obj2.encomiendas

class Articulo(
    val nombre: String,
    val peso: Int,
    val volumen: Int,
    val largo: Double,
    val ancho: Double,
    val alto:  Double,
    val esPeligroso: Boolean,
    val destino: Sucursal
)