package pl.ewqa.rest.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig_Json {
    public static ResponseSpecification responseSpec;
    public static RequestSpecification ewqa_requestSpec;
    public static RequestSpecification ewqaAuth_requestSpec;

    @BeforeClass
    public static void setup(){
        ewqa_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://rest.ewqa.pl").
                setBasePath("/api/v1/").
                addHeader("Content-Type", "application/testJson").// change between "application/testXml" and "application/testJson" as required
                addHeader("Accept", "application/testJson").// change between "application/testXml" and "application/testJson" as required
                build().log().all();

//        auth_requestSpec = new RequestSpecBuilder().
//                setBaseUri("http://rest.ewqa.pl").
//                setBasePath("/v2/").
//                addHeader("X-Auth-Token", "482ad799f56e46ffadcfb82b544c4742").
//                addHeader("X-Response-Control", "minified").
//                build();

        RestAssured.requestSpecification = ewqa_requestSpec; // change this to ewqa_requestSpec or ewqaAuth_requestSpec as required

        responseSpec = new ResponseSpecBuilder().
//                expectStatusCode(200).
                build();
        RestAssured.responseSpecification = responseSpec;
    }
}
