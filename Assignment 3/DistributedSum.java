//A3 MPI COMMUNICATION

import mpi.*;

public class DistributedSum {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank(); // Get the rank of the current process
        int size = MPI.COMM_WORLD.Size(); // Get the total number of processes

        int N = 100; // Total number of elements in the array
        int n = size; // Number of processors (assumed equal to the number of processes)

        // Calculate the number of elements to distribute to each process
        int elementsPerProcess = N / n;

        // Create the array and initialize it
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }

        // Calculate the starting and ending indices for each process
        int startIndex = rank * elementsPerProcess;
        int endIndex = startIndex + elementsPerProcess - 1;

        // Calculate the local sum for each process
        int localSum = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            localSum += array[i];
        }

        // Display the intermediate sum calculated at each process
        System.out.println("Process " + rank + " - Local Sum: " + localSum);

        // Perform global sum reduction using MPI
        int[] globalSum = new int[1];
        MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0, globalSum, 0, 1, MPI.INT, MPI.SUM, 0);

        // Display the final sum calculated at the root process
        if (rank == 0) {
            System.out.println("Final Sum: " + globalSum[0]);
        }

        MPI.Finalize();
    }
}
