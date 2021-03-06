package lab1;

/**
 * Created by Mateusz on 07/03/2016.
 */
public class Producer implements Runnable {
    private Buffer buffer;
    private String id;
    private int n = 10;

    public Producer(Buffer buffer, String id) {
        this.buffer = buffer;
        this.id = id;
    }

    public void run() {
        //notifyWait();
        checkIfEmpty();
    }

    public void notifyWait(){
        for(int i = 0;  i < n;   i++) {
            synchronized(buffer){
                buffer.put("message " + i);
                System.out.println("Putting message " + i + "(" + id + ")");
                buffer.notify();
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("+Producer thread terminated" + "(" + id + ")");
    }

    public void checkIfEmpty(){
        synchronized(buffer){
            while (!(buffer.take() == null)){
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int i = buffer.getMessageNo();
            buffer.incMessageNo();
            buffer.put("message " + i);
            buffer.notifyAll();
            System.out.println("+Producer " + this.id + " putting message " + i);

        }
        System.out.println("+Producer " + this.id + " thread terminated");
    }
}

