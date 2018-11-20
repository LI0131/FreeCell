import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * The main window for the game of FreeCell.
 * @author Liam McCann
 * @author Drew Thompson
 * @author Jay Roberts
 * @author Pierce Cusick
 */
public class AppView extends JFrame{
	
    private GameModel model;

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
            MultiCardPanel topCells = new MultiCardPanel(cell);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            i--;
        }
        for( Cell cell: model.HomeCells ) {
    		constraints.gridx = j;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            constraints.weighty = 3;
            constraints.fill = GridBagConstraints.BOTH;
            SingleCardPanel topCells = new SingleCardPanel(cell);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            j++;
        }
        for( Cell cell: model.FreeCells ) {
    		constraints.gridx = j;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            constraints.weighty = 3;
            constraints.fill = GridBagConstraints.BOTH;
            SingleCardPanel topCells = new SingleCardPanel(cell);
            topCells.setBackground(new Color(37, 149, 37));
            topCells.setMinimumSize(new Dimension(100, 100));
            topCells.setSize(new Dimension(200, 200));
            c.add(topCells, constraints);
            j++;
        }

        // Setup New Game Button
        JButton newButton = new JButton("New Game");
        newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.newGame();
				AppView.this.repaint();
            }});
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 8;
        constraints.fill = GridBagConstraints.NONE;
        c.add(newButton, constraints);
        this.add(c);
    }
}