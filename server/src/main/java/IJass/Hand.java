package IJass;

import java.util.Collections;
import java.util.Vector;

public class Hand {
    private Vector<Card> content;

    public Hand() { content = new Vector<>(); }

    public void addCard(Card card) {
        if (card == null)
            throw new RuntimeException("Adding null card to hand");
        content.add(card);
    }

    public void sort() { Collections.sort(content); }
}
