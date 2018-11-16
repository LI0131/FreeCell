import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class MultiCardPanel extends AbstractPanel {
	
	/**
     * Constructor for a multi card panel
     */
	public MultiCardPanel(Cell cell) {
		super(cell);
	}
	
	/**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
	public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Icon image;
    	if( cell.isEmpty() ) {
    		image = Card.getBack();
    		g.setColor(Color.yellow);
    		int x = (getWidth() - image.getIconWidth()) / 2;
    		int y = (getHeight() - image.getIconHeight()) / 2;
    		g.drawRect(x, 0, image.getIconWidth(), image.getIconHeight());
    	} else {
    		Iterator<Card> cellIter = cell.iterator();
    		int scale = 5;
    		while(cellIter.hasNext()) {
    			Card card = cellIter.next();
    			image = card.getImage();
    			int x = (getWidth() - image.getIconWidth()) / 2;
    			int y = scale;
    			image.paintIcon(this, g, x, y);
    			scale += 30;
    		}
    	}
	}
}