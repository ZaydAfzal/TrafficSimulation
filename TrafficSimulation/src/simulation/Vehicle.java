package simulation;
import java.time.LocalTime;
public class Vehicle {

    private final String destination;
    private char destinationJunction;
    private char currentJunction;
    private final LocalTime entryTime;
    private LocalTime parkingTime;

    public Vehicle(String des) {
        this.destination = des;
        this.entryTime = Clock.getTimeNow();
        setDestinationJunction();
        display();
    }

    public String getDestination() {
        return destination;
    }
    public void setDestinationJunction() {
    	switch(destination) {
    	case "University" , "Station":
    		destinationJunction = 'D';
    		break;
    	case "Shopping Centre":
    		destinationJunction = 'C';
    		break;
    	case "Industrial Park":
    		destinationJunction = 'A';
    		break;
    		
    	}
    }
    public char getDestinationJunction() {
    	return destinationJunction;
    }
    public void setCurrentJunction(char s) {
    	currentJunction = s;
    }
    public char getCurrentJunction() {
    	return currentJunction;
    }
    public LocalTime getEntryTime() {
        return entryTime;
    }

    public LocalTime getParkTime() {
        return parkingTime;
    }

    public void setParkTime() {
        this.parkingTime = Clock.getTimeNow();
    }
    
    public void display() {
        System.out.println("Destination of the vehicle is " + destination);
        System.out.println("Destination Junction of the vehicle is " + destinationJunction);
        System.out.println("Entry time of the vehicle is " + entryTime);
        System.out.println("Parking time of the vehicle is " + (parkingTime != null ? parkingTime : "Not parked yet"));
    }
}