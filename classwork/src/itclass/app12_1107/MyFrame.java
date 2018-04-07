package itclass.app12_1107;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyFrame extends Frame implements ActionListener {


    public MyFrame() {

     //   this.

        this.setTitle("Title");
        this.setSize(640, 480);

        this.addWindowListener(new FrameListener2());


        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        /*GridLayout gridLayout = new GridLayout(3,3);
        this.setLayout(gridLayout);*/


        /*

         */
        Label label = new Label();
        label.setText("Label");
        add(label,BorderLayout.CENTER);

        /*

         */
        Button button = new Button();
        button.setLabel("But");
        add(button,BorderLayout.EAST);
        button.addActionListener(this);


        /*

        */
        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("X");
        checkbox.setState(true);
        add(checkbox,BorderLayout.EAST);

        /*

 */

        Checkbox android = new Checkbox("Android");
        Checkbox ios = new Checkbox("iOS");

        CheckboxGroup os = new CheckboxGroup();
        android.setCheckboxGroup(os);
        ios.setCheckboxGroup(os);
        add(android,BorderLayout.WEST);
        add(ios,BorderLayout.WEST);


/*

 */
        TextField textField = new TextField();
        add(textField);
        textField.setEchoChar('*');


        /*

         */
        TextArea textArea = new TextArea();
        add(textArea, BorderLayout.CENTER);

        /*

         */
        List list = new List();
        list.setMultipleMode(true);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        add(list,BorderLayout.CENTER);

/*

 */

/*
        FlowLayout flowLayout = new FlowLayout(FlowLayout.TRAILING);
        this.setLayout(flowLayout);
*/


        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuFileOpenFile  = new MenuItem("Open file");
        menuFileOpenFile.setShortcut(new MenuShortcut(KeyEvent.VK_O));
        MenuItem menuFileSaveFile  = new MenuItem("Save file");
        MenuItem menuFileExit  = new MenuItem("Exit");
        Menu menuHelp = new Menu("Help");
        menuFile.add(menuFileOpenFile);
        menuFile.add(menuFileSaveFile);
        menuFile.add(menuFileExit);
        MenuItem menuHelpAbout = new MenuItem("About");
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        this.setMenuBar(menuBar);



        this.setVisible(true);



    }


    public static void main(String[] args) {

        new MyFrame();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("aaaa");


    }
}
