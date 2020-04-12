package ec504Group3.Tokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    public static final File FILES_PATH = new File("external/taggers/models/");
    public static List<List<Token>> getTokens(String inputStr){

        //Regular expression
        inputStr = inputStr.replaceAll("([^a-zA-Z\\s\\.'])|(http:\\/\\/.*?(?=[\\s\"']|$))|(www\\..*?(?=[\\s\"']|$))", "");
        inputStr = inputStr.replaceAll("(\\.+)",".");

        String processedStr = getTags(inputStr);
        List<String> sentenceList = Arrays.asList(processedStr.split("\\._\\."));
        List<List<Token>> storeList = new ArrayList<>();

        for (String ss: sentenceList){

            String[] tempSS;

            // creating word_pos arrayList
            tempSS = ss.replaceAll("^\\s+", "").replaceAll("\\s+$", "").split("\\s");

            List<String> wordList = Arrays.asList(tempSS);
            List<Token> tokenList = new ArrayList<Token>();

            for(String word: wordList){
                if(word.isEmpty()) continue;

                // splits the word at every underscore
                String[] underscoreSplit = word.replaceAll("\\.", "").split("_");

                // creating tokens
                if (underscoreSplit.length > 1){
                    String posTag = underscoreSplit[1].toUpperCase();
                    String token = underscoreSplit[0].toLowerCase();
                    tokenList.add(new Token(posTag,token));
                }

            }
            storeList.add(tokenList);
        }
//        System.out.println(listOLists);

        return storeList;
    }

    private static String getTags(String content){
        MaxentTagger tagger = new MaxentTagger((new File(FILES_PATH, "english-bidirectional-distsim.tagger")).getAbsolutePath());
        String targetContent = tagger.tagString(content);
        return targetContent;
    }
}
