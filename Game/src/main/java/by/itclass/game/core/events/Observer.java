package by.itclass.game.core.events;

/**
 * Объекты, реализующие данный интерфейс, являются слушателями события
 *
 * @param <E> - тип события
 */
public interface Observer<E> {

    void notify(E event);

}
