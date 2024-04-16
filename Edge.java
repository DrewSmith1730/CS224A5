// jdh CS224 Spring 2023

public class Edge {
    Node n1;
    Node n2;
    int weight;

    public Edge(Node n1, Node n2, int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        String s = "(" + n1 + ", " + n2 + ") w=" + weight;
        return s;
    }
} // class Edge
