package by.itclass.game.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStorage implements AutoCloseable {

    private Connection connection;
    private PreparedStatement usernameStatement;
    private PreparedStatement usernameInsertStatement;
    private PreparedStatement usernameFindStatement;
    private PreparedStatement usernameGetStatement;

    public PlayerStorage(Connection connection) {

        try {
            this.connection = connection;

            usernameStatement = connection.prepareStatement("SELECT * FROM players WHERE name = ?");
            usernameInsertStatement = connection.prepareStatement("INSERT INTO players (name) VALUES (?)");
            usernameFindStatement = connection.prepareStatement("SELECT id FROM players WHERE name = ?");
            usernameGetStatement = connection.prepareStatement("SELECT name FROM players WHERE id = ?");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка создания подключения");
        }

    }

    public boolean isUserRegistered(String username) {

        try {
            usernameStatement.setString(1, username);

            boolean result = usernameStatement.execute();

            if (result) {

                ResultSet resultSet = usernameStatement.getResultSet();

                return resultSet.next();

            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Неверное имя пользователя");
        }


    }


    public boolean registerUser(String username) {
        try {
            usernameInsertStatement.setString(1, username);

            int result = usernameInsertStatement.executeUpdate();


            return result == 1;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Неверное имя пользователя");
        }
    }

    public int findPlayerId(String username) {
        try {
            usernameFindStatement.setString(1, username);

            boolean result = usernameFindStatement.execute();

            if (result) {

                ResultSet resultSet = usernameFindStatement.getResultSet();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    return id;
                } else {
                    return 0;
                }

            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Неверное имя пользователя");
        }
    }

    public String findPlayer(int id) {
        try {
            usernameGetStatement.setInt(1, id);

            boolean result = usernameGetStatement.execute();

            if (result) {

                ResultSet resultSet = usernameGetStatement.getResultSet();

                if (resultSet.next()) {
                    String name = resultSet.getString(1);
                    return name;
                } else {
                    return null;
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Неверное имя пользователя");
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
