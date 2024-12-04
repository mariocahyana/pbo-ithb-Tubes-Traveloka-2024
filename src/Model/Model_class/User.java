package Model.Model_class;

abstract class User {
    private String nama, password;

    public User() {
    }

    public User(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =password;
   }
}
