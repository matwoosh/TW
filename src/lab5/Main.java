package lab5;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;

import static java.lang.Math.sqrt;

@SuppressWarnings("serial")
public class Main extends JFrame {
 
    private final int MAX_ITER = 2000;
    private final double ZOOM = 200;
    private static final List<Integer> threads = new ArrayList<>();
    private BufferedImage I;
 
    public Main() throws InterruptedException, ExecutionException {
        super("Mandelbrot");
        setBounds(200, 200, 600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        threads.add(1);
        threads.add(2);
        threads.add(4);
        threads.add(8);
        threads.add(16);
    }

    private void executorSpawner(ExecutorService executor) throws InterruptedException, ExecutionException {
        ExecutorService pool = executor;
        Set<Future<Object>> callables = new HashSet<Future<Object>>();

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Callable<Object> callable = new Mandelbrot(x, y, MAX_ITER, ZOOM, I);
                callables.add(pool.submit(callable));
            }
        }

        for (Future<Object> callable : callables) {
        	callable.get();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(I, 0, 0, this);
    }

    public static Double calculateMean(ArrayList<Long> data) {
        double n = 0.0;
        double result;
        for (Long x : data) {
            n += x;  //summing all elements of processed data
        }
        result = n / data.size();    //sum of all elements devided by number of elements
        return result;
    }

    public static Double calculateStdDev(ArrayList<Long> data) {
        double sum = 0.0;
        double avg = calculateMean(data);
        for (Long x : data) {
            // summing squares of difference between particular elements and mean
            sum += (x - avg) * (x - avg);
        }
        return sqrt(sum / (data.size()));//calculating standard deviation
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Main m = new Main();

        ArrayList<Long> data1 = new ArrayList<>();
        ArrayList<Long> data2 = new ArrayList<>();

        ExecutorService ex1;
        ExecutorService ex2;

        double mean1, mean2;
        double stdDev1, stdDev2;

        for(int x : threads){
            ex1 = Executors.newFixedThreadPool(x);
            ex2 = Executors.newWorkStealingPool(x);
            System.out.println("Threads number: " + x);

            for(int i=0;i<10;i++) {
                long start = System.nanoTime();
                m.executorSpawner(ex1);
                long end = System.nanoTime();
                data1.add(end-start);

                start = System.nanoTime();
                m.executorSpawner(ex2);
                end = System.nanoTime();
                data2.add(end-start);
            }

            mean1 = calculateMean(data1);
            mean2 = calculateMean(data2);
            stdDev1 = calculateStdDev(data1);
            stdDev2 = calculateStdDev(data2);

            System.out.println("data1 - FixedThreadPool: " + data1);
            System.out.println("Mean: " + mean1);
            System.out.println("Standard deviation: " + stdDev1);
            data1.clear();

            System.out.println("data2 - WorkStealingPool: " + data2);
            System.out.println("Mean: " + mean2);
            System.out.println("Standard deviation: " + stdDev2);
            System.out.println();
            data2.clear();
        }
        m.setVisible(true);
    }
}