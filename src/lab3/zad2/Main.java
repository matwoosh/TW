package lab3.zad2;

import java.util.HashSet;

/**
 * Created by Mateusz on 16/03/2016.
 */
public class Main {
    private final static int M = 4;
    private final static int N = 100;

    public static void main(String[] args) throws InterruptedException {
        PrintingMonitor monitor = new PrintingMonitor(M);
        HashSet<Thread> jobs = new HashSet<>();

        for (int i = 0; i < N; i++) {
            jobs.add(new Thread(new Job(i, monitor)));
        }

        for (Thread job : jobs) {
            job.start();
        }

        for (Thread job : jobs) {
            job.join();
        }

        System.out.println("Printing done.");
    }
}
