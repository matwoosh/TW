package lab1;

/**
 * Created by Mateusz on 07/03/2016.
 */
public class Consumer implements Runnable {
    private Buffer buffer;
    private String id;
    private int n = 10;

    public Consumer(Buffer buffer, String id) {
        this.buffer = buffer;
        this.id = id;
    }

    public void run() {
        notifyWait();
        //checkIfEmpty();
    }

    public void notifyWait(){
        for(int i = 0;  i < n;   i++) {
            String message;
            synchronized(buffer){
                message = buffer.take();
                System.out.println("Taking " + message + "(" + id + ")");
                buffer.notify();
                try {
                    if(i < 99){
                        buffer.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Consumer thread terminated");
    }

    public void checkIfEmpty(){
        synchronized(buffer){
            while (buffer.take() == null){
                try {
                    buffer.notify();
                    buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String message = buffer.take();
            buffer.put(null);
            System.out.println("Consumer" + this.id + " taking " + message);

        }
        System.out.println("Consumer" + this.id + " thread terminated");
    }
}

