package com.everis.base;

import com.everis.base.models.Book;
import com.everis.base.models.User;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JuegosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JuegosService.class);
    static private final String BASE_URL = "https://www.freetogame.com/api/games/";

    private String cadena;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;
    private String bodyPost;

    @Before
    public String cambioUrl(String url){
        cadena = "";
        return cadena;
    }

    @Before
    public void init() {
        if(cadena.equals("")){
            LOGGER.info(" Inicializa el constructor request ");
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .build();

            LOGGER.info(" Inicializa el constructor response ");
            responseSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .build();
        }else{
            LOGGER.info(" Inicializa el constructor request ");
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(cadena)
                    .build();

            LOGGER.info(" Inicializa el constructor response ");
            responseSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .build();
        }

    }

    @Step("obtiene lista de juegos")
    public void listJuegos(String platform, String category) {

        given().
                spec(requestSpec).
                queryParam("platform", platform).
                queryParam("category", category).
                when().
                get("").
                then().
                spec(responseSpec).
                and();
        String titulo = lastResponse().jsonPath().param("", 0).getString("title");
        String id = lastResponse().jsonPath().param("", 0).getString("id");

        System.out.println("ID: " + id);
        System.out.println("TITULOS: " + titulo);

        System.out.println("----------------------------------------------------------------------------------");
    }
    public void getJuegos(String platform, String category) {

        given()
                .spec(requestSpec)
                .queryParam("platform", platform).
                queryParam("category", category).
                when()
                .get("").
                then().
                and();
    }


    public void validateStatusCode(int i) {
        assertThat(lastResponse().statusCode(), is(i));

        System.out.println(stadoCorrecto(i));
    }

    public String stadoCorrecto(int i){
        String resultado = "";
        if(lastResponse().statusCode() == i){
            resultado = "Se validó el estado: " + i;
        }
        return  resultado;
    }

}
