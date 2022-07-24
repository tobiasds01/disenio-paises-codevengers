package ar.edu.unahur.obj2.paises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec ({
    val builder = PaisConcreteBuilder()

    builder.reset()
    builder.setNombre("Argentina")
    builder.setBloquesRegionales(mutableSetOf("SELA"))
    builder.setIdiomasOficiales(mutableSetOf("Español"))
    builder.setCotizacionDelDolar(190.0)
    val argentina = builder.getResult()

    builder.reset()
    builder.setNombre("Brasil")
    builder.setBloquesRegionales(mutableSetOf("SELA"))
    builder.setIdiomasOficiales(mutableSetOf("Portugues"))
    val brasil = builder.getResult()

    builder.reset()
    builder.setNombre("Paraguay")
    builder.setBloquesRegionales(mutableSetOf("SELA"))
    builder.setIdiomasOficiales(mutableSetOf("Español", "Guarani"))
    val paraguay = builder.getResult()

    builder.reset()
    builder.setNombre("Chile")
    builder.setBloquesRegionales(mutableSetOf("AL"))
    builder.setIdiomasOficiales(mutableSetOf("Español"))
    val chile = builder.getResult()

    builder.reset()
    builder.setNombre("Peru")
    val peru = builder.getResult()

    builder.reset()
    builder.setNombre("Bolivia")
    builder.setIdiomasOficiales(mutableSetOf("Español"))
    builder.setCotizacionDelDolar(6.89)
    val bolivia = builder.getResult()

    Observatorio.conjuntoPaises.add(argentina)
    Observatorio.conjuntoPaises.add(brasil)
    Observatorio.conjuntoPaises.add(paraguay)
    Observatorio.conjuntoPaises.add(bolivia)
    Observatorio.conjuntoPaises.add(chile)
    Observatorio.conjuntoPaises.add(peru)


    describe("Probamos en el observatorio"){
        describe("son limitrofes"){
            argentina.paisesLimitrofes.add(brasil)
            argentina.paisesLimitrofes.add(chile)
            argentina.paisesLimitrofes.add(paraguay)
            peru.paisesLimitrofes.add(brasil)
            peru.paisesLimitrofes.add(chile)

            it("con paises que si se tienen en las listas"){
                Observatorio.sonLimitrofes("Argentina", "Brasil").shouldBeTrue()
            }
            it("con paises que no se tienen"){
                Observatorio.sonLimitrofes("Argentina","Peru").shouldBeFalse()
            }
        }

        describe("necesitan traducción"){
            it("con paises que hablan el mismo idioma"){
                Observatorio.necesitanTraduccion("Argentina", "Paraguay").shouldBeFalse()
            }
            it("con paises que no lo hacen"){
                Observatorio.necesitanTraduccion("Brasil", "Paraguay").shouldBeTrue()
            }
        }

        describe("son potenciales aliados") {
            it("con ambos requisitos cumplidos"){
                Observatorio.sonPotencialesAliados("Argentina", "Paraguay").shouldBeTrue()
            }
            it("con solo un requisito cumplido"){
                Observatorio.sonPotencialesAliados("Chile", "Paraguay").shouldBeFalse()
                Observatorio.sonPotencialesAliados("Argentina", "Brasil").shouldBeFalse()
            }
            it("con ningún requisito cumplido"){
                Observatorio.sonPotencialesAliados("Brasil", "Chile").shouldBeFalse()
            }
        }

        describe("conviene ir de compras"){
            it("con una cotización mayor"){
                Observatorio.convieneIrDeCompras("Argentina", "Bolivia").shouldBeFalse()
            }
            it("con una cotización menor"){
                Observatorio.convieneIrDeCompras("Bolivia","Argentina").shouldBeTrue()
            }
        }

        describe("a cuanto equivale el dinero"){
            Observatorio.aCuantoEquivale(689.0, "Bolivia", "Argentina").shouldBe(19000.0)

            Observatorio.aCuantoEquivale(190.0, "Argentina", "Bolivia").shouldBe(6.89)
        }
    }
})