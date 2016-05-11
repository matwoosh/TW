package lab6.app;

import static lab6.app.Runner.run;

/**
 * Created by Mateusz on 10/05/2016.
 */
public class Main {
    public static void main(String[] args) {
        int workAmount = 100000;

        for (int bufSize = 10; bufSize <= 810; bufSize += 100) {
            for (int workerCount = 1; workerCount <= 20; workerCount++) {
                run(bufSize, workerCount, workAmount);
            }
            System.out.println();
        }
        System.exit(0);
    }
}
