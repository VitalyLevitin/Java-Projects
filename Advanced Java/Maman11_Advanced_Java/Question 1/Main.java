import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DeckofCards p1d, p2d, maind;//Player 1 deck, Player 2 deck, and main deck.
        Card p1, p2; // The card which the corresponding player is holding.
        ArrayList<Card> pool = new ArrayList<>();
        p1d = new DeckofCards();
        p2d = new DeckofCards();
        p1d.empty();//The constructor adds cards to the decks but we them empty to avoid duplicates.
        p2d.empty();
        maind = new DeckofCards();
        maind.dealCards(p1d,p2d);
        p1 = p1d.placeCard();
        p2 = p2d.placeCard();
        while(true)//Endless loop. Loop will break once a victor will emerge.
        {
            maind.fight(p1,p2,p1d,p2d,pool);
            if(p1d.isEmpty() || p2d.isEmpty())
                System.exit(0);
            p1 = p1d.placeCard();
            p2 = p2d.placeCard();
        }
    }
}
