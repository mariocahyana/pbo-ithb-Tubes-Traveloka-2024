package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;

import java.util.List;

import Controller.CustomerTransactionController;
import Controller.FlightController;
import Controller.LoginController;
import Controller.TransactionController;
import Model.Model_class.Transaksi;
import Model.Model_class.User;
import Model.Model_enum.ActiveTicket;
import Model.Model_enum.StatusPembayaran;

public class PayTicket {
    private JFrame frame;
    private CustomerTransactionController controller;
    private Transaksi trans;

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
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

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

    public PayTicket() {
        controller = new CustomerTransactionController();
        initViewActiveTicket();
    }

    public void initViewActiveTicket() {
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

        List<Transaksi> transaksi = controller.getTransactionsPayLater();
        for (Transaksi transactions : transaksi) {
            if (transactions.getStatus() == StatusPembayaran.PAYLATER) {
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
                    transactions.getTicket(),
            });
            }
            
        }

        JTable table = new JTable(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getColumnModel().getColumn(10).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(10).setCellEditor(new ButtonEditor(table));

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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText(value != null ? value.toString() : "EDIT");
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JTable table) {
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> {
                int row = table.getSelectedRow();
                String transaksiID = table.getValueAt(row, 0).toString();
                JOptionPane.showMessageDialog(null, "Editing Transaksi ID: " + transaksiID);
                TransactionController tc = new TransactionController();
                trans = tc.getTransactionByID(Integer.parseInt(transaksiID));
                clicked = true;
                payNow(Integer.parseInt(transaksiID));
                fireEditingStopped();
            });
        }

        public void payNow(int idTrans) {
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

            int xLeft = 80; 
            int buttonWidth = 240;
            if (user.getBalance() < trans.getPrice()) {
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

                String pesan = "<html>Saldo Kamu: " + user.getBalance() + "<br>" +
                        "Harga Tiket" + trans.getPrice() + "<br>" +
                        "Sisa Saldo" + (user.getBalance() - trans.getPrice()) + "<br>" +
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
                    trans.setUser(user);
                    trans.setTicket(ActiveTicket.ACTIVE);
                    trans.setStatus(StatusPembayaran.PAYNOW);
                    tc.updateTransaction((idTrans), trans.getSeat(), "PAYNOW", "ACTIVE");
                    JOptionPane.showMessageDialog(null, "Berhasil Membayar");
                    new CustomerMenu();
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

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            label = value != null ? value.toString() : "EDIT";
            button.setText(label);
            clicked = false;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
}
