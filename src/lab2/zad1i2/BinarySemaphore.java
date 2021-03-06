package lab2.zad1i2;

public class BinarySemaphore {
    private boolean locked = false;

    BinarySemaphore(int init) {
        locked = (init == 0);
    }

    public synchronized void waitForNotify() throws InterruptedException {
        while (locked) {
            wait();
        }
        locked = true;
    }

    public synchronized void notifyToWakeup() {
        if (locked) {
            notifyAll();
        }
        locked = false;
    }

}
