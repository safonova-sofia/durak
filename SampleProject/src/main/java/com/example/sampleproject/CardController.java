package com.example.sampleproject;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.*;

public class CardController {
    Pane cardPane;

    private boolean active = true;

    GameWindowController gameWindow;

    @FXML
    private Text rank;

    @FXML
    private Text suit;

    @FXML
    private ImageView imgBuffer;

    @FXML
    private Pane imgPane;

    public void setCardParameters(String rank, String suit, GameWindowController gameWindow, Pane cardPane) throws FileNotFoundException {
        this.rank.setText(rank);
        this.suit.setText(suit);
        this.gameWindow = gameWindow;
        this.cardPane = cardPane;

        File img = new File("src\\main\\resources\\com\\example\\sampleproject\\EntrancePicture.jpg");
        InputStream isImage = new FileInputStream(img);
        imgBuffer = new ImageView(new Image(isImage));
        imgBuffer.setFitWidth(100);
        imgBuffer.setPreserveRatio(true);
        //imgBuffer.setSmooth(true);

        cardPane.getChildren().add(imgBuffer);

        //imgPane = new Pane();
        imgPane.getChildren().setAll(imgBuffer);

    }

    public String getRank() {
        return this.rank.getText();
    }

    public String getSuit() {
        return this.suit.getText();
    }


    @FXML
    void replaceCardToTable() throws IOException {
        if (this.isActive()) {
            gameWindow.addCardOnTable(this);
            cardPane.setVisible(false);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
