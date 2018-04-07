package by.itclass.game.core.events;

/**
 * Класс игрового события
 */
public abstract class Event {

    private Object source;

    public Event(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }
}
