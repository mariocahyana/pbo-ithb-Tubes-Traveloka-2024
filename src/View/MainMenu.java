package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu {
    private JFrame frame;

    public MainMenu() {
        showMainMenu();
    }

    public void showMainMenu() {
        frame = new JFrame("Traveloka App Pesawat");
        frame.setSize(450, 500);
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

        JLabel title1 = new JLabel("TRAVEL APP");
        title1.setBounds(0, 50, 450, 50);
        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title1.setFont(new Font("SansSerif", Font.BOLD, 24));
        title1.setForeground(Color.WHITE);
        gradientPanel.add(title1);

        JLabel title2 = new JLabel("PESAWAT");
        title2.setBounds(0, 100, 450, 50);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setFont(new Font("SansSerif", Font.BOLD, 24));
        title2.setForeground(Color.WHITE);
        gradientPanel.add(title2);

        JButton loginButton = createGradientButton("LOGIN", 170, e -> {
            frame.dispose();
            new LoginView();
        });

        JButton registerButton = createGradientButton("REGISTER", 240, e -> {
            frame.dispose();
            new RegisterView();
        });

        JButton exitButton = createGradientButton("EXIT", 310, e -> frame.dispose());

        gradientPanel.add(loginButton);
        gradientPanel.add(registerButton);
        gradientPanel.add(exitButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JButton createGradientButton(String text, int yPosition, ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 153, 204);
                Color color2 = new Color(51, 204, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        button.setBounds(95, yPosition, 260, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(action);
        return button;
    }
}
