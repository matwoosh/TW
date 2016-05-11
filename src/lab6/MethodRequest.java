package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public abstract class MethodRequest {
    protected ServantBuffer buffor;
    protected Future<Object> future;

    public MethodRequest(Future<Object> f, ServantBuffer b) {
        buffor = b;
        future = f;
    }

    abstract boolean guard();

    abstract void call();
}
