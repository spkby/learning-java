package itclass.app10_2610;

import itclass.app05_0410.animals.*;

import java.util.*;

public class Application {

    public static void main(String[] args) {

        //Map - основной интерфейс для хранения пар "ключ-значение"
        //является обобщенным и в качестве обобщения указываются типы
        //ключа и значения
        Map<String, Float> moneys = new TreeMap<>();

        //Метод put - кладем значение по ключу в карту (если
        //значение существовало, то происходит его замена
        moneys.put("Петров", 100.50f);
        moneys.put("Сидоров", 220.0f);

        //Обойти все пары в карте
        //1. Через ключи
        System.out.println("Обход через все ключи");
        for (String key : moneys.keySet()) {
            System.out.printf("%s -> %s\n", key, moneys.get(key));
        }

        Set<String> keys = new HashSet<>();
        keys.add("Петров");
        keys.add("Иванов");
        System.out.println("Обход через собственный набор ключей");
        for (String key : keys) {
            Float money = moneys.get(key);
            if (money != null)
                System.out.printf("%s -> %s\n", key, money);
        }

        System.out.println("Обход через собственный набор ключей #2");
        for (String key : keys) {
            if (moneys.containsKey(key))
                System.out.printf("%s -> %s\n", key, moneys.get(key));
        }

        //2. Через пары ключ-значение
        System.out.println("Обход через все пары ключ-значение");
        for (Map.Entry<String, Float> entry : moneys.entrySet()) {
            System.out.printf("%s -> %s\n", entry.getKey(), entry.getValue());
        }

        moneys.remove("Петров");    //удаление
        Float prev = moneys.put("Сидоров", 250f);
        System.out.println("Предыдущая зарплата Сидорова = " + prev);
        System.out.println("Обход через все пары ключ-значение");
        for (Map.Entry<String, Float> entry : moneys.entrySet()) {
            System.out.printf("%s -> %s\n", entry.getKey(), entry.getValue());
        }

        //Хэш-таблица - карта, в которой нет порядка между
        //ключами, но зато скорость её на практике быстрее, чем TreeMap
        HashMap<String, Float> bonuses = new HashMap<>(32); //количество корзин
        bonuses.put("Андреев", 100f);
        bonuses.put("Алексеев", 2000f);

        //bonuses.putAll(moneys);
        moneys.putAll(bonuses);
        System.out.println("Содержимое объединенных карт");
        for (Map.Entry<String, Float> entry : moneys.entrySet()) {
            System.out.printf("%s -> %s\n", entry.getKey(), entry.getValue());
        }

        //Карта людей, у коротых есть свои домашние животные
        TreeMap<Human, List<Animal>> owners =
                new TreeMap<>(new HumanComparator());

        Human h1 = new Clerk("Иван Иванович Иванов", 10000);
        Human h2 = new Clerk("Петр Иванович Иванов", 12000);
        Human h3 = new Worker("Василий Андреевич Федоров", 120);

        owners.put(h1, new ArrayList<>());
        owners.get(h1).add(new Cat("Маркиза"));
        owners.get(h1).add(h3);


        for (Human human : owners.keySet()) {
            System.out.println("Животные " + human.getName());
            for (Animal animal : owners.get(human)) {
                System.out.println(animal.getName());
            }
        }


        //TreeMap<String, TreeSet<List<Map<String, Integer>>>>
        //Map<Client, List<Storage>>

    }

}
