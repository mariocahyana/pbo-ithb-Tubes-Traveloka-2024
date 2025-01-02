package View;

import javax.swing.*;

import Controller.AirportController;

import java.awt.*;
import java.awt.event.*;

import Model.Model_class.Airport;

public class InputAirportData {
    private JFrame frame;
    private AirportController controller;
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
        button.setBounds(xPosition, yPosition, 100, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public InputAirportData(int actionValue, Airport airports) {
        controller = new AirportController();
        showInputAirlineData(actionValue, airports);
    }

    public void showInputAirlineData(int actionValue, Airport airports) {
        frame = new JFrame("INPUT AIRPORT DATA");
        frame.setSize(400, 150);
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

        JLabel airportLabel = new JLabel("Airport city:");
        airportLabel.setBounds(50, 10, 100, 25);
        airportLabel.setForeground(Color.WHITE);
        airportLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(airportLabel);

        JTextField airportField = new JTextField();
        airportField.setBounds(150, 10, 200, 25);
        gradientPanel.add(airportField);

        JButton update = createGradientButton("UPDATE", 30, 50, new Color(51, 204, 255), new Color(0, 153, 204));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = airportField.getText();
                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nama kota airport tidak boleh kosong!", "Peringatan",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                airports.setCity(nama);
                boolean updateSuccess = controller.updateData(airports); // Panggil fungsi update

                if (updateSuccess) {
                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui.", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
                new AirportData(airports);
            }
        });

        JButton delete = createGradientButton("DELETE", 132, 50, new Color(0, 153, 204), new Color(51, 204, 255));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = airportField.getText();
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
                    new AirportData(null);
                }
            }
        });

        JButton back = createGradientButton("BACK", 255, 50, new Color(51, 204, 255), new Color(0, 153, 204));
        back.addActionListener(e -> {
            frame.dispose();
            new AirportData(airports);
        });

        JButton submit = createGradientButton("SUBMIT", 150, 50, new Color(0, 153, 204), new Color(51, 204, 255));
        submit.addActionListener(e -> {
            String name = airportField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name of airport must be filled");
            }

            Airport airport = new Airport(0, name);
            String result = controller.add(airport);
            switch (result) {
                case "SUCCESS":
                    JOptionPane.showMessageDialog(frame, "Add airport successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AirportData(airport);
                    break;
                case "NAME_EXISTS":
                    JOptionPane.showMessageDialog(frame,
                            "Name of airport already in use. Please use a different name.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Add airport failed. Try again.", "Error",
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

            airportField.setText(airports.getCity());
        }

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
