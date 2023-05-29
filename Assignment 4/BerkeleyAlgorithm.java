//BERKELEY ALGORITHM
import java.util.Scanner;

class ClockServer {

    int[] clocks;

    public ClockServer(int numClocks) {
        clocks = new int[numClocks];
    }

    public void setClock(int index, int clock) {
        clocks[index] = clock;
    }

    public int[] getClocks() {
        return clocks;
    }

    public int synchronizeClocks() {
        int sum = 0;
        for (int i = 0; i < clocks.length; i++) {
            sum += clocks[i];
        }


        int average = sum / clocks.length;

        return average;
    }
}

class ClockClient {

    int clock;

    public ClockClient(int clock) {
        this.clock = clock;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}

public class BerkeleyAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get number of clocks from the user
        System.out.print("Enter the number of clocks: ");
        int numClocks = scanner.nextInt();
        ClockServer server = new ClockServer(numClocks);

        // Get clock values from the user and set them in the server
        System.out.println("Enter the clock values:");
        for (int i = 0; i < numClocks; i++) {
            int clock = scanner.nextInt();
            server.setClock(i, clock);
        }

        // Display initial clocks
        int[] initialClocks = server.getClocks();
        System.out.print("Initial Clocks: ");
        displayClocks(initialClocks);

        // Perform clock synchronization
        int averageClock = server.synchronizeClocks();

        // Display synchronized clocks
        System.out.println("Synchronized Clocks: " + averageClock);


        // Update clocks for clients
        ClockClient[] clients = new ClockClient[numClocks];
        for (int i = 0; i < numClocks; i++) {
            clients[i] = new ClockClient(scanner.nextInt());
        }

        for (int i = 0; i < clients.length; i++) {
            clients[i].setClock(averageClock);
        }


        // Display updated clocks for clients
        System.out.println("Updated Clocks for Clients: ");
        for (int i = 0; i < clients.length; i++) {
            System.out.println(clients[i].getClock());
        }


        scanner.close();
    }

    private static void displayClocks(int[] clocks) {
        System.out.print("[");
        for (int i = 0; i < clocks.length; i++) {
            System.out.print(clocks[i]+" ");
            
        }
        System.out.println("]");
    }
}
