import java.util.*;

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Map<String, List<AddOnService>> map = new HashMap<>();

        String id = "RES1";

        map.putIfAbsent(id, new ArrayList<>());

        map.get(id).add(new AddOnService("WiFi", 500));
        map.get(id).add(new AddOnService("Breakfast", 800));

        double total = 0;

        for (AddOnService s : map.get(id)) {
            System.out.println(s.name + " - ₹" + s.cost);
            total += s.cost;
        }

        System.out.println("Total: ₹" + total);
    }
}