package lab4.zad2;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    final Lock lock = new ReentrantLock();
    final Condition firstProd  = lock.newCondition();
    final Condition firstCons = lock.newCondition();
    final Condition restProd  = lock.newCondition();
    final Condition restCons = lock.newCondition();
    Boolean firstProdWaits, firstConsWaits;

    final Integer[] items;
    int M, inBuffer, start;

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

            while( 2*M - inBuffer < amount) {
                firstProd.await();
                firstProdWaits = true;
            }
            //if there is a place
            for(int i=0; i<amount; i++){
                System.out.println( "Saving in buffer" + (start + inBuffer)% (2*M));
                items[ (start + inBuffer)% (2*M)] = 1;
                inBuffer++;
            }
            System.out.println(" In buffer: " + inBuffer);
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

            while(inBuffer < amount) {
                firstCons.await();
                firstConsWaits = true;
            }
            //can take
            for( int i=0; i< amount; i++){
                System.out.println("Releasing buffer " + start%(2*M));
                items[start%(2*M)] = 0;
                inBuffer--;
                start++;
            }
            System.out.println(" In buffer: " + inBuffer);
            if(start >= 2*M )
                start = start%(2*M);

            restCons.signal();
            firstProd.signal();

        } finally {
            lock.unlock();
        }
    }
}
