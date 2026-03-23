import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {
    static void validate(String roomType, int rooms, Map<String, Integer> inventory) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type");
        }

        if (rooms <= 0) {
            throw new InvalidBookingException("Rooms must be greater than 0");
        }

        if (inventory.get(roomType) < rooms) {
            throw new InvalidBookingException("Not enough rooms available");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);

        try {
            String roomType = "Single";
            int roomsRequested = 3;

            BookingValidator.validate(roomType, roomsRequested, inventory);

            inventory.put(roomType, inventory.get(roomType) - roomsRequested);

            System.out.println("Booking Successful");

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System is still running safely...");
    }
}