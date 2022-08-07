package com.everis.base.stepDefinitions;

import com.everis.base.JuegosService;
//import com.everis.base.steps.NetflixSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class JuegosSD {

    @Steps
    public JuegosService juegos;
    @Given("que la aplicación esta operativo")
    public void queLaAplicaciónEstaOperativo() {

    }

    @When("muestra información de juegos de plataforma {string} y {string}")
    public void muestraInformaciónDeJuegosDePlataformaY(String platform , String category ) {
        juegos.listJuegos(platform, category);
    }

    @Then("valida que la respuesta sea {int}")
    public void validaQueLaRespuestaSea(int i) {
        juegos.validateStatusCode(i);
    }


    @When("se digita mas plataforma {string} y categoria {string}")
    public void seDigitaMasPlataformaYCategoria(String platform, String category) {
        juegos.getJuegos(platform, category);
    }





}
