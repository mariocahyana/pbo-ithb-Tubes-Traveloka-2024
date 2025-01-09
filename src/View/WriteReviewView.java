package View;

import Controller.WriteReviewController;

import javax.swing.*;
import java.awt.*;

public class WriteReviewView {
    private JFrame frame;
    private WriteReviewController writeReviewController;

    public WriteReviewView() {
        writeReviewController = new WriteReviewController();
        showWriteReviewForm();
    }

    public void showWriteReviewForm() {
        frame = new JFrame("Write a Review");
        frame.setSize(600, 500);
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

        JLabel titleLabel = new JLabel("Write a Review");
        titleLabel.setBounds(0, 20, 600, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JLabel reviewLabel = new JLabel("Your Review:");
        reviewLabel.setBounds(50, 100, 150, 30);
        reviewLabel.setForeground(Color.WHITE);
        reviewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gradientPanel.add(reviewLabel);

        JTextArea reviewArea = new JTextArea();
        reviewArea.setBounds(50, 140, 500, 100);
        gradientPanel.add(reviewArea);

        JButton submitButton = createButton("Submit", new Color(0, 153, 204), new Color(51, 204, 255));
        submitButton.setBounds(180, 260, 240, 50);
        submitButton.addActionListener(e -> {
            String review = reviewArea.getText().trim();
            if (review.isEmpty()) {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Review jangan kosong dongg");
            } else if (writeReviewController.saveReview(review)) {
                AlertDesignTemplate.showInfoDialog(frame, "Success", "Yeyy, review udah kesimpen nihh");
                frame.dispose();
                new CustomerMenu();
            } else {
                AlertDesignTemplate.showErrorDialog(frame, "Error", "Yahh, gagal nyimpen review mu");
            }            
        });
        gradientPanel.add(submitButton);

        JButton backButton = createButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(180, 320, 240, 50);
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
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
