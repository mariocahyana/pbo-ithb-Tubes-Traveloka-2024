package View;

import Controller.RescheduleController;

import javax.swing.*;
import java.awt.*;

public class RescheduleView {
    private JFrame frame;
    private RescheduleController rescheduleController;

    public RescheduleView() {
        rescheduleController = new RescheduleController();
        showRescheduleForm();
    }

    public void showRescheduleForm() {
        frame = new JFrame("Request Reschedule");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = createGradientPanel();
        gradientPanel.setLayout(null);

        JLabel titleLabel = createStyledLabel("Request Reschedule", 24);
        titleLabel.setBounds(110, 20, 500, 40);
        gradientPanel.add(titleLabel);

        JLabel oldTransLabel = createStyledLabel("Old Transaction ID:", 14);
        oldTransLabel.setBounds(50, 80, 150, 25);
        gradientPanel.add(oldTransLabel);

        JTextField oldTransField = new JTextField();
        oldTransField.setBounds(220, 80, 200, 25);
        gradientPanel.add(oldTransField);

        JLabel newTransLabel = createStyledLabel("New Flight ID:", 14);
        newTransLabel.setBounds(50, 130, 150, 25);
        gradientPanel.add(newTransLabel);

        JTextField newTransField = new JTextField();
        newTransField.setBounds(220, 130, 200, 25);
        gradientPanel.add(newTransField);

        JLabel reasonLabel = createStyledLabel("Reason:", 14);
        reasonLabel.setBounds(50, 180, 150, 25);
        gradientPanel.add(reasonLabel);

        JTextArea reasonArea = new JTextArea();
        reasonArea.setBounds(220, 180, 200, 60);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        gradientPanel.add(reasonArea);

        JButton submitButton = createButton("Submit");
        submitButton.setBounds(100, 270, 130, 40);
        submitButton.addActionListener(e -> {
            String oldTrans = oldTransField.getText().trim();
            String newTrans = newTransField.getText().trim();
            String reason = reasonArea.getText().trim();

            if (oldTrans.isEmpty() || newTrans.isEmpty() || reason.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }

            if (rescheduleController.requestReschedule(oldTrans, newTrans, reason)) {
                JOptionPane.showMessageDialog(frame, "Reschedule request submitted.");
                frame.dispose();
                new CustomerMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to submit request.");
            }
        });
        gradientPanel.add(submitButton);

        JButton backButton = createButton("Back");
        backButton.setBounds(260, 270, 130, 40);
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu();
        });
        gradientPanel.add(backButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), getHeight(), new Color(102, 204, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 153, 204), 0, getHeight(), new Color(51, 204, 255));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
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
