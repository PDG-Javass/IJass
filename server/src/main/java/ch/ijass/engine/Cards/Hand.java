package ch.ijass.engine.Cards;

import java.util.Collections;
import java.util.Vector;

public class Hand extends Deck {

    public Hand() { content = new Vector<>(); }

    public void sort() { Collections.sort(content); }
}
