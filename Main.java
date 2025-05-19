import java.util.ArrayList;
import java.util.Scanner;

// Main class to run the Urban Traffic Simulation System
public class Main {
    // Lists to hold vehicles and traffic signals
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<TrafficSignal> trafficSignals = new ArrayList<>();
    private static IntersectionNetwork intersectionNetwork = new IntersectionNetwork();

    public static void main(String[] args) {
        // Initialize intersections and traffic signals
        intersectionNetwork.moveThrough(); // Initialize intersection matrix
        initializeTrafficSignals(); // Create and add traffic signal objects to the list

        // Run the simulation
        runSimulation();
    }

    // Initializes traffic signals with default settings
    private static void initializeTrafficSignals() {
        trafficSignals.add(new TrafficSignal("Signal 1"));
        trafficSignals.add(new TrafficSignal("Signal 2"));
        trafficSignals.add(new TrafficSignal("Signal 3"));
    }

    // Runs the menu-driven simulation loop
    private static void runSimulation() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display menu options
            System.out.println("\n=== Urban Traffic Simulation Menu ===");
            System.out.println("1. View current traffic status");
            System.out.println("2. Add a vehicle");
            System.out.println("3. Remove a vehicle");
            System.out.println("4. Adjust traffic signal timers");
            System.out.println("5. Run 5-minute traffic simulation");
            System.out.println("6. Simulate invalid intersection movement");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            // Handle user input with error checking
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
                continue;
            }

            switch (choice) {
                case 1:
                    viewTrafficStatus();
                    break;
                case 2:
                    addVehicle(scanner);
                    break;
                case 3:
                    removeVehicle(scanner);
                    break;
                case 4:
                    adjustTrafficSignalTimers(scanner);
                    break;
                case 5:
                    moveVehiclesThroughIntersections();
                    break;
                case 6:
                    simulateInvalidMovement(scanner);
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting simulation...");
                    break;
                default:
                    System.out.println("Error: Invalid option. Please choose 1-7.");
            }
        }
        scanner.close();
    }

    // Displays current traffic status for vehicles, signals
    private static void viewTrafficStatus() {
        System.out.println("\n=== Current Traffic Status ===");
        // Check if there are any vehicles to display
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in simulation.");
        } else {
            System.out.println("Vehicles:");
            for (Vehicle vehicle : vehicles) {
                vehicle.showTrafficState(); // Show status of each vehicle
            }
        }
        // Show status of all traffic signals
        System.out.println("\nTraffic Signals:");
        for (TrafficSignal signal : trafficSignals) {
            signal.showTrafficSignal();
        }
    }

    // Adds a vehicle to the simulation
    private static void addVehicle(Scanner scanner) {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter vehicle type (Car/Bus/Truck): ");
        String type = scanner.nextLine().trim();
        System.out.print("Enter vehicle ID: ");
        String id = scanner.nextLine().trim();

        // Validate ID
        if (id.isEmpty()) {
            System.out.println("Error: Vehicle ID cannot be empty.");
            return;
        }
        // Check for duplicate IDs
        for (Vehicle v : vehicles) {
            if (v.getVehicleID().equals(id)) {
                System.out.println("Error: Vehicle ID " + id + " already exists.");
                return;
            }
        }

        // Create a vehicle object based on input type
        Vehicle vehicle = null;
        switch (type.toLowerCase()) {
            case "car":
                vehicle = new Car(id);
                break;
            case "bus":
                vehicle = new Bus(id);
                break;
            case "truck":
                vehicle = new Truck(id);
                break;
            default:
                System.out.println("Error: Invalid vehicle type. Use Car, Bus, or Truck.");
                return;
        }
        // Add vehicle to the list and network
        vehicles.add(vehicle);
        intersectionNetwork.addVehicle(id); // Add to IntersectionNetwork
        System.out.println("Vehicle " + type + " with ID " + id + " added successfully.");
    }

    // Removes a vehicle from the simulation
    private static void removeVehicle(Scanner scanner) {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter vehicle ID to remove: ");
        String id = scanner.nextLine().trim();

        // Remove from vehicles list
        // lambda expression
        // For each vehicle element in the list, if vehicle.getVehicleID() is equal to the given id, remove that element
        boolean removed = vehicles.removeIf(vehicle -> vehicle.getVehicleID().equals(id));
        if (removed) {
            intersectionNetwork.getVehicles().remove(id); // Remove from IntersectionNetwork
            System.out.println("Vehicle with ID " + id + " removed successfully.");
        } else {
            System.out.println("Error: Vehicle with ID " + id + " not found.");
        }
    }

    // Adjusts traffic signal timers
    private static void adjustTrafficSignalTimers(Scanner scanner) {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter signal ID (e.g., Signal 1): ");
        String id = scanner.nextLine().trim();

        // Adjust red timer
        System.out.print("Enter red timer (seconds): ");
        int red;
        try {
            red = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input for red timer.");
            scanner.nextLine();
            return;
        }

        // Adjust yellow timer
        System.out.print("Enter yellow timer (seconds): ");
        int yellow;
        try {
            yellow = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input for yellow timer.");
            scanner.nextLine();
            return;
        }

        // Adjust green timer
        System.out.print("Enter green timer (seconds): ");
        int green;
        try {
            green = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input for green timer.");
            scanner.nextLine();
            return;
        }

        // Update signal timer settings
        // Find the signal with the specified ID and try to update its red, yellow, and green light timers.
        for (TrafficSignal signal : trafficSignals) {
            if (signal.getSignalID().equals(id)) {
                try {
                    signal.setTimers(red, yellow, green);
                    System.out.println("Timers updated for Signal " + id + ": Red=" + red + "s, Yellow=" + yellow + "s, Green=" + green + "s.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Error: Signal ID " + id + " not found.");
    }

    // Simulates vehicles moving through intersections for 5 minutes
    private static void moveVehiclesThroughIntersections() {
        intersectionNetwork.moveThrough(); // Ensure intersections are initialized
        int[] signalCalls = new int[trafficSignals.size()]; // Track signal calls per signal

        // Loop over 5 minutes (each with 12 steps)
        for (int minute = 1; minute <= 5; minute++) {
            for (int step = 1; step <= 12; step++) {
                // Move all vehicles
                for (Vehicle vehicle : vehicles) {
                    vehicle.move(); // Perform vehicle movement
                    int currentLane = vehicle.getLane(); // Get the lane
                    // Display intersection status at step 2 and 12
                    if (step == 2 || step == 12) {
                        System.out.println("Vehicle " + vehicle.getVehicleID() + " in lane " + currentLane);
                        intersectionNetwork.showIntersectionStatus(currentLane);
                    }
                    // Simulate invalid movement if vehicle is in lane 2
                    if (currentLane == 2) {
                        intersectionNetwork.simulateInvalidMovement(currentLane);
                    }
                }

                // Handle traffic signals for first 3 steps
                for (int i = 0; i < trafficSignals.size(); i++) {
                    if (step <= 3 && signalCalls[i] < 3) {
                        try {
                            trafficSignals.get(i).signal(); // Transition to next signal state
                            signalCalls[i]++;
                        } catch (SimulationException e) {
                            System.out.println("Signal Error: " + e.getMessage());
                        }
                    }
                }

                // Display status at specific steps
                if (step == 2 || step == 12) {
                    viewTrafficStatus();
                }
            }
            System.out.println("One Minute Completed!");
        }
        System.out.println("Simulation completed (5 minutes)!");
    }

    // Simulates movement through a specified lane to test invalid cases
    private static void simulateInvalidMovement(Scanner scanner) {
        System.out.print("Enter lane to simulate (1-3, or 4 for invalid): ");
        try {
            int lane = scanner.nextInt();
            intersectionNetwork.simulateInvalidMovement(lane);
        } catch (Exception e) {
            System.out.println("Error: Please enter a valid number.");
            scanner.nextLine();// Clear invalid input
        }
    }
}
