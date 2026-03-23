import java.util.*;

class Reservation {
    String id;
    String guestName;

    Reservation(String id, String guestName) {
        this.id = id;
        this.guestName = guestName;
    }
}

class BookingHistory {
    List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {
    void showAllBookings(List<Reservation> list) {
        for (Reservation r : list) {
            System.out.println(r.id + " - " + r.guestName);
        }
    }

    void totalBookings(List<Reservation> list) {
        System.out.println("Total Bookings: " + list.size());
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        history.addReservation(new Reservation("RES1", "Harsha"));
        history.addReservation(new Reservation("RES2", "Arun"));
        history.addReservation(new Reservation("RES3", "Priya"));

        report.showAllBookings(history.getAllReservations());
        report.totalBookings(history.getAllReservations());
    }
}