package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest
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
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void extractAllIds()
    {
        List<Integer>  allId=response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Id's are : "+allId);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void extractAllNames()
    {
        List<String>  allName=response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Id's are : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractSingleName()
    {
        //3. Extract the name of 5th object
        String singleName =response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name is : "+singleName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void extractAllNamesObject()
    {
        List<String> allName=response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object name whose status is inactive : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void extractAllGender()
    {
        List<String> allName=response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All genders whose status is active : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void printName()
    {
        List<String> allName=response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All name of gender female : "+allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void printEmail()
    {
        List<String> allEmail=response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All email whose status is inactive : "+allEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void printId()
    {
        List<String> allId=response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All id whose gender is male : "+allId);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void getStatus()
    {
        List<String> allStatus=response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All status : "+allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void getEmail()
    {
        List<String> singleEmail =response.extract().path("findAll{it.name=='Sukanya Mahajan'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email is : "+singleEmail);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void getGender()
    {
        List<String> getGender =response.extract().path("findAll{it.id==4136846}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is : "+getGender);
        System.out.println("------------------End of Test---------------------------");
    }
}
