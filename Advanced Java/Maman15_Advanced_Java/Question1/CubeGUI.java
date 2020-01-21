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
    ControlListener controlPanel;

    private JLabel ele, thread, pass;
    public CubeGUI(){
        initComponents();
    }

    private void initComponents(){
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
        controlPanel = new ControlListener();
        updateBtn.addActionListener(controlPanel);
        startBtn.addActionListener(controlPanel);
        restartBtn.addActionListener(controlPanel);


        //Listeners
        //MouseListener mousePanel = new MouseListener();

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


//        Center Panel.
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
        for (int i = 0; i < numOfElements; i++) {
            for (int j = 0; j < numOfElements; j++) {
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
            {
                try {
                    numOfElements = Integer.parseInt(numOfElementsText.getText());
                    numOfThreads = Integer.parseInt(numOfThreadsText.getText());
                    numOfPasses = Integer.parseInt(numOfPassesText.getText());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showConfirmDialog(null,"Fields can't be blank.");
                }
                getContentPane().remove(center);
                getContentPane().add(centerPanel(),BorderLayout.CENTER);
                validate();
            }
            else if (e.getSource() == startBtn) {
                ExecutorService ex = Executors.newFixedThreadPool(numOfThreads);
                for (int i = 0; i < numOfThreads; i++) {
                    ex.execute(new Cube(btnArray, numOfThreads, numOfElements, numOfPasses, i));
                }
                ex.shutdown();
            } else if (e.getSource() == restartBtn) {
                getContentPane().remove(center);
                getContentPane().add(centerPanel(),BorderLayout.CENTER);
                validate();
            } else {
                JButton btn = (JButton) e.getSource();
                if (!(btn.getBackground() == Color.black))
                    btn.setBackground(Color.black);
            }
        }
    }
}
