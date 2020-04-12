package ec504Group3.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.accessibility.AccessibleContext;
import javax.swing.*;

public class frame extends JFrame {
    public static void main(String[] args) {
        JTextField jt1 = new JTextField();
        JTextArea jt2 = new JTextArea();
        JScrollPane jsp = new JScrollPane(jt2);
        JFrame jf = new JFrame("ECE504 Group3 Language Correction");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = jf.getContentPane();
        jf.setVisible(true);
        jf.setSize(1300, 800);
        jf.setLayout(new BorderLayout());
        jf.add(BorderLayout.NORTH, jt1);
        jt1.setBackground(Color.yellow);
        jf.add(BorderLayout.CENTER, jt2);
        jt2.setBackground(Color.white);
    }
}
