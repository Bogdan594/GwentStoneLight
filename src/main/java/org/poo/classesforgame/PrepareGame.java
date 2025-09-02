package org.poo.classesforgame;

import org.poo.fileio.Input;

public class PrepareGame {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private int player1NrDecks;
    private int player2NrDecks;
    private int player1NrCardsInDeck;
    private int player2NrCardsInDeck;

    /**
     *
     * @return the number of decks of player1
     */
    public int getPlayer1NrDecks() {
        return player1NrDecks;
    }

    /**
     *
     * @param player1NrDecks set the number of decks of player1
     */
    public void setPlayer1NrDecks(final int player1NrDecks) {
        this.player1NrDecks = player1NrDecks;
    }

    /**
     *
     * @return the number of decks of player1
     */
    public int getPlayer1NrCardsInDeck() {
        return player1NrCardsInDeck;
    }

    /**
     *
     * @param player1NrCardsInDeck set the number of decks of player1
     */
    public void setPlayer1NrCardsInDeck(final int player1NrCardsInDeck) {
        this.player1NrCardsInDeck = player1NrCardsInDeck;
    }

    /**
     *
     * @return number of cards in a deck of player 2
     */
    public int getPlayer2NrCardsInDeck() {
        return player2NrCardsInDeck;
    }

    /**
     *
     * @param player2NrCardsInDeck set the number of cards in a deck of player 2
     */
    public void setPlayer2NrCardsInDeck(final int player2NrCardsInDeck) {
        this.player2NrCardsInDeck = player2NrCardsInDeck;
    }

    /**
     *
     * @return number of cards in a deck of player 2
     */
    public int getPlayer2NrDecks() {
        return player2NrDecks;
    }

    /**
     *
     * @param player2NrDecks
     */
    public void setPlayer2NrDecks(final int player2NrDecks) {
        this.player2NrDecks = player2NrDecks;
    }

    /**
     *
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     *
     * @param player1 set player1
     */
    public void setPlayer1(final Player player1) {
        this.player1 = player1;
    }

    /**
     *
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     *
     * @param player2 set player2
     */
    public void setPlayer2(final Player player2) {
        this.player2 = player2;
    }

    /**
     *
     * @param inputData read from input
     */
    public PrepareGame(final Input inputData) {
        this.player1NrDecks = inputData.getPlayerOneDecks().getNrDecks();
        this.player2NrDecks = inputData.getPlayerTwoDecks().getNrDecks();
        this.player1NrCardsInDeck = inputData.getPlayerOneDecks().getNrCardsInDeck();
        this.player2NrCardsInDeck = inputData.getPlayerTwoDecks().getNrCardsInDeck();
        Decks decks1 = new Decks(inputData.getPlayerOneDecks());
        Decks decks2 = new Decks(inputData.getPlayerTwoDecks());
        this.player1.setDecks(decks1);
        this.player2.setDecks(decks2);
    }
}
