package itclass.app08_1910.generics;

//Интерфейсы тоже могут быть обобщенными
//можно наложить ограничение на тип обобщения (с помощью ключевого слова extends)
interface Stats<T extends Number> {

    double mean();
    T max();
    T min();

}