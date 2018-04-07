package by.itclass.game.core;

import java.util.Random;

public class Enemy extends Unit {

    private final float PERIOD_TIME = 500;

    private float time;

    public Enemy(UnitType unitType, double x, double y, GameMap map) {
        super(unitType, x, y, map);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        time += deltaTime * 1000;

        if (time > PERIOD_TIME) {

            Random random = new Random();

            int gen = random.nextInt();

            if (gen != 0) {
                gen /= Math.abs(gen);
            }

            byte directionX = (byte) gen;

            gen = random.nextInt();

            if (gen != 0) {
                gen /= Math.abs(gen);
            }

            byte directionY = (byte) gen;

            this.setVerticalMovement(directionY);
            this.setHorizontalMovement(directionX);

            time = 0.0f;
        }
    }
}
