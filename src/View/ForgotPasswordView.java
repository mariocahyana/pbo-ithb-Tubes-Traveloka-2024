package View;

import Controller.ForgotPasswordController;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordView {
    public ForgotPasswordView() {
        JFrame frame = new JFrame("Forgot Password");
        frame.setSize(460, 330);
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

        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setBounds(25, 20, 400, 40);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 25);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        emailLabel.setForeground(Color.WHITE);
        gradientPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(185, 80, 200, 25);
        gradientPanel.add(emailField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(50, 130, 150, 25);
        newPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        newPasswordLabel.setForeground(Color.WHITE);
        gradientPanel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(185, 130, 200, 25);
        gradientPanel.add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 180, 150, 25);
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gradientPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(185, 180, 200, 25);
        gradientPanel.add(confirmPasswordField);

        JButton submitButton = createButton("Submit", new Color(0, 153, 204), new Color(51, 204, 255));
        submitButton.setBounds(90, 230, 120, 40);
        submitButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Isi semua fieldnya dulu yaa!");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Password nya ga cocok nihh!");
                return;
            }

            ForgotPasswordController forgotPasswordController = new ForgotPasswordController();
            if (forgotPasswordController.requestPasswordReset(email, newPassword)) {
                AlertDesignTemplate.showInfoDialog(frame, "Success","Uda berhasil request, tunggu yaa :)");
                frame.dispose();
                new LoginView();
            } else {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "request gagal, email ndak ktemu");
            }
        });
        gradientPanel.add(submitButton);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(240, 230, 120, 40);
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });
        gradientPanel.add(backButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color color1, Color color2) {
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
