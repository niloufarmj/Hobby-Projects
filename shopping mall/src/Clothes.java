public class Clothes extends Wearing {

    enum ClothesType {
        Trousers, Dress
    }
    String size;
    ClothesType type;

    public Clothes(ProductType type, double volume, String companyName, double price, Producer producer, Gender gender, String material, String size , ClothesType typec) {
        super(type, volume, companyName, price, producer, gender, material);
        this.size = size;
        this.type = typec;
    }
}
