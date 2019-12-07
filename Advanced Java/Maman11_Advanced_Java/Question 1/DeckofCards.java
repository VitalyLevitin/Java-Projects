import javax.swing.*;
import java.security.SecureRandom;
import java.util.ArrayList;


public class DeckofCards {
    private static final int NUMBER_OF_CARDS = 52; //Deck size.
    private static final SecureRandom randomNumbers = new SecureRandom();
    private ArrayList<Card> deck;

    /**
     * Constructor of the decks.
     * Using general faces and suits as in normal game of cards.
     * Special addition is setting strength values via their face values.
     */
    public DeckofCards() { //Method mostly taken from the book.
        String[] faces = {"Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        deck = new ArrayList<>(NUMBER_OF_CARDS);
        for (int counter = 0; counter < NUMBER_OF_CARDS; counter++){
            deck.add(counter, new Card(faces[counter % 13], suits[counter / 13],counter%13));
        }
        shuffle(deck);
    }

    private void shuffle(ArrayList<Card> deck) {
        for(Card card:deck) {//Randomly swaping places between cards in the deck.
            Card randomCard = deck.get(randomNumbers.nextInt(deck.size()));
            int currentIndex = deck.indexOf(card);
            int randomIndex  = deck.indexOf(randomCard);
            deck.set(currentIndex, randomCard);
            deck.set(randomIndex, card);
        }
    }
    public void empty (){//Removing every object in the deck.
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
    private void addCard(Card card) {
        deck.add(card);
    }
    public Card placeCard() {
        if(deck != null){//As we give a card we also remove it from the deck (both logical and to avoid duplication.
            return deck.remove(0);}
        return null;
    }

    //Adds the current pool to the winning player and clears the pool(deck).
    private void collect(ArrayList<Card> pool){
        shuffle(pool);
        deck.addAll(pool);
        pool.clear();
    }

    /**
     * Using the general rules of the war game.
     * The player with the stronger card wins the round and takes the pool.
     * If they have the same card, war mode ensues. (Both player throw 3 cards into the pool and flip the 4th one.)
     * If one of the players deck is empty he lost the game.
     * If both players decks are empty, the game is a draw (happens in war).
     * @param player1
     * @param player2
     * @param player1Deck
     * @param player2Deck
     * @param pool The current cards the players are fighting for.
     */
    public void fight(Card player1, Card player2, DeckofCards player1Deck, DeckofCards player2Deck, ArrayList<Card> pool) {
        if(player1Deck.isEmpty() && player2Deck.isEmpty()){
            JOptionPane.showMessageDialog(null, "Both players decks are empty" +
                    "\n<html><h1><u>Game is a draw<u></h1></html>", "Everyone won(yay)",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (player1.getStrength() > player2.getStrength()) {
            if(player2Deck.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Player's 2 deck is empty." +
                        "\n<html><h1><u><font color=blue>Player 1 has won the game!</font><u></h1></html>",
                        "Player 1 won",JOptionPane.PLAIN_MESSAGE);
                return;
            }
            if(!pool.isEmpty()) player1Deck.collect(pool);
            else{
                JOptionPane.showMessageDialog(null, "Player 1 has "+ player1 + "\nPlayer 2 has " + player2
                        + "\n<html><h1><u><font color=blue>Player 1 won the round</font><u></h1></html>",
                        "Player 1 won",JOptionPane.PLAIN_MESSAGE);
            }
            return;
        }
        else if (player2.getStrength() > player1.getStrength()) {
            if (player1Deck.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Player's 1 deck is empty." +
                        "\n<html><h1><u><font color=green>Player 2 has won the game!</font><u></h1></html>",
                        "Player 2 won",JOptionPane.PLAIN_MESSAGE);
                return;
            }
            if(!pool.isEmpty()) player2Deck.collect(pool);
            else {
                JOptionPane.showMessageDialog(null, "Player 1 has "+ player1 + "\nPlayer 2 has " + player2
                        + "\n<html><h1><u><font color=green>Player 2 won the round</font><u></h1></html>",
                        "Player 2 won",JOptionPane.PLAIN_MESSAGE);
            }
            return;
        }
        else
            JOptionPane.showMessageDialog(null, "Player 1 has "+ player1 + "\nPlayer 2 has " + player2
                    + "\n<html><h1><u><font color=red>It's war time</font><u></h1></html>", "WAR",JOptionPane.PLAIN_MESSAGE);
            for (int i = 0; i < 3; i++) {//If the player has less than 3 cards he will use his last card.
                if(!player1Deck.isEmpty()) pool.add(player1 = player1Deck.placeCard());
                if(!player2Deck.isEmpty()) pool.add(player2 = player2Deck.placeCard());
            }
            fight(player1, player2, player1Deck, player2Deck, pool);
        }

    public boolean isEmpty() //Checker if there are no objects in the current ArrayList.
    {
        return deck.isEmpty();
    }
}
