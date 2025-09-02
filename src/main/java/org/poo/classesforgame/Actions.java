package org.poo.classesforgame;

import org.poo.classesforoutput.OutputsActions;
import org.poo.classesforoutput.OutputsCommands;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.fileio.ActionsInput;

public final class Actions {
    private String command;
    private int handIdx;
    private Coordinatess att;
    private Coordinatess def;
    private int affRow;
    private int playerIdx;
    private int x;
    private int y;

    public String getCommand() {
        return command;
    }

    public void setCommand(final String command) {
        this.command = command;
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }

    public Coordinatess getAttacker() {
        return att;
    }

    public void setAttacker(final Coordinatess attacker) {
        att = attacker;
    }

    public Coordinatess getDefender() {
        return def;
    }

    public void setDefender(final Coordinatess defender) {
        def = defender;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public int getAffRow() {
        return affRow;
    }

    public void setAffRow(final int affRow) {
        this.affRow = affRow;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public Actions(final ActionsInput input) {
        if (input.getCardAttacker() != null && input.getCardAttacked() != null) {
            this.command = input.getCommand();
            this.affRow = input.getAffectedRow();
            this.handIdx = input.getHandIdx();
            this.playerIdx = input.getPlayerIdx();
            this.att = new Coordinatess();
            this.att.setX(input.getCardAttacker().getX());
            this.att.setY(input.getCardAttacker().getY());
            this.def = new Coordinatess();
            this.def.setX(input.getCardAttacked().getX());
            this.def.setY(input.getCardAttacked().getY());
        } else if (input.getCardAttacker() != null && input.getCardAttacked() == null) {
            this.command = input.getCommand();
            this.playerIdx = input.getPlayerIdx();
            this.handIdx = input.getHandIdx();
            this.affRow = input.getAffectedRow();
            this.setX(input.getCardAttacker().getX());
            this.setY(input.getCardAttacker().getY());
        } else {
            this.command = input.getCommand();
            this.playerIdx = input.getPlayerIdx();
            this.handIdx = input.getHandIdx();
            this.affRow = input.getAffectedRow();
            this.setX(input.getX());
            this.setY(input.getY());
        }
    }

    /**
     *
     * @param output           the main output object to store results.
     * @param outputsActions   the list of actions to be performed in the game.
     * @param outputsCommands  the list of commands to execute during the game.
     * @param game             the current state of the game.
     * @param stats            the statistics related to the game or players.
     * @param update           a flag or object representing an update state.
     * @param objectMapper     an ObjectMapper instance for JSON processing.
     */
    public void doAction(final ArrayNode output, final OutputsActions outputsActions,
                         final OutputsCommands outputsCommands, final Game game,
                         final Stats stats, final GameUpdate update,
                         final ObjectMapper objectMapper) {
        if (this.getCommand().equals("getPlayerDeck")) {
            outputsCommands.outputGetPlayerDeck(objectMapper, output, this, update, game);
        } else if (this.getCommand().equals("getPlayerHero")) {
            outputsCommands.outputGetPlayerHero(objectMapper, output, this, update, game);
        } else if (this.getCommand().equals("getPlayerTurn")) {
            outputsCommands.outputGetPlayerTurn(objectMapper, output, this, update, game);
        } else if (this.getCommand().equals("placeCard")) {
            if (game.playerTurn == 1) {
                game.board.playerTurn = 1;
                int variable = game.board.placeCard(update.pl1CardsInHand.get(this.getHandIdx()),
                        game.pl1Mana);
                if (variable == 1) {
                    outputsCommands.outputPlaceCardNotEnoughMana(objectMapper, output,
                            this, update, game);
                } else if (variable == -1) {
                    outputsCommands.outputPlaceCardRowFull(objectMapper, output,
                            this, update, game);
                } else {
                    game.pl1Mana -= update.pl1CardsInHand.get(this.getHandIdx()).getMana();
                    update.removePl1CardFromHand(this.getHandIdx());
                }
            } else if (game.playerTurn == 2) {
                game.board.playerTurn = 2;
                int variable = game.board.placeCard(update.pl2CardsInHand.get(this.getHandIdx()),
                        game.pl2mana);
                if (variable == 1) {
                    outputsCommands.outputPlaceCardNotEnoughMana(objectMapper, output,
                            this, update, game);
                } else if (variable == -1) {
                    outputsCommands.outputPlaceCardRowFull(objectMapper, output,
                            this, update, game);
                } else {
                    game.pl2mana -= update.pl2CardsInHand.get(this.getHandIdx()).getMana();
                    update.removePl2CardFromHand(this.getHandIdx());
                }
            }
        } else if (this.getCommand().equals("endPlayerTurn")) {
            game.board.unfroze(game.playerTurn);
            game.changeTurn();
            if (game.totalTurns % 2 == 0) {
                update.getInHandPL1(update.deckPl1, update.pl1CardsInHand);
                update.getInHandPL2(update.deckPl2, update.pl2CardsInHand);
                game.board.afterTurnResetAttackIdx();
                game.start.getPlayerOneHero().setHasAttackedThisTurn(0);
                game.start.getPlayerTwoHero().setHasAttackedThisTurn(0);
            }
        } else if (this.getCommand().equals("getCardsInHand")) {
            outputsCommands.outputGetCardsInHand(objectMapper, output,
                    this, update, game);
        } else if (this.getCommand().equals("getPlayerMana")) {
            outputsCommands.outputGetPlayerMana(objectMapper, output,
                    this, update, game);
        } else if (this.getCommand().equals("getCardsOnTable")) {
            outputsCommands.outputGetCardsOnTable(objectMapper, output,
                    this, update, game);
        } else if (this.getCommand().equals("getCardAtPosition")) {
            if (game.board.getCard(this.getX(), this.getY()) == null) {
                outputsCommands.outputGetCardAtPositionError(objectMapper, output,
                        this, update, game);
            } else {
                outputsCommands.outputGetCardAtPosition(objectMapper, output,
                        this, update, game);
            }
        } else if (this.getCommand().equals("cardUsesAttack")) {
            int xAtt = this.getAttacker().getX();
            int yAtt = this.getAttacker().getY();
            int xDef = this.getDefender().getX();
            int yDef = this.getDefender().getY();
            if (game.playerTurn == 1 && (this.getDefender().getX() == 2
                    || this.getDefender().getX() == Constants.THREE)) {
                outputsActions.outputCardUsesAttackNotEnemyCard(objectMapper, output,
                        this, update, game);
            } else if (game.playerTurn == 2 && (this.getDefender().getX() == 0
                    || this.getDefender().getX() == 1)) {
                outputsActions.outputCardUsesAttackNotEnemyCard(objectMapper, output,
                        this, update, game);
            } else if (game.board.getCard(xAtt, yAtt).getHasAttackedThisTurn() == 1) {
                outputsActions.outputCardUsesAttackErrorAlreadyAttacked(objectMapper, output,
                        this, update, game);
            } else if (game.board.getCard(xAtt, yAtt).getIsFrozen() == 1) {
                outputsActions.outputCardUsesAttackErrorFrozen(objectMapper, output,
                        this, update, game);
            } else if (game.board.isTank(game.board.getCard(this.getDefender().getX(),
                    this.getDefender().getY())) == 1) {
                if (game.board.tankExistance(game.playerTurn) == 0) {
                    outputsActions.outputAttackedNotTank(objectMapper, output,
                            this, update, game);
                } else {
                    Card attacker = game.board.getCard(xAtt, yAtt);
                    Card defender = game.board.getCard(xDef, yDef);
                    attacker.attackAnotherCard(defender);
                    attacker.setHasAttackedThisTurn(1);
                    game.board.updateCardOnGameboard(attacker, xAtt, yAtt);
                    game.board.updateCardOnGameboard(defender, xDef, yDef);
                    if (game.board.getCard(xDef, yDef) == null) {
                        game.board.rearrange();
                    }
                }
                game.board.rearrange();
            } else {
                Card attacker = game.board.getCard(xAtt, yAtt);
                Card defender = game.board.getCard(xDef, yDef);
                attacker.attackAnotherCard(defender);
                attacker.setHasAttackedThisTurn(1);
                game.board.updateCardOnGameboard(attacker, xAtt, yAtt);
                game.board.updateCardOnGameboard(defender, xDef, yDef);
                if (game.board.getCard(xDef, yDef) == null) {
                    game.board.rearrange();
                }
            }
        } else if (this.getCommand().equals("cardUsesAbility")) {
            int xAtt = this.getAttacker().getX();
            int yAtt = this.getAttacker().getY();
            int xDef = this.getDefender().getX();
            int yDef = this.getDefender().getY();
            Card attt = game.board.getCard(xAtt, yAtt);
            Card deff = game.board.getCard(xDef, yDef);
            if (attt.getIsFrozen() == 1) {
                outputsActions.outputCardUsesAttackErrorFrozen(objectMapper, output,
                        this, update, game);
            } else if (attt.getHasAttackedThisTurn() == 1) {
                outputsActions.outputCardUsesAttackErrorAlreadyAttacked(objectMapper, output,
                        this, update, game);
            } else if (attt.getName().equals("Disciple")) {
                if (((game.playerTurn == 1 && (xDef == 0 || xDef == 1))
                        || (game.playerTurn == 2 && (xDef == 2 || xDef == Constants.THREE)))) {
                    outputsActions.outputAttackedNotCurrentPlayer(objectMapper, output,
                            this, update, game);
                } else {
                    attt.useAbilityDisciple(deff);
                    attt.setHasAttackedThisTurn(1);
                    game.board.updateCardOnGameboard(attt, xAtt, yAtt);
                    game.board.updateCardOnGameboard(deff, xDef, yDef);
                }
            } else if ((attt.getName().equals("The Ripper")
                    && ((game.playerTurn == 1 && xAtt == 2)
                    || (game.playerTurn == 2 && xAtt == 1)))
                    || (attt.getName().equals("Miraj") && ((game.playerTurn == 1 && xAtt == 2)
                    || (game.playerTurn == 2 && xAtt == 1)))
                    || attt.getName().equals("The Cursed One")
                    && ((game.playerTurn == 1 && xAtt == Constants.THREE)
                    || (game.playerTurn == 2 && xAtt == 0))) {
                if ((game.playerTurn == 1 && (xDef == 2 || xDef == Constants.THREE))
                        || (game.playerTurn == 2 && (xDef == 0 || xDef == 1))) {
                    outputsActions.outputCardUsesAttackNotEnemyCard(objectMapper, output,
                            this, update, game);
                } else if (game.board.tankExistance(game.playerTurn) == 0
                        && game.board.isTank(deff) == 1) {
                    outputsActions.outputAttackedNotTank(objectMapper, output,
                            this, update, game);
                } else {
                    if (attt.getName().equals("Miraj")) {
                        attt.useAbilityMiraj(deff);
                        attt.setHasAttackedThisTurn(1);
                        game.board.updateCardOnGameboard(attt, xAtt, yAtt);
                        game.board.updateCardOnGameboard(deff, xDef, yDef);
                        game.board.rearrange();
                    } else if (attt.getName().equals("The Cursed One")) {
                        attt.useAbilityTheCursedOne(deff);
                        attt.setHasAttackedThisTurn(1);
                        game.board.updateCardOnGameboard(attt, xAtt, yAtt);
                        game.board.updateCardOnGameboard(deff, xDef, yDef);
                        game.board.rearrange();
                    } else if (attt.getName().equals("The Ripper")) {
                        attt.useAbilityTheRipper(deff);
                        attt.setHasAttackedThisTurn(1);
                        game.board.updateCardOnGameboard(attt, xAtt, yAtt);
                        game.board.updateCardOnGameboard(deff, xDef, yDef);
                        game.board.rearrange();
                    }
                }
            }
        } else if (this.getCommand().equals("useAttackHero")) {
            Card attacker = game.board.getCard(this.getX(), this.getY());
            Coordinatess attt = new Coordinatess();
            attt.setX(this.getX());
            attt.setY(this.getY());
            if (attacker.getIsFrozen() == 1) {
                outputsActions.outputCardUsesAttackErrorFrozenForHero(objectMapper, output,
                        this, update, game, attt);
            } else if (attacker.getHasAttackedThisTurn() == 1) {
                outputsActions.outputCardUsesAttackErrorAlreadyAttackedForHero(objectMapper,
                        output, this, update, game, attt);
            } else if (game.board.tankExistance(game.playerTurn) == 0) {
                outputsActions.outputAttackedNotTankForHero(objectMapper, output,
                        this, update, game, attt);
            } else {
                if (game.playerTurn == 1) {
                    attacker.setHasAttackedThisTurn(1);
                    game.start.getPlayerTwoHero().isAttacked(attacker);
                    game.board.updateCardOnGameboard(attacker, this.getX(), this.getY());
                    if (game.start.getPlayerTwoHero().isDead() == 0) {
                        outputsCommands.outputPlayer1Won(objectMapper, output, this, update, game);
                        stats.setPlayer1Wins(stats.getPlayer1Wins() + 1);
                    }
                } else {
                    attacker.setHasAttackedThisTurn(1);
                    game.start.getPlayerOneHero().isAttacked(attacker);
                    game.board.updateCardOnGameboard(attacker, this.getX(), this.getY());
                    if (game.start.getPlayerOneHero().isDead() == 0) {
                        outputsCommands.outputPlayer2Won(objectMapper, output, this, update, game);
                        stats.setPlayer2Wins(stats.getPlayer2Wins() + 1);
                    }
                }
            }
        } else if (this.getCommand().equals("useHeroAbility")) {
            int error = 0;
            Hero hero = new Hero();
            if (game.playerTurn == 1) {
                hero = game.getStart().getPlayerOneHero();
            } else if (game.playerTurn == 2) {
                hero = game.start.getPlayerTwoHero();
            }
            if ((game.playerTurn == 1 && game.pl1Mana < hero.getMana())
                    || (game.playerTurn == 2 && game.pl2mana < hero.getMana())) {
                error = 1;
                outputsActions.outputHeroNotEnoughMana(objectMapper, output, this, update, game);
            } else if ((game.playerTurn == 1
                    && game.start.getPlayerOneHero().getHasAttackedThisTurn() == 1)
                    || (game.playerTurn == 2
                    && game.start.getPlayerTwoHero().getHasAttackedThisTurn() == 1)) {
                error = 1;
                outputsActions.outputHeroAlreadyUsed(objectMapper, output,
                        this, update, game);
            } else if (hero.getName().equals("Lord Royce")
                    || hero.getName().equals("Empress Thorina")) {
                if ((game.playerTurn == 1 && (this.getAffRow() == 2
                        || this.getAffRow() == Constants.THREE))
                        || (game.playerTurn == 2 && (this.getAffRow() == 0
                        || this.getAffRow() == 1))) {
                    error = 1;
                    outputsActions.outputHeroInvalidRow(objectMapper, output, this, update, game);
                }
            } else if (hero.getName().equals("General Kocioraw")
                    || hero.getName().equals("King Mudface")) {
                if ((game.playerTurn == 1 && (this.getAffRow() == 0
                        || this.getAffRow() == 1))
                        || (game.playerTurn == 2 && (this.getAffRow() == 2
                        || this.getAffRow() == Constants.THREE))) {
                    error = 1;
                    outputsActions.outputHeroInvalidRow1(objectMapper, output, this, update, game);
                }
            }
            if (error == 0) {
                if (game.playerTurn == 1) {
                    game.pl1Mana -= game.start.getPlayerOneHero().getMana();
                    game.start.getPlayerOneHero().setHasAttackedThisTurn(1);
                    game.start.getPlayerOneHero().useAbility(game.board, this);
                } else if (game.playerTurn == 2) {
                    game.pl2mana -= game.start.getPlayerTwoHero().getMana();
                    game.start.getPlayerTwoHero().setHasAttackedThisTurn(1);
                    game.start.getPlayerTwoHero().useAbility(game.board, this);
                }
            }
        } else if (this.getCommand().equals("getFrozenCardsOnTable")) {
            outputsCommands.outputGetFrozenCardsOnTable(objectMapper, output, this, update, game);
        } else if (this.getCommand().equals("getTotalGamesPlayed")) {
            outputsCommands.outputGetTotalGamesPlayed(objectMapper, output, this, update, stats);
        } else if (this.getCommand().equals("getPlayerOneWins")) {
            outputsCommands.outputGetPlayerOneWins(objectMapper, output, this, update, stats);
        } else if (this.getCommand().equals("getPlayerTwoWins")) {
            outputsCommands.outputGetPlayerTwoWins(objectMapper, output, this, update, stats);
        }
    }
}
