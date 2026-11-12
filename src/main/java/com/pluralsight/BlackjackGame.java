package com.pluralsight;

import java.util.ArrayList;

public class BlackjackGame {

    private Deck deck;

    private ArrayList<Player> players;

    public BlackjackGame() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
    }

    private void choosePlayers() {
        int numberOfPlayers =
                InputToolbox.promptInt("How many players for Blackjack today? (1-5): ", 1, 5);
        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = InputToolbox.promptString("Please enter player " + (i + 1) + "'s name: ");
            players.add(new Player(playerName));
        }
    }

    private void dealStartingHand() {

    }

}
