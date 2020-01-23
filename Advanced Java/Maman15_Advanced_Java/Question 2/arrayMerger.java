import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class arrayMerger implements Runnable {

    private int arraySize, numOfThreads;
    private ArrayList<ArrayList<Integer>> array;

    /**
     * Getting input from the user and populating the array with
     * random values between 0-100.
     */
    public arrayMerger() {
        getInput(); //Output to the user requesting the size and how many threads.
        array = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            Integer random = ThreadLocalRandom.current().nextInt(0, 100); //Random generator.
            ArrayList<Integer> a = new ArrayList<>(1);
            a.add(random);
            array.add(i,a);
        }
    }

    /**
     * Sends x threads (given by the user) to complete the task.
     */
    @Override
    public void run() {
        Controller c = new Controller(array, numOfThreads);
        for (int i = 0; i < numOfThreads; i++)
            (new PrimeThread(c,i,numOfThreads)).start();
    }

    private void getInput() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel numLabel = new JLabel("Please enter the array size: ");
        JLabel threadLabel = new JLabel("Please enter how many threads: ");
        JTextField numText = new JTextField(10);
        JTextField threadText = new JTextField(10);
        boolean notNumber = true;
        panel.add(numLabel);
        panel.add(numText);
        panel.add(threadLabel);
        panel.add(threadText);
        while(notNumber) { //Making sure the input is correct.
            int result = JOptionPane.showConfirmDialog(null,
                    panel, "Array Initializer", JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {
                try {
                    arraySize = Integer.parseInt(numText.getText());
                    numOfThreads = Integer.parseInt(threadText.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Only numbers are allowed", "", JOptionPane.ERROR_MESSAGE);
                }
                if(arraySize>0)
                    notNumber = false;
            } else {//Default values.
                arraySize = 10;
                numOfThreads = 2;
                JOptionPane.showMessageDialog(null,
                        "Default values have been set (size: 10, threads: 2).", "", JOptionPane.INFORMATION_MESSAGE);
                notNumber = false;
            }
        }
    }
}
