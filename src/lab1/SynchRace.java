package lab1;

public class SynchRace {

    static private Value n;
    static private boolean threadTerminated;
    static long range = 100000;

    static class Incrementer extends Thread {
        public void run() {
            inc(n);
        }
        public void inc(Value val){
            synchronized (val){
                threadTerminated = false;
                for (int x = 0; x< range; x++){
                    val.notify();
                    try {
                        val.setN(val.getN()+1);
                        if (!threadTerminated){
                            val.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(val.getN());
                threadTerminated = true;
                val.notifyAll();
            }
        }


    }

    static class Decrementer extends Thread {
        public void run() {
            dec(n);
        }
        public void dec(Value val){
            synchronized (val) {
                threadTerminated = false;
                for (int x = 0; x < range; x++){
                    val.notify();
                    try {
                        val.setN(val.getN() - 1);
                        if(!threadTerminated){
                            val.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("victory");
                threadTerminated = true;
                val.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
        n = new Value();
        Incrementer i = new Incrementer();
        Decrementer d = new Decrementer();
        Thread t1 = new Thread(i);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();
    }

}
