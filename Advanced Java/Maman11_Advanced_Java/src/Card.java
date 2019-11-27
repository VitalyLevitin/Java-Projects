public class Card {
    private final String face; // Face of cards (Ace, Deuce ....)
    private final String suit; // Suit of cards (Hearts, Diamonds ....)
    private int strength = 0;

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
