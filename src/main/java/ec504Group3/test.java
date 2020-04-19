package ec504Group3;

import ec504Group3.GUI.*;
import ec504Group3.Crawler.URLListCreater;

import java.net.URL;

public class test {

    public static void main (String[] args) throws Exception {
        GUI gui = new GUI();
        gui.run();
//        URLListCreater uc = new URLListCreater();
//        URL url = new URL("https://www.bild.de/news/inland/news-inland/coronavirus-bewegungsradar-als-interaktive-karte-wie-bewegt-sich-deutschland-69598262.bild.html");
//        uc.create(url);
    }
}
