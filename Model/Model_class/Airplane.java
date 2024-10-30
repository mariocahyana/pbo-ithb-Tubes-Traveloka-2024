package Model_class;

public class Airplane {
    String name, idPlane;
    int capacity;
    
    public Airplane(String name, String idPlane, int capacity) {
        this.name = name;
        this.idPlane = idPlane;
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
