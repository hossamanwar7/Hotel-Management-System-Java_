public class Room {

    private int roomNumber;
    private int floor;
    private double pricePerNight;

    private RoomType roomType;
    private RoomStatus roomStatus;

    public Room(
            int roomNumber,
            int floor,
            double pricePerNight,
            RoomType roomType,
            RoomStatus roomStatus
    ) {

        setRoomNumber(roomNumber);
        setFloor(floor);
        setPricePerNight(pricePerNight);
        setRoomType(roomType);
        setRoomStatus(roomStatus);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomNumber(int roomNumber) {

        if (roomNumber <= 0) {
            throw new IllegalArgumentException(
                    "Room number must be greater than 0."
            );
        }

        this.roomNumber = roomNumber;
    }

    public void setFloor(int floor) {

        if (floor < 0) {
            throw new IllegalArgumentException(
                    "Floor cannot be negative."
            );
        }

        this.floor = floor;
    }

    public void setPricePerNight(
            double pricePerNight
    ) {

        if (pricePerNight <= 0) {
            throw new IllegalArgumentException(
                    "Price per night must be greater than 0."
            );
        }

        this.pricePerNight = pricePerNight;
    }

    public void setRoomType(
            RoomType roomType
    ) {

        if (roomType == null) {
            throw new IllegalArgumentException(
                    "Room type cannot be null."
            );
        }

        this.roomType = roomType;
    }

    public void setRoomStatus(
            RoomStatus roomStatus
    ) {

        if (roomStatus == null) {
            throw new IllegalArgumentException(
                    "Room status cannot be null."
            );
        }

        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {

        return "=============================\n" +
                "Room Number : " + roomNumber +
                "\nFloor : " + floor +
                "\nRoom Type : " + roomType +
                "\nStatus : " + roomStatus +
                "\nPrice Per Night : " + pricePerNight +
                "\n=============================";
    }
}