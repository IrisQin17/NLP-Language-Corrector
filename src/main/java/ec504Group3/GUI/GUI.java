package ec504Group3.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private boolean EnglishSelected, ChineseSelected, FrenchSelected = false;
    public String crawlerInput = "";
    public String checkerInput;

    public GUI() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(url_Input.getText().equals(null) || url_Input.getText().equals("") ||test_Input.getText().equals(null) || test_Input.getText().equals("") ){
                    textField.setText("Please fill out both inputs!");
                    return;
                }

                String inputCom = url_Input.getText();
                crawlerInput = url_Input.getText();
                System.out.println(crawlerInput);
                textField.setText(inputCom);
                inputCom += "\n"+test_Input.getText();
                checkerInput = test_Input.getText();
                textField.setText(inputCom);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    // 解析
    private void commandAnalyzer(String inputStr){
        String[] list = inputStr.split(" ");
        for (String s : list){
            System.out.println(" " + s);
        }
        return;
    }

    public void run() {
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
