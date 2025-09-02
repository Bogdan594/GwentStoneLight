package org.poo.classesforgame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    @JsonIgnore
    private int isFrozen = 0;
    @JsonIgnore
    private int hasAttackedThisTurn = 0;

    /**
     *
     * @return whether the card has attacked or not
     */
    public int getHasAttackedThisTurn() {
        return hasAttackedThisTurn;
    }

    /**
     *
     * @param hasAttackedThisTurn whether the card has attacked or not
     */
    public void setHasAttackedThisTurn(final int hasAttackedThisTurn) {
        this.hasAttackedThisTurn = hasAttackedThisTurn;
    }

    /**
     *
     * @return whether the card is frozen or not
     */
    public int getIsFrozen() {
        return isFrozen;
    }

    /**
     *
     * @param isFrozen whether the card is frozen or not
     */
    public void setIsFrozen(final int isFrozen) {
        this.isFrozen = isFrozen;
    }

    /**
     *
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name of the card
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return the colors of the cards
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     *
     * @param colors the colors of the cards
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     *
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description of the card
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @return the health of the card
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health the health of the card
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     *
     * @return the attack damage of the card
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     *
     * @param attackDamage the attack damage of the card
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     *
     * @return the mana cost of the card
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     * @param mana the mana cost of the card
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     *
     * @param otherCard the card that is attacked
     */
    public void attackAnotherCard(final Card otherCard) {
        this.setHasAttackedThisTurn(1);
        int newHealth = otherCard.getHealth() - this.getAttackDamage();
        otherCard.setHealth(newHealth);
    }

    /**
     *
     * @param otherCard the card that is affected by the ability
     */
    public void useAbilityDisciple(final Card otherCard) {
        otherCard.setHealth(otherCard.getHealth() + 2);
    }

    /**
     *
     * @param otherCard the card that is affected by the ability
     */
    public void useAbilityMiraj(final Card otherCard) {
        int attHealth = this.getHealth();
        int defHealth = otherCard.getHealth();
        this.setHealth(defHealth);
        otherCard.setHealth(attHealth);
    }

    /**
     *
     * @param otherCard the card that is affected by the ability
     */
    public void useAbilityTheCursedOne(final Card otherCard) {
        int hlth = otherCard.getHealth();
        int attDmg = otherCard.getAttackDamage();
        otherCard.setHealth(attDmg);
        otherCard.setAttackDamage(hlth);
    }

    /**
     *
     * @param otherCard the card that is affected by the ability
     */
    public void useAbilityTheRipper(final Card otherCard) {
        int attDmg = otherCard.getAttackDamage();
        otherCard.setAttackDamage(attDmg - 2);
        if (otherCard.getAttackDamage() < 0) {
            otherCard.setAttackDamage(0);
        }
    }

    /**
     *
     * @param input from input to copy
     */
    public Card(final CardInput input) {
        this.mana = input.getMana();
        this.attackDamage = input.getAttackDamage();
        this.health = input.getHealth();
        this.description = input.getDescription();
        this.colors = input.getColors();
        this.name = input.getName();
    }

    public Card() {
    }

    /**
     *
     * @param card to copy the stats to the current one
     */
    public void copyStats(final Card card) {
        this.setName(card.getName());
        this.setColors(card.getColors());
        this.setHealth(card.getHealth());
        this.setMana(card.getMana());
        this.setDescription(getDescription());
        this.setAttackDamage(card.getAttackDamage());
        this.setIsFrozen(card.getIsFrozen());
        this.setHasAttackedThisTurn(card.getHasAttackedThisTurn());
    }

    /**
     *
     * @param hero that the current card attacks
     */
    public void attacksHero(final Hero hero) {
        this.setHasAttackedThisTurn(1);
        int attDmg = this.getAttackDamage();
        hero.setHealth(hero.getHealth() - attDmg);
    }

    /**
     *
     * @return all the stats of the card
     */
    public String toString() {
        return "CardInput{"
                +  "mana="
                + this.getMana()
                + ", health="
                + this.getHealth()
                + ", attackDamage="
                + this.getAttackDamage()
                +  ", description='"
                + this.getDescription()
                + '\''
                + ", colors="
                + this.getColors()
                + ", name='"
                +  ""
                + this.getName()
                + '\''
                + '}';
    }

}
