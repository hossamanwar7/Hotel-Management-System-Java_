import java.util.ArrayList;

public class HotelService {

    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private final ArrayList<Payment> payments = new ArrayList<>();

    // =========================
    // Getters
    // =========================

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    // =========================
    // CUSTOMER
    // =========================

    public void addCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        if (searchCustomerByID(customer.getCustomerId()) != null) {
            throw new IllegalArgumentException(
                    "Customer ID already exists."
            );
        }

        customers.add(customer);
    }

    public void removeCustomer(int id) {

        Customer customer = searchCustomerByID(id);

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer not found."
            );
        }

        customers.remove(customer);
    }

    public Customer searchCustomerByID(int id) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == id) {
                return customer;
            }
        }

        return null;
    }

    public void viewCustomers() {

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // =========================
    // ROOM
    // =========================

    public void addRoom(Room room) {

        if (room == null) {
            throw new IllegalArgumentException(
                    "Room cannot be null."
            );
        }

        if (searchRoomByNumber(room.getRoomNumber()) != null) {
            throw new IllegalArgumentException(
                    "Room number already exists."
            );
        }

        rooms.add(room);
    }

    public void removeRoom(int roomNumber) {

        Room room = searchRoomByNumber(roomNumber);

        if (room == null) {
            throw new IllegalArgumentException(
                    "Room not found."
            );
        }

        if (room.getRoomStatus() == RoomStatus.RESERVED) {
            throw new IllegalArgumentException(
                    "Cannot delete a reserved room."
            );
        }

        rooms.remove(room);
    }

    public Room searchRoomByNumber(int number) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == number) {
                return room;
            }
        }

        return null;
    }

    public void viewRooms() {

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // =========================
    // RESERVATION
    // =========================

    public void addReservation(Reservation reservation) {

        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation cannot be null."
            );
        }

        if (searchReservationById(
                reservation.getReservationId()) != null) {

            throw new IllegalArgumentException(
                    "Reservation ID already exists."
            );
        }

        Room room = reservation.getRoom();

        if (room == null) {
            throw new IllegalArgumentException(
                    "Reservation room cannot be null."
            );
        }

        if (room.getRoomStatus() != RoomStatus.AVAILABLE) {
            throw new IllegalArgumentException(
                    "Room is not available."
            );
        }

        reservations.add(reservation);

        room.setRoomStatus(
                RoomStatus.RESERVED
        );
    }

    public void cancelReservation(int reservationId) {

        Reservation reservation =
                searchReservationById(reservationId);

        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation not found."
            );
        }

        Room room = reservation.getRoom();

        if (room != null) {

            room.setRoomStatus(
                    RoomStatus.AVAILABLE
            );
        }

        reservations.remove(reservation);
    }

    public Reservation searchReservationById(
            int reservationId) {

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId()
                    == reservationId) {

                return reservation;
            }
        }

        return null;
    }

    public void viewReservations() {

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    // =========================
    // PAYMENT
    // =========================

    public Payment searchPaymentById(int id) {

        for (Payment payment : payments) {

            if (payment.getId() == id) {
                return payment;
            }
        }

        return null;
    }

    public void addPayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment cannot be null."
            );
        }

        if (searchPaymentById(payment.getId()) != null) {
            throw new IllegalArgumentException(
                    "Payment ID already exists."
            );
        }

        payments.add(payment);
    }

    public void removePayment(int id) {

        Payment payment = searchPaymentById(id);

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment not found."
            );
        }

        payments.remove(payment);
    }

    public void viewPayments() {

        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    // =========================
    // DASHBOARD
    // =========================

    public int getCustomersCount() {
        return customers.size();
    }

    public int getRoomsCount() {
        return rooms.size();
    }

    public int getReservationsCount() {
        return reservations.size();
    }

    public int getPaymentsCount() {
        return payments.size();
    }

    public int getAvailableRoomsCount() {

        int count = 0;

        for (Room room : rooms) {

            if (room.getRoomStatus()
                    == RoomStatus.AVAILABLE) {

                count++;
            }
        }

        return count;
    }

    public double getTotalRevenue() {

        double total = 0;

        for (Payment payment : payments) {

            if (payment.getPaymentStatus()
                    == PaymentStatus.PAID) {

                total += payment.getAmount();
            }
        }

        return total;
    }
}