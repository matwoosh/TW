package lab2.zad3;

public class CountingSemaphore {
    String id;
    private int value = 0;
    private int waitCount = 0;
    private int notifyCount = 0;

    public CountingSemaphore(String id, int initial) {
        this.id = id;
        if (initial > 0) {
            value = initial;
        }
    }

    public synchronized void waitForNotify() {
        if (value <= waitCount) {
            waitCount++;
            try {
                do {
                    System.out.println("Cannot add another basket");
                    wait();
                } while (notifyCount == 0);
            } catch (InterruptedException e) {
                System.out.println("Cannot add another basket!!!");
                notifyAll();
            } finally {
                waitCount--;
            }
            notifyCount--;
        }
        value--;
        System.out.println("Semaphore_" + id +": " + value);
    }

    public synchronized void notifyToWakeup() {
        value++;
        if (waitCount > notifyCount) {
            notifyCount++;
            System.out.println("Semaphore_" + id +": " + value);
            notifyAll();
        }
    }
}
