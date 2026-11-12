package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();

        Card heart = new Card("Hearts", "A");
        heart.flip();
        cards.add(heart);

        Card spade = new Card("Spades", "10");
        spade.flip();
        cards.add(spade);

        cards.add(new Card("Clubs", "7"));   // left face down

        for (String line : CardArt.drawCards(cards)) {
            System.out.println(line);
        }

    }

}
