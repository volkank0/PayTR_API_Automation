package com.example.trello.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CardApi {
    private final String apiKey;
    private final String token;

    public CardApi(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
    }

    public Response createCard(String listId, String name) {
        return given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .body("{\"idList\": \"" + listId + "\", \"name\": \"" + name + "\"}")
                .post(RestAssured.baseURI + "/cards");
    }

    public Response updateCard(String cardId, String newName) {
        return given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .body("{\"name\": \"" + newName + "\"}")
                .put(RestAssured.baseURI + "/cards/" + cardId);
    }

    public Response deleteCard(String cardId) {
        return given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .delete(RestAssured.baseURI + "/cards/" + cardId);
    }
}
