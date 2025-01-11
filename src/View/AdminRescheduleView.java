package View;

import Controller.AdminRescheduleController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminRescheduleView {
    private JFrame frame;
    private AdminRescheduleController adminRescheduleController;

    public AdminRescheduleView() {
        adminRescheduleController = new AdminRescheduleController();
        showRescheduleRequests();
    }

    public void showRescheduleRequests() {
        frame = new JFrame("Manage Reschedule Requests");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = createGradientPanel();
        gradientPanel.setLayout(new BorderLayout());

        JLabel titleLabel = createStyledLabel("Pending Reschedule Requests", 24);
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Request ID", "Old Transaksi ID", "New Transaksi ID", "Reason", "Date"
        }, 0);
        
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        List<String[]> requests = adminRescheduleController.getPendingRescheduleRequests();
        for (String[] req : requests) {
            model.addRow(req);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton approveButton = createButton("APPROVE");
        approveButton.addActionListener(e -> processRequest(table, model, true));

        JButton rejectButton = createButton("REJECT");
        rejectButton.addActionListener(e -> processRequest(table, model, false));

        JButton backButton = createButton("BACK");
        backButton.addActionListener(e -> {
            frame.dispose();
            new AdminMenu();
        });

        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(backButton);

        gradientPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(gradientPanel);
        frame.setVisible(true);
    }

    private void processRequest(JTable table, DefaultTableModel model, boolean approve) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a request.");
            return;
        }

        int requestID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        String oldTransID = model.getValueAt(selectedRow, 1).toString();
        String newTransID = model.getValueAt(selectedRow, 2).toString();

        boolean result = approve
                ? adminRescheduleController.approveReschedule(requestID, oldTransID, newTransID)
                : adminRescheduleController.rejectReschedule(requestID);

        if (result) {
            JOptionPane.showMessageDialog(frame, approve ? "Request Approved!" : "Request Rejected!");
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to process request.");
        }
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), getHeight(), new Color(102, 204, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 153, 204), 0, getHeight(), new Color(51, 204, 255));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
        };
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
