import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
	* The <code>Cell</code> interface represents the various types
	* of Cell objects in the game of FreeCell.
	* @author Liam McCann
	* @author Jay Roberts
	* @author Pierce Cusick
	* @author Drew Thompson
	*/

public interface Cell extends Iterable<Card>{
	
	public Card getTopCard();
	
	public boolean canMoveCards(Cell fromPile, Cell toPile);
	
	public void moveCards(Cell fromPile, Cell toPile);

	/**
		* Clears the Cell object of all Card objects.
		*/
	public void clear();

	/**
		* Checks if Cell object is empty.
		* @return true if cell object is empty, false if not empty.
		*/
	public boolean isEmpty();

	/**
		* Returns the number of cards in the Cell object as an integer.
		* @return the size of the Cell object.
		*/
	public int size();

	/**
		* Adds a card object to the Cell object, if allowed.
		* @param card A Card object to be added to the Cell object.
		* @return True if card is succesfully added, false otherwise.
		*/
	public boolean add(Card card);

	/**
		* Removes and returns the card at the top of the Cell object.
		* @return the Card object at the top of the pile.
		*/
	public Card remove();

	/**
		* Checks if the Card object at the top of the Cell object is removable.
		* @return True if card at the top of the Cell object is removable, False otherwise.
		*/
	public boolean canRemoveFrom();

	/**
		* Checks if card is able to be added to Cell object based on the rules of FreeCell.
		* @return True if card can be added to Cell object, False otherwise.
		*/
	public boolean canAddTo(Card card);

	/**
		* Returns an iterator on the Cell object.
		* @return The iterator object of Cell object.
		*/
	public Iterator<Card> iterator();
	
	/**
	 	* Returns an ListIterator on the Cell object.
	 	* @return The iterator object of Cell object.
	 	*/
	public ListIterator<Card> iterator(int index);

	/**
		* Creates a string representation of the Cell object.
		* @return the string representation of the Cell object.
		*/
	public String toString();
	
	/**
	 * Adds all the cards to the Cell from a given ArrayList
	 * @param cards -- an ArrayList<Card> to be added to the Cell
	 * @return true if added
	 * @throws UnsupportedOperationException: if of type HomeCell or FreeCell
	 */
	public boolean addAll(ArrayList<Card> cards);
	
	/**
	 * returns the last sorted card in the tableau -- if returns null the whole tableau is sorted
	 * @return Card or Null
	 */
	public Card isSorted();

}