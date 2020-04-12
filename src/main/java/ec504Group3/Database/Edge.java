package ec504Group3.Database;

import com.arangodb.entity.BaseEdgeDocument;


public class Edge {
    private Node from;
    private Node to;
    private long frequency;

    public Edge(Node from, Node to, long frequency) {
        this.from = from;
        this.to = to;
        this.frequency = frequency;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public long getFrequency() {
        return this.frequency;
    }

    public String getKey() {
        return this.from.getPos() + "_" + this.to.getPos();
    }

    public void incrementFrequency() {
        this.frequency++;
    }


    public BaseEdgeDocument getDocument() {
        final BaseEdgeDocument edge = new BaseEdgeDocument("words/" + this.from.getPos(), "words/" + this.to.getPos());
        edge.setKey(this.getKey());
        edge.addAttribute("frequency", this.frequency);
        return edge;
    }
}

