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
		Iterator<Card> tabIter = this.iterator();
		Iterator<Card> tabTrailer = this.iterator();
		Card prevCard;
		Card thisCard = null;
		//set iterator count
		int stackCounter = 0;
		//while cards are not equal and iterator has next
		while( tabIter.hasNext() ) {
			//wait for this card to be set
			if( stackCounter > 1) {
				// set previous card
				tabTrailer.hasNext();
				prevCard = tabTrailer.next();
				// compare for correct suit
				if (! prevCard.isDifferentColor(thisCard)) { return prevCard; }
				//compare for correct rank
				if ( thisCard.getRank() != prevCard.getRank() + 1 ) { return prevCard; }
			}
			//set this card
			thisCard = tabIter.next();
			//increment counts
			stackCounter++;
		}
		return super.cell.get(0);
	}
	
	@Override
	public boolean moveCards(Cell fromPile, Cell toPile) {
		if ( fromPile instanceof Tableau && toPile instanceof Tableau ) {
			//get an array of sorted cards in the tableau as an array list
			ArrayList<Card> sortedCards = new ArrayList<Card>(); 
			Card lastSortedCard = fromPile.isSorted();
			System.out.println(lastSortedCard);
			Iterator<Card> cellIter = fromPile.iterator();
			while( cellIter.hasNext() && cellIter.next() != lastSortedCard ) {
				sortedCards.add(cellIter.next());
			}
			System.out.println(sortedCards);
			if( fromPile.getTopCard() == null ) {
				this.movingCards = sortedCards; 
				System.out.println("is null");
			} else {
				System.out.println("is not null");
				//add to an existing tableau of cards
				Card TopOfToPile = toPile.getTopCard();	
				int indexToAddTo = -1;
				for(int i = sortedCards.size() - 1; i >= 0; i--) {
					if( TopOfToPile.isGreaterByOne(sortedCards.get(i)) && TopOfToPile.isDifferentColor(sortedCards.get(i))) { indexToAddTo = i; }
				}
				
				for( int i = 0; i <= indexToAddTo; i++ ) {
					this.movingCards.add(sortedCards.get(i));
				}
				for( Card card: this.movingCards ) {
					toPile.add(card);
					fromPile.remove();
				}
				this.movingCards.clear();
				return true;
			}
			return false;
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
