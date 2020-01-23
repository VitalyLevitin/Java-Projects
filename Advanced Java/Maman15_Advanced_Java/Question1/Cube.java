import javax.swing.*;
import java.awt.*;

public class Cube implements Runnable {

    private int passes, slice, offset;
    private JButton[][] board, boardPortion;
    private boolean[][] values;
    Color black = Color.black;
    Color white = Color.white;

    /**
     * Setting the threads to work on different locations at the array.
     * Each array will get a slice he's in charge of.
     * @param board the JButton 2d array.
     * @param threadsNumber how many threads will be in the process.
     * @param size size of the array.
     * @param passes how many passages should the threads make.
     * @param offset keeping track of the final thread.
     */
    public Cube(JButton[][] board, int threadsNumber, int size, int passes, int offset) {
        this.passes = passes;
        this.offset = offset;
        this.board = board;
        int offsetLast = 0; //Unless it's the final thread it doesn't affect the rest of the threads.
        slice = board.length / threadsNumber; //The slice each thread gets.
        if (offset+1 == threadsNumber) //The last thread gets his slice + rest of the rows no thread received.
            offsetLast = board.length - slice * (offset) - slice;
        boardPortion = new JButton[slice+offsetLast][size];//The smaller array each thread gets.
        values = new boolean[slice+offsetLast][size];//Boolean array for the neighbour check.
        for (int i = 0 ; i < slice + offsetLast ; i++)
        {
            for (int j = 0 ; j < board.length ; j++)
            {
                boardPortion[i][j] = board[i + slice  *(offset)][j];//Populating the array with the full sized array vals.
            }
        }
    }

    @Override
    /**
     * Checks if a cell is black. If so checks his neighbours if all of them are black.
     * If not changes it's color to white. Repeats as many times as the user requested.
     */
    public void run() {
        for (int i = 0; i < passes; i++) {
            for (int j = 0; j < boardPortion.length; j++) {
                for (int k = 0; k < boardPortion[0].length; k++) {
                    if (boardPortion[j][k].getBackground() == black)
                        checkNeighbours(j, k);//Checking if all the neighbours are black.
                }
            }
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < values[0].length; k++) {
                    if (values[j][k])//If true the cell has white neighbours so the color needs to change.
                        boardPortion[j][k].setBackground(white);
                }
            }
            try {
                Thread.sleep(2000);//Sleep between each pass.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Default neighbour check, checking all 8 possible locations.
     * If there are white neighbours setting the boolean array to true.
     * Later on we update the array following the boolean array.
     * It's done like this to avoid messing the data of the other cells.
     */
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
    //Checks if the given JButton is colored white or black.
    private boolean isWhite(JButton button) {
        return button.getBackground().equals(white);
    }
}
