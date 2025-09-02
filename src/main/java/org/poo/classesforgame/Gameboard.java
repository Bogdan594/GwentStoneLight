package org.poo.classesforgame;

import java.util.ArrayList;

public class Gameboard {
    public Card[][] board = new Card[Constants.FOUR][Constants.FIVE];
    public int playerTurn;

    /**
     *
     * @param card the card to be added to the table
     * @param playerMana the mana of the player that wants to place the card
     * @return 1 if not enough mana, 0 if succesfull, -1 if there is no place
     */
    public int placeCard(final Card card, final int playerMana) {
        int rowToPlace = -1;
        if (card.getMana() > playerMana) {
            return 1;
        }
        if (card.getName().equals("Sentinel") || card.getName().equals("Berserker")
                || card.getName().equals("The Cursed One") || card.getName().equals("Disciple")) {
            rowToPlace = (playerTurn == 1) ? Constants.THREE : 0;
        } else if (card.getName().equals("Goliath") || card.getName().equals("Warden")
                || card.getName().equals("Miraj") || card.getName().equals("The Ripper")) {
            rowToPlace = (playerTurn == 1) ? 2 : 1;
        }
        if (rowToPlace == -1) {
            return -1;
        }
        for (int i = 0; i < Constants.FIVE; i++) {
            if (board[rowToPlace][i] == null) {
                board[rowToPlace][i] = card;
                return 0;
            }
        }
        return -1;
    }

    /**
     *
     * @return all the cards placed on table
     */
    public ArrayList<ArrayList<Card>> getCardsOnTable() {
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        for (int i = 0; i < Constants.FOUR; i++) {
            ArrayList<Card> row = new ArrayList<>();
            for (int j = 0; j < Constants.FIVE; j++) {
                if (board[i][j] != null) {
                    row.add(board[i][j]);
                }
            }
            table.add(row);
        }
        return table;
    }

    /**
     *
     * @param x
     * @param y
     * @return card on table at x,y
     */
    public Card getCard(final int x, final int y) {
        return this.board[x][y];
    }

    /**
     *
     * @param card the new card in final form placed on board x,y
     * @param x
     * @param y
     */
    public void updateCardOnGameboard(final Card card, final int x, final int y) {
        if (card.getHealth() <= 0) {
            board[x][y] = null;
        } else {
            board[x][y].copyStats(card);
        }
    }

    /**
     * rearranges the table after all the changes
     */
    public void rearrange() {
        for (int row = 0; row < Constants.FOUR; row++) {
            int index = 0;
            for (int col = 0; col < Constants.FIVE; col++) {
                if (board[row][col] != null) {
                    if (index != col) {
                        board[row][index] = board[row][col];
                        board[row][col] = null;
                    }
                    index++;
                }
            }
        }
    }


    /**
     * resets the attack index after attacking
     */
    public void afterTurnResetAttackIdx() {
        for (int i = 0; i < Constants.FOUR; i++) {
            for (int j = 0; j < Constants.FIVE; j++) {
                if (board[i][j] != null) {
                    Card card = this.getCard(i, j);
                    card.setHasAttackedThisTurn(0);
                    board[i][j] = card;
                }
            }
        }
    }

    /**
     *
     * @param card the card we want to verify if it s a tank
     * @return 0 if yes, 1 if no
     */
    public int isTank(final Card card) {
        if (card.getName().equals("Warden") || card.getName().equals("Goliath")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     *
     * @param playerturn
     * @return whether there is a tank that can be attacked first
     */
    public int tankExistance(final int playerturn) {
        int exists = 1;
        int row = -1;
        if (playerturn == 1) {
            row = 1;
        } else if (playerturn == 2) {
            row = 2;
        }
        for (int i = 0; i < Constants.FIVE; i++) {
            Card card = this.getCard(row, i);
            if (card != null && (card.getName().equals("Warden")
                    || card.getName().equals("Goliath"))) {
                exists = 0;
                break;
            }
        }
        return exists;
    }

    /**
     *
     * @param playerturn unfrozez all the cards after a turn
     */
    public void unfroze(final int playerturn) {
        int start, end;
        if (playerturn == 1) {
            start = 2;
            end = Constants.THREE;
            for (int i = start; i <= end; i++) {
                for (int j = 0; j < Constants.FIVE; j++) {
                    if (board[i][j] != null && board[i][j].getIsFrozen() == 1) {
                        board[i][j].setIsFrozen(0);
                    }
                }
            }
        } else if (playerTurn == 2) {
            start = 0;
            end = 1;
            for (int i = start; i <= end; i++) {
                for (int j = 0; j < Constants.FIVE; j++) {
                    if (board[i][j] != null && board[i][j].getIsFrozen() == 1) {
                        board[i][j].setIsFrozen(0);
                    }
                }
            }
        }
    }

    /**
     *
     * @return all the frozen cards on table
     */
    public ArrayList<Card> getFrozenCardsOnTable() {
        ArrayList<Card> arr = new ArrayList<>();
        for (int i = 0; i < Constants.FOUR; i++) {
            for (int j = 0; j < Constants.FIVE; j++) {
                if (board[i][j] != null && board[i][j].getIsFrozen() == 1) {
                    arr.add(board[i][j]);
                }
            }
        }
        return arr;
    }

}
