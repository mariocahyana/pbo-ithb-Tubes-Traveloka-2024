package Controller;

import Model.Model_class.Admin;
import Model.Model_class.Customer;
import Model.Model_class.User;
import View.loginView;
import View.registerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class loginController {
    private List<User> userList;
    private loginView loginView;

    public loginController(List<User> userList, loginView loginView) {
        this.userList = userList;
        this.loginView = loginView;

        loginView.addRoleSelectionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRoleSelection();
            }
        });

        loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        loginView.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleRoleSelection() {
        String selectedRole = loginView.getSelectedRole();
        loginView.toggleRegisterButton("User".equals(selectedRole));
    }

    private void handleLogin() {
        String role = loginView.getSelectedRole();
        String input = loginView.getInput().trim();
        String password = loginView.getPassword().trim();

        System.out.println("Tes Valid data. User:");
        for (User user : userList) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                System.out.println(((Customer) user).getEmail());
                System.out.println("pass input: " + password + " pass simpan: " + customer.getPassword());
                if (customer.getEmail().equals(input) && customer.getPassword().equals(password)) {
                    loginView.displayMessage("Welcomeeeee User!");
                    return;
                }
            }
        } 

        if ("Admin".equals(role)) {
            for (User user : userList) {
                if (user instanceof Admin && user.getNama().equals(input) && user.getPassword().equals(password)) {
                    loginView.displayMessage("Welcome Admin!");
                    return;
                }
            }
            loginView.displayMessage("Input data salah.");
        } else {
            for (User user : userList) {
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;
                    if (customer.getEmail().equals(input) && customer.getPassword().equals(password)) {
                        loginView.displayMessage("Welcome Userrrr!");
                        return;
                    }
                }
            }
            loginView.displayMessage("Input data salah.");
        }
    }

    private void handleRegister() {
        loginView.setVisible(false);
        registerView registerView = new registerView();

        registerView.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                registerView.displayMessage("Registrasi Berhasil!");

                registerView.setVisible(false);
                loginView.setVisible(true);
            }
        });

        registerView.setVisible(true);
    }
}
