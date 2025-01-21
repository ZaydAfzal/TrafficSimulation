package simulation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Clock  implements Runnable {
	    private static LocalDateTime dateTimeNow;
	    private static LocalTime timeNow;
	    private static int speed = 10;
	    private volatile boolean running = true; // To manage the clock thread
	    public Clock() {
	        timeNow = LocalTime.MIDNIGHT;
	    }
	    public static LocalDateTime getDateTimeNow() {
	        return dateTimeNow;
	    }
	    public static int getSpeed() {
	        return speed;
	    }
	    public static void setSpeed(int s) {
	        if (s > 0) {
	            speed = s;
	        }
	    }
	    public static LocalTime getTimeNow() {
	        return timeNow;
	    }
	    public static int compareTo(LocalTime t) {
	        return timeNow.compareTo(t);
	    }
	    public static LocalTime addTwoTimes(LocalTime a, LocalTime b) {
	        int totalSeconds = (a.toSecondOfDay() + b.toSecondOfDay()) % (24 * 3600); 
	        return LocalTime.ofSecondOfDay(totalSeconds);
	    }
	    public void stopClock() {
	        running = false;
	    }

	    @Override
	    public void run() {
	        LocalDate date = LocalDate.now();
	        while (running) {
	                dateTimeNow = LocalDateTime.of(date, timeNow); // Update dateTimeNow
	                timeNow = timeNow.plusSeconds(speed);
	                if (timeNow.equals(LocalTime.MIDNIGHT)) {
	                    date = date.plusDays(1); // Increment date at midnight
	                }
	            try {
	                Thread.sleep(1000); //after 1 second 
	            } catch (InterruptedException e) {
	                System.out.println("Clock thread interrupted.");
	                break;
	            }
	            System.out.println("Time now is " + timeNow);
	        }
	    }

	    public static LocalTime formatDuration(float hours) {
	        int totalSeconds = Math.round(hours * 3600); 
	        return LocalTime.ofSecondOfDay(totalSeconds % (24 * 3600));
	    }

	    // For String
	    public static String formatDurationSec(int totalSeconds) {
	        return LocalTime.ofSecondOfDay(totalSeconds % (24 * 3600)).toString();
	    }
	}
