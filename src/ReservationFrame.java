import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReservationFrame extends JFrame {

    private final HotelService hotelService;
    private final DashboardFrame dashboardFrame;

    private JTextField reservationIdField;
    private JTextField checkInField;
    private JTextField checkOutField;

    private JComboBox<CustomerItem> customerComboBox;
    private JComboBox<RoomItem> roomComboBox;

    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public ReservationFrame(
            HotelService hotelService,
            DashboardFrame dashboardFrame
    ) {

        this.hotelService = hotelService;
        this.dashboardFrame = dashboardFrame;

        setTitle("Reservation Management");
        setSize(1100, 650);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        buildUI();

        loadCustomers();
        loadRooms();
        loadReservations();
    }

    private void buildUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel title =
                new JLabel(
                        "Reservation Management"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                new Color(20, 32, 55)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                3, 4, 10, 10
                        )
                );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        reservationIdField = new JTextField();
        checkInField = new JTextField();
        checkOutField = new JTextField();

        customerComboBox =
                new JComboBox<>();

        roomComboBox =
                new JComboBox<>();

        formPanel.add(
                createLabel("Reservation ID")
        );

        formPanel.add(
                reservationIdField
        );

        formPanel.add(
                createLabel("Customer")
        );

        formPanel.add(
                customerComboBox
        );

        formPanel.add(
                createLabel("Room")
        );

        formPanel.add(
                roomComboBox
        );

        formPanel.add(
                createLabel(
                        "Check-in (YYYY-MM-DD)"
                )
        );

        formPanel.add(
                checkInField
        );

        formPanel.add(
                createLabel(
                        "Check-out (YYYY-MM-DD)"
                )
        );

        formPanel.add(
                checkOutField
        );

        formPanel.add(new JLabel());
        formPanel.add(new JLabel());

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Reservation ID",
                                "Customer",
                                "Room",
                                "Check-in",
                                "Check-out",
                                "Nights",
                                "Total Price"
                        },
                        0
                );

        reservationTable =
                new JTable(tableModel);

        reservationTable.setRowHeight(28);

        centerPanel.add(
                new JScrollPane(
                        reservationTable
                ),
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JButton addButton =
                createButton(
                        "Add Reservation"
                );

        JButton searchButton =
                createButton("Search");

        JButton cancelButton =
                createButton(
                        "Cancel Reservation"
                );

        JButton clearButton =
                createButton("Clear");

        JButton refreshButton =
                createButton("Refresh");

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addReservation()
        );

        searchButton.addActionListener(
                e -> searchReservation()
        );

        cancelButton.addActionListener(
                e -> cancelReservation()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> {
                    loadCustomers();
                    loadRooms();
                    loadReservations();
                }
        );

        add(mainPanel);
    }

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                new Color(45, 55, 72)
        );

        return label;
    }

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBackground(
                new Color(52, 152, 219)
        );

        button.setForeground(Color.WHITE);

        return button;
    }

    private void loadCustomers() {

        customerComboBox.removeAllItems();

        for (Customer customer :
                hotelService.getCustomers()) {

            customerComboBox.addItem(
                    new CustomerItem(customer)
            );
        }
    }

    private void loadRooms() {

        roomComboBox.removeAllItems();

        for (Room room :
                hotelService.getRooms()) {

            if (room.getRoomStatus()
                    == RoomStatus.AVAILABLE) {

                roomComboBox.addItem(
                        new RoomItem(room)
                );
            }
        }
    }

    private void addReservation() {

        try {

            int id =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );

            if (customerComboBox
                    .getSelectedItem() == null) {

                throw new IllegalArgumentException(
                        "Please select a customer."
                );
            }

            if (roomComboBox
                    .getSelectedItem() == null) {

                throw new IllegalArgumentException(
                        "No available room selected."
                );
            }

            CustomerItem customerItem =
                    (CustomerItem)
                            customerComboBox
                                    .getSelectedItem();

            RoomItem roomItem =
                    (RoomItem)
                            roomComboBox
                                    .getSelectedItem();

            LocalDate checkIn =
                    LocalDate.parse(
                            checkInField
                                    .getText()
                                    .trim()
                    );

            LocalDate checkOut =
                    LocalDate.parse(
                            checkOutField
                                    .getText()
                                    .trim()
                    );

            Reservation reservation =
                    new Reservation(
                            id,
                            checkIn,
                            checkOut,
                            customerItem.customer,
                            roomItem.room
                    );

            hotelService.addReservation(
                    reservation
            );

            loadReservations();
            loadRooms();
            clearFields();

            dashboardFrame.refreshDashboard();

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            showError(
                    "Reservation ID must be a number."
            );

        } catch (DateTimeParseException e) {

            showError(
                    "Use date format: YYYY-MM-DD"
            );

        } catch (IllegalArgumentException e) {

            showError(e.getMessage());
        }
    }

    private void searchReservation() {

        try {

            int id =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );

            Reservation reservation =
                    hotelService.searchReservationById(id);

            if (reservation == null) {

                showWarning(
                        "Reservation not found."
                );

                return;
            }

            checkInField.setText(
                    reservation.getCheckIn().toString()
            );

            checkOutField.setText(
                    reservation.getCheckOut().toString()
            );

            selectCustomer(
                    reservation.getCustomer()
            );

            // We don't need the room to be available
            // when searching for an existing reservation.
            loadAllRooms();

            selectRoom(
                    reservation.getRoom()
            );

        } catch (NumberFormatException e) {

            showError(
                    "Enter a valid Reservation ID."
            );
        }
    }

    private void cancelReservation() {

        try {

            int id =
                    Integer.parseInt(
                            reservationIdField
                                    .getText()
                                    .trim()
                    );

            Reservation reservation =
                    hotelService.searchReservationById(id);

            if (reservation == null) {

                showWarning(
                        "Reservation not found."
                );

                return;
            }

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to cancel this reservation?",
                            "Confirm Cancellation",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                hotelService.cancelReservation(id);

                loadReservations();
                loadRooms();
                clearFields();

                dashboardFrame.refreshDashboard();

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation cancelled successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            showError(
                    "Enter a valid Reservation ID."
            );

        } catch (IllegalArgumentException e) {

            showError(e.getMessage());
        }
    }

    private void loadAllRooms() {

        roomComboBox.removeAllItems();

        for (Room room :
                hotelService.getRooms()) {

            roomComboBox.addItem(
                    new RoomItem(room)
            );
        }
    }

    private void selectCustomer(
            Customer customer
    ) {

        for (int i = 0;
             i < customerComboBox.getItemCount();
             i++) {

            CustomerItem item =
                    customerComboBox.getItemAt(i);

            if (item.customer == customer) {

                customerComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    private void selectRoom(Room room) {

        for (int i = 0;
             i < roomComboBox.getItemCount();
             i++) {

            RoomItem item =
                    roomComboBox.getItemAt(i);

            if (item.room == room) {

                roomComboBox.setSelectedIndex(i);

                return;
            }
        }
    }

    private void loadReservations() {

        tableModel.setRowCount(0);

        for (Reservation reservation :
                hotelService.getReservations()) {

            String customerName =
                    reservation.getCustomer()
                            .getFirstName()
                            + " "
                            + reservation.getCustomer()
                            .getLastName();

            tableModel.addRow(
                    new Object[]{
                            reservation.getReservationId(),
                            customerName,
                            reservation.getRoom()
                                    .getRoomNumber(),
                            reservation.getCheckIn(),
                            reservation.getCheckOut(),
                            reservation.getNumberOfNights(),
                            reservation.getTotalPrice()
                    }
            );
        }
    }

    private void clearFields() {

        reservationIdField.setText("");
        checkInField.setText("");
        checkOutField.setText("");

        if (customerComboBox
                .getItemCount() > 0) {

            customerComboBox
                    .setSelectedIndex(0);
        }

        loadRooms();
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showWarning(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private static class CustomerItem {

        private final Customer customer;

        public CustomerItem(Customer customer) {
            this.customer = customer;
        }

        @Override
        public String toString() {

            return customer.getCustomerId()
                    + " - "
                    + customer.getFirstName()
                    + " "
                    + customer.getLastName();
        }
    }

    private static class RoomItem {

        private final Room room;

        public RoomItem(Room room) {
            this.room = room;
        }

        @Override
        public String toString() {

            return room.getRoomNumber()
                    + " - "
                    + room.getRoomType()
                    + " - "
                    + room.getPricePerNight();
        }
    }
}