package Model.Model_class;

import java.util.Date;

public class Log {
    private String logID;
	private Date date_purchase;
    public Log() {
    }
    public Log(String logID, Date date_purchase) {
        this.logID = logID;
        this.date_purchase = date_purchase;
    }
    public String getLogID() {
        return logID;
    }
    public void setLogID(String logID) {
        this.logID = logID;
    }
    public Date getDate_purchase() {
        return date_purchase;
    }
    public void setDate_purchase(Date date_purchase) {
        this.date_purchase = date_purchase;
    }
}
