import java.util.ArrayList;

public class Producer extends User{

    static class Pair {
        Product product;
        Client client;

        public Pair(Product product, Client client) {
            this.product = product;
            this.client = client;
        }
    }

    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Pair> sold = new ArrayList<Pair>();
    double money;

    public Producer(String username , String password) {
        super(username, password);
        this.money = 0;
    }

    public void addProduct (Product p) {
        products.add(p);
    }

    public void deleteProduct (int index) {
        products.remove(index);
    }
}
