package View;

import javax.swing.*;

import Controller.AirlineController;
import Model.Model_class.Airline;

import java.awt.*;
import java.awt.event.ActionListener;

public class EditAirlineData {
    private JFrame frame;
    private AirlineController controller;
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
        button.setBounds(xPosition, yPosition, 90, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(action);
        return button;
    }

    public EditAirlineData(){
        controller = new AirlineController();
        showEditAirlineData();
    }

    public void showEditAirlineData(){
        frame = new JFrame("CARI AIRLINE DATA");
        frame.setSize(450, 150);
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

        JLabel keyLabel = new JLabel("Masukkan Airline ID : ");
        keyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        keyLabel.setBounds(10, 10, 200, 20);
        gradientPanel.add(keyLabel);

        JTextField keyField = new JTextField();
        keyField.setBounds(200, 10, 200, 25);
        gradientPanel.add(keyField);

        JButton searchButton = createGradientButton("CARI", 200, 50, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            if (!keyField.getText().isEmpty()) {
                int keyFieldValue = Integer.parseInt(keyField.getText()); 
                Airline airline = controller.getAirline(keyFieldValue);
                if (airline != null) {
                    frame.dispose();
                    new InputAirlineData(2, airline);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Data airline tidak ditemukan!", "Notifikasi", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Field Harus Diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gradientPanel.add(searchButton);

        JButton back = createGradientButton("BACK", 310, 50, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new AirlineData(null);
        });
        gradientPanel.add(back);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
