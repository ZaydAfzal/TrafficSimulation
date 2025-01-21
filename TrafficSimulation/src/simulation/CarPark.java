package simulation;

public class CarPark implements Runnable {

    private final Vehicle[] vehicles;
    private int count = 0;
    private final Road road;

    public CarPark(int capacity, Road road) {
        vehicles = new Vehicle[capacity];
        this.road = road;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (road.hasVehicleToRemove() && count < vehicles.length) {
                    Vehicle vehicle = road.removeVehicle();
                    if (vehicle != null) {
                        Thread.sleep(12000); // Simulate time to admit car
                        vehicle.setParkTime();
                        vehicles[count++] = vehicle;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void display() {
        System.out.println("Car park has a capacity of " + vehicles.length + " vehicles.");
        System.out.println("Current number of vehicles in the car park: " + count);
    }
}