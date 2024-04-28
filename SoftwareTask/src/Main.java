
public class Main {

    public static void main(String[] args) throws Passenger.ActivityFullException, Passenger.InsufficientBalanceException, Activity.ActivityFullException, Passenger.PassengerTypeException {

        // Create a travel package
        TravelPackage TravelPackage = new TravelPackage("Island Paradise", 30);

        // Add destinations
        Destination hawaii = new Destination("Hawaii");
        Destination bali = new Destination("Bali");
        TravelPackage.addDestination(hawaii);
        TravelPackage.addDestination(bali);

        // Add activities
        Activity hawaiiSurfing = new Activity("Surfing Lesson", "Learn to ride the waves!", 50, 10, hawaii);
        Activity hawaiiVolcano = new Activity("Volcano Hike", "Explore the fiery side of the island", 75, 5, hawaii);
        Activity baliDiving = new Activity("Scuba Diving", "Discover the underwater world", 100, 15, bali);
        Activity baliCooking = new Activity("Cooking Class", "Learn to make local dishes", 30, 8, bali);

        hawaii.addActivity(hawaiiSurfing);
        hawaii.addActivity(hawaiiVolcano);
        bali.addActivity(baliDiving);
        bali.addActivity(baliCooking);

        // Create passengers
        Passenger johnDoe = new Passenger("John Doe", 1, Passenger.PassengerType.STANDARD, 1000);
        Passenger janeDoe = new Passenger("Jane Doe", 2, Passenger.PassengerType.GOLD, 500);
        Passenger aliceSmith = new Passenger("Alice Smith", 3, Passenger.PassengerType.PREMIUM);

//         Sign up passengers for activities
        johnDoe.registerActivity(hawaiiSurfing);
        janeDoe.registerActivity(baliDiving);
        aliceSmith.registerActivity(hawaiiVolcano);
        aliceSmith.registerActivity(baliCooking);

        // Add passengers to package
        try {
        	TravelPackage.addPassenger(johnDoe);
        	TravelPackage.addPassenger(janeDoe);
        	TravelPackage.addPassenger(aliceSmith);
        } catch (Exception e) {
            System.out.println("Error: Package capacity reached.");
        }

        // Print information
        TravelPackage.printItinerary();
        TravelPackage.printPassengerList();
        johnDoe.printDetails();
        janeDoe.printDetails();
        aliceSmith.printDetails();
        TravelPackage.printActivities();
    }
}
