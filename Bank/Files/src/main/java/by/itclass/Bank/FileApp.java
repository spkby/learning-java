package by.itclass.Bank;

//import by.itclass.Bank.bank.*;
import by.itclass.Bank.bank.Bank;
import by.itclass.Bank.bank.Client;
import by.itclass.Bank.bank.NotEnoughValuableException;
import by.itclass.Bank.bank.value.*;


import java.util.HashSet;
import java.util.Scanner;

public class FileApp {

    private static Bank bank;
    private static String separateLine = "_____________________________________________" + "\n";

    /*
    основное меню
     */
    private static void printMenu() {
        System.out.println("Выберите операцию");
        System.out.println("1. Зарегистрировать клиента");
        System.out.println("2. Открыть счет");
        System.out.println("3. Снять со счета");
        System.out.println("4. Пополнить на счет");
        System.out.println("5. Проверить остаток на счете");
        System.out.println("6. Перевести со счета на счет");
        System.out.println("7. Показать все счета клиента");
        System.out.println("8. Снять все комиссии");
        System.out.println("9. Загрузить/Выгрузить данные");
        System.out.println();
        System.out.println("0. Выход");
        System.out.println();
    }

    public static void main(String[] args) {

        /*
        инициализация переменных
         */
        bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        showMainMenu(scanner);

    }

    private static void showMainMenu(Scanner scanner){
        /*
        главное меню
        */
        int command;
        do {
            command = -1;
            printMenu();
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            switch (command) {
                case 0:
                    break;
                case 1:
                    registerNewClient(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 2:
                    registerNewAccount(bank, scanner);
                    System.out.println(" ");
                    break;
                case 3:
                    withdrawAccountMoney(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 4:
                    depositAccountMoney(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 5:
                    checkAccountBalance(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 6:
                    transferBetweenAccounts(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 7:
                    printAllAccountByClient(bank, scanner);
                    System.out.println(separateLine);
                    break;
                case 8:
                    bank.checkCommissionsMonthly();
                    System.out.println("Все комисии сняты успешно" + "\n" + separateLine);
                    break;
                case 9:
                    ioOperations(scanner);
                    //System.out.println("---" + "\n" + separateLine);
                    break;
                default:
                    command = -1;
                    break;
            }
        } while (command != 0);
        System.out.println("> До свидания");
    }


    private static void ioOperations(Scanner scanner) {
        int type;

        do {
            System.out.println();
            System.out.println("1. Загрузить из текстовых файлов");
            System.out.println("2. Сохранить в текстовые файлы");
            System.out.println("3. Загрузить из XML файлов");
            System.out.println("4. Сохранить в XML файлы");
            System.out.println("5. Загрузить из JSON файлов");
            System.out.println("6. Сохранить в JSON файлы");
            System.out.println();
            System.out.println("0. Отмена");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 0:
                    return;
                case 1:
                case 3:
                case 5:
                    bank.loadData(type);
                    return;
                case 2:
                case 4:
                case 6:
                    bank.saveData(type);
                    return;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);


    }

    private static void printAllAccountByClient(Bank bank, Scanner scanner) {
        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;

        HashSet<Long> set = (HashSet<Long>) bank.getArrayAccountId(client);

        for (long id : set) {
            switch (bank.getTypeAccount(id)) {
                case 1:
                case 2:
                case 3:
                    Money money = (Money) bank.getAccountBalance(client, id);
                    System.out.println("Номер счета '" + id + "' на счету = " + money.getAmount() + " "
                            + money.getCurrency().name());
                    break;
                case 4:
                    Stock stock = (Stock) bank.getAccountBalance(client, id);
                    System.out.println("Номер счета '" + id + "' в ячейке: " + stock.getCount() + " акций компании "
                            + stock.getCompany() + " на сумму " + stock.getAmount());
                    break;
                case 5:
                    Metal metal = (Metal) bank.getAccountBalance(client, id);
                    System.out.println("Номер счета '" + id + "' в ячейке: " + metal.getCount() + " грамм "
                            + metal.getType() + " на сумму " + metal.getAmount());
                    break;
            }
        }
    }

    /*
    перевод между счетами
     */
    private static void transferBetweenAccounts(Bank bank, Scanner scanner) {
        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;

        System.out.println("Исходный");
        long source = askForAccountId(bank, scanner);
        System.out.println("Назвначения");
        long destination = askForAccountId(bank, scanner);

        if (bank.getTypeAccount(source) != bank.getTypeAccount(destination)) {
            System.out.println("<!> Счета разных типов");
            return;
        }

        double count = getCount(bank, scanner, source);

        try {
            bank.transfer(client, source, destination, bank.generateValue(source, count));
            System.out.println("> Перевод выполнен успешно");
        } catch (NotEnoughValuableException e) {
            System.out.println("<!> Недостаточно на счету");
        } catch (IllegalArgumentException e) {
            System.out.println("<!> " + e.getMessage());
        }
    }

    private static double getCount(Bank bank, Scanner scanner, long source) {
        switch (bank.getTypeAccount(source)) {
            case 1:
            case 2:
            case 3:
                return askForAmount(scanner);
            case 4:
            case 5:
                return askForCount(scanner);
        }
        return 0;
    }

    /*
    пункт снятие со счета
     */
    private static void withdrawAccountMoney(Bank bank, Scanner scanner) {
        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;
        long accountId = askForAccountId(bank, scanner);

        double count = getCount(bank, scanner, accountId);

        try {
            bank.withdrawFromAccount(client, accountId, bank.generateValue(accountId, count));
            System.out.println("> Cнятие выполнено успешно");
        } catch (NotEnoughValuableException e) {
            System.out.println("<!> Недостаточно на счету");
        } catch (IllegalArgumentException e) {
            System.out.println("<!> " + e.getMessage());
        }
    }

    /*
    пункт проверка баланса
     */
    private static void checkAccountBalance(Bank bank, Scanner scanner) {
        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;
        long accountId = askForAccountId(bank, scanner);

        switch (bank.getTypeAccount(accountId)) {
            case 1:
            case 2:
            case 3:
                Money money = (Money) bank.getAccountBalance(client, accountId);
                System.out.println("Денег на счету = " + money.getAmount() + " " + money.getCurrency());
                break;
            case 4:
                Stock stock = (Stock) bank.getAccountBalance(client, accountId);
                System.out.println("В ячейке: " + stock.getCount() + " акций компании " + stock.getCompany()
                        + " на сумму " + stock.getAmount());
                break;
            case 5:
                Metal metal = (Metal) bank.getAccountBalance(client, accountId);
                System.out.println("В ячейке: " + metal.getCount() + " грамм " + metal.getType() + " на сумму "
                        + metal.getAmount());
                break;
        }
    }

    /*
    пополнить счет
     */
    private static void depositAccountMoney(Bank bank, Scanner scanner) {
        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;
        long accountId = askForAccountId(bank, scanner);
        double count = 0;
        switch (bank.getTypeAccount(accountId)) {
            case 1:
                count = askForAmount(scanner);
                break;
            case 2:
            case 3:
                count = askForCount(scanner);
                break;
        }

        try {
            bank.depositOnAccount(client, accountId, bank.generateValue(accountId, count));
            System.out.println("> Счет пополнен успешно");
        } catch (IllegalArgumentException e) {
            System.out.println("<!> " + e.getMessage());
        }
    }

    /*
    запрос номера счета
     */
    private static long askForAccountId(Bank bank, Scanner scanner) {
        long id;

        do {
            System.out.print("Введите номер счета = ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0) {
                    if (bank.searchAccountById(id)) {
                        return id;
                    } else {
                        id = 0;
                        System.out.println("Такого счёта не существует");
                    }
                } else {
                    id = 0;
                }
            } catch (NumberFormatException e) {
                id = 0;
            }
        } while (id <= 0);


        return id;
    }

    /*
    пункт регистрация нового счета
     */
    private static void registerNewAccount(Bank bank, Scanner scanner) {

        Client client = askForClientInfo(bank, scanner);
        if (client == null) return;
        int accountType = askForAccount(scanner);
        if (accountType == 0) return;
        switch (accountType) {
            case 0:
                return;
            case 1:
                Currency currency = askForCurrency(scanner);
                double amount = askForAmount(scanner);
                Money money = new Money(currency, amount);
                long number = bank.askForNewDebitMoneyAccount(client, money);
                System.out.println("> Открыт новый дебетовый счет под номером " + number);
                break;
            case 2:
                currency = askForCurrency(scanner);
                amount = askForAmount(scanner);
                money = new Money(currency, amount);
                int percents = askForPercents(scanner);
                number = bank.askForNewCreditMoneyAccount(client, money, percents);
                System.out.println("> Открыт новый кредитный счет под номером " + number);
                break;
            case 3:
                currency = askForCurrency(scanner);
                amount = askForAmount(scanner);
                money = new Money(currency, amount);
                percents = askForPercents(scanner);
                number = bank.askForNewPercentMoneyAccount(client, money, percents);
                System.out.println("> Открыт новый процентный счет под номером " + number);
                break;
            case 4:
                Stock stock = askForStock(scanner);
                long cell = bank.askForNewStockAccount(client, stock);
                System.out.println("> Открыт новый счет под номером " + cell);
                break;
            case 5:
                Metal metal = askForMetal(scanner);
                cell = bank.askForNewMetalAccount(client, metal);
                System.out.println("> Открыт новый счет под номером " + cell);
                break;
        }
    }

    /*
    запрос типа метала
     */
    private static Metal askForMetal(Scanner scanner) {

        Metal metal = null;
        int type;

        do {
            System.out.println("Выберите метал");
            System.out.println("1. Золото");
            System.out.println("2. Серебро");
            System.out.println("3. Платино");
            System.out.println("4. Палладий");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            try {
                switch (type) {
                    case 1:
                        int count = askForCount(scanner);
                        metal = new Metal(Metals.GOLD, count);
                        break;
                    case 2:
                        count = askForCount(scanner);
                        metal = new Metal(Metals.SILVER, count);
                        break;
                    case 3:
                        count = askForCount(scanner);
                        metal = new Metal(Metals.PLATINUM, count);
                        break;
                    case 4:
                        count = askForCount(scanner);
                        metal = new Metal(Metals.PALLADIUM, count);
                        break;
                    default:
                        type = -1;
                        break;
                }
            } catch (IllegalArgumentException e) {
                type = -1;
                System.out.println("<!>" + e.getMessage());
            }

        } while (type == -1);
        return metal;
    }

    /*
    запрос название компании для акций
     */
    private static Stock askForStock(Scanner scanner) {

        int type;
        Stock stock = null;

        do {
            System.out.println("Выберите компанию");
            Stocks stocks = new Stocks();
            String[] company = new String[stocks.size()];

            int index = 0;
            for (String str : stocks) {
                company[index++] = str;
            }

            for (int i = 1; i <= company.length; i++) {
                System.out.println(i + ". " + company[i - 1]);
            }
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }

            if (type > company.length || type < 1) {
                type = -1;
            }
            if (type != -1) {
                int count = askForCount(scanner);
                stock = new Stock(company[type - 1], count);
            }

        } while (type == -1);
        return stock;
    }

    /*
    запрос на тип счета
     */
    private static int askForAccount(Scanner scanner) {

        int type;

        do {
            System.out.println("Выберите тип счета");
            System.out.println("1. Дебетовый");
            System.out.println("2. Кредитный");
            System.out.println("3. Процентный");
            System.out.println("4. Ячейка для акций");
            System.out.println("5. Ячейка для драгоценных металов");
            System.out.println("");
            System.out.println("0. Отмена");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 0:
                    type = 0;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return type;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return type;
    }

    /*
    запрос на тип валюты
     */
    private static Currency askForCurrency(Scanner scanner) {

        int type;

        do {
            System.out.println("Выберите тип счета");
            System.out.println("1. BYN");
            System.out.println("2. EUR");
            System.out.println("3. USD");
            System.out.println("4. RUR");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 1:
                    return Currency.BYN;
                case 2:
                    return Currency.EUR;
                case 3:
                    return Currency.USD;
                case 4:
                    return Currency.RUR;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return Currency.BYN;
    }

    /*
    запрос процентов
     */
    private static int askForPercents(Scanner scanner) {
        int percents = 0;

        do {
            System.out.print("Введите процент = ");
            try {
                percents = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                percents = 0;
            }
        } while (percents <= 0);
        return percents;
    }

    /*
    запрос на наличие такого клиента по по номеру паспорта
     */
    private static Client askForClientInfo(Bank bank, Scanner scanner) {
        String passport;
        do {
            System.out.print("Введите номер паспорта = ");
            passport = scanner.nextLine();
        } while (passport.isEmpty());

        boolean clientExists = bank.searchClientByPassport(passport);
        if (clientExists) {
            return new Client("_", "_", passport);
        } else {
            System.out.println("<!> Такого клиента не существует");
        }
        return null;
    }

    /*
    пункт рагистрация клианта
     */
    private static void registerNewClient(Bank bank, Scanner sc) {

        boolean success = false;
        try {
            System.out.print("Введите имя = ");
            String name = sc.nextLine();
            System.out.print("Введите фамилию = ");
            String surname = sc.nextLine();
            System.out.print("Введите номер паспорта = ");
            String passportNumber = sc.nextLine();
            bank.registerClient(name, surname, passportNumber);
            success = true;
        } catch (IllegalArgumentException e) {
            success = false;
            System.out.println(e.getMessage());
        } catch (Exception e) {
            success = false;
        }

        if (success) {
            System.out.println("> Клиент успешно зарегистрирован");
        } else {
            System.out.println("<!> Ошибка при регистрации клиента");
        }
    }

    /*
    запрос на количество для ячейки
     */
    private static int askForCount(Scanner scanner) {
        int count;

        do {
            System.out.print("Введите количество = ");
            try {
                count = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                count = 0;
            }
        } while (count <= 0);
        return count;
    }

    /*
    запрос на количество денег
     */
    private static double askForAmount(Scanner scanner) {
        double count;

        do {
            System.out.print("Введите количество = ");
            try {
                count = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                count = 0;
            }
        } while (count <= 0);
        return count;
    }
}
