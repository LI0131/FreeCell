import javax.swing.*;
import java.awt.*;

/**
 * Represents the GUI component for painting an image of a playing card.
 * @author lambertk
 *
 */
public class SingleCardPanel extends AbstractPanel{

	/**
     * Constructor for a single card panel
     */
    public SingleCardPanel(Cell cell){
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
    		Card card = this.cell.getTopCard();
    		image = card.getImage();
			int x = (getWidth() - image.getIconWidth()) / 2;
			int y = ((getHeight()) - image.getIconHeight()) / 2;
			image.paintIcon(this, g, x, y);
    	}

    }

}