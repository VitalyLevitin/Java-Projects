import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DeckofCards p1d, p2d, maind;
        Card p1, p2;
        ArrayList<Card> pool = new ArrayList<>();
        p1d = new DeckofCards();
        p2d = new DeckofCards();
        p1d.empty();
        p2d.empty();
        maind = new DeckofCards();
        maind.dealCards(p1d,p2d);
        p1 = p1d.placeCard();
        p2 = p2d.placeCard();
        int i = 0;
        while(i!=-1)
        {

            maind.fight(p1,p2,p1d,p2d,pool);
            if(p1d.isEmpty()&& p2d.isEmpty()) {
                System.out.println("draw");
                i = -1;
                break;
            }
            if(p1d.isEmpty()) {
                System.out.println("p2 win");
                i = -1;
                break;
            }
            if(p2d.isEmpty()) {
                System.out.println("p1 win");
                i = -1;
                break;
            }
            p1 = p1d.placeCard();
            p2 = p2d.placeCard();
        }
    }
}
