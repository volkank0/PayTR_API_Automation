package com.example.trello.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardApi {
    private final String apiKey;
    private final String token;

    public BoardApi(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
    }

    public Response createBoard(String name) {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .queryParam("name", name)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .post(RestAssured.baseURI + "/boards");
    }

    public Response deleteBoard(String boardId) {
        return given()
                .log().all()
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .delete(RestAssured.baseURI + "/boards/" + boardId);
    }

    public Response getLists(String boardId) {
        return given()
                .log().all()
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .get(RestAssured.baseURI + "/boards/" + boardId + "/lists");
    }
}
