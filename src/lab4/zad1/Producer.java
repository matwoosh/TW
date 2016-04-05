package lab4.zad1;

public class Producer implements Runnable {
    private Buffer buffer;


    Producer(Buffer buffer){
        this.buffer = buffer;
    }
    public void run() {


        while( true ){
            try {
                System.out.println("\t PRODUCE!");
                buffer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}


