package View;

import Model.Model_class.Airplane;
import Model.Model_class.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseSeat {
    JFrame frame;
    JPanel seatPanel;
    JButton[] seats;
    char[] availabilitySeat;
    ArrayList<String> selectedSeats;
    Flight flight;
    Airplane airplane;

    public ChooseSeat(Flight flightt, Airplane airplanee) {
        flight = flightt;
        String seatRow = flight.getSeat_row();
        seats = new JButton[seatRow.length()];
        availabilitySeat = new char[seatRow.length()];
        selectedSeats = new ArrayList<>();

        for (int i = 0; i < seatRow.length(); i++) {
            availabilitySeat[i] = seatRow.charAt(i);
        }
        airplane = airplanee;
        initChooseSeat(seatRow.length());
    }

    void initChooseSeat(int length) {
        frame = new JFrame("Seat Selection");
        frame.setSize(700, 400);
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
        gradientPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bagian Depan", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        seatPanel = new JPanel();
        seatPanel.setLayout(new GridBagLayout());
        seatPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 15; i++) {
            seats[i] = createSeatButton(Integer.toString(i + 1), availabilitySeat[i] == '0');
            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            seatPanel.add(seats[i], gbc);
        }

        gbc.gridx = 3;
        gbc.gridy = 0;
        seatPanel.add(new JLabel("     "), gbc);

        for (int i = 15; i < length; i++) {
            seats[i] = createSeatButton(Integer.toString(i + 1), availabilitySeat[i] == '0');
            gbc.gridx = (i - 15) % 3 + 4;
            gbc.gridy = (i - 15) / 3;
            seatPanel.add(seats[i], gbc);
        }

        JScrollPane scrollPane = new JScrollPane(seatPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private JButton createSeatButton(String text, boolean isAvailable) {
        JButton button = new JButton(text);
        button.setBackground(isAvailable ? Color.GREEN : Color.RED);
        button.setFocusPainted(false);
        button.setEnabled(isAvailable);
        
        if (isAvailable) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you want to select this seat?",
                        "Confirm Seat Selection",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (response == JOptionPane.YES_OPTION) {
                        frame.setVisible(false);
                        new OrderTransaction(flight, airplane, button.getText());
                        
                    
                    }
                }
            });
        }

        return button;
    }

    public ArrayList<String> getSelectedSeats() {
        return selectedSeats;
    }
}
