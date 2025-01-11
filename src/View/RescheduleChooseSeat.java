// package View;

// import Model.Model_class.Airplane;
// import Model.Model_class.Flight;
// import Model.Model_enum.ActiveTicket;
// import Model.Model_enum.StatusPembayaran;
// import Model.Model_enum.Usia;
// import Model.Model_class.Transaksi;

// import javax.swing.*;

// import Controller.LoginController;
// import Controller.RescheduleController;
// import Controller.TransactionController;

// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

// public class RescheduleChooseSeat {
//     private JFrame frame;
//     private JPanel seatPanel;
//     private JButton[] seats;
//     private char[] availabilitySeat;
//     private ArrayList<String> selectedSeats;
//     private Flight flight;
//     private Airplane airplane;
//     private int id;
//     private TransactionController tc;
//     private RescheduleController rc;

//     public RescheduleChooseSeat(int idd, Flight flightt, Airplane airplanee) {
//         id = idd;
//         flight = flightt;
//         String seatRow = flight.getSeat_row();
//         seats = new JButton[seatRow.length()];
//         availabilitySeat = new char[seatRow.length()];
//         selectedSeats = new ArrayList<>();

//         for (int i = 0; i < seatRow.length(); i++) {
//             availabilitySeat[i] = seatRow.charAt(i);
//         }
//         airplane = airplanee;
//         initRescheduleChooseSeat(seatRow.length());
//     }

//     void initRescheduleChooseSeat(int length) {
//         frame = new JFrame("Seat Selection");
//         frame.setSize(700, 400);
//         frame.setLocationRelativeTo(null);
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel gradientPanel = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 Graphics2D g2d = (Graphics2D) g;
//                 Color color1 = new Color(0, 102, 204);
//                 Color color2 = new Color(102, 204, 255);
//                 GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
//                 g2d.setPaint(gp);
//                 g2d.fillRect(0, 0, getWidth(), getHeight());
//             }
//         };
//         gradientPanel.setLayout(new BorderLayout());

//         JLabel titleLabel = new JLabel("Bagian Depan", JLabel.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         titleLabel.setForeground(Color.WHITE);
//         gradientPanel.add(titleLabel, BorderLayout.NORTH);

//         seatPanel = new JPanel();
//         seatPanel.setLayout(new GridBagLayout());
//         seatPanel.setOpaque(false);
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(5, 5, 5, 5);

//         for (int i = 0; i < 15; i++) {
//             seats[i] = createSeatButton(Integer.toString(i + 1), availabilitySeat[i] == '0');
//             gbc.gridx = i % 3;
//             gbc.gridy = i / 3;
//             seatPanel.add(seats[i], gbc);
//         }

//         gbc.gridx = 3;
//         gbc.gridy = 0;
//         seatPanel.add(new JLabel("     "), gbc);

//         for (int i = 15; i < length; i++) {
//             seats[i] = createSeatButton(Integer.toString(i + 1), availabilitySeat[i] == '0');
//             gbc.gridx = (i - 15) % 3 + 4;
//             gbc.gridy = (i - 15) / 3;
//             seatPanel.add(seats[i], gbc);
//         }

//         JScrollPane scrollPane = new JScrollPane(seatPanel);
//         scrollPane.setOpaque(false);
//         scrollPane.getViewport().setOpaque(false);
//         gradientPanel.add(scrollPane, BorderLayout.CENTER);

//         frame.add(gradientPanel);
//         frame.setVisible(true);
//     }

//     private JButton createSeatButton(String text, boolean isAvailable) {
//         JButton button = new JButton(text);
//         button.setBackground(isAvailable ? Color.GREEN : Color.RED);
//         button.setFocusPainted(false);
//         button.setEnabled(isAvailable);
        
//         if (isAvailable) {
//             button.addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     int response = JOptionPane.showConfirmDialog(
//                         frame,
//                         "Do you want to select this seat?",
//                         "Confirm Seat Selection",
//                         JOptionPane.YES_NO_OPTION
//                     );
//                     if (response == JOptionPane.YES_OPTION) {
//                         frame.setVisible(false);
//                         rescheduleOrder(button.getText());
//                     }
//                 }
//             });
//         }

//         return button;
//     }

//     public ArrayList<String> getSelectedSeats() {
//         return selectedSeats;
//     }
//     public void rescheduleOrder(String seat) {
//         var user = LoginController.getInstance().getLoggedInUser();
//         frame = new JFrame("Reschedule Form Transaction");
//         frame.setSize(450, 350);
//         frame.setLocationRelativeTo(null);
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel gradientPanel = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 Graphics2D g2d = (Graphics2D) g;
//                 Color color1 = new Color(0, 102, 204);
//                 Color color2 = new Color(102, 204, 255);
//                 GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
//                 g2d.setPaint(gp);
//                 g2d.fillRect(0, 0, getWidth(), getHeight());
//             }
//         };
//         gradientPanel.setLayout(null);

//         JLabel nameLabel = new JLabel("Nama :");
//         nameLabel.setBounds(50, 50, 100, 25);
//         nameLabel.setForeground(Color.WHITE);
//         nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
//         gradientPanel.add(nameLabel);

//         JTextField nameField = new JTextField();
//         nameField.setBounds(150, 50, 200, 25);
//         gradientPanel.add(nameField);

//         JLabel nikLabel = new JLabel("NIK :");
//         nikLabel.setBounds(50, 100, 100, 25);
//         nikLabel.setForeground(Color.WHITE);
//         nikLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
//         gradientPanel.add(nikLabel);

//         JTextField nikField = new JTextField();
//         nikField.setBounds(150, 100, 200, 25);
//         gradientPanel.add(nikField);

//         JLabel emailLabel = new JLabel("Umur:");
//         emailLabel.setBounds(50, 150, 100, 25);
//         emailLabel.setForeground(Color.WHITE);
//         emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
//         gradientPanel.add(emailLabel);

//         JComboBox<Usia> usiaComboBox = new JComboBox<>(Usia.values());
//         usiaComboBox.setBounds(150, 150, 200, 25);
//         gradientPanel.add(usiaComboBox);

//         JButton checkout = createGradientButton("Checkout", new Color(0, 153, 204), new Color(51, 204, 255));
//         checkout.setBounds(50, 250, 140, 40);

//         gradientPanel.add(checkout);  

//         checkout.addActionListener(e -> {
//             if (flight == null || seat == null || nameField == null || usiaComboBox == null || nikField == null) {
//                 JOptionPane.showMessageDialog(null, "Mohon isi semua data diatas");
//             }else{
//                 Transaksi transaksi = new Transaksi();
//                 transaksi.setAge((Usia) usiaComboBox.getSelectedItem());
//                 transaksi.setFlight(flight);
//                 transaksi.setSeat(seat);
//                 transaksi.setName(nameField.getText());
//                 transaksi.setPrice((int) flight.getPrice());
//                 transaksi.setNik(Integer.parseInt(nikField.getText()));
//                 transaksi.setDate_transaksi(new java.sql.Date(System.currentTimeMillis()).toLocalDate());
//                 transaksi.setUser(user);
//                 transaksi.setStatus(StatusPembayaran.PAYLATER);
//                 transaksi.setTicket(ActiveTicket.NONACTIVE);
//                 frame.setVisible(false);
//                 tc = new TransactionController();
//                 int id2 = tc.createReturnedTransaction(transaksi);
//                 JOptionPane.showMessageDialog(frame, "Reschedule successful!, tinggal menunggu admin");
//                 new CustomerMenu();

//                 rc.createTransaction(id, id2);
//             }
            

//         });

//         JButton backButton = createGradientButton("Back", new Color(0, 153, 204), new Color(51, 204, 255));
//         backButton.setBounds(210, 250, 140, 40);
//         backButton.addActionListener(e -> {
//             frame.dispose();
//             new CustomerMenu();
//         });
//         gradientPanel.add(backButton);
//         frame.add(gradientPanel);
//         frame.setVisible(true);
//     }

//     private JButton createGradientButton(String text, Color color1, Color color2) {
//         JButton button = new JButton(text) {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 Graphics2D g2d = (Graphics2D) g;
//                 GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
//                 g2d.setPaint(gp);
//                 g2d.fillRect(0, 0, getWidth(), getHeight());
//                 super.paintComponent(g);
//             }
//         };
//         button.setForeground(Color.WHITE);
//         button.setFont(new Font("SansSerif", Font.BOLD, 14));
//         button.setBorderPainted(false);
//         button.setFocusPainted(false);
//         button.setContentAreaFilled(false);
//         return button;
//     }
// }
