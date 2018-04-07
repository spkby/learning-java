public abstract class ProcessRunnable implements Runnable {

    private int heightStart;
    private int heightEnd;
    private int widthStart;
    private int widthEnd;
    protected int[][] pixels, pixelsNew;


    public ProcessRunnable(int[][] pixels, int heightStart, int heightEnd, int widthStart, int widthEnd) {
        this.heightStart = heightStart;
        this.heightEnd = heightEnd;
        this.widthStart = widthStart;
        this.widthEnd = widthEnd;
        this.pixels = pixels;
        pixelsNew = new int[widthEnd][heightEnd];
    }

    public void run() {
        for (int y = heightStart; y < heightEnd; y++) {
            for (int x = widthStart; x < widthEnd; x++) {
                process(x, y);
            }
        }
    }

    public int[][] getPixels() {
        return pixelsNew;
    }

    protected abstract void process(int x, int y);
}
