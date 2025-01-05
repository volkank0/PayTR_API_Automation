package com.example.trello.tests;

import com.example.trello.api.BoardApi;
import com.example.trello.api.CardApi;
import com.example.trello.config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class TrelloTest {
    private BoardApi boardApi;
    private CardApi cardApi;
    private String boardId;
    private String listId;
    private String[] cardIds = new String[2];

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Config.BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        boardApi = new BoardApi(Config.API_KEY, Config.TOKEN);
        cardApi = new CardApi(Config.API_KEY, Config.TOKEN);
        System.out.println("Setup completed. Base URL set to: " + Config.BASE_URL);
    }

    @Test(priority = 1)
    public void createBoardTest() {
        Response response = boardApi.createBoard("Otomasyon Board");
        response.then().statusCode(200);
        boardId = response.jsonPath().getString("id");
        System.out.println("Board created successfully. Board ID: " + boardId);
    }

    @Test(priority = 2, dependsOnMethods = "createBoardTest")
    public void getDefaultListTest() {
        Response response = boardApi.getLists(boardId);
        response.then().statusCode(200);
        listId = response.jsonPath().getString("[0].id");
        System.out.println("Default list retrieved successfully. List ID: " + listId);
    }

    @Test(priority = 3, dependsOnMethods = "getDefaultListTest")
    public void addCardsTest() {
        for (int i = 0; i < 2; i++) {
            Response response = cardApi.createCard(listId, "Kart " + (i + 1));
            response.then().statusCode(200);
            cardIds[i] = response.jsonPath().getString("id");
            System.out.println("Card " + (i + 1) + " created successfully. Card ID: " + cardIds[i]);
        }
    }

    @Test(priority = 4, dependsOnMethods = "addCardsTest")
    public void updateRandomCardTest() {
        int randomIndex = new Random().nextInt(2);
        Response response = cardApi.updateCard(cardIds[randomIndex], "Güncellenmiş Kart");
        response.then().statusCode(200);
        System.out.println("Card " + (randomIndex + 1) + " updated successfully. New name: Güncellenmiş Kart");
    }

    @Test(priority = 5, dependsOnMethods = "addCardsTest")
    public void deleteCardsTest() {
        for (String cardId : cardIds) {
            Response response = cardApi.deleteCard(cardId);
            response.then().statusCode(200);
            System.out.println("Card with ID " + cardId + " deleted successfully.");
        }
    }

    @Test(priority = 6, dependsOnMethods = "deleteCardsTest")
    public void deleteBoardTest() {
        Response response = boardApi.deleteBoard(boardId);
        response.then().statusCode(200);
        System.out.println("Board with ID " + boardId + " deleted successfully.");
    }
}
