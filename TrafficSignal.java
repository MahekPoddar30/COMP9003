public class TrafficSignal {

    // Creation of TrafficSignal class with appropriate methods and attributes
    private String signalID;   // ID for signal
    private String[] states = {"Red", "Yellow", "Green"}; // signal states
    private int[] timers = {10, 2, 15};  // setting timers for each state
    private int currentStateIndex = -1;  // start before first state

    //Defining a constructor that matches Main.java usage
    public TrafficSignal(String signalID) {
        if (signalID == null || signalID.trim().isEmpty()) {
            throw new IllegalArgumentException("Signal ID cannot be null or empty.");
        }
        this.signalID = signalID;
    }

    // Defining a method to cycle through the states
    public void signal() throws SimulationException{
        try {
            currentStateIndex++;

            // check for invalid transition (only 3 valid states)
            if (currentStateIndex >= states.length) {
                throw new SimulationException("Invalid state: No such state exists.");
            }

            // print current signal state and its timer
            System.out.println("Signal " + signalID + " is now " + states[currentStateIndex] +
                    " for " + timers[currentStateIndex] + " seconds.");

        } catch (SimulationException e) {
            System.out.println("Error in signal " + signalID + ": " + e.getMessage());
        }
    }

    // Now Show the current signal state
    public void showTrafficSignal() {
        if (currentStateIndex >= 0 && currentStateIndex < states.length) {
            System.out.println("Signal " + signalID + " currently at: " + states[currentStateIndex]);
        } else {
            System.out.println("Signal " + signalID + " is in an improper state.");
        }
    }

    // Adjusts timer durations from Main class
    public void setTimers(int redDuration, int yellowDuration, int greenDuration) {
        if (redDuration <= 0 || yellowDuration <= 0 || greenDuration <= 0) {
            throw new IllegalArgumentException("Timer durations must be positive.");
        }
        timers[0] = redDuration;
        timers[1] = yellowDuration;
        timers[2] = greenDuration;
    }

    public String getSignalID() {
        return signalID;
    }
    public int[] getTimers() {
        return timers;
    }
    //  Main method to test the TrafficSignal class independently
    public static void main(String[] args) {
        try {
            // First create a traffic signal object
            TrafficSignal signal = new TrafficSignal("Traffic_Signal_1");

            // Now call signal() method 3 times to show the Red, Yellow and Green state
            signal.signal(); // Red
            signal.signal(); // Yellow
            signal.signal(); // Green

            // Call fourth time to show exception (invalid transition)
            signal.signal();

            // Now show the current traffic signal state
            signal.showTrafficSignal();

        } catch (SimulationException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
