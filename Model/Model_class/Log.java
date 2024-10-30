package Model_class;

import Model_enum.*;

public class Log {
    private int idLog;
	private Status status;
	private Flight flight;
    public int getIdLog() {
        return idLog;
    }
    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
