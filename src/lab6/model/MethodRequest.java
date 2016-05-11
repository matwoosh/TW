package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */
public abstract class MethodRequest {
    protected Servant buffer;
    protected Future<Object> future;

    public MethodRequest(Future<Object> f, Servant b) {
        buffer = b;
        future = f;
    }

    abstract boolean guard();

    abstract void call();
}
