package model;

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
		ListIterator<Card> iterator = this.iterator(this.size());
		
		int iterCount = 0;
		
		while(iterator.hasPrevious()) {
			iterator.previous();
			if (iterCount >= 1) { iterator.previous(); }
			if(iterator.hasPrevious()) {
				Card thisCard = iterator.previous();
				iterator.next();
				Card prevCard = iterator.next(); 
				
				iterCount++;
				
				if (! thisCard.isDifferentColor(prevCard)) { return prevCard; }
				if ( thisCard.getRank() != prevCard.getRank() + 1 ) { return prevCard; }
					
			}
		}
		
		return null;
		
	}
	
	@Override
	public boolean canMoveCards(Cell fromPile, Cell toPile) {
		if ( fromPile instanceof Tableau && toPile instanceof Tableau ) {
			//get an array of sorted cards in the tableau as an array list
			ArrayList<Card> sortedCards = new ArrayList<Card>(); 
			Card lastSortedCard = fromPile.isSorted();
			ListIterator<Card> cellIter = fromPile.iterator(fromPile.size());
			if( lastSortedCard == null ) {
				while( cellIter.hasPrevious() ) {
					sortedCards.add(cellIter.previous());
				}
			} else {
				while( cellIter.hasPrevious() ) {
					Card nextCard = cellIter.previous();
					if( nextCard != lastSortedCard ) { 
						sortedCards.add(nextCard); 
					} else { 
						sortedCards.add(nextCard);
						break; 
					}
				}
			}
			
			if( toPile.getTopCard() == null ) {
				this.movingCards = sortedCards; 
			} else if ( fromPile.size() == 1 ) {
				Card fromPileTopCard = fromPile.getTopCard();
				if (toPile.canAddTo(fromPileTopCard)) {
					this.movingCards.add(fromPile.getTopCard());
				} else { return false; }
			} else {
				int indexInArray = 0;
				int indexToAddTo = -1;
				for( Card card: sortedCards ) {
					if( toPile.canAddTo(card) ) {
						indexToAddTo = indexInArray;
					}
					indexInArray++;
				}
				
				movingCards.clear();
				
				if( indexToAddTo == -1 ) { return false; }
				
				//add to an existing tableau of cards
				for( int i = 0; i <= indexToAddTo; i++ ) {
					Card nextCard = sortedCards.get(i);
					System.out.println("Next Card: " + nextCard);
					this.movingCards.add(nextCard);
					System.out.println("MC: " + movingCards);
				}
				
				System.out.println("Sorted Cards: " + sortedCards);
				System.out.println("MC: " + movingCards);
				System.out.println("Index: " + indexToAddTo);
				
			}
			
			if( this.movingCards.size() == 0 ) { return false; }
			
			return true;
			
		} else {
			return super.canMoveCards(fromPile, toPile);
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
