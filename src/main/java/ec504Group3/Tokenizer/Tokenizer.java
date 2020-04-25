package ec504Group3.Tokenizer;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

//https://interviewbubble.com/stanford-pos-tagger-tutorial-stanfords-part-of-speech-label-demo/
public class Tokenizer {

  /** A logger for this class */
  private static Redwood.RedwoodChannels log = Redwood.channels(Tokenizer.class);

  /**  getTokens()
   * @input the right language tagger, input text file path
   * the text is separated by sentence, each word in a sentence convert to TokenType
   **/
  public static List<List<TokenType>> getTokens(MaxentTagger tagger, String inputFilePath) throws Exception{
    List<List<TokenType>> res = new LinkedList<>();
    List<List<HasWord>> sentences = tagger.tokenizeText(new BufferedReader(new FileReader(inputFilePath)));
    for (List<HasWord> sentence : sentences) {
      List<TokenType> tSentence = new LinkedList<>();
      for (HasWord w: sentence) {
        // get rid of punctuation
        if (Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", w.word()))
          continue;
        String[] underscoreSplit = tagger.tagTokenizedString(w.word()).replaceAll("-", "").replaceAll("``", "ZT").split("[_ ]");

//        System.out.println(tagger.tagTokenizedString(w.word()));

        try {
          tSentence.add(new TokenType(underscoreSplit[1], underscoreSplit[0]));
        }
        catch(IndexOutOfBoundsException ignored) {
        }
      }
      res.add(tSentence);
    }
    return res;
  }


  public static MaxentTagger englishTagger = new MaxentTagger("external/taggers/models/english-bidirectional-distsim.tagger");

  // example of how to use
  public static void main (String[] args) throws Exception {

    List<List<TokenType>> lists = getTokens(englishTagger,"external/taggers/input/english-input.txt");

    // print out for test
    for (List<TokenType> l : lists) {
      for (TokenType t : l)
        System.out.print(t.word + ": " + t.pos + " ");
      System.out.println();
    }
  }
}
