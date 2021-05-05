import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Client extends User{

    int spentMoney;

    ArrayList<Product> sendingQueue = new ArrayList<Product>();
    ArrayList<Product> received = new ArrayList<Product>();

    public Client(String username , String password) {
        super(username, password);
        spentMoney = 0;
    }

}
