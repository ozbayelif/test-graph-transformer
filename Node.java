import java.util.ArrayList;
import java.util.HashSet;

public class Node {

    // Properties
    private ArrayList<Node> elements; // Nodes are composed of nodes
    private HashSet<Node> nextNodes;
    private String name; // For easy debug

    // Constructor
    public Node() {
        this.elements = new ArrayList<Node>();
        this.nextNodes = new HashSet<Node>();
        this.name = "";
    }

    // Getters Setters
    public ArrayList<Node> getElements() {
        return this.elements;
    }
    public void setElements(ArrayList<Node> elements) {
        this.elements = elements;
    }
    public HashSet<Node> getNextNodes() {
        return this.nextNodes;
    }
    public void setNextNodes(HashSet<Node> nextNodes) {
        this.nextNodes = nextNodes;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void addElement(Node node) {
        this.elements.add(node);
    } // To expand the node with elements
    public void addNextNode(Node node) {
        this.nextNodes.add(node);
    } // To connect nodes with nextNodes

}
