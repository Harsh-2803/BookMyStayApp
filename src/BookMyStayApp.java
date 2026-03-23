import java.util.*;

class Reservation {
    String id;
    String roomType;
    boolean active;

    Reservation(String id, String roomType) {
        this.id = id;
        this.roomType = roomType;
        this.active = true;
    }
}

class CancellationService {
    Stack<String> releasedRooms = new Stack<>();

    void cancel(String id,
                Map<String, Reservation> bookings,
                Map<String, Integer> inventory) {

        if (!bookings.containsKey(id)) {
            System.out.println("Cancellation Failed: Booking not found");
            return;
        }

        Reservation r = bookings.get(id);

        if (!r.active) {
            System.out.println("Cancellation Failed: Already cancelled");
            return;
        }

        releasedRooms.push(id);

        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        r.active = false;

        System.out.println("Booking Cancelled: " + id);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);

        Map<String, Reservation> bookings = new HashMap<>();
        bookings.put("RES1", new Reservation("RES1", "Single"));

        CancellationService service = new CancellationService();

        service.cancel("RES1", bookings, inventory);
        service.cancel("RES1", bookings, inventory);
        service.cancel("RES2", bookings, inventory);

        System.out.println("Available Single Rooms: " + inventory.get("Single"));
    }
}