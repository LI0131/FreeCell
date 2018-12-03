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
	public List<Cell> HomeCells = new ArrayList<Cell>();
	public List<Cell> FreeCells = new ArrayList<Cell>();
	public List<Cell> Tableaus = new ArrayList<Cell>();
	private ArrayList<Card> movingCards = new ArrayList<Card>();
	private int moveCount = 0;
	
	public GameModel() {
		this.instantiateCells();
		this.newGame();
	}
	
	private void instantiateCells() {
		for (int i = 0; i < 4; i++) { HomeCells.add(new HomeCell()); }
		for (int i = 0; i < 4; i++) { FreeCells.add(new FreeCell()); }
		for (int i = 0; i < 8; i++) { Tableaus.add(new Tableau()); }
	}
	
	public void newGame() {
		for( Cell c: HomeCells ) { c.clear(); }
		for( Cell c: FreeCells ) { c.clear(); }
		for( Cell c: Tableaus ) { c.clear(); }
		this.deck = new Deck();
		this.deck.shuffle();
		this.moveCount = 0;
		this.dealDeck();
	}
	
	private Card getDealtCard() {
		Card card = deck.deal();
		card.turn();
		return card;
	} 
	
	public void dealDeck() {
		int iterCount = 1;
		for( Cell tab : Tableaus ) {
			ArrayList<Card> cards = new ArrayList<Card>();
			if( iterCount <= 4 ) {
				for(int i = 0; i < 6; i++) { cards.add(this.getDealtCard()); }
				tab.addAll(cards);
			} else {
				for(int i = 0; i < 7; i++) { cards.add(this.getDealtCard()); }
				tab.addAll(cards);
			}
			iterCount++;
		}
	}
	
	public boolean hasWinner() {
		for( Cell toTest: Tableaus ) {
			if( toTest.isSorted() != null ) { return false; }
		}
		return true;
	}
	
	public boolean hasLoser() {
		
		for( int i = 0; i < Tableaus.size(); i++ ) {
			Cell fromPileToTest = Tableaus.get(i);
			for( int j = i + 1; j < Tableaus.size(); j++ ) {
				Cell toPileToTest = Tableaus.get(j);
				if( fromPileToTest.canMoveCards(fromPileToTest, toPileToTest) ) { return false; }
			}
		}
		
		for( int i = 0; i < Tableaus.size(); i++ ) {
			Cell fromPileToTest = Tableaus.get(i);
			for( int j = 0; j < FreeCells.size(); j++ ) {
				Cell toPileToTest =  FreeCells.get(j);
				if( fromPileToTest.canMoveCards(fromPileToTest, toPileToTest) ) { return false; }
			}
		}
		
		System.out.println("2");
		
		for( int i = 0; i < FreeCells.size(); i++ ) {
			Cell fromPileToTest = FreeCells.get(i);
			for( int j = 0; j < Tableaus.size(); j++ ) {
				Cell toPileToTest =  Tableaus.get(j);
				if( fromPileToTest.canMoveCards(fromPileToTest, toPileToTest) ) { return false; }
			}
		}
		
		System.out.println("3");
		
		for( int i = 0; i < FreeCells.size(); i++ ) {
			Cell fromPileToTest = FreeCells.get(i);
			for( int j = 0; j < HomeCells.size(); j++ ) {
				Cell toPileToTest =  HomeCells.get(j);
				if( fromPileToTest.canMoveCards(fromPileToTest, toPileToTest) ) { return false; }
			}
		}
		
		System.out.println("4");
		
		for( int i = 0; i < Tableaus.size(); i++ ) {
			Cell fromPileToTest = Tableaus.get(i);
			for( int j = 0; j < HomeCells.size(); j++ ) {
				Cell toPileToTest =  HomeCells.get(j);
				if( fromPileToTest.canMoveCards(fromPileToTest, toPileToTest) ) { return false; }
			}
		}
		
		System.out.println("5");
		
		return true;
		
	}
	
	public String toString() {
		String output = "";
		for (Cell cell: HomeCells ) { output += cell.toString(); }
		for (Cell cell: FreeCells ) { output += cell.toString(); }
		for (Cell cell: Tableaus ) { output += cell.toString(); }
		return output;
	}
	
	public String getMoveCount() {
		return Integer.toString(this.moveCount);
	}
	
	public void incMoveCount() {
		this.moveCount ++;
	}


}