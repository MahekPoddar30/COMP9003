import java.util.ArrayList;
import java.util.List;



// Class modeling the road network and vehicle movement simulation
public class IntersectionNetwork {
    private int[][] intersectionMatrix; // 2D array to model lanes and intersections
    private List<String> vehicles; // List to store vehicle IDs for simulation
    private static final int LANES = 3; // Number of lanes
    private static final int INTERSECTIONS = 10; // Number of intersections

    // Constructor initializes the intersection matrix and vehicle list
    public IntersectionNetwork() {
        intersectionMatrix = new int[LANES][INTERSECTIONS];
        vehicles = new ArrayList<>();
    }

    // Initializes the intersection matrix with intersections on lanes 1 and 3 at odd-numbered positions
    public void moveThrough() {
        for (int lane = 0; lane < LANES; lane++) {
            for (int i = 0; i < INTERSECTIONS; i++) {
                // Intersections exist on lanes 1 and 3 (0-based: 0 and 2)
                // at odd-numbered intersections (0-based: 0, 2, 4, 6, 8)
                if ((lane == 0 || lane == 2) && (i % 2 == 0)) {
                    intersectionMatrix[lane][i] = 1; // Intersection present
                } else {
                    intersectionMatrix[lane][i] = 0; // No intersection
                }
            }
        }
    }

    // Displays intersection status for a given lane
    public void showIntersectionStatus(int lane) {
        // Validate lane input
        if (lane < 1 || lane > LANES) {
            System.out.println("Error: Invalid lane " + lane);
            return;
        }
        System.out.println("Vehicle moving through Lane " + lane + ":");
        for (int i = 1; i <= INTERSECTIONS; i++) {
            if (i % 2 == 1) { // Odd-numbered intersections (1, 3, 5, 7, 9)
                if (intersectionMatrix[lane - 1][i - 1] == 1) {
                    System.out.println("Intersection " + i + ": Turned (through intersection)");
                } else {
                    System.out.println("Intersection " + i + ": No intersection to turn");
                }
            } else {
                System.out.println("Intersection " + i + ": Skipped (even-numbered intersection)");
            }
        }
    }

    // Adds a vehicle to the simulation by ID
    public void addVehicle(String vehicleId) {
        vehicles.add(vehicleId);
    }

    // Simulates movement through an invalid lane or intersection
    public void simulateInvalidMovement(int lane) {
        try {
            // Check for invalid lane
            if (lane < 1 || lane > LANES) {
                throw new SimulationException("Attempted movement on non-existent lane " + lane);
            }
            // Check for intersections on the specified lane (e.g., lane 2 should have none)
            for (int i = 1; i <= INTERSECTIONS; i++) {
                if (intersectionMatrix[lane - 1][i - 1] == 1) {
                    throw new SimulationException("Illegal intersection on lane " + lane + " at intersection " + i);
                }
            }
            System.out.println("Simulation through lane " + lane + " completed without intersections.");
        } catch (SimulationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Getter for intersection matrix
    public int[][] getIntersectionMatrix() {
        return intersectionMatrix;
    }

    // Getter for vehicles list
    public List<String> getVehicles() {
        return vehicles;
    }

    // Main method for testing IntersectionNetwork functionality
    public static void main(String[] args) {
        IntersectionNetwork network = new IntersectionNetwork();

        // Initialize the road network
        network.moveThrough();

        // Add vehicles for simulation
        network.addVehicle("Vehicle1");
        network.addVehicle("Vehicle2");
        network.addVehicle("Vehicle3");

        // Test lane 1
        System.out.println("--- Testing Lane 1 ---");
        network.showIntersectionStatus(1);

        System.out.println();

        // Test lane 3
        System.out.println("--- Testing Lane 3 ---");
        network.showIntersectionStatus(3);

        System.out.println();

        // Test invalid movement on lane 2
        System.out.println("--- Testing Invalid Movement on Lane 2 ---");
        network.simulateInvalidMovement(2);

        System.out.println();

        // Test invalid lane (e.g., lane 4)
        System.out.println("--- Testing Invalid Lane 4 ---");
        network.simulateInvalidMovement(4);
    }
}
