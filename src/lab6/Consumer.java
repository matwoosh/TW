package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Consumer extends Thread {
    private ProxyBuffer buf;
    private int maxConsumeCount;
    static int consumeCount=0;
    Consumer(int maxConsumeCount, ProxyBuffer buf) {
        this.maxConsumeCount=maxConsumeCount;
        this.buf=buf;
    }

    public void run(){
        while(consumeCount<maxConsumeCount){
            buf.take();
            consumeCount++;
        }
    }
}
