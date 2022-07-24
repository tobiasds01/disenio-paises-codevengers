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
    var bolivia = Pais()

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
                builder.setPaisesLimitrofes(mutableSetOf(argentina, brasil, chile, paraguay, peru))
            }

            it("con sus bloques regionales"){
                builder.setBloquesRegionales(mutableSetOf("UNASUR"))
            }

            it("con sus idiomas oficiales"){
                builder.setIdiomasOficiales(mutableSetOf("Español", "Quechua", "Aymara"))
            }

            bolivia = builder.getResult()

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
            argentina.idiomasOficiales.add("Español")
            paraguay.idiomasOficiales.add("Español")
            paraguay.idiomasOficiales.add("Guarani")
            it("con un solo idioma oficial"){ argentina.esPlurinacional().shouldBeFalse() }
            it("con más de un idioma oficial"){ paraguay.esPlurinacional().shouldBeTrue()}
        }

        describe("es insular (una isla)") {
            builder.reset()
            builder.setPaisesLimitrofes(mutableSetOf())
            var cuba = builder.getResult()
            builder.reset()
            builder.setPaisesLimitrofes(mutableSetOf(chile))
            var peru = builder.getResult()

            it("sin paises limitrofes") { cuba.esUnaIsla().shouldBeTrue() }
            it("con paises limitrofes") { peru.esUnaIsla().shouldBeFalse()}
        }

        describe("calcula su densidad poblacional") {
            builder.reset()
            builder.setPoblacion(10985059)
            builder.setSuperficie(1098581.0)
            bolivia = builder.getResult()
            bolivia.densidadPoblacional().shouldBe(10)
        }

        describe("devuelve su vecino más poblado") {
            brasil.poblacion = 213993441
            brasil.paisesLimitrofes.add(argentina)
            brasil.paisesLimitrofes.add(paraguay)

            argentina.poblacion = 47327407
            argentina.paisesLimitrofes.add(brasil)
            argentina.paisesLimitrofes.add(paraguay)

            paraguay.poblacion = 7333562
            paraguay.paisesLimitrofes.add(brasil)
            paraguay.paisesLimitrofes.add(argentina)

            it("probando con el país más poblado"){
                brasil.vecinoMasPoblado().shouldBe(brasil)
            }
            it("probando con sus paises limitrofes"){
                argentina.vecinoMasPoblado().shouldBe(brasil)
                paraguay.vecinoMasPoblado().shouldBe(brasil)
            }
        }
    }

    describe("Dos paises") {
        describe("son limitrofes"){
            it("con paises que si se tienen en las listas"){
                brasil.esLimitrofeCon(argentina).shouldBeTrue()
                argentina.esLimitrofeCon(brasil).shouldBeTrue()
            }
            it("con paises que no se tienen"){
                peru.esLimitrofeCon(argentina).shouldBeFalse()
                argentina.esLimitrofeCon(peru).shouldBeFalse()
            }
        }

        describe("necesitan traducción"){
            brasil.idiomasOficiales.add("Portugues")

            it("con paises que hablan el mismo idioma"){
                argentina.necesitanTraduccion(paraguay).shouldBeFalse()
                paraguay.necesitanTraduccion(argentina).shouldBeFalse()
            }
            it("con paises que no lo hacen"){
                brasil.necesitanTraduccion(paraguay).shouldBeTrue()
                paraguay.necesitanTraduccion(brasil).shouldBeTrue()
            }
        }

        describe("son potenciales aliados"){
            argentina.bloquesRegionales.add("SELA")
            argentina.idiomasOficiales.add("Español")
            brasil.bloquesRegionales.add("SELA")
            brasil.idiomasOficiales.add("Portugues")
            paraguay.bloquesRegionales.add("SELA")
            paraguay.idiomasOficiales.add("Español")

            chile.idiomasOficiales.add("Español")
            chile.bloquesRegionales.add("AL")

            it("con ambos requisitos cumplidos"){
                argentina.sonPotencialesAliados(paraguay).shouldBeTrue()
            }
            it("con solo un requisito cumplido"){
                chile.sonPotencialesAliados(paraguay).shouldBeFalse()
                brasil.sonPotencialesAliados(argentina).shouldBeFalse()
            }
            it("con ningún requisito cumplido"){
                brasil.sonPotencialesAliados(chile).shouldBeFalse()
            }
        }

        describe("conviene ir de compras"){
            argentina.cotizacionDolar = 190.0
            bolivia.cotizacionDolar = 6.89

            it("con una cotización mayor"){
                argentina.convieneIrDeCompras(bolivia).shouldBeFalse()
            }
            it("con una cotización menor"){
                bolivia.convieneIrDeCompras(argentina).shouldBeTrue()
            }
        }

        describe("a cuanto equivale el dinero"){
            bolivia.aCuantoEquivale(689.0, argentina).shouldBe(19000.0)

            argentina.aCuantoEquivale(190.0, bolivia).shouldBe(6.89)
        }
    }
})