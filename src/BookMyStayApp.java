import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    void displayRequests() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Harsha", "Single Room"));
        requestQueue.addRequest(new Reservation("Arun", "Double Room"));
        requestQueue.addRequest(new Reservation("Priya", "Suite Room"));

        System.out.println("Booking Requests in Queue");
        requestQueue.displayRequests();
    }
}