package homework.App03_0920.Task02;

class Duck {

    private byte stomachVolume,foodVolume;

    Duck(byte stomachVolume, byte filled){
        if(stomachVolume <= 0){
            System.err.println("Stomach Volume must be positive");
        } else {
            this.stomachVolume = stomachVolume;
            this.foodVolume = filled;
        }
    }

    byte getStomachVolume(){
        return stomachVolume;
    }

    byte getFoodVolume(){
        return foodVolume;
    }

    double getPercent(int volume){

        return  (double) (volume+foodVolume)/stomachVolume;
    }

    public void eat(byte breadVolume){
        if(breadVolume <= 0){
            System.err.println("Bread Volume must be positive");
        } else {
            int result = foodVolume + breadVolume;
            if(result <= 0.9 * stomachVolume){
                this.foodVolume = (byte) result;
            } else {
                this.foodVolume = (byte) (0.9 * stomachVolume);
                System.out.println("Duck is full (90%)");
            }
        }
    }

    @Override
    public String toString(){
        return "Duck " + (double)foodVolume/stomachVolume*100d + "%";
    }
}
