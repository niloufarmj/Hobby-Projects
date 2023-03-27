public class Product {
    enum ProductType {
        Food , Wearing , Device
    }
    ProductType type;
    double volume;
    String companyName;
    double price;
    Producer producer;

    public Product(ProductType type , double volume , String companyName , double price , Producer producer) {
        this.type = type;
        this.volume = volume;
        this.companyName = companyName;
        this.price = price;
        this.producer = producer;
    }


}
