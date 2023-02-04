package ca.etsmtl.log240.financej;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.*;

//Login JFrame class for the FinanceJ application
public class Login extends javax.swing.JFrame {
    //define variables
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel title;
    private JFrame frame;


    public static void main(String[] args) {
        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    public Login() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("FinanceJ Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("FinanceJ");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        usernameLabel = new JLabel("Nom d'utilisateur : ");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Mot de passe : ");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Connexion");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                //create variable passHash in md5 via password
                String passHash = XMLTools.getMd5(password);
                //check in FinanceJ.users if username and passHash are correct
                for (User user : FinanceJ.users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(passHash)) {
                        frame.dispose();
                        new FinanceJ(username).setVisible(true);
                        System.out.println("Login successful");
                        return;
                    }
                }
                if (username.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (username.equals("admin") && passHash.equals("21232f297a57a5a743894a0e4a801fc3")) {
                    frame.dispose();
                    new FinanceJ(username).setVisible(true);
                    System.out.println("Login successful");
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Nom de'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);

            }
        });

        // Ajouter des composants au panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        // Centrer la fenêtre sur l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }

}
