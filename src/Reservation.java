import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private int reservationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numberOfNights;
    private double totalPrice;

    private Customer customer;
    private Room room;

    public Reservation(int reservationId,
                       LocalDate checkIn,
                       LocalDate checkOut,
                       Customer customer,
                       Room room) {

        setReservationId(reservationId);
        setCheckIn(checkIn);
        setCheckOut(checkOut);

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }

        this.customer = customer;
        this.room = room;

        calculateReservationDetails();
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setReservationId(int reservationId) {

        if (reservationId <= 0) {
            throw new IllegalArgumentException(
                    "Reservation ID must be greater than 0."
            );
        }

        this.reservationId = reservationId;
    }

    public void setCheckIn(LocalDate checkIn) {

        if (checkIn == null) {
            throw new IllegalArgumentException(
                    "Check-in date cannot be null."
            );
        }

        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {

        if (checkOut == null) {
            throw new IllegalArgumentException(
                    "Check-out date cannot be null."
            );
        }

        this.checkOut = checkOut;
    }

    private void calculateReservationDetails() {

        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date."
            );
        }

        numberOfNights =
                (int) ChronoUnit.DAYS.between(checkIn, checkOut);

        totalPrice =
                room.getPricePerNight() * numberOfNights;
    }

    @Override
    public String toString() {

        return "=============================\n" +
                "Reservation ID : " + reservationId +
                "\nCustomer : " + customer.getFirstName() +
                " " + customer.getLastName() +
                "\nRoom Number : " + room.getRoomNumber() +
                "\nRoom Type : " + room.getRoomType() +
                "\nCheck-in : " + checkIn +
                "\nCheck-out : " + checkOut +
                "\nNumber of Nights : " + numberOfNights +
                "\nTotal Price : " + totalPrice +
                "\n=============================";
    }
}