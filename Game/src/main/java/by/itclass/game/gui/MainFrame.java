package by.itclass.game.gui;

import by.itclass.game.core.*;
import by.itclass.game.core.commands.KeyboardPressCommand;
import by.itclass.game.core.commands.KeyboardReleaseCommand;
import by.itclass.game.db.PlayerStorage;
import by.itclass.game.db.RoundDAO;
import by.itclass.game.db.entities.Round;
import by.itclass.game.io.PointImageReader;
import by.itclass.game.io.UnitTypeLoader;
import by.itclass.game.io.json.CellTypeLoader;
import by.itclass.game.io.json.MapReader;
import by.itclass.game.io.sax.SAXUnitTypeLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {

    private long TIMER_TICK = 1000 / 30;

    private long prevTime;

    private Timer timer;

    private Game game;

    private static PlayerStorage storage;

    private static RoundDAO dao;
    private JLabel label;
    private JList<String> recordList;
    private JScrollPane scrollPane;
    private boolean needList = true;

    public MainFrame(GameMap map,
                     FinalPoint point,
                     UnitTypeLoader unitLoader,
                     List<Enemy> enemies,
                     Unit player,
                     int playerId, RoundDAO dao) {

        if (map == null) {
            throw new IllegalArgumentException("Отсутствует карта");
        }


        game = new Game(map, unitLoader, point, enemies, player, playerId, dao);

        this.addKeyListener(new KeyboardListener());

        timer = new Timer();
        timer.schedule(new GameTimer(), 0, TIMER_TICK);

        int windowWidth = game.getWidth();
        int windowHeight = game.getHeight();

        this.setUndecorated(true);
        this.setLayout(new BorderLayout());

        recordList = new JList<>();

        Font font = new Font("Consolas", Font.BOLD, 32);
        recordList.setFont(font);
        //this.add(recordList, BorderLayout.CENTER);
        scrollPane = new JScrollPane(recordList);
        scrollPane.setVisible(false);
        this.add(scrollPane, BorderLayout.CENTER);

        label = new JLabel();
        label.setFont(new Font("Consolas", Font.BOLD, 32));

        label.setVisible(false);
        this.add(label, BorderLayout.NORTH);

        this.setSize(windowWidth, windowHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


        prevTime = System.nanoTime();
    }


    public static void startGame(String name) {

        SAXUnitTypeLoader unitLoader = new SAXUnitTypeLoader("units.xml");
        unitLoader.load();

        CellTypeLoader loader = new CellTypeLoader("images.json");
        loader.load();

        PointImageReader imgReader = new PointImageReader("destination.txt");
        BufferedImage img = imgReader.readImage();

        MapReader reader = new MapReader("map.json");
        GameMap map = reader.read(loader);
        FinalPoint point = reader.readFinalPoint(map, img);
        List<Enemy> enemies = reader.readEnemies(unitLoader, map);
        Unit player = reader.readHero(map, unitLoader);

        Connection connection = openConnection("db.properties");

        storage = openStorage(connection);

        if (!storage.isUserRegistered(name)) {
            storage.registerUser(name);
        }

        int playerId = storage.findPlayerId(name);

        dao = new RoundDAO(connection);

        new MainFrame(map, point, unitLoader, enemies, player, playerId, dao);

    }

    private static Connection openConnection(String propertiesFile) {

        Properties properties = new Properties();
        try {
            properties.load(MainFrame.class.getClassLoader().getResourceAsStream(propertiesFile));

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            throw new IllegalArgumentException("Отсутствует файл с настройками подключения");
        }
    }

    private static PlayerStorage openStorage(Connection connection) {
        return new PlayerStorage(connection);
    }

    @Override
    public void paint(Graphics g) {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - prevTime) * 1.0 / 1000000000;
        prevTime = currentTime;
        if (game.getGameState() == GameState.PLAY) {
            game.draw(g, deltaTime);
        } else {
            timer.cancel();
            if (needList) {
                g.clearRect(0, 0, this.getWidth(), this.getHeight());
                List<Round> rounds = dao.findTop(20);

                String[] strings = new String[rounds.size()];
                for (int i = 0; i < rounds.size(); i++) {
                    String username = storage.findPlayer(rounds.get(i).getPlayerId());
                    strings[i] = String.format("%02d - %s - %.3f c", (i + 1), username, rounds.get(i).getTime() / 1000.0);
                }

                if (game.getGameState() == GameState.WIN) {
                    label.setForeground(Color.GREEN);
                    label.setText("Победа");
                    label.setVisible(true);
                } else {

                    label.setForeground(Color.RED);
                    label.setText("Поражение");
                    label.setVisible(true);
                }

                recordList.setListData(strings);
                scrollPane.setVisible(true);
                needList = false;
            }

        }
    }

    /**
     * Внутренний класс, может существовать только
     * внутри объекта внешнего класса и имеет доступ
     * к его переменным
     */
    class KeyboardListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            KeyboardPressCommand pk = new KeyboardPressCommand(game, e.getKeyCode());
            game.sendCommand(pk);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            KeyboardReleaseCommand kc = new KeyboardReleaseCommand(game, e.getKeyCode());
            game.sendCommand(kc);
        }
    }

    class GameTimer extends TimerTask {

        long prevTime;

        public GameTimer() {
            prevTime = System.nanoTime();
        }

        @Override
        public void run() {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - prevTime) * 1.0 / 1000000000;
            prevTime = currentTime;
            game.update(deltaTime);
            repaint();
        }
    }

}
