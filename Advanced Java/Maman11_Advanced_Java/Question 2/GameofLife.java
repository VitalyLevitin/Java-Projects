import java.security.SecureRandom;

public class GameofLife {
    private static final int arraySize = 10;
    private boolean[][] mainMatrix, secondaryMatrix;
    private static final SecureRandom randomBooleans = new SecureRandom();

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
                int neighbours = neighbourCount(i,j);
                lifeOrDeath(i,j,neighbours);
            }
        }
    }
    public void swap() {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                mainMatrix[i][j] = secondaryMatrix[i][j];
            }
        }
    }

    private int neighbourCount(int x, int y){
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

    private boolean inbounds(int x, int y) {
        return ((x>0 && x<arraySize-1) && (y>0 && y<arraySize-1));
    }

    private void lifeOrDeath(int x, int y, int neighbours){
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
