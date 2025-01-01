package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.AirplaneController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Model_class.Airplane;

public class AirplaneData {
    private JFrame frame;
    private AirplaneController controller;
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

    public AirplaneData(Airplane airplane){
        controller = new AirplaneController();
        showAirplaneData(airplane);
    }

    public void showAirplaneData(Airplane airplane){
        frame = new JFrame("AIRPLANE DATA");
        frame.setSize(525, 250);
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

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(56, 243, 240));
                } else {
                    c.setBackground(new Color(102, 204, 255));
                }

                return c;
            }
        };

        String[] columnNames = {"Airplane ID", "Airline ID", "Airline Name", "Airplane Name" , "Seat"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Airplane> airplanes = controller.getAirplaneData();
        for (Airplane airplaness : airplanes) {
            model.addRow(new Object[] {
                    airplaness.getAirplaneID(),
                    airplaness.getAirline().getAirlineID(),
                    airplaness.getAirlineName(),
                    airplaness.getAirplaneName(),
                    airplaness.getSeat()
            });
        }

        JTable table = new JTable(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 20, 450, 100);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        JButton add = createGradientButton("ADD", 30, 140, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new InputAirplaneData(1, null);
        });
        JButton edit = createGradientButton("EDIT", 130, 140, new Color(51, 204, 255), new Color(0, 153, 204), e -> {
            frame.dispose();
            new EditAirplaneData();
        });
        JButton back = createGradientButton("BACK", 390, 140, new Color(0, 153, 204), new Color(51, 204, 255), e -> {
            frame.dispose();
            new AdminMenu();
        });

        gradientPanel.setLayout(null);
        gradientPanel.add(add);
        gradientPanel.add(edit);
        gradientPanel.add(back);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
