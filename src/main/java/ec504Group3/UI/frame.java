package ec504Group3.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class frame extends JFrame{
    private JFrame frame;
    private JTextArea jt;
    private JPanel jp1;
    private JPanel jp2;
    private Container c;
    private JButton read;
    private JButton write;
    private File file;
    /*
     * 此方法实例化文件对象，如果文件不存在，则新建文件
     */
    public void createFile(){
        file=new File("D:/2.txt");//实例化文件对象
        if (!file.exists()) {//判断文件是否存在
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /*
     * 此方法返回一个“读取文件”按钮
     */
    public JButton createReadButton(){
        JButton read=new JButton("读取文件");
        read.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    FileReader fr=new FileReader(file);//读取文件内容
                    BufferedReader br=new BufferedReader(fr);//新建缓存
                    String s=null;
                    while ((s=br.readLine())!=null) {
                        jt.append(s);//设置文本框内容
                        jt.append("\n");//增加换行符
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        return read;
    }
    /*
     * 此方法返回一个“写入文件”按钮
     */
    public JButton createWriteButton(){
        JButton write=new JButton("写入文件");
        write.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    FileWriter fw=new FileWriter(file);
                    fw.write(jt.getText());//将文本框内容全部写入文件
                    fw.close();
                    System.out.println("写入文件成功");//在控制台输出提示“文件写入成功”
                    jt.setText("");//清空文本框内容
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        return write;
    }
    private void go(){
        createFile();
        frame=new JFrame("test");
        c=frame.getContentPane();
        read=createReadButton();//调用方法返回“读取文件”按钮
        read.setSize(50, 30);
        write=createWriteButton();//调用方法返回“写入文件”按钮
        write.setSize(50,30);
        jt=new JTextArea(20, 40);//设置文本框行数
        jt.setLineWrap(true);// 激活自动换行功能
        jt.setWrapStyleWord(true);// 激活断行不断字功能
        jp1=new JPanel();
        jp1.add(jt);
        jp2=new JPanel();
        jp2.add(read);
        jp2.add(write);
        c.add(BorderLayout.NORTH,jp1);
        c.add(BorderLayout.SOUTH,jp2);
        frame.setVisible(true);
        frame.setSize(500,500);
    }
    public static void main(String[] args) {
        new frame().go();
    }
}
