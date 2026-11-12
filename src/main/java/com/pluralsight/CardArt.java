package com.pluralsight;

import java.util.ArrayList;

import static com.pluralsight.Symbols.*;

//! turns cards into ASCII pictures. It only uses Card but does not change it.
public class CardArt {

    //? Constant for how many lines tall my card will be.
    public static final int CARD_HEIGHT = 7;

    //? private constructor
    private CardArt() {
        //? This is just here so there's no accidental CardArt object creation.
    }

    public static String[] drawCard(Card card) {
        String[] cardLines = new String[CARD_HEIGHT];
        cardLines[0] = "┌───────────┐";
        cardLines[6] = "└───────────┘";
        if (card.isFaceUp()) {
            cardLines[1] = String.format("│" + "%-2s" + "         │", card.getValue());
            cardLines[2] = "│           │";
            cardLines[3] = "│     " + getSymbol(card.getSuit()) + "     │";
            cardLines[4] = "│           │";
            cardLines[5] = String.format("│       " + "  %2s" + "│", card.getValue());
            if (isRed(card.getSuit())) {
                for (int i = 0; i < CARD_HEIGHT; i++) {
                    cardLines[i] = ColorCodes.RED + cardLines[i] + ColorCodes.RESET;
                }
            }
        } else {
            for (int i = 1; i < CARD_HEIGHT - 1; i++) {
                cardLines[i] = "│░░░░░░░░░░░│";
            }
        }
        return cardLines;
    }

    public static String[] drawCards(ArrayList<Card> cards) {
        ArrayList<String[]> drawnCards = new ArrayList<>();
        String[] drawnCardLines = new String[CARD_HEIGHT];
        for (Card card : cards) {
            drawnCards.add(drawCard(card));
        }
        for (int i = 0; i < CARD_HEIGHT; i++) {
            drawnCardLines[i] = "";
            for (String[] drawnCard : drawnCards) {
                drawnCardLines[i] += drawnCard[i];
            }
        }
        return drawnCardLines;
    }

    private static String getSymbol(String suit) {
        switch (suit) {
            case "Hearts":
                return HEARTS;
            case "Spades":
                return SPADES;
            case "Diamonds":
                return DIAMONDS;
            case "Clubs":
                return CLUBS;
            default:
                return "?";
        }
    }

    private static boolean isRed(String suit) {
        switch (suit) {
            case "Hearts":
                return true;
            case "Diamonds":
                return true;
            default:
                return false;

        }
    }

}
