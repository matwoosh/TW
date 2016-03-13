package lab2;

/**
 * Created by Mateusz on 13/03/2016.
 */
public class Shop {
    protected static final CountingSemaphore countingSemaphore = new CountingSemaphore(
            3);

    static class Customer extends Thread {
        private String id;
        public Customer(String id) {
            this.id = id;
        }
        public void run() {
            shop();
        }
        public void shop(){
            countingSemaphore.waitForNotify();
            System.out.println(id + " starts shopping.");
            for (int x = 0; x< 100000000; x++) {
            }
            countingSemaphore.notifyToWakeup();
            System.out.println(id + " done.");
        }
    }

    public static void main(String[] args) {
        int threadsNo = 100;
        for (int x = 0; x < threadsNo; x++) {
            String id = Integer.toString(x);
            Customer c = new Customer(id);
            Thread cThread = new Thread(c);
            cThread.start();
        }
    }
}
