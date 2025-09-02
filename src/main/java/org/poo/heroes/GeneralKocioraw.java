package org.poo.heroes;

import org.poo.classesforgame.*;
import org.poo.fileio.CardInput;

public class GeneralKocioraw extends Hero {

    public GeneralKocioraw(final CardInput card) {
        this.setName(card.getName());
        this.setDescription(card.getDescription());
        this.setHealth(Constants.HEROHEALTH);
        this.setColors(card.getColors());
        this.setMana(card.getMana());
        this.setIsFrozen(0);
        this.setHasAttackedThisTurn(0);
    }

    public GeneralKocioraw() { }

    /**
     *
     * @param gameboard the gameboard affected by the card ability
     * @param currentAction the action
     */
    @Override
    public void useAbility(final Gameboard gameboard, final Actions currentAction) {
        for (int i = 0; i < Constants.FIVE; i++) {
            Card card = gameboard.getCard(currentAction.getAffRow(), i);
            if (card == null) {
                break;
            }
            card.setAttackDamage(card.getAttackDamage() + 1);
            gameboard.updateCardOnGameboard(card, currentAction.getAffRow(), i);
        }
    }
}
