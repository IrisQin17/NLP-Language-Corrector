package ec504Group3.Crawler;

import java.io.IOException;
import java.util.List;

import ec504Group3.Tokenizer.*;
import ec504Group3.Database.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class buildDict {
   public void build(MaxentTagger languageTagger,String mode) throws Exception {
        String urlAddress = "src/main/java/ec504Group3/Resource/"+mode+"File/url-";
        int FileCount=0;

        while (true){
//            FileInputStream fin;
            List<List<TokenType>> tokens;
            try{
                tokens = Tokenizer.getTokens(languageTagger,urlAddress+FileCount+".txt");
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
                    switch (mode){
                        case "german": germanDatabase.getDatabase().EdgePutin(token.get(i).pos,token.get(i+1).pos); break;
                        case "french": frenchDatabase.getDatabase().EdgePutin(token.get(i).pos,token.get(i+1).pos); break;
                        case "spanish": spanishDatabase.getDatabase().EdgePutin(token.get(i).pos,token.get(i+1).pos); break;
                        default: englishDatabase.getDatabase().EdgePutin(token.get(i).pos,token.get(i+1).pos);break;
                    }

                }
            }
            FileCount++;
        }
//        List<Edge> edgeList = Database.getDatabase().getALLEdges();
//        List<Edge> jjlist = Database.getDatabase().getNodeEdges("JJ");
//        int jjc=0;
//        for (Edge jj:jjlist){
//            jjc+=jj.getFrequency();
//        }
//        for (Edge e:edgeList){
//            System.out.println(e.getKey()+" "+e.getFrequency()+" in "+FileCount);
//        }
//        long compare = Database.getDatabase().getNode("JJ").getNodeNum();
//        System.out.println("jj"+jjc+" and "+compare);


    }
}
