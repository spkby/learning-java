package by.itclass.game.core;

import by.itclass.game.core.commands.*;
import by.itclass.game.core.events.Event;
import by.itclass.game.core.events.*;
import by.itclass.game.core.events.Observer;
import by.itclass.game.db.RoundDAO;
import by.itclass.game.db.entities.Round;
import by.itclass.game.io.UnitTypeLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Queue;

/**
 * Класс, в котором инкапсулирована вся логика игры
 */
public class Game implements Drawable, Updatable, Observer<Event>, EventSource<Event> {

    private GameMap gameMap;
    private Unit hero;
    private Queue<Command> queue;

    private GameState gameState;

    private List<Observer<Event>> observers;

    private List<Enemy> enemies;
    private FinalPoint finalPoint;

    private int totalTime;

    private int playerId;
    private RoundDAO dao;

    public Game(GameMap map,
                UnitTypeLoader loader,
                FinalPoint finalPoint,
                List<Enemy> enemies,
                Unit player,
                int playerId,
                RoundDAO dao) {
        if (map == null) {
            throw new IllegalArgumentException("Карта отсутствует");
        }
        if (loader == null) {
            throw new IllegalArgumentException("Отсутствует информация о героях");
        }
        if (finalPoint == null) {
            throw new IllegalArgumentException("Отсутствует точка назначения");
        }
        this.finalPoint = finalPoint;
        this.gameMap = map;
        this.enemies = enemies;

        this.observers = new ArrayList<>();

        this.playerId = playerId;
        this.dao = dao;

        this.hero = player;
        this.hero.addObserver(this);

        for (Enemy enemy : enemies) {
            enemy.addObserver(this);
        }

        this.addObserver(this);

        this.gameState = GameState.PLAY;

        this.queue = new LinkedList<>();
    }

    public int getWidth() {
        return this.gameMap.getWidth() * this.gameMap.CELL_WIDTH;
    }

    public int getHeight() {
        return this.gameMap.getHeight() * this.gameMap.CELL_HEIGHT;
    }

    public void update(double elapsedTime) {

        switch (gameState) {
            case PLAY:
                while (!queue.isEmpty()) {
                    Command command = queue.poll();
                    if (command != null) {

                        if (command instanceof KeyboardPressCommand) {
                            KeyboardPressCommand kpc = (KeyboardPressCommand) command;
                            int key = kpc.getCode();
                            switch (key) {
                                case KeyEvent.VK_UP:
                                    Command c = new VerticalMovementCommand((byte) -1, hero);
                                    sendCommand(c);
                                    break;
                                case KeyEvent.VK_DOWN:
                                    c = new VerticalMovementCommand((byte) 1, hero);
                                    sendCommand(c);
                                    break;
                                case KeyEvent.VK_LEFT:
                                    c = new HorizontalMovementCommand((byte) -1, hero);
                                    sendCommand(c);
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    c = new HorizontalMovementCommand((byte) 1, hero);
                                    sendCommand(c);
                                    break;
                            }
                        }

                        if (command instanceof KeyboardReleaseCommand) {
                            KeyboardReleaseCommand kpc = (KeyboardReleaseCommand) command;
                            int key = kpc.getCode();
                            switch (key) {
                                case KeyEvent.VK_UP:
                                case KeyEvent.VK_DOWN:
                                    Command c = new VerticalMovementCommand((byte) 0, hero);
                                    sendCommand(c);
                                    break;
                                case KeyEvent.VK_LEFT:
                                case KeyEvent.VK_RIGHT:
                                    c = new HorizontalMovementCommand((byte) 0, hero);
                                    sendCommand(c);
                                    break;
                            }
                        }

                        command.execute();
                    }
                }
                hero.update(elapsedTime);

                for (Unit unit : enemies) {
                    unit.update(elapsedTime);
                }
                totalTime += (int) (elapsedTime * 1000);
                break;
        }


    }

    public void sendCommand(Command command) {
        queue.add(command);
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        g.clearRect(0, 0, gameMap.getPixelWidth(), gameMap.getPixelHeight());
        gameMap.draw(g, deltaTime);
        hero.draw(g, deltaTime);
        finalPoint.draw(g, deltaTime);
        for (Unit unit : enemies) {
            unit.draw(g, deltaTime);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void notify(Event event) {
        if (event instanceof UnitCellChangeEvent) {
            UnitCellChangeEvent changeEvent = (UnitCellChangeEvent) event;

            if (changeEvent.getSource() == hero) {
                int h_x = hero.getCellX();
                int h_y = hero.getCellY();
                int f_x = finalPoint.getCellX();
                int f_y = finalPoint.getCellY();

                if (h_x == f_x &&
                        h_y == f_y) {
                    for (Observer<Event> observer : observers) {
                        observer.notify(new WinEvent(this));
                    }
                }
            } else if (enemies.contains(changeEvent.getSource())) {

                if (hero.intersects((Unit) changeEvent.getSource())) {
                    for (Observer<Event> observer : observers) {
                        observer.notify(new LoseEvent(this));
                    }
                }
            }
        } else if (event instanceof WinEvent) {
            gameState = GameState.WIN;

            Round round = new Round();
            round.setTime(totalTime);
            round.setPlayDate(new Date());
            round.setPlayerId(playerId);

            dao.add(round);

        } else if (event instanceof LoseEvent) {
            gameState = GameState.LOSE;
        }
    }

    @Override
    public void addObserver(Observer<Event> observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Event> observer) {
        this.observers.remove(observer);
    }
}
