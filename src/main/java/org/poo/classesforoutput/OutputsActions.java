package org.poo.classesforoutput;

import org.poo.classesforgame.Actions;
import org.poo.classesforgame.Coordinatess;
import org.poo.classesforgame.Game;
import org.poo.classesforgame.GameUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OutputsActions {
    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     */
    public void outputCardUsesAttackNotEnemyCard(final ObjectMapper objectMapper,
                                                 final ArrayNode output, final Actions action,
                                                 final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(action.getAttacker()));
        objectNode.set("cardAttacked", objectMapper.valueToTree(action.getDefender()));
        objectNode.put("error", "Attacked card does not belong to the enemy.");
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
    public void outputCardUsesAttackErrorAlreadyAttacked(final ObjectMapper objectMapper,
                                                         final ArrayNode output,
                                                         final Actions action,
                                                         final GameUpdate update,
                                                         final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(action.getAttacker()));
        objectNode.set("cardAttacked", objectMapper.valueToTree(action.getDefender()));
        objectNode.put("error", "Attacker card has already attacked this turn.");
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
    public void outputCardUsesAttackErrorFrozen(final ObjectMapper objectMapper,
                                                final ArrayNode output, final Actions action,
                                                final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(action.getAttacker()));
        objectNode.set("cardAttacked", objectMapper.valueToTree(action.getDefender()));
        objectNode.put("error", "Attacker card is frozen.");
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
    public void outputAttackedNotTank(final ObjectMapper objectMapper,
                                      final ArrayNode output, final Actions action,
                                      final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(action.getAttacker()));
        objectNode.set("cardAttacked", objectMapper.valueToTree(action.getDefender()));
        objectNode.put("error", "Attacked card is not of type 'Tank'.");
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
    public void outputAttackedNotCurrentPlayer(final ObjectMapper objectMapper,
                                               final ArrayNode output, final Actions action,
                                               final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(action.getAttacker()));
        objectNode.set("cardAttacked", objectMapper.valueToTree(action.getDefender()));
        objectNode.put("error", "Attacked card does not belong to the current player.");
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
    public void outputHeroNotEnoughMana(final ObjectMapper objectMapper,
                                        final ArrayNode output, final Actions action,
                                        final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("affectedRow", action.getAffRow());
        objectNode.put("error", "Not enough mana to use hero's ability.");
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
    public void outputHeroAlreadyUsed(final ObjectMapper objectMapper,
                                      final ArrayNode output, final Actions action,
                                      final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("affectedRow", action.getAffRow());
        objectNode.put("error", "Hero has already attacked this turn.");
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
    public void outputHeroInvalidRow(final ObjectMapper objectMapper,
                                     final ArrayNode output, final Actions action,
                                     final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("affectedRow", action.getAffRow());
        objectNode.put("error", "Selected row does not belong to the enemy.");
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
    public void outputHeroInvalidRow1(final ObjectMapper objectMapper,
                                      final ArrayNode output, final Actions action,
                                      final GameUpdate update, final Game game) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.put("affectedRow", action.getAffRow());
        objectNode.put("error", "Selected row does not belong to the current player.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     * @param att the attacker
     */
    public void outputAttackedNotTankForHero(final ObjectMapper objectMapper,
                                             final ArrayNode output, final Actions action,
                                             final GameUpdate update, final Game game,
                                             final Coordinatess att) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(att));
        objectNode.put("error", "Attacked card is not of type 'Tank'.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     * @param att the attacker
     */
    public void outputCardUsesAttackErrorFrozenForHero(final ObjectMapper objectMapper,
                                                       final ArrayNode output,
                                                       final Actions action,
                                                       final GameUpdate update, final Game game,
                                                       final Coordinatess att) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(att));
        objectNode.put("error", "Attacker card is frozen.");
        output.add(objectNode);
    }

    /**
     *
     * @param objectMapper the object mapper to create json nodes
     * @param output the array node where the output will be added
     * @param action the action being performed
     * @param update updates the game
     * @param game the current game
     * @param att the attacker
     */
    public void outputCardUsesAttackErrorAlreadyAttackedForHero(final ObjectMapper objectMapper,
                                                                final ArrayNode output,
                                                                final Actions action,
                                                                final GameUpdate update,
                                                                final Game game,
                                                                final Coordinatess att) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", action.getCommand());
        objectNode.set("cardAttacker", objectMapper.valueToTree(att));
        objectNode.put("error", "Attacker card has already attacked this turn.");
        output.add(objectNode);
    }


}
