package View;

import Model.Model_class.User;

import javax.swing.*;
import java.awt.*;

public class AdminMenu {
    private JFrame frame;
    private User user;

    public AdminMenu(User user) {
        this.user = user;
        showAdminMenu();
    }

    public void showAdminMenu() {
        frame = new JFrame("Admin Menu");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel welcomeLabel = new JLabel("Welcome, Admin " + user.getNama() + "!");
        welcomeLabel.setBounds(0, 20, 600, 50);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(220, 200, 160, 50);
        logoutButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        gradientPanel.add(logoutButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
