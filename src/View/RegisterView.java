package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView {
    private JFrame frame;

    public RegisterView(){
        showRegisterView();
    }

    public void showRegisterView(){
        frame = new JFrame("Traveloka App Pesawat");
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
