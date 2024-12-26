package Model.Model_class;

public class Active_Ticket {

    private String name;
    private String nik;
    private String age;
    private String originCity;
    private String destinationCity;

    public Active_Ticket (String name, String nik, String age, String originCity, String destinationCity) {
        this.name = name;
        this.nik = nik;
        this.age = age;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }
    public Active_Ticket () {
      
    }

    public String getName() {
        return name;
    }

    public String getNik() {
        return nik;
    }

    public String getAge() {
        return age;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }





@Override
    public String toString() {
        return "Active_Ticket {" +
                "name='" + name + '\'' +
                ", nik='" + nik + '\'' +
                ", age=" + age +
                ", originCity='" + originCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                '}';
    }
}
