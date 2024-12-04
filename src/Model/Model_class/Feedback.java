package Model.Model_class;

import java.util.Date;

public class Feedback {
    private String feedbackID, customer_review;
    private Customer customer;
    private Date date_review;
    public Feedback() {
    }
    public Feedback(String feedbackID, String customer_review, Customer customer, Date date_review) {
        this.feedbackID = feedbackID;
        this.customer_review = customer_review;
        this.customer = customer;
        this.date_review = date_review;
    }
    public String getFeedbackID() {
        return feedbackID;
    }
    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }
    public String getCustomer_review() {
        return customer_review;
    }
    public void setCustomer_review(String customer_review) {
        this.customer_review = customer_review;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Date getDate_review() {
        return date_review;
    }
    public void setDate_review(Date date_review) {
        this.date_review = date_review;
    }
	
}