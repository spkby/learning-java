package by.itclass.Bank.bank.value;

public class Metal {

    private int count;
    private Metals type;

    private final int MAX_COUNT = 3000;

    public Metal(Metals metal, int count) {
        if (metal == null) {
            throw new IllegalArgumentException("Не указан тип металла");
        }
        this.type = metal;
        setCount(count);
    }

    public int getCount() {
        return count;
    }

    public Metals getType() {
        return type;
    }

    public void setCount(int count) {
        if (count < 0 || count > MAX_COUNT) {
            throw new IllegalArgumentException("Количество металла должно быть положительным и не превышать 3000");
        }
        this.count = count;
    }

    public double getAmount() {
        Prices price = new Prices();
        return count * price.getPriceMetal(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metal other = (Metal) o;
        boolean a = this.type == other.type;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return "Metal{" +
                "type=" + type +
                ", count=" + count +
                '}';
    }
}