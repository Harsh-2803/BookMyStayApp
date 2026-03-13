import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.offer(r);
    }

    Reservation nextRequest() {
        return queue.poll();
    }

    boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {

    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private Set<String> usedRoomIds = new HashSet<>();
    private int idCounter = 1;

    void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

        while (queue.hasRequests()) {

            Reservation r = queue.nextRequest();
            int available = inventory.getAvailability(r.roomType);

            if (available > 0) {

                String roomId = r.roomType.replace(" ", "") + "-" + idCounter++;

                if (!usedRoomIds.contains(roomId)) {

                    usedRoomIds.add(roomId);

                    allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                    allocatedRooms.get(r.roomType).add(roomId);

                    inventory.decrement(r.roomType);

                    System.out.println("Reservation Confirmed");
                    System.out.println("Guest: " + r.guestName);
                    System.out.println("Room Type: " + r.roomType);
                    System.out.println("Assigned Room ID: " + roomId);
                    System.out.println();
                }

            } else {
                System.out.println("No rooms available for " + r.roomType + " for guest " + r.guestName);
                System.out.println();
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Harsha", "Single Room"));
        queue.addRequest(new Reservation("Arun", "Double Room"));
        queue.addRequest(new Reservation("Priya", "Suite Room"));
        queue.addRequest(new Reservation("Kiran", "Single Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService bookingService = new BookingService();

        bookingService.processBookings(queue, inventory);
    }
}