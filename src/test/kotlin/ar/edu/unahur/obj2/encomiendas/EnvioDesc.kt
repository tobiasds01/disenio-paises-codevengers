import ar.edu.unahur.obj2.encomiendas.Continental
import ar.edu.unahur.obj2.encomiendas.Insular
import ar.edu.unahur.obj2.encomiendas.Sucursal
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class EnvioDesc: DescribeSpec({
    val williamsMorris: Sucursal = Sucursal(Continental)
    williamsMorris.definirPosicion(34, 58)
    val rosario: Sucursal = Sucursal(Continental)
    rosario.definirPosicion(32, 60)
    val baseMarambio: Sucursal = Sucursal(Insular)
    baseMarambio.definirPosicion(64, 56)


    describe("Prueba de distancias") {
        williamsMorris.distanciaTierra(rosario).shouldBe(4.0)
        williamsMorris.distanciaDirecta(baseMarambio).shouldBe(30.066)
        rosario.distanciaDirecta(baseMarambio).shouldBe(32.249)
    }
}
)