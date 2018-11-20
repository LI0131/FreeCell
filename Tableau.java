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
		while( tabIter.hasNext()) {
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
		return super.cell.get(cell.size() - 1);
	}
	
	public boolean moveCards(Cell fromPile, Cell toPile) {
		return false;
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
