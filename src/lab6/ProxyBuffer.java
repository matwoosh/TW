package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class ProxyBuffer {
    private Scheduler scheduler;
    private ServantBuffer buffor;

    public ProxyBuffer(int size) {
        scheduler = new Scheduler();
        buffor = new ServantBuffer(size);
    }

    public Future<Object> add(Object o) {
        Future<Object> fut = new Future<Object>();
        AddMethodRequest mr = new AddMethodRequest(fut, buffor, o);
        scheduler.enqueue(mr);
        return fut;
    }

    public Future<Object> take() {
        Future<Object> fut = new Future<Object>();
        TakeMethodRequest mr = new TakeMethodRequest(fut, buffor);
        scheduler.enqueue(mr);
        return fut;
    }

    public void printBuffor(){
        buffor.printBuffor();
    }

    void done(){
        scheduler.done();
    }
}
