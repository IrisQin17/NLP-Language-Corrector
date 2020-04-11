package ec504Group3;


import ec504Group3.Checker.ScoreChecker;
import ec504Group3.Crawler.URL2File;
import ec504Group3.Crawler.URLListCreater;

import java.io.*;
import java.net.URL;

public class test {
    public static void main (String[] args) throws IOException {
        URLListCreater creater = new URLListCreater();
        creater.create(new URL("https://www.rndsystems.com/cn"));
        String urlAddress = "src/main/java/ec504Group3/Resource/URL-list";
        FileInputStream inputStream = new FileInputStream(urlAddress);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        URL2File uf = new URL2File();
        ScoreChecker sc = new ScoreChecker();
        int count=0;
        while((str = bufferedReader.readLine()) != null)
        {
            try{
                uf.StoreFile(str,count);
            }catch (Exception e){
                continue;
            }
            System.out.println(sc.check("src/main/java/ec504Group3/Resource/webFile/url-"+count+".txt"));
            count++;
        }
        //close
        inputStream.close();
        bufferedReader.close();


    }
}
