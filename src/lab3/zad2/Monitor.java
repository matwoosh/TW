package lab3.zad2;

/**
 * Created by Mateusz on 16/03/2016.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private Lock lock = new ReentrantLock();
    private Condition not_full = lock.newCondition();
    List<Boolean> available_printers;

    public Monitor(int m) {
        available_printers = new ArrayList<>();
        for (int i=0; i<m; i++) {
            available_printers.add(true);
            System.out.println("Printer " + i + " has been created.");
        }
    }

    public int reserve() {
        int printer_no;
        lock.lock();
        while ((printer_no = available_printers.indexOf(true)) == -1) {
            // All printers have been reserved
            System.out.println("All printers have been reserved!!!");
            try {
                not_full.await();
            } catch (InterruptedException e) {}
        }
        // An available printer has been found
        available_printers.set(printer_no, false);
        lock.unlock();
        return printer_no;
    }

    public void release(int printer_no) {
        lock.lock();
        available_printers.set(printer_no, true);
        not_full.signal();
        lock.unlock();
    }


}
