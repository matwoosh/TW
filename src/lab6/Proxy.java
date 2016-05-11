package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Proxy {
    private Scheduler scheduler;
    private ServantBuffer buffer;

    public Proxy(int size) {
        scheduler = new Scheduler();
        buffer = new ServantBuffer(size);
    }

    public Future<Object> add(Object o) {
        Future<Object> fut = new Future<Object>();
        AddMethodRequest mr = new AddMethodRequest(fut, buffer, o);
        scheduler.enqueue(mr);
        return fut;
    }

    public Future<Object> take() {
        Future<Object> fut = new Future<Object>();
        TakeMethodRequest mr = new TakeMethodRequest(fut, buffer);
        scheduler.enqueue(mr);
        return fut;
    }

    public void printBuffor() {
        buffer.printBuffor();
    }

    void done() {
        scheduler.done();
    }
}
