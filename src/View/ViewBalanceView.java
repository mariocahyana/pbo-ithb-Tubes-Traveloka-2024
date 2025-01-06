package View;

import Controller.TopUpController;
import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class ViewBalanceView {
    private JFrame frame;
    private TopUpController topUpController;

    public ViewBalanceView() {
        topUpController = new TopUpController();
        showBalance();
    }

    public void showBalance() {
        frame = new JFrame("View Balance");
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

        JLabel titleLabel = new JLabel("Your Balance");
        titleLabel.setBounds(0, 20, 400, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        double balance = topUpController.getBalance();

        JLabel balanceLabel = new JLabel("Balance: Rp" + balance);
        balanceLabel.setBounds(50, 100, 300, 25);
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        balanceLabel.setForeground(Color.WHITE);
        gradientPanel.add(balanceLabel);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(130, 180, 140, 40);
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
