import java.util.HashSet;

public class Graph {

    // Properties
    private Node startNode;
    private Node endNode;
    private HashSet<Node> nodes; // Does not contain start and end nodes, only intermediate nodes

    // Constructor
    public Graph() {
        this.startNode = null;
        this.endNode = null;
        this.nodes = new HashSet<>();
    }

    // Getters Setters
    public Node getStartNode() {
        return startNode;
    }
    public void setStartNode(Node startNode) {
        startNode.setName("[");
        this.startNode = startNode;
    }
    public Node getEndNode() {
        return endNode;
    }
    public void setEndNode(Node endNode) {
        endNode.setName("]");
        this.endNode = endNode;
    }
    public HashSet<Node> getNodes() {
        return nodes;
    }
    public void setNodes(HashSet<Node> nodes) {
        this.nodes = nodes;
    }

    // Methods
    public void addNode(Node newNode) {
        this.nodes.add(newNode);
    } // To add a node to the graph
    public Node getNodeWithName(String name) {
        if(name.equals("[")) {
            return this.startNode;
        } else if(name.equals("]")) {
            return this.endNode;
        } else {
            for (Node newNode : this.getNodes()) {
                if (newNode.getName().equals(name)) {
                    return newNode;
                }
            }
            return null;
        }
    } // To get the node with it's name
}
