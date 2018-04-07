package by.itclass.game.core.events;

/**
 * Событие перехода персонажа на другую клетку
 */
public class UnitCellChangeEvent extends Event {

    private int cellX;
    private int cellY;

    public UnitCellChangeEvent(Object source, int cellX, int cellY) {
        super(source);
        this.cellX = cellX;
        this.cellY = cellY;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

}
