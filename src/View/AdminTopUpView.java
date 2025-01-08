package View;

import Controller.TopUpController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class AdminTopUpView {
    private JFrame frame;
    private TopUpController topUpController;

    public AdminTopUpView() {
        topUpController = new TopUpController();
        showPendingRequests();
    }

    public void showPendingRequests() {
        frame = new JFrame("Pending TopUp Requests");
        frame.setSize(600, 400);
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

        JLabel titleLabel = new JLabel("Pending TopUp Requests");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Request ID", "User ID", "Amount", "Date"}, 0);
        JTable table = new JTable(model);

        List<Map<String, Object>> requests = topUpController.getPendingTopUpRequests();
        for (Map<String, Object> request : requests) {
            model.addRow(new Object[]{
                    request.get("requestID"),
                    request.get("userID"),
                    request.get("amount"),
                    request.get("request_date")
            });
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(550, 200));
        gradientPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton approveButton = createButton("APPROVE", new Color(0, 153, 204), new Color(51, 204, 255));
        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int requestID = (int) table.getValueAt(selectedRow, 0);
                if (topUpController.approveTopUpRequest(requestID)) {
                    JOptionPane.showMessageDialog(frame, "Request sudah diapprove", "Success", JOptionPane.INFORMATION_MESSAGE);
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal approve request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton rejectButton = createButton("REJECT", new Color(0, 153, 204), new Color(51, 204, 255));
        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int requestID = (int) table.getValueAt(selectedRow, 0);
                if (topUpController.rejectTopUpRequest(requestID)) {
                    JOptionPane.showMessageDialog(frame, "Request sudah direject", "Success", JOptionPane.INFORMATION_MESSAGE);
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal reject request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = createButton("BACK", new Color(0, 153, 204), new Color(51, 204, 255));
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
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }
}
