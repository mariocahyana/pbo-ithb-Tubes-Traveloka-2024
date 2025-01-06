package View;

import Controller.TopUpController;

import javax.swing.*;
import java.awt.*;

public class TopUpBalanceView {
    private JFrame frame;
    private TopUpController topUpController;

    public TopUpBalanceView() {
        topUpController = new TopUpController();
        showTopUpForm();
    }

    public void showTopUpForm() {
        frame = new JFrame("Top Up Balance");
        frame.setSize(400, 330);
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

        JLabel titleLabel = new JLabel("Top Up Balance");
        titleLabel.setBounds(0, 20, 400, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 100, 100, 25);
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        amountLabel.setForeground(Color.WHITE);
        gradientPanel.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 100, 200, 25);
        gradientPanel.add(amountField);

        JButton submitButton = createButton("Submit", new Color(0, 153, 204), new Color(51, 204, 255));
        submitButton.setBounds(53, 170, 140, 40);
        submitButton.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty() || !amountText.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (topUpController.topUpBalance(amount)) {
                JOptionPane.showMessageDialog(frame, "Top Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new ViewBalanceView();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to Top Up. Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gradientPanel.add(submitButton);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(200, 170, 140, 40);
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
