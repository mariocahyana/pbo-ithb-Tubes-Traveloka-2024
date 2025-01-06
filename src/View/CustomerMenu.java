package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class CustomerMenu {
    private JFrame frame;

    public CustomerMenu() {
        showMenu();
    }

    public void showMenu() {
        var user = LoginController.getInstance().getLoggedInUser();

        if (user == null) {
            JOptionPane.showMessageDialog(null, "No user is logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            new MainMenu();
            return;
        }

        frame = new JFrame("Customer Menu");
        frame.setSize(600, 500);
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

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getNama() + "!");
        welcomeLabel.setBounds(0, 20, 600, 50);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(welcomeLabel);

        JButton writeReviewButton = createButton("Write a Review", new Color(0, 153, 204), new Color(51, 204, 255));
        writeReviewButton.setBounds(180, 100, 240, 50);
        writeReviewButton.addActionListener(e -> {
            frame.dispose();
            new WriteReviewView();
        });
        gradientPanel.add(writeReviewButton);

        JButton viewReviewsButton = createButton("View Reviews", new Color(0, 153, 204), new Color(51, 204, 255));
        viewReviewsButton.setBounds(180, 160, 240, 50);
        viewReviewsButton.addActionListener(e -> {
            frame.dispose();
            new ViewReviewsView();
        });
        gradientPanel.add(viewReviewsButton);

        JButton topUpBalanceButton = createButton("Top Up Balance", new Color(0, 153, 204), new Color(51, 204, 255));
        topUpBalanceButton.setBounds(180, 220, 240, 50);
        topUpBalanceButton.addActionListener(e -> {
            frame.dispose();
            new TopUpBalanceView();
        });
        gradientPanel.add(topUpBalanceButton);

        JButton viewBalanceButton = createButton("View Balance", new Color(0, 153, 204), new Color(51, 204, 255));
        viewBalanceButton.setBounds(180, 280, 240, 50);
        viewBalanceButton.addActionListener(e -> {
            frame.dispose();
            new ViewBalanceView();
        });
        gradientPanel.add(viewBalanceButton);

        JButton logoutButton = createButton("Logout", new Color(0, 153, 204), new Color(51, 204, 255));
        logoutButton.setBounds(180, 340, 240, 50);
        logoutButton.addActionListener(e -> {
            LoginController.getInstance().logout();
            frame.dispose();
            new MainMenu();
        });
        gradientPanel.add(logoutButton);

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
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
