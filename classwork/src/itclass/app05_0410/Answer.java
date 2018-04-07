package itclass.app05_0410;

//Перечисление
public enum Answer {
    //Рекомендуется использовать заглавные буквы и
    //использовать нижнее подчеркивание для разделения слов
    YES,
    NO,
    CANCEL
}


enum Fruit {
    APPLE(20),
    PINEAPPLE(30),
    GRAPEFRUIT(25);

    //Все поля перечисления можно назначить только
    //в конструкторе (на этапе создания)
    private double price;

    Fruit(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}