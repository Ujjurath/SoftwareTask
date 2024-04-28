import java.util.HashSet;
import java.util.Set;

public class Activity {

    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;
    private Set<Passenger> enrolledPassengers;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.enrolledPassengers = new HashSet<>();
    }


	public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public Destination getDestination() {
        return destination;
    }

    public boolean isFull() {
        return enrolledPassengers.size() >= capacity;
    }
    
    public Set<Passenger> getEnrolledPassengers() {
        return new HashSet<>(enrolledPassengers); // Create a copy to avoid direct access modification
    }

    public boolean isPassengerEnrolled(Passenger passenger) {
        return enrolledPassengers.contains(passenger);
    }


    public void signUpPassenger(Passenger passenger) throws ActivityFullException, Passenger.InsufficientBalanceException {
        if (isFull()) {
            throw new ActivityFullException("Activity capacity reached.");
        }
        enrolledPassengers.add(passenger);
        // Apply discount for gold passengers
        if (passenger.getPassengerType() == Passenger.PassengerType.GOLD) {
            passenger.deductBalance(cost * 0.1);
        } else if (passenger.getPassengerType() == Passenger.PassengerType.STANDARD) {
            passenger.deductBalance(cost);
        }
    }

    public void printDetails() {
        System.out.println("  - " + name + " (" + destination.getName() + ")");
        System.out.println("    Description: " + description);
        System.out.println("    Cost: $" + cost);
        System.out.println("    Capacity: " + capacity + " (Available: " + (capacity - enrolledPassengers.size()) + ")");
    }

  
	public static class ActivityFullException extends Exception {
        public ActivityFullException(String message) {
            super(message);
        }
    }


	public boolean hasCost() {
		// TODO Auto-generated method stub
		return true;
	}
}
