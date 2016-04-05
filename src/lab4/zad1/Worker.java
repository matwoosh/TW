package lab4.zad1;

/**
 * Created by oswin on 22.03.16.
 */
public class Worker implements  Runnable {
    int id;
    Buffer buffer;
    Worker(Buffer buffer, int id){
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {


        while( true ){
            try {
                System.out.println("\t WORK!!");
                buffer.work(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }


}
