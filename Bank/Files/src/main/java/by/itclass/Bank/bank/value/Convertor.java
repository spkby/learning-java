package by.itclass.Bank.bank.value;

public class Convertor {

    public static Currency toCurrency(String currency){

        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Не определен тип валюты");
        }

        switch (currency) {
            case "BYN":
                return Currency.BYN;
            case "EUR":
                return Currency.EUR;
            case "USD":
                return Currency.USD;
            case "RUR":
                return Currency.RUR;
            default: throw new IllegalArgumentException("Неизвестный тип валюты");
        }
    }

    public static Metals toMetal(String metal){

        if (metal == null || metal.isEmpty()) {
            throw new IllegalArgumentException("Не указан тип металла");
        }

        switch (metal) {
            case "GOLD":
                return Metals.GOLD;
            case "SILVER":
                return Metals.SILVER;
            case "PALLADIUM":
                return Metals.PALLADIUM;
            case "PLATINUM":
                return Metals.PLATINUM;
            default: throw new IllegalArgumentException("Неизвестный тип метала");
        }
    }
}
