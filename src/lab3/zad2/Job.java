package lab3.zad2;

/**
 * Created by Mateusz on 16/03/2016.
 */
public class Job implements Runnable {

    private int id;
    private PrintingMonitor monitor;

    public Job(int id, PrintingMonitor monitor) {
        this.id = id;
        this.monitor = monitor;
        System.out.println("Task "+id+" has been created.");
    }

    public void run() {
        System.out.println("Queuing task no "+id);
        int printer_no;
        printer_no = monitor.reserve();
        System.out.println("Printing task "+id+" on printer no "+printer_no+".");
        monitor.release(printer_no);
    }

}
