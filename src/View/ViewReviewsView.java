package View;

import Controller.ViewReviewsController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewReviewsView {
    private JFrame frame;
    private ViewReviewsController viewReviewsController;

    public ViewReviewsView() {
        viewReviewsController = new ViewReviewsController();
        showReviews();
    }

    public void showReviews() {
        frame = new JFrame("View Reviews");
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

        JLabel titleLabel = new JLabel("View Reviews");
        titleLabel.setBounds(0, 20, 600, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(titleLabel);

        JTextArea reviewsArea = new JTextArea();
        reviewsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reviewsArea);
        scrollPane.setBounds(50, 100, 500, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gradientPanel.add(scrollPane);

        List<String> reviews = viewReviewsController.getAllReviews();
        if (reviews.isEmpty()) {
            reviewsArea.setText("No reviews available.");
        } else {
            reviewsArea.setText(String.join("\n", reviews));
        }

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
