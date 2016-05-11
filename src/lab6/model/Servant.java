package lab6.model;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Servant {
    private Object[] buffer;
    private int fillPointer;

    public Servant(int size) {
        buffer = new Object[size];
        fillPointer = -1;
    }

    public boolean empty_p() {
        return fillPointer == -1;
    }

    public boolean full_p() {
        return fillPointer == (buffer.length - 1);
    }


    public void add(Object n) {
        fillPointer++;
        buffer[fillPointer] = n;
    }

    public Object take() {
        Object res = buffer[fillPointer];
        fillPointer--;
        return res;
    }

    public void printBuffor() {
        System.out.print("BUFOR: ");
        for (int i = 0; i <= fillPointer; i++) {
            System.out.print(buffer[i] + " ");
        }
        System.out.println();
    }
}
