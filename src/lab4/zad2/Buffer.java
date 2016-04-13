package lab4.zad2;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final Lock lock = new ReentrantLock();
    private final Condition firstProd  = lock.newCondition();
    private final Condition firstCons = lock.newCondition();
    private final Condition restProd  = lock.newCondition();
    private final Condition restCons = lock.newCondition();
    private Boolean firstProdWaits, firstConsWaits;

    private final Integer[] items;
    private int M, inBuffer, start;

    Buffer(int M){
        this.M = M;
        inBuffer = 0;
        start = 0;
        firstProdWaits = false;
        firstConsWaits = false;
        items = new Integer[2*M];
    }

    public int size(){return this.M;}

    public void put(int amount) throws InterruptedException {
        lock.lock();
        try {
            //check, if anybody is waiting
            if(firstProdWaits)
                restProd.await();
            firstProdWaits = true;
            while( 2*M - inBuffer < amount) {
                firstProd.await();
            }
//            inBuffer += amount;
            //if there is a place
            for(int i=0; i<amount; i++){
                System.out.println( "Saving in buffer" + (start + inBuffer)% (2*M));
                items[ (start + inBuffer)% (2*M)] = 1;
                inBuffer++;
            }
            System.out.println(" In buffer: " + inBuffer);
            firstProdWaits = false;
            restProd.signal();
            firstCons.signal();

        } finally {
            lock.unlock();
        }
    }

    public void take(int amount ) throws InterruptedException {
        lock.lock();
        try {
            //check if any consumer is waiting
            if(firstConsWaits)
                restCons.await();
            firstConsWaits = true;
            while(inBuffer < amount) {
                firstCons.await();
            }
//            inBuffer -= amount;
//            can take
            for( int i=0; i< amount; i++){
                System.out.println("Releasing buffer " + start%(2*M));
                items[start%(2*M)] = 0;
                inBuffer--;
                start++;
            }
            System.out.println(" In buffer: " + inBuffer);
            if(start >= 2*M )
                start = start%(2*M);
            firstConsWaits = false;
            restCons.signal();
            firstProd.signal();

        } finally {
            lock.unlock();
        }
    }
}
