import java.security.SecureRandom;
import java.util.ArrayList;


public class DeckofCards {
    private static final int NUMBER_OF_CARDS = 52;							// Constant number of cards
    private static final SecureRandom randomNumbers = new SecureRandom(); 	// Random number generator
    private ArrayList<Card> deck; 											// ArrayList of Card objects

    public DeckofCards() {
        //	these two arrays were taken from the book and they represent the available cards
        String[] faces = {"Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new ArrayList<>(NUMBER_OF_CARDS);    // the deck that contains the cards
        // sets the cards in the deck
        for (int counter = 0; counter < NUMBER_OF_CARDS; counter++){
            deck.add(counter, new Card(faces[counter % 13], suits[counter / 13],counter%13));
        }
        shuffle(deck);
    }

    public void shuffle(ArrayList<Card> deck) {
        for(Card card:deck) {
            Card randomCard = deck.get(randomNumbers.nextInt(deck.size()));
            int currentIndex = deck.indexOf(card);
            int randomIndex  = deck.indexOf(randomCard);
            deck.set(currentIndex, randomCard);
            deck.set(randomIndex, card);
        }
    }
    public void empty (){
        deck.clear();
    }
    public void dealCards(DeckofCards player1, DeckofCards player2){
        for (int i = 0; i < deck.size(); i++) {
            if(i%2==0)
                player1.addCard(deck.get(i));
            else
                player2.addCard(deck.get(i));
        }
        deck.clear();
    }
    public void addCard(Card card) {
        deck.add(card);
    }
    public Card placeCard() {
        if(deck != null){
            return deck.remove(0);}
        return null;
    }

    public void collect(ArrayList<Card> pool){
        shuffle(pool);
        deck.addAll(pool);
        pool.clear();
    }

    public void fight(Card player1, Card player2, DeckofCards player1Deck, DeckofCards player2Deck, ArrayList<Card> pool) {
        if(player1Deck.isEmpty() && player2Deck.isEmpty()){
            System.out.println("draw");
            return;
        }
        if (player1.getStrength() > player2.getStrength()) {
            if(player2Deck.isEmpty()) {
                System.out.println("p1 game winner");
                return;
            }
            if(!pool.isEmpty()) player1Deck.collect(pool);
            System.out.println("p1 round winner");
            return;
        }
        else if (player2.getStrength() > player1.getStrength()) {
            if (player1Deck.isEmpty()) {
                System.out.println("p2 game winner");
                return;
            }
            if(!pool.isEmpty()) player2Deck.collect(pool);
            System.out.println("p2 round winner");
            return;
        }
        else
            System.out.println("WAR");
            for (int i = 0; i < 3; i++) {
                if(!player1Deck.isEmpty()) pool.add(player1 = player1Deck.placeCard());
                if(!player2Deck.isEmpty()) pool.add(player2 = player2Deck.placeCard());
            }
            fight(player1, player2, player1Deck, player2Deck, pool);
        }

    public boolean isEmpty()
    {
        return deck.isEmpty();
    }
}
