package org.poo.classesforgame;

public class Player {
    private Decks decks;
    private int totalGamesPlayed;
    private int totalWins;

    /**
     *
     * @return the decks
     */
    public Decks getDecks() {
        return decks;
    }

    /**
     *
     * @param decks
     */
    public void setDecks(final Decks decks) {
        this.decks = decks;
    }

    /**
     *
     * @return total games played
     */
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    /**
     *
     * @param totGamesPlayed total
     */
    public void setTotalGamesPlayed(final int totGamesPlayed) {
        totalGamesPlayed = totGamesPlayed;
    }

    /**
     *
     * @return total games wom
     */
    public int getTotalWins() {
        return totalWins;
    }

    /**
     *
     * @param totWins
     */
    public void setTotalWins(final int totWins) {
        totalWins = totWins;
    }
}
