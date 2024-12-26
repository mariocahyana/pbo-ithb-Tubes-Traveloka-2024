package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Model_class.User;


public class UserMenu {
    public UserMenu(User user){
        initUserMenu(user);
    }
    public void initUserMenu(User user){

        JFrame frame = new JFrame("Travel App Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 300);

        JLabel halo = new JLabel("Hai " + user.getNama() + ", selamat datang di Traveloke !!" );

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding di sekitar panel

        JButton btnCheckBalance = new JButton("Check Balance");
        JButton btnCheckTicket = new JButton("Check Ticket");
        JButton btnBookingTicket = new JButton("Booking Ticket");
        JButton btnViewActiveTicket = new JButton("View Active Ticket");
        JButton btnReview = new JButton("Review");


        btnCheckBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCheckTicket.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBookingTicket.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnViewActiveTicket.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReview.setAlignmentX(Component.CENTER_ALIGNMENT);
        halo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(halo);
        panel.add(Box.createVerticalStrut(15)); // Spasi antar tombol
        panel.add(btnCheckBalance);
        panel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
        panel.add(btnCheckTicket);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnBookingTicket);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnViewActiveTicket);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnReview);

        frame.add(panel);

        btnCheckBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Check Balance clicked!");
            }
        });

        btnCheckTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Check Ticket clicked!");
            }
        });

        btnBookingTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Booking Ticket clicked!");
            }
        });

        // btnViewActiveTicket.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         JOptionPane.showMessageDialog(frame, "View Active Ticket clicked!");
        //     }
        // });

        btnViewActiveTicket.addActionListener(e -> {
            frame.dispose();
            new ActiveTicket(user);
        });

        frame.setVisible(true);
    }
}
