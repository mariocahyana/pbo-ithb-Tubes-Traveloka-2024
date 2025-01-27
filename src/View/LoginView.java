package View;

import Controller.LoginController;
import Model.Model_class.User;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    private JFrame frame;
    private LoginController loginController;

    public LoginView() {
        loginController = LoginController.getInstance();
        showLoginForm();
    }

    public void showLoginForm() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
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

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 25);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 25);
        gradientPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 25);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25);
        gradientPanel.add(passwordField);

        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setBounds(220, 130, 140, 40);
        forgotPasswordButton.setBorderPainted(false);
        forgotPasswordButton.setFocusPainted(false);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setOpaque(false);
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.addActionListener(e -> {
            frame.dispose();
            new ForgotPasswordView();
        });
        gradientPanel.add(forgotPasswordButton);

        JButton loginButton = createGradientButton("Login", new Color(0, 153, 204), new Color(51, 204, 255));
        loginButton.setBounds(50, 180, 140, 40);
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Email atau password tolong diisi dulu ya");
                return;
            }

            User user = loginController.login(email, password);
            if (user != null) {
                AlertDesignTemplate.showInfoDialog(frame, "Success", "Yeyy berhasil loginn!!! Hii, " + user.getNama());
                frame.dispose();

                if (user.getStatus().toString().equalsIgnoreCase("ADMIN")) {
                    new AdminMenu();
                } else {
                    new CustomerMenu();
                }
            } else {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Email atau passwordmu ndak bener nih.");
            }
        });
        gradientPanel.add(loginButton);

        JButton backButton = createGradientButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(210, 180, 140, 40);
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
