package simulation;

public class Road {

    public final Vehicle[] vehicles;
    public int currentVehicleCount = 0;
    public String[] junctionsAssociated = new String[2];

    public Road(int capacity, String a, String b) {
        vehicles = new Vehicle[capacity];
        junctionsAssociated[0] = a; // Entry Point 
        junctionsAssociated[1] = b; // leave Point
    }

    public synchronized boolean hasVacancy() {
        return currentVehicleCount < vehicles.length;
    }
    
    public String getEntryPoint() {
        return junctionsAssociated[0];
    }
    
    public String getExitPoint() {
        return junctionsAssociated[1];
    }
    
    public synchronized boolean hasVehicleToRemove() {
        return currentVehicleCount > 0;
    }

    public synchronized void addVehicle(Vehicle vehicle) {
        if (hasVacancy()) {
            vehicles[currentVehicleCount] = vehicle;
            currentVehicleCount++;
            if (junctionsAssociated[1].length() == 1) {
                vehicle.setCurrentJunction(junctionsAssociated[1].charAt(0));
                System.out.println(Thread.currentThread().getName() + ": Vehicle added. New current junction: " + junctionsAssociated[1].charAt(0));
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": No vacancy to add vehicle.");
        }
    }

    public synchronized Vehicle removeVehicle() {
        if (hasVehicleToRemove()) {
            Vehicle vehicle = vehicles[0]; // to return vehicle which will be removed
            int j = 0;
            for (int i = 1; i < vehicles.length; i++, j++) {
                vehicles[j] = vehicles[i];  // remove first vehicle
            }
            vehicles[j] = null; // Clear the last spot
            currentVehicleCount--;
            System.out.println(Thread.currentThread().getName() + ": Vehicle removed. New vehicle count: " + currentVehicleCount);
            return vehicle;
        } else {
            System.out.println(Thread.currentThread().getName() + ": No vehicle to remove.");
        }
        return null; // must return 
    }
    
    public void display() {
        System.out.println("Road details:");
        System.out.println("  Entry point: " + junctionsAssociated[0]);
        System.out.println("  Exit point: " + junctionsAssociated[1]);
        System.out.println("  Current number of vehicles: " + currentVehicleCount);
    }
}