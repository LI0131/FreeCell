import java.util.*;
/**
* This file implements the necessary cell methods for all cell types
* @author Liam McCann
* @author Jay Roberts
* @author Pierce Cusick
* @author Drew Thompson
*/

public abstract class AbstractCell implements Cell {
	
	protected ArrayList<Card> cell;
	protected ArrayList<Card> movingCards = new ArrayList<Card>();
	
	public AbstractCell() {
		this(new ArrayList<Card>());
	}
	
	public AbstractCell(ArrayList<Card> c) {
		this.cell = c;
	}
	
	abstract public boolean moveCards(Cell fromPile, Cell toPile);
	
	public Card getTopCard() {
		if( this.size() == 0 ) { return null; }
		return this.cell.get(this.cell.size() - 1);
	}
	
	public void clear() {
		this.cell = new ArrayList<Card>();
	}
	
	public boolean isEmpty() {
		if( this.size() > 0 ) { return false; }
		else { return true; }
	}
	
	public int size() {
		return this.cell.size();
	}
	
	public boolean add(Card card) {
		if( this.canAddTo(card) ) {
			this.cell.add(card);
			return true;
		} else { return false; }
	}
	
	public boolean addAll(ArrayList<Card> cards) {
		throw new UnsupportedOperationException("Cannot add multiple cards");
	}
	
	public Card remove() {
		if( this.canRemoveFrom() ) {
			return this.cell.remove(this.cell.size() - 1);
		} else { return null; }
	}
	
	public boolean canRemoveFrom() {
		if( this.isEmpty() || this instanceof HomeCell ) {
			return false;
		} else { return true; }
	}
	
	public boolean canAddTo(Card card) {
		if( this.size() == 0 ) {
			return true;
		} else { return false; }
	}

	
	public Iterator<Card> iterator() {
		return this.cell.iterator();
	}
	
	public String toString() {
		return this.cell.toString();
	}
	
	public Card isSorted() {
		throw new UnsupportedOperationException("Cell of this type cannot be sorted");
	}

}
