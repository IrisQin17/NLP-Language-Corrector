package ec504Group3.Checker;

import ec504Group3.Database.*;
import ec504Group3.Tokenizer.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScoreChecker{
    public static MaxentTagger englishTagger = new MaxentTagger("external/taggers/models/english-bidirectional-distsim.tagger");
    public static MaxentTagger germanTagger = new MaxentTagger("external/taggers/models/german-fast.tagger");
    public static MaxentTagger spanishTagger = new MaxentTagger("external/taggers/models/spanish.tagger");
    public static MaxentTagger chineseTagger = new MaxentTagger("external/taggers/models/chinese-distsim.tagger");
    public static MaxentTagger frenchTagger = new MaxentTagger("external/taggers/models/french.tagger");

    public int check(String checkFile) throws Exception {
        int score  = 0;
        List<List<TokenType>> tokens;
        try{
            tokens = Tokenizer.getTokens(englishTagger,checkFile);
        }catch (IOException e){
            return -1;
        }
        float maintain = 0;
        for (List<TokenType> token : tokens) {
            for (int i=0;i<token.size()-1;i++) {
                Node from = Database.getDatabase().getNode(token.get(i).pos);
                Node to = Database.getDatabase().getNode(token.get(i+1).pos);
                Edge e = Database.getDatabase().getEdge(from,to);
                long edge_freq = e.getFrequency();
                long node_freq = from.getNodeNum();
                float ratio = (float)edge_freq / (float)node_freq;
                System.out.println(edge_freq+" chuyi "+node_freq);
                if (ratio<0.05){
                    maintain = maintain==0?ratio:maintain*ratio;
                    System.out.println(from.getPos()+" to "+to.getPos()+" : "+ (int)(100 *(1-ratio)));
                }else if (ratio>0.6){
                    System.out.println(from.getPos()+" to "+to.getPos()+" : "+ 0);
                }

            }
        }
        score = (int) (100 *(1-maintain));
        return score;
    }
}
