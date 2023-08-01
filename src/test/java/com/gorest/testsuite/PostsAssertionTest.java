package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest
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

    //1. Verify the if the total record is 25
    @Test
    public void verifyTotal()
    {
        response.body("size",equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    @Test
    public void verifyTitleId()
    {
        response.body("find{it.id == 57623}.title", equalTo("Substantia summopere desolo auris cimentarius recusandae deinde."));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void singleUserId()
    {
        response.body("user_id", hasItem(4136847));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void multipleUserId()
    {
        response.body("id", hasItems(57621,57620,57619));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void verifyDescription()
    {
        response.body("find{it.user_id == 4136840}.body", equalTo("Architecto tabesco arbustum. Tutamen teres videlicet. Molestiae utrimque decimus. Venia virgo denique. Verecundia demoror clibanus. Appello articulus cernuus. Celebrer consequatur cicuta. Dolores crebro ultio. Via solum conqueror. Sunt tam apud. Admoveo omnis tandem. Aequitas commemoro validus."));
    }
}
