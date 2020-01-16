

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookGUI extends JFrame {
    private JTable table;
    private JScrollPane pane;
    private JButton addBtn, removeBtn, updateBtn, searchBtn, loadBtn, saveBtn;
    private JTextField nameText, phoneText;
    private JLabel nameString, phoneString;
    private Phonebook phonebook;
    private TableRowSorter<TableModel> sorter;

    public PhonebookGUI() {
        initComponents();
    }

    private void initComponents() {
        final DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new JTable(model);
        pane = new JScrollPane();
        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        searchBtn = new JButton("Search");
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        JPanel south = new JPanel();
        JPanel north = new JPanel(new GridBagLayout());
        nameText = new JTextField(15);
        phoneText = new JTextField(15);
        nameString = new JLabel("Name:");
        phonebook = new Phonebook();

        //Initialize JTable.
        model.addColumn("Name");
        model.addColumn("Number");
        pane.setViewportView(table);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().add(pane, BorderLayout.CENTER);

        //Sorting the JTable keys.
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        //North.
        north.add(nameString);
        north.add(nameText);
        phoneString = new JLabel("Phone:");
        north.add(phoneString);
        north.add(phoneText);
        north.add(updateBtn);
        north.add(addBtn);
        north.add(removeBtn);
        north.add(searchBtn);
        getContentPane().add(north, BorderLayout.NORTH);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String phone = phoneText.getText();
                try {
                    Contact contact = new Contact(name, phone);
                    boolean action = phonebook.addContact(contact);
                    if (action) {
                        model.addRow(new Object[]{name, phone});
                        model.fireTableDataChanged();
                        nameText.setText(null);
                        phoneText.setText(null);
                    }
                } catch (IlegalPhoneNumberException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (AlreadyExistsContactException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String phone = phoneText.getText();
                try {
                    Contact contact = new Contact(name, phone);
                    int indexToRemove = removeRow(name);
                    phonebook.removeContact(contact);
                    if (indexToRemove != -1)
                        model.removeRow(indexToRemove);
                    model.fireTableDataChanged();
                } catch (NumberFormatException | DoesNotExistContactException | IlegalPhoneNumberException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldName = table.getValueAt(table.getSelectedRow(), 0).toString();
                String name = nameText.getText();
                String phone = phoneText.getText();
                try {
                    Contact contact = new Contact(name, phone);
                    if (phonebook.updateContact(contact, oldName)) {
                        table.setValueAt(name, table.getSelectedRow(), 0);
                        table.setValueAt(phone, table.getSelectedRow(), 1);
                        model.fireTableDataChanged();
                    }
                } catch (DoesNotExistContactException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IlegalPhoneNumberException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (AlreadyExistsContactException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String phone = phoneText.getText();
                try {
                    Contact contact = new Contact(name, phone);
                    boolean status = phonebook.searchContact(contact);
                    if (status) {
                        for (int i = 0; i < phonebook.size(); i++) {
                            if (name.compareTo(table.getValueAt(i, 0).toString()) == 0) {
                                table.setRowSelectionInterval(i, i);
                                break;
                            }
                        }
                    }
                } catch (DoesNotExistContactException | IlegalPhoneNumberException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() < 0) {
                    nameText.setText(null);
                    phoneText.setText(null);
                } else {
                    nameText.setText(phonebook.myMap().get(table.getValueAt(table.getSelectedRow(), 0).toString()).getName());
                    phoneText.setText(phonebook.myMap().get(table.getValueAt(table.getSelectedRow(), 0).toString()).getNumber());
                }
            }
        });

        //Bottom part.
        loadBtn.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  File workingDirectory = new File(System.getProperty("user.dir"));
                  JFileChooser fc = new JFileChooser(workingDirectory);
                  fc.showOpenDialog(null);
                  File loadFile = fc.getSelectedFile();
                  try {
                      Scanner sc = new Scanner(loadFile);
                      phonebook.removePhonebook();
                      while (table.getRowCount() != 0) {
                          model.removeRow(0);
                          model.fireTableDataChanged();
                      }
                      while (sc.hasNext()) {
                          String[] a;
                          a = sc.nextLine().split(",");
                          String name = a[0].trim();
                          String phone = a[1].trim();
                          Contact contact = new Contact(name, phone);
                          phonebook.addContact(contact);
                          model.addRow(new Object[]{name, phone});
                      }
                  }catch (IOException ex) {
                      JOptionPane.showMessageDialog(null, ex.getMessage());
                  }catch(NullPointerException ex){
                      JOptionPane.showMessageDialog(null, "Cancel");
                  } catch (AlreadyExistsContactException ex) {
                      JOptionPane.showMessageDialog(null, ex.getMessage());
                  } catch (IlegalPhoneNumberException ex) {
                      JOptionPane.showMessageDialog(null, ex.getMessage());
                  }
              }
          });
        saveBtn.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  try {
                      File workingDirectory = new File(System.getProperty("user.dir"));
                      JFileChooser fc = new JFileChooser(workingDirectory);
                      fc.showOpenDialog(null);
                      File loadFile = fc.getSelectedFile();
                      FileWriter writer = new FileWriter(loadFile);
                      for (int i = 0; i < phonebook.size(); i++) {
                          writer.write(table.getValueAt(i, 0).toString() + "," + table.getValueAt(i, 1).toString() + "\n");
                      }
                      writer.close();
                  } catch (IOException ex) {
                      JOptionPane.showMessageDialog(null, ex.getMessage());
                  }
              }
          });
        south.setLayout(new GridLayout());
        south.add(loadBtn);
        south.add(saveBtn);

        getContentPane().add(south, BorderLayout.PAGE_END);
        pack();
    }

    private int removeRow(String name) {
        for (int i = 0; i < phonebook.size(); i++) {
            if (name.compareTo(table.getValueAt(i, 0).toString()) == 0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
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
