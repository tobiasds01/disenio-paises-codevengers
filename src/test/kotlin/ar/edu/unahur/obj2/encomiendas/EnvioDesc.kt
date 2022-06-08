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
    val camionMorris = Camion(15000)
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
    rosario.transportes = mutableListOf(camionRosario1, camionRosario2, motoRosario, barcoRosario)
    baseMarambio.transportes = mutableListOf(avionMarambio1, avionMarambio2, camionMarambio, barcoMarambio)

    val carlos = Cliente("carlos", 12897896)
    val juan = Cliente("juan", 24569874)
    val omar = Cliente("omar", 36547789)
    val david = Cliente("david", 14744584)
    val cesar = Cliente("cesar", 40569874)
    val articulo1 = Articulo("Heladera", 80, 10, 0.80, 0.90, 1.90, false, rosario)
    val articulo2 = Articulo("cocina", 50, 8, 1.20, 0.70, 1.10, false, rosario)
    val articulo3 = Articulo("reactorNuclear", 7000, 4000, 5.00, 6.90, 4.00, true, baseMarambio)
    val articulo4 = Articulo("paletLadriollos", 600, 5000, 1.00, 1.00, 1.50, false, williamsMorris)
    val articulo5 = Articulo("celular", 1, 1, 0.05, 0.10, 0.20, false, williamsMorris)
    val envio1 = Envio(carlos, listOf(articulo2, articulo1), williamsMorris, rosario, carlos, EncomiendaEstandar)
    val envio2 = Envio(juan, listOf(articulo3), williamsMorris, baseMarambio, carlos, EncomiendaPrioritaria)
    val envio3 = Envio(omar, listOf(articulo5), rosario, williamsMorris, omar, EncomiendaPrioritaria)
    val envio4 = Envio(david, listOf(articulo3), rosario, baseMarambio, david, EncomiendaEstandar)
    val envio5 = Envio(juan, listOf(articulo3), baseMarambio, rosario, carlos, EncomiendaPrioritaria)
    val gestorEncomienda = GestorEncomiendas


    describe(" costo de llevar un envío desde la sucursal de origen hasta la sucursal de destino ") {
        it("es camion") {
            gestorEncomienda.precioDelEnvio(envio1, camionMorris).shouldBe(1200)
        }
        it("es moto") {
            gestorEncomienda.precioDelEnvio(envio3, motoRosario).shouldBe(3000.0)
        }
        it("es avion") {
            gestorEncomienda.precioDelEnvio(envio2, avionMorris).shouldBe(112747.5)
        }
        it("es barco") {
            gestorEncomienda.precioDelEnvio(envio4, barcoRosario).shouldBe(8062.250000000001)
        }
    }
    describe("puede llevar un envio") {
        motoRosario.esTransporteApto(envio2).shouldBeFalse()
        motoRosario.esTransporteApto(envio3).shouldBeTrue()
        camionMorris.esTransporteApto(envio2).shouldBeFalse()
        avionMorris.esTransporteApto(envio2).shouldBeTrue()
    }
    describe("cuáles de los transportes que están en esa sucursal pueden llevar un determinado envío") {
        williamsMorris.listaDeTransportesAptos(envio2).shouldContain(avionMorris)
        rosario.listaDeTransportesAptos(envio2).shouldContain(barcoRosario)
        baseMarambio.listaDeTransportesAptos(envio5).shouldContainAll(avionMarambio1, avionMarambio2, barcoMarambio)
    }

    describe("Prueba de distancias") {
        williamsMorris.distanciaTierra(rosario).shouldBe(4.0)
        williamsMorris.distanciaDirecta(baseMarambio).shouldBe(30.066)
        rosario.distanciaDirecta(baseMarambio).shouldBe(32.249)
    }
    describe("caso1") {
        camionMorris.cargar(envio1)
        avionMorris.cargar(envio2)
        val envio6 = Envio(cesar, listOf(articulo5), williamsMorris, rosario, cesar, EncomiendaEstandar)
        williamsMorris.listaDeTransportesAptos(envio6).shouldContain(camionMorris)
    }

    describe("caso2") {
        val denis = Cliente("denis",26123752)
        val art10 = Articulo("garrafa", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art11 = Articulo("garrafaN", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art12 = Articulo("garrafaS", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art13 = Articulo("garrafaD", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art14 = Articulo("garrafaA", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art15 = Articulo("garrafaG", 25, 800, 0.80, 0.90, 1.90, true, williamsMorris)
        val art16 = Articulo("garrafaH", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art17 = Articulo("garrafaJ", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art18 = Articulo("garrafaK", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art19 = Articulo("garrafaL", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art20 = Articulo("garrafaM", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art21 = Articulo("garrafaB", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val art22 = Articulo("garrafaP", 25, 100, 0.80, 0.90, 1.90, true, williamsMorris)
        val envio7 = Envio(denis, listOf(art10,art11,art12,art13,art14,art15,art16,art17,art18,art19,art20,art21,art22), williamsMorris, rosario, david, EncomiendaEstandar)

        williamsMorris.listaDeTransportesAptos(envio7).shouldContain(camionMorris)



    }
    describe("caso 3"){
        val articulo7 =Articulo("paletCeresita", 600, 1500, 1.00, 1.00, 1.50, false, baseMarambio)
        val articulo8 =Articulo("paletCal", 750, 1700, 1.00, 1.00, 1.50, false, baseMarambio)
        val articulo9 =Articulo("paletCemento", 900, 2000, 1.00, 1.00, 1.50, false, baseMarambio)
        val envio8 = Envio(omar, listOf(articulo7,articulo8),baseMarambio,rosario,omar,EncomiendaEstandar)
        val envio9 = Envio(david, listOf(articulo9),baseMarambio,rosario,david,EncomiendaEstandar)
        avionMarambio1.cargar(envio8)
        baseMarambio.listaDeTransportesAptos(envio9).shouldContainAll(avionMarambio2,barcoMarambio)



    }


})

