package com.example.sampleproject.cards;

public enum Rank {
    ACE("Туз"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("Валет"), QUEEN("Дама"), KING("Король");
    private String rank;

    Rank(String name) {
        this.rank = name;
    }

    @Override
    public String toString() {
        return rank;
    }
}
