package com.pluralsight;

public class Main {

    public static void main(String[] args) {

        boolean playAgain = true;
        while (playAgain) {
            BlackjackGame newGame = new BlackjackGame();
            newGame.playBlackjack();
            playAgain = InputToolbox.promptYesNo("Play again?");
        }
        System.out.println("Thanks for playing!");
    }

}
