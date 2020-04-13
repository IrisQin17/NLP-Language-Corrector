package ec504Group3;

import ec504Group3.Checker.ScoreChecker;
import ec504Group3.Crawler.*;
import ec504Group3.GUI.*;
import java.io.*;
import java.net.URL;

public class test {

    // global variables
    static String urlListPath, textPath ;
    static boolean correctFormat;



    public static void main (String[] args) throws Exception {
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

            count++;
        }

        //close
        inputStream.close();
        bufferedReader.close();
        buildDict db = new buildDict();
        db.build();
        System.out.println(sc.check("src/main/java/ec504Group3/Resource/CheckFile/check-1cx.txt"));

        System.out.println("--finish--");
        System.exit(0);
    }
}
