import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CubeGUI extends JFrame {
    private myButton [][]btnArray;
    private JPanel center, south, north;
    private JButton start, restart, update;
    private JTextField numOfElementsText, numOfThreadsText, numOfPassesText;
    private int numOfElements, numOfThreads, numOfPasses;

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
        update = new JButton("Update");
        start = new JButton("Start");
        restart = new JButton("Restart");


        //Listeners
        ControlListener controlPanel = new ControlListener();
        MouseListener mousePanel = new MouseListener();

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
        north.add(update);

        //Center Panel.
        center = new JPanel(new GridLayout(numOfElements,numOfElements));
        btnArray = new myButton[numOfElements][numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            for (int j = 0; j < numOfElements; j++) {
                btnArray[i][j] = new myButton(i,j);
                btnArray[i][j].addMouseListener(mousePanel);
                center.add(btnArray[i][j]);
            }
        }


        //South Panel.
        south.add(start);
        south.add(restart);


        //Main Panel.
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);



//            numOfElements = Integer.parseInt(this.numOfElementsText.getText());
//            numOfThreads = Integer.parseInt(this.numOfThreadsText.getText());
//            numOfPasses = Integer.parseInt((this.numOfPassesText.getText()));

    }

    private class MouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX()+ "" + e.getY());
            btnArray[e.getX()][e.getY()].setBackground(Color.black);
        }
    }
    private class ControlListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnArray){
                myButton b = (myButton)e.getSource();
                b.setBackground(Color.black);

            }
        }
    }
}
