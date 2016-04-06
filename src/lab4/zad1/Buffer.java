package lab4.zad1;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    final Lock lock = new ReentrantLock();
    final Condition[] conditions;
    final Condition producer = lock.newCondition();
    final Condition consumer = lock.newCondition();

    final int N; //buffer size
    final int M; //processes amount;
    int curr;
    int[] buffer;
    int[] positions;

    Buffer(int N, int M) {
        this.N = N;
        this.M = M;
        curr = 0;
        conditions = new Condition[M];
        buffer = new int[N];
        positions = new int[M];
        for (int i = 0; i < M; i++) {
            conditions[i] = lock.newCondition();
            positions[i] = 0;
        }
        positions[M - 1] = 0; //consumer

        for (int i = 0; i < N; i++) {
            buffer[i] = -1;
        }

    }

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while(buffer[(positions[0]%N)] != -1)
                conditions[M-1].await();
            buffer[positions[0]] ++;
            System.out.println("Producer processed buffer[" + positions[0] +"]");
            positions[0]++;
            positions[0] = positions[0]%N;
            conditions[1].signal();
        } finally {
            lock.unlock();
        }
    }

    public void work( int id) throws InterruptedException {
        lock.lock();
        try {
            while( positions[id] == positions[id-1]) {
                conditions[id].await();
            }
        } finally {
            lock.unlock();
        }

            buffer[positions[id]]++;
            System.out.println("Process nr "+id+" changed buffer["+ positions[id]+  "] to " +buffer[positions[id]]);

        try{
            lock.lock();
            positions[id]++;
            positions[id] = positions[id]%N;
            conditions[(id+1)%M].signal();


        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while( positions[M-1] > positions[0]) {
                conditions[M-1].await();
            }
            System.out.println("Consumer took value "+buffer[positions[M-1]%N]+" from buffer["+ positions[M-1]%N+  "]");
            buffer[positions[M-1]] = -1;
            positions[M-1]++;
            positions[M-1] = positions[M-1]%N;
            conditions[0].signal();


        } finally {
            lock.unlock();
        }
    }

}