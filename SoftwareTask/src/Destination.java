import java.util.ArrayList;
import java.util.List;
public class Destination {

    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void printActivities() {
        System.out.println("  Activities at " + name + ":");
        for (Activity activity : activities) {
            activity.printDetails();
        }
    }
}
