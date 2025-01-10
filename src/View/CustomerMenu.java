package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class CustomerMenu {
    private JFrame frame;

    public CustomerMenu() {
        showMenu();
    }

    public void showMenu() {
        var user = LoginController.getInstance().getLoggedInUser();

        if (user == null) {
            AlertDesignTemplate.showErrorDialog(frame, "Error", "Gada user yang login nihh");
            new MainMenu();
            return;
        }

        frame = new JFrame("Customer Menu");
        frame.setSize(808, 658);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getNama() + "!");
        welcomeLabel.setBounds(0, 20, 800, 50);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(welcomeLabel);

        int xLeft = 150;
        int xRight = 410;
        int yStart = 100;
        int buttonWidth = 240;
        int buttonHeight = 50;
        int ySelisih = 60; 

        JButton writeReviewButton = createButton("Write a Review", new Color(0, 153, 204), new Color(51, 204, 255));
        writeReviewButton.setBounds(xLeft, yStart, buttonWidth, buttonHeight);
        writeReviewButton.addActionListener(e -> {
            frame.dispose();
            new WriteReviewView();
        });
        gradientPanel.add(writeReviewButton);

        JButton viewReviewsButton = createButton("View Reviews", new Color(0, 153, 204), new Color(51, 204, 255));
        viewReviewsButton.setBounds(xLeft, yStart + ySelisih, buttonWidth, buttonHeight);
        viewReviewsButton.addActionListener(e -> {
            frame.dispose();
            new ViewReviewsView();
        });
        gradientPanel.add(viewReviewsButton);

        JButton topUpBalanceButton = createButton("Top Up Balance", new Color(0, 153, 204), new Color(51, 204, 255));
        topUpBalanceButton.setBounds(xLeft, yStart + 2 * ySelisih, buttonWidth, buttonHeight);
        topUpBalanceButton.addActionListener(e -> {
            frame.dispose();
            new TopUpBalanceView();
        });
        gradientPanel.add(topUpBalanceButton);

        JButton viewBalanceButton = createButton("View Balance", new Color(0, 153, 204), new Color(51, 204, 255));
        viewBalanceButton.setBounds(xLeft, yStart + 3 * ySelisih, buttonWidth, buttonHeight);
        viewBalanceButton.addActionListener(e -> {
            frame.dispose();
            new ViewBalanceView();
        });
        gradientPanel.add(viewBalanceButton);

        JButton updateProfileButton = createButton("Update Profile", new Color(0, 153, 204), new Color(51, 204, 255));
        updateProfileButton.setBounds(xLeft, yStart + 4 * ySelisih, buttonWidth, buttonHeight);
        updateProfileButton.addActionListener(e -> {
            frame.dispose();
            new UpdateProfileView();
        });
        gradientPanel.add(updateProfileButton);

        JButton changePasswordButton = createButton("Change Password", new Color(0, 153, 204), new Color(51, 204, 255));
        changePasswordButton.setBounds(xLeft, yStart + 5 * ySelisih, buttonWidth, buttonHeight);
        changePasswordButton.addActionListener(e -> {
            frame.dispose();
            new ChangePasswordView();
        });
        gradientPanel.add(changePasswordButton);

        JButton viewActiveTicketButton = createButton("View Active Ticket", new Color(0, 153, 204), new Color(51, 204, 255));
        viewActiveTicketButton.setBounds(xLeft, yStart + 6 * ySelisih, buttonWidth, buttonHeight);
        viewActiveTicketButton.addActionListener(e -> {
            frame.dispose();
            // new viewActiveTicketView();
        });
        gradientPanel.add(viewActiveTicketButton);

        JButton confirmPaymentButton = createButton("Confirm Payment", new Color(0, 153, 204), new Color(51, 204, 255));
        confirmPaymentButton.setBounds(xRight, yStart, buttonWidth, buttonHeight);
        confirmPaymentButton.addActionListener(e -> {
            frame.dispose();
            // new ConfirmPaymentView();
        });
        gradientPanel.add(confirmPaymentButton);

        JButton bookTicketButton = createButton("Book Ticket", new Color(0, 153, 204), new Color(51, 204, 255));
        bookTicketButton.setBounds(xRight, yStart + ySelisih, buttonWidth, buttonHeight);
        bookTicketButton.addActionListener(e -> {
            frame.dispose();
            // new BookTicketView();
        });
        gradientPanel.add(bookTicketButton);

        JButton chooseSeatButton = createButton("Choose Seat", new Color(0, 153, 204), new Color(51, 204, 255));
        chooseSeatButton.setBounds(xRight, yStart + 2 * ySelisih, buttonWidth, buttonHeight);
        chooseSeatButton.addActionListener(e -> {
            frame.dispose();
            // new ChooseSeatView();
        });
        gradientPanel.add(chooseSeatButton);

        JButton searchFlightButton = createButton("Search Flight", new Color(0, 153, 204), new Color(51, 204, 255));
        searchFlightButton.setBounds(xRight, yStart + 3 * ySelisih, buttonWidth, buttonHeight);
        searchFlightButton.addActionListener(e -> {
            frame.dispose();
            // new SearchFlightView();
        });
        gradientPanel.add(searchFlightButton);

        JButton viewPurchaseHistoryButton = createButton("View Purchase History", new Color(0, 153, 204), new Color(51, 204, 255));
        viewPurchaseHistoryButton.setBounds(xRight, yStart + 4 * ySelisih, buttonWidth, buttonHeight);
        viewPurchaseHistoryButton.addActionListener(e -> {
            frame.dispose();
            // new ViewPurchaseHistoryView();
        });
        gradientPanel.add(viewPurchaseHistoryButton);

        JButton rescheduleButton = createButton("Reschedule", new Color(0, 153, 204), new Color(51, 204, 255));
        rescheduleButton.setBounds(xRight, yStart + 5 * ySelisih, buttonWidth, buttonHeight);
        rescheduleButton.addActionListener(e -> {
            frame.dispose();
            new Reschedule();
        });
        gradientPanel.add(rescheduleButton);

        JButton logoutButton = createButton("Logout", new Color(0, 153, 204), new Color(51, 204, 255));
        logoutButton.setBounds(xRight, yStart + 6 * ySelisih, buttonWidth, buttonHeight);
        logoutButton.addActionListener(e -> {
            LoginController.getInstance().logout();
            frame.dispose();
            new MainMenu();
        });
        gradientPanel.add(logoutButton);

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
