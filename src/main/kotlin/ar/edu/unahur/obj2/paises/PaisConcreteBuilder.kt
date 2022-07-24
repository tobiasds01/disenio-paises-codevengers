package ar.edu.unahur.obj2.paises

class PaisConcreteBuilder: Builder {
    lateinit var nuevoPais: Pais
    override fun reset() { nuevoPais = Pais() }
    override fun setNombre(value: String) { nuevoPais.nombre = value}
    override fun setCodigoIso3(value: String) { nuevoPais.codigoIso3 = value}
    override fun setPoblacion(value: Int) { nuevoPais.poblacion = value }
    override fun setSuperficie(value: Double) { nuevoPais.superficie = value}
    override fun setContinente(value: String) { nuevoPais.continente = value }
    override fun setCodigoMoneda(value: String) { nuevoPais.codigoMoneda = value }
    override fun setCotizacionDelDolar(value: Double) { nuevoPais.cotizacionDolar = value }
    override fun setPaisesLimitrofes(value: MutableSet<Pais>) { nuevoPais.paisesLimitrofes = value }
    override fun setBloquesRegionales(value: MutableSet<String>) { nuevoPais.bloquesRegionales = value }
    override fun setIdiomasOficiales(value: MutableSet<String>) { nuevoPais.idiomasOficiales = value }
    override fun getResult(): Pais = nuevoPais
}