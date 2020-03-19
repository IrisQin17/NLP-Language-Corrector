package ec504Group3.Crawler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLListCreater {
    public void create(URL url){
        HashSet<String> urlSet = new HashSet<String>();
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);
        try {
            urlconn = url.openConnection();
            pw = new PrintWriter(new FileWriter("src/ec504Group3/Resource/URL-list"), true);
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    String tmp = buf_m.group();
                    System.out.println(tmp);
                    URL uu = new URL(tmp);
                    int flag=0;
                    try{
                        uu.openStream();
                        System.out.println("~~~~");
                    }catch (Exception e){
                        System.out.println("++++");
                        flag=1;
                    }
                    if (flag==1) break;
                    urlSet.add(buf_m.group());

                }
            }
            for(String s:urlSet){
                pw.println(s);
            }
            System.out.println("finish and success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }

}