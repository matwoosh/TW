package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler extends Thread {
    private BlockingQueue<MethodRequest> queue = new LinkedBlockingQueue<>();

    public Scheduler() {
        start();
    }

    public void run() {
        MethodRequest mr;
        while (true) {
            try {
                mr = queue.take();
                if (mr.guard()) {
                    mr.call();

                } else {
                    queue.add(mr);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueue(MethodRequest mr) {
        //System.out.println("Received task " + mr.getClass().toString());
        queue.add(mr);
    }
}
