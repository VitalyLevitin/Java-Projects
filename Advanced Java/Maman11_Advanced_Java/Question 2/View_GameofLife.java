import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_GameofLife extends JPanel {
    private GameofLife matrix;
    private int offset = 50; //Dimension of the cube (x,y)
    public View_GameofLife() {
        matrix = new GameofLife();
        repaint();
    }

    /**
     * This method repaints the JPanel depending on the life/death status of the game.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        int h = getHeight();
        int w = getWidth();
        for (int x = 0; x < matrix.getArraySize(); x++) {
            for (int y = 0; y < matrix.getArraySize(); y++) {
                if (!matrix.getCordinates(x, y)) { //White means no life in the specific cube (x,y)
                    g.setColor(Color.white);
                    g.fillRect(x*offset, y*offset, offset, offset);
                }
                if (matrix.getCordinates(x, y)) { //Green == Life.
                    g.setColor(Color.green);
                    g.fillRect(x*offset, y*offset, offset, offset);
                }
                g.setColor(Color.black);
                g.drawLine(x*offset,0,x*offset,h);
                g.drawLine(0,y*offset,w,y*offset);
            }
        }
    }

    public void sprout(){
        int input = 0;
        while(input == 0) {// 0 == Yes option, meaning we want the next generation.
            input = JOptionPane.showConfirmDialog(null, "Proceed to the next generation?",
                    "Game of Life", JOptionPane.YES_NO_OPTION);
            matrix.nextGeneration();
            repaint();
            matrix.swap();//Swaps between the values of the first and second Matrix (aka first and second gen)
        }
        System.exit(0);//User decided that he saw enough gens, so we close the app.
    }
}
