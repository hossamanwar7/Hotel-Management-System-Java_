import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class PaymentFrame extends JFrame {

    private final HotelService hotelService;

    private JTextField idField;
    private JTextField accountField;
    private JTextField amountField;

    private JComboBox<Reservation> reservationComboBox;
    private JComboBox<PaymentMethod> methodComboBox;
    private JComboBox<PaymentStatus> statusComboBox;

    private JTextArea paymentArea;

    public PaymentFrame(HotelService hotelService) {

        this.hotelService = hotelService;

        setTitle("Payment Management");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        refreshPayments();
    }

    private JPanel createHeader() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(new Color(20, 32, 55));
        panel.setBorder(
                new EmptyBorder(20, 25, 20, 25)
        );

        JLabel title = new JLabel("Payment Management");

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        panel.add(title, BorderLayout.WEST);

        return panel;
    }

    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        mainPanel.add(
                createFormPanel(),
                BorderLayout.WEST
        );

        paymentArea = new JTextArea();

        paymentArea.setEditable(false);
        paymentArea.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane =
                new JScrollPane(paymentArea);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return mainPanel;
    }

    private JPanel createFormPanel() {

        JPanel panel = new JPanel();

        panel.setPreferredSize(
                new Dimension(300, 450)
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 232)
                        ),
                        new EmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title = new JLabel("Add Payment");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(20)
        );

        idField = new JTextField();

        accountField = new JTextField();

        amountField = new JTextField();

        reservationComboBox =
                new JComboBox<>();

        methodComboBox =
                new JComboBox<>(
                        PaymentMethod.values()
                );

        statusComboBox =
                new JComboBox<>(
                        PaymentStatus.values()
                );

        addField(panel, "Payment ID", idField);
        addField(panel, "Account", accountField);
        addField(panel, "Amount", amountField);

        JLabel reservationLabel =
                new JLabel("Reservation");

        reservationLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(reservationLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        reservationComboBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loadReservations();

        panel.add(reservationComboBox);

        panel.add(
                Box.createVerticalStrut(12)
        );

        JLabel methodLabel =
                new JLabel("Payment Method");

        methodLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(methodLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        methodComboBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(methodComboBox);

        panel.add(
                Box.createVerticalStrut(12)
        );

        JLabel statusLabel =
                new JLabel("Payment Status");

        statusLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(statusLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        statusComboBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(statusComboBox);

        panel.add(
                Box.createVerticalStrut(20)
        );

        JButton addButton =
                new JButton("Add Payment");

        addButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        addButton.setFocusPainted(false);

        addButton.addActionListener(
                e -> addPayment()
        );

        panel.add(addButton);

        panel.add(
                Box.createVerticalStrut(10)
        );

        JButton removeButton =
                new JButton("Remove Payment");

        removeButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        removeButton.setFocusPainted(false);

        removeButton.addActionListener(
                e -> removePayment()
        );

        panel.add(removeButton);

        return panel;
    }

    private void addField(
            JPanel panel,
            String labelText,
            JTextField field
    ) {

        JLabel label =
                new JLabel(labelText);

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(5)
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(field);

        panel.add(
                Box.createVerticalStrut(12)
        );
    }

    private void loadReservations() {

        reservationComboBox.removeAllItems();

        for (Reservation reservation :
                hotelService.getReservations()) {

            reservationComboBox.addItem(
                    reservation
            );
        }
    }

    private void addPayment() {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            String account =
                    accountField.getText().trim();

            double amount =
                    Double.parseDouble(
                            amountField.getText().trim()
                    );

            Reservation reservation =
                    (Reservation)
                            reservationComboBox
                                    .getSelectedItem();

            PaymentMethod method =
                    (PaymentMethod)
                            methodComboBox
                                    .getSelectedItem();

            PaymentStatus status =
                    (PaymentStatus)
                            statusComboBox
                                    .getSelectedItem();

            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a reservation.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Payment payment =
                    new Payment(
                            account,
                            id,
                            LocalDate.now(),
                            amount,
                            method,
                            status,
                            reservation
                    );

            hotelService.addPayment(payment);

            refreshPayments();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Payment added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID and Amount must be valid numbers.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void removePayment() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Payment ID:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input.trim()
                    );

            Payment payment =
                    hotelService.searchPaymentById(id);

            if (payment == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            hotelService.removePayment(id);

            refreshPayments();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Payment ID.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshPayments() {

        if (paymentArea == null) {
            return;
        }

        paymentArea.setText("");

        if (hotelService.getPayments().isEmpty()) {

            paymentArea.setText(
                    "No payments available."
            );

            return;
        }

        for (Payment payment :
                hotelService.getPayments()) {

            paymentArea.append(
                    payment.toString()
            );

            paymentArea.append(
                    "\n\n"
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        accountField.setText("");
        amountField.setText("");

        loadReservations();
    }
}