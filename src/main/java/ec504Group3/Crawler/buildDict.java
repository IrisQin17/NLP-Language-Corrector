package ec504Group3.Crawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ec504Group3.Tokenizer.*;
import ec504Group3.Database.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class buildDict {
    public static MaxentTagger englishTagger = new MaxentTagger("external/taggers/models/english-bidirectional-distsim.tagger");
    public static MaxentTagger chineseTagger = new MaxentTagger("external/taggers/models/chinese-distsim.tagger");
    public static MaxentTagger frenchTagger = new MaxentTagger("external/taggers/models/french.tagger");
    public void build() throws Exception {
        String urlAddress = "src/main/java/ec504Group3/Resource/webFile/url-";
        int FileCount=0;
        while (true){
//            FileInputStream fin;
            List<List<TokenType>> tokens;
            try{
                tokens = Tokenizer.getTokens(englishTagger,urlAddress+FileCount+".txt");
            }catch (IOException e){
                break;
            }
//            StringBuilder text = new StringBuilder();
//            InputStreamReader reader = new InputStreamReader(fin);
//            BufferedReader buffReader = new BufferedReader(reader);
//            String strTmp = "";
//            while((strTmp = buffReader.readLine())!=null){
//                text.append(strTmp);
//            }
//            buffReader.close();

            for (List<TokenType> token : tokens) {
                for (int i=0;i<token.size()-1;i++) {
//                    System.out.println("the tag is " + value.pos + " the token is " + value.word);
//                    System.out.println(token.get(i).pos+"----"+token.get(i+1).pos+"---");
                    Database.getDatabase().EdgePutin(token.get(i).pos,token.get(i+1).pos);
                }
            }
            FileCount++;
        }
        List<Edge> edgeList = Database.getDatabase().getALLEdges();
        for (Edge e:edgeList){
            System.out.println(e.getKey()+" "+e.getFrequency()+" in "+FileCount);
        }


    }
}
