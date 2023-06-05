package com.example.sampleproject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import com.example.sampleproject.cards.Rank;
import com.example.sampleproject.cards.Suit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameWindowController {


    LinkedList<CardController> attackCards = new LinkedList<>();
    LinkedList<CardController> defendCards = new LinkedList<>();
    boolean isAttackMove = true;

    @FXML
    private Button FirstCardsButton;

    @FXML
    private GridPane deskAttackCardPane;

    @FXML
    private GridPane deskAnswerCardPane;

    @FXML
    private ScrollPane firstPlayerScroll;

    @FXML
    private ScrollPane secondPlayerScroll;

    @FXML
    private FlowPane firstPlayerPane;

    @FXML
    private FlowPane secondPlayerPane;


    CardController createCard(String rank, String suit) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Card.fxml"));
        Pane newPane = loader.load();
        CardController cardController = loader.getController();
        cardController.setCardParameters(rank, suit, this, newPane);
        return cardController;
    }

    public LinkedList<CardController> addCard() throws IOException, InterruptedException {
        LinkedList<CardController> deskCards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deskCards.add(createCard(rank.toString(), suit.toString()));
            }
        }
        return deskCards;
    }

    public void giveCards() throws IOException {
        LinkedList<CardController> deskCards;
        try {
            deskCards = addCard();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int cardsCount = 36;
        Random random = new Random();
        firstPlayerScroll = new ScrollPane();
        secondPlayerScroll = new ScrollPane();
        for (int i = 0; i < 6; i++) {
            CardController cardFirstPlayer = deskCards.get(random.nextInt(1, cardsCount));
            CardController cardSecondPlayer = deskCards.get(random.nextInt(1, cardsCount));
            deskCards.remove(cardFirstPlayer);
            deskCards.remove(cardSecondPlayer);
            cardsCount -= 2;
            firstPlayerPane.getChildren().add(cardFirstPlayer.cardPane);
            secondPlayerPane.getChildren().add(cardSecondPlayer.cardPane);
        }
        firstPlayerScroll.setContent(firstPlayerPane);
        secondPlayerScroll.setContent(secondPlayerPane);
        secondPlayerPane.setDisable(true);
        secondPlayerPane.setOpacity(1);
        FirstCardsButton.setVisible(false);
    }


    public void addCardOnTable(CardController card) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Card.fxml"));
        Pane newPane = loader.load();
        card.setActive(false);
        CardController cardController = loader.getController();
        cardController.setCardParameters(card.getRank(), card.getSuit(), this, newPane);

        if (isAttackMove) {
            attackCards.add(card);
            deskAttackCardPane.add(newPane, deskAttackCardPane.getChildren().size(), 0);
            firstPlayerPane.setDisable(true);
            firstPlayerPane.setOpacity(0);
            secondPlayerPane.setDisable(false);
            secondPlayerPane.setOpacity(1);
        } else {
            LinkedList<String> usedCards = new LinkedList<>();
            for (CardController usingCard : attackCards) {
                if (!usedCards.contains(usingCard.getRank())) {
                    usedCards.add(usingCard.getRank());
                }
            }

            defendCards.add(card);
            deskAnswerCardPane.add(newPane, deskAnswerCardPane.getChildren().size(), 0);
            secondPlayerPane.setDisable(true);
            secondPlayerPane.setOpacity(0);
            firstPlayerPane.setDisable(false);
            firstPlayerPane.setOpacity(1);
        }
        isAttackMove = !isAttackMove;
    }


}
