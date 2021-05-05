import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Admin admin = new Admin(null, null);
    static ArrayList<Client> clients = new ArrayList<Client>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("welcome to this shopping mall");
        while (true) {
            if (admin.username == null)
                System.out.println("Enter 1 to sign up as admin");
            else
                System.out.println("Enter 1 to log in as admin");
            if (admin.off != 0)
                System.out.println("All products " + admin.off + "% off!");
            System.out.println("Enter 2 to log in as producer");
            System.out.println("Enter 3 to log in as carrier");
            System.out.println("Enter 4 to sign up as client");
            System.out.println("Enter 5 to log in as client");
            System.out.println("Enter 6 to exit the program");
            String cmd = sc.next();
            if (cmd.equals("6"))
                break;
            switch (cmd) {
                case "1":
                    //admin kamel shd
                    if (admin.username == null) {
                        System.out.println("Enter your username");
                        admin.setUsername(sc.next());
                        System.out.println("Enter your username");
                        admin.setPassword(sc.next());
                        System.out.println("Your username and password was set successfully");
                        while (true) {
                            System.out.println("Enter 1 if you want to continue as admin");
                            System.out.println("Enter 2 if you want to log out of admin mode");
                            cmd = sc.next();
                            if (cmd.equals("1")) {
                                System.out.println("Enter 1 to add producer");
                                System.out.println("Enter 2 to add carrier");
                                System.out.println("Enter 3 to see producers' list");
                                System.out.println("Enter 4 to see carriers' list");
                                System.out.println("Enter 5 if you add off to all products");
                                cmd = sc.next();
                                switch (cmd) {
                                    case "1":
                                        System.out.println("Enter a new producer's username and password");
                                        admin.makeProducer(sc.next(), sc.next());
                                        break;
                                    case "2":
                                        System.out.println("Enter a new carrier's username and password");
                                        String user = sc.next();
                                        String pass = sc.next();
                                        System.out.println("Enter 1 to set motorCycle");
                                        System.out.println("Enter 2 to set pickup");
                                        System.out.println("Enter 3 to set car");
                                        cmd = sc.next();
                                        Carrier.Vehicle type = null;
                                        if (cmd.equals("1"))
                                            type = Carrier.vehicle.motorCycle;
                                        else if (cmd.equals("2"))
                                            type = Carrier.vehicle.pickup;
                                        else if (cmd.equals("3"))
                                            type = Carrier.vehicle.car;
                                        admin.makeCarrier(user, pass, type);
                                        break;
                                    case "3":
                                        for (int i = 0; i < admin.producers.size(); i++)
                                            System.out.println(i + 1 + ". " + admin.producers.get(i).username);
                                        System.out.println("Enter producer's index to see and edit information");
                                        System.out.println("Enter -1 if you want to delete a producer. Then enter producer's index");
                                        System.out.println("Enter 0 if you want to exit here");
                                        int index = sc.nextInt();
                                        if (index == -1) {
                                            index = sc.nextInt();
                                            admin.producers.remove(index - 1);
                                            System.out.println("The producer was removed successfully");
                                        }
                                        else if (index != 0) {
                                            Producer p = admin.producers.get(index - 1);
                                            System.out.println(p.username + ":");
                                            System.out.println("This producer has " + p.money + " Tomans");
                                            for (int i = 0; i < p.products.size(); i++) {
                                                Product pt = p.products.get(i);
                                                if (pt instanceof Food)
                                                    System.out.println(i + 1 + ". Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                                if (pt instanceof Wearing) {
                                                    if (pt instanceof Clothes)
                                                        System.out.println(i + 1 + ". Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                                    if (pt instanceof Shoe)
                                                        System.out.println(i + 1 + ". Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                                }
                                                if (pt instanceof Device) {
                                                    if (pt instanceof Console) {
                                                        String extensions = "";
                                                        for (String s : ((Console) pt).extensions)
                                                            extensions += s + "/";
                                                        System.out.println(i + 1 + ". Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                                    }
                                                    if (pt instanceof Display)
                                                        System.out.println(i + 1 + ". Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                                }
                                            }
                                        }
                                        break;
                                    case "4":
                                        for (int i = 0; i < admin.carriers.size(); i++)
                                            System.out.println(i+1 + ". " + admin.carriers.get(i).username);
                                        System.out.println("Enter carrier's index to see and edit information");
                                        System.out.println("Enter -1 if you want to delete a carrier. Then enter carrier's index");
                                        System.out.println("Enter 0 if you want to exit here");
                                        index = sc.nextInt();
                                        if (index == -1) {
                                            index = sc.nextInt();
                                            admin.carriers.remove(index - 1);
                                            System.out.println("The carrier was removed successfully");
                                        }
                                        else if (index != 0) {
                                            System.out.println(admin.carriers.get(index - 1).username + ":");
                                            System.out.println("vehicle: " + admin.carriers.get(index - 1).vehicle);
                                            System.out.println("sending list:");
                                            for (int i = 0; i < admin.carriers.get(index - 1).sending.size(); i++) {
                                                Product pt = admin.carriers.get(index - 1).sending.get(i).product;
                                                String cl = admin.carriers.get(index - 1).sending.get(i).client.username;
                                                System.out.print(i + 1 + ". ");
                                                if (pt instanceof Food)
                                                    System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                else if (pt instanceof Wearing) {
                                                    if (pt instanceof Clothes)
                                                        System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                    if (pt instanceof Shoe)
                                                        System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                }
                                                else if (pt instanceof Device) {
                                                    if (pt instanceof Console) {
                                                        System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                    }
                                                    if (pt instanceof Display)
                                                        System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                }
                                            }
                                            System.out.println("sent list:");
                                            for (int i = 0; i < admin.carriers.get(index - 1).sent.size(); i++) {
                                                Product pt = admin.carriers.get(index - 1).sent.get(i).product;
                                                System.out.print(i + 1 + ". ");
                                                if (pt instanceof Food)
                                                    System.out.print("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                else if (pt instanceof Wearing) {
                                                    if (pt instanceof Clothes)
                                                        System.out.print("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                    if (pt instanceof Shoe)
                                                        System.out.print("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                }
                                                else if (pt instanceof Device) {
                                                    if (pt instanceof Console) {
                                                        System.out.print("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                    }
                                                    if (pt instanceof Display)
                                                        System.out.print("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                }
                                                System.out.println(" was sent to client " + admin.carriers.get(index - 1).sent.get(i).client.username);
                                            }
                                        }
                                        break;
                                    case "5":
                                        System.out.println("How many percents off?");
                                        double off = sc.nextDouble();
                                        for (Producer producer : admin.producers) {
                                            for (Product product : producer.products)
                                                product.price -= product.price*(off/100);
                                        }
                                }
                            } else
                                break;
                        }
                    } else {
                        System.out.println("Enter your username");
                        if (sc.next().equals(admin.username)) {
                            System.out.println("Enter your password");
                            if (!sc.next().equals(admin.getPassword()))
                                System.out.println("Wrong password");
                            else {
                                while (true) {
                                    System.out.println("Enter 1 if you want to continue as admin");
                                    System.out.println("Enter 2 if you want to log out of admin mode");
                                    cmd = sc.next();
                                    if (cmd.equals("1")) {
                                        System.out.println("Enter 1 to add producer");
                                        System.out.println("Enter 2 to add carrier");
                                        System.out.println("Enter 3 to see producers' list");
                                        System.out.println("Enter 4 to see carriers' list");
                                        cmd = sc.next();
                                        switch (cmd) {
                                            case "1":
                                                System.out.println("Enter a new producer's username and password");
                                                admin.makeProducer(sc.next(), sc.next());
                                                break;
                                            case "2":
                                                System.out.println("Enter a new carrier's username and password");
                                                String user = sc.next();
                                                String pass = sc.next();
                                                System.out.println("Enter 1 to set motorCycle");
                                                System.out.println("Enter 2 to set pickup");
                                                System.out.println("Enter 3 to set car");
                                                cmd = sc.next();
                                                Carrier.Vehicle type = null;
                                                if (cmd.equals("1"))
                                                    type = Carrier.vehicle.motorCycle;
                                                else if (cmd.equals("2"))
                                                    type = Carrier.vehicle.pickup;
                                                else if (cmd.equals("3"))
                                                    type = Carrier.vehicle.car;
                                                admin.makeCarrier(user, pass, type);
                                                break;
                                            case "3":
                                                for (int i = 0; i < admin.producers.size(); i++)
                                                    System.out.println(i + 1 + ". " + admin.producers.get(i).username);
                                                System.out.println("Enter producer's index to see and edit information");
                                                System.out.println("Enter -1 if you want to delete a producer. Then enter producer's index");
                                                System.out.println("Enter 0 if you want to exit here");
                                                int index = sc.nextInt();
                                                if (index == -1) {
                                                    index = sc.nextInt();
                                                    admin.producers.remove(index - 1);
                                                    System.out.println("The producer was removed successfully");
                                                }
                                                else if (index != 0) {
                                                    Producer p = admin.producers.get(index - 1);
                                                    System.out.println(p.username + ":");
                                                    System.out.println("This producer has " + p.money + " Tomans");
                                                    for (int i = 0; i < p.products.size(); i++) {
                                                        Product pt = p.products.get(i);
                                                        if (pt instanceof Food)
                                                            System.out.println(i + 1 + ". Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                                        if (pt instanceof Wearing) {
                                                            if (pt instanceof Clothes)
                                                                System.out.println(i + 1 + ". Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                                            if (pt instanceof Shoe)
                                                                System.out.println(i + 1 + ". Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                                        }
                                                        if (pt instanceof Device) {
                                                            if (pt instanceof Console) {
                                                                String extensions = "";
                                                                for (String s : ((Console) pt).extensions)
                                                                    extensions += s + "/";
                                                                System.out.println(i + 1 + ". Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                                            }
                                                            if (pt instanceof Display)
                                                                System.out.println(i + 1 + ". Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                                        }
                                                    }
                                                }
                                                break;
                                            case "4":
                                                for (int i = 0; i < admin.carriers.size(); i++)
                                                    System.out.println(i+1 + ". " + admin.carriers.get(i).username);
                                                System.out.println("Enter carrier's index to see and edit information");
                                                System.out.println("Enter -1 if you want to delete a carrier. Then enter carrier's index");
                                                System.out.println("Enter 0 if you want to exit here");
                                                index = sc.nextInt();
                                                if (index == -1) {
                                                    index = sc.nextInt();
                                                    admin.carriers.remove(index - 1);
                                                    System.out.println("The carrier was removed successfully");
                                                }
                                                else if (index != 0) {
                                                    System.out.println(admin.carriers.get(index - 1).username + ":");
                                                    System.out.println("vehicle: " + admin.carriers.get(index - 1).vehicle);
                                                    System.out.println("sending list:");
                                                    for (int i = 0; i < admin.carriers.get(index - 1).sending.size(); i++) {
                                                        Product pt = admin.carriers.get(index - 1).sending.get(i).product;
                                                        String cl = admin.carriers.get(index - 1).sending.get(i).client.username;
                                                        System.out.print(i + 1 + ". ");
                                                        if (pt instanceof Food)
                                                            System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                        else if (pt instanceof Wearing) {
                                                            if (pt instanceof Clothes)
                                                                System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                            if (pt instanceof Shoe)
                                                                System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                        }
                                                        else if (pt instanceof Device) {
                                                            if (pt instanceof Console) {
                                                                System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                            }
                                                            if (pt instanceof Display)
                                                                System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                                        }
                                                    }
                                                    System.out.println("sent list:");
                                                    for (int i = 0; i < admin.carriers.get(index - 1).sent.size(); i++) {
                                                        Product pt = admin.carriers.get(index - 1).sent.get(i).product;
                                                        System.out.print(i + 1 + ". ");
                                                        if (pt instanceof Food)
                                                            System.out.print("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                        else if (pt instanceof Wearing) {
                                                            if (pt instanceof Clothes)
                                                                System.out.print("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                            if (pt instanceof Shoe)
                                                                System.out.print("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                        }
                                                        else if (pt instanceof Device) {
                                                            if (pt instanceof Console) {
                                                                System.out.print("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                            }
                                                            if (pt instanceof Display)
                                                                System.out.print("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                                        }
                                                        System.out.println(" was sent to client " + admin.carriers.get(index - 1).sent.get(i).client.username);
                                                    }
                                                }
                                                break;
                                        }
                                    } else
                                        break;
                                }
                            }
                        } else
                            System.out.println("Wrong username");
                    }
                    break;

                case "2":
                    //producer kamel shd
                    System.out.println("Enter your username and password");
                    String username = sc.next();
                    String password = sc.next();
                    boolean yes = false;
                    for (Producer u : admin.producers) {
                        if (u.username.equals(username) && u.getPassword().equals(password)) {
                            yes = true;
                            System.out.println("You are logged in successfully");
                            System.out.println("You have " + u.money + " Tomans from selling products");
                            System.out.println("Enter 1 to add a product");
                            System.out.println("Enter 2 to remove a product");
                            System.out.println("Enter 3 to see the list of sold products");
                            System.out.println("Enter 4 to log out of producer mode");
                            cmd = sc.next();
                            if (cmd.equals("1")) {
                                while (true) {

                                    System.out.println("Enter 1 if you want to add food");
                                    System.out.println("Enter 2 if you want to add wearings");
                                    System.out.println("Enter 3 if you want to add device");
                                    System.out.println("Enter 4 if you want to add off to your products");
                                    System.out.println("Enter 5 if you are done here");
                                    cmd = sc.next();
                                    if (cmd.equals("1")) {
                                        System.out.println("Enter 1 if you want to add junk");
                                        System.out.println("Enter 2 if you want to add dairy");
                                        System.out.println("Enter 3 if you want to add vegetables or fruits");
                                        cmd = sc.next();
                                        Food.foodType type = null;
                                        if (cmd.equals("1"))
                                            type = Food.foodType.junk;
                                        if (cmd.equals("2"))
                                            type = Food.foodType.dairy;
                                        if (cmd.equals("3"))
                                            type = Food.foodType.veg;
                                        System.out.println("Please enter volume , company name , price and expiration date in order");
                                        System.out.println("Be careful to enter date in the format 'YY.MM.DD'");
                                        u.addProduct(new Food(Product.ProductType.Food, sc.nextDouble(), sc.next(), sc.nextDouble(), (Producer) u, type, sc.next()));

                                        System.out.println("Your product was added successfully");

                                    }
                                    else if (cmd.equals("2")) {
                                        System.out.println("Enter 1 if you want to add shoes");
                                        System.out.println("Enter 2 if you want to add trousers");
                                        System.out.println("Enter 3 if you want to add dress");
                                        cmd = sc.next();
                                        if (cmd.equals("1")) {
                                            System.out.println("Enter 1 if it's for women");
                                            System.out.println("Enter 2 if it's for men");
                                            System.out.println("Enter 3 if it's for children");
                                            cmd = sc.next();
                                            Wearing.Gender gnd = null;
                                            if (cmd.equals("1"))
                                                gnd = Wearing.Gender.Women;
                                            if (cmd.equals("2"))
                                                gnd = Wearing.Gender.Men;
                                            if (cmd.equals("3"))
                                                gnd = Wearing.Gender.Children;
                                            System.out.println("Enter volume , company name , price , material and size in order");
                                            u.addProduct(new Shoe(Product.ProductType.Wearing, sc.nextDouble(), sc.next(), sc.nextDouble(), (Producer) u, gnd, sc.next(), sc.nextInt()));
                                        } else {
                                            Clothes.ClothesType type = null;
                                            if (cmd.equals("2"))
                                                type = Clothes.ClothesType.Trousers;
                                            if (cmd.equals("3"))
                                                type = Clothes.ClothesType.Dress;
                                            System.out.println("Enter 1 if it's for women");
                                            System.out.println("Enter 2 if it's for men");
                                            System.out.println("Enter 3 if it's for children");
                                            cmd = sc.next();
                                            Wearing.Gender gnd = null;
                                            if (cmd.equals("1"))
                                                gnd = Wearing.Gender.Women;
                                            if (cmd.equals("2"))
                                                gnd = Wearing.Gender.Men;
                                            if (cmd.equals("3"))
                                                gnd = Wearing.Gender.Children;
                                            System.out.println("Enter volume , company name , price , material and size in order");
                                            u.addProduct(new Clothes(Product.ProductType.Wearing, sc.nextDouble(), sc.next(), sc.nextDouble(), (Producer) u, gnd, sc.next(), sc.next(), type));
                                        }
                                        System.out.println("Your product was added successfully");
                                    }
                                    else if (cmd.equals("3")) {
                                        System.out.println("Enter 1 if you want to add cellphone");
                                        System.out.println("Enter 2 if you want to add TV");
                                        System.out.println("Enter 3 if you want to add gaming console");
                                        cmd = sc.next();
                                        if (cmd.equals("3")) {
                                            System.out.println("Enter the list of extensions");
                                            System.out.println("Enter 0 when it's over");
                                            ArrayList<String> extensions = new ArrayList<String>();
                                            cmd = sc.next();
                                            while (!cmd.equals("0")) {
                                                extensions.add(cmd);
                                                cmd = sc.next();
                                            }
                                            System.out.println("Enter volume , company name , price and production year");
                                            u.addProduct(new Console(Product.ProductType.Device, sc.nextDouble(), sc.next(), sc.nextDouble(), (Producer) u, sc.nextInt(), extensions));
                                        } else {
                                            Display.displayType type = null;
                                            if (cmd.equals("1"))
                                                type = Display.displayType.cellphone;
                                            if (cmd.equals("2"))
                                                type = Display.displayType.tv;
                                            System.out.println("Enter volume , company name , price , production year and screen size");
                                            ((Producer) u).addProduct(new Display(Product.ProductType.Device, sc.nextDouble(), sc.next(), sc.nextDouble(), (Producer) u, sc.nextInt(), type, sc.nextInt()));
                                        }
                                        System.out.println("Your product was added successfully");
                                    }
                                    else if (cmd.equals("4")) {
                                        System.out.println("How many percents off ?");
                                        double off = sc.nextDouble();
                                        for (Product p : u.products)
                                            p.price -= p.price*(off/100);
                                    }
                                    else if (cmd.equals("5"))
                                        break;

                                }
                            }
                            else if (cmd.equals("2")) {
                                if (u.products.size() == 0)
                                    System.out.println("No products to remove");
                                else {
                                    for (int i = 0; i < u.products.size(); i++) {
                                        Product pt = u.products.get(i);
                                        if (pt instanceof Food)
                                            System.out.println(i + 1 + ". Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        else if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println(i + 1 + ". Wearing:     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                            if (pt instanceof Shoe)
                                                System.out.println(i + 1 + ". Wearing:     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        } else if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                System.out.println(i + 1 + ". Device:     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println(i + 1 + ". Device:     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        }
                                    }
                                    System.out.println("Enter index of the product you want to remove");
                                    System.out.println("Enter 0 if you want to exit here");
                                    int index = sc.nextInt();
                                    if (index != 0)
                                        u.deleteProduct(index);
                                }
                            }
                            else if (cmd.equals("3")) {
                                for (Producer.Pair pr : u.sold) {
                                    Product pt = pr.product;
                                    if (pt instanceof Food)
                                        System.out.print("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    else if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.print("Wearing:     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        if (pt instanceof Shoe)
                                            System.out.print("Wearing:     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    } else if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            System.out.print("Device:     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        }
                                        if (pt instanceof Display)
                                            System.out.print("Device:     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    }
                                    System.out.println("was sold to client " + pr.client.username);
                                }
                            }
                            break;
                        }
                    }
                    if (!yes)
                        System.out.println("Your username or password was wrong!");
                    break;

                case "3":
                    //carrier kamel shd
                    if (admin.carriers.size() == 0) {
                        System.out.println("No carriers are added yet");
                        break;
                    }
                    System.out.println("Enter your username and password");
                    yes = false;
                    int j = 0;
                    while (!yes) {
                        username = sc.next();
                        password = sc.next();
                        for (j = 0; j < admin.carriers.size(); j++) {
                            Carrier cr = admin.carriers.get(j);
                            if (cr.username.equals(username) && cr.getPassword().equals(password)) {
                                System.out.println("You are logged in successfully");
                                yes = true;
                                break;
                            }
                        }
                        if (!yes) {
                            System.out.println("your username or password is wrong! please try again");
                            System.out.println("Enter your username and password");
                        }
                    }
                    while (true) {
                        System.out.println("Enter 1 to see clients' orders");
                        System.out.println("Enter 2 to see sending list");
                        System.out.println("Enter 3 to see sent list");
                        System.out.println("Enter 4 to change vehicle");
                        System.out.println("Enter 5 to exit carrier mode");
                        cmd = sc.next();
                        switch (cmd) {
                            case "1":
                                for (int i = 0; i < clients.size(); i++) {
                                    System.out.println("client " + (i+1) + " wants these products:");
                                    for (int k = 0; k < clients.get(i).sendingQueue.size(); k++) {
                                        System.out.print("   " + (k + 1) + ". ");
                                        Product pt = clients.get(i).sendingQueue.get(k);
                                        if (pt instanceof Food)
                                            System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        else if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println("Wearing:     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                            if (pt instanceof Shoe)
                                                System.out.println("Wearing:     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        }
                                        else if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                System.out.println("Device:     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println("Device:     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        }
                                    }
                                }
                                while (admin.carriers.get(j).volume > 0) {
                                    System.out.println("Your vehicle has " + admin.carriers.get(j).volume + " cubic meters left");
                                    System.out.println("Enter 0 if you are over here");
                                    System.out.println("Which client are you going to send products to");
                                    int clientIndex = sc.nextInt();
                                    if (clientIndex == 0)
                                        break;
                                    System.out.println("Enter the index of product");
                                    int productIndex = sc.nextInt();
                                    if (admin.carriers.get(j).volume >= clients.get(clientIndex-1).sendingQueue.get(productIndex-1).volume) {
                                        admin.carriers.get(j).volume -= clients.get(clientIndex-1).sendingQueue.get(productIndex-1).volume;
                                        admin.carriers.get(j).sending.add(new Carrier.Pair(clients.get(clientIndex-1).sendingQueue.get(productIndex-1) , clients.get(clientIndex-1)));
                                        clients.get(clientIndex-1).sendingQueue.remove(productIndex-1);
                                    }
                                    else
                                        System.out.println("There isn't enough space on your vehicle to add this product");

                                }
                                if (admin.carriers.get(j).volume == 0)
                                    System.out.println("Your vehicle is full");
                                break;
                            case "2":
                                for (int i = 0; i < admin.carriers.get(j).sending.size(); i++) {
                                    Product pt = admin.carriers.get(j).sending.get(i).product;
                                    String cl = admin.carriers.get(j).sending.get(i).client.username;
                                    System.out.print(i + 1 + ". ");
                                    if (pt instanceof Food)
                                        System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                    else if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                        if (pt instanceof Shoe)
                                            System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                    }
                                    else if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                        }
                                        if (pt instanceof Display)
                                            System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " for client " + cl);
                                    }
                                }
                                while (true) {
                                    System.out.println("Enter 0 if you are over here");
                                    System.out.println("Enter the index of product you want to be sent");
                                    int productIndex = sc.nextInt();
                                    if (productIndex == 0)
                                        break;
                                    admin.carriers.get(j).volume += admin.carriers.get(j).sending.get(productIndex-1).product.volume;
                                    admin.carriers.get(j).sent.add(admin.carriers.get(j).sending.get(productIndex-1));
                                    admin.carriers.get(j).sending.get(productIndex-1).client.received.add(admin.carriers.get(j).sending.get(productIndex-1).product);
                                    admin.carriers.get(j).sending.remove(productIndex-1);
                                }
                                break;
                            case "3":
                                for (int i = 0; i < admin.carriers.get(j).sent.size(); i++) {
                                    Product pt = admin.carriers.get(j).sent.get(i).product;
                                    System.out.print(i + 1 + ". ");
                                    if (pt instanceof Food)
                                        System.out.print("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    else if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.print("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        if (pt instanceof Shoe)
                                            System.out.print("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    }
                                    else if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            System.out.print("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume);
                                        }
                                        if (pt instanceof Display)
                                            System.out.print("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume);
                                    }
                                    System.out.println(" was sent to client " + admin.carriers.get(j).sent.get(i).client.username);
                                }
                                break;
                            case "4":
                                double usedVolume = 0;
                                if (admin.carriers.get(j).vehicle.equals(Carrier.Vehicle.motorCycle))
                                    usedVolume = 0.5 - admin.carriers.get(j).volume;
                                else if (admin.carriers.get(j).vehicle.equals(Carrier.Vehicle.car))
                                    usedVolume = 2 - admin.carriers.get(j).volume;
                                else if (admin.carriers.get(j).vehicle.equals(Carrier.Vehicle.pickup))
                                    usedVolume = 12 - admin.carriers.get(j).volume;
                                System.out.println("Enter 1 if you want to change your vehicle with motorCycle");
                                System.out.println("Enter 2 if you want to change your vehicle with car");
                                System.out.println("Enter 3 if you want to change your vehicle with pickup");
                                cmd = sc.next();
                                switch (cmd) {
                                    case "1":
                                        if (usedVolume > 0.5)
                                            System.out.println("This vehicle doesn't have enough space for your products");
                                        else {
                                            admin.carriers.get(j).vehicle = Carrier.Vehicle.motorCycle;
                                            admin.carriers.get(j).volume = 0.5 - usedVolume;
                                            System.out.println("Your vehicle now is motorCycle");
                                        }
                                        break;
                                    case "2":
                                        if (usedVolume > 2)
                                            System.out.println("This vehicle doesn't have enough space for your products");
                                        else {
                                            admin.carriers.get(j).vehicle = Carrier.Vehicle.motorCycle;
                                            admin.carriers.get(j).volume = 2 - usedVolume;
                                            System.out.println("Your vehicle now is car");
                                        }
                                        break;
                                    case "3":
                                        admin.carriers.get(j).vehicle = Carrier.Vehicle.motorCycle;
                                        admin.carriers.get(j).volume = 0.5 - usedVolume;
                                        System.out.println("Your vehicle now is pickup");
                                        break;
                                }
                                break;
                            case "5":
                                break;
                        }
                        if (cmd.equals("5"))
                            break;
                    }
                    break;
                case "4":
                    //client kamel shd
                    System.out.println("Enter your username");
                    username = sc.next();
                    while (true) {
                        boolean repeated = false;
                        for (Client c : clients) {
                            if (c.username.equals(username)) {
                                System.out.println("Username already taken. Please enter another one");
                                username = sc.next();
                                repeated = true;
                                break;
                            }
                        }
                        if (!repeated)
                            break;
                    }
                    System.out.println("Enter your password");
                    Client c = new Client(username, sc.next());
                    clients.add(c);
                    System.out.println("Your username and password was set successfully");
                    System.out.println("Enter 1 if you want to continue as client");
                    System.out.println("Enter 2 if you want to exit client mode");
                    cmd = sc.next();
                    if (cmd.equals("2"))
                        break;
                    if (cmd.equals("1")) {
                        while (true) {
                            System.out.println("You have spent " + c.spentMoney + " Tomans on buying products");
                            System.out.println("Enter 1 if you want to see the list of producers");
                            System.out.println("Enter 2 if you want to enter food category");
                            System.out.println("Enter 3 if you want to enter wearing category");
                            System.out.println("Enter 4 if you want to enter device category");
                            System.out.println("Enter 5 if you want to see your bought products");
                            System.out.println("Enter 6 if you want to see the products that has been sent to you");
                            System.out.println("Enter 7 if you want to see the products that we will send you soon");
                            System.out.println("Enter 8 if you want to exit client mode");
                            cmd = sc.next();
                            if (cmd.equals("8"))
                                break;
                            switch (cmd) {
                                case "1":
                                    for (int i = 0; i < admin.producers.size(); i++)
                                        System.out.println(i + 1 + ". " + admin.producers.get(i).username);
                                    System.out.println("Enter producer's index if you want to see the products");
                                    System.out.println("Enter 0 if you want to exit this list");
                                    int index = sc.nextInt();
                                    if (index != 0) {
                                        Producer p = admin.producers.get(index - 1);
                                        System.out.println(p.username + ":");
                                        for (int i = 0; i < p.products.size(); i++) {
                                            Product pt = p.products.get(i);
                                            if (pt instanceof Food)
                                                System.out.println(i + 1 + ". Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                            if (pt instanceof Wearing) {
                                                if (pt instanceof Clothes)
                                                    System.out.println(i + 1 + ". Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                                if (pt instanceof Shoe)
                                                    System.out.println(i + 1 + ". Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                            }
                                            if (pt instanceof Device) {
                                                if (pt instanceof Console) {
                                                    String extensions = "";
                                                    for (String s : ((Console) pt).extensions)
                                                        extensions += s + "/";
                                                    System.out.println(i + 1 + ". Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                                }
                                                if (pt instanceof Display)
                                                    System.out.println(i + 1 + ". Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                            }
                                        }
                                        System.out.println("Enter product's index if you want to add it to your sending queue");
                                        System.out.println("Else enter 0");
                                        index = sc.nextInt();
                                        if (index != 0) {
                                            p.money += p.products.get(index - 1).price;
                                            p.sold.add(new Producer.Pair(p.products.get(index - 1) , c));
                                            c.sendingQueue.add(p.products.get(index - 1));
                                            c.spentMoney += p.products.get(index - 1).price;
                                            System.out.println("The product was added to your sending queue successfully");
                                        }
                                    }
                                    break;
                                case "2":
                                    ArrayList<Food> foods = new ArrayList<Food>();
                                    for (Producer p : admin.producers)
                                        for (Product pt : p.products)
                                            if (pt instanceof Food)
                                                foods.add((Food) pt);
                                    for (int i = 0; i < foods.size(); i++) {
                                        Food pt = foods.get(i);
                                        System.out.println(i + 1 + " type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price + " producer -> " + pt.producer.username);
                                    }
                                    System.out.println("Enter product's index if you want to add it to your sending queue");
                                    System.out.println("Else enter 0");
                                    index = sc.nextInt();
                                    if (index != 0) {
                                        foods.get(index - 1).producer.money += foods.get(index - 1).price;
                                        foods.get(index - 1).producer.sold.add(new Producer.Pair(foods.get(index - 1) , c));
                                        c.spentMoney += foods.get(index - 1).price;
                                        c.sendingQueue.add(foods.get(index - 1));
                                    }
                                    break;
                                case "3":
                                    ArrayList<Wearing> wearings = new ArrayList<Wearing>();
                                    for (Producer p : admin.producers)
                                        for (Product pt : p.products)
                                            if (pt instanceof Wearing)
                                                wearings.add((Wearing) pt);
                                    for (int i = 0; i < wearings.size(); i++) {
                                        Wearing pt = wearings.get(i);
                                        if (pt instanceof Clothes)
                                            System.out.println(i + 1 + " type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price + " producer -> " + pt.producer.username);
                                        if (pt instanceof Shoe)
                                            System.out.println(i + 1 + " type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price + " producer -> " + pt.producer.username);

                                    }
                                    System.out.println("Enter product's index if you want to add it to your sending queue");
                                    System.out.println("Else enter 0");
                                    index = sc.nextInt();
                                    if (index != 0) {
                                        c.sendingQueue.add(wearings.get(index - 1));
                                        wearings.get(index - 1).producer.sold.add(new Producer.Pair(wearings.get(index - 1) , c));
                                        c.spentMoney += wearings.get(index - 1).price;
                                        wearings.get(index - 1).producer.money += wearings.get(index - 1).price;
                                    }
                                    break;
                                case "4":
                                    ArrayList<Device> devices = new ArrayList<Device>();
                                    for (Producer p : admin.producers)
                                        for (Product pt : p.products)
                                            if (pt instanceof Device)
                                                devices.add((Device) pt);
                                    for (int i = 0; i < devices.size(); i++) {
                                        Device pt = devices.get(i);
                                        if (pt instanceof Console) {
                                            String extensions = "";
                                            for (String s : ((Console) pt).extensions)
                                                extensions += s + "/";
                                            System.out.println(i + 1 + "type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions + " producer -> " + pt.producer.username);
                                        }
                                        if (pt instanceof Display)
                                            System.out.println(i + 1 + "type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize + " producer -> " + pt.producer.username);

                                    }
                                    System.out.println("Enter product's index if you want to add it to your sending queue");
                                    System.out.println("Else enter 0");
                                    index = sc.nextInt();
                                    if (index != 0) {
                                        devices.get(index - 1).producer.money += devices.get(index - 1).price;
                                        devices.get(index - 1).producer.sold.add(new Producer.Pair(devices.get(index - 1) , c));
                                        c.spentMoney += devices.get(index - 1).price;
                                        c.sendingQueue.add(devices.get(index - 1));
                                    }
                                    break;
                                case "5":
                                    for (Product pt : c.sendingQueue) {
                                        if (pt instanceof Food)
                                            System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                        if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                            if (pt instanceof Shoe)
                                                System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                        }
                                        if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                String extensions = "";
                                                for (String s : ((Console) pt).extensions)
                                                    extensions += s + "/";
                                                System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                        }
                                    }
                                    break;
                                case "6":
                                    for (Product pt : c.received) {
                                        if (pt instanceof Food)
                                            System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                        if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                            if (pt instanceof Shoe)
                                                System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                        }
                                        if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                String extensions = "";
                                                for (String s : ((Console) pt).extensions)
                                                    extensions += s + "/";
                                                System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                        }
                                    }
                                    break;
                                case "7":
                                    ArrayList<Product> sending = new ArrayList<Product>();
                                    for (Product pt : c.sendingQueue) {
                                        if (!c.received.contains(pt))
                                            sending.add(pt);
                                    }
                                    for (Product pt : sending) {
                                        if (pt instanceof Food)
                                            System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                        if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                            if (pt instanceof Shoe)
                                                System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                        }
                                        if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                String extensions = "";
                                                for (String s : ((Console) pt).extensions)
                                                    extensions += s + "/";
                                                System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                        }
                                    }
                                    break;
                            }
                        }

                    }
                    break;
                case "5":
                    //client kamel shd
                    j = 0;
                    if (clients.size() == 0) {
                        System.out.println("There is no client in the system. Try signing up first");
                        break;
                    }
                    System.out.println("Enter your username and password");
                    yes = false;
                    while (!yes) {
                        username = sc.next();
                        password = sc.next();
                        for (j = 0; j < clients.size(); j++) {
                            Client cl = clients.get(j);
                            if (cl.username.equals(username) && cl.getPassword().equals(password)) {
                                System.out.println("You are logged in successfully");
                                yes = true;
                                break;
                            }
                        }
                        if (!yes) {
                            System.out.println("your username or password is wrong! please try again");
                            System.out.println("Enter your username and password");
                        }
                    }
                    c = clients.get(j);

                    while (true) {
                        System.out.println("You have spent " + c.spentMoney + " Tomans on buying products");
                        System.out.println("Enter 1 if you want to see the list of producers");
                        System.out.println("Enter 2 if you want to enter food category");
                        System.out.println("Enter 3 if you want to enter wearing category");
                        System.out.println("Enter 4 if you want to enter device category");
                        System.out.println("Enter 5 if you want to see your bought products");
                        System.out.println("Enter 6 if you want to see the products that has been sent to you");
                        System.out.println("Enter 7 if you want to see the products that we will send you soon");
                        System.out.println("Enter 8 if you want to exit client mode");
                        cmd = sc.next();
                        if (cmd.equals("8"))
                            break;
                        switch (cmd) {
                            case "1":
                                for (int i = 0; i < admin.producers.size(); i++)
                                    System.out.println(i + 1 + ". " + admin.producers.get(i).username);
                                System.out.println("Enter producer's index if you want to see the products");
                                System.out.println("Enter 0 if you want to exit this list");
                                int index = sc.nextInt();
                                if (index != 0) {
                                    Producer p = admin.producers.get(index - 1);
                                    System.out.println(p.username + ":");
                                    for (int i = 0; i < p.products.size(); i++) {
                                        Product pt = p.products.get(i);
                                        if (pt instanceof Food)
                                            System.out.println(i + 1 + ". Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                        if (pt instanceof Wearing) {
                                            if (pt instanceof Clothes)
                                                System.out.println(i + 1 + ". Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                            if (pt instanceof Shoe)
                                                System.out.println(i + 1 + ". Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                        }
                                        if (pt instanceof Device) {
                                            if (pt instanceof Console) {
                                                String extensions = "";
                                                for (String s : ((Console) pt).extensions)
                                                    extensions += s + "/";
                                                System.out.println(i + 1 + ". Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                            }
                                            if (pt instanceof Display)
                                                System.out.println(i + 1 + ". Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                        }
                                    }
                                    System.out.println("Enter product's index if you want to add it to your sending queue");
                                    System.out.println("Else enter 0");
                                    index = sc.nextInt();
                                    if (index != 0) {
                                        p.money += p.products.get(index - 1).price;
                                        p.sold.add(new Producer.Pair(p.products.get(index - 1) , c));
                                        c.sendingQueue.add(p.products.get(index - 1));
                                        c.spentMoney += p.products.get(index - 1).price;
                                        System.out.println("The product was added to your sending queue successfully");
                                    }
                                }
                                break;
                            case "2":
                                ArrayList<Food> foods = new ArrayList<Food>();
                                for (Producer p : admin.producers)
                                    for (Product pt : p.products)
                                        if (pt instanceof Food)
                                            foods.add((Food) pt);
                                for (int i = 0; i < foods.size(); i++) {
                                    Food pt = foods.get(i);
                                    System.out.println(i + 1 + " type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price + " producer -> " + pt.producer.username);
                                }
                                System.out.println("Enter product's index if you want to add it to your sending queue");
                                System.out.println("Else enter 0");
                                index = sc.nextInt();
                                if (index != 0) {
                                    foods.get(index - 1).producer.money += foods.get(index - 1).price;
                                    foods.get(index - 1).producer.sold.add(new Producer.Pair(foods.get(index - 1) , c));
                                    c.spentMoney += foods.get(index - 1).price;
                                    c.sendingQueue.add(foods.get(index - 1));
                                }
                                break;
                            case "3":
                                ArrayList<Wearing> wearings = new ArrayList<Wearing>();
                                for (Producer p : admin.producers)
                                    for (Product pt : p.products)
                                        if (pt instanceof Wearing)
                                            wearings.add((Wearing) pt);
                                for (int i = 0; i < wearings.size(); i++) {
                                    Wearing pt = wearings.get(i);
                                    if (pt instanceof Clothes)
                                        System.out.println(i + 1 + " type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price + " producer -> " + pt.producer.username);
                                    if (pt instanceof Shoe)
                                        System.out.println(i + 1 + " type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price + " producer -> " + pt.producer.username);

                                }
                                System.out.println("Enter product's index if you want to add it to your sending queue");
                                System.out.println("Else enter 0");
                                index = sc.nextInt();
                                if (index != 0) {
                                    c.sendingQueue.add(wearings.get(index - 1));
                                    wearings.get(index - 1).producer.sold.add(new Producer.Pair(wearings.get(index - 1) , c));
                                    c.spentMoney += wearings.get(index - 1).price;
                                    wearings.get(index - 1).producer.money += wearings.get(index - 1).price;
                                }
                                break;
                            case "4":
                                ArrayList<Device> devices = new ArrayList<Device>();
                                for (Producer p : admin.producers)
                                    for (Product pt : p.products)
                                        if (pt instanceof Device)
                                            devices.add((Device) pt);
                                for (int i = 0; i < devices.size(); i++) {
                                    Device pt = devices.get(i);
                                    if (pt instanceof Console) {
                                        String extensions = "";
                                        for (String s : ((Console) pt).extensions)
                                            extensions += s + "/";
                                        System.out.println(i + 1 + "type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions + " producer -> " + pt.producer.username);
                                    }
                                    if (pt instanceof Display)
                                        System.out.println(i + 1 + "type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize + " producer -> " + pt.producer.username);

                                }
                                System.out.println("Enter product's index if you want to add it to your sending queue");
                                System.out.println("Else enter 0");
                                index = sc.nextInt();
                                if (index != 0) {
                                    devices.get(index - 1).producer.money += devices.get(index - 1).price;
                                    devices.get(index - 1).producer.sold.add(new Producer.Pair(devices.get(index - 1) , c));
                                    c.spentMoney += devices.get(index - 1).price;
                                    c.sendingQueue.add(devices.get(index - 1));
                                }
                                break;
                            case "5":
                                for (Product pt : c.sendingQueue) {
                                    if (pt instanceof Food)
                                        System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                    if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                        if (pt instanceof Shoe)
                                            System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                    }
                                    if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            String extensions = "";
                                            for (String s : ((Console) pt).extensions)
                                                extensions += s + "/";
                                            System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                        }
                                        if (pt instanceof Display)
                                            System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                    }
                                }
                                break;
                            case "6":
                                for (Product pt : c.received) {
                                    if (pt instanceof Food)
                                        System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                    if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                        if (pt instanceof Shoe)
                                            System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                    }
                                    if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            String extensions = "";
                                            for (String s : ((Console) pt).extensions)
                                                extensions += s + "/";
                                            System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                        }
                                        if (pt instanceof Display)
                                            System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                    }
                                }
                                break;
                            case "7":
                                ArrayList<Product> sending = new ArrayList<Product>();
                                for (Product pt : c.sendingQueue) {
                                    if (!c.received.contains(pt))
                                        sending.add(pt);
                                }
                                for (Product pt : sending) {
                                    if (pt instanceof Food)
                                        System.out.println("Food:    type: " + ((Food) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " expiration date: " + ((Food) pt).expirationDate + " price: " + pt.price);
                                    if (pt instanceof Wearing) {
                                        if (pt instanceof Clothes)
                                            System.out.println("Wearing     type: " + ((Clothes) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Clothes) pt).size + " material: " + ((Clothes) pt).material + " price: " + pt.price);
                                        if (pt instanceof Shoe)
                                            System.out.println("Wearing     type: " + "shoes" + " company name: " + pt.companyName + " volume: " + pt.volume + " size: " + ((Shoe) pt).size + " price: " + pt.price);
                                    }
                                    if (pt instanceof Device) {
                                        if (pt instanceof Console) {
                                            String extensions = "";
                                            for (String s : ((Console) pt).extensions)
                                                extensions += s + "/";
                                            System.out.println("Device     type: console" + " company name: " + pt.companyName + " volume: " + pt.volume + " extensions: " + extensions);
                                        }
                                        if (pt instanceof Display)
                                            System.out.println("Device     type: " + ((Display) pt).type + " company name: " + pt.companyName + " volume: " + pt.volume + " screen size: " + ((Display) pt).screenSize);
                                    }
                                }
                                break;
                        }
                    }

                    break;
            }
        }
    }

}