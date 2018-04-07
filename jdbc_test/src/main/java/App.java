import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class App {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        //Объект, представляющий собой соединение с базой данных
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://" +
                        "localhost:5432/company",
                        "IT Class",
                        "123456");
        connection.setAutoCommit(false);

        System.out.println("Соединение установлено");
        //selectTest(connection);
        //selectTable(connection);
        //updateTable(connection);
        //insertTable(connection);
        //insertTablePlain(connection);
        //insertTablePrepared(connection);
        //insertTableBatch(connection);
        //insertTableBatchNoCommit(connection);
        insertTablePreparedFromFile(connection);
        connection.close();

    }

    private static void selectTest(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        //Выполнение запроса в БД
        boolean success = statement.execute("SELECT 2 + 2 AS SUM;");

        if (success) {
            //Получение результатов запроса
            ResultSet result = statement.getResultSet();

            //Получить следующую строчку
            boolean isRes = result.next();

            if (isRes) {
                // Нумерация столбцов начинается с 1
                int res = result.getInt("SUM");
                System.out.println("2 + 2 = " + res);
            }

        } else {
            System.out.println("Выражение не вычислено");
        }
    }

    private static void selectTable(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        //Выполнение запроса в БД
        boolean success = statement.execute("SELECT name, surname FROM workers;");

        if (success) {
            //Получение результатов запроса
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                String name = result.getString("name");
                String surname = result.getString("surname");
                System.out.println(name + ", " + surname);
            }

        } else {
            System.out.println("Выражение не вычислено");
        }
    }

    private static void updateTable(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        //Выполнение запроса в БД (вставка, удаление и изменение)
        int count = statement.executeUpdate("UPDATE salaries " +
                "SET quantity = quantity + quantity / 10" +
                "WHERE quantity < 1000;");

        System.out.println("Количество измененных строк = " + count);
    }

    private static void insertTable(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        //Выполнение запроса в БД (вставка, удаление и изменение)
        int count = statement.executeUpdate("INSERT INTO workers"
                + "(name, surname, birthdate)" +
                "VALUES ('Ivan', 'Ivanov', '10.10.2010')",
                Statement.RETURN_GENERATED_KEYS);

        //Получить идентификаторы вставленных строчек
        ResultSet idset = statement.getGeneratedKeys();

        while (idset.next()) {
            int id = idset.getInt(1);
            System.out.println("id вставленной записи - " + id);
        }

        System.out.println("Количество вставленных строк = " + count);
    }


    private static void insertTablePlain(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        long time = System.nanoTime();

        int count = 0;
        //Выполнение запроса в БД (вставка, удаление и изменение)
        for (int i = 0; i < 100000; i++) {
            count += statement.executeUpdate("INSERT INTO workers"
                            + "(name, surname, birthdate)" +
                            "VALUES ('Ivan', 'Ivanov', '10.10.2010')",
                    Statement.NO_GENERATED_KEYS);
        }
        connection.commit();


        long time2 = System.nanoTime();

        System.out.println("Количество вставленных строк = " + count);
        System.out.printf("Время = %.3f", (time2 - time) / 1e9);
    }

    private static void insertTablePrepared(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        PreparedStatement statement = connection.prepareStatement("INSERT INTO workers"
                + "(name, surname, birthdate)" +
                "VALUES ('Ivan', 'Ivanov', '10.10.2010')");

        long time = System.nanoTime();

        int count = 0;
        //Выполнение запроса в БД (вставка, удаление и изменение)
        for (int i = 0; i < 100000; i++) {
            count += statement.executeUpdate();
        }
        connection.commit();

        long time2 = System.nanoTime();

        System.out.println("Количество вставленных строк = " + count);
        System.out.printf("Время = %.3f", (time2 - time) / 1e9);
    }


    private static void insertTableBatch(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        long time = System.nanoTime();

        int count = 0;
        //Выполнение запроса в БД (вставка, удаление и изменение)
        for (int i = 0; i < 100000; i++) {
            statement.addBatch("INSERT INTO workers"
                    + "(name, surname, birthdate)" +
                    "VALUES ('Ivan', 'Ivanov', '10.10.2010')");
        }
        int[] ids = statement.executeBatch();


        long time2 = System.nanoTime();

        System.out.println("Количество вставленных строк = " + ids.length);
        System.out.printf("Время = %.3f", (time2 - time) / 1e9);
    }


    private static void insertTableBatchNoCommit(Connection connection) throws SQLException {
        //Объект, предназначенный для выполнения запросов к БД
        Statement statement = connection.createStatement();

        long time = System.nanoTime();

        int count = 0;
        //Выполнение запроса в БД (вставка, удаление и изменение)
        for (int i = 0; i < 100000; i++) {
            statement.addBatch("INSERT INTO workers"
                    + "(name, surname, birthdate)" +
                    "VALUES ('Ivan', 'Ivanov', '10.10.2010')");
        }
        int[] ids = statement.executeBatch();
        connection.commit();

        long time2 = System.nanoTime();

        System.out.println("Количество вставленных строк = " + ids.length);
        System.out.printf("Время = %.3f", (time2 - time) / 1e9);
    }


    private static void insertTablePreparedFromFile(Connection connection) throws SQLException, IOException, ParseException {
        //Объект, предназначенный для выполнения запросов к БД

        BufferedReader reader = new BufferedReader(new FileReader("c:/workers.txt"));

        PreparedStatement statement = connection.prepareStatement("INSERT INTO workers"
                + "(name, surname, birthdate)" +
                "VALUES (?, ?, ?)");

        long time = System.nanoTime();

        int count = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdate = format.parse("10.10.2012");
        java.sql.Date sqlDate = new java.sql.Date(birthdate.getTime());
        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            String surname = parts[1];
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setDate(3, sqlDate);
            count += statement.executeUpdate();
            line = reader.readLine();
        }
        connection.commit();

        long time2 = System.nanoTime();

        System.out.println("Количество вставленных строк = " + count);
        System.out.printf("Время = %.3f", (time2 - time) / 1e9);
    }


}
