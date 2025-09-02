package org.poo.classesforgame;

import java.util.ArrayList;

public class GameUpdate {
    public ArrayList<Card> deckPl1;
    public ArrayList<Card> deckPl2;
    public ArrayList<Card> pl1CardsInHand = new ArrayList<>();
    public ArrayList<Card> pl2CardsInHand = new ArrayList<>();

    /**
     *
     * @param deckPL1 the deck from where we get the card
     * @param pl1CardsinHand the card in hand
     */
    public void getInHandPL1(final ArrayList<Card> deckPL1, final ArrayList<Card> pl1CardsinHand) {
        if (!deckPL1.isEmpty()) {
            Card card = deckPL1.remove(0);
            pl1CardsinHand.add(card);
        }
    }

    /**
     *
     * @param deckPL2 the deck from where we get the card
     * @param pl2CardsinHand the card in hand
     */
    public void getInHandPL2(final ArrayList<Card> deckPL2, final ArrayList<Card> pl2CardsinHand) {
        if (!deckPL2.isEmpty()) {
            Card card = deckPL2.remove(0);
            pl2CardsinHand.add(card);
        }
    }

    /**
     *
     * @param index removes a card from hand
     */
    public void removePl1CardFromHand(final int index) {
        if (!this.pl1CardsInHand.isEmpty() && index >= 0 && index < this.pl1CardsInHand.size()) {
            this.pl1CardsInHand.remove(index);
        }
    }

    /**
     *
     * @param index removes a card from hand
     */
    public void removePl2CardFromHand(final int index) {
        if (!this.pl2CardsInHand.isEmpty() && index >= 0 && index < this.pl2CardsInHand.size()) {
            this.pl2CardsInHand.remove(index);
        }
    }
}
