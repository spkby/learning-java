public class NegativeRunnable extends ProcessRunnable{


    public NegativeRunnable(int[][] pixels, int heightStart, int heightEnd, int widthStart, int widthEnd) {
        super(pixels, heightStart, heightEnd, widthStart, widthEnd);
    }

    @Override
    protected void process(int x, int y){
        pixelsNew[x][y] = Filters.negative(pixels[x][y]);
    }
}
