package itclass.app14_0911;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToe extends JFrame {
private Figure move;

    private Figure[][] figures;

    public TicTacToe() {


        setTitle("TicTacToe");
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300,300));

        figures = new Figure[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                figures[i][j] = Figure.EMPTY;

            }
        }

        move = Figure.CROSS;


        //addMouseListener(new MouseListener());

        setVisible(true);



    }


    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        drawLines(graphics);
        drawFigures(graphics);
    }

    private void drawLines(Graphics graphics) {

        for (int i = 0; i < 2; i++) {
            int x = (i + 1) * this.getWidth() / 3;
            graphics.drawLine(x,0,x,this.getHeight()-1);
        }

        for (int i = 0; i < 2; i++) {
            int y = (i + 1) * this.getWidth() / 3;
            graphics.drawLine(0,y,this.getWidth()-1,y);
        }


    }

    private void drawFigures(Graphics graphics) {

        final int RADIUS = 10;
        int w = getWidth();
        int h = getHeight();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int x = j * (w / 3) + w / 6;
                int y = i * (h / 3) + h / 6;

                Figure figure = figures[i][j];

                switch (figure) {
                    case NOUGHT:
                        graphics.drawOval(x-RADIUS/2,y-RADIUS/2,RADIUS,RADIUS);
                        break;
                    case CROSS:
                        graphics.drawLine(x-RADIUS/2, y-RADIUS/2,x+RADIUS/2,y+RADIUS/2);
                        graphics.drawLine(x-RADIUS/2, y+RADIUS/2,x+RADIUS/2,y-RADIUS/2);
                        break;
                }
            }

        }

    }

    public static void main(String[] args) {


        new TicTacToe();


    }


    class TTTMouseAdapter extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            int i = e.getY() / (getHeight() / 3);
            int j = e.getX() / (getWidth() / 3);

            if (figures[i][j] == Figure.EMPTY) {
                if (move == Figure.CROSS) {
                    figures[i][j] = Figure.CROSS;
                    move = Figure.NOUGHT;
                } else if (move == Figure.NOUGHT) {
                    figures[i][j] = Figure.NOUGHT;
                    move = Figure.CROSS;
                }
            }
            repaint();
            Figure win = checkWin();
            /*switch(win){
                case CROSS:*/
        }





    }

    protected Figure checkWin() {
        Figure result = checkHorizontalWin();
        if (result != Figure.EMPTY) {
            return result;
        }

        result = checkVerticalWin();
        if (result != Figure.EMPTY) {
            return result;
        }
        result = checkDiagonalWin();
        if (result != Figure.EMPTY) {
            return result;
        }
        return Figure.EMPTY;

    }

    private Figure checkHorizontalWin() {

        for (int i = 0; i < 3; i++) {
            if (figures[i][0] == figures[i][1] && figures[i][2] == figures[i][1]) {
                if (figures[i][1] != Figure.EMPTY) return figures[i][1];
            }
        }
        return Figure.EMPTY;
    }
    private Figure checkVerticalWin() {
        for (int j = 0; j < 3; j++) {
            if (figures[0][j] == figures[1][j] && figures[2][j] == figures[1][j]) {
                if (figures[1][j] != Figure.EMPTY) return figures[1][j];
            }
        }
        return Figure.EMPTY;
    }
    private Figure checkDiagonalWin() {

        if (figures[0][0] == figures[1][1] && figures[2][2] == figures[1][1]) {
            if (figures[1][1] != Figure.EMPTY) return figures[1][1];
        }
        if (figures[0][2] == figures[1][1] && figures[2][0] == figures[1][1]) {
            if (figures[1][1] != Figure.EMPTY) return figures[1][1];
        }
        return Figure.EMPTY;
    }

    enum Figure {
        EMPTY,
        NOUGHT,
        CROSS
    }

}

