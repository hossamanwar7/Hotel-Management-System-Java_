public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String nationalId;
    private String phone;

    public Customer(int customerId, String firstName, String lastName,
                    String address, String nationalId, String phone) {

        setCustomerId(customerId);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setNationalId(nationalId);
        setPhone(phone);
    }

    // Getters

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhone() {
        return phone;
    }

    // Setters + Validation

    public void setCustomerId(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be greater than 0."
            );
        }

        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "First name cannot be empty."
            );
        }

        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Last name cannot be empty."
            );
        }

        this.lastName = lastName.trim();
    }

    public void setAddress(String address) {

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Address cannot be empty."
            );
        }

        this.address = address.trim();
    }

    public void setNationalId(String nationalId) {

        if (nationalId == null || nationalId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "National ID cannot be empty."
            );
        }

        this.nationalId = nationalId.trim();
    }

    public void setPhone(String phone) {

        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be empty."
            );
        }

        this.phone = phone.trim();
    }

    // toString

    @Override
    public String toString() {

        return "=============================\n" +
                "Customer ID : " + customerId +
                "\nFirst Name : " + firstName +
                "\nLast Name : " + lastName +
                "\nAddress : " + address +
                "\nNational ID : " + nationalId +
                "\nPhone : " + phone +
                "\n=============================";
    }
}