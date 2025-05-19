class Truck extends Vehicle {
    public Truck(String vehicleID) {
        super(vehicleID);
        setVelocity(90); // 90 meters per 5 seconds
    }

    @Override
    public int velocity() {
        return getVelocity() / 5; // 18 m/s
    }

    @Override
    public void move() {
        super.move();
        if (getMovesCount() == 3) {
            System.out.println("Truck " + getVehicleID() + " checks load stability.");
        }
    }
}