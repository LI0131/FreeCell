import java.awt.Dimension;
import javax.swing.JFrame;
import model.GameModel;
import view.AppView;

/**
 * Generic main method template for any GUI-based application.
 * Instantiates a model and passes it to a new view.
 * @author lambertk, Liam McCann, Drew Thompson, Jay Roberts, Pierce Cusick
 */
public class FreeCellApp{

    public static void main(String[] args){
        final GameModel model = new GameModel();
        final JFrame view = new AppView(model);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(800, 600);
        view.setMinimumSize(new Dimension(800, 600));
        view.setVisible(true);
    }
    
}
