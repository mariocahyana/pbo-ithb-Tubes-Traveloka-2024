package Model_enum;

public enum Status {
    SUCCESS(0), FAILED(1);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getStatus(){
        return value;
    }
}
