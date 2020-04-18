package ec504Group3.Checker;

import ec504Group3.Database.*;
import ec504Group3.Tokenizer.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;
import java.util.List;

public class ScoreChecker{
    public String check(String checkFile, MaxentTagger languageTagger,String mode) throws Exception {
        StringBuilder res = new StringBuilder();

        int score  = 0;
        List<List<TokenType>> tokens;
        try{
            tokens = Tokenizer.getTokens(languageTagger,checkFile);
        }catch (IOException e){
            return null;
        }
        float maintain = 0;
        for (List<TokenType> token : tokens) {
            int tmp_score=0;
            StringBuilder sentence = new StringBuilder();
            for (int i=0;i<token.size()-1;i++) {
                sentence.append(token.get(i).word).append(" ");
                Node from,to;
                Edge e;
                switch (mode){
                    case "chinese":
                        from = chineseDatabase.getDatabase().getNode(token.get(i).pos);
                        to = chineseDatabase.getDatabase().getNode(token.get(i+1).pos);
                        e = chineseDatabase.getDatabase().getEdge(from,to);
                        break;
                    case "french":
                        from = frenchDatabase.getDatabase().getNode(token.get(i).pos);
                        to = frenchDatabase.getDatabase().getNode(token.get(i+1).pos);
                        e = frenchDatabase.getDatabase().getEdge(from,to);
                        break;
                    default:
                        from = englishDatabase.getDatabase().getNode(token.get(i).pos);
                        to = englishDatabase.getDatabase().getNode(token.get(i+1).pos);
                        e = englishDatabase.getDatabase().getEdge(from,to);
                        break;
                }
                float ratio;
                long edge_freq;
                edge_freq = e==null?0:e.getFrequency();
                long node_freq = from.getNodeNum();
                ratio = (float)edge_freq / (float)node_freq;
                if (ratio<0.01){
                    tmp_score = tmp_score>=100?tmp_score:tmp_score+10;
                    res.append(from.getPos()).append(" to ").append(to.getPos()).append(" : ").append((int) (100 * (1 - ratio))).append("\n");
                    System.out.println(from.getPos()+" to "+to.getPos()+" : "+ (int)(100 *(1-ratio)));
                }else if (ratio>0.6){
                    res.append(from.getPos()).append(" to ").append(to.getPos()).append(" : ").append(0).append("\n");
                    System.out.println(from.getPos()+" to "+to.getPos()+" : "+ 0);
                }
            }
            if (token.size()>0) {
                sentence.append(token.get(token.size() - 1).word).append(" ");
                res.append("{").append(sentence.toString()).append("} score: ").append(tmp_score).append("\n");
                System.out.println("{" + sentence.toString() + "} score: " + tmp_score);
            }
        }
        return res.toString();
    }
}
