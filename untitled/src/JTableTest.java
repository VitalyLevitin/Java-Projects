import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A sample application showing a JTable.
 *
 * @author Zachary Seguin
 * @version 1.0 (6/6/2012)
 */
public class JTableTest extends JFrame
{
    /**
     * This title label.
     *
     * @since 1.0
     */
    private JLabel lblTitle;

    /**
     * Scroll pane to hold the JTable, so a scroll bar is shown when the data exceeds the visible size.
     *
     * @since 1.0
     */
    private JScrollPane scrollPane;
    /**
     * Self explanatory...
     */
    private JTable jTable;

    /**
     * Manages the layout of the application.
     *
     * @since 1.0
     */
    private SpringLayout layout;

    /**
     * Contructs and initalizes the GUI interface.
     *
     * @since 1.0
     */
    public JTableTest()
    {
        //SETUP JTABLE
        String [] columnNames = {"ID", "First Name", "Last Name", "Email Address"};
        String [][] data = {
                { "1", "Rambotimous", "Prime", "rprime@ramferno.org" },
                { "2", "John", "Doe", "jdoe@me.com" },
                { "3", "Peneloppe", "Garcia", "pgarcia@fbi.gov"},
                { "4", "Chloe", "O'Brian", "cobrian@ctu.gov"},
                { "5", "Spencer", "Reid", "sreid@fbi.gov"},
                { "6", "Jack", "Bauer", "jbauer@ctu.gov"}
        };

        this.jTable = new JTable(data, columnNames);

        this.scrollPane = new JScrollPane(this.jTable);

        //SETUP TITLE
        this.lblTitle = new JLabel("JTable Test");
        this.lblTitle.setFont(new Font("sansserif", Font.BOLD, 24));

        //SET THE LAYOUT MANAGER
        this.layout = new SpringLayout();
        this.getContentPane().setLayout(this.layout);

        //ADD ELEMENTS TO SCREEN
        this.add(this.lblTitle);
        this.add(this.scrollPane);

        //PLACE THE ELEMENTS
        this.layout.putConstraint(SpringLayout.NORTH, this.lblTitle, 10, SpringLayout.NORTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.WEST, this.lblTitle, 10, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.scrollPane, 50, SpringLayout.NORTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.WEST, this.scrollPane, 10, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, this.scrollPane, -10, SpringLayout.EAST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.SOUTH, this.scrollPane, -10, SpringLayout.SOUTH, this.getContentPane());

        //SETUP THE JFRAME
        this.setTitle("JTable Test");
        this.setSize(600, 400);
        this.setMinimumSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }//End of constructor method

    public static void main (String [] args)
    {
        new JTableTest();
    }//End of main method
}//End of class