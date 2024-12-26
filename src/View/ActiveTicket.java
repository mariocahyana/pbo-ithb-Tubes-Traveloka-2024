package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Model.Model_class.Active_Ticket;
import Model.Model_class.User;
import Controller.ActiveTicketController;

public class ActiveTicket {
    private JTable ticketTable;
    private DefaultTableModel tableModel;

    public ActiveTicket(User user) {
        initActiveTicket(user);
    }

    public void initActiveTicket(User user) {
        JFrame frame = new JFrame();
        frame.setTitle("Tiket yang Dibeli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        String[] columnNames = { "Name", "NIK", "age", "Origin", "Destination" };
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(ticketTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> System.exit(0));
        mainPanel.add(closeButton, BorderLayout.SOUTH);

        frame.add(mainPanel);

        ActiveTicketController activeTicketController = new ActiveTicketController(Integer.parseInt(user.getUserID()));
        List<Active_Ticket> aTicket = activeTicketController.InitActiveController(Integer.parseInt(user.getUserID()));
        this.addTickets(aTicket);
        frame.setVisible(true);
    }

    public void addTickets(List<Active_Ticket> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            Active_Ticket ticket = tickets.get(i);
            tableModel.addRow(new Object[] {
                    ticket.getName(),
                    ticket.getNik(),
                    ticket.getAge(),
                    ticket.getOriginCity(),
                    ticket.getDestinationCity()
            });
        }
    }
}