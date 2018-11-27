import java.util.*;

/**
* Implements the necessary methods for the Tableau Cell type
* @author Liam McCann
* @author Jay Roberts
* @author Pierce Cusick
* @author Drew Thompson
*/

public class Tableau extends AbstractCell {
	
	public Tableau() {
		super();
	}
	
	@Override
	public Card isSorted() {
		//Declare iterators and card pointers
		ListIterator<Card> tabIter = this.iterator(this.size());
		ListIterator<Card> tabTrailer = this.iterator(this.size());
		Card prevCard;
		Card thisCard = null;
		//set iterator count
		int listCounter = 0;
		//while cards are not equal and iterator has next
		while( tabIter.hasPrevious() ) {
			//wait for this card to be set
			if( listCounter > 1) {
				// set previous card
				tabTrailer.hasPrevious();
				prevCard = tabTrailer.previous();
				// compare for correct suit
				if (! prevCard.isDifferentColor(thisCard)) { return prevCard; }
				//compare for correct rank
				if ( thisCard.getRank() != prevCard.getRank() + 1 ) { return prevCard; }
			}
			//set this card
			thisCard = tabIter.previous();
			//increment counts
			listCounter++;
		}
		return super.cell.get(0);
	}
	
	@Override
	public boolean moveCards(Cell fromPile, Cell toPile) {
		if ( fromPile instanceof Tableau && toPile instanceof Tableau ) {
			//get an array of sorted cards in the tableau as an array list
			ArrayList<Card> sortedCards = new ArrayList<Card>(); 
			Card lastSortedCard = fromPile.isSorted();
			ListIterator<Card> cellIter = fromPile.iterator(fromPile.size());
			while( cellIter.hasPrevious() ) {
				Card nextCard = cellIter.previous();
				if( nextCard != lastSortedCard ) { 
					sortedCards.add(nextCard); 
				} else { 
					sortedCards.add(nextCard); 
					break; 
				}
			}
			
			if( toPile.getTopCard() == null ) {
				this.movingCards = sortedCards; 
			} else if ( fromPile.size() == 1 ) {
				this.movingCards.add(fromPile.getTopCard());
			} else {
				//add to an existing tableau of cards
				Card TopOfToPile = toPile.getTopCard();	
				int indexToAddTo = -1;
				for(int i = sortedCards.size() - 1; i >= 0; i--) {
					if( TopOfToPile.isGreaterByOne(sortedCards.get(i)) && TopOfToPile.isDifferentColor(sortedCards.get(i))) { indexToAddTo = i; }
				}
				
				for( int i = 0; i <= indexToAddTo; i++ ) {
					this.movingCards.add(sortedCards.get(i));
				}
			}
			
			if( this.movingCards.size() == 0 ) { return false; }
			
			int removeCount = 0;
			
			for( int i = this.movingCards.size() - 1; i >= 0; i-- ) {
				toPile.add(this.movingCards.get(i));
				System.out.println(toPile);
				System.out.println(fromPile);
				removeCount++;
			}
			
			for( int i = 0; i < removeCount; i++ ) { fromPile.remove(); } 
			
			this.movingCards.clear();
			return true;
			
		} else {
			return super.moveCards(fromPile, toPile);
		}
	}
	
	@Override
	public boolean addAll(ArrayList<Card> cards) {
		for( Card c: cards ) {
			this.cell.add(c);
		}
		return true;
	}
	
	@Override
	public boolean canAddTo(Card card) {
		if( this.size() == 0 ) { return true; }
		Card topCard = this.cell.get(this.cell.size() - 1);
		if(! topCard.isDifferentColor(card) ) { return false; }
		if(! topCard.isGreaterByOne(card) ) { return false; }
		return true;
	}
	
}
