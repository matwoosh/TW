package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler extends Thread {
    private BlockingQueue<MethodRequest> queue = new LinkedBlockingQueue<MethodRequest>();


    @SuppressWarnings("deprecation")
    synchronized void done(){
        //stop();
    }

    public Scheduler() {
        start();
    }

    public void run() {
        MethodRequest mr;
        while (true) {
            try {
                mr = queue.take();
                synchronized (mr) {
                    if (mr.guard()) {
                        mr.call();

                    } else {
                        queue.add(mr);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueue(MethodRequest mr) {
        //System.out.println("Dostałem zadanie " + mr.getClass().toString());
        queue.add(mr);
    }
}
