package com.pluralsight;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // A Card is dealt to the Hand and the Hand is responsible // to store the card
    public void deal(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    // The Hand uses the methods of each card to determine // the value of each card - and adds up all values
    public int getValue() {
        int value = 0;
        for (Card card : cards) {
            //? flip card to see value
            card.flip();
            //? value is equal to value + whatever that card's value was
            value += card.getPointValue();
            //? flip card back over to hide it.
            card.flip();
        }
        for (Card card : cards) {
            if (value > 21 && card.isAce()) {
                value -= 10;
            }
        }
        return value;
    }

    public boolean flipCard(int index) {
        //? If the card's position (provided index)
        //? is at least 0, AND the card's position is less than the size of the hand
        if (index >= 0 && index < getSize()) {
            //? Then flip the card matching that position / index.
            cards.get(index).flip();
            //? return true to wherever this method was called
            return true;
        } //? We only get to this return false if index was not at least 0 & less than the size of the hand
        return false;
    }

    //? for loop - for every card in our cards ArrayList, flip it.
    //? runs until no more cards.
    public void flipAll() {
        for (Card card : cards) {
            card.flip();
        }
    }

    //? Hands the list of cards to wherever called.
    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isBust() {
        if (getValue() > 21) {
            return true;
        } else {
            return false;
        }
    }

}
