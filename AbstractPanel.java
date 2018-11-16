import javax.swing.*;
import java.awt.*;

abstract public class AbstractPanel extends JPanel {
	
	protected Cell cell;
	
	public AbstractPanel(Cell cell) {
		setBackground(Color.green);
		this.cell = cell;
	}
	
	/**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
