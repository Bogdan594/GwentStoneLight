package org.poo.classesforgame;

import org.poo.fileio.CardInput;
import org.poo.fileio.DecksInput;

import java.util.ArrayList;

public class Decks {
    private int nrCardsInDeck;
    private int nrDecks;
    private ArrayList<ArrayList<Card>> decks;

    /**
     *
     * @return the decks
     */
    public ArrayList<ArrayList<Card>> getDecks() {
        return decks;
    }

    /**
     *
     * @param decks the decks
     */
    public void setDecks(final ArrayList<ArrayList<Card>> decks) {
        this.decks = decks;
    }

    /**
     *
     * @return number of decks
     */
    public int getNrDecks() {
        return nrDecks;
    }

    /**
     *
     * @param nrDecks number of decks
     */
    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    /**
     *
     * @return the number of cards in deck
     */
    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    /**
     *
     * @param nrCardsInDeck the number of cards in deck
     */
    public void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    /**
     *
     * @param input from input to copy
     */
    public Decks(final DecksInput input) {
        this.nrCardsInDeck = input.getNrCardsInDeck();
        this.nrDecks = input.getNrDecks();
        ArrayList<ArrayList<Card>> convertedDecks = new ArrayList<>();
        for (ArrayList<CardInput> cardInputList : input.getDecks()) {
            ArrayList<Card> cardList = new ArrayList<>();
            for (CardInput cardInput : cardInputList) {
                cardList.add(new Card(cardInput));
            }
            convertedDecks.add(cardList);
        }
        this.decks = convertedDecks;
    }
}
