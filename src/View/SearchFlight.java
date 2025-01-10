package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.FlightController;

import javax.swing.*;
import java.awt.*;

import Model.Model_class.User;
import Model.Model_enum.StatusUser;

public class SearchFlight {
    public SearchFlight(){
        initSearchFlight();
    }

    public void initSearchFlight(){
        JFrame frame = new JFrame("Register");
        frame.setSize(450, 350);
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

        JLabel originLabel = new JLabel("Origin City:");
        originLabel.setBounds(50, 50, 100, 25);
        originLabel.setForeground(Color.WHITE);
        originLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(originLabel);

        JTextField originField = new JTextField();
        originField.setBounds(150, 50, 200, 25);
        gradientPanel.add(originField);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(50, 100, 100, 25);
        destinationLabel.setForeground(Color.WHITE);
        destinationLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(destinationLabel);

        JTextField destinationField = new JTextField();
        destinationField.setBounds(150, 100, 200, 25);
        gradientPanel.add(destinationField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 150, 100, 25);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(150, 150, 200, 25);
        gradientPanel.add(dateField);

        JButton searchButton = createGradientButton("Search", new Color(0, 153, 204), new Color(51, 204, 255));
        searchButton.setBounds(50, 250, 140, 40);

        searchButton.addActionListener(e -> {
            FlightController fc = new FlightController();
            new ViewFlight(fc.searchFlightsByCriteria(originField.getText(),destinationField.getText(),dateField.getText()));
        });

        gradientPanel.add(searchButton);

        JButton backButton = createGradientButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
        backButton.setBounds(210, 250, 140, 40);
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu();
        });
        gradientPanel.add(backButton);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JButton createGradientButton(String text, Color color1, Color color2) {
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

