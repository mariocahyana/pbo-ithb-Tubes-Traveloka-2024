package View;

import Controller.AdminFeedbackController;
import Controller.LoginController;
import Model.Model_class.Feedback;
import Model.Model_class.Reply;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class AdminFeedbackView {
    private JFrame frame;
    private AdminFeedbackController adminFeedbackController;
    private Color primaryColor = new Color(0, 102, 204);
    private Color secondaryColor = new Color(102, 204, 255);

    public AdminFeedbackView() {
        adminFeedbackController = new AdminFeedbackController();
        showFeedbacks();
    }

    public void showFeedbacks() {
        frame = new JFrame("VIEW FEEDBACKS");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, primaryColor, getWidth(), getHeight(), secondaryColor);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Feedback Admin");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        List<Feedback> feedbackList = adminFeedbackController.getAllFeedbacks();
        if (feedbackList.isEmpty()) {
            JLabel noFeedbackLabel = createStyledLabel("No feedback from Customer", 18);
            contentPanel.add(noFeedbackLabel);
        } else {
            for (Feedback feedback : feedbackList) {
                JPanel feedbackPanel = createFeedbackPanel(feedback);
                contentPanel.add(feedbackPanel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(255, 255, 255, 100);
                this.trackColor = new Color(0, 0, 0, 0);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JButton backButton = createButton("BACK", 0, e -> {
            frame.dispose();
            new AdminMenu();
        }, true);
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font(backButton.getFont().getName(), backButton.getFont().getStyle(), 18));
        footerPanel.add(backButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createFeedbackPanel(Feedback feedback) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 10));
        panel.setMaximumSize(new Dimension(750, 150));
        panel.setPreferredSize(new Dimension(750, 150));
        panel.setBorder(new CompoundBorder(
            new LineBorder(new Color(255, 255, 255, 100), 1, true),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.setBackground(new Color(255, 255, 255, 230));

        JPanel userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(feedback.getUser().getNama());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(primaryColor);

        userInfoPanel.add(nameLabel, BorderLayout.NORTH);

        JTextArea feedbackText = new JTextArea(feedback.getCustomer_review());
        feedbackText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        feedbackText.setLineWrap(true);
        feedbackText.setWrapStyleWord(true);
        feedbackText.setEditable(false);
        feedbackText.setOpaque(false);
        feedbackText.setBorder(null);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton replyButton = createButton("Reply", 0, e -> showReplyDialog(feedback), true);
        buttonPanel.add(replyButton);

        buttonPanel.setPreferredSize(new Dimension(100, 50));
        panel.add(userInfoPanel, BorderLayout.NORTH);
        panel.add(feedbackText, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void showReplyDialog(Feedback feedback) {
        JDialog dialog = new JDialog(frame, "Reply to Feedback", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel dialogPanel = new JPanel(new BorderLayout(10, 10));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea replyArea = new JTextArea();
        replyArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        replyArea.setLineWrap(true);
        replyArea.setWrapStyleWord(true);
        replyArea.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JScrollPane replyScroll = new JScrollPane(replyArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton sendButton = createButton("Send Reply", 0, e -> {
            String replyText = replyArea.getText().trim();
            if (!replyText.isEmpty()) {
                Reply reply = new Reply(
                    Integer.parseInt(feedback.getFeedbackID()),
                    LoginController.getInstance().getLoggedInUser().getUserID(),
                    replyText
                );

                if (adminFeedbackController.isReplyAlreadyExists(reply.getReviewID(), reply.getUserID())) {
                    AlertDesignTemplate.showErrorDialog(frame, "Warning", "Sudah pernah mereply feedback ini :)");
                    return;
                }

                if (adminFeedbackController.replyToFeedback(reply)) {
                    AlertDesignTemplate.showInfoDialog(frame, "Success", "Yeyy, reply berhasil!");
                    dialog.dispose();
                } else {
                    AlertDesignTemplate.showErrorDialog(frame, "Error", "Yahh, gagal ngereply :(");
                }
            }
        }, true);

        JButton cancelButton = createButton("Cancel", 0, e -> dialog.dispose(), true);

        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);

        dialogPanel.add(new JLabel("Your Reply:"), BorderLayout.NORTH);
        dialogPanel.add(replyScroll, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }

    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createButton(String text, int yPosition, ActionListener action, boolean isRounded) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 153, 204);
                Color color2 = new Color(51, 204, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                if (isRounded) {
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                } else {
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);

        return button;
    }
}
