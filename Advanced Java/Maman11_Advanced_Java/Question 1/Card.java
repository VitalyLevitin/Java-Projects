public class Card {
    private final String face;
    private final String suit;
    private int strength; //Setting Strength so we could compare between the cards.
    //Ace is the strongest and Deuce is the weakest. (Suit has no impact)

    public Card(String cardFace, String cardSuit, int cardStrength) {
        face = cardFace;
        suit = cardSuit;
        strength= cardStrength;
    }

    public int getStrength() {
        return strength;
    }

    public String toString() {
        return face + " of " + suit;
    }
}
