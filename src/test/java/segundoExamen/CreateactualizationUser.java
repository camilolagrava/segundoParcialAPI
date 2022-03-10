package segundoExamen;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateactualizationUser {


    @Test
    public void createTest(){
        String s = "{" +
                "'Email': 'camilolagrava@gmail.com'," +
                "'FullName' : 'camilo'," +
                "'Password':'camilo123'}";
        JSONObject body = new JSONObject(s);
        Response response=given()

                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/user.json");

        response.then()
                .statusCode(200)
                .body("FullName",equalTo("camilo"))
                .log().all();
    }

    @Test
    public void actializacionTest() {
        String s = "{" +
                "'FullName' : 'Milo'" +
                "}";
        JSONObject body = new JSONObject(s);
        Response response=given()
                .auth()
                .preemptive()
                .basic("camilolagrava@gmail.com","camilo123")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/user/0.json");

        response.then()
                .statusCode(200)
                .body("FullName",equalTo("Milo"))
                .log().all();
    }


}
