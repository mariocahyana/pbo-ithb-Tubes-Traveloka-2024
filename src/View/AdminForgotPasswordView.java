package View;

import Controller.AdminForgotPasswordController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminForgotPasswordView {
    private JFrame frame;
    private AdminForgotPasswordController passwordResetController;
    private Color primaryColor = new Color(0, 102, 204);
    private Color secondaryColor = new Color(102, 204, 255);

    public AdminForgotPasswordView() {
        passwordResetController = new AdminForgotPasswordController();
        showPendingRequests();
    }

    public void showPendingRequests() {
        frame = new JFrame("PASSWORD RESET REQUESTS");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = createGradientPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = createStyledLabel("Pending Password Reset Requests", 28);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Reset ID", "User Name", "Email", "Requested Date"}, 0
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

        List<String[]> pendingRequests = passwordResetController.getPendingRequest();
        for (String[] request : pendingRequests) {
            model.addRow(new Object[]{
                request[0], request[1], request[2], request[3]
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
                int resetID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                handleResetAction(resetID, true, selectedRow, model);
            } else {
                showWarningMessage("Pilih permintaan yang ingin di approve :)");
            }
        });

        JButton rejectButton = createButton("REJECT", e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int resetID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                handleResetAction(resetID, false, selectedRow, model);
            } else {
                showWarningMessage("Pilih permintaan yang ingin di reject :)");
            }
        });

        JButton backButton = createButton("BACK", e -> {
            frame.dispose();
            new AdminMenu();
        });

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

    private JButton createButton(String text, ActionListener action) {
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
        };
    }

    private void handleResetAction(int resetID, boolean isApprove, int selectedRow, DefaultTableModel model) {
        boolean success = isApprove ?
            passwordResetController.approveRequest(resetID) :
            passwordResetController.rejectRequest(resetID);

        if (success) {
            showSuccessMessage(isApprove ? "Berhasil di-approve!" : "Berhasil di-reject!");
            model.removeRow(selectedRow);
        } else {
            showErrorMessage(isApprove ? "Gagal approve :(" : "Gagal reject :(");
        }
    }

    private void showSuccessMessage(String message) {
        AlertDesignTemplate.showInfoDialog(frame, "Success", message);
    }

    private void showErrorMessage(String message) {
        AlertDesignTemplate.showInfoDialog(frame, "Error", message);
    }

    private void showWarningMessage(String message) {
        AlertDesignTemplate.showInfoDialog(frame, "Warning", message);
    }
}
