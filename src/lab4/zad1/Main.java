package lab4.zad1;

public class Main {
    private final static int N = 30; //buffer size
    private final static int M = 10; //processes number
    private final static int W = 8;

    public static void main(String args[]){
        Buffer buffer = new Buffer(N,M);

        Thread p = new Thread(new Producer(buffer));
        Thread[] workers = new Thread[W];
        for (int i = 1; i <= W; i++) {
            workers[i-1] = new Thread(new Worker(buffer, i));
        }
        Thread c = new Thread(new Consumer(buffer));

        p.start();
        for (Thread w : workers) {
            w.start();
        }
        c.start();


    }
}
