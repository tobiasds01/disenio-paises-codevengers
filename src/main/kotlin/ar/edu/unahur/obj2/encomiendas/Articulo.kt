package ar.edu.unahur.obj2.encomiendas

class Articulo(
    val nombre: String,
    val peso: Int,
    val volumen: Int,
    val largo: Float,
    val ancho: Float,
    val alto: Float,
    val esPeligroso: Boolean,
    val destino: Sucursal
)