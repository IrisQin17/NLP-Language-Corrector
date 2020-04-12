package ec504Group3.Crawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ec504Group3.Tokenizer.*;
import ec504Group3.Database.*;


public class buildDict {
    public void build() throws IOException {
        String urlAddress = "src/main/java/ec504Group3/Resource/webFile/url-";
        int FileCount=0;
        while (true){
            FileInputStream fin;
            try{
                fin = new FileInputStream(urlAddress+FileCount+".txt");
            }catch (IOException e){
                break;
            }
            StringBuilder text = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(fin);
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp = "";
            while((strTmp = buffReader.readLine())!=null){
                text.append(strTmp);
            }
            buffReader.close();
            List<List<Token>> tokens = Tokenizer.getTokens(text.toString());
            for (List<Token> token : tokens) {
                for (Token value : token) {
                    System.out.println("the tag is " + value.tag + " the token is " + value.token);
                }
            }
            FileCount++;
            break;
        }


    }
}
