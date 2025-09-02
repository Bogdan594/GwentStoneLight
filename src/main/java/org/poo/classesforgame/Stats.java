package org.poo.classesforgame;

public class Stats {
    private int totalGamesPlayed;
    private int player1Wins;
    private int player2Wins;

    /**
     *
     * @return the number of game played
     */
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    /**
     *
     * @param totalGamesPlayed to set the number of total games played
     */
    public void setTotalGamesPlayed(final int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    /**
     *
     * @return the number of wins for player1
     */
    public int getPlayer1Wins() {
        return player1Wins;
    }

    /**
     *
     * @param player1Wins to set the number of wins for player1
     */
    public void setPlayer1Wins(final int player1Wins) {
        this.player1Wins = player1Wins;
    }

    /**
     *
     * @return the number of wins for player2
     */
    public int getPlayer2Wins() {
        return player2Wins;
    }

    /**
     *
     * @param player2Wins to set the number of wins for player2
     */
    public void setPlayer2Wins(final int player2Wins) {
        this.player2Wins = player2Wins;
    }

    /**
     * initializes the stats with 0
     */
    public Stats() {
        this.setPlayer1Wins(0);
        this.setPlayer2Wins(0);
        this.setTotalGamesPlayed(0);
    }
}
