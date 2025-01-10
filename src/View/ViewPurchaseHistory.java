package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.CustomerTransactionController;
import Controller.LoginController;
import Model.Model_class.Transaksi;
import Model.Model_class.User;

public class ViewPurchaseHistory {
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

    public ViewPurchaseHistory() {
        controller = new CustomerTransactionController();
        initViewPurchaseHistory();
    }

    public void initViewPurchaseHistory() {
         var user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            JOptionPane.showMessageDialog(null, "No user is login!", "Error", JOptionPane.ERROR_MESSAGE);
            new MainMenu();
            return;
        }


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

        List<Transaksi> transaksi = controller.getTransactions(user.getUserID());
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
            new CustomerMenu();
        });
        gradientPanel.setLayout(null);
        gradientPanel.add(backAdminMenu);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }
}
