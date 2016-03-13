package lab1;

/**
 * Created by Mateusz on 07/03/2016.
 */
public class Lunch {
    public static void spawnAndRun() {
        Buffer buffer = new Buffer();
        int threadsNo = 8;
        for (int i = 0; i < threadsNo; i++) {
            String id = Integer.toString(i);
            Producer p = new Producer(buffer, id);
            Consumer c = new Consumer(buffer, id);
            Thread pThread = new Thread(p);
            Thread cThread = new Thread(c);
            pThread.start();
            cThread.start();
        }
    }

    public static void main(String[] arg){
        spawnAndRun();
    }
}
