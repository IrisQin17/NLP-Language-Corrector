package ec504Group3.Database;

import com.arangodb.entity.BaseDocument;

public class Node {
    private String pos;

    public Node(String pos) {
        this.pos = pos;
    }

    public String getPos() { return this.pos; }

    public BaseDocument getDocument() {
        final BaseDocument node = new BaseDocument();
        node.setKey(this.pos);
        node.addAttribute("pos", this.pos);
        return node;
    }
}
