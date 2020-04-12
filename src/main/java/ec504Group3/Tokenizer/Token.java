package ec504Group3.Tokenizer;

public class Token {
    public String tag;
    public String token;

    public Token(String pos, String word){
        this.tag = pos;
        this.token = word;
    }
}
