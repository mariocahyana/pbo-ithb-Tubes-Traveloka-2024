package Controller;

import Model.Model_class.Customer;
import Model.Model_class.User;
import View.registerView;
import View.loginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class registerController {
    private List<User> userList;
    private registerView registerView;
    private loginView loginView;

    public registerController(List<User> userList, registerView registerView, loginView loginView) {
        this.userList = userList;
        this.registerView = registerView;
        this.loginView = loginView;

        registerView.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleRegister() {
        String name = registerView.getName();
        String password = registerView.getPassword();
        String email = registerView.getEmail();
        String phoneNumber = registerView.getPhoneNumber();

        String customerID = "C" + (userList.size() + 1);

        for (User user : userList) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                if (customer.getEmail().equals(email)) {
                    registerView.displayMessage("Email sudah terdaftar.");
                    return;
                }
            }
        }

        Customer newCustomer = new Customer(customerID, name, password, email, phoneNumber, 0.0);
        userList.add(newCustomer);
        registerView.displayMessage("Registration berhasilll!");

        System.out.println("List user after regis:");
        for (User user : userList) {
            if (user instanceof Customer) {
                System.out.println(((Customer) user).getEmail());
            }
        }

        registerView.setVisible(false);
        loginView.setVisible(true);
    }
}
