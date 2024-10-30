package Model_enum;

public enum FlightClass {
    ECONOMY(0), BUSINESS(1), FIRSTCLASS(2);

    private int value;

    FlightClass(int value) {
        this.value = value;
    }

    public int getFlightClass() {
        return value;
    }
}
