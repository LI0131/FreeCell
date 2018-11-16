import java.util.*;

/**
* Implements the necessary methods for the Game Model
* @author Liam McCann
* @author Jay Roberts
* @author Pierce Cusick
* @author Drew Thompson
*/

public class GameModel {
	
	private Deck deck;
	private HashMap<String, Cell> gameMap = new HashMap<String, Cell>();
	private ArrayList<Card> movingCards = new ArrayList<Card>();
	
	public GameModel() {
		this.instantiateCells();
		this.newGame();
	}
	
	private void instantiateCells() {
		gameMap.put("Home1", new HomeCell());
		gameMap.put("Home2", new HomeCell());
		gameMap.put("Home3", new HomeCell());
		gameMap.put("Home4", new HomeCell());
		gameMap.put("Free1", new FreeCell());
		gameMap.put("Free2", new FreeCell());
		gameMap.put("Free3", new FreeCell());
		gameMap.put("Free4", new FreeCell());
		gameMap.put("Tab1", new Tableau());
		gameMap.put("Tab2", new Tableau());
		gameMap.put("Tab3", new Tableau());
		gameMap.put("Tab4", new Tableau());
		gameMap.put("Tab5", new Tableau());
		gameMap.put("Tab6", new Tableau());
		gameMap.put("Tab7", new Tableau());
		gameMap.put("Tab8", new Tableau());
	}
	
	public void newGame() {
		for(Cell cell: gameMap.values()) {
			cell.clear();
		}
		this.deck = new Deck();
		this.deck.shuffle();
		this.dealDeck();
	}
	
	private Card getDealtCard() {
		Card card = deck.deal();
		card.turn();
		return card;
	}
	
	public Cell getCell(String name) {
		return gameMap.get(name);
	}
	
	public void dealDeck() {
		List<String> namesList = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8");
		int iterCount = 1;
		
		for( String name: namesList ) {
			ArrayList<Card> cards = new ArrayList<Card>();
			Cell cell = gameMap.get(name);
			if( iterCount > 4 ) {
				for(int i = 0; i < 6; i++) { cards.add(this.getDealtCard()); }
				cell.addAll(cards);
			} else {
				for(int i = 0; i < 7; i++) { cards.add(this.getDealtCard()); }
				cell.addAll(cards);
			}
			iterCount++;
		}
	}
	
	/*public boolean moveOneCard(Cell fromPile, Cell toPile) {
		
		Card movingCard;
		
		if ( fromPile.canRemoveFrom() == false ) {
			return false;
		} else { movingCard = fromPile.remove(); }
		System.out.println(movingCard);
		
		if( toPile.canAddTo(movingCard) ) {
			toPile.add(movingCard);
			return true;
		} else { return false; }
		
	}*/
	
	/*public boolean moveManyCards(Tableau fromPile, Cell toPile, Card highestMovingCard) {
		if( fromPile.isSorted(highestMovingCard) ) {
			if ( toPile.canAddTo(highestMovingCard) ) {
				toPile.add(highestMovingCard);
				this.movingCards.push(fromPile.remove());
				while( this.movingCards.peek() != highestMovingCard ) {
					this.movingCards.push(fromPile.remove());
				}
				while( !this.movingCards.isEmpty() ) {
					toPile.add(this.movingCards.pop());
				}
				fromPile.remove();
				return true;
			}
			return false;
		} else { return false; }
	}*/
	
	private int getIndexOfCard(ArrayList<Card> list, Card checkCard) {
		for(int i = list.size() - 1; i >= 0; i--) {
			if( checkCard.isGreaterByOne(list.get(i)) && checkCard.isDifferentColor(list.get(i))) { return i; }
		}
		return -1;
	}
	
	public boolean moveCards(Cell fromPile, Cell toPile) {
		
		//make sure fromPile is non-empty
		if( fromPile.getTopCard() == null ) { return false; }
		
		//get the Card at the top of the piles
		Card topOfToPile = toPile.getTopCard();
		Card topOfFromPile = fromPile.getTopCard();
		
		//if fromPile is a tableau
		if( fromPile instanceof Tableau ) {
			//get an array of sorted cards in the tableau as an array list
			ArrayList<Card> sortedCards = new ArrayList<Card>(); 
			Card lastSortedCard = ((Tableau) fromPile).isSorted();
			Iterator cellIter = fromPile.iterator();
			while( cellIter.hasNext() && cellIter.next() != lastSortedCard ) {
				sortedCards.add((Card) cellIter.next());
			}
			
			//add to an empty Cell
			if( toPile instanceof Tableau ) {
				if( topOfToPile == null ) { 
					this.movingCards = sortedCards; 
				} else {
					//add to an existing tableau of cards
					int indexToAddTo = this.getIndexOfCard(sortedCards, topOfToPile);
					if( indexToAddTo == -1 ) { return false; }
					
					for( int i = 0; i <= indexToAddTo; i++ ) {
						this.movingCards.add(sortedCards.get(i));
					}
				}
				
			} else {
				if (toPile.canAddTo(topOfFromPile)) {
					this.movingCards.add(topOfFromPile);
				} else {
					return false;
				}
			}
					
		//for when the fromPile is not a tableau 
		} else {
			if ( !fromPile.canRemoveFrom() ) { return false; }
			
			//compare top of each pile and add if possible
			if ( topOfToPile.isGreaterByOne(topOfFromPile) && topOfToPile.isDifferentColor(topOfFromPile) ) {
				this.movingCards.add(topOfFromPile);
			} else { return false; }
		}
		return true;
	}
	
	public boolean hasWinner() {
		List<String> namesList = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8");
		
		for(String name: namesList) {
			Tableau toTest = (Tableau) gameMap.get(name);
			if( toTest.isSorted() != null ) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String output = "";
		for (Cell cell: gameMap.values() ) {
			output += cell.toString();
		}
		return output;
	}
	
	public Iterator<String> iterator() {
		return this.gameMap.keySet().iterator();
	}

}