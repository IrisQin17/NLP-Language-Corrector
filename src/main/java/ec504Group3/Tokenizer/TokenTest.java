package ec504Group3.Tokenizer;

import edu.stanford.nlp.util.logging.Redwood;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class TokenTest  {

    /** A logger for this class */
    private static Redwood.RedwoodChannels log = Redwood.channels(TokenTest.class);

    private TokenTest() {}

    public static void main(String[] args) throws Exception {
        String filePath = "external/taggers/input/sample-input.txt";
        String text ="";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
            text += strTmp;
        }
        buffReader.close();
        List<List<Token>> tokens = Tokenizer.getTokens(text);
        for(int i =0; i< tokens.size(); i++){
            for(int j = 0; j < tokens.get(i).size();j++){
                System.out.println("the tag is "+tokens.get(i).get(j).tag+" the token is "+tokens.get(i).get(j).token);
            }
        }
//        MaxentTagger tagger = new MaxentTagger("external/taggers/models/english-bidirectional-distsim.tagger");
//        List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader("external/taggers/input/sample-input.txt")));
//
//
//        for (List<HasWord> sentence : sentences) {
//            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
//            System.out.println(SentenceUtils.listToString(tSentence, false));
//        }
    }
}