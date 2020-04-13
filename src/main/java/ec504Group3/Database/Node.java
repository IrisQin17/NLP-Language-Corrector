package ec504Group3.Database;

import com.arangodb.entity.BaseDocument;

public class Node {
    private String pos;
    private long nodeNum;

    public Node(String pos, long nodeNum) {
        this.pos = pos;
        this.nodeNum = nodeNum;
    }

    public String getPos() { return this.pos; }

    public long getNodeNum() { return this.nodeNum; }

    public void nodeNumPlus()  {
        this.nodeNum++;
    }

    public BaseDocument getDocument() {
        final BaseDocument node = new BaseDocument();
        node.setKey(this.pos);
        node.addAttribute("pos", this.pos);
        node.addAttribute("nodeNum", this.nodeNum);
        return node;
    }
}
