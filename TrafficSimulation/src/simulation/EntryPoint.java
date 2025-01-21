package simulation;

import java.util.Random;

public class EntryPoint implements Runnable {
    private static final String[] destinations = {"University", "Station", "Shopping Centre", "Industrial Park"};
    private final Road road;
    private final Random random = new Random();
    private int runLimit = 3; // Maximum number of runs

    public EntryPoint(Road road) {
        this.road = road;
    }

    
    @Override
    public void run() {
        for (int i = 0; i < runLimit; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " is running. Iteration: " + (i + 1));
                // Simulate some logic for the entry point
                if (road.hasVacancy()) {
                    Vehicle vehicle = new Vehicle(getRandomDestination());
                    vehicle.display();
                    road.addVehicle(vehicle);
                    road.display();
                }
                Thread.sleep(1000); // Simulate processing time
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                break; // Exit the loop if interrupted
            }
        }
        System.out.println(Thread.currentThread().getName() + " has finished running.");
    }

    private String getRandomDestination() {
        int rand = random.nextInt(100);
        if (rand <= 10) {
            return destinations[0];  // return University
        } else if (10 < rand && rand <= 30) {
            return destinations[1];  // return Station
        } else if (30 < rand && rand <= 60) {
            return destinations[2];  // return Shopping Centre
        } else {
            return destinations[3]; // return Industrial Park
        }
    }
    
    public void display() {
        System.out.println("Entry point is generating vehicles with destinations: ");
        for (String destination : destinations) {
            System.out.println(destination);
        }
    }
}