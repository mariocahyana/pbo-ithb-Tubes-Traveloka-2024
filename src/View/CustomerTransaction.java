package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.CustomerTransactionController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Model_class.Transaksi;

public class CustomerTransaction {
    private JFrame frame;
    private CustomerTransactionController controller;

    private JButton createGradientButton(String text, ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 102, 204);
                Color color2 = new Color(102, 204, 255);
                GradientPaint gp = new GradientPaint(30, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        button.setBounds(1127, 150, 100, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(action);
        return button;
    }

    public CustomerTransaction() {
        controller = new CustomerTransactionController();
        showCustomerTransaction();
    }

    public void showCustomerTransaction() {

        frame = new JFrame("VIEW CUSTOMER TRANSACTION");
        frame.setSize(1270, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 102, 204);
                Color color2 = new Color(102, 204, 255);
                GradientPaint gp = new GradientPaint(30, 0, color1, getWidth(), getHeight(), color2);
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

        String[] columnNames = {
                "Transaksi ID", "User ID", "Price", "Flight ID", "NIK", "Name", "Date Transaksi", "Age",
                "Payment Confirmation", "Active Ticket", "EDIT"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Transaksi> transaksi = controller.getTransactions();
        for (Transaksi transactions : transaksi) {
            model.addRow(new Object[] {
                    transactions.getTransaksiID(),
                    transactions.getUser(),
                    transactions.getPrice(),
                    transactions.getFlight(),
                    transactions.getNik(),
                    transactions.getName(),
                    transactions.getDate_transaksi(),
                    transactions.getAge(),
                    transactions.getStatus(),
                    transactions.getTicket()
            });
        }

        JTable table = new JTable(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 20, 1200, 100);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backAdminMenu = createGradientButton("BACK", e -> {
            frame.dispose();
            new AdminMenu();
        });
        gradientPanel.setLayout(null);
        gradientPanel.add(backAdminMenu);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
