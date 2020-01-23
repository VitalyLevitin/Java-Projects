import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class arrayMerger implements Runnable {

    private int arraySize, numOfThreads;
    private ArrayList<ArrayList<Integer>> array;

    public arrayMerger() {
        getInput();
        array = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            Integer random = ThreadLocalRandom.current().nextInt(0, 100);
            ArrayList<Integer> a = new ArrayList<>(1);
            a.add(random);
            array.add(i,a);
        }
    }

    @Override
    public void run() {
        Controller c = new Controller(array, numOfThreads);
        for (int i = 0; i < numOfThreads; i++)
            (new PrimeThread(c,i,numOfThreads)).start();
//        c.result();
    }

    private void getInput() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel numLabel = new JLabel("Please enter the array size: ");
        JLabel threadLabel = new JLabel("Please enter how many threads: ");
        JTextField numText = new JTextField(10);
        JTextField threadText = new JTextField(10);
        panel.add(numLabel);
        panel.add(numText);
        panel.add(threadLabel);
        panel.add(threadText);
        int result = JOptionPane.showConfirmDialog(null,
                panel, "Array Initializer", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            try {
                arraySize = Integer.parseInt(numText.getText());
                numOfThreads = Integer.parseInt(threadText.getText());
            } catch (NumberFormatException e) {
            }
        } else {
            arraySize = 10;
            numOfThreads = 2;
            JOptionPane.showConfirmDialog(null,
                    "Default values have been set (size: 10, threads: 2).", "", JOptionPane.OK_OPTION);
        }

    }
}
