import java.security.SecureRandom;

public class GameofLife {
    private static final int arraySize = 10; //As assigned in the question.
    private boolean[][] mainMatrix, secondaryMatrix; //Main == First gen. Secondary == Second gen.
    private static final SecureRandom randomBooleans = new SecureRandom(); //Random as in the book.

    /**
     * Constructor of the matrix, creates two boolean matrixes
     * And randomizes the main matrix. (No need to randomize the second one since
     * it's a place holder.)
     */
    public GameofLife(){
        mainMatrix = new boolean[arraySize][arraySize];
        secondaryMatrix = new boolean[arraySize][arraySize];
        randomize((mainMatrix));
    }

    public static int getArraySize() {
        return arraySize;
    }

    public boolean getCordinates(int x, int y) {
        return mainMatrix[x][y];
    }

    public void randomize(boolean[][] matrix){
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                matrix[i][j] = randomBooleans.nextBoolean();
            }
        }
    }

    public void nextGeneration(){
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                int neighbours = neighbourCount(i,j);//Checking all 8 sides of the cube for live neighbours.
                lifeOrDeath(i,j,neighbours);//Deciding for each cube if it's going to live or die in the next gen.
            }
        }
    }

    /**
     * Places the second gen values into the main one.
     * Happens only after we fully checked the new gen and ready to use it.
     */
    public void swap() {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                mainMatrix[i][j] = secondaryMatrix[i][j];
            }
        }
    }

    private int neighbourCount(int x, int y){//Checking all 8 angles for live neighbours.
        int neighbours = 0;
        if(inbounds(x+1,y)&& mainMatrix[x+1][y])
            neighbours++;
        if(inbounds(x-1,y) && mainMatrix[x-1][y])
            neighbours++;
        if(inbounds(x,y+1)&& mainMatrix[x][y+1])
            neighbours++;
        if(inbounds(x,y-1)&& mainMatrix[x][y-1] )
            neighbours++;
        if(inbounds(x+1,y+1)&& mainMatrix[x+1][y+1])
            neighbours++;
        if(inbounds(x-1,y+1)&& mainMatrix[x-1][y+1])
            neighbours++;
        if(inbounds(x+1,y-1)&& mainMatrix[x+1][y-1])
            neighbours++;
        if(inbounds(x-1,y-1)&& mainMatrix[x-1][y-1])
            neighbours++;
        return neighbours;
    }

    private boolean inbounds(int x, int y) {//Making sure to not check an oob cube.
        return ((x>0 && x<arraySize-1) && (y>0 && y<arraySize-1));
    }

    private void lifeOrDeath(int x, int y, int neighbours){//Following the game rules as assigned.
        if((!mainMatrix[x][y]) && neighbours == 3)
            secondaryMatrix[x][y] = true;
        else if(mainMatrix[x][y] && (neighbours == 0 || neighbours == 1 || neighbours > 3))
            secondaryMatrix[x][y] = false;
        else if(mainMatrix[x][y] && (neighbours == 2 || neighbours == 3))
            secondaryMatrix[x][y] = true;
        else
            secondaryMatrix[x][y] = false;
    }
}
