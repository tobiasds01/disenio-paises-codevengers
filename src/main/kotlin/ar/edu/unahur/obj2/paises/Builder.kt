package ar.edu.unahur.obj2.paises

interface Builder {
    fun reset()
    fun setNombre(value: String)
    fun setCodigoIso3(value: String)
    fun setPoblacion(value: Int)
    fun setSuperficie(value: Double)
    fun setContinente(value: String)
    fun setCodigoMoneda(value: String)
    fun setCotizacionDelDolar(value: Double)
    fun setPaisesLimitrofes(value: List<Pais>)
    fun setBloquesRegionales(value: List<String>)
    fun setIdiomasOficiales(value: List<String>)
    fun getResult(): Pais
}