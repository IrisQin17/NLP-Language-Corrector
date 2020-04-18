package ec504Group3;

import ec504Group3.GUI.*;
import ec504Group3.Crawler.URLListCreater;

import java.net.URL;

public class test {

    public static void main (String[] args) throws Exception {
        System.out.println("start");
        GUI gui = new GUI();
        gui.run();
//        URLListCreater uc = new URLListCreater();
//        URL url = new URL("https://www.lemonde.fr/");    // french web
//        uc.create(url);
    }
}
