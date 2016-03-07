package lab1;

public class Race {

    static private int n = 0;

    public Race() {
        this.n = 0;
    }

    static class Incrementer extends Thread {
        public void run() {
            inc();
        }
        public  void inc(){
            for (int x = 0; x< 1000000; x++)
                n = n+1;
            System.out.println(n);
        }


    }

    static class Decrementer extends Thread {
        public void run() {
            dec();
        }
        public void dec(){
            for (int x = 0; x< 1000000; x++)
                n = n-1;
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
        Incrementer i = new Incrementer();
        Decrementer d = new Decrementer();
        Thread t1 = new Thread(i);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();
    }

}