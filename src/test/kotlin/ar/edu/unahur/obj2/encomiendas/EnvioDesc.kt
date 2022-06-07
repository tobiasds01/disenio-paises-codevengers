import ar.edu.unahur.obj2.encomiendas.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll

class EnvioDesc: DescribeSpec({
    val williamsMorris: Sucursal = Sucursal(Continental, 3000)
    williamsMorris.definirPosicion(34, 58)
    val rosario: Sucursal = Sucursal(Continental, 8000)
    rosario.definirPosicion(32, 60)
    val baseMarambio: Sucursal = Sucursal(Insular, 5000)
    baseMarambio.definirPosicion(64, 56)
    val camionMorris = Camion(10000)
    camionMorris.kilometraje = 30000
    val avionMorris = Avion()
    val camionRosario1 = Camion(8000)
    val camionRosario2 = Camion(15000)
    val barcoRosario = Barco()
    val motoRosario = Moto(20)
    val avionMarambio1 = Avion()
    val avionMarambio2 = Avion()
    val barcoMarambio = Barco()
    val camionMarambio = Camion(13000)
    williamsMorris.agregarTransporte(camionMorris)
    williamsMorris.agregarTransporte(avionMorris)


    val carlos = Cliente("carlos",12897896)
    val juan = Cliente("juan", 24569874)
    val omar = Cliente("omar",36547789)
    val david = Cliente("david", 14744584)
    val articulo1 = Articulo("Heladera",80,10, 0.80,0.90,1.90,false,rosario)
    val articulo2 = Articulo("cocina",50,8, 1.20,0.70,1.10,false,rosario)
    val articulo3 = Articulo("reactorNuclear",7000,4000, 5.00,6.90,4.00,true,baseMarambio)
    val articulo4 = Articulo("paletLadriollos",600,5000, 1.00,1.00,1.50,false,williamsMorris)
    val articulo5 = Articulo("celular",1,1,0.05,0.10,0.20,false,williamsMorris)
    val envio1 = Envio(carlos, listOf(articulo2,articulo1),williamsMorris, rosario,carlos,EncomiendaEstandar )
    val envio2 = Envio(juan, listOf(articulo3),williamsMorris, baseMarambio,carlos,EncomiendaPrioritaria )
    val envio3 = Envio(omar, listOf(articulo5),rosario,williamsMorris,omar,EncomiendaPrioritaria)
    val envio4 = Envio(david, listOf(articulo3),rosario,baseMarambio,david,EncomiendaEstandar)
    val envio5 = Envio(juan, listOf(articulo3),baseMarambio, rosario,carlos,EncomiendaPrioritaria )
    val gestorEncomienda = GestorEncomiendas


    describe(" costo de llevar un envío desde la sucursal de origen hasta la sucursal de destino "){
        it("es camion"){
            gestorEncomienda.precioDelEnvio(envio1,camionMorris).shouldBe(1200)
        }
        it("es moto"){
            gestorEncomienda.precioDelEnvio(envio3,motoRosario).shouldBe(3000.0)
        }
        it("es avion"){
            gestorEncomienda.precioDelEnvio(envio2,avionMorris).shouldBe(112747.5)
        }
        it("es barco"){
            gestorEncomienda.precioDelEnvio(envio4,barcoRosario).shouldBe(8062.250000000001)
        }
    }
    describe("puede llevar un envio"){
            motoRosario.esTransporteApto(envio2).shouldBeFalse()
            motoRosario.esTransporteApto(envio3).shouldBeTrue()
            camionMorris.esTransporteApto(envio2).shouldBeFalse()
    }
    describe("cuáles de los transportes que están en esa sucursal pueden llevar un determinado envío"){
            //williamsMorris.listaDeTransportesAptos(envio2).shouldBeEmpty()
           //rosario.listaDeTransportesAptos(envio2).shouldBe(barcoRosario)
            //baseMarambio.listaDeTransportesAptos(envio5).shouldContain(avionMarambio1)
    }

    describe("Prueba de distancias") {
        williamsMorris.distanciaTierra(rosario).shouldBe(4.0)
        williamsMorris.distanciaDirecta(baseMarambio).shouldBe(30.066)
        rosario.distanciaDirecta(baseMarambio).shouldBe(32.249)
    }
    describe("caso1"){

    }
}
)

