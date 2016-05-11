package lab6;

/**
 * Created by Mateusz on 11/05/2016.
 */
public class Future <T>{
    T result=null;
    boolean done=false;


    synchronized public T get(){
        while(!done){
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    synchronized public boolean done_p(){
        return done;
    }

    synchronized void set(T result){
        this.result=result;
        done=true;
        notifyAll();
    }
}
