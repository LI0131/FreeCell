package view;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import model.GameModel;
import model.Cell;
import model.Card;

/**
 * The main window for the game of FreeCell.
 * @author Liam McCann
 * @author Drew Thompson
 * @author Jay Roberts
 * @author Pierce Cusick
 */
public class AppView extends JFrame{
	
    private GameModel model;
    private AbstractPanel fromPanel;
    private AppViewInformer viewInformer = new AppViewInformer();
    private final JLabel moveCountTitle = new JLabel();
    private TimeHandler th = new TimeHandler();
    private Timer timer = new Timer(1000, th);
    private JLabel timeSpent = new JLabel("Total Time: 00:00");

    public AppView(GameModel model){
        this.model = model;

        setTitle("The Game of FreeCell");
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        Container c = new JPanel(layout);
        constraints.anchor = GridBagConstraints.NORTH;
        
        // Setup HomeCell and FreeCell Titles
        JLabel freeCellTitle = new JLabel("Free Cells");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 4; 
        constraints.fill = GridBagConstraints.NONE;
        c.add(freeCellTitle, constraints);
     
        JLabel homeCellTitle = new JLabel("Home Cells");
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.NONE;
        c.add(homeCellTitle, constraints);
        
      //Setup cells
        int i = 7;
        int j = 0;
        
        for( Cell cell: model.Tableaus ) {
    		constraints.gridx = i;
            constraints.gridy = 2;
            constraints.weightx = 1;
            constraints.weighty = 10;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.BOTH;
            MultiCardPanel topCells = new MultiCardPanel(cell, viewInformer);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            i--;
        }
        for( Cell cell: model.FreeCells ) {
    		constraints.gridx = j;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            constraints.weighty = 3;
            constraints.fill = GridBagConstraints.BOTH;
            SingleCardPanel topCells = new SingleCardPanel(cell, viewInformer);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            j++;
        }
        for( Cell cell: model.HomeCells ) {
    		constraints.gridx = j;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            constraints.weighty = 3;
            constraints.fill = GridBagConstraints.BOTH;
            SingleCardPanel topCells = new SingleCardPanel(cell, viewInformer);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            j++;
        }
        
        // Setup move count
        moveCountTitle.setText("Move Count: " + model.getMoveCount());
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 2; 
        constraints.fill = GridBagConstraints.VERTICAL;
        c.add(moveCountTitle, constraints);
        
        
        // Setup timer
        timer.start();
        constraints.gridx = 6;
        constraints.gridy = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.VERTICAL;
        c.add(timeSpent, constraints);

        // Setup New Game Button
        JButton newButton = new JButton("New Game");
        newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.newGame();
				th.restart();
				timer.restart();
	    		moveCountTitle.setText("Move Count: " + model.getMoveCount());
				AppView.this.repaint();
            }});
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.NONE;
        c.add(newButton, constraints);
        this.add(c);
        
    }
    
    
    private class TimeHandler implements ActionListener {
    	
    	private int timeCounter = 0;
    	private Integer minutes = 0;
    	private Integer seconds = 0; 
    	private String time = "00:00";
    	
    	public void actionPerformed(ActionEvent te) {
    		timeCounter++;
    		if( timeCounter % 60 == 0 ) { minutes++; seconds = 0; }
    		else { seconds++; }
    		
    		if(!model.hasWinner()) { 
    			formatTime();
    			timeSpent.setText("Total Time: " + time);
    		} else {
    			timer.stop();
    			timeSpent.setText("TOTAL TIME: " + time);
    		}
    	}
    	
    	private void formatTime() {
    		String minStr = minutes.toString();
    		String secStr = seconds.toString();
    		
    		if( minutes < 10 ) { time = "0" + minStr + ":"; }
    		else { time = minStr + ":"; }
    		
    		if( seconds < 10 ) { time += "0" + secStr; }
    		else { time += secStr; }
    		
    	}
    	
    	public void restart() {
    		timeCounter = 0;
    		time = "00:00";
    		minutes = 0;
    		seconds = 0;
    	}
    	
    }
    
    
    private class AppViewInformer implements ViewInformer {
    	
    	public void panelPressed(AbstractPanel P) {
    		if( fromPanel == null ) {
    			fromPanel = P;
    		} else if ( fromPanel == P ) {
    			fromPanel = P;
    		} else {
    			if (fromPanel.makeMove(fromPanel, P)) {
    				model.incMoveCount();
    	    		moveCountTitle.setText("Move Count: " + model.getMoveCount());
    				if (model.hasWinner()) { JOptionPane.showMessageDialog(AppView.this, "Winner\n It took " + model.getMoveCount() + " moves."); }
				if (model.hasLoser()) { JOptionPane.showMessageDialog(AppView.this, "Loser"); }
    	    		repaint();
    			} else {
    				if (model.hasLoser()) { JOptionPane.showMessageDialog(AppView.this, "Loser"); }
    				JOptionPane.showMessageDialog(AppView.this, "Illegal Move");
    			}
    			fromPanel = null;
    		}
    		repaint();
    	}
    	
    }
    
}
