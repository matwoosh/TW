package lab3.zad2;

import java.util.HashSet;

/**
 * Created by Mateusz on 16/03/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        int m = 2;
        PrintingMonitor monitor = new PrintingMonitor(m);

        HashSet<Thread> jobs = new HashSet<Thread>();
        for (int i = 0; i < n; i++) {
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
