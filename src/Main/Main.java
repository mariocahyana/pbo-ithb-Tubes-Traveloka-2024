package Main;

import Controller.LoginController;
import Model.Model_class.Admin;
import Model.Model_class.Customer;
import Model.Model_class.User;
import View.LoginView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new Admin("A001", "mar", "marioYOW"));
        userList.add(new Customer("C001", "derel", "derelYOW", "derel@gmail.com", "08123456789", 0));
        userList.add(new Customer("C002", "paul", "paulYOW", "paul@gmail.com", "08123456788", 0));

        LoginView loginView = new LoginView();

        new LoginController(userList, loginView);

        loginView.setVisible(true);
    }
}
