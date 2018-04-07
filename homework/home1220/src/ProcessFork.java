import java.util.concurrent.RecursiveAction;

public abstract class ProcessFork extends RecursiveAction {

    protected int heightStart;
    protected int heightEnd;
    protected int widthStart;
    protected int widthEnd;
    protected int[][] pixels, newPixels;
    protected int threshold;

    public ProcessFork(int[][] pixels, int heightStart, int heightEnd, int widthStart, int widthEnd, int threshold) {
        this.heightStart = heightStart;
        this.heightEnd = heightEnd;
        this.widthStart = widthStart;
        this.widthEnd = widthEnd;
        this.pixels = pixels;
        this.threshold = threshold;

        //newPixels = new int[widthEnd][heightEnd];
    }

    public int[][] getPixels() {
        return pixels;
    }

    protected abstract void process(int x, int y);

    protected abstract void compute();
}
