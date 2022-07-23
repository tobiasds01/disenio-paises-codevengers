package ar.edu.unahur.obj2.paises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class PaisesTest: DescribeSpec({

    val builder = PaisConcreteBuilder()
    var argentina = Pais()
    var brasil = Pais()
    val chile = Pais()
    var paraguay = Pais()
    val peru = Pais()

    describe("Un país") {
        describe("se crea uno nuevo") {
            it("lo inicializamos") { builder.reset() }

            it("con su nombre, código y continente") {
                builder.setNombre("Bolivia")
                builder.setCodigoIso3("BOL")
                builder.setContinente("América")
            }
            it("con su población"){
                builder.setPoblacion(10985059)
            }

            it("con su superficie"){ builder.setSuperficie(1098581.0) }

            it("con su moneda y cotización del dolar"){
                builder.setCodigoMoneda("BOB")
                builder.setCotizacionDelDolar(6.89)
            }
            it("con sus paises limítrofes"){
                builder.setPaisesLimitrofes(listOf(argentina, brasil, chile, paraguay, peru))
            }

            it("con sus bloques regionales"){
                builder.setBloquesRegionales(listOf("UNASUR"))
            }

            it("con sus idiomas oficiales"){
                builder.setIdiomasOficiales(listOf("Español", "Quechua", "Aymara"))
            }

            var bolivia = builder.getResult()

            describe("y comprobamos los datos"){
                it("su nombre") { bolivia.nombre.shouldBe("Bolivia") }
                it("su código ISO 3") { bolivia.codigoIso3.shouldBe("BOL") }
                it("su continente") { bolivia.continente.shouldBe("América") }
                it("su población") { bolivia.poblacion.shouldBe(10985059) }
                it("su superficie") { bolivia.superficie.shouldBe(1098581.0) }
                it("su código de moneda") { bolivia.codigoMoneda.shouldBe("BOB") }
                it("su cotización del dolar") { bolivia.cotizacionDolar.shouldBe(6.89) }
                it("sus paises limítrofes") { bolivia.paisesLimitrofes.shouldContainAll(argentina, brasil, chile, paraguay, peru) }
                it("su bloque regional") { bolivia.bloquesRegionales.shouldContain("UNASUR") }
                it("sus idiomas oficiales") { bolivia.idiomasOficiales.shouldContainAll("Español", "Quechua", "Aymara") }
            }
        }

        describe("es plurinacional"){
            builder.reset()
            builder.setIdiomasOficiales(listOf("Español"))
            argentina = builder.getResult()
            builder.reset()
            builder.setIdiomasOficiales(listOf("Español", "Guaraní"))
            paraguay = builder.getResult()
            it("con un solo idioma oficial"){ argentina.esPlurinacional().shouldBeFalse() }
            it("con más de un idioma oficial"){ paraguay.esPlurinacional().shouldBeTrue()}
        }

        describe("es insular (una isla)") {
            builder.reset()
            builder.setPaisesLimitrofes(listOf())
            var cuba = builder.getResult()
            builder.reset()
            builder.setPaisesLimitrofes(listOf(brasil, paraguay, chile))
            argentina = builder.getResult()

            it("sin paises limitrofes") { cuba.esUnaIsla().shouldBeTrue() }
            it("con paises limitrofes") { argentina.esUnaIsla().shouldBeFalse()}
        }

        describe("calcula su densidad poblacional") {
            builder.reset()
            builder.setPoblacion(10985059)
            builder.setSuperficie(1098581.0)
            val bolivia = builder.getResult()
            bolivia.densidadPoblacional().shouldBe(10)
        }

        describe("devuelve su vecino más poblado") {
            builder.reset()
            builder.setPoblacion(213993441)
            builder.setPaisesLimitrofes(listOf(argentina, paraguay))
            brasil = builder.getResult()

            builder.reset()
            builder.setPoblacion(47327407)
            builder.setPaisesLimitrofes(listOf(brasil, paraguay))
            argentina = builder.getResult()

            builder.reset()
            builder.setPoblacion(7333562)
            builder.setPaisesLimitrofes(listOf(argentina, brasil))
            paraguay = builder.getResult()

            it("probando con el país más poblado"){
                brasil.vecinoMasPoblado().shouldBe(brasil)
            }
            it("probando con sus paises limitrofes"){
                argentina.vecinoMasPoblado().shouldBe(brasil)
                paraguay.vecinoMasPoblado().shouldBe(brasil)
            }
        }
    }
})