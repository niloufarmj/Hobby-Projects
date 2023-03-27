import java.util.ArrayList;
import java.util.Scanner;

class TimePair {
    int start_time;
    int end_time;
    String user;
    public TimePair(int start_time, int end_time, String user) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.user = user;
    }
}
class Desk {
    private int number;
    private int floor;
    private String desk_id;
    private boolean full;
    private String feature_code;
    ArrayList <TimePair> full_times = new ArrayList<>();

    public Desk(int floor, int number, String feature_code) {
        this.floor = floor;
        this.number = number;
        this.full = false;
        this.desk_id = floor + "-" + number;
        this.feature_code = feature_code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(String desk_id) {
        this.desk_id = desk_id;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public void setFreeByTime(int time) {
        for (int i = 0; i < full_times.size(); i++) {
            if (full_times.get(i).end_time <= time) {
                full_times.remove(i);
            }
        }
    }

    public void setTime(int start_time, int end_time, String user) {
        full_times.add(new TimePair(start_time, end_time, user));
    }

    public boolean checkTimeFree(int start_time, int duration) {
        for (TimePair time_pair : full_times) {
            if (time_pair.start_time <= start_time && start_time < time_pair.end_time)
                return false;
            if (start_time <= time_pair.start_time && (start_time + duration) <= time_pair.end_time && time_pair.start_time < (start_time + duration))
                return false;
        }
        return true;
    }

    public String getFeature_code() {
        return feature_code;
    }

    public void setFeature_code(String feature_code) {
        this.feature_code = feature_code;
    }
}

enum FloorType {
    special, free
}
class Floor {
    private int number;
    public ArrayList<Desk> desks;
    private FloorType type;
    private int price;

    public Floor(int number, int num_of_desks, FloorType type, int price, ArrayList<String> features) {
        this.number = number;
        this.type = type;
        this.price = price;
        desks = new ArrayList<>();
        for (int i = 0; i < num_of_desks; i++) {
            this.desks.add(new Desk(number , i+1, features.get(i)));
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Desk getDesk(int start_time, int duration, String user) {
        for (int i = 0; i < this.desks.size(); i++) {
            Desk d = this.desks.get(i);
            d.setFreeByTime(start_time);
            if (d.checkTimeFree(start_time, duration)) {
                d.setTime(start_time, start_time + duration, user);
                return d;
            }
        }
        return null;
    }

    public Desk reserveDesk(int start_time, int duration, String feature_code, String user) {
        for (int i = 0; i < this.desks.size(); i++) {
            Desk d = this.desks.get(i);

            if (d.checkTimeFree(start_time, duration) && feature_code.equals(d.getFeature_code())) {
                d.setTime(start_time, start_time + duration, user);
                return d;
            }
        }
        return null;
    }

    public FloorType getType() {
        return type;
    }

    public void setType(FloorType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


public class Main {

    static ArrayList<Floor> floors = new ArrayList<>();
    static ArrayList<Integer> feature_prices = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num_of_features = sc.nextInt();
        for (int i = 0; i < num_of_features; i++)
            feature_prices.add(sc.nextInt());

        int num_of_floors = sc.nextInt();
        int price = sc.nextInt();

        for (int i = 0; i < num_of_floors; i++) {
            int num_of_desks = sc.nextInt();
            String type_str = sc.next();
            FloorType type;
            int floor_price = price;
            if (type_str.equals("free")) {
                type = FloorType.free;
                floor_price = 0;
            }
            else
                type = FloorType.special;

            ArrayList<String> features = new ArrayList<>();
            for (int j = 0; j < num_of_desks; j++) {
                features.add(sc.next());
            }

            floors.add(new Floor(i+1, num_of_desks, type, floor_price, features));
        }

        String line = sc.nextLine();
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.equals("end"))
                break;
            Scanner l_sc = new Scanner(line);
            int start_time = l_sc.nextInt();
            String command = l_sc.next();
            String userName;

            switch (command) {
                case "request_desk":
                    userName = l_sc.next();
                    String required_type_str = l_sc.next();
                    int duration = l_sc.nextInt();
                    requestDesk(userName, required_type_str, start_time, duration);
                    break;
                case "reserve_desk":
                    userName = l_sc.next();
                    int reserve_start_time = l_sc.nextInt();
                    int reserve_duration = l_sc.nextInt();
                    String feature_code = l_sc.next();
                    reserveDesk(userName, reserve_start_time, reserve_duration, feature_code);
                    break;
                case "reserve_multiple_desks":
                    userName = l_sc.next();
                    int num_of_desks = l_sc.nextInt();
                    reserve_start_time = l_sc.nextInt();
                    reserve_duration = l_sc.nextInt();
                    reserveMultiple(userName, num_of_desks, reserve_start_time, reserve_duration);
                    break;
                case "desk_status":
                    String desk_id = l_sc.next();
                    showDeskStatus(desk_id, start_time);

            }

        }

    }

    public static void requestDesk(String userName, String required_type_str, int start_time, int duration) {
        FloorType required_type;
        if (required_type_str.equals("free"))
            required_type = FloorType.free;
        else required_type = FloorType.special;

        Desk desk = null;
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getType() == required_type) {
                desk = floors.get(i).getDesk(start_time, duration, userName);
                if (desk != null) {
                    System.out.print(userName + " got desk " + desk.getDesk_id());

                    System.out.print(" for " + calculateTotalPrice(floors.get(i).getPrice(), desk.getFeature_code(), duration));
                    System.out.println();
                    break;
                }
            }

        }
        if (desk == null)
            System.out.println("no desk available");
    }

    public static void reserveDesk(String userName, int reserve_start_time, int reserve_duration, String feature_code) {
        Desk desk = null;
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getType() == FloorType.special) {
                desk = floors.get(i).reserveDesk(reserve_start_time, reserve_duration, feature_code, userName);
                if (desk != null) {
                    System.out.println(userName + " reserved desk " + desk.getDesk_id() + " for " + calculateTotalPrice(floors.get(i).getPrice(), desk.getFeature_code(), reserve_duration));
                    break;
                }
            }

        }
        if (desk == null)
            System.out.println("no desk available");
    }

    public static void reserveMultiple(String userName, int num_of_desks, int start_time, int duration) {
        ArrayList<Desk> reserved_desks = new ArrayList<>();
        for (Floor floor: floors) {
            if (floor.getType() == FloorType.special) {
                for (int i = 0; i < floor.desks.size(); i++) {
                    Desk d = floor.desks.get(i);
                    if (d.checkTimeFree(start_time, duration) && reserved_desks.size() < num_of_desks) {
                        reserved_desks.add(d);
                    }
                }

                if (reserved_desks.size() == num_of_desks) {
                    int final_price = 0;
                    for (Desk d : reserved_desks) {
                        d.setTime(start_time, start_time + duration, userName);
                        final_price += calculateTotalPrice(floor.getPrice(), d.getFeature_code(), duration);
                    }
                    System.out.print(userName + " reserved desks ");
                    for (Desk d : reserved_desks)
                        System.out.print(d.getDesk_id() + " ");
                    System.out.print("for " + final_price);
                    System.out.println();
                    return;
                }
            }
        }
        System.out.println("not enough desks available");

    }

    public static int calculateTotalPrice(int floor_price, String feature_code, int duration) {
        int total_price = floor_price;
        for (int i = 0; i < feature_code.length(); i++) {
            if (feature_code.charAt(i) == '1')
                total_price += (feature_prices.get(i)*duration);
        }
        return total_price;
    }

    public static void showDeskStatus(String desk_id, int start_time) {

        for (Floor floor : floors) {
            for (Desk desk : floor.desks) {
                if (desk_id.equals(desk.getDesk_id())) {
                    desk.setFreeByTime(start_time);

                    if (desk.full_times.size() == 0) {
                        System.out.println("desk is available");
                        return;
                    }

                    int min_start_time = desk.full_times.get(0).start_time;
                    for (TimePair time : desk.full_times) {
                        if (start_time >= time.start_time && start_time <= time.end_time) {
                            System.out.println(time.user + " got desk until " + time.end_time);
                            return;
                        }
                        min_start_time = Math.min(min_start_time, time.start_time);
                    }

                    System.out.println("desk available until " + min_start_time);
                }
            }
        }
        System.out.println("desk not found");
    }

}
