package lab6.app;

import lab6.model.Proxy;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Producer extends Thread {
    private Proxy buf;
    private int maxProduceCount;
    static int produceCount = 0;

    Producer(int maxProduceCount, Proxy buf) {
        this.maxProduceCount = maxProduceCount;
        this.buf = buf;
    }

    public void run() {
        while (produceCount < maxProduceCount) {
            buf.add("Some buffer stuff");
            produceCount++;
        }
    }
}
