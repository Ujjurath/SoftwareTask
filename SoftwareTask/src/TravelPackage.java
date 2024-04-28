import java.util.ArrayList;
import java.util.List;


public class TravelPackage {

    private String name;
    private int capacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.setItinerary(new ArrayList<>());
        this.passengers = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        this.getItinerary().add(destination);
    }

    public void addPassenger(Passenger passenger) throws ExceededCapacityException {
        if (passengers.size() >= capacity) {
            throw new ExceededCapacityException("Package capacity reached.");
        }
        this.passengers.add(passenger);
    }

    public void removePassenger(int passengerNumber) {
        passengers.removeIf(passenger -> passenger.getNumber() == passengerNumber);
    }

    public Passenger findPassenger(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getNumber() == passengerNumber) {
                return passenger;
            }
        }
        return null;
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + capacity);
        System.out.println("Number of Passengers: " + passengers.size());
        System.out.println("Destinations:");
        for (Destination destination : getItinerary()) {
            System.out.println("  - " + destination.getName());
            destination.printActivities();
        }
        System.out.println();
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger List:");
        for (Passenger passenger : passengers) {
            System.out.println("  - " + passenger.getName() + " (" + passenger.getNumber() + ")");
        }
        System.out.println();
    }

    public class ExceededCapacityException extends Exception {
        public ExceededCapacityException(String message) {
            super(message);
        }
    }

    public void printActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : itinerary) {
            System.out.println("\n * " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("    - " + activity.getName() + " (" + activity.getDescription() + ")");
                if (activity.hasCost()) {
                    System.out.println("      Cost: $" + activity.getCost());
                } else {
                    System.out.println("      Cost: Free");
                }
            }
        }
    }


	public List<Destination> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Destination> itinerary) {
		this.itinerary = itinerary;
	}
}
