public class Device extends Product {

    int productionYear;

    public Device(ProductType type, double volume, String companyName, double price , Producer producer , int productionYear) {
        super(type, volume, companyName, price , producer);
        this.productionYear = productionYear;
    }
}
