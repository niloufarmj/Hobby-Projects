public class Display extends Device {

    enum displayType {
        cellphone , tv
    }
    displayType type;
    double screenSize;

    public Display(ProductType type, double volume, String companyName, double price, Producer producer, int productionYear, displayType typed, double screenSize) {
        super(type, volume, companyName, price, producer, productionYear);
        this.screenSize = screenSize;
        this.type = typed;
    }
}
