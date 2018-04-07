package by.itclass.game.db;

import by.itclass.game.db.entities.Round;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс доступа к информации о данных
 */
public class RoundDAO {

    private static final String ADD_SQL = "INSERT INTO rounds (player_id, time, playdate) " +
            "VALUES (?, ?, ?)";
    private static final String REMOVE_SQL = "DELETE FROM rounds where id = ?";
    private static final String UPDATE_SQL = "UPDATE rounds SET player_id = ?, time = ?," +
            " playdate = ? WHERE id = ?";
    private static final String FIND_SQL = "SELECT * FROM rounds WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM rounds";
    private static final String FIND_TOP_SQL = "SELECT * FROM rounds ORDER BY time ASC LIMIT ?";

    private PreparedStatement addStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement removeStatement;
    private PreparedStatement findStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement findTopStatement;

    public RoundDAO(Connection connection) {
        try {
            addStatement = connection.prepareStatement(ADD_SQL, Statement.RETURN_GENERATED_KEYS);
            removeStatement = connection.prepareStatement(REMOVE_SQL);
            updateStatement = connection.prepareStatement(UPDATE_SQL);
            findStatement = connection.prepareStatement(FIND_SQL);
            findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
            findTopStatement = connection.prepareStatement(FIND_TOP_SQL);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Невозжно подготовить запрос");
        }
    }

    public void add(Round round) {

        try {
            addStatement.setInt(1, round.getPlayerId());
            addStatement.setInt(2, round.getTime());
            java.sql.Date sqlDate =
                    new java.sql.Date(round.getPlayDate().getTime());
            addStatement.setDate(3, sqlDate);
            addStatement.executeUpdate();

            ResultSet key = addStatement.getGeneratedKeys();

            if (key.next()) {
                int id = key.getInt(1);
                round.setId(id);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка добавления");
        }

    }

    public void delete(Round round) {

        try {
            removeStatement.setInt(1, round.getId());
            removeStatement.executeUpdate();
            round.setId(0);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка удаления");
        }


    }

    public void update(Round round) {
        try {
            updateStatement.setInt(1, round.getPlayerId());
            updateStatement.setInt(2, round.getTime());
            java.sql.Date sqlDate =
                    new java.sql.Date(round.getPlayDate().getTime());
            updateStatement.setDate(3, sqlDate);
            updateStatement.setInt(4, round.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка добавления");
        }
    }

    public Round find(int id) {
        try {

            findStatement.setInt(1, id);
            findStatement.execute();

            ResultSet rows = findStatement.getResultSet();

            if (rows.next()) {
                int player_id = rows.getInt("player_id");
                int time = rows.getInt("time");
                java.util.Date playDate = rows.getDate("playdate");

                return new Round(id, player_id, time, playDate);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка получения");
        }
    }


    public List<Round> findAll() {
        List<Round> rounds = new ArrayList<>();
        try {

            findAllStatement.execute();

            ResultSet rows = findAllStatement.getResultSet();

            while (rows.next()) {
                int id = rows.getInt("id");
                int player_id = rows.getInt("player_id");
                int time = rows.getInt("time");
                java.util.Date playDate = rows.getDate("playdate");
                rounds.add(new Round(id, player_id, time, playDate));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка получения");
        }
        return rounds;
    }

    public List<Round> findTop(int count) {
        List<Round> rounds = new ArrayList<>();
        try {

            findTopStatement.setInt(1, count);
            findTopStatement.execute();

            ResultSet rows = findTopStatement.getResultSet();

            while (rows.next()) {
                int id = rows.getInt("id");
                int player_id = rows.getInt("player_id");
                int time = rows.getInt("time");
                java.util.Date playDate = rows.getDate("playdate");
                rounds.add(new Round(id, player_id, time, playDate));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка получения");
        }
        return rounds;
    }

}
