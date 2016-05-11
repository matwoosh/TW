package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class AddMethodRequest extends MethodRequest {
    Object o;

    public AddMethodRequest(Future<Object> f, ServantBuffer b, Object o) {
        super(f, b);
        this.o = o;
    }

    @Override
    boolean guard() {
        return !buffor.full_p();
    }

    @Override
    void call() {
        buffor.add(o);
        future.set(null);
    }
}
