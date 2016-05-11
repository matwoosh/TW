package lab6.app;

import lab6.model.Proxy;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Runner {

    Proxy buf;
    Consumer[] consumers;
    Producer[] producers;

    Runner(int bufSize, int workerCount, int workAmmount) {
        buf = new Proxy(bufSize);
        consumers = new Consumer[workerCount];
        producers = new Producer[workerCount];
        for (int i = 0; i < workerCount; i++) {
            consumers[i] = new Consumer(workAmmount, buf);
            producers[i] = new Producer(workAmmount, buf);
        }
    }

    private void startAll() {
        for (int i = 0; i < consumers.length; i++) {
            consumers[i].start();
            producers[i].start();
        }
    }

    private void joinAll() {
        for (int i = 0; i < consumers.length; i++) {
            try {
                consumers[i].join();
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void cleanUp() {
        buf.done();
    }

    public static void run(int bufSize, int workerCount, int workAmount) {
        Runner r = new Runner(bufSize, workerCount, workAmount);
        r.startAll();
        r.joinAll();
        r.cleanUp();
        System.out.println(bufSize + " " + workerCount + " " + workAmount);
    }
}
