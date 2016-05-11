package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Producer extends Thread {
    private ProxyBuffer buf;
    private int maxProduceCount;
    static int produceCount=0;
    Producer(int maxProduceCount, ProxyBuffer buf) {
        this.maxProduceCount=maxProduceCount;
        this.buf=buf;
    }

    public void run(){
        while(produceCount<maxProduceCount){
            buf.add("RZECZ W BUFORZE");
            produceCount++;
        }
    }
}
