package lab4.zad1;

import java.util.HashSet;

/**
 * Created by Mateusz on 23/03/2016.
 */
public class Main {
    private final static int BUFFER_LENGTH = 100;
    private final static int P = 3;
    private final static int C = 10;

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(BUFFER_LENGTH);
        HashSet<Thread> producers = new HashSet<>();
        HashSet<Thread> consumers = new HashSet<>();

        for (int i = 0; i < P; i++) {
            producers.add(new Thread(new Producer(i)));
        }

        for (int i = 0; i < C; i++) {
            consumers.add(new Thread(new Consumer(i)));
        }

        for (Thread p : producers) {
            p.start();
        }

        for (Thread c : consumers) {
            c.start();
        }

    }
}
