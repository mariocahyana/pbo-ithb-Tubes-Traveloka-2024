package View;

import Controller.TopUpController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AdminTopUpView {
    private JFrame frame;
    private TopUpController topUpController;
    private Color primaryColor = new Color(0, 102, 204);
    private Color secondaryColor = new Color(102, 204, 255);

    public AdminTopUpView() {
        topUpController = new TopUpController();
        showPendingRequests();
    }

    public void showPendingRequests() {
        frame = new JFrame("TOPUP REQUEST");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = createStyledLabel("Customer Topup Request", 28);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Request ID", "User ID", "Amount", "Date"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(primaryColor);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(51, 204, 255, 100));
        table.setGridColor(new Color(230, 230, 230));
        
        List<Map<String, Object>> requests = topUpController.getPendingTopUpRequests();
        for (Map<String, Object> request : requests) {
            model.addRow(new Object[]{
                request.get("requestID"),
                request.get("userID"),
                request.get("amount"),
                request.get("request_date")
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(createCustomScrollBarUI());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton approveButton = createButton("APPROVE", e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int requestID = (int) table.getValueAt(selectedRow, 0);
                handleTopUpAction(requestID, true, selectedRow, model);
            } else {
                showWarningMessage("Please select a row to approve.");
            }
        }, true);

        JButton rejectButton = createButton("REJECT", e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int requestID = (int) table.getValueAt(selectedRow, 0);
                handleTopUpAction(requestID, false, selectedRow, model);
            } else {
                showWarningMessage("Please select a row to reject.");
            }
        }, true);

        JButton backButton = createButton("BACK", e -> {
            frame.dispose();
            new AdminMenu();
        }, true);

        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(backButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, primaryColor, getWidth(), getHeight(), secondaryColor);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JButton createButton(String text, ActionListener action, boolean isRounded) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 153, 204), 0, getHeight(), new Color(51, 204, 255));
                g2d.setPaint(gp);
                if (isRounded) {
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                } else {
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 50));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);

        return button;
    }

    private BasicScrollBarUI createCustomScrollBarUI() {
        return new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(255, 255, 255, 100);
                this.trackColor = new Color(0, 0, 0, 0);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        };
    }

    private void handleTopUpAction(int requestID, boolean isApprove, int selectedRow, DefaultTableModel model) {
        boolean success = isApprove ? 
            topUpController.approveTopUpRequest(requestID) : 
            topUpController.rejectTopUpRequest(requestID);

        if (success) {
            showSuccessMessage(isApprove ? "Request approved successfully!" : "Request rejected successfully!");
            model.removeRow(selectedRow);
        } else {
            showErrorMessage(isApprove ? "Failed to approve request." : "Failed to reject request.");
        }
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}