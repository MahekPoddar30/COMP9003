abstract class Vehicle {
    // Attributes
    private String vehicleID; // Unique identifier for the vehicle
    private int velocity; // Velocity in meters per 5 seconds
    private int[] lanes; // Array to hold lane values (1, 2, 3)
    private int laneIndex; // Current lane index
    private int currentPosition; // Current position in meters
    private int movesCount; // Track number of move() calls

    // Constructor to initialize vehicle attributes
    public Vehicle(String vehicleID) {
        this.vehicleID = vehicleID;
        this.velocity = 100; // Default velocity
        this.lanes = new int[]{1, 2, 3};
        this.laneIndex = 0; // Start in lane 1
        this.currentPosition = 0;
        this.movesCount = 0;
    }

    // Calculates velocity in meters per second
    public int velocity() {
        return velocity / 5;
    }

    // Changes lane with error handling
    public void lane(int newLane) throws SimulationException {
        if (newLane < 1 || newLane > lanes.length) {
            throw new SimulationException("Invalid lane transition: " + newLane);
        }
        this.laneIndex = newLane - 1;
    }

    // Updates vehicle position and lane, called every 5 seconds
    public void move() {
        try {
            int newLane = lanes[(laneIndex + 1) % lanes.length]; // Cycle through lanes 1-2-3
            lane(newLane);
            movesCount++;
            if (movesCount == 5) {
                currentPosition = velocity() * 5; // Final position after 5 moves
            }
        } catch (SimulationException e) {
            System.out.println(e.getMessage());
        }
    }

    // Displays current traffic state
    public void showTrafficState() {
        System.out.println("Vehicle ID: " + vehicleID +
                ", Velocity: " + velocity() + " m/s, Lane: " + lanes[laneIndex] +
                ", Current Position: " + currentPosition + " meters");
    }

    // Getters and setters
    public String getVehicleID() { return vehicleID; }
    public int getVelocity() { return velocity; }
    public void setVelocity(int velocity) { this.velocity = velocity; }
    public int[] getLanes() { return lanes; }
    public int getLaneIndex() { return laneIndex; }
    public void setLaneIndex(int laneIndex) { this.laneIndex = laneIndex; }
    public int getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(int currentPosition) { this.currentPosition = currentPosition; }
    public int getLane() { return lanes[laneIndex]; }
    public int getMovesCount() { return movesCount; }
}