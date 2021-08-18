// 1 - Pacote
package petstore;

//  2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.google.common.collect.Range.all;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

// 3 - Classe
public class Pet {

    // 3.1 - Atributos
    // endereço entidade Pet
    String uri = "https://petstore.swagger.io/v2/pet";

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test // Identifica o método ou função como um teste
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // dado - Quando - Então
        // Giv- When - Then

        given() // Dado
                .contentType("application/json") // Comum em API REST - antigas eram "text/xml"
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Então
                .log().all()
                .statusCode(200)
                .body("name",is("Max"))
                .body("status", is("available"))
                
        ;

    }
   // @Test
    public void  consultarPet(){
        String petId = "128796";

        given()

                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)


        ;


    }
}
