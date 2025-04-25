import java.util.*;

// Custom exception class for simulation errors
class SimulationException extends Exception {
    public SimulationException(String message) {
        super(message);
    }
}

// Class modeling the road network and vehicle movement
class IntersectionNetwork {
    private int[][] intersectionMatrix;
    private List<IntersectionNetwork> vehicles;
    private int[] status; // status[i] = 1 if vehicle turns at i-th intersection
    private static final int LANES = 3;
    private static final int INTERSECTIONS = 10;

    public IntersectionNetwork() {
        intersectionMatrix = new int[LANES][INTERSECTIONS];
        vehicles = new ArrayList<>();
        status = new int[INTERSECTIONS];

        // Only lane 0 and lane 2 (1 and 3 in human terms) have intersections
        for (int i = 0; i < INTERSECTIONS; i++) {
            if (i % 2 == 0) { // intersections 1, 3, 5, ... 
                intersectionMatrix[0][i] = 1;
                intersectionMatrix[2][i] = 1;
            }
        }
    }

    // Simulate moving a vehicle through the network
    public void moveThrough() {
        for (int i = 0; i < INTERSECTIONS; i++) {
            if (i % 2 == 0) {
                status[i] = 1; // Move through odd-numbered intersections
            } else {
                status[i] = 0; // Skip even-numbered intersections
            }
        }
    }

    // Display the status of intersections for the current object
    public void showIntersectionStatus() {
        System.out.println("Vehicle Intersection Status:");
        for (int i = 0; i < INTERSECTIONS; i++) {
            System.out.println("Intersection " + (i + 1) + ": " + (status[i] == 1 ? "Turned" : "Skipped"));
        }
    }

    // Add vehicle to the simulation
    public void addVehicle(IntersectionNetwork vehicle) {
        vehicles.add(vehicle);
    }

    // Simulate movement through an invalid lane (e.g., lane 1 which has no intersections)
    public void simulateInvalidMovement() {
        try {
            for (int i = 0; i < INTERSECTIONS; i++) {
                if (intersectionMatrix[1][i] == 1) {
                    throw new SimulationException("Illegal movement on middle lane at intersection " + (i + 1));
                }
            }
            System.out.println("Simulation through middle lane completed without intersections.");
        } catch (SimulationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
