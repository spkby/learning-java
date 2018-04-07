package by.itclass.game.core;

/**
 * Клетка на игровом поле
 */
public class Cell {

    private int type;       //тип клетки

    public Cell(int type) {
        if (type < 0) {
            throw new IllegalArgumentException("Неверный тип клетки");
        }
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
