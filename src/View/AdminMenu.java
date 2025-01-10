package View;

import javax.swing.*;

import Controller.LoginController;

import java.awt.*;
import java.awt.event.ActionListener;


public class AdminMenu {
    private JFrame frame;
    private JButton createGradientButton(String text, int xPosition, int yPosition, Color color1, Color color2, ActionListener action) {
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
        button.setBounds(xPosition, yPosition, 250, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(action);
        return button;
    }

    public AdminMenu(){
        showMainMenu();
    }

    public void showMainMenu(){
        frame = new JFrame("HOME ADMIN");
        frame.setSize(600, 430);
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

        JButton customerTransaction = createGradientButton("CUSTOMER TRANSACTION", 30, 30, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new CustomerTransaction();
        });

        JButton airlineData = createGradientButton("AILINE DATA", 30, 100, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new AirlineData(null);
        });

        JButton airplaneData = createGradientButton("AIPLANE DATA", 30, 170, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new AirplaneData(null);
        });

        JButton airportData = createGradientButton("AIRPORT DATA", 30, 240, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new AirportData(null);
        });

        JButton flightSchedule = createGradientButton("FLIGHT SCHEDULE", 310, 30, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            frame.dispose();
            new FlightData(null);
        });

        JButton reschedule = createGradientButton("RESCHEDULE", 310, 100, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            frame.dispose();
            new AdminMenu();
        });

        JButton customerReview = createGradientButton("CUSTOMER REVIEW", 310, 170, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            frame.dispose();
            new AdminFeedbackView();
        });

        JButton confirmTopup = createGradientButton("CONFIRM TOPUP", 310, 240, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            frame.dispose();
            new AdminTopUpView();
        });

        JButton exit = createGradientButton("LOG OUT", 310, 310, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            LoginController.getInstance().logout();
            frame.dispose();
            new MainMenu();
        });

        gradientPanel.add(customerTransaction);
        gradientPanel.add(airlineData);
        gradientPanel.add(flightSchedule);
        gradientPanel.add(airplaneData);
        gradientPanel.add(airportData);
        gradientPanel.add(reschedule);
        gradientPanel.add(customerReview);
        gradientPanel.add(confirmTopup);
        gradientPanel.add(exit);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
