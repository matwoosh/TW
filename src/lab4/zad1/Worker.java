package lab4.zad1;

public class Worker implements  Runnable {
    int id;
    Buffer buffer;

    public Worker(Buffer buffer, int id){
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("\t Working...");
                buffer.work(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }


}
