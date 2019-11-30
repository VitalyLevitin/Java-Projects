import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(518, 550);
        View_GameofLife matrix = new View_GameofLife();
        frame.add(matrix);
        frame.setVisible(true);
        matrix.sprout();
    }

}
