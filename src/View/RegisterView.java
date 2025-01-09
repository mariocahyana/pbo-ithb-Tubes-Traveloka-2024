package View;

import Controller.RegisterController;
import Model.Model_class.User;
import Model.Model_enum.StatusUser;

import javax.swing.*;
import java.awt.*;

public class RegisterView {
    private JFrame frame;
    private RegisterController registerController;

    public RegisterView() {
        registerController = new RegisterController();
        showRegisterForm();
    }

    public void showRegisterForm() {
        frame = new JFrame("Register");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 102, 204);
                Color color2 = new Color(102, 204, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 25);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 25);
        gradientPanel.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 25);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25);
        gradientPanel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 25);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 25);
        gradientPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 200, 120, 25);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        gradientPanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 200, 200, 25);
        gradientPanel.add(phoneField);

        JButton registerButton = createGradientButton("Register", new Color(0, 153, 204), new Color(51, 204, 255));
        registerButton.setBounds(50, 250, 140, 40);

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Isi Semua field yaa");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "format emailnya ni => mario@microsoft");
                return;
            }

            if (!phone.matches("\\d+")) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Nomer telepon harus angka ya");
                return;
            }

            User user = new User(0, name, password, email, phone, "0", StatusUser.CUSTOMER);
            
            String result = registerController.register(user);

            switch (result) {
                case "SUCCESS":
                    AlertDesignTemplate.showInfoDialog(frame, "Success", "Yey, regis berhasil :)");
                    frame.dispose();
                    new MainMenu();
                    break;
                case "EMAIL_EXISTS":
                    AlertDesignTemplate.showErrorDialog(frame, "Error", "Email udah pernah kedaftar ni, pake email lain dong");
                    break;
                case "PHONE_EXISTS":
                    AlertDesignTemplate.showErrorDialog(frame, "Error", "Nomer udah pernah kedaftar ni, pake nomer lain dong");
                    break;
                default:
                    AlertDesignTemplate.showErrorDialog(frame, "Error", "Yah, regis gagal. Coba lagi aja");
            }
        });

        gradientPanel.add(registerButton);

        JButton backButton = createGradientButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(210, 250, 140, 40);
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        gradientPanel.add(backButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JButton createGradientButton(String text, Color color1, Color color2) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
