package by.itclass.game.core.commands;

import by.itclass.game.core.Unit;

public class HorizontalMovementCommand implements Command {

    private byte direction;
    private Unit unit;

    public HorizontalMovementCommand(byte direction, Unit unit) {
        this.direction = direction;
        this.unit = unit;
    }

    @Override
    public void execute() {
        if (unit != null) {
            unit.setHorizontalMovement(direction);
        }
    }
}
