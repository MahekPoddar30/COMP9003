// UrbanTrafficSimulation.java
public class UrbanTrafficSimulation {
    
    // Main class to run the Urban Traffic Simulation System
    public static void main(String[] args) {
        // Creating instances of different vehicle types
        Vehicle car = new Car("Car1");
        Vehicle bus = new Bus("Bus1");
        Vehicle truck = new Truck("Truck1");

        // Simulating movements for the 5 iterations
        for (int i = 0; i < 2; i++) {
            car.move(); // Move the car
            bus.move(); // Move the bus
            truck.move(); // Move the truck
            
            // Displaying Traffic State after 2 moves
            car.showTrafficState();
            bus.showTrafficState();
            truck.showTrafficState();
            System.out.println(); // Adding a line break for clarity

        for (int i = 0; i < 3; i++) {
            car.move(); // Move the car
            bus.move(); // Move the bus
            truck.move(); // Move the truck
            
            //  Displaying Traffic State after 5 moves
            car.showTrafficState();
            bus.showTrafficState();
            truck.showTrafficState();
            System.out.println(); // Adding a line break for clarity
        }
    }
}

// Abstract class Vehicle
abstract class Vehicle {
    // All the Attributes
    private String vehicleID; // Unique identifier for the vehicle
    private int velocity; // Default velocity of the vehicle
    private int[] lanes; // Array to hold lane values
    private int laneIndex; // Current lane index
    private int currentPosition; // Current position in meters

    // Constructor used to initialize vehicle attributes
    public Vehicle(String vehicleID) {
        this.vehicleID = vehicleID; // Setting the vehicle ID
        this.velocity = 100; // Default velocity in meters per 5 seconds
        this.lanes = new int[]{1, 2, 3}; // Initializing lanes
        this.laneIndex = 0; // Starting in lane 1 (index 0)
        this.currentPosition = 0; // Starting at position 0
    }

    // Method used  to calculate velocity in meters per second
    public int velocity() {
        return velocity / 5; // Converting velocity to m/s
    }

    // Method used to change lane with error handling
    public void changeLane(int newLane) throws SimulationException {
        // Checking if the new lane is valid
        if (newLane < 1 || newLane > lanes.length) {
            throw new SimulationException("Invalid lane transition: " + newLane);
        }
        this.laneIndex = newLane - 1; // Updating to new lane index
    }

    // Moving method to update vehicle status
    public void move() {
        try {
            // Updating the current position based on velocity
            currentPosition += velocity();
            // Changing the lane randomly for demonstration
            int newLane = (int) (Math.random() * lanes.length) + 1; // Random lane between 1 and 3
            changeLane(newLane); // Attempt to change lane
        } catch (SimulationException e) {
            // Printing the error message if lane change is invalid
            System.out.println(e.getMessage());
        }
    }

    // Method to show the current traffic state of the vehicle
    public void showTrafficState() {
        System.out.println("Vehicle ID: " + vehicleID + 
                           ", Velocity: " + velocity() + 
                           " m/s, Lane: " + lanes[laneIndex] + 
                           ", Current Position: " + currentPosition + " meters");
    }

    // Getter for vehicle ID
    public String getVehicleID() {
        return vehicleID;
    }
}

// Custom Exception Class for handling simulation errors
class SimulationException extends Exception {
    public SimulationException(String message) {
        super(message); // Calling the superclass constructor with the error message
    }
}

// Car Class extending Vehicle
class Car extends Vehicle {
    public Car(String vehicleID) {
        super(vehicleID); // Calling the constructor of the Vehicle class
    }

    @Override
    public void move() {
        // Specific behavior for Car (if needed)
        super.move(); // Calling the move method from the Vehicle class
    }
}

// The Bus Class extending Vehicle
class Bus extends Vehicle {
    public Bus(String vehicleID) {
        super(vehicleID); // Calling the constructor of the Vehicle class
    }

    @Override
    public void move() {
        // Specific behavior for Bus (if needed)
        super.move(); // Calling the move method from the Vehicle class
    }
}

// Truck Class extending Vehicle
class Truck extends Vehicle {
    public Truck(String vehicleID) {
        super(vehicleID); // Call the constructor of the Vehicle class
    }

    @Override
    public void move() {
        // The specific behavior for Truck (if needed)
        super.move(); // Calling the move method from the Vehicle class
    }
}
