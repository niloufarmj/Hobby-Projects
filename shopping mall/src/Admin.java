import java.util.ArrayList;

public class Admin extends User {


    public ArrayList<Producer> producers = new ArrayList<Producer>();
    public ArrayList<Carrier> carriers = new ArrayList<Carrier>();
    double off;
    public Admin(String username , String password) {
        super(username , password);
        off = 0;
    }

    public void makeCarrier(String username, String password , Carrier.Vehicle type) {
        carriers.add(new Carrier(username, password , type));
        System.out.println("carrier " + username + " was added successfully");
    }

    public void makeProducer(String username, String password) {
        producers.add(new Producer(username, password));
        System.out.println("producer " + username + " was added successfully");
    }
}
