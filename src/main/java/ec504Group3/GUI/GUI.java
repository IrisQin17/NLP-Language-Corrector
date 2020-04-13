package ec504Group3.GUI;

import ec504Group3.Checker.ScoreChecker;
import ec504Group3.Crawler.URL2File;
import ec504Group3.Crawler.URLListCreater;
import ec504Group3.Crawler.buildDict;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;

public class GUI {
    private JPanel panel1;
    private JTextArea textField;
    private JRadioButton EnglishButton;
    private JFormattedTextField url_Input;
    private JButton enterButton;
    private JLabel Title;
    private JRadioButton ChineseButton;
    private JRadioButton FrenchButton;
    private JButton resetButton;
    private JFormattedTextField test_Input;
    private JLabel url_lable;
    private JLabel test_label;
    private JRadioButton GermanButton;
    private JRadioButton SpanishButton;
//    private boolean EnglishSelected, ChineseSelected, FrenchSelected = false;
//    public String crawlerInput = "";
//    public String checkerInput;

    public GUI(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(url_Input.getText().equals(null) || url_Input.getText().equals("") ||test_Input.getText().equals(null) || test_Input.getText().equals("") ){
                    textField.setText("Please fill out both inputs!");
                    return;
                }



                String urlAddress = url_Input.getText();

                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(urlAddress);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String str = null;
                URL2File uf = new URL2File();
                ScoreChecker sc = new ScoreChecker();
                int count=0;
                while(true)
                {
                    try {
                        if (!((str = bufferedReader.readLine()) != null)) break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try{
                        uf.StoreFile(str,count);
                    }catch (Exception ee){
                        continue;
                    }

                    count++;
                }

                //close
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                buildDict db = new buildDict();
                try {
                    db.build();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    System.out.println(sc.check(test_Input.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("--finish--");
                System.exit(0);

//
//                String inputCom = url_Input.getText();
//                crawlerInput = url_Input.getText();
//
//                System.out.println(crawlerInput);
//
//                textField.setText(inputCom);
//                inputCom += "\n"+test_Input.getText();
//                checkerInput = test_Input.getText();
//
//                textField.setText(inputCom);
            }
        });



        EnglishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EnglishButton.isSelected()){
                    textField.setText("English Selected");
                }
            }
        });
        ChineseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ChineseButton.isSelected()){
                    textField.setText("Chinese Selected");
                }
            }
        });
        FrenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FrenchButton.isSelected()){
                    textField.setText("French Selected");
                }
            }
        });

        GermanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GermanButton.isSelected()){
                    textField.setText("German Selected");
                }
            }
        });
        SpanishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SpanishButton.isSelected()){
                    textField.setText("Spanish Selected");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                url_Input.setText(null);
                test_Input.setText(null);
                textField.setText("\n" +
                        "Waiting for inputs in the correct format, e.g.: \n" +
                        "\n" +
                        "Input PATH OF FILE OF URLs like this:\n" +
                        "ec504Group3/Resource/URL-list\n" +
                        "\n" +
                        "Input PATH OF FILE TO CHECK like this:\n" +
                        "external/taggers/input/english-input.txt");
            }
        });

    }


    public static void main (String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(400, 200, 600, 400);
        frame.setVisible(true);
//        GUI myGUI = new GUI();
//        String test = "crawler www.google.com";
//        myGUI.commandAnalyzer(test);
    }
}
