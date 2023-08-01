package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest
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


    @Test
    public void verifyTotal()
    {
        //1. Verify the if the total record is 20
        response.body("size",equalTo(25));
    }

    @Test
    public void verifyName()
    {
        //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
        response.body("findAll{it.id==4136850}", hasItem(hasEntry("name","Pres. Tanya Bharadwaj")));
    }

    @Test
    public void singleName()
    {
        //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
        response.body("name",hasItem("Balaaditya Reddy MD"));
    }

    @Test
    public void verifyMultipleuser()
    {
        //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
        response.body("name",hasItems("Sukanya Mahajan","Anunay Nambeesan","Kumar Mukhopadhyay"));
    }

    @Test
    public void verifyEmail()
    {
        //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
        response.body("findAll{it.id==4136848}", hasItem(hasEntry("email", "balaaditya_md_reddy@harvey.test")));
    }

    @Test
    public void verifyStatus()
    {
        //6. Verify the status is “Active” of user name is “Shanti Bhat V”
        response.body("findAll{it.status == 'active'}", hasItem(hasEntry("name", "Balaaditya Reddy MD")));
    }

    @Test
    public void verifyGender()
    {
        //7. Verify the Gender = male of user name is “Niro Prajapat”
        response.body("findAll{it.gender == 'male'}",hasItem(hasEntry("name","Balaaditya Reddy MD")));
    }
}
