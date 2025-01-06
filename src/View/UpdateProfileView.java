package View;

import Controller.LoginController;
import Controller.UpdateProfileController;

import javax.swing.*;
import java.awt.*;

public class UpdateProfileView {
    private JFrame frame;
    private UpdateProfileController updateProfileController;

    public UpdateProfileView() {
        updateProfileController = new UpdateProfileController();
        showUpdateProfileForm();
    }

    public void showUpdateProfileForm() {
        frame = new JFrame("Update Profile");
        frame.setSize(400, 400);
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

        JLabel titleLabel = new JLabel("Update Profile");
        titleLabel.setBounds(0, 20, 400, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 100, 25);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(nameLabel);

        JTextField nameField = new JTextField(LoginController.getInstance().getLoggedInUser().getNama());
        nameField.setBounds(150, 100, 200, 25);
        gradientPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 25);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(emailLabel);

        JTextField emailField = new JTextField(LoginController.getInstance().getLoggedInUser().getEmail());
        emailField.setBounds(150, 150, 200, 25);
        gradientPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 200, 100, 25);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(phoneLabel);

        JTextField phoneField = new JTextField(LoginController.getInstance().getLoggedInUser().getNoTelp());
        phoneField.setBounds(150, 200, 200, 25);
        gradientPanel.add(phoneField);

        JButton updateButton = createButton("Update", new Color(0, 153, 204), new Color(51, 204, 255));
        updateButton.setBounds(130, 250, 140, 40);
        updateButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid email format. Example mario@microsoft :>", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Phone number must numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updateProfileController.updateProfile(name, email, phone)) {
                JOptionPane.showMessageDialog(frame, "Update Profile Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new CustomerMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update profile. Try again :>.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gradientPanel.add(updateButton);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(130, 300, 140, 40);
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
