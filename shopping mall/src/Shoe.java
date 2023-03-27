public class Shoe extends Wearing {

    int size;

    public Shoe(ProductType type, double volume, String companyName, double price, Producer producer, Gender gender, String material, int size) {
        super(type, volume, companyName, price, producer, gender, material);
        this.size = size;
    }
}
