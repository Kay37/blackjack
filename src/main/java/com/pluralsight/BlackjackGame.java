package com.pluralsight;

import java.util.ArrayList;

public class BlackjackGame {

    private static final int STARTING_CARDS = 2;

    public static final int BLACKJACK = 21;

    private Deck deck;

    private ArrayList<Player> players;

    //? Every BlackjackGame gets a new deck and new list of players
    public BlackjackGame() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
    }

    private void choosePlayers() {
        //? Using my input toolbox method to ask the user how many players, with a minimum amount of 1 and a max of 5
        //? storing that answer within variable numberOfPlayers
        int numberOfPlayers =
                InputToolbox.promptInt("How many players for Blackjack today? (1-5): ", 1, 5);
        //? a for loop that loops as many times as the # of players
        for (int i = 0; i < numberOfPlayers; i++) {
            //? Asking the user for each player's name, and storing it within a playerName variable that gets reused
            //? on the next passover of the loop.
            String playerName = InputToolbox.promptString("Please enter player " + (i + 1) + "'s name: ");
            //? adding that player to the ArrayList for the players.
            players.add(new Player(playerName));
        }
    }

    private void dealStartingHand() {
        //? Shuffle the deck
        deck.shuffle();
        //? Every player in blackjack gets 2 cards at the start of the game. So we loop 2 times
        for (int i = 0; i < STARTING_CARDS; i++) {
            //? for every player, get handed / dealt a card (2 b/c loop runs 2x)
            for (Player player : players) {
                player.takeCard(deck.deal());
            }

        }
    }

    private void takeTurn(Player player) {
        showHand(player);
        boolean keepGoing = true;
        while (keepGoing && player.getScore() < 21) {
            String hitOrStand = InputToolbox.promptChoice(player.getName() + ", Hit or Stand?", "Hit", "Stand");
            switch (hitOrStand) {
                case "hit":
                    System.out.println("Hit!");
                    player.takeCard(deck.deal());
                    showHand(player);
                    break;
                case "stand":
                    System.out.println("Stand.");
                    keepGoing = false;
                    break;
            }
        }
    }

    private void playTurns() {
        for (Player player : players) {
            takeTurn(player);
            System.out.println("─────────────── ♠ ♥ ♦ ♣ ───────────────");
        }
    }

    private void showHand(Player player) {
        //? Grab's player's name & current score given their hand
        System.out.println(player.getName() + ": " + player.getScore());
        //? If their score is a bust, print out that it is
        if (player.isBust()) {
            System.out.println("Bust!");
        }
        //? flips all cards so we can see the hand
        player.getHand().flipAll();

        //? Creating a String array called rows that consists of drawn ASCII cards created from the player's current
        //? hand / cards
        String[] rows = CardArt.drawCards(player.getHand().getCards());
        //? For every line of the rows String array
        for (String line : rows) {
            //? print that line
            System.out.println(line);
        }
        //? flip cards back over
        player.getHand().flipAll();
    }

    private void showAllHands() {
        for (Player player : players) {
            showHand(player);
            System.out.println();
        }
    }

    public static ArrayList<Player> findWinners(ArrayList<Player> players) {
        ArrayList<Player> winners = new ArrayList<>();
        int bestScore = 0;
        for (Player player : players) {
            if (player.getScore() > bestScore && !player.isBust()) {
                bestScore = player.getScore();
            }
        }
        for (Player player : players) {
            if (!player.isBust() && player.getScore() == bestScore) {
                winners.add(player);
            }
        }
        return winners;
    }

    private void announceWinners() {
        ArrayList<Player> winners = findWinners(players);
        switch (winners.size()) {
            case 0:
                System.out.println("No winner :( Everyone busted.");
                break;
            case 1:
                System.out.println(winners.get(0).getName() + " wins!");
                break;
            default:
                System.out.println("There was a tie!");
                for (Player player : winners) {
                    System.out.println(player.getName() + ": " + player.getScore());
                }
                break;
        }
    }

    public void playBlackjack() {
        choosePlayers();
        dealStartingHand();
        playTurns();
        showAllHands();
        announceWinners();
    }

}
