package lab6;

/**
 * Created by Mateusz on 10/05/2016.
 */
public class Main {
    Proxy buf;
    Consumer[] consumers;
    Producer[] producers;

    Main(int bufSize, int workerCount, int workAmmount) {
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

    public static void measure(int bufSize, int workerCount, int workAmmount) {
        Main m = new Main(bufSize, workerCount, workAmmount);
        long start, end;
        start = System.nanoTime();
        m.startAll();
        m.joinAll();
        end = System.nanoTime();
        m.cleanUp();
        System.out.println(bufSize + " " + workerCount + " " + workAmmount + " " + (end - start));
    }


    public static void main(String[] args) {
        int workAmount = 100000;
        for (int bufSize = 10; bufSize <= 1000; bufSize += 40) {
            for (int workerCount = 1; workerCount <= 20; workerCount++) {

                measure(bufSize, workerCount, workAmount);
            }
            System.out.println();
        }

        //		Main m = new Main(2);
//		Future<Object> f = m.buf.take();
//
//
//		//System.out.println(f.done_p());
//		//System.out.println(f.get());
//		m.buf.add("elo");
//		m.buf.add("cccccc");
//		m.buf.add("dsa");
//
//		System.out.println(f.done_p());
//
//		System.out.println(f.get());
//		m.buf.printBuffor();

    }
}
