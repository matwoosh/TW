package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Proxy {
    private Scheduler scheduler;
    private Servant buffer;

    public Proxy(int size) {
        scheduler = new Scheduler();
        buffer = new Servant(size);
    }

    public Future<Object> add(Object o) {
        Future<Object> fut = new Future<>();
        AddMethodRequest mr = new AddMethodRequest(fut, buffer, o);
        scheduler.enqueue(mr);
        return fut;
    }

    public Future<Object> take() {
        Future<Object> fut = new Future<>();
        TakeMethodRequest mr = new TakeMethodRequest(fut, buffer);
        scheduler.enqueue(mr);
        return fut;
    }

    public void printBuffor() {
        buffer.printBuffor();
    }

    public void done() {
        scheduler.done();
    }
}
