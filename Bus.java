class Bus extends Vehicle {
    public Bus(String vehicleID) {
        super(vehicleID);
        setVelocity(80); // 80 meters per 5 seconds
    }

    @Override
    public int velocity() {
        return getVelocity() / 5; // 16 m/s
    }

    @Override
    public void move() {
        super.move();
        if (getMovesCount() % 2 == 0) {
            System.out.println("Bus " + getVehicleID() + " stops at a bus stop.");
        }
    }
}
