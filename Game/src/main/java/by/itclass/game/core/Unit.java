package by.itclass.game.core;

import by.itclass.game.core.events.Event;
import by.itclass.game.core.events.EventSource;
import by.itclass.game.core.events.Observer;
import by.itclass.game.core.events.UnitCellChangeEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс боевой единицы
 */
public class Unit implements Drawable, Updatable, EventSource<Event> {

    private UnitType unitType;

    private double x;                //x-координата персонажа
    private double y;                //y-координата персонажа

    private byte verticalMovement;
    private byte horizontalMovement;

    private GameMap gameMap;

    private ArrayList<Observer<Event>> observers = new ArrayList<>();


    public Unit(UnitType unitType, double x, double y, GameMap map) {
        if (unitType == null) {
            throw new IllegalArgumentException("Отсутствует тип героя");
        }
        if (map == null) {
            throw new IllegalArgumentException("Отсутсвует карта");
        }
        this.unitType = unitType;
        this.x = x;
        this.y = y;
        this.gameMap = map;
    }


    private void move(double time) {
        double nextX = this.x;
        double nextY = this.y;

        double centerX = this.x + unitType.getWidth() / 2;
        double centerY = this.y + unitType.getHeight() / 2;

        double speed = unitType.getMaxMovementSpeed();
        if (checkPointIsSwimable(centerX, centerY)) {
            speed *= unitType.getSwimFactor();
        }

        double delta = speed * time;
        if (horizontalMovement == 0) {
            switch (verticalMovement) {
                case -1:
                    nextY = this.y - delta;
                    break;
                case 1:
                    nextY = this.y + delta;
                    break;
            }
        } else if (horizontalMovement == -1) {
            switch (verticalMovement) {
                case -1:
                    double diag = delta * Math.sqrt(2) / 2;
                    nextX = this.x - diag;
                    nextY = this.y - diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2) / 2;
                    nextX = this.x - diag;
                    nextY = this.y + diag;
                    break;
                case 0:
                    nextX = this.x - delta;
                    break;
            }
        } else if (horizontalMovement == 1) {
            switch (verticalMovement) {
                case -1:
                    double diag = delta * Math.sqrt(2) / 2;
                    nextX = this.x + diag;
                    nextY = this.y - diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2) / 2;
                    nextX = this.x + diag;
                    nextY = this.y + diag;
                    break;
                case 0:
                    nextX = this.x + delta;
                    break;
            }
        }

        double upY = nextY;
        double downY = nextY + unitType.getHeight();
        double leftX = nextX;
        double rightX = nextX + unitType.getWidth();

        if ((checkPointInMap(leftX, upY) && (checkPointIsWalkable(leftX, upY) || checkPointIsSwimable(leftX, upY))) &&
                (checkPointInMap(rightX, upY) && (checkPointIsWalkable(rightX, upY) || checkPointIsSwimable(rightX, upY))) &&
                (checkPointInMap(leftX, downY) && (checkPointIsWalkable(leftX, downY) || checkPointIsSwimable(leftX, downY)))&&
                (checkPointInMap(rightX, downY) && (checkPointIsWalkable(rightX, downY) || checkPointIsSwimable(rightX, downY)))) {
            this.x = nextX;
            this.y = nextY;
        }

        double newCenterX = this.x + unitType.getWidth() / 2;
        double newCenterY = this.y + unitType.getHeight() / 2;

        if (newCenterX != centerX || newCenterY != centerY) {
            //int old_i = (int) (centerY / gameMap.CELL_HEIGHT);
            //int old_j = (int) (centerX / gameMap.CELL_WIDTH);
            int new_i = (int) (newCenterY / gameMap.CELL_HEIGHT);
            int new_j = (int) (newCenterX / gameMap.CELL_WIDTH);
            //if (old_i != new_i || old_j != new_j) {
            UnitCellChangeEvent event = new UnitCellChangeEvent(this,
                        new_j, new_i);
                for (Observer<Event> observer : observers) {
                    observer.notify(event);
                }
            //}
        }


    }


    private boolean checkPointInMap(double x, double y) {
        int j = (int) (x / gameMap.CELL_WIDTH);
        int i = (int) (y / gameMap.CELL_HEIGHT);

        return i >= 0 && j >= 0 && i < gameMap.getWidth() && j < gameMap.getHeight();
    }

    private boolean checkPointIsWalkable(double x, double y) {
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        int type = gameMap.getCell(i, j).getType();

        return gameMap.getType(type).isWalkable();
    }

    private boolean checkPointIsSwimable(double x, double y) {
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        int type = gameMap.getCell(i, j).getType();

        return gameMap.getType(type).isSwimmable() && unitType.isCanSwim();
    }


    public void setVerticalMovement(byte verticalMovement) {
        this.verticalMovement = verticalMovement;
    }

    public void setHorizontalMovement(byte horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        g.drawImage(unitType.getImage(), (int) x, (int) y, null);
    }

    @Override
    public void update(double deltaTime) {
        move(deltaTime);
    }


    public int getCellX() {
        return (int) ((x + this.unitType.getWidth() / 2) / gameMap.CELL_WIDTH);
    }

    public int getCellY() {
        return (int) ((y + this.unitType.getHeight() / 2) / gameMap.CELL_HEIGHT);
    }

    @Override
    public void addObserver(Observer<Event> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Event> observer) {
        observers.remove(observer);
    }

    public boolean intersects(Unit other) {
        if (other == null) {
            return false;
        }

        double deltaX = Math.abs((x + this.unitType.getWidth() / 2) - (other.x + other.unitType.getWidth() / 2));
        double deltaY = Math.abs((y + this.unitType.getHeight() / 2) - (other.y + other.unitType.getHeight() / 2));

        double sumW = this.unitType.getWidth() / 2 + other.unitType.getWidth() / 2;
        double sumH = this.unitType.getHeight() / 2 + other.unitType.getHeight() / 2;

        return deltaX <= sumW && deltaY <= sumH;
    }

}
