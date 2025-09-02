package org.poo.classesforgame;

import org.poo.fileio.StartGameInput;

public class Start {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private int shuffleSeed;
    private Hero playerOneHero;
    private Hero playerTwoHero;
    private int startingPlayer;

    /**
     *
     * @return deck index for player1
     */
    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    /**
     *
     * @param playerOneDeckIdx to set the deck index for player1
     */
    public void setPlayerOneDeckIdx(final int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    /**
     *
     * @return deck index for player2
     */
    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    /**
     *
     * @param playerTwoDeckIdx to set the deck index for player2
     */
    public void setPlayerTwoDeckIdx(final int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }

    /**
     *
     * @return the shuffle seed
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     *
     * @param shuffleSeed to set the suffle seed
     */
    public void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    /**
     *
     * @return player1 hero
     */
    public Hero getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     *
     * @param hero to set player1 hero
     */
    public void setPlayerOneHero(final Hero hero) {
        playerOneHero = hero;
    }

    /**
     *
     * @return player2 hero
     */
    public Hero getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     *
     * @param hero to set player2 hero
     */
    public void setPlayerTwoHero(final Hero hero) {
        playerTwoHero = hero;
    }

    /**
     *
     * @return the starting player
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     *
     * @param startingPlayer to set the starting player
     */
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    /**
     *
     * @param input to read from input files
     */
    public Start(final StartGameInput input) {
        this.playerOneDeckIdx = input.getPlayerOneDeckIdx();
        this.playerTwoDeckIdx = input.getPlayerTwoDeckIdx();
        this.shuffleSeed = input.getShuffleSeed();
        this.playerOneHero = new Hero(input.getPlayerOneHero());
        this.playerTwoHero = new Hero(input.getPlayerTwoHero());
        this.startingPlayer = input.getStartingPlayer();
    }
}
