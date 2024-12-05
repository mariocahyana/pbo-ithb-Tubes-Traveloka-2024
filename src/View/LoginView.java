package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class loginView extends JFrame {
    private JComboBox<String> roleSelector;
    private JTextField inputField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public loginView() {
        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Select Role:"));
        roleSelector = new JComboBox<>(new String[]{"Admin", "User"});
        add(roleSelector);

        add(new JLabel("Username/Email:"));
        inputField = new JTextField();
        add(inputField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        add(loginButton);
        add(registerButton);

        registerButton.setVisible(false);

        setLocationRelativeTo(null);
    }

    public String getSelectedRole() {
        return (String) roleSelector.getSelectedItem();
    }

    public String getInput() {
        return inputField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addRoleSelectionListener(ActionListener listener) {
        roleSelector.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void toggleRegisterButton(boolean visible) {
        registerButton.setVisible(visible);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
