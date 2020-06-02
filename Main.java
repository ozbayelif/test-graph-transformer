
public class Main {

    public static void main(String[] args) {

        /*Node start = new Node();
        Node end = new Node();
        Node c1 = new Node();
        Node p1 = new Node();
        Node x1 = new Node();
        Node p2 = new Node();

        start.addNextNode(c1);
        start.addNextNode(x1);

        c1.addElement(c1);
        p1.addElement(p1);
        x1.addElement(x1);
        p2.addElement(p2);

        c1.addNextNode(c1);
        c1.addNextNode(p1);
        c1.addNextNode(x1);

        x1.addNextNode(x1);
        x1.addNextNode(c1);
        x1.addNextNode(p2);

        p1.addNextNode(p1);
        p1.addNextNode(c1);
        p1.addNextNode(x1);
        p1.addNextNode(end);

        p2.addNextNode(c1);
        p2.addNextNode(x1);
        p2.addNextNode(end);

        c1.setName("c1");
        x1.setName("x1");
        p1.setName("p1");
        p2.setName("p2");

        Graph g1 = new Graph();
        g1.addNode(c1);
        g1.addNode(x1);
        g1.addNode(p1);
        g1.addNode(p2);
        g1.setStartNode(start);
        g1.setEndNode(end);

        Graph g2, g3;
        Transformer prod2 = new Transformer(g1, g1);
        g2 = prod2.transform();

        Transformer prod3 = new Transformer(g1, g2);
        g3 = prod3.transform();*/

        Transformer tr = new Transformer("input.txt", "input2.txt");
        Graph g2 = tr.transform();
    }
}
