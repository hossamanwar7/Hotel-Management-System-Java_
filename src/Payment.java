import java.time.LocalDate;

public class Payment {

    private String account;
    private int id;
    private LocalDate paymentDate;

    private double amount;

    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    private Reservation reservation;

    public Payment(String account,
                   int id,
                   LocalDate paymentDate,
                   double amount,
                   PaymentMethod paymentMethod,
                   PaymentStatus paymentStatus,
                   Reservation reservation) {

        setAccount(account);
        setId(id);
        setPaymentDate(paymentDate);
        setAmount(amount);
        setPaymentMethod(paymentMethod);
        setPaymentStatus(paymentStatus);

        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation cannot be null."
            );
        }

        this.reservation = reservation;
    }

    // Getters

    public String getAccount() {
        return account;
    }

    public int getId() {
        return id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Reservation getReservation() {
        return reservation;
    }

    // Setters + Validation

    public void setAccount(String account) {

        if (account == null || account.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account cannot be empty."
            );
        }

        this.account = account.trim();
    }

    public void setId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Payment ID must be greater than 0."
            );
        }

        this.id = id;
    }

    public void setPaymentDate(LocalDate paymentDate) {

        if (paymentDate == null) {
            throw new IllegalArgumentException(
                    "Payment date cannot be null."
            );
        }

        this.paymentDate = paymentDate;
    }

    public void setAmount(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than 0."
            );
        }

        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {

        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                    "Payment method cannot be null."
            );
        }

        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {

        if (paymentStatus == null) {
            throw new IllegalArgumentException(
                    "Payment status cannot be null."
            );
        }

        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {

        return "=============================\n" +
                "Payment ID : " + id +
                "\nAccount : " + account +
                "\nAmount : " + amount +
                "\nPayment Date : " + paymentDate +
                "\nPayment Method : " + paymentMethod +
                "\nPayment Status : " + paymentStatus +
                "\nReservation ID : " + reservation.getReservationId() +
                "\n=============================";
    }
}