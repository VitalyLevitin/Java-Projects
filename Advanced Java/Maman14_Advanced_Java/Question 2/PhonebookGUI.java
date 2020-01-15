

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PhonebookGUI extends JFrame implements Serializable {
    private JTable table;
    private JScrollPane pane;
    private JButton addBtn, removeBtn, updateBtn, loadBtn, saveBtn;
    private JTextField nameText, phoneText;
    private JLabel nameString, phoneString;
    private Phonebook phonebook;
    private TableRowSorter<TableModel> sorter;

    public PhonebookGUI() {
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        pane = new JScrollPane();
        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        JPanel south = new JPanel();
        JPanel north = new JPanel(new GridBagLayout());
        nameText = new JTextField(15);
        phoneText = new JTextField(15);
        nameString = new JLabel("Name:");
        phonebook = new Phonebook();

        //North.
        north.add(nameString);
        north.add(nameText);
        phoneString = new JLabel("Phone:");
        north.add(phoneString);
        north.add(phoneText);
        north.add(updateBtn);
        north.add(addBtn);
        north.add(removeBtn);
        getContentPane().add(north,BorderLayout.NORTH);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String phone = phoneText.getText();
                Contact contact = new Contact(name, phone);
                try{
                    boolean action = phonebook.addContact(contact);
                    if(action) {
                        model.addRow(new Object[]{name, phone});
                        model.fireTableDataChanged();
                    }
                }
                catch(Exception err) {
                    System.out.println("Fuck");
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                nameText.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                phoneText.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
                sorter.sort();

            }
        });

        //Initialize JTable.
        model.addColumn("Name");
        model.addColumn("Number");
        pane.setViewportView(table);
        getContentPane().add(pane,BorderLayout.CENTER);

        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
//        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);


        //Bottom part.
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        south.setLayout(new GridLayout());
        south.add(loadBtn);
        south.add(saveBtn);
        getContentPane().add(south,BorderLayout.PAGE_END);
        pack();

    }

    public static void main(String[] args) {
        try{
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new PhonebookGUI().setVisible(true);
    }
}
