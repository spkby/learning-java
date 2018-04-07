package by.itclass.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JTextField usernameField = new JTextField();
    JButton button = new JButton("Войти");

    public LoginFrame() {
        this.setLayout(new BorderLayout());

        this.add(usernameField);
        this.add(button, BorderLayout.SOUTH);

        this.setSize(200, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        button.addActionListener(this);
    }

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!usernameField.getText().isEmpty()) {
            MainFrame.startGame(usernameField.getText());
            this.setVisible(false);
        }
    }
}
