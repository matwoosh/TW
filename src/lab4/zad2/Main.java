package lab4.zad2;

public class Main {
    private final static int M = 4; //buffer size
    public static void main(String args[]){
        Buffer buffer = new Buffer(M);

        (new Thread(new Producer(buffer,0))).start();
        for(int i=0; i< 3; i++){
            (new Thread(new Producer(buffer,i+1))).start();
            (new Thread(new Consumer(buffer,i))).start();
        }
    }
}
