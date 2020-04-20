package ec504Group3.Database;

import com.arangodb.*;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.model.EdgeCreateOptions;
import com.arangodb.model.EdgeUpdateOptions;
import com.arangodb.model.VertexCreateOptions;
import com.arangodb.model.VertexUpdateOptions;
import com.arangodb.util.MapBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class spanishDatabase {

    private static spanishDatabase database;
    private static ArangoDatabase db;
    public static ArangoVertexCollection nodes;
    public static ArangoEdgeCollection edges;
    private static final String DATABASE = "_system";
    private static final String GRAPH = "spanish";
    private static final String NODES = "words";
    private static final String EDGES = "freqs";
    private spanishDatabase() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.OFF);
        db = new ArangoDB.Builder().host("127.0.0.1",8529).user("root").password("").build().db(DATABASE);
        ArangoGraph graph = db.graph(GRAPH);
        nodes = graph.vertexCollection(NODES);
        edges = graph.edgeCollection(EDGES);
    }

    public static spanishDatabase getDatabase() {
        if (database == null) {
            database = new spanishDatabase();
            System.out.println("-----------------Spanish Create----------------");
        }
        return database;
    }

    public List<Edge> getNodeEdges(String pos) {
        Map<String, Object> bindVars = new MapBuilder().get();
        String query;
        query = "FOR e IN " + EDGES + " FILTER e._from == \"words/"  + pos + "\" SORT e.frequency DESC, e._from, e._to RETURN e";
        ArangoCursor cursor;
        cursor = db.query(query, bindVars, null, BaseDocument.class);
        List<BaseDocument> docs = new ArrayList<>();
        while (cursor.hasNext()) {
            docs.add((BaseDocument) cursor.next());
        }

        List<Edge> allEdges = new ArrayList<>();
        for (BaseDocument document : docs) {
            String nodeChild;
            String[] childArr = document.getKey().split("!");
            nodeChild = childArr[1];
            allEdges.add(new Edge(getNode(pos), getNode(nodeChild), (Long) document.getAttribute("frequency")));
        }
        return allEdges;
    }

    public List<Edge> getALLEdges(){
        Map<String, Object> bindVars = new MapBuilder().get();
        String query;
        query = "FOR e IN " + EDGES + " FILTER e._from != \"words/"  + "FORALL" + "\" SORT e.frequency DESC, e._from, e._to RETURN e";
        ArangoCursor cursor;
        cursor = db.query(query, bindVars, null, BaseDocument.class);
        List<BaseDocument> docs = new ArrayList<>();
        while (cursor.hasNext()) {
            docs.add((BaseDocument) cursor.next());
        }
        List<Edge> allEdges = new ArrayList<>();
        for (BaseDocument document : docs) {
            String nodeChild;
            String nodeParent;
            String[] childArr = document.getKey().split("!");

            nodeParent = childArr[0];
            nodeChild = childArr[1];

//            Node parentNode = new Node(nodeParent,);
//            Node newNode = new Node(nodeChild);

            allEdges.add(new Edge(getNode(nodeParent), getNode(nodeChild), (Long) document.getAttribute("frequency")));
        }
        return allEdges;

    }

    public Node getNode(String pos) {
        BaseDocument node = nodes.getVertex(pos, BaseDocument.class);
        if (node == null) return null;
        return new Node((String) node.getAttribute("pos"), (Long) node.getAttribute("nodeNum"));
    }

    private boolean insertNode(String pos, long nodeNum) {
        try {
            nodes.insertVertex(new Node(pos, nodeNum).getDocument(), new VertexCreateOptions());
            return true;
        } catch (ArangoDBException err) {
            return false;
        }
    }

    public boolean NodePutin(String pos) {
        Node node = getNode(pos);
        if (node == null) {
            return insertNode(pos, 1);

        }
        return true;
    }

    public Edge getEdge(Node from, Node to) {
        if(edges.getEdge(from.getPos() + "!" + to.getPos(), BaseEdgeDocument.class) == null){
            return null;
        }
        BaseEdgeDocument edge = edges.getEdge(from.getPos() + "!" + to.getPos(), BaseEdgeDocument.class);
        return new Edge(from, to, (Long) edge.getAttribute("frequency"));
    }

    private boolean insertEdge(Node from, Node to) {
        try {
            edges.insertEdge(new Edge(from, to, 1).getDocument(), new EdgeCreateOptions());
            return true;
        } catch (ArangoDBException err) {
            return false;
        }
    }


    private boolean updateEdge(Edge edge) {
        try {
            edge.incrementFrequency();
            edges.updateEdge(edge.getKey(), edge.getDocument(), new EdgeUpdateOptions());
            return true;
        } catch (ArangoDBException err) {
            return false;
        }
    }

//    private Node guaranteedEtherealNode(String pos) {
//        Node node = getNode(pos);
//        if (node == null) {
//            insertNode(pos);
//            node = getNode(pos);
//        }
//        return node;
//    }

    private boolean incrementNode(Node node) {
        try {
            node.nodeNumPlus();
            nodes.updateVertex(node.getPos(), node, new VertexUpdateOptions());
            return true;
        } catch (ArangoDBException err) {
            return false;
        }
    }

    public void EdgePutin(String fromPos, String toPos) {
        Node fromNode = getNode(fromPos);
        Node toNode = getNode(toPos);
        if (fromNode==null){
            insertNode(fromPos,0);
            fromNode = getNode(fromPos);
        }
        if (toNode==null){
            insertNode(toPos,0);
            toNode = getNode(toPos);
        }
        Edge edge = getEdge(fromNode, toNode);
        if (edge == null) {
            insertEdge(fromNode, toNode);
            return;
        }
        if (incrementNode(fromNode)) {
            updateEdge(edge);
        }
    }

}
