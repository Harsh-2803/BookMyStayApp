import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String id;
    String guest;

    Reservation(String id, String guest) {
        this.id = id;
        this.guest = guest;
    }
}

class DataStore implements Serializable {
    List<Reservation> bookings;
    Map<String, Integer> inventory;

    DataStore(List<Reservation> bookings, Map<String, Integer> inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

class PersistenceService {
    static void save(DataStore data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"))) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println("Save failed");
        }
    }

    static DataStore load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"))) {
            return (DataStore) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data, starting fresh");
            return new DataStore(new ArrayList<>(), new HashMap<>());
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        DataStore data = PersistenceService.load();

        data.inventory.putIfAbsent("Single", 2);

        data.bookings.add(new Reservation("RES1", "Harsha"));

        data.inventory.put("Single", data.inventory.get("Single") - 1);

        System.out.println("Bookings:");
        for (Reservation r : data.bookings) {
            System.out.println(r.id + " - " + r.guest);
        }

        System.out.println("Available Rooms: " + data.inventory.get("Single"));

        PersistenceService.save(data);
    }
}