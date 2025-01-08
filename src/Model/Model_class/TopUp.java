package Model.Model_class;

import java.time.LocalDate;
import Model.Model_enum.TopUpStatus;

public class TopUp {
    private int requestID;
    private int userID;
    private double amount;
    private TopUpStatus status;
    private LocalDate requestDate;

    public TopUp(int requestID, int userID, double amount, TopUpStatus status, LocalDate requestDate) {
        this.requestID = requestID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
        this.requestDate = requestDate;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TopUpStatus getStatus() {
        return status;
    }

    public void setStatus(TopUpStatus status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
