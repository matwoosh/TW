package lab2;

public class Race {

    protected static final BinarySemaphore binarySemaphore0 = new BinarySemaphore(
                0);
    protected static final BinarySemaphore binarySemaphore1 = new BinarySemaphore(
                1);

    static private int n = 0;

    public Race() {
        n = 0;
    }

    static class Incrementer extends Thread {
        public void run() {
            inc();
        }
        public  void inc(){
                for (int x = 0; x< 10; x++) {
                        try {
                            binarySemaphore0.waitForNotify();
                            n = n + 1;
                            System.out.println(n);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        binarySemaphore1.notifyToWakeup();
                    }
                System.out.println(n);
            }
    }

    static class Decrementer extends Thread {
        public void run() {
            dec();
        }
        public void dec(){
                for (int x = 0; x< 10; x++){
                        try {
                            binarySemaphore1.waitForNotify();
                            n = n- 1;
                            System.out.println(n);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        binarySemaphore0.notifyToWakeup();
                    }
                System.out.println(n);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
        Incrementer i = new Incrementer();
        Decrementer d = new Decrementer();
        Thread t1 = new Thread(i);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();
    }

}