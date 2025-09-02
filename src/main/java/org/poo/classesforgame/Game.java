package org.poo.classesforgame;

import java.util.ArrayList;

public class Game {
    public Start start;
    public ArrayList<Actions> actions;
    public PrepareGame prepareGame;
    public int playerTurn;
    public Gameboard board = new Gameboard();
    public int pl1Mana = 1;
    public int pl2mana = 1;
    public int totalTurns;
    public int currentRound = 1;

    /**
     *
     * @return start
     */
    public Start getStart() {
        return start;
    }

    /**
     * @param start
     */

    public void setStart(final Start start) {
        this.start = start;
    }

    /**
     * @return actions of the game
     */
    public ArrayList<Actions> getActions() {
        return actions;
    }

    /**
     * @param actions of the game
     */
    public void setActions(final ArrayList<Actions> actions) {
        this.actions = actions;
    }

    /**
     *  chaanges the turn and if the round changes adds mana
     */
    public void changeTurn() {
        if (this.playerTurn == 1) {
            this.playerTurn = 2;
        } else if (this.playerTurn == 2) {
            this.playerTurn = 1;
        }
        this.totalTurns++;
        if (this.totalTurns % 2 == 0) {
            if (this.currentRound < Constants.TEN) {
                this.currentRound++;
                this.pl1Mana += this.currentRound;
                this.pl2mana += this.currentRound;
            } else {
                this.pl1Mana += Constants.TEN;
                this.pl2mana += Constants.TEN;
            }
        }
    }
}
