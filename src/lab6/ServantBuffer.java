package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class ServantBuffer {
    private Object[] bufor;
    private int fillPointer;

    public ServantBuffer(int size) {
        bufor = new Object[size];
        fillPointer = -1;
    }

    public boolean empty_p() {
        return fillPointer == -1;
    }

    public boolean full_p() {
        return fillPointer == (bufor.length - 1);
    }


    public void add(Object n) {
        fillPointer++;
        bufor[fillPointer] = n;
    }

    public Object take() {
        Object res = bufor[fillPointer];
        fillPointer--;
        return res;
    }

    public void printBuffor() {
        System.out.print("BUFOR: ");
        for (int i = 0; i <= fillPointer; i++) {
            System.out.print(bufor[i] + " ");
        }
        System.out.println();
    }
}
