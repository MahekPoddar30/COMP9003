import java.util.ArrayList;
import java.util.Scanner;

// Main class to run the Urban Traffic Simulation System
public class Main {
    // List to hold vehicles and traffic signals
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<TrafficSignal> trafficSignals = new ArrayList<>();
    private static IntersectionNetwork intersectionNetwork = new IntersectionNetwork();

    public static void main(String[] args) {
        // Initialize traffic signals
        initializeTrafficSignals();

        // Simulation loop
        runSimulation();
    }

    // Method to initialize traffic signals
    private static void initializeTrafficSignals() {
        // Create and add traffic signals to the list
        trafficSignals.add(new TrafficSignal("Signal 1"));
        trafficSignals.add(new TrafficSignal("Signal 2"));
        trafficSignals.add(new TrafficSignal("Signal 3"));
    }

    // Method to run the simulation
    private static void runSimulation() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display menu options
            System.out.println("Urban Traffic Simulation Menu:");
            System.out.println("1. View current traffic status");
            System.out.println("2. Add a vehicle");
            System.out.println("3. Remove a vehicle");
            System.out.println("4. Adjust traffic signal timers");
            System.out.println("5. Move vehicles through intersections");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
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
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // Method to view current traffic status
    private static void viewTrafficStatus() {
        System.out.println("Current Traffic Status:");
        for (Vehicle vehicle : vehicles) {
            vehicle.showTrafficState();
        }
        for (TrafficSignal signal : trafficSignals) {
            signal.showTrafficSignal();
        }
        //intersectionNetwork.showIntersectionStatus(); // Show intersection status
        //Loop through all 3 lanes to show intersection status
        for (int lane = 1; lane <= 3; lane++) {
           intersectionNetwork.showIntersectionStatus(lane);
        }
    }

    // Method to add a vehicle
    private static void addVehicle(Scanner scanner) {
        System.out.print("Enter vehicle type (Car/Bus/Truck): ");
        String type = scanner.next();
        System.out.print("Enter vehicle ID: ");
        String id = scanner.next();

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
                System.out.println("Invalid vehicle type.");
                return;
        }
        vehicles.add(vehicle);
        System.out.println(type + " added with ID: " + id);
    }

    // Method to remove a vehicle
    private static void removeVehicle(Scanner scanner) {
        System.out.print("Enter vehicle ID to remove: ");
        String id = scanner.next();
        vehicles.removeIf(vehicle -> vehicle.getVehicleID().equals(id));
        System.out.println("Vehicle with ID: " + id + " removed.");
    }

    // Method to adjust traffic signal timers (placeholder)
    private static void adjustTrafficSignalTimers(Scanner scanner) {
        // Implementation for adjusting traffic signal timers can be added here
        System.out.println("Adjusting traffic signal timers (not implemented).");
    }

    // Method to move vehicles through intersections
    private static void moveVehiclesThroughIntersections() {
        for (Vehicle vehicle : vehicles) {
            vehicle.move(); // Move each vehicle
        }
    }
}