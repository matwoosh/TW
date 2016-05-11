package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class TakeMethodRequest extends MethodRequest {
    public TakeMethodRequest(Future<Object> f, ServantBuffer b) {
        super(f, b);
    }

    @Override
    boolean guard() {
        return !buffor.empty_p();
    }

    @Override
    void call() {
        Object o=buffor.take();
        future.set(o);
    }
}
