package pl.ewqa.rest.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig_Xml {
    public static ResponseSpecification responseSpec;
    public static RequestSpecification ewqa_requestSpec;
    public static RequestSpecification ewqaAuth_requestSpec;

    @BeforeClass
    public static void setup(){
        ewqa_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://rest.ewqa.pl").
                setBasePath("/api/v1/").
                addHeader("Content-Type", "application/xml").// change between "application/xml" and "application/json" as required
                addHeader("Accept", "application/xml").// change between "application/xml" and "application/json" as required
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
