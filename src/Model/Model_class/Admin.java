package Model.Model_class;

public class Admin extends User {
    private String adminID;

    public Admin() {
    }

    public Admin(String adminID, String nama, String password) {
        super(nama, password);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
