package ec504Group3;


import ec504Group3.Checker.ScoreChecker;
import ec504Group3.Crawler.URL2File;
import java.io.*;

public class test {
    public static void main (String[] args) throws IOException {
        ScoreChecker sc = new ScoreChecker();
        String urlAddress = "src/ec504Group3/Resource/URL-list";
        FileInputStream inputStream = new FileInputStream(urlAddress);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            System.out.println(str);
        }
        //close
        inputStream.close();
        bufferedReader.close();
        URL2File uf = new URL2File();
        uf.StoreFile("https://en.wikipedia.org/wiki/English_language",0);
        System.out.println(sc.check("src/ec504Group3/Resource/webFile/url-0.txt"));
    }
}
