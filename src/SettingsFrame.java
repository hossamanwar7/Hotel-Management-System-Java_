import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsFrame extends JFrame {

    private final HotelService hotelService;

    private JComboBox<String> fontSizeComboBox;
    private JCheckBox notificationsCheckBox;

    public SettingsFrame(HotelService hotelService) {

        this.hotelService = hotelService;

        setTitle("Settings");
        setSize(600, 450);

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
                createSettingsPanel(),
                BorderLayout.CENTER
        );
    }

    private JPanel createHeader() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                new Color(
                        20,
                        32,
                        55
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel title =
                new JLabel(
                        "Settings"
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

    private JPanel createSettingsPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        30, 40, 30, 40
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel fontLabel =
                new JLabel(
                        "Font Size"
                );

        fontLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        panel.add(fontLabel);

        panel.add(
                Box.createVerticalStrut(8)
        );

        fontSizeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Small",
                                "Medium",
                                "Large"
                        }
                );

        fontSizeComboBox.setSelectedItem(
                "Medium"
        );

        panel.add(
                fontSizeComboBox
        );

        panel.add(
                Box.createVerticalStrut(25)
        );

        notificationsCheckBox =
                new JCheckBox(
                        "Enable Notifications"
                );

        notificationsCheckBox.setSelected(
                true
        );

        notificationsCheckBox.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        panel.add(
                notificationsCheckBox
        );

        panel.add(
                Box.createVerticalStrut(30)
        );

        JButton saveButton =
                new JButton(
                        "Save Settings"
                );

        saveButton.setFocusPainted(
                false
        );

        saveButton.addActionListener(
                e -> saveSettings()
        );

        panel.add(
                saveButton
        );

        panel.add(
                Box.createVerticalStrut(10)
        );

        JButton resetButton =
                new JButton(
                        "Reset"
                );

        resetButton.setFocusPainted(
                false
        );

        resetButton.addActionListener(
                e -> resetSettings()
        );

        panel.add(
                resetButton
        );

        return panel;
    }

    private void saveSettings() {

        JOptionPane.showMessageDialog(
                this,
                "Settings saved successfully.",
                "Settings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void resetSettings() {

        fontSizeComboBox.setSelectedItem(
                "Medium"
        );

        notificationsCheckBox.setSelected(
                true
        );

        JOptionPane.showMessageDialog(
                this,
                "Settings have been reset.",
                "Settings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}