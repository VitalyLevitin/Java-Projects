import javax.swing.*;
import java.awt.*;

public class Cube implements Runnable {

    private int size, passes, slice, offset;
    private JButton[][] board, boardPortion;
    private boolean[][] values;
    Color black = Color.black;
    Color white = Color.white;

    public Cube(JButton[][] board, int threadsNumber, int size, int passes, int offset) {
        this.size = size;
        this.passes = passes;
        this.offset = offset;
        this.board = board;
        int offsetLast = 0;
        slice = board.length / threadsNumber;
        if (offset+1 == threadsNumber)
            offsetLast = board.length - slice * (offset) - slice;
        boardPortion = new JButton[slice+offsetLast][size];
        values = new boolean[slice+offsetLast][size];
        for (int i = 0 ; i < slice + offsetLast ; i++)
        {
            for (int j = 0 ; j < board.length ; j++)
            {
                boardPortion[i][j] = board[i + slice  *(offset)][j];
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < passes; i++) {
            for (int j = 0; j < boardPortion.length; j++) {
                for (int k = 0; k < boardPortion[0].length; k++) {
                    if (boardPortion[j][k].getBackground() == black)
                        checkNeighbours(j, k);
                }
            }
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < values[0].length; k++) {
                    if (values[j][k])
                        boardPortion[j][k].setBackground(white);
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkNeighbours(int x, int y) {
        int i = x;
        x+=offset;
        if(inbounds(x+1,y)){
            if(board[x+1][y].getBackground().equals(white)){
                values[i][y] = true;
                return;
            }
        }
        if(inbounds(x-1,y) && isWhite(board[x-1][y])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x,y+1)&& isWhite(board[x][y+1])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x,y-1)&& isWhite(board[x][y-1])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x+1,y+1)&& isWhite(board[x+1][y+1])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x-1,y+1)&& isWhite(board[x-1][y+1])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x+1,y-1)&& isWhite(board[x+1][y-1])){
            values[i][y] = true;
            return;
        }
        if(inbounds(x-1,y-1)&& isWhite(board[x-1][y-1])){
            values[i][y] = true;
            return;
        }
    }
    private boolean inbounds(int x, int y) {//Making sure to not check an oob cube.
        return ((x>=0 && x<board.length) && (y>=0 && y<board[0].length));
    }

    private boolean isWhite(JButton button) {
        return button.getBackground().equals(white);
    }
}
