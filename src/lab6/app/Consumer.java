package lab6.app;

import lab6.model.Proxy;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Consumer extends Thread {
    private Proxy buf;
    private int maxConsumeCount;
    static int consumeCount = 0;

    Consumer(int maxConsumeCount, Proxy buf) {
        this.maxConsumeCount = maxConsumeCount;
        this.buf = buf;
    }

    public void run() {
        while (consumeCount < maxConsumeCount) {
            buf.take();
            consumeCount++;
        }
    }
}
