package lab3.zad3;

/**
 * Created by Mateusz on 22/03/2016.
 */
public class Restaurant {
    public final static int N = 10;

    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        for(int i = 0; i < N; i++) {
            new Thread(new Client(i, waiter)).start();
            new Thread(new Client(i, waiter)).start();
        }

    }

}
