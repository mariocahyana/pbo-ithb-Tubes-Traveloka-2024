package Model_enum;

public enum Usia {
    BAYI(0), ANAK(1), DEWASA(2), LANSIA(3);

    private int value;

    Usia(int value) {
        this.value = value;
    }

    public int getUsia(){
        return value;
    }
}
