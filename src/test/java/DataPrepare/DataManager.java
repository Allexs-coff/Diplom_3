package DataPrepare;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DataManager {



    private Map<String, String> testData = new HashMap<>();
    private Response response;


    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    public void testDataGen(){
        Random rn = new Random();
        int randomNum = rn.nextInt(9999999);
        testData.put("email","test-data"+randomNum+"@yandex.ru");
        testData.put("password","password123");
        testData.put("name","Username"+randomNum);
    }

    public void createUniqueUser() {
        testDataGen();
        String userData = "{\n" +
                "\"email\": \"" + testData.get("email") + "\",\n" +
                "\"password\": \"" + testData.get("password") + "\",\n" +
                "\"name\": \"" + testData.get("name") + "\"\n}";
        createUserWithUserData(userData);
        checkingResponseCode(200);
        checkAttribute("success", true);
        checkAttribute("user.email", testData.get("email"));
        checkAttribute("user.name", testData.get("name"));
        checkAttribute("accessToken", "notNullValue");
        checkAttribute("refreshToken", "notNullValue");
    }

    private void checkingResponseCode(int code){
        response.then().statusCode(code);
    }

    private void createUserWithUserData(String userData){
        response = given().header("Content-type", "application/json").and().body(userData).when().post("/api/auth/register");
    }
    public void testWrongDataGen(){
        Random rn = new Random();
        int randomNum = rn.nextInt(9999999);
        testData.put("email","test-data"+randomNum+"@yandex.ru");
        testData.put("password","1");
        testData.put("name","Username"+randomNum);
    }
        private void checkAttribute(String attribute, Object expectedValue){
            if (expectedValue.equals("notNullValue")){
                response.then().assertThat().body(attribute,notNullValue());
            }
            else {
                response.then().assertThat().body(attribute,equalTo(expectedValue));
            }
        }

    public void authWithUserData(){
        String userData = "{\n" +
                "\"email\": \""+testData.get("email")+"\",\n" +
                "\"password\": \""+testData.get("password")+"\",\n" +
                "\"name\": \""+testData.get("name")+"\"\n}";
        login(userData);
    }


    private void login(String userData){
        response = given().header("Content-type", "application/json").and().body(userData).when().post("/api/auth/login");
    }

    public void deleteUser(String userAccedesToken)
    {
        StringBuilder builder = new StringBuilder(userAccedesToken);
        String token = builder.substring(builder.indexOf("Bearer ",0)+7,builder.length());
        response = given().auth().oauth2(token).and().body("{"+userAccedesToken+"}").when().delete("/api/auth/user");
        response.then().statusCode(202);
    }

    public Map<String, String> getTestData() {
        return testData;
    }


}
