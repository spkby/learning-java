package itclass.app14_0911;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.*;

public class FormApp extends JFrame implements ActionListener{

    private JMenuBar mainMenu;
    private JTextArea textArea;

    private JScrollPane scrollPane;


    private File openedFile = null;
    private boolean textModified = false;

    public FormApp(){

        this.setTitle("Notepad");
        this.setSize(640,480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());



        /*
        mainmenu
         */
        JMenu menuFile = new JMenu("File");
        JMenuItem menuFileOpen = new JMenuItem("Open");
        menuFileOpen.setName("open");
        menuFileOpen.addActionListener(this);
        menuFile.add(menuFileOpen);
        JMenuItem menuFileSave = new JMenuItem("Save");
        menuFileSave.setName("save");
        menuFileSave.addActionListener(this);
        menuFile.add(menuFileSave);
        JMenuItem menuFileClose = new JMenuItem("Close");
        menuFileClose.setName("close");
        menuFileClose.addActionListener(this);
        menuFile.add(menuFileClose);
        JMenuItem menuFileExit = new JMenuItem("Exit");
        menuFileExit.setName("exit");
        menuFileExit.addActionListener(this);
        menuFile.add(menuFileExit);

        JMenu menuSettings = new JMenu("Settings");
        JCheckBoxMenuItem menuSettingsWordWrap = new JCheckBoxMenuItem("Word Wrap");
        menuSettingsWordWrap .setName("wordwrap");
        menuSettings.add(menuSettingsWordWrap);
        menuSettingsWordWrap.addActionListener(this);

        mainMenu = new JMenuBar();
        mainMenu.add(menuFile);
        mainMenu.add(menuSettings);
        add(mainMenu,BorderLayout.NORTH);

        /*
        text area & scrolls
         */
        textArea = new JTextArea();
        add(textArea,BorderLayout.CENTER);
        textArea.getDocument().addDocumentListener(new NotepadDocumentListener());

        scrollPane = new JScrollPane(textArea);
        add(scrollPane,BorderLayout.CENTER);


        /*

        */
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FormApp();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem source = (JMenuItem) e.getSource();
        switch (source.getName()){
            case "open":
                closePrevFile();
                openFile();
                break;
            case "save":
                saveText();
                break;
            case "close":
                closePrevFile();
                break;
            case "exit":
                closePrevFile();
                this.dispose();
                break;
            case "wordwrap":
                JCheckBoxMenuItem item = (JCheckBoxMenuItem) source;
                textArea.setLineWrap(item.getState());
                break;
        }

    }

    private void openFile(){
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            openedFile = chooser.getSelectedFile();
            FileReader reader = null;
            try {
                reader = new FileReader(openedFile);
                BufferedReader buffer = new BufferedReader(reader);
                String line = buffer.readLine();
                while (line != null) {
                    textArea.append(line + "\n");
                    line = buffer.readLine();
                }
                textModified = false;
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    private void closePrevFile() {
        if (textModified) {

            Object[] variants = new Object[] {"Yes","No","Cancel"};
            int variant = JOptionPane.showOptionDialog(this,"File changed. Do you want save file?",
                    "Question",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,
                    variants,variants[0]);

            if(variant == 0){
                saveText();
                clearAllInfo();
            }else if(variant ==1){
                clearAllInfo();
            }

        }

    }


    private void saveText(){
        if (openedFile != null) {
            saveTextToFile();
        } else {
            JFileChooser chooser = new JFileChooser();



            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                openedFile = chooser.getSelectedFile();
            }
            saveTextToFile();
        }
    }


    private void clearAllInfo(){
        openedFile = null;
        textArea.setText("");
        textModified = false;
    }


    private void saveTextToFile() {
        try {
            FileWriter fileWriter = new FileWriter(openedFile);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class NotepadDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            textModified = true;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            textModified = true;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            textModified = true;
        }
    }


    class NotepadFrameAdapter extends WindowAdapter{

        /*@Override
        public void
        */
    }


}
