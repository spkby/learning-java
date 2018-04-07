public class NegativeFork extends ProcessFork {


    public NegativeFork(int[][] pixels, int heightStart, int heightEnd, int widthStart, int widthEnd, int threshold) {
        super(pixels, heightStart, heightEnd, widthStart, widthEnd, threshold);
    }

    protected void compute() {

        if (heightEnd - heightStart < threshold) {
            for (int x = widthStart; x < widthEnd; x++) {
                for (int y = heightStart; y < heightEnd; y++) {
                    process(x, y);
                }
            }
        } else {
            int center = heightStart + (heightEnd - heightStart) / 2;

            invokeAll(new NegativeFork(pixels, heightStart, center, 0, widthEnd, threshold),
                    new NegativeFork(pixels, center, heightEnd, 0, widthEnd, threshold));
        }
    }

    protected void process(int x, int y){
        pixels[x][y] = Filters.negative(pixels[x][y]);
    }
}
