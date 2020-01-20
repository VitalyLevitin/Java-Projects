import javax.swing.*;
import java.awt.*;

public class Cube implements Runnable{

    private int size;
    private int passes;
    private int slice;
    private JButton [][]board;
    private boolean [][] values;
    Color black = Color.black;
    Color white = Color.white;
    public Cube(JButton [][]board, int threadsNumber, int size, int passes, int offset) {
        this.size = size;
        this.passes = passes;
        slice = board.length / threadsNumber;
        this.board = board;
        if (offset == threadsNumber && offset%2==0){}

    }
    @Override
    public void run() {

    }
}
