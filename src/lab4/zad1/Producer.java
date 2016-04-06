package lab4.zad1;

public class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    public void run() {
        while(true) {
            try {
                System.out.println(" Producing...");
                buffer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}


