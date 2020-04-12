package ec504Group3.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class GUI {
    private JPanel panel1;
    private JTextArea textField;
    private JRadioButton EnglishButton;
    private JFormattedTextField CommandInput;
    private JButton enterButton;
    private JTextArea Tittle;
    private JRadioButton ChineseButton;
    private JRadioButton FrenchButton;
    private boolean EnglishSelected, ChineseSelected, FrenchSelected = false;

    public GUI() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputCom = CommandInput.getText();
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
