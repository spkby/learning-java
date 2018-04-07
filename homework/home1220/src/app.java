import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class app {


    static final int CPUs = Runtime.getRuntime().availableProcessors();
    static int threadCount = 10;
    static final String FILENAME = "pic";
    static final String FILEEXT = "jpg";
    static BufferedImage img;
    public static int height;
    public static int width;

    public static void main(String[] args) {

        loadImg();

        serial(new GrayRunnable(fill(), 0, height, 0, width), "gs");
        serial(new NegativeRunnable(fill(), 0, height, 0, width),"ns");
        serial(new SepiaRunnable(fill(),0, height, 0, width),"ss");

        threads(fill(),0);
        threads(fill(),1);
        threads(fill(),2);


        forks(new GrayFork(fill(),0, height, 0, width,threadCount),"gf");
        forks(new NegativeFork(fill(),0, height, 0, width,threadCount),"nf");
        forks(new SepiaFork(fill(),0, height, 0, width,threadCount),"sf");


    }

    /*
    Serial
     */
    static void serial(ProcessRunnable runnable, String str) {

        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();

        System.out.printf(str + ": %f %n", (endTime - startTime) / 1e9d);

        saveImg(runnable.getPixels(), str);
    }

    /*
    Fork
     */
    static void forks(ProcessFork fork, String str) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        long startTime = System.nanoTime();
        pool.invoke(fork);
        long endTime = System.nanoTime();

        System.out.printf(str + ": %f %n", (endTime - startTime) / 1e9d);
        saveImg(fork.getPixels(), str);
    }


    static void saveImg(int[][] pixels, String method) {

        BufferedImage save = img;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                save.setRGB(x, y, pixels[x][y]);
            }
        }

        try {
            ImageIO.write(save, FILEEXT, new File(FILENAME + "_" + method + "." + FILEEXT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadImg() {
        try {
            img = ImageIO.read(new File(FILENAME + "." + FILEEXT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        height = img.getHeight();
        width = img.getWidth();
    }

    /*
    Threads
     */
    static void threads(int[][] pixels, int type) {


        int[][] pixelsNew = new int[width][height];

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                pixelsNew[i][j] = pixels[i][j];
            }
        }

        Thread[] threads = new Thread[threadCount];
        ProcessRunnable[] tasks = null;

        switch (type) {
            case 0:
                tasks = new GrayRunnable[threadCount];
                break;
            case 1:
                tasks = new NegativeRunnable[threadCount];
                break;
            case 2:
                tasks = new SepiaRunnable[threadCount];
                break;
        }


        long startTime = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {

            int start = i * img.getHeight() / threadCount;
            int end = (i + 1) * img.getHeight() / threadCount - i * img.getHeight() / threadCount;

            if (i + 1 == threadCount) end = img.getHeight() - i * img.getHeight() / threadCount;

            switch (type) {
                case 0:
                    tasks[i] = new GrayRunnable(pixelsNew, start, end, 0, width);
                    break;
                case 1:
                    tasks[i] = new NegativeRunnable(pixelsNew, start, end, 0, width);
                    break;
                case 2:
                    tasks[i] = new SepiaRunnable(pixelsNew, start, end, 0, width);
                    break;
            }
        }


        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();

        String str = "";
        switch (type) {
            case 0:
                str = "gt";
                break;
            case 1:
                str = "nt";
                break;
            case 2:
                str = "st";
                break;
        }

        System.out.printf(str + ": %f %n", (endTime - startTime) / 1e9d);
        saveImg(pixelsNew, str);
    }

    /*
    Executors
     */
    static BufferedImage executors(BufferedImage img) {

        ProcessRunnable[] tasks = new ProcessRunnable[threadCount];

        ExecutorService service = null;
        service = Executors.newCachedThreadPool();
        Future<?>[] futures = new Future[threadCount];


        long startTime = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            futures[i] = service.submit(tasks[i]);
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                futures[i].get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();

        long endTime = System.nanoTime();

        System.out.printf("ThreadSimple: %f %n", (endTime - startTime) / 1e9d);
        return null;
    }

    static int[][] fill(){

        int[][] tmp = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tmp[x][y] = img.getRGB(x, y);
            }
        }
        return tmp;
    }
}

