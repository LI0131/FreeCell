package model;

/**
* Implements the necessary methods for the Home Cell type
* @author Liam McCann
* @author Jay Roberts
* @author Pierce Cusick
* @author Drew Thompson
*/

public class HomeCell extends AbstractCell {
	
	public HomeCell() {
		super();
	}
	
	@Override
	public boolean canMoveCards(Cell fromPile, Cell toPile) {
		if( fromPile instanceof HomeCell ) {
			return false;
		} else { return super.canMoveCards(fromPile, toPile); }
	}
	
	@Override
	public boolean add(Card card) {
		if( this.canAddTo(card) ) {
			this.cell.clear();
			this.cell.add(card);
			return true;
		} else { return false; }
	}
	
	@Override
	public boolean canAddTo(Card card) {
		if ( this.size() == 0 ) { 
			if( card.getRank() == 1 ) {
				return true;
			} else { return false; }
		} else {
			Card currentCard = this.cell.get(0);
			if (currentCard.getSuit().compareTo(card.getSuit()) != 0 ) { return false; }
			if (currentCard.getRank() != card.getRank() - 1) { return false; }
		}
		return true;
	}

	@Override
	public Card remove() {
		throw new UnsupportedOperationException("Remove not supported by HomeCell.");
	}
	
	@Override
	public boolean canRemoveFrom() {
		return false;
	}	

}
