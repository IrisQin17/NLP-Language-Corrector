package ec504Group3.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class URL2File{
    public void StoreFile(String purpose,int count,String mode) throws IOException {

//        File urls = new File(urlAddress);
//        System.out.println(urls.exists());
//        System.out.println("---");
//        String purpose = "https://en.wikipedia.org/wiki/English_language";
        Document finaldocu = Jsoup.connect(purpose).get();
        //Get article title
        Elements h1_elements = finaldocu.select("h1");
        String title = h1_elements.text();
        //Fet article content
        Elements p_Elements = finaldocu.select("p");
        String Content = p_Elements.text();
        //Create TXT file
        String txtPath = "src/main/java/ec504Group3/Resource/"+mode+"File";
        File file = new File(txtPath+"/url-"+count+".txt");
        boolean res = file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        fileOutputStream.write(Content.getBytes());
        fileOutputStream.close();
    }
}
