import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsFrame extends JFrame {

    private final HotelService hotelService;

    private JTextArea reportArea;

    public ReportsFrame(HotelService hotelService) {

        this.hotelService = hotelService;

        setTitle("Hotel Reports");
        setSize(900, 650);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        add(
                createHeader(),
                BorderLayout.NORTH
        );

        add(
                createMainPanel(),
                BorderLayout.CENTER
        );

        generateReport();
    }

    private JPanel createHeader() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                new Color(20, 32, 55)
        );

        panel.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel title =
                new JLabel(
                        "Hotel Reports"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        panel.add(
                title,
                BorderLayout.WEST
        );

        return panel;
    }

    private JPanel createMainPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        panel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20
                )
        );

        reportArea =
                new JTextArea();

        reportArea.setEditable(false);

        reportArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        reportArea
                );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton(
                        "Refresh Report"
                );

        refreshButton.setFocusPainted(
                false
        );

        refreshButton.addActionListener(
                e -> generateReport()
        );

        panel.add(
                refreshButton,
                BorderLayout.SOUTH
        );

        return panel;
    }

    private void generateReport() {

        int customers =
                hotelService.getCustomersCount();

        int rooms =
                hotelService.getRoomsCount();

        int availableRooms =
                hotelService.getAvailableRoomsCount();

        int reservations =
                hotelService.getReservationsCount();

        int payments =
                hotelService.getPaymentsCount();

        double revenue =
                hotelService.getTotalRevenue();

        StringBuilder report =
                new StringBuilder();

        report.append(
                "============================================\n"
        );

        report.append(
                "              HOTEL REPORT\n"
        );

        report.append(
                "============================================\n\n"
        );

        report.append(
                "CUSTOMERS\n"
        );

        report.append(
                "--------------------------------------------\n"
        );

        report.append(
                "Total Customers : "
        ).append(customers).append("\n\n");

        report.append(
                "ROOMS\n"
        );

        report.append(
                "--------------------------------------------\n"
        );

        report.append(
                "Total Rooms     : "
        ).append(rooms).append("\n");

        report.append(
                "Available Rooms : "
        ).append(availableRooms).append("\n\n");

        report.append(
                "RESERVATIONS\n"
        );

        report.append(
                "--------------------------------------------\n"
        );

        report.append(
                "Total Reservations : "
        ).append(reservations).append("\n\n");

        report.append(
                "PAYMENTS\n"
        );

        report.append(
                "--------------------------------------------\n"
        );

        report.append(
                "Total Payments : "
        ).append(payments).append("\n");

        report.append(
                "Total Revenue  : "
        ).append(
                String.format(
                        "%.2f",
                        revenue
                )
        ).append("\n\n");

        report.append(
                "============================================\n"
        );

        report.append(
                "CUSTOMER LIST\n"
        );

        report.append(
                "============================================\n\n"
        );

        for (Customer customer :
                hotelService.getCustomers()) {

            report.append(
                    customer
            );

            report.append("\n\n");
        }

        reportArea.setText(
                report.toString()
        );
    }
}