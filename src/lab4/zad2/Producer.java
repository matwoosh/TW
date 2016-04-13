package lab4.zad2;
import java.util.Random;

public class Producer implements Runnable {
    private Buffer buffer;
    private int M, id;

    public Producer(Buffer buffer,int id) {
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
        while(true){
            try {
                amount = generator.nextInt(M)+1;
                System.out.println(" Producer " + id +" wants to put " + amount);
                buffer.put(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}


