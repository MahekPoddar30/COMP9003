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
    private static final int LANES = 3;
    private static final int INTERSECTIONS = 10;

    public IntersectionNetwork() {
        intersectionMatrix = new int[LANES][INTERSECTIONS];
        vehicles = new ArrayList<>();
    }
    
    public void moveThrough() {
        // Initialize: lane 1 and lane 3 have intersections at odd-numbered places only
        for (int lane = 1; lane <= LANES; lane++) {
            for (int i = 1; i <= INTERSECTIONS; i++) {
                if ((lane == 1 || lane == 3) && (i % 2 == 1)) {
                    intersectionMatrix[lane - 1][i - 1] = 1; // There are intersections at odd-numbered locations
                } else {
                    intersectionMatrix[lane - 1][i - 1] = 0; // No intersections
                }
            }
        }
    }

    // Simulate moving through intersections for a given lane
    public void showIntersectionStatus(int lane) {
        System.out.println("Vehicle moving through Lane " + lane + ":");

        for (int i = 1; i <= INTERSECTIONS; i++) {
            if (i % 2 == 1) { // Odd-numbered intersections (numbers 1, 3, 5, 7, 9)
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

    // Add vehicle to the simulation
    public void addVehicle(IntersectionNetwork vehicle) {
        vehicles.add(vehicle);
    }

    // Simulate movement through an invalid lane (e.g., lane 2 which has no intersections)
    public void simulateInvalidMovement() {
        try {
            for (int i = 1; i <= INTERSECTIONS; i++) {
                if (intersectionMatrix[1][i - 1] == 1) {
                    throw new SimulationException("Illegal movement on middle lane at intersection " + i);
                }
            }
            System.out.println("Simulation through middle lane completed without intersections.");
        } catch (SimulationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        IntersectionNetwork vehicle1 = new IntersectionNetwork();
        IntersectionNetwork vehicle2 = new IntersectionNetwork();
        IntersectionNetwork vehicle3 = new IntersectionNetwork();

        vehicle1.addVehicle(vehicle1);
        vehicle2.addVehicle(vehicle2);
        vehicle3.addVehicle(vehicle3);

        // Vehicle 1 tests lane = 1
        System.out.println("--- Vehicle 1 moving through Lane 1 ---");
        vehicle1.moveThrough();
        vehicle1.showIntersectionStatus(1);

        System.out.println();

        // Vehicle 2 tests lane = 3
        System.out.println("--- Vehicle 2 moving through Lane 3 ---");
        vehicle2.moveThrough();
        vehicle2.showIntersectionStatus(3);

        System.out.println();

        // Vehicle 3 test lane = 2
        System.out.println("--- Vehicle 3 simulate invalid movement on Lane 2 ---");
        vehicle3.moveThrough();
        vehicle3.showIntersectionStatus(2);
    }
}
