package View;

import Controller.FlightController;
import Controller.LoginController;
import Controller.TransactionController;
import Model.Model_class.Airplane;
import Model.Model_class.Flight;
import Model.Model_class.Transaksi;
import Model.Model_class.User;
import Model.Model_enum.ActiveTicket;
import Model.Model_enum.StatusPembayaran;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PaymentConfirmation {
    private JFrame frame;
    private Flight flight;
    private Airplane airplane;
    private Transaksi transaksi;
    private String seat;

    PaymentConfirmation(Transaksi transaksii) {
        transaksi = transaksii;
        initShowMenu();
    }

    public void initShowMenu() {
        int extraSize = 50;
        frame = new JFrame("Confirm Here");
        frame.setSize(400, 600 + extraSize);
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

        int xLeft = 80; // klom kiri
        int yStart = 280;
        int buttonWidth = 240;
        int buttonHeight = 50;
        int ySelisih = 60; // jarak antar tombol


        JLabel welcomeLabel = new JLabel("Payment Confirmation");
        welcomeLabel.setBounds(-100, 20, 600, 50);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(welcomeLabel);


        JLabel detailLabel = new JLabel(detail());
        detailLabel.setBounds(xLeft, 80, buttonWidth, 150);
        detailLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        detailLabel.setForeground(Color.WHITE);
        gradientPanel.add(detailLabel);

        JLabel askLabel = new JLabel("Silakan Pilih Opsi Pembayaran ");
        askLabel.setBounds(80, 200+ extraSize, 240, 50);
        askLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        askLabel.setForeground(Color.WHITE);
        gradientPanel.add(askLabel);

        JButton payNowButton = createButton("Pay Now", new Color(0, 153, 204), new Color(51, 204, 255));
        payNowButton.setBounds(xLeft, yStart+ extraSize, buttonWidth, buttonHeight);
        payNowButton.addActionListener(e -> {
            frame.dispose();
            payNow();
        });
        gradientPanel.add(payNowButton);

        JButton viewReviewsButton = createButton("Pay Later", new Color(0, 153, 204), new Color(51, 204, 255));
        viewReviewsButton.setBounds(xLeft, yStart + ySelisih+ extraSize, buttonWidth, buttonHeight);
        viewReviewsButton.addActionListener(e -> {
            frame.dispose();
            payLater();
        });
        gradientPanel.add(viewReviewsButton);

        JButton cancelButton = createButton("Cancel", new Color(0, 153, 204), new Color(51, 204, 255));
        cancelButton.setBounds(xLeft, yStart + 2 * ySelisih+ extraSize, buttonWidth, buttonHeight);
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu();
        });
        gradientPanel.add(cancelButton);

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

    public String detail() {
        String detailsHtml = String.format(
                "<html>" +
                        "<b>Flight Details:</b><br>" +
                        "<b>Departure Time:</b> %s<br>" +
                        "<b>Origin:</b> %s<br>" +
                        "<b>Destination:</b> %s<br>" +
                        "<b>Nama Penumpang:</b> %s<br>" +
                        "<b>NIK:</b> %s<br>" +
                        "<b>Umur:</b> %s<br>" +
                        "<b>Seat:</b> %s<br>" +
                        "<b>Price:</b> %.2f" +
                        "</html>",
                transaksi.getFlight().getDepartureTime(),
                transaksi.getFlight().getOriginCity(),
                transaksi.getFlight().getDestinationCity(),
                transaksi.getName(),
                transaksi.getNik(),
                transaksi.getAge(),
                transaksi.getSeat(),
                transaksi.getPrice());
        return detailsHtml;
    }
    
    public void payLater(){
        var user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
        JOptionPane.showMessageDialog(null, "No user is login!", "Error",
        JOptionPane.ERROR_MESSAGE);
        new MainMenu();
        return;
        }
        TransactionController tc = new TransactionController();
        frame = new JFrame("Confirm Here");
        frame.setSize(500, 400);
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
        frame.add(gradientPanel);

        int xLeft = 80; // klom kiri
        int buttonWidth = 240;

            JLabel welcomeLabel = new JLabel("Payment Notification - Pay Later");
            welcomeLabel.setBounds(45, 20, 400, 50);
            welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(welcomeLabel);

            JLabel line = new JLabel("---------------------------------------");
            line.setBounds(30, 50, 450, 50);
            line.setFont(new Font("SansSerif", Font.BOLD, 24));
            line.setForeground(Color.WHITE);
            line.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(line);

            JLabel messageLabel = new JLabel("<html>Tiket Sudah Dipesan <br> Mohon segera dibayar </html>");
            messageLabel.setBounds(120, 110, buttonWidth, 120);
            messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(messageLabel);
            Border border = BorderFactory.createEtchedBorder(Color.white, Color.BLUE);
            messageLabel.setBorder(border);

            transaksi.setUser(user);
            transaksi.setTicket(ActiveTicket.ACTIVE);
            transaksi.setStatus(StatusPembayaran.PAYLATER);
            tc.createTransaction(transaksi);
            FlightController fc = new FlightController();
            String seatRow = fc.getSeatRow(transaksi.getFlight().getFlightID());
            String updatedRow = seatRow.substring(0, Integer.parseInt(transaksi.getSeat())) + 1 + seatRow.substring(Integer.parseInt(transaksi.getSeat()) + 1);
            fc.updateSeatRow(transaksi.getFlight().getFlightID(), updatedRow, (fc.getActiveTicket(transaksi.getFlight().getFlightID())));
            
            JButton payButton = createButton("Oke", new Color(0, 153, 204), new Color(51, 204, 255));
            payButton.setBounds(xLeft+90, 280, 150, 40);
            payButton.addActionListener(e -> {
                frame.dispose();
                
            });
            gradientPanel.add(payButton);
            frame.setVisible(true);
    }

    public void payNow() {
        var user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
        JOptionPane.showMessageDialog(null, "No user is login!", "Error",
        JOptionPane.ERROR_MESSAGE);
        new MainMenu();
        return;
        }
        TransactionController tc = new TransactionController();
        frame = new JFrame("Confirm Here");
        frame.setSize(500, 400);
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
        frame.add(gradientPanel);

        int xLeft = 80; // klom kiri
        int buttonWidth = 240;
        if (user.getBalance() < transaksi.getPrice()) {
            JOptionPane.showMessageDialog(null, "Maaf Saldo kamu belum cukup, silakan melakukan Top Up Balance",
                    "Error", JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
        } else {

            JLabel welcomeLabel = new JLabel("Payment Confirmation - Pay Now");
            welcomeLabel.setBounds(45, 20, 400, 50);
            welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(welcomeLabel);

            JLabel line = new JLabel("---------------------------------------");
            line.setBounds(30, 50, 450, 50);
            line.setFont(new Font("SansSerif", Font.BOLD, 24));
            line.setForeground(Color.WHITE);
            line.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(line);

            String pesan = "<html>Saldo Kamu: "+user.getBalance() +"<br>" +
                    "Harga Tiket"+transaksi.getPrice() +"<br>"  +
                    "Sisa Saldo"+(user.getBalance()-transaksi.getPrice()) +"<br>"  +
                    "Apakah Kamu Yakin?</html>";

            JLabel messageLabel = new JLabel(pesan);
            messageLabel.setBounds(120, 110, buttonWidth, 120);
            messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gradientPanel.add(messageLabel);
            Border border = BorderFactory.createEtchedBorder(Color.white, Color.BLUE);
            messageLabel.setBorder(border);

            JButton payButton = createButton("Pay", new Color(0, 153, 204), new Color(51, 204, 255));
            payButton.setBounds(xLeft, 280, 150, 40);
            payButton.addActionListener(e -> {
                frame.dispose();
                transaksi.setUser(user);
                transaksi.setTicket(ActiveTicket.ACTIVE);
                transaksi.setStatus(StatusPembayaran.PAYNOW);
                tc.createTransaction(transaksi);
                FlightController fc = new FlightController();
                String seatRow = fc.getSeatRow(transaksi.getFlight().getFlightID());
                System.out.println(seatRow);
                String updatedRow = seatRow.substring(0, Integer.parseInt(transaksi.getSeat())) + 1 + seatRow.substring(Integer.parseInt(transaksi.getSeat()) + 1);
                System.out.println(updatedRow);
                fc.updateSeatRow(transaksi.getFlight().getFlightID(), updatedRow, (fc.getActiveTicket(transaksi.getFlight().getFlightID())));
            });
            gradientPanel.add(payButton);

            JButton cancelButton = createButton("Cancel", new Color(0, 153, 204), new Color(51, 204, 255));
            cancelButton.setBounds(xLeft + 200, 280, 150, 40);
            cancelButton.addActionListener(e -> {
                frame.dispose();
                new CustomerMenu();
            });
            gradientPanel.add(cancelButton);
            frame.setVisible(true);
        }
    }

}
