import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CubeGUI extends JFrame {
    private JButton [][]btnArray;
    private JPanel center, south, north, tabs;
    private JButton startBtn, restartBtn, updateBtn;
    private JTextField numOfElementsText, numOfThreadsText, numOfPassesText;
    private int numOfElements, numOfThreads, numOfPasses;
    private ControlListener controlPanel;

    private JLabel ele, thread, pass;
    public CubeGUI(){
        initComponents();
    }

    private void initComponents(){
        //Setting the JFrame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        south = new JPanel();
        north = new JPanel(new GridBagLayout());
        numOfElementsText = new JTextField(5);
        numOfThreadsText = new JTextField(5);
        numOfPassesText = new JTextField(5);
        ele = new JLabel(" Please enter the grid size: ");
        thread = new JLabel(" Please enter how many threads: ");
        pass = new JLabel(" Please enter how much passes: ");
        updateBtn = new JButton("Update");
        startBtn = new JButton("Start");
        restartBtn = new JButton("Restart");

        //Listeners
        controlPanel = new ControlListener();
        updateBtn.addActionListener(controlPanel);
        startBtn.addActionListener(controlPanel);
        restartBtn.addActionListener(controlPanel);

        //Default values
        numOfElementsText.setText(Integer.toString(numOfElements = 20));
        numOfThreadsText.setText(Integer.toString(numOfThreads = 2));
        numOfPassesText.setText(Integer.toString(numOfPasses = 2));

        //North Panel.
        north.add(ele);
        north.add(numOfElementsText);
        north.add(thread);
        north.add(numOfThreadsText);
        north.add(pass);
        north.add(numOfPassesText);
        north.add(updateBtn);

        //Center Panel.
        centerPanel();

        //South Panel.
        south.add(startBtn);
        south.add(restartBtn);

        //Main Panel.
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
    }

    private JPanel centerPanel() {
        center = new JPanel(new GridLayout(numOfElements,numOfElements));
        btnArray = new JButton[numOfElements][numOfElements];
        for (int i = 0; i < numOfElements; i++) {//Creating a 2d JButton array to act as cells.
            for (int j = 0; j < numOfElements; j++) {//Giving every button an action listener and default vals.
                btnArray[i][j] = new JButton();
                btnArray[i][j].addActionListener(controlPanel);
                btnArray[i][j].setBackground(Color.white);
                center.add(btnArray[i][j]);
            }
        }
        return center;
    }

    private class ControlListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == updateBtn)
            {//Once the new value is set we remove the old panel and add a new one with current numbers.
                try {
                    numOfElements = Integer.parseInt(numOfElementsText.getText());
                    numOfThreads = Integer.parseInt(numOfThreadsText.getText());
                    numOfPasses = Integer.parseInt(numOfPassesText.getText());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,
                            "Fields can't be blank nor not Integers", "", JOptionPane.ERROR_MESSAGE);
                }
                getContentPane().remove(center);
                getContentPane().add(centerPanel(),BorderLayout.CENTER);
                validate();//Refresh the changed panel.
            }

            else if (e.getSource() == startBtn) {
                ExecutorService ex = Executors.newFixedThreadPool(numOfThreads);//Setting how many threads should run.
                for (int i = 0; i < numOfThreads; i++) {
                    ex.execute(new Cube(btnArray, numOfThreads, numOfElements, numOfPasses, i));
                }
                ex.shutdown();

            } else if (e.getSource() == restartBtn) {//Similar to update just staying with same values.
                getContentPane().remove(center);
                getContentPane().add(centerPanel(),BorderLayout.CENTER);
                validate();

            } else {//If an event happened and it was neither of the options, it means the user clicked a JButton.
                JButton btn = (JButton) e.getSource();
                if (!(btn.getBackground() == Color.black))
                    btn.setBackground(Color.black);
            }
        }
    }
}
