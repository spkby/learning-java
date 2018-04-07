package by.itclass.game.db.entities;

import java.util.Date;

/**
 * Класс, представляющий собой запись о сыгранном раунде
 */
public class Round {

    private int id;
    private int playerId;
    private int time;
    private Date playDate;

    public Round() {

    }

    public Round(int id,
                 int playerId,
                 int time,
                 Date playDate) {
        this.id = id;
        this.playerId = playerId;
        this.time = time;
        this.playDate = playDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }
}
