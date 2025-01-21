package simulation;

public class Test {

    public static void main(String[] args) {
        // Initialize and start the clock thread
        Clock clock = new Clock();
        Thread clockThread = new Thread(clock, "ClockThread");
        clockThread.start();
        
        // Initialize junction threads with names
        Thread A = new Thread(Database.JunctionDb.A, "JunctionA");
        Thread B = new Thread(Database.JunctionDb.B, "JunctionB");
        Thread C = new Thread(Database.JunctionDb.C, "JunctionC");
        Thread D = new Thread(Database.JunctionDb.D, "JunctionD");

        // Initialize and start the entry point thread
        EntryPoint n = new EntryPoint(Database.RoadsDb.fromNorthToC);
        Thread entryPointThread = new Thread(n, "EntryPointNorthToC");
        entryPointThread.start();

        // Let the entry point thread run for some time
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start junction threads
        A.start();
        B.start();
        C.start();
        D.start();

        // Let the junction threads run for some time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entryPointThread.interrupt();
    }
}