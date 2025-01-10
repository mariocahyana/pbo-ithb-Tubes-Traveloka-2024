package View;

import Controller.LoginController;
import Controller.RegisterController;
import Controller.TransactionController;
import Controller.LoginController;
import Model.Model_class.Airplane;
import Model.Model_class.Flight;
import Model.Model_class.User;
import Model.Model_class.Transaksi;
import Model.Model_enum.StatusUser;


import Model.Model_enum.Usia;
import Model.Model_enum.StatusPembayaran;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class OrderTransaction {
    private JFrame frame;
    private TransactionController transactionController;
    private Flight flight;
    private Airplane airplane;
    private String seat;
    Date now = new Date();

    public OrderTransaction(Flight flightt, Airplane airplanee, String seatt) {
        seat = seatt;
        transactionController = new TransactionController();
        flight = flightt;
        airplane = airplanee;
        initOrderTransaction(seat);
    }

    public void initOrderTransaction(String seat) {
        var user = LoginController.getInstance().getLoggedInUser();
        frame = new JFrame("Form Transaction");
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

        JLabel nameLabel = new JLabel("Nama :");
        nameLabel.setBounds(50, 50, 100, 25);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 25);
        gradientPanel.add(nameField);

        JLabel nikLabel = new JLabel("NIK :");
        nikLabel.setBounds(50, 100, 100, 25);
        nikLabel.setForeground(Color.WHITE);
        nikLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(nikLabel);

        JTextField nikField = new JTextField();
        nikField.setBounds(150, 100, 200, 25);
        gradientPanel.add(nikField);

        JLabel emailLabel = new JLabel("Umur:");
        emailLabel.setBounds(50, 150, 100, 25);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(emailLabel);

        JComboBox<Usia> usiaComboBox = new JComboBox<>(Usia.values());
        usiaComboBox.setBounds(150, 150, 200, 25);
        gradientPanel.add(usiaComboBox);

        JButton checkout = createGradientButton("Checkout", new Color(0, 153, 204), new Color(51, 204, 255));
        checkout.setBounds(50, 250, 140, 40);

        gradientPanel.add(checkout);  

        checkout.addActionListener(e -> {
            if (flight == null || seat == null || nameField == null || usiaComboBox == null || nikField == null) {
                JOptionPane.showMessageDialog(null, "Mohon isi semua data diatas");
            }else{
                Transaksi transaksi = new Transaksi();
                transaksi.setAge((Usia) usiaComboBox.getSelectedItem());
                transaksi.setFlight(flight);
                transaksi.setSeat(seat);
                transaksi.setName(nameField.getText());
                transaksi.setPrice((int) flight.getPrice());
                transaksi.setNik(Integer.parseInt(nikField.getText()));
                transaksi.setDate_transaksi(new java.sql.Date(System.currentTimeMillis()).toLocalDate());
                transaksi.setUser(user);
                frame.setVisible(false);
                new PaymentConfirmation(transaksi);
            }
            

        });

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
