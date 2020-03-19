package ec504Group3.Crawler;

import java.io.File;

public class URL2File implements crawler{
    public static void main (String[] args){
        String urlAddress = "src/ec504Group3/Resource/URL-list";
        File urls = new File(urlAddress);
        System.out.println(urls.exists());
    }
}
