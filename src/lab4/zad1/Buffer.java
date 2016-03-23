package lab4.zad1;

import java.util.concurrent.locks.*;

/**
 * Created by Mateusz on 23/03/2016.
 */
public class Buffer {
//    private final Lock lock = new ReentrantLock();
//    private Condition[] pair = new Condition[Restaurant.N];
//    private final Condition available = lock.newCondition();
//    private int[] requests = new int[Restaurant.N];
    private int[] data;

    public Buffer(int n) {
        this.data = new int[n];
        for (int x : data){
            x = -1;
        }
    }

    public int getData(int n) {
        return data[n];
    }

    public void setData(int n, int value) {
        this.data[n] = value;
    }

}
