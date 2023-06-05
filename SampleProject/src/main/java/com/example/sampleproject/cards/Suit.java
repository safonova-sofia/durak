package com.example.sampleproject.cards;

public enum Suit {
    CLUBS("Крести"), DIAMONDS("Буби"), HEARTS("Черви"), SPADES("Пики");
    private String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}