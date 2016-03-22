package lab3.zad3;

/**
 * Created by Mateusz on 22/03/2016.
 */
import java.util.concurrent.locks.*;

public class Waiter {
    private final Lock lock = new ReentrantLock();
    private Condition[] pair = new Condition[Restaurant.N];
    private final Condition available = lock.newCondition();
    private int[] requests = new int[Restaurant.N];
    private int currentClients = 0;

    public Waiter() {
        for(int i = 0; i < Restaurant.N; i++)
            requests[i] = 0;

        for(int i = 0; i < Restaurant.N; i++)
            pair[i] = lock.newCondition();
    }

    public void getTable(int j) {
        lock.lock();
        try {
            System.out.println("Client from pair #" + j + " has made a request.");
            requests[j]++;
            if(requests[j] < 2) {
                pair[j].await();
            }
            else {
                while(currentClients > 0)
                    available.await();
                currentClients = 2;
                System.out.println("Pair #" + j + " got the table.");
                pair[j].signal();
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void releaseTable() {
        lock.lock();
        try {
            currentClients--;
            if(currentClients == 0) {
                available.signal();
                System.out.println("\tTable has been released.");
            }
        } finally {
            lock.unlock();
        }
    }

}