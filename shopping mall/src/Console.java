import java.util.ArrayList;

public class Console extends Device {

    ArrayList<String> extensions = new ArrayList<>();

    public Console(ProductType type, double volume, String companyName, double price, Producer producer, int productionYear, ArrayList<String> extensions) {
        super(type, volume, companyName, price, producer , productionYear);
        this.extensions = extensions;
    }
}
