public class GrayRunnable extends ProcessRunnable {


    public GrayRunnable(int[][] pixels, int heightStart, int heightEnd, int widthStart, int widthEnd) {
        super(pixels, heightStart, heightEnd, widthStart, widthEnd);
    }

    @Override
    protected void process(int x, int y) {
        pixelsNew[x][y] = Filters.grayscale(pixels[x][y]);
    }
}
