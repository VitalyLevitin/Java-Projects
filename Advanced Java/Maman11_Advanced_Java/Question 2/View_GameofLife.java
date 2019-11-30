import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_GameofLife extends JPanel {
    private GameofLife matrix;
    private int offset = 50;
    public View_GameofLife() {
        matrix = new GameofLife();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        int h = getHeight();
        int w = getWidth();
        for (int x = 0; x < matrix.getArraySize(); x++) {
            for (int y = 0; y < matrix.getArraySize(); y++) {
                if (!matrix.getCordinates(x, y)) {
                    g.setColor(Color.white);
                    g.fillRect(x*offset, y*offset, offset, offset);
                }
                if (matrix.getCordinates(x, y)) {
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
        while(input == 0) {
            input = JOptionPane.showConfirmDialog(null, "Proceed to the next generation?",
                    "Game of Life", JOptionPane.YES_NO_OPTION);
            matrix.nextGeneration();
            repaint();
            matrix.swap();
        }
    }
}
