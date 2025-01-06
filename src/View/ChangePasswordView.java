package View;

import Controller.ChangePasswordController;
import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordView {
    private JFrame frame;
    private ChangePasswordController changePasswordController;

    public ChangePasswordView() {
        changePasswordController = new ChangePasswordController();
        showChangePasswordForm();
    }

    public void showChangePasswordForm() {
        frame = new JFrame("Change Password");
        frame.setSize(460, 400);
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

        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setBounds(30, 20, 400, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setBounds(50, 100, 210, 25);
        oldPasswordLabel.setForeground(Color.WHITE);
        oldPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(oldPasswordLabel);

        JPasswordField oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(190, 100, 200, 25);
        gradientPanel.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(50, 150, 210, 25);
        newPasswordLabel.setForeground(Color.WHITE);
        newPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(190, 150, 200, 25);
        gradientPanel.add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 200, 210, 25);
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(190, 200, 200, 25);
        gradientPanel.add(confirmPasswordField);

        JButton changePasswordButton = createButton("Change My Pass", new Color(0, 153, 204), new Color(51, 204, 255));
        changePasswordButton.setBounds(135, 250, 170, 40);
        changePasswordButton.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword()).trim();
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int userId = LoginController.getInstance().getLoggedInUser().getUserID();
            if (changePasswordController.changePassword(userId, oldPassword, newPassword)) {
                JOptionPane.showMessageDialog(frame, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new CustomerMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed. Input your correct password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gradientPanel.add(changePasswordButton);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(135, 300, 170, 40);
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu();
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
