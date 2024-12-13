package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JFrame frame;

    public MainMenu(){
        showMainMenu();
    }

    public void showMainMenu() {

        frame = new JFrame("Traveloka App Pesawat");
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 500);

        JLabel title1 = new JLabel("TRAVEL APP");
        title1.setSize(400, 100);
        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title1.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(title1);

        JLabel title2 = new JLabel("PESAWAT");
        title2.setSize(400, 150);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(title2);

        JButton login = new JButton("LOGIN");
        login.setBounds(70, 130, 260, 50);
        login.setFont(new Font("SansSerif", Font.BOLD, 15));
        login.setFocusable(false);
        login.addActionListener(e -> {
            frame.dispose();
            new LoginView();
        });
        panel.add(login);

        JButton register = new JButton("REGISTER");
        register.setBounds(70, 200, 260, 50);
        register.setFont(new Font("SansSerif", Font.BOLD, 15));
        register.setFocusable(false);
        panel.add(register);

        register.addActionListener(e -> {
            frame.dispose();
            new RegisterView();
        });

        JButton exit = new JButton("EXIT");
        exit.setBounds(70, 270, 260, 50);
        exit.setFont(new Font("SansSerif", Font.BOLD, 15));
        exit.setFocusable(false);
        panel.add(exit);

        exit.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
