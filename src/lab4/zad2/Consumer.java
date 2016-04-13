package lab4.zad2;
import java.util.Random;


public class Consumer implements Runnable {
    private Buffer buffer;
    private int id, M;

    public Consumer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.M = buffer.size();
        this.id = id;

    }

    public int getId(){
        return this.id;
    }

    public void run() {
        Random generator = new Random();
        int amount;
        for (;;) {
            try {
                amount = generator.nextInt(M)+1;
                System.out.println(" Consumer " + id +" wants to take "+ amount);
                buffer.take(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}
