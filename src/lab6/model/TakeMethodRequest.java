package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class TakeMethodRequest extends MethodRequest {
    public TakeMethodRequest(Future<Object> f, Servant b) {
        super(f, b);
    }

    @Override
    boolean guard() {
        return !buffer.empty_p();
    }

    @Override
    void call() {
        Object o = buffer.take();
        future.set(o);
    }
}
