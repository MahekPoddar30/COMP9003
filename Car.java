// Car class extending Vehicle
class Car extends Vehicle {
    public Car(String vehicleID) {
        super(vehicleID);
        setVelocity(100); // 100 meters per 5 seconds
    }

    @Override
    public void move() {
        super.move();
        if (getMovesCount() == 5) {
            System.out.println("Car " + getVehicleID() + " completes journey.");
        }
    }
}