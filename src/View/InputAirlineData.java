package View;

import javax.swing.*;

import Controller.AirlineController;

import java.awt.*;
import java.awt.event.*;

import Model.Model_class.Airline;

public class InputAirlineData {
    private JFrame frame;
    private AirlineController Controller;

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

    public InputAirlineData(int actionValue, Airline airlines) {
        Controller = new AirlineController();
        showInputAirlineData(actionValue, airlines);
    }

    public void showInputAirlineData(int actionValue, Airline airlines) {
        frame = new JFrame("INPUT AIRLINE DATA");
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

        JLabel airlineLabel = new JLabel("Airline name:");
        airlineLabel.setBounds(50, 10, 100, 25);
        airlineLabel.setForeground(Color.WHITE);
        airlineLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gradientPanel.add(airlineLabel);

        JTextField airlineField = new JTextField();
        airlineField.setBounds(150, 10, 200, 25);
        gradientPanel.add(airlineField);

        JButton update = createGradientButton("UPDATE", 30, 50, new Color(51, 204, 255), new Color(0, 153, 204));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = airlineField.getText();
                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nama airline tidak boleh kosong!", "Peringatan",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                airlines.setName(nama);
                boolean updateSuccess = Controller.updateData(airlines); // Panggil fungsi update

                if (updateSuccess) {
                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui.", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
                new AirlineData(airlines);
            }
        });
        JButton delete = createGradientButton("DELETE", 132, 50, new Color(0, 153, 204), new Color(51, 204, 255));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = airlineField.getText();
                int option = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin menghapus data ?",
                        "Konfirmasi Penghapusan", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    boolean deleteSuccess = Controller.deleteData(nama);
                    if (deleteSuccess) {
                        JOptionPane.showMessageDialog(frame, "Data dengan Nama " + nama + " berhasil dihapus.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Gagal menghapus data dengan Nama " + nama + ".");
                    }
                    new AirlineData(null);
                }
            }
        });

        JButton back = createGradientButton("BACK", 255, 50, new Color(51, 204, 255), new Color(0, 153, 204));
        back.addActionListener(e -> {
            frame.dispose();
            new AirlineData(airlines);
        });

        JButton submit = createGradientButton("SUBMIT", 310, 240, new Color(0, 153, 204), new Color(51, 204, 255));
        submit.addActionListener(e -> {
            String name = airlineField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name of airline must be filled");
            }

            Airline airline = new Airline(0, name);
            String result = Controller.add(airline);
            switch (result) {
                case "SUCCESS":
                    JOptionPane.showMessageDialog(frame, "Add airline successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AirlineData(airline);
                    break;
                case "NAME_EXISTS":
                    JOptionPane.showMessageDialog(frame,
                            "Name of airline already in use. Please use a different name.", "Error",
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

            airlineField.setText(airlines.getName());
        }

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
