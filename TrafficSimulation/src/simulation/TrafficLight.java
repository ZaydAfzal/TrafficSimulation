package simulation;

public class TrafficLight implements Runnable {
	    public enum Light {
	        RED, YELLOW, GREEN
	    }

	    private Light currentLight;
	    private final int redLightDuration; // Duration for red light in ms
	    private final int yellowLightDuration; // Duration for yellow light in ms
	    private final int greenLightDuration; // Duration for green light in ms
	    private boolean running;

	    public TrafficLight(int[] duration) {
	        this.redLightDuration = duration[0];
	        this.yellowLightDuration = duration[1];
	        this.greenLightDuration = duration[2];
	        this.currentLight = Light.RED;
	        this.running = true;
	    }

	    public void stopTrafficLight() {
	        running = false;
	    }

	    @Override
	    public void run() {
	        while (running) {
	            try {
	                switch (currentLight) {
	                    case RED:
	                        Thread.sleep(redLightDuration);
	                        currentLight = Light.GREEN;
	                        break;
	                    case YELLOW:
	                        Thread.sleep(yellowLightDuration);
	                        currentLight = Light.RED;
	                        break;
	                    case GREEN:
	                        Thread.sleep(greenLightDuration);
	                        currentLight = Light.YELLOW;
	                        break;
	                }
	            } catch (InterruptedException e) {
	                System.out.println("Traffic light thread interrupted.");
	                running = false;
	            }
	        }
	    }

	    public void display() {
	        System.out.println("Current light: " + currentLight);
	    }
	    public Light getLightStatus() {
	        return(currentLight);
	    }
	    
}
