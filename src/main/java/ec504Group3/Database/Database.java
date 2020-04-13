package ec504Group3.Database;

import com.arangodb.*;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.CollectionPropertiesEntity;
import com.arangodb.model.EdgeCreateOptions;
import com.arangodb.model.EdgeUpdateOptions;
import com.arangodb.model.VertexCreateOptions;
import com.arangodb.model.VertexUpdateOptions;
import com.arangodb.util.MapBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jsoup.Connection;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {

    private static Database database;
    private static ArangoDatabase db;
    public static ArangoVertexCollection nodes;
    public static ArangoEdgeCollection edges;
    private static final String DATABASE = "_system";
    private static final String GRAPH = "allston";
    private static final String NODES = "words";
    private static final String EDGES = "freqs";


    private Database() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.OFF);
        db = new ArangoDB.Builder().host("127.0.0.1",8529).user("root").password("").build().db(DATABASE);
        ArangoGraph graph = db.graph(GRAPH);
        nodes = graph.vertexCollection(NODES);
        edges = graph.edgeCollection(EDGES);
    }

    public static Database getDatabase() {
        if (database == null) database = new Database();
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
        Node node = new Node(pos);
        List<Edge> allEdges = new ArrayList<>();
        for (BaseDocument document : docs) {
            String nodeChild;
            String[] childArr = document.getKey().split("\\_");
            nodeChild = childArr[1];
            Node newNode = new Node(nodeChild);
            allEdges.add(new Edge(node, newNode, (Long) document.getAttribute("frequency")));
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
            String[] childArr = document.getKey().split("\\_");

            nodeParent = childArr[0];
            nodeChild = childArr[1];

            Node parentNode = new Node(nodeParent);
            Node newNode = new Node(nodeChild);

            allEdges.add(new Edge(parentNode, newNode, (Long) document.getAttribute("frequency")));
        }
        return allEdges;

    }

    private Node getNode(String pos) {
        BaseDocument node = nodes.getVertex(pos, BaseDocument.class);
        if (node == null) return null;
        return new Node((String) node.getAttribute("pos"));
    }

    private boolean insertNode(String pos) {
        try {
            nodes.insertVertex(new Node(pos).getDocument(), new VertexCreateOptions());
            return true;
        } catch (ArangoDBException err) {
            return false;
        }
    }

    public boolean NodePutin(String pos) {
        Node node = getNode(pos);
        if (node == null) {
            return insertNode(pos);

        }
        return true;
    }

    private Edge getEdge(Node from, Node to) {
        if(edges.getEdge(from.getPos() + "_" + to.getPos(), BaseEdgeDocument.class) == null){
            return null;
        }
        BaseEdgeDocument edge = edges.getEdge(from.getPos() + "_" + to.getPos(), BaseEdgeDocument.class);
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

    public boolean EdgePutin(String fromPos, String toPos) {
        Node fromNode = getNode(fromPos);
        Node toNode = getNode(toPos);
        if (fromNode==null){
            insertNode(fromPos);
            fromNode = getNode(fromPos);
        }
        if (toNode==null){
            insertNode(toPos);
            toNode = getNode(toPos);
        }
        Edge edge = getEdge(fromNode, toNode);
        if (edge == null) {
            return insertEdge(fromNode, toNode);
        }
        return updateEdge(edge);
    }

}


