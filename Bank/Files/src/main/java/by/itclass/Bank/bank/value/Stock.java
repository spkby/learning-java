package by.itclass.Bank.bank.value;

public class Stock {

    private int count;
    private String company;

    public Stock(String company, int count) {
        setCount(count);
        if (company.isEmpty()) {
            throw new IllegalArgumentException("Название 'Компании' не может быть пусты");
        }
        this.company = company;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Количество акций должно быть положительным числом");
        }
        this.count = count;
    }

    public String getCompany() {
        return company;
    }

    public double getAmount() {
        Prices price = new Prices();
        return count * price.getPriceStock(company);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        return this.company.equals(stock.company);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "company=" + company +
                ", count='" + count + '\'' +
                '}';
    }

}
