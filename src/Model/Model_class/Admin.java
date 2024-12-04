package Model.Model_class;

public class Admin extends User{
    private int adminId;
	public Admin() {
    }
    public Admin(String nama, String password, int adminId) {
        super(nama, password);
        this.adminId = adminId;
    }
    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
} 

