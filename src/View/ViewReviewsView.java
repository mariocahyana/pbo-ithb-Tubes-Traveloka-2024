package View;

import Controller.ViewReviewsController;
import javax.swing.*;
import javax.swing.border.*;
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
        frame.setSize(600, 530);
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

        JPanel reviewsContainer = new JPanel();
        reviewsContainer.setLayout(new BoxLayout(reviewsContainer, BoxLayout.Y_AXIS));
        reviewsContainer.setOpaque(false);

        List<String> reviews = viewReviewsController.getAllReviews();
        if (reviews.isEmpty()) {
            JLabel emptyLabel = new JLabel("Belum ada review yang tersedia :(");
            emptyLabel.setForeground(Color.WHITE);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            reviewsContainer.add(emptyLabel);
        } else {
            for (String review : reviews) {
                reviewsContainer.add(createMessagePanel(review));
                reviewsContainer.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scrollPane = new JScrollPane(reviewsContainer);
        scrollPane.setBounds(50, 100, 500, 300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        gradientPanel.add(scrollPane);

        JButton backButton = createButton("BACK", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(180, 420, 240, 50);
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu();
        });
        gradientPanel.add(backButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JPanel createMessagePanel(String message) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setMaximumSize(new Dimension(480, 150));
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(224, 224, 224), 1, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        String[] parts = message.split("\n  Admin:");
        String customerPart = parts[0];
        String adminReply = parts.length > 1 ? parts[1].trim() : null;

        String customerName = customerPart.split(":")[0];
        String customerReview = customerPart.split(":", 2)[1].trim();

        JLabel nameLabel = new JLabel(customerName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        nameLabel.setForeground(new Color(33, 33, 33));

        JLabel reviewLabel = new JLabel("<html><p style='width:400px;'>" + customerReview + "</p></html>");
        reviewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        reviewLabel.setForeground(Color.DARK_GRAY);

        messagePanel.add(nameLabel);
        messagePanel.add(Box.createVerticalStrut(5));
        messagePanel.add(reviewLabel);

        if (adminReply != null && !adminReply.equals("(Belum ada balasan dari admin, sabar ya adminnya lagi healing)")) {
            JLabel adminLabel = new JLabel("Balasan Admin:");
            adminLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            adminLabel.setForeground(new Color(51, 153, 255));

            JLabel replyLabel = new JLabel("<html><p style='width:400px;'>" + adminReply.replaceAll("\n", "<br>") + "</p></html>");
            replyLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            replyLabel.setForeground(new Color(51, 153, 255));

            messagePanel.add(Box.createVerticalStrut(8));
            messagePanel.add(adminLabel);
            messagePanel.add(replyLabel);
        }

        return messagePanel;
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
