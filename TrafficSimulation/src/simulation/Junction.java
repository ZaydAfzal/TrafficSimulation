package simulation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import simulation.TrafficLight.Light;

import java.util.concurrent.locks.Condition;

public class Junction implements Runnable {
    private final Road[] roadsAvailable;
    private final int maxCarsPerMinute = 12;
    private TrafficLight tl;
    private volatile boolean running = true;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Junction(Road[] roads,  int [] durationArray) {
        this.tl = new TrafficLight(durationArray);
        Thread trafficLightThread = new Thread(tl);
        trafficLightThread.start();
        this.roadsAvailable = roads;
    }

    public void stopJunction() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String threadName = Thread.currentThread().getName();
                if (tl.getLightStatus() == Light.GREEN) {
                    System.out.println(threadName + ": Light status is green");

                    for (Road r : roadsAvailable) {
                        if (r.currentVehicleCount != 0) {
                            Vehicle v = r.vehicles[0];
                            System.out.println(threadName + ": Vehicle found on road " + r.getEntryPoint() + " with " + r.currentVehicleCount + " vehicles.");

                            Road nextRoad = determineNextRoad(v);
                            if (nextRoad != null && nextRoad.hasVacancy()) {
                                r.removeVehicle();
                                nextRoad.addVehicle(v);
                                System.out.println(threadName + ": Vehicle moved from road " + r.getEntryPoint() + " to road " + nextRoad.getEntryPoint());
                            } else {
                                System.out.println(threadName + ": No vacancy on the next road or no suitable road found.");
                            }
                        }
                    }

                    // Simulate time for vehicles to cross
                    Thread.sleep(1000); // Delay of 1 second
                } else {
                    // If the light is not green, wait briefly
                    System.out.println(threadName + ": Light status is not green, waiting.");
                    Thread.sleep(500); // Delay of 0.5 seconds
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Road determineNextRoad(Vehicle v) {
        char destinationJunction = v.getDestinationJunction();
        char currentJunction = v.getCurrentJunction();
        boolean atDestination = destinationJunction == currentJunction;
        
        System.out.println(Thread.currentThread().getName() + ": Determining next road for vehicle. Current junction: " + currentJunction + ", Destination junction: " + destinationJunction);

        for (Road r : roadsAvailable) {
            char entryPoint = r.getEntryPoint().charAt(0);

            if (entryPoint == currentJunction) {
                if (atDestination) {
                    if (r.getExitPoint().equals(v.getDestination())) {
                        return r;
                    }
                } else {
                    int exitPointAscii = r.getExitPoint().charAt(0);
                    if (Math.abs(currentJunction - destinationJunction) > Math.abs(destinationJunction - exitPointAscii)) {
                        return r;
                    }
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + ": No suitable road found for vehicle.");
        return null;
    }

    public TrafficLight getTrafficLight() {
        return tl;
    }
    
    public void display() {
        System.out.println("Junction with the following entry roads:");
        for (Road road : roadsAvailable) {
            road.display();
        }
    }
}