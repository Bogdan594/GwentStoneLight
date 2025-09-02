package org.poo.classesforgame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CardInput;

public class Hero extends Card {
    @JsonIgnore
    private int attackDamage;
    // asta e pus pentru a fi ignorat de json la output, alta solutie nu am gasit


    /**
     *
     * @param input reads from input
     */
    public Hero(final CardInput input) {
        this.setName(input.getName());
        this.setDescription(input.getDescription());
        this.setColors(input.getColors());
        this.setMana(input.getMana());
        this.setIsFrozen(0);
        this.setHasAttackedThisTurn(0);
        this.setHealth(Constants.HEROHEALTH);
    }

    /**
     *
     */
    public Hero() { }

    /**
     *
     * @return attackDamage
     */
    @Override
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     *
     * @param attackDamage the attack damage of the card
     */
    @Override
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     *
     * @param card the card that attacks the hero
     */
    public void isAttacked(final Card card) {
        int healthBefore = this.getHealth();
        this.setHealth(healthBefore - card.getAttackDamage());
    }

    /**
     *
     * @return 0 if the hero is dead, 1 if it s not
     */
    public int isDead() {
        if (this.getHealth() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     *
     * @param gameboard
     * @param currentAction
     */
    public void useAbility(final Gameboard gameboard, final Actions currentAction) { }

    /**
     *
     * @return all the stats of the hero
     */
    @Override
    public String toString() {
        return "CardInput{"
                +  "mana="
                + this.getMana()
                + ", health="
                + this.getHealth()
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
