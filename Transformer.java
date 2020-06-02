import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Transformer { // transformer

    // Properties
    private Graph G1; // 1-sequence coverage
    private Graph Gk; // k-sequence coverage

    // Constructor
    public Transformer(Graph G1, Graph Gk) {
        this.G1 = G1;
        this.Gk = Gk;
    }

    // Constructor from file
    public Transformer(String G1Path, String GkPath) {

        String[] paths = {G1Path, GkPath};
        Graph[] graphs = {new Graph(), new Graph()};
        Scanner sc = null;

        try {
            for(int i = 0; i < 2; i++) {
                File file = new File(paths[i]);
                sc = new Scanner(file);
                Graph graph = graphs[i];

                Node startNode = new Node();
                Node endNode = new Node();
                startNode.setName("[");
                endNode.setName("]");

                graph.setStartNode(startNode);
                graph.setEndNode(endNode);

                String[] nodeNames = null;
                String lastFirstNodeName = "";
                Node firstNode = null;
                Node secNode = null;

                while (sc.hasNext()) {
                    nodeNames = sc.nextLine().split(" ");

                    if (!lastFirstNodeName.equals(nodeNames[0])) { // To speed up by not searching again for redundant nodes
                        firstNode = graph.getNodeWithName(nodeNames[0]);
                        if (firstNode == null) {
                            firstNode = new Node();
                            firstNode.setName(nodeNames[0]);

                            if (i == 0) {
                                firstNode.addElement(firstNode);
                            } else {
                                for (String elementName : nodeNames[0].split(":")) {
                                    Node element = this.G1.getNodeWithName(elementName);
                                    firstNode.addElement(element);
                                }
                            }
                            graph.addNode(firstNode);
                        }
                    }

                    secNode = graph.getNodeWithName(nodeNames[1]);
                    if (secNode == null) {
                        secNode = new Node();
                        secNode.setName(nodeNames[1]);

                        if(i == 0) {
                            secNode.addElement(secNode);
                        } else {
                            for(String elementName : nodeNames[1].split(":")) {
                                Node element = this.G1.getNodeWithName(elementName);
                                secNode.addElement(element);
                            }
                        }
                        graph.addNode(secNode);
                    }
                    firstNode.addNextNode(secNode);
                }
                if(i == 0) {
                    this.G1 = graph;
                } else {
                    this.Gk = graph;
                }
                System.out.println("d");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(sc != null) {
                sc.close();
            }
        }
    }

    // Getters Setters
    public Graph getG1() {
        return G1;
    }
    public void setG1(Graph g1) {
        G1 = g1;
    }
    public Graph getGk() {
        return Gk;
    }
    public void setGk(Graph gk) {
        Gk = gk;
    }

    // Methods
    public Graph transform() {
        Graph GNew = new Graph();
        Node startNode = new Node();
        Node endNode = new Node();
        GNew.setStartNode(startNode);
        GNew.setEndNode(endNode);

        for (Node mid : this.Gk.getStartNode().getNextNodes()) {
            for (Node right : mid.getElements().get(mid.getElements().size() - 1).getNextNodes()) {
                if (right != this.G1.getEndNode()) {
                    Node newRight = new Node();

                    for (Node node : mid.getElements()) {
                        newRight.addElement(node);
                    }
                    newRight.addElement(right);

                    newRight.setName(mid.getName() + ":" + right.getName());

                    GNew.addNode(newRight);
                    startNode.addNextNode(newRight);
                }
            }
        }

        for (Node left : this.Gk.getNodes()) {
            for (Node mid : left.getNextNodes()) {
                if(mid != this.Gk.getEndNode()) {
                    Node newLeft = GNew.getNodeWithName(left.getElements().get(0).getName() + ":" + mid.getName());
                    if (newLeft == null) {
                        newLeft = new Node();

                        newLeft.addElement(left.getElements().get(0));
                        for (Node node : mid.getElements()) {
                            newLeft.addElement(node);
                        }

                        newLeft.setName(left.getElements().get(0).getName() + ":" + mid.getName());

                        GNew.addNode(newLeft);
                    }

                    for (Node right : mid.getElements().get(mid.getElements().size() - 1).getNextNodes()) {
                        if(right == this.G1.getEndNode()) {
                            newLeft.addNextNode(endNode);
                        } else {
                            Node newRight = GNew.getNodeWithName(mid.getName() + ":" + right.getName());
                            if (newRight == null) {
                                newRight = new Node();

                                for (Node node : mid.getElements()) {
                                    newRight.addElement(node);
                                }
                                newRight.addElement(right);
                                newRight.setName(mid.getName() + ":" + right.getName());

                                GNew.addNode(newRight);
                            }
                            newLeft.addNextNode(newRight);
                        }
                    }
                }
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter("output.txt");

            for(Node startNextNode : GNew.getStartNode().getNextNodes()) {
                fw.append("[ " + startNextNode.getName() + "\n");
            }
            for(Node baseNode : GNew.getNodes()) {
                for(Node nextNode : baseNode.getNextNodes()) {
                    fw.append(baseNode.getName() + " " + nextNode.getName() + "\n");
                }
            }

        } catch(Exception e) {
            System.out.println(e);
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return GNew;
    } // To produce the (k+1)-sequence coverage
}
