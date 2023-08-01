package com.gorest.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import java.util.HashMap;

public class TestBase
{

    @BeforeClass
    public static void inIt() {

        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page","1");
        qParam.put("per_page","25");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";

    }
}
