package View;

import javax.swing.*;
import java.awt.*;

public class AlertDesignTemplate {
    private static final Color color1 = new Color(41, 128, 185);
    private static final Color color2 = new Color(236, 240, 241);
    private static final Color color3 = new Color(52, 152, 219);
    
    private static final Color suksesColor = new Color(46, 204, 113);
    private static final Color cancelColor = new Color(231, 76, 60);
    
    private static final Color teks1 = new Color(44, 62, 80);
    private static final Color teks2 = new Color(255, 255, 255);

    private static final Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    private static final Font messageFont = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font buttonFont = new Font("SansSerif", Font.PLAIN, 13);

    public static void showInfoDialog(JFrame parentFrame, String title, String message) {
        showDialog(parentFrame, title, message, "INFO");
    }

    public static void showErrorDialog(JFrame parentFrame, String title, String message) {
        showDialog(parentFrame, title, message, "ERROR");
    }

    public static int showConfirmDialog(JFrame parentFrame, String title, String message) {
        return showKonfirmDialog(parentFrame, title, message);
    }

    private static void showDialog(JFrame parentFrame, String title, String message, String type) {
        JDialog dialog = new JDialog(parentFrame, title, true);
        dialog.setSize(350, 190);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBackground(color2);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color1, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), 0, color3);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(400, 50));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(teks2);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        messageLabel.setFont(messageFont);
        messageLabel.setForeground(teks1);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = createStyledButton("OK", suksesColor);
        okButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(messageLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private static int showKonfirmDialog(JFrame parentFrame, String title, String message) {
        final int[] result = {JOptionPane.NO_OPTION};
        JDialog dialog = new JDialog(parentFrame, title, true);
        dialog.setSize(350, 180);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBackground(color2);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color1, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), 0, color3);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(400, 50));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(teks2);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        messageLabel.setFont(messageFont);
        messageLabel.setForeground(teks1);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton yesButton = createStyledButton("YES", suksesColor);
        JButton noButton = createStyledButton("NO", cancelColor);

        yesButton.addActionListener(e -> {
            result[0] = JOptionPane.YES_OPTION;
            dialog.dispose();
        });
        noButton.addActionListener(e -> {
            result[0] = JOptionPane.NO_OPTION;
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(messageLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);

        return result[0];
    }

    private static JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(baseColor.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(baseColor.brighter());
                } else {
                    g2d.setColor(baseColor);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
        };
        
        button.setForeground(teks2);
        button.setFont(buttonFont);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 35));
        
        return button;
    }
}