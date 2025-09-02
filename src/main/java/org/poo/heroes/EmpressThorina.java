package org.poo.heroes;

import org.poo.classesforgame.*;
import org.poo.fileio.CardInput;

public class EmpressThorina extends Hero {
    public EmpressThorina(final CardInput card) {
        this.setName(card.getName());
        this.setDescription(card.getDescription());
        this.setHealth(Constants.HEROHEALTH);
        this.setColors(card.getColors());
        this.setMana(card.getMana());
        this.setIsFrozen(0);
        this.setHasAttackedThisTurn(0);
    }

    public EmpressThorina() { }

    /**
     *
     * @param gameboard the gameboard affected by the card ability
     * @param currentAction the action
     */
    @Override
    public void useAbility(final Gameboard gameboard, final Actions currentAction) {
        Card highestHealthCard = null;
        int highestHealth = -1;
        int col = -1;
        for (int i = 0; i < Constants.FIVE; i++) {
            Card current = gameboard.getCard(currentAction.getAffRow(), i);
            if (current != null && current.getHealth() > highestHealth) {
                highestHealth = current.getHealth();
                highestHealthCard = current;
                col = i;
            }
        }
        if (highestHealthCard != null) {
            highestHealthCard.setHealth(0);
            gameboard.updateCardOnGameboard(highestHealthCard, currentAction.getAffRow(), col);
            gameboard.rearrange();
        }
    }
}
