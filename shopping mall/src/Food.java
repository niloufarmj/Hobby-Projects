public class Food extends Product {
    enum foodType {
        junk , dairy , veg
    }
    foodType type;
    String expirationDate;

    public Food(ProductType type, double volume, String companyName, double price, Producer producer, foodType typef, String expirationDate) {
        super(type, volume, companyName, price , producer);
        this.type = typef;
        this.expirationDate = expirationDate;
    }
}
