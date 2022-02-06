package com.spiashko.restpersistence.demo.rest;

import com.spiashko.restpersistence.demo.BaseApplicationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.*;


class GetPersonEndpointTest extends BaseApplicationTest {

    @Test
    @DisplayName("when person GET endpoint invoked without params then all records received")
    void whenGetWithoutParams_thenAllRecordsReceived() {
        // @formatter:off
        RestAssured
                .given()
                .when()
                    .get("/persons")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", is(4))
                    .body("[0].father.name", is(nullValue()));
        // @formatter:on
    }

    @Test
    @DisplayName("when person GET endpoint invoked with filter param then only relevant records are received")
    void whenGetWithFilterParam_thenOnlyRelevantRecordsAreReceived() {
        // @formatter:off
        RestAssured
                .given()
                    .queryParam("filter", "father.name==vasily")
                .when()
                    .get("/persons")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", is(2));
        // @formatter:on
    }

    @Test
    @DisplayName("when person GET endpoint invoked with include param then records with requested scope are received")
    void whenGetWithIncludeParam_thenRecordsWithRequestedScopeAreReceived() {
        // @formatter:off
        RestAssured
                .given()
                    .queryParam("include", "father")
                .when()
                    .get("/persons")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", is(4))
                    .body("[0].father.name", is(not(emptyString())));
        // @formatter:on
    }

    @Test
    @DisplayName("when person GET endpoint invoked with both params then only relevant records with requested scope are received")
    void whenGetWithBothParams_thenOnlyRelevantRecordsWithRequestedScopeAreReceived() {
        // @formatter:off
        RestAssured
                .given()
                    .queryParam("include", "father")
                    .queryParam("filter", "father.name==vasily")
                .when()
                    .get("/persons")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", is(2))
                    .body("[0].father.name", is(not(emptyString())));
        // @formatter:on
    }

}
