    package View;

    import javax.swing.*;

    import Controller.FlightController;

    import java.awt.*;
    import java.awt.event.*;
    import Model.Model_class.Flight;
    import Model.Model_enum.FlightClass;

    public class InputFlightData {
        private JFrame frame;
        private FlightController controller;

        private JButton createGradientButton(String text, int xPosition, int yPosition, Color color1, Color color2) {
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
            button.setBounds(xPosition, yPosition, 95, 45);
            button.setFont(new Font("SansSerif", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            return button;
        }

        public InputFlightData(int actionValue, Flight flights) {
            controller = new FlightController();
            showInputFlightData(actionValue, flights);
        }

        public void showInputFlightData(int actionValue, Flight flights) {
            frame = new JFrame("INPUT FLIGHT DATA");
            frame.setSize(425, 470);
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
            gradientPanel.setLayout(null);

            JLabel flightLabel = new JLabel("Flight name:");
            flightLabel.setBounds(30, 10, 150, 25);
            flightLabel.setForeground(Color.WHITE);
            flightLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(flightLabel);

            JTextField flightField = new JTextField();
            flightField.setBounds(200, 10, 180, 25);
            gradientPanel.add(flightField);

            JLabel airplaneLabel = new JLabel("Airplane name:");
            airplaneLabel.setBounds(30, 50, 150, 25);
            airplaneLabel.setForeground(Color.WHITE);
            airplaneLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(airplaneLabel);

            JTextField airplaneField = new JTextField();
            airplaneField.setBounds(200, 50, 180, 25);
            gradientPanel.add(airplaneField);

            JLabel originCityLabel = new JLabel("Origin City name:");
            originCityLabel.setBounds(30, 90, 150, 25);
            originCityLabel.setForeground(Color.WHITE);
            originCityLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(originCityLabel);

            JTextField originCityField = new JTextField();
            originCityField.setBounds(200, 90, 180, 25);
            gradientPanel.add(originCityField);

            JLabel destinationCityLabel = new JLabel("Destination City Name:");
            destinationCityLabel.setBounds(30, 130, 200, 25);
            destinationCityLabel.setForeground(Color.WHITE);
            destinationCityLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(destinationCityLabel);

            JTextField destinationCityField = new JTextField();
            destinationCityField.setBounds(200, 130, 180, 25);
            gradientPanel.add(destinationCityField);

            JLabel ticketCountLabel = new JLabel("Ticket Count:");
            ticketCountLabel.setBounds(30, 170, 150, 25);
            ticketCountLabel.setForeground(Color.WHITE);
            ticketCountLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(ticketCountLabel);

            JTextField ticketCountField = new JTextField();
            ticketCountField.setBounds(200, 170, 180, 25);
            gradientPanel.add(ticketCountField);

            JLabel departureTimeLabel = new JLabel("Departure Time:");
            departureTimeLabel.setBounds(30, 210, 150, 25);
            departureTimeLabel.setForeground(Color.WHITE);
            departureTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(departureTimeLabel);

            JTextField departureTimeField = new JTextField();
            departureTimeField.setBounds(200, 210, 180, 25);
            gradientPanel.add(departureTimeField);

            JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
            arrivalTimeLabel.setBounds(30, 250, 150, 25);
            arrivalTimeLabel.setForeground(Color.WHITE);
            arrivalTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(arrivalTimeLabel);

            JTextField arrivalTimeField = new JTextField();
            arrivalTimeField.setBounds(200, 250, 180, 25);
            gradientPanel.add(arrivalTimeField);

            JLabel priceLabel = new JLabel("Price:");
            priceLabel.setBounds(30, 290, 150, 25);
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(priceLabel);

            JTextField priceField = new JTextField();
            priceField.setBounds(200, 290, 180, 25);
            gradientPanel.add(priceField);

            JLabel ticketClassLabel = new JLabel("Ticket Class:");
            ticketClassLabel.setBounds(30, 330, 150, 25);
            ticketClassLabel.setForeground(Color.WHITE);
            ticketClassLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            gradientPanel.add(ticketClassLabel);

            // MAKE COMBO BOX
            JComboBox<String> ticketClassComboBox = new JComboBox<>();
            ticketClassComboBox.addItem("ECONOMY");
            ticketClassComboBox.addItem("BUSINESS");
            ticketClassComboBox.addItem("FIRST CLASS");

            ticketClassComboBox.setBounds(200, 330, 180, 25);
            gradientPanel.add(ticketClassComboBox);

            JButton update = createGradientButton("UPDATE", 30, 375, new Color(51, 204, 255), new Color(0, 153, 204));
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String flightName = flightField.getText().trim();
                    String airplaneName = airplaneField.getText().trim();
                    String originCityName = originCityField.getText().trim();
                    String destinationCityName = destinationCityField.getText().trim();
                    String ticketCountName = ticketCountField.getText().trim();
                    String departureTime = departureTimeField.getText().trim();
                    String arrivalTime = arrivalTimeField.getText().trim();
                    String price = priceField.getText().trim();
                    FlightClass tiket = controller.getFlightClass(String.valueOf(ticketClassComboBox.getSelectedItem()));
                    
                    if (flightName.isEmpty() || airplaneName.isEmpty() || originCityName.isEmpty() ||
                            destinationCityName.isEmpty() || ticketCountName.isEmpty() || departureTime.isEmpty() ||
                            arrivalTime.isEmpty() || price.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "All of the field must be filled");
                        if (originCityName == destinationCityName) {
                            JOptionPane.showMessageDialog(frame, "Name of origin and destination can't same");
                        }
                    }

                    int ticketCount = Integer.parseInt(ticketCountName);
                    Double harga = Double.parseDouble(price);

                    flights.setFlightName(flightName);
                    flights.setAirplaneName(airplaneName);
                    flights.setOriginCity(originCityName);
                    flights.setDestinationCity(destinationCityName);
                    flights.setCountTicket(ticketCount);
                    flights.setDepartureTime(departureTime);
                    flights.setArrivalTime(arrivalTime);
                    flights.setPrice(harga);
                    flights.setFlightClass(tiket);
                    flights.setSeat_row("0".repeat(ticketCount));
                    
                    boolean updateSuccess = controller.updateData(flights);

                    if (updateSuccess) {
                        JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui.", "Sukses",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new FlightData(flights);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JButton delete = createGradientButton("DELETE", 132, 375, new Color(0, 153, 204), new Color(51, 204, 255));
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nama = flightField.getText();
                    int option = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin menghapus data ?",
                            "Konfirmasi Penghapusan", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        frame.dispose();
                        boolean deleteSuccess = controller.deleteData(nama);
                        if (deleteSuccess) {
                            JOptionPane.showMessageDialog(frame, "Data dengan Nama " + nama + " berhasil dihapus.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Gagal menghapus data dengan Nama " + nama + ".");
                        }
                        new FlightData(null);
                    }
                }
            });

            JButton back = createGradientButton("BACK", 284, 375, new Color(51, 204, 255), new Color(0, 153, 204));
            back.addActionListener(e -> {
                frame.dispose();
                new FlightData(flights);
            });
            JButton submit = createGradientButton("SUBMIT", 180, 375, new Color(0, 153, 204), new Color(51, 204, 255));
            submit.addActionListener(e -> {
                String flightName = flightField.getText().trim();
                String airplaneName = airplaneField.getText().trim();
                String originCityName = originCityField.getText().trim();
                String destinationCityName = destinationCityField.getText().trim();
                String ticketCountName = ticketCountField.getText().trim();
                String departureTime = departureTimeField.getText().trim();
                String arrivalTime = arrivalTimeField.getText().trim();
                String price = priceField.getText().trim();
                FlightClass tiket = controller.getFlightClass(String.valueOf(ticketClassComboBox.getSelectedItem()));

                if (flightName.isEmpty() || airplaneName.isEmpty() || originCityName.isEmpty() ||
                        destinationCityName.isEmpty() || ticketCountName.isEmpty() || departureTime.isEmpty() ||
                        arrivalTime.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All of the field must be filled");
                    if (originCityName == destinationCityName) {
                        JOptionPane.showMessageDialog(frame, "Name of origin and destination can't same");
                    }
                }

                int ticketCount = Integer.parseInt(ticketCountName);
                Double harga = Double.parseDouble(price);
                Flight flight = new Flight(0, flightName, null, ticketCount, null, null, airplaneName, originCityName,
                        destinationCityName, departureTime, arrivalTime, tiket, harga, null);
                String result = controller.add(flight);
                switch (result) {
                    case "SUCCESS":
                        JOptionPane.showMessageDialog(frame, "Add flight successful!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new FlightData(flight);
                        break;
                    case "NAME_EXISTS":
                        JOptionPane.showMessageDialog(frame,
                                "Name of airplane already in use. Please use a different name.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case "CITYOFORIGIN_NOT_FOUND":
                        JOptionPane.showMessageDialog(frame,
                                "City of origin already in use. Please use a different name.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case "CITYOFDESTINATION_NOT_FOUND":
                        JOptionPane.showMessageDialog(frame,
                                "City of destination is not found. Please check again the airport name.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case "AIRPLANE_NOT_FOUND":
                        JOptionPane.showMessageDialog(frame, "Airplane is not found. Please check again the airport name.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Add flight failed. Try again.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
            });

            gradientPanel.add(back);
            gradientPanel.add(update);
            gradientPanel.add(delete);
            gradientPanel.add(submit);

            if (actionValue == 1) {
                back.setVisible(true);
                update.setVisible(false);
                delete.setVisible(false);
                submit.setVisible(true);
            } else {
                back.setVisible(true);
                update.setVisible(true);
                delete.setVisible(true);
                submit.setVisible(false);

                flightField.setText(flights.getFlightName());
                airplaneField.setText(flights.getAirplaneName());
                originCityField.setText(flights.getOriginCity());
                destinationCityField.setText(flights.getDestinationCity());
                ticketCountField.setText(String.valueOf(flights.getCountTicket()));
                departureTimeField.setText(flights.getDepartureTime());
                arrivalTimeField.setText(flights.getArrivalTime());
                priceField.setText(String.valueOf(flights.getPrice()));
                ticketClassComboBox.setSelectedItem(flights.getFlightClass().toString());
            }

            frame.add(gradientPanel);
            frame.setVisible(true);
        }
    }
