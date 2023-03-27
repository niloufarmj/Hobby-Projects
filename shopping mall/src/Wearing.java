public class Wearing extends Product {

    enum Gender {
        Men , Women , Children
    }
    Gender gender;
    String material;

    public Wearing(ProductType type, double volume, String companyName, double price , Producer producer, Gender gender , String material) {
        super(type, volume, companyName, price, producer);
    }

}
