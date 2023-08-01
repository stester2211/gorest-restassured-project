package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page","1");
        qParam.put("per_page","25");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void extractTitle()
    {
        List<String> allTitle= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All titles are : " + allTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void extractTotalRecord()
    {
        Integer total= response.extract().path("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All total records are : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void extractBody()
    {
        List<String> bodyOfRecord= response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All body are : " + bodyOfRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void extractUserId()
    {
        List<Integer> userIdAllRecord= response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All records userid are : " + userIdAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void extractAllTitleRecords()
    {
        List<Integer> titleOfAllRecord= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All title records are : " + titleOfAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void extractAllTitleByUserId()
    {
        List<Integer> allRecord= response.extract().path("findAll{it.user_id == 4136850}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All records of userid are : " + allRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 2671
    @Test
    public void extractBodyOfAllRecord() {
        List<HashMap<String, ?>> bodyOfRecord = response.extract().path("findAll{it.id == 57618}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  body of all records whose id = 57618: " + bodyOfRecord   );
        System.out.println("------------------End of Test---------------------------");
    }
}
