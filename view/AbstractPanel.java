package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Cell;
import model.Card;

abstract public class AbstractPanel extends JPanel {
	
	protected Cell cell;
	private ViewInformer viewInformer;
	
	public AbstractPanel(Cell cell, ViewInformer viewInformer) {
		setBackground(Color.green);
		this.cell = cell;
		this.viewInformer = viewInformer;
		addMouseListener(new PanelListener());
	}
	
	/**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public Cell getCell() {
		return this.cell;
	}
	
	public boolean makeMove(AbstractPanel fromPanel, AbstractPanel toPanel) {
		Cell fromPile = fromPanel.cell;
		Cell toPile = toPanel.cell;
		if (fromPile.canMoveCards(fromPile, toPile)) {
			fromPile.moveCards(fromPile, toPile);
			return true;
		} else { return false; }
	}
	
	private class PanelListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			viewInformer.panelPressed(AbstractPanel.this);
		}
		
	}
	
}
