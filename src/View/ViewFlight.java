package View;

import javax.swing.*;
import Controller.AirlineController;
import Controller.AirplaneController;
import Controller.FlightController;
import Model.Model_class.Flight;
import Model.Model_class.Airline;
import Model.Model_class.Airplane;
import java.awt.*;
import java.util.List;

public class ViewFlight {
    public ViewFlight(List<Flight> flightt) {
        flight = flightt;
        initViewFlight();
    }

    private List<Flight> flight;
    JFrame frame;
    public void initViewFlight() {
        FlightController fC = new FlightController();

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

        frame = new JFrame("Flight View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JPanel panelTitle = new JPanel();
        JLabel label = new JLabel("Traveloka Flight View");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        panelTitle.add(label);
        frame.add(panelTitle, BorderLayout.NORTH);

        JPanel flightPanel = new JPanel(){
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
        flightPanel.setLayout(null);
        flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.Y_AXIS));

        if (flight == null) {
             flight = fC.getFlightData();
        }else{
            
        }
        for (Flight flight : flight) {
            JPanel flightCard = createFlightCard(flight);
            flightPanel.add(flightCard, BorderLayout.CENTER);
        }

        JScrollPane scrollPane = new JScrollPane(flightPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton = createButton("Search");
        
        JButton backButton = createButton("Back");
        backButton.addActionListener(e -> {
            frame.setVisible(false);
            new CustomerMenu();
        });
        bottomPanel.add(backButton);
        
        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Search button clicked.");
            new SearchFlight();
        });

        bottomPanel.add(searchButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createFlightCard(Flight flight) {
        AirlineController airlineC = new AirlineController();
        AirplaneController airplaneC = new AirplaneController();
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(5, 2)); // 5 baris untuk setiap atribut
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        card.setBackground(Color.decode("#87CEEB"));

        Airplane airplane = airplaneC.getAirplane(flight.getAirplane().getAirplaneID());
        Airline airline = airlineC.getAirline(airplane.getAirline().getAirlineID());

        card.add(new JLabel("            " + airline.getName()) {{
            setFont(new Font("Arial", Font.BOLD, 16));
        }});
        card.add(new JLabel(airplaneC.getAirplane(airplane.getAirplaneID()).getAirplaneName()) {{
            setForeground(Color.decode("#333333")); // Menetapkan warna font
        }});
        card.add(new JLabel("Departure Time : " + flight.getDepartureTime()) {{
            setForeground(Color.decode("#333333")); 
        }});
        card.add(new JLabel("Arrival Time: " + flight.getArrivalTime()) {{
            setForeground(Color.decode("#333333")); // Menetapkan warna font
        }});
        card.add(new JLabel("Origin: " + flight.getOriginCity()) {{
            setForeground(Color.decode("#333333")); // Menetapkan warna font
        }});
        card.add(new JLabel("Destination: " + flight.getDestinationCity()) {{
            setForeground(Color.decode("#333333")); // Menetapkan warna font
        }});
        card.add(new JLabel("Price: " + flight.getPrice()) {{
            setForeground(Color.decode("#333333")); // Menetapkan warna font
        }});

        JPanel paddedCard = new JPanel(new BorderLayout());
        paddedCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Padding kiri dan kanan
        paddedCard.add(card, BorderLayout.CENTER);
        Dimension paddedCardSize = new Dimension(500, 130);
        paddedCard.setPreferredSize(paddedCardSize);
        paddedCard.setMinimumSize(paddedCardSize);
        paddedCard.setMaximumSize(paddedCardSize);
        paddedCard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String details = String.format(
                    "Flight Details:\n" +
                    "Airline: %s\n" +
                    "Airplane: %s\n" +
                    "Departure Time: %s\n" +
                    "Arrival Time: %s\n" +
                    "Origin: %s\n" +
                    "Destination: %s\n" +
                    "Price: %.2f",
                    airline.getName(),
                    airplane.getAirplaneName(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getOriginCity(),
                    flight.getDestinationCity(),
                    flight.getPrice()
                );
                // Menampilkan dialog konfirmasi
                int response = JOptionPane.showConfirmDialog(null, details, "Apakah Benar ini pesanan anda? ", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                // Menangani pilihan Ya atau Tidak
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Lanjut Memilih Kursi");
                    frame.setVisible(false);
                    new ChooseSeat(flight,airplane);
                    
                } else if (response == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "You selected 'No'. Cancelling the flight.");
                    frame.setVisible(false);
                    new CustomerMenu();
                }
            }
        });
        return paddedCard;
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
