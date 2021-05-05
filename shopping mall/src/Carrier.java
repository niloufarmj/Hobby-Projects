import java.util.ArrayList;

public class Carrier extends User{

    static class Pair {
        Product product;
        Client client;

        public Pair(Product product, Client client) {
            this.product = product;
            this.client = client;
        }
    }
    enum Vehicle {
        motorCycle , pickup , car
    }
    static Vehicle vehicle;
    double volume = 0;
    ArrayList<Pair> sending = new ArrayList<Pair>();
    ArrayList<Pair> sent = new ArrayList<Pair>();

    public Carrier(String username , String password , Vehicle carType) {
        super(username, password);
        this.vehicle = carType;
        if (carType.equals(Vehicle.motorCycle))
            volume = 0.5;
        else if (carType.equals(Vehicle.pickup))
            volume = 12;
        else if (carType.equals(Vehicle.car))
            volume = 2;
    }

    public void change(Vehicle type) {
        this.vehicle = type;
        if (type.equals(Vehicle.motorCycle))
            volume = 0.5;
        else if (type.equals(Vehicle.pickup))
            volume = 12;
        else if (type.equals(Vehicle.car))
            volume = 2;
    }
}
