package ca.etsmtl.log240.financej;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AppAccountCreating extends JDialog {
    private JTextField usernameField;
    private JTextField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton createButton;

    public AppAccountCreating(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        passwordField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        JLabel roleLabel = new JLabel("Role:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(roleLabel, constraints);

        roleComboBox = new JComboBox<>(new String[]{"Advanced User", "Normal User"});
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(roleComboBox, constraints);

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if all fields are filled
                if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (FinanceJ.xmlTools.checkIfUserExists(usernameField.getText())) {
                    JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (usernameField.getText().equals("admin")) {
                    JOptionPane.showMessageDialog(null, "You can't use this Username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String username = usernameField.getText();
                String password = XMLTools.getMd5(passwordField.getText());
                String role = (String) roleComboBox.getSelectedItem();
                FinanceJ.users.add(new User(username, password, role));
                FinanceJ.xmlTools.writeXMLFile(FinanceJ.users);
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, constraints);

        add(panel);
        pack();
        setTitle("Create Account");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}