package View;

import javax.swing.*;

import Controller.AirplaneController;

import java.awt.*;
import java.awt.event.*;

import Model.Model_class.Airline;
import Model.Model_class.Airplane;

public class InputAirplaneData {
    private JFrame frame;
    private AirplaneController controller;

    private JButton createGradientButton(String text, int xPosition, int yPosition, Color color1, Color color2) {
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
        button.setBounds(xPosition, yPosition, 95, 45);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public InputAirplaneData(int actionValue, Airplane airplanes) {
        controller = new AirplaneController();
        showInputAirplaneData(actionValue, airplanes);
    }

    public void showInputAirplaneData(int actionValue, Airplane airplanes) {
        frame = new JFrame("INPUT AIRPLANE DATA");
        frame.setSize(380, 250);
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

        JLabel airlineLabel = new JLabel("Airline name:");
        airlineLabel.setBounds(30, 10, 100, 25);
        airlineLabel.setForeground(Color.WHITE);
        airlineLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(airlineLabel);

        JTextField airlineField = new JTextField();
        airlineField.setBounds(150, 10, 180, 25);
        gradientPanel.add(airlineField);

        JLabel airplaneLabel = new JLabel("Airplane name:");
        airplaneLabel.setBounds(30, 50, 120, 25);
        airplaneLabel.setForeground(Color.WHITE);
        airplaneLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(airplaneLabel);

        JTextField airplaneField = new JTextField();
        airplaneField.setBounds(150, 50, 180, 25);
        gradientPanel.add(airplaneField);

        JLabel seatLabel = new JLabel("Total Seat:");
        seatLabel.setBounds(30, 90, 100, 25);
        seatLabel.setForeground(Color.WHITE);
        seatLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(seatLabel);

        JTextField seatField = new JTextField();
        seatField.setBounds(150, 90, 180, 25);
        gradientPanel.add(seatField);

        JButton update = createGradientButton("UPDATE", 30, 140, new Color(51, 204, 255), new Color(0, 153, 204));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String airlineName = airlineField.getText().trim();
                String airplaneName = airplaneField.getText().trim();
                String seatText = seatField.getText().trim();
                if (airlineName.isEmpty() || airplaneName.isEmpty() || seatText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All of the field must be filled");
                }

                int seat = Integer.parseInt(seatText);
                airplanes.setAirlineName(airlineName);
                airplanes.setAirplaneName(airplaneName);
                airplanes.setSeat(seat);

                boolean updateSuccess = controller.updateData(airplanes);

                if (updateSuccess) {
                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui.", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AirplaneData(airplanes);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton delete = createGradientButton("DELETE", 132, 140, new Color(0, 153, 204), new Color(51, 204, 255));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = airplaneField.getText();
                int option = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin menghapus data ?",
                        "Konfirmasi Penghapusan", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    boolean deleteSuccess = controller.deleteData(nama);
                    if (deleteSuccess) {
                        JOptionPane.showMessageDialog(frame, "Data dengan Nama " + nama + " berhasil dihapus.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Gagal menghapus data dengan Nama " + nama + ".");
                    }
                    new AirplaneData(null);
                }
            }
        });

        JButton back = createGradientButton("BACK", 235, 140, new Color(51, 204, 255), new Color(0, 153, 204));
        back.addActionListener(e -> {
            frame.dispose();
            new AirplaneData(airplanes);
        });

        JButton submit = createGradientButton("SUBMIT", 120, 140, new Color(0, 153, 204), new Color(51, 204, 255));
        submit.addActionListener(e -> {
            Airline airline = new Airline();
            String airlineName = airlineField.getText().trim();
            String airplaneName = airplaneField.getText().trim();
            String seatText = seatField.getText().trim();

            if (airlineName.isEmpty() || airplaneName.isEmpty() || seatText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All of the field must be filled");
            }

            int seatTotal = Integer.parseInt(seatText);
            Airplane airplane = new Airplane(0, airline, airlineName, airplaneName, seatTotal);
            String result = controller.add(airplane);
            switch (result) {
                case "SUCCESS":
                    JOptionPane.showMessageDialog(frame, "Add airplane successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AirplaneData(airplane);
                    break;
                case "NAME_EXISTS":
                    JOptionPane.showMessageDialog(frame,
                            "Name of airplane already in use. Please use a different name.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case "AIRLINE_NOT_FOUND":
                    JOptionPane.showMessageDialog(frame,
                            "Name of airline is not found. Please check again the airline name.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Add airline failed. Try again.", "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        gradientPanel.add(back);
        gradientPanel.add(update);
        gradientPanel.add(delete);
        gradientPanel.add(submit);

        if (actionValue == 1) {
            back.setVisible(true);
            update.setVisible(false);
            delete.setVisible(false);
            submit.setVisible(true);
        } else {
            back.setVisible(true);
            update.setVisible(true);
            delete.setVisible(true);
            submit.setVisible(false);

            airlineField.setText(airplanes.getAirlineName());
            airplaneField.setText(airplanes.getAirplaneName());
            seatField.setText(String.valueOf(airplanes.getSeat()));
        }

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
