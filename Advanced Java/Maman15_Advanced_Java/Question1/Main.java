/**
 * This program receives a given 2d array (size determined by the user).
 * Inside the array the user can choose to change the color of cells into black.
 * Once the program starts using the amount of threads and passes the user chose
 * the black cells turn into white if they have a neighbour that's white.
 *
 * @author Vitaly Levitin.
 */
public class Main {
    public static void main(String[] args) {
        new CubeGUI().setVisible(true);
    }
}
