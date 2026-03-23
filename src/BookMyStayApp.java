import java.util.*;

class BookingProcessor {
    private int availableRooms = 1;

    public synchronized void bookRoom(String guest) {
        if (availableRooms > 0) {
            System.out.println(guest + " is booking...");
            availableRooms--;
            System.out.println(guest + " booked successfully");
        } else {
            System.out.println(guest + " failed - No rooms available");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingProcessor processor = new BookingProcessor();

        Thread t1 = new Thread(() -> processor.bookRoom("Guest 1"));
        Thread t2 = new Thread(() -> processor.bookRoom("Guest 2"));
        Thread t3 = new Thread(() -> processor.bookRoom("Guest 3"));

        t1.start();
        t2.start();
        t3.start();
    }
}