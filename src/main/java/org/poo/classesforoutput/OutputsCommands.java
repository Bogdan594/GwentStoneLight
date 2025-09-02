package org.poo.classesforoutput;

import org.poo.classesforgame.Actions;
import org.poo.classesforgame.Game;
import org.poo.classesforgame.GameUpdate;
import org.poo.classesforgame.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OutputsCommands {
    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetPlayerDeck(final ObjectMapper objectMapper, final ArrayNode output,
                                    final Actions action, final GameUpdate update,
                                    final Game game) {
        if (action.getPlayerIdx() == 1) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", action.getCommand());
            objectNode.put("playerIdx", action.getPlayerIdx());
            objectNode.set("output", objectMapper.valueToTree(update.deckPl1));
            output.add(objectNode);
        } else if (action.getPlayerIdx() == 2) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", action.getCommand());
            objectNode.put("playerIdx", action.getPlayerIdx());
            objectNode.set("output", objectMapper.valueToTree(update.deckPl2));
            output.add(objectNode);
        }
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetPlayerHero(final ObjectMapper objectMapper, final ArrayNode output,
                                    final Actions action, final GameUpdate update,
                                    final Game game) {
        if (action.getPlayerIdx() == 1) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", action.getCommand());
            objectNode.put("playerIdx", action.getPlayerIdx());
            objectNode.set("output", objectMapper.valueToTree(game.start.getPlayerOneHero()));
            output.add(objectNode);
        } else if (action.getPlayerIdx() == 2) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", action.getCommand());
            objectNode.put("playerIdx", action.getPlayerIdx());
            objectNode.set("output", objectMapper.valueToTree(game.start.getPlayerTwoHero()));
            output.add(objectNode);
        }
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetPlayerTurn(final ObjectMapper objectMapper,
                                    final ArrayNode output, final Actions action,
                                    final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(game.playerTurn));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetCardsInHand(final ObjectMapper objectMapper,
                                     final ArrayNode output, final Actions action,
                                     final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("playerIdx", action.getPlayerIdx());
        if (action.getPlayerIdx() == 1) {
            objectNode.set("output", objectMapper.valueToTree(update.pl1CardsInHand));
        } else if (action.getPlayerIdx() == 2) {
            objectNode.set("output", objectMapper.valueToTree(update.pl2CardsInHand));
        }
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetPlayerMana(final ObjectMapper objectMapper,
                                    final ArrayNode output, final Actions action,
                                    final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("playerIdx", action.getPlayerIdx());
        if (action.getPlayerIdx() == 1) {
            objectNode.set("output", objectMapper.valueToTree(game.pl1Mana));
        } else if (action.getPlayerIdx() == 2) {
            objectNode.set("output", objectMapper.valueToTree(game.pl2mana));
        }
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetCardsOnTable(final ObjectMapper objectMapper,
                                      final ArrayNode output, final Actions action,
                                      final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(game.board.getCardsOnTable()));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetFrozenCardsOnTable(final ObjectMapper objectMapper,
                                            final ArrayNode output, final Actions action,
                                            final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(game.board.getFrozenCardsOnTable()));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetCardAtPosition(final ObjectMapper objectMapper,
                                        final ArrayNode output, final Actions action,
                                        final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", "getCardAtPosition");
        objectNode.put("x", action.getX());
        objectNode.put("y", action.getY());
        objectNode.set("output", objectMapper.valueToTree(game.board.getCard(action.getX(),
                action.getY())));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputGetCardAtPositionError(final ObjectMapper objectMapper,
                                             final ArrayNode output, final Actions action,
                                             final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", "getCardAtPosition");
        objectNode.put("x", action.getX());
        objectNode.put("y", action.getY());
        objectNode.put("output", "No card available at that position.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputPlaceCardNotEnoughMana(final ObjectMapper objectMapper,
                                             final ArrayNode output, final Actions action,
                                             final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("handIdx", action.getHandIdx());
        objectNode.put("error", "Not enough mana to place card on table.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputPlaceCardRowFull(final ObjectMapper objectMapper,
                                       final ArrayNode output, final Actions action,
                                       final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("handIdx", action.getHandIdx());
        objectNode.put("error", "Cannot place card on table since row is full.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param stats to get the needed stat
     */
    public void outputGetPlayerOneWins(final ObjectMapper objectMapper,
                                       final ArrayNode output, final Actions action,
                                       final GameUpdate update, final Stats stats) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(stats.getPlayer1Wins()));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param stats to get the needed stat
     */
    public void outputGetPlayerTwoWins(final ObjectMapper objectMapper,
                                       final ArrayNode output, final Actions action,
                                       final GameUpdate update, final Stats stats) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(stats.getPlayer2Wins()));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param stats to get the needed stat
     */
    public void outputGetTotalGamesPlayed(final ObjectMapper objectMapper,
                                          final ArrayNode output, final Actions action,
                                          final GameUpdate update, final Stats stats) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("output", objectMapper.valueToTree(stats.getTotalGamesPlayed()));
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputPlayer1Won(final ObjectMapper objectMapper,
                                 final ArrayNode output, final Actions action,
                                 final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("gameEnded", "Player one killed the enemy hero.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputPlayer2Won(final ObjectMapper objectMapper,
                                 final ArrayNode output, final Actions action,
                                 final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("gameEnded", "Player two killed the enemy hero.");
        output.add(objectNode);
    }
}
