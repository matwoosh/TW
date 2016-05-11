package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class AddMethodRequest extends MethodRequest {
    Object o;

    public AddMethodRequest(Future<Object> f, Servant b, Object o) {
        super(f, b);
        this.o = o;
    }

    @Override
    boolean guard() {
        return !buffer.full_p();
    }

    @Override
    void call() {
        buffer.add(o);
        future.set(null);
    }
}
