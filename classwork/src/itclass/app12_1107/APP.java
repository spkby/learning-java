package itclass.app12_1107;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class APP {

    public static void main(String[] args) {


        Frame frame = new Frame();


        frame.setTitle("Окно AWT");

        frame.setSize(640, 480);

        frame.setVisible(true);


        //
        //frame.addWindowListener(new FrameListener());
        frame.addWindowListener(new FrameListener2());

    }
}


    class FrameListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent windowEvent) {

            System.out.println("windows open");

        }

        @Override
        public void windowClosing(WindowEvent windowEvent) {
            System.out.println("windows try to close");
            windowEvent.getWindow().dispose();

        }

        @Override
        public void windowClosed(WindowEvent windowEvent) {
            System.out.println("windows closed");

        }

        @Override
        public void windowIconified(WindowEvent windowEvent) {
            System.out.println("windows unminimized");
        }

        @Override
        public void windowDeiconified(WindowEvent windowEvent) {
            System.out.println("windows minimized");
        }

        @Override
        public void windowActivated(WindowEvent windowEvent) {
            System.out.println("windows actived");
        }

        @Override
        public void windowDeactivated(WindowEvent windowEvent) {
            System.out.println("windows deactived");
        }
    }


class FrameListener2 extends WindowAdapter{

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        windowEvent.getWindow().dispose();
    }
}