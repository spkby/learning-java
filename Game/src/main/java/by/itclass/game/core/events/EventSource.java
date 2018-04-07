package by.itclass.game.core.events;

/**
 * Объекты, реализующие данный интерфейс, являются источниками событий
 */
public interface EventSource<E> {

    void addObserver(Observer<E> observer);

    void removeObserver(Observer<E> observer);

}
