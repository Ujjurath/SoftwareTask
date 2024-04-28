//import Activity.ActivityFullException;

import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private String name;
    private int number;
    public PassengerType passengerType;
    private List<Activity> enrolledActivities;
    private double balance; // Only for STANDARD and GOLD passengers
    
    
    public enum PassengerType {
        STANDARD, GOLD, PREMIUM
    }
    
//    Activity A1 = new Activity(name, name, balance, number, null);

    public Passenger(String name, int number, PassengerType passengerType) {
        this.name = name;
        this.number = number;
        this.passengerType = passengerType;
        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            this.balance = 0.0; // Initialize balance for applicable types
        }
    }

    public Passenger(String name, int number, PassengerType passengerType, double balance) {
        this(name, number, passengerType);
        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance is only applicable for Standard and Gold passengers.");
        }
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public double getBalance() {
        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            return balance;
        } else {
            throw new UnsupportedOperationException("Balance is not available for this passenger type.");
        }
    }

    public void deductBalance(double amount) throws InsufficientBalanceException {
        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            if (balance - amount < 0) {
                throw new InsufficientBalanceException("Insufficient balance for activity.");
            }
            balance -= amount;
        } else {
            throw new UnsupportedOperationException("Balance deduction is not applicable for this passenger type.");
        }
    }

    public void printDetails() {
        System.out.println("  - " + name + " (" + number + ")");
        System.out.println("    Type: " + passengerType);
        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            System.out.println("    Balance: $" + balance);
        }
    }

    public static class InsufficientBalanceException extends Exception {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }
    
    public static class ActivityFullException extends Exception {
        public ActivityFullException(String message) {
            super(message);
        }
    }
    
    public static class PassengerTypeException extends Exception {
        public PassengerTypeException(String message) {
            super(message);
        }
    }
    
    public List<Activity> getEnrolledActivities() {
        return new ArrayList<>(enrolledActivities); // Create a copy to avoid direct access modification
    }

    public boolean isEnrolledIn(Activity activity) {
        return enrolledActivities.contains(activity);
    }


    public void registerActivity(Activity activity) throws ActivityFullException, InsufficientBalanceException, PassengerTypeException {
    	assert enrolledActivities != null;
    	if (enrolledActivities == null) {
    	    enrolledActivities = new ArrayList<>();
    	}
    	enrolledActivities.add(activity);

    	try {
			if (activity.isFull()) {
			    throw new ActivityFullException("Activity capacity reached.");
			}
		} catch (Passenger.ActivityFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD) {
            if (balance - activity.getCost() < 0) {
                throw new InsufficientBalanceException("Insufficient balance for activity.");
            }
        } else if (passengerType != PassengerType.PREMIUM) {
            throw new PassengerTypeException("Only Standard, Gold, and Premium passengers can register for activities.");
        }

        enrolledActivities.add(activity);

        if (passengerType == PassengerType.STANDARD) {
            balance -= activity.getCost();
        } else if (passengerType == PassengerType.GOLD) {
            balance -= activity.getCost() * 0.1; // Apply 10% discount
        }
    }



}
