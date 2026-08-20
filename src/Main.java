import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HotelService hotelService =
                    new HotelService();

            DashboardFrame dashboard =
                    new DashboardFrame(
                            hotelService
                    );

            dashboard.setVisible(true);
        });
    }
}