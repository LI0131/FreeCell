import java.util.*;

/**
* @author Liam McCann
* @author Jay Roberts
* @author Pierce Cusick
* @author Drew Thompson
*/

public class Tester {

	public static void main(String[] args) {
		
		GameModel game = new GameModel();
		
		System.out.println(game);
		
		HomeCell home = new HomeCell();
		FreeCell free = new FreeCell();
		
		ArrayList<Card> list = new ArrayList<Card>();
		list.add(new Card(Suit.spade, 5));
		list.add(new Card(Suit.heart, 4));
		list.add(new Card(Suit.spade, 3));
		
		Tableau tab = new Tableau();
		System.out.println(tab.add(new Card(Suit.heart, 2)));
		
		System.out.println(tab);
		System.out.println(home);
		System.out.println(free);
		
		/*Cell tab1 = game.getCell("Tab1");
		System.out.println(tab1);
		Cell tab3 = game.getCell("Tab3");
		game.moveCards(tab1, tab3);
		System.out.println(game.getCell("Tab3"));
		
		tab1 = game.getCell("Tab1");
		Cell free1 = game.getCell("Free1");
		game.moveCards(tab1, free1);
		System.out.println(game.getCell("Free1"));
		
		Cell tab2 = game.getCell("Tab8");
		Cell home1 = game.getCell("Home1");
		game.moveCards(tab2, home1);
		System.out.println(game.getCell("Home1"));*/
		
		Iterator<Card> iter = tab.iterator();
		
		System.out.println(tab.isSorted());
		
		while( iter.hasNext() ) {
			tab.remove();
			System.out.println(tab);
		}
		
		game.newGame();
		
		System.out.println(game);

	}

}
