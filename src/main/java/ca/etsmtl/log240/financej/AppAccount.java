package ca.etsmtl.log240.financej;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppAccount extends JDialog {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private JButton createButton;
    private JButton deleteButton;
    private ArrayList<User> users = FinanceJ.users;
    private AppAccountCreating AccountCreatingDialog;

    public UserTableModel tbModel;

    public AppAccount(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        //create a frame for the user management
        final JFrame frame = new JFrame("User Management");
        // Set up the frame
        setTitle("FinanceJ - User Management");
        setSize(800, 300);

        // Create the table
        tbModel = new UserTableModel(users);
        table = new JTable(tbModel);
        JScrollPane scrollPane = new JScrollPane(table);


        // Create the buttons
        createButton = new JButton("Create User");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountCreatingDialog = new AppAccountCreating(frame, true);
                AccountCreatingDialog.setVisible(true);
                reloadTable();

            }
        });
        deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    users.remove(selectedRow);
                    FinanceJ.xmlTools.writeXMLFile(FinanceJ.users);
                    reloadTable();
                }
            }
        });

        // Add the components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);

        // Center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    public void reloadTable() {
        tbModel.fireTableDataChanged();

    }
    public void addUser(String username, String password, String role) {
        users.add(new User(username, password, role));

    }

    //Fuction for write users list in a file xml format
    public void writeUsers(){
        FinanceJ.xmlTools.writeXMLFile(users);
    }
}