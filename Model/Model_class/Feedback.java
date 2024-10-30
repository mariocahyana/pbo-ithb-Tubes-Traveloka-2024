package Model_class;

public class Feedback {
    private String ulasan;
	private Customer customer;
    public String getUlasan() {
        return ulasan;
    }
    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
