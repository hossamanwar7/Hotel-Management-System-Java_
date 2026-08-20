import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomFrame extends JFrame {

    private final HotelService hotelService;

    private JTextField roomNumberField;
    private JTextField floorField;
    private JTextField priceField;

    private JComboBox<RoomType> roomTypeComboBox;
    private JComboBox<RoomStatus> roomStatusComboBox;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    public RoomFrame(HotelService hotelService) {

        this.hotelService = hotelService;

        setTitle("Room Management");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buildUI();
        loadRooms();
    }

    private void buildUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel title = new JLabel("Room Management");

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
                                3,
                                4,
                                10,
                                10
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

        roomNumberField = new JTextField();
        floorField = new JTextField();
        priceField = new JTextField();

        roomTypeComboBox =
                new JComboBox<>(RoomType.values());

        roomStatusComboBox =
                new JComboBox<>(RoomStatus.values());

        formPanel.add(
                createLabel("Room Number")
        );

        formPanel.add(roomNumberField);

        formPanel.add(
                createLabel("Floor")
        );

        formPanel.add(floorField);

        formPanel.add(
                createLabel("Price Per Night")
        );

        formPanel.add(priceField);

        formPanel.add(
                createLabel("Room Type")
        );

        formPanel.add(roomTypeComboBox);

        formPanel.add(
                createLabel("Room Status")
        );

        formPanel.add(roomStatusComboBox);

        formPanel.add(
                new JLabel()
        );

        formPanel.add(
                new JLabel()
        );

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
                                "Room Number",
                                "Floor",
                                "Price Per Night",
                                "Room Type",
                                "Room Status"
                        },
                        0
                );

        roomTable = new JTable(tableModel);

        roomTable.setRowHeight(28);

        roomTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        roomTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(roomTable);

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JButton addButton =
                createButton("Add Room");

        JButton searchButton =
                createButton("Search");

        JButton deleteButton =
                createButton("Delete");

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
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addRoom()
        );

        searchButton.addActionListener(
                e -> searchRoom()
        );

        deleteButton.addActionListener(
                e -> deleteRoom()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> loadRooms()
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

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 18, 10, 18
                )
        );

        return button;
    }

    private void addRoom() {

        try {

            int roomNumber =
                    Integer.parseInt(
                            roomNumberField
                                    .getText()
                                    .trim()
                    );

            int floor =
                    Integer.parseInt(
                            floorField
                                    .getText()
                                    .trim()
                    );

            double price =
                    Double.parseDouble(
                            priceField
                                    .getText()
                                    .trim()
                    );

            RoomType roomType =
                    (RoomType)
                            roomTypeComboBox
                                    .getSelectedItem();

            RoomStatus roomStatus =
                    (RoomStatus)
                            roomStatusComboBox
                                    .getSelectedItem();

            Room room =
                    new Room(
                            roomNumber,
                            floor,
                            price,
                            roomType,
                            roomStatus
                    );

            hotelService.addRoom(room);

            loadRooms();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Room added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room number, floor and price must be valid numbers.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void searchRoom() {

        try {

            int roomNumber =
                    Integer.parseInt(
                            roomNumberField
                                    .getText()
                                    .trim()
                    );

            Room room =
                    hotelService.searchRoomByNumber(
                            roomNumber
                    );

            if (room == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room not found.",
                        "Search",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            floorField.setText(
                    String.valueOf(
                            room.getFloor()
                    )
            );

            priceField.setText(
                    String.valueOf(
                            room.getPricePerNight()
                    )
            );

            roomTypeComboBox.setSelectedItem(
                    room.getRoomType()
            );

            roomStatusComboBox.setSelectedItem(
                    room.getRoomStatus()
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Room Number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void deleteRoom() {

        try {

            int roomNumber =
                    Integer.parseInt(
                            roomNumberField
                                    .getText()
                                    .trim()
                    );

            Room room =
                    hotelService.searchRoomByNumber(
                            roomNumber
                    );

            if (room == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room not found.",
                        "Delete",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this room?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                hotelService.removeRoom(
                        roomNumber
                );

                loadRooms();
                clearFields();

                JOptionPane.showMessageDialog(
                        this,
                        "Room deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Room Number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadRooms() {

        tableModel.setRowCount(0);

        for (Room room :
                hotelService.getRooms()) {

            tableModel.addRow(
                    new Object[]{
                            room.getRoomNumber(),
                            room.getFloor(),
                            room.getPricePerNight(),
                            room.getRoomType(),
                            room.getRoomStatus()
                    }
            );
        }
    }

    private void clearFields() {

        roomNumberField.setText("");
        floorField.setText("");
        priceField.setText("");

        roomTypeComboBox.setSelectedIndex(0);
        roomStatusComboBox.setSelectedIndex(0);
    }
}