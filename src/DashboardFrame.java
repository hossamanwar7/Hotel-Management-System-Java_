import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final Color NAVY =
            new Color(20, 32, 55);

    private final Color DARK_NAVY =
            new Color(14, 24, 42);

    private final Color BLUE =
            new Color(52, 152, 219);

    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color WHITE =
            Color.WHITE;

    private final Color TEXT =
            new Color(45, 55, 72);

    private final Color SECONDARY_TEXT =
            new Color(110, 120, 135);

    private final HotelService hotelService;

    private JPanel contentPanel;

    public DashboardFrame(
            HotelService hotelService
    ) {

        this.hotelService = hotelService;

        setTitle(
                "Hotel Management System"
        );

        setSize(1200, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        add(
                createSidebar(),
                BorderLayout.WEST
        );

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        showDashboard();
    }

    // ====================================
    // IMPORTANT
    // ====================================

    public void refreshDashboard() {
        showDashboard();
    }

    // ====================================
    // SIDEBAR
    // ====================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(240, 700)
        );

        sidebar.setBackground(
                DARK_NAVY
        );

        JPanel logoPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoPanel.setBackground(
                DARK_NAVY
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        25, 20, 25, 20
                )
        );

        JLabel icon =
                new JLabel("🏨");

        icon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        32
                )
        );

        JLabel name =
                new JLabel(
                        "<html><b>HOTEL</b><br>MANAGEMENT</html>"
                );

        name.setForeground(
                WHITE
        );

        name.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        logoPanel.add(
                icon,
                BorderLayout.WEST
        );

        logoPanel.add(
                name,
                BorderLayout.CENTER
        );

        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );

        JPanel menuPanel =
                new JPanel();

        menuPanel.setBackground(
                DARK_NAVY
        );

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setBorder(
                new EmptyBorder(
                        10, 12, 10, 12
                )
        );

        JButton dashboardButton =
                createMenuButton(
                        "▣   Dashboard"
                );

        JButton customersButton =
                createMenuButton(
                        "♙   Customers"
                );

        JButton roomsButton =
                createMenuButton(
                        "▤   Rooms"
                );

        JButton reservationsButton =
                createMenuButton(
                        "▣   Reservations"
                );

        JButton paymentsButton =
                createMenuButton(
                        "◆   Payments"
                );

        JButton reportsButton =
                createMenuButton(
                        "▥   Reports"
                );

        JButton settingsButton =
                createMenuButton(
                        "⚙   Settings"
                );

        menuPanel.add(dashboardButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(customersButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(roomsButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(reservationsButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(paymentsButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(reportsButton);
        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(settingsButton);

        // Dashboard
        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        // Customers
        customersButton.addActionListener(
                e -> new CustomerFrame(
                        hotelService,
                        this
                ).setVisible(true)
        );

        // Rooms
        roomsButton.addActionListener(
                e -> new RoomFrame(
                        hotelService
                ).setVisible(true)
        );

        // Reservations
        reservationsButton.addActionListener(
                e -> new ReservationFrame(
                        hotelService,
                        this
                ).setVisible(true)
        );

        // Payments
        paymentsButton.addActionListener(
                e -> new PaymentFrame(
                        hotelService
                ).setVisible(true)
        );

        // Reports
        reportsButton.addActionListener(
                e -> new ReportsFrame(
                        hotelService
                ).setVisible(true)
        );

        // Settings
        settingsButton.addActionListener(
                e -> new SettingsFrame(
                        hotelService
                ).setVisible(true)
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                DARK_NAVY
        );

        bottomPanel.setBorder(
                new EmptyBorder(
                        10, 12, 20, 12
                )
        );

        JButton logoutButton =
                createMenuButton(
                        "↪   Logout"
                );

        logoutButton.addActionListener(
                e -> {

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (result ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );

        bottomPanel.add(
                logoutButton
        );

        sidebar.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        button.setPreferredSize(
                new Dimension(
                        210,
                        48
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                DARK_NAVY
        );

        button.setBorder(
                new EmptyBorder(
                        0, 15, 0, 10
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ====================================
    // DASHBOARD
    // ====================================

    private void showDashboard() {

        contentPanel.removeAll();

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

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                WHITE
        );

        header.setBorder(
                new EmptyBorder(
                        25, 30, 20, 30
                )
        );

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                TEXT
        );

        JLabel subtitle =
                new JLabel(
                        "Welcome to Hotel Management System"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setBackground(
                WHITE
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        JLabel admin =
                new JLabel(
                        "Admin ●"
                );

        admin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        admin.setForeground(
                BLUE
        );

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                admin,
                BorderLayout.EAST
        );

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                2, 3, 20, 20
                        )
                );

        cardsPanel.setBackground(
                BACKGROUND
        );

        cardsPanel.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30
                )
        );

        cardsPanel.add(
                createCard(
                        "CUSTOMERS",
                        String.valueOf(customers),
                        "Total Customers"
                )
        );

        cardsPanel.add(
                createCard(
                        "ROOMS",
                        String.valueOf(rooms),
                        "Total Rooms"
                )
        );

        cardsPanel.add(
                createCard(
                        "AVAILABLE",
                        String.valueOf(availableRooms),
                        "Available Rooms"
                )
        );

        cardsPanel.add(
                createCard(
                        "RESERVATIONS",
                        String.valueOf(reservations),
                        "Total Reservations"
                )
        );

        cardsPanel.add(
                createCard(
                        "PAYMENTS",
                        String.valueOf(payments),
                        "Total Payments"
                )
        );

        cardsPanel.add(
                createCard(
                        "REVENUE",
                        String.format(
                                "%.2f",
                                revenue
                        ),
                        "Total Revenue"
                )
        );

        contentPanel.add(
                header,
                BorderLayout.NORTH
        );

        contentPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createCard(
            String title,
            String value,
            String description
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        238
                                )
                        ),
                        new EmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        titleLabel.setForeground(
                SECONDARY_TEXT
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        valueLabel.setForeground(
                NAVY
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setBackground(
                WHITE
        );

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.add(titleLabel);

        textPanel.add(
                Box.createVerticalStrut(8)
        );

        textPanel.add(valueLabel);

        textPanel.add(
                Box.createVerticalStrut(5)
        );

        textPanel.add(
                descriptionLabel
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        return card;
    }
}