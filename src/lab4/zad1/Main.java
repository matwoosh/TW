package lab4.zad1;

public class Main {

    public static void main(String args[]){
        int M,N;
        M = 4;
        N = 3;
        Buffer buffer = new Buffer(N,M);
        (new Thread(new Producer(buffer))).start();
        (new Thread(new Worker(buffer,1))).start();
        (new Thread(new Worker(buffer,2))).start();
        (new Thread(new Consumer(buffer))).start();
    }
}
