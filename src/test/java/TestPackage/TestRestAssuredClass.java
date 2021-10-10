package TestPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRestAssuredClass
{

    @Test
    public void getHeaderResponse()
    {
        String GetHeaderResponse = RestAssured.
                given().
                request().
                when().
                get("http://httpbin.org/headers").
                then().
                assertThat().
                contentType(ContentType.JSON).
                and().
                statusCode(200).
                extract().
                response().
                asString();
        String HeaderResponse = JsonPath.from(GetHeaderResponse).getString("headers.Accept-Encoding");
        Assert.assertEquals(HeaderResponse,"gzip,deflate");
    }
    @Test
    public void getHTTPResponse()
    {
        String GetHTTPResponse = RestAssured.
                given().
                request().
                when().
                get("http://httpbin.org/get").
                then().
                assertThat().
                contentType(ContentType.JSON).
                and().
                statusCode(200).
                extract().
                response().
                asString();
        String HTTPResponse = JsonPath.from(GetHTTPResponse).getString("headers.Host");
        Assert.assertEquals(HTTPResponse,"httpbin.org");
    }
    @Test
    public void postResponseHeaders()
    {
        String PostHeaderResponse = RestAssured.
                given().
                request().
                when().
                post("http://httpbin.org/response-headers?freeform=test").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                extract().
                response().
                asString();
        String freeform = JsonPath.from(PostHeaderResponse).getString("freeform");
        Assert.assertEquals(freeform,"test");
    }
    @Test
    public void postHTTPResponse()
    {
        String PostHTTPResponse = RestAssured.
                given().
                request().
                accept(ContentType.JSON).
                when().
                post("http://httpbin.org/post").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                extract().
                response().
                asString();
        String form_http = JsonPath.from(PostHTTPResponse).getString("data");
        Assert.assertEquals(form_http,"");
    }
    @Test
    public void putHTTPResponse()
    {
        String PutHTTPResponse = RestAssured
                .given()
                .request()
                .accept(ContentType.JSON)
                .when()
                .put("http://httpbin.org/put")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .asString();

        String UpdatedResponse = JsonPath.from(PutHTTPResponse).getString("data");
        Assert.assertEquals(UpdatedResponse,"");
    }
    @Test
    public void deleteHTTPResponse()
    {
        String DeleteHTTPResponse = RestAssured
                .given()
                .request()
                .accept(ContentType.JSON)
                .when()
                .delete("http://httpbin.org/delete")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .asString();

        String DeletedResponse = JsonPath.from(DeleteHTTPResponse).getString("json");
        Assert.assertEquals(DeletedResponse,null);

    }

}
