package ec504Group3.GUI;

import ec504Group3.Checker.ScoreChecker;
import ec504Group3.Crawler.URL2File;
import ec504Group3.Crawler.buildDict;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI {
    private JLabel Title, url_label, test_label;
    private JPanel panel1;
    private JTextArea textField;
    private JRadioButton EnglishButton;
    private JFormattedTextField url_Input;
    private JButton enterButton;
    private JRadioButton ChineseButton;
    private JRadioButton FrenchButton;
    private JButton resetButton;
    private JFormattedTextField test_Input;
    private JRadioButton GermanButton;
    private JRadioButton SpanishButton;
    private ButtonGroup LanguageSelect;
    private MaxentTagger languageTagger;
    private String LanguageMode;
    public static MaxentTagger englishTagger = new MaxentTagger("external/taggers/models/english-bidirectional-distsim.tagger");
    public static MaxentTagger germanTagger = new MaxentTagger("external/taggers/models/german-fast.tagger");
    public static MaxentTagger spanishTagger = new MaxentTagger("external/taggers/models/spanish.tagger");
    public static MaxentTagger chineseTagger = new MaxentTagger("external/taggers/models/chinese-distsim.tagger");
    public static MaxentTagger frenchTagger = new MaxentTagger("external/taggers/models/french.tagger");


    public GUI(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to the selected language
                if (LanguageSelect.getSelection().equals(EnglishButton.getModel())){
                    languageTagger = englishTagger;
                    LanguageMode = "english";
                }
                else if (LanguageSelect.getSelection().equals(ChineseButton.getModel()))
                {
                    languageTagger = chineseTagger;
                    LanguageMode = "chinese";
                }
                else if (LanguageSelect.getSelection().equals(FrenchButton.getModel()))
                {
                    languageTagger = frenchTagger;
                    LanguageMode = "french";
                }
                else if (LanguageSelect.getSelection().equals(SpanishButton.getModel()))
                {
                    languageTagger = spanishTagger;
                    LanguageMode = "spanish";
                }
                else
                {
                    languageTagger = germanTagger;
                    LanguageMode = "german";
                }


                textField.setText("Processing...");
                if(url_Input.getText() == null || url_Input.getText().equals("") || test_Input.getText() == null || test_Input.getText().equals("") ){
                    textField.setText("Please fill out both inputs in correct format!");
                    return;
                }

                textField.setText(textField.getText() + "\nCrawling...");

                String urlAddress = url_Input.getText();

                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(urlAddress);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    textField.setText("Please fill out both inputs in correct format!");
                }
                assert inputStream != null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String str = null;
                URL2File uf = new URL2File();
                ScoreChecker sc = new ScoreChecker();
                int count=0;
                while(true)
                {
                    try {
                        if ((str = bufferedReader.readLine()) == null) break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        textField.setText("Please fill out both inputs in correct format!");
                    }
                    try{
                        uf.StoreFile(str,count,LanguageMode);
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
                    textField.setText("Please fill out both inputs in correct format!");
                }
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    textField.setText("Please fill out both inputs in correct format!");
                }



                textField.setText(textField.getText() + "\nChecking...");

                buildDict db = new buildDict();
                try {
                    db.build(languageTagger,LanguageMode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textField.setText("Please fill out both inputs in correct format!");
                }
                try {

                    // main output
                    textField.setText(textField.getText() +"\n" + (sc.check(test_Input.getText(), languageTagger)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textField.setText("Please fill out both inputs in correct format!");
                }


                textField.setText(textField.getText() + "\n--finish--");
                System.out.println("--finish--");
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
                textField.setText(
                        "Waiting for inputs in the correct format, e.g.:\n" +
                        "\n" +
                        "Input PATH OF FILE OF URLs like this:\n" +
                        "/PATH/TO/URL-list\n" +
                        "\n" +
                        "Input PATH OF FILE TO CHECK like this:\n" +
                        "/PATH/TO/check-0.txt");
            }
        });

    }

    public void run() {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(400, 200, 600, 400);
        frame.setVisible(true);
    }
}
