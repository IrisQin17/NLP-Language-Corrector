import edu.stanford.nlp.util.logging.Redwood;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

//https://interviewbubble.com/stanford-pos-tagger-tutorial-stanfords-part-of-speech-label-demo/
public class Tokenizer {

  /** A logger for this class */
  private static Redwood.RedwoodChannels log = Redwood.channels(Tokenizer.class);

  /**  Tokenizer  */
  public static MaxentTagger englishTagger = new MaxentTagger("src/taggers/models/english-bidirectional-distsim.tagger");
  public static MaxentTagger chineseTagger = new MaxentTagger("src/taggers/models/chinese-distsim.tagger");
  public static MaxentTagger frenchTagger = new MaxentTagger("src/taggers/models/french.tagger");

  /**  getTokens()
   * @input the right language tagger, input text file path
   * the text is separated by sentence, each word in a sentence convert to TokenType
   **/
  public static List<List<TokenType>> getTokens(MaxentTagger tagger, String inputFilePath) throws Exception{
    List<List<TokenType>> res = new LinkedList<>();
    List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(inputFilePath)));
    for (List<HasWord> sentence : sentences) {
      List<TokenType> tSentence = new LinkedList<>();
      for (HasWord w: sentence) {
        // get rid of punctuation
        if (Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", w.word()))
          continue;
        String[] underscoreSplit = tagger.tagTokenizedString(w.word()).split("_");
        tSentence.add(new TokenType(underscoreSplit[1], underscoreSplit[0]));
      }
      res.add(tSentence);
    }
    return res;
  }






  // example of use
  public static void main (String[] args) throws Exception {
    List<List<TokenType>> lists = getTokens(englishTagger,"src/taggers/input/english-input.txt");

    // print out for test
    for (List<TokenType> l : lists) {
      for (TokenType t : l)
        System.out.print(t.word + ": " + t.pos + " ");
      System.out.println();
    }
  }
}
