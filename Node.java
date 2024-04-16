// jdh CS224 Spring 2023

import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class Node {
    List<Edge> adjlistOut;
    List<Edge> adjlistIn;
    int name;

    public Node(int name) {
        this.name = name;
        this.adjlistIn = new ArrayList<Edge>();
        this.adjlistOut = new ArrayList<Edge>();
    }

    public void addEdge(Node n2, int weight) {
        if (n2.name == this.name) {
            System.out.println("ERROR: cannot add an edge from " + this + " to " + this);
            return;
        }

        // make sure that this edge doesn't already exist
        for (Edge e: this.adjlistOut) {
            if (e.n2 == n2) {
                System.out.println("ERROR: there is already an edge from " + this.name + " to " + n2);
                return;
            }
        }

        // add the edge from this to edge.n2
        Edge outEdge = new Edge(this, n2, weight);
        this.adjlistOut.add(outEdge);

        // add the edge to adjlistIn of the other node
        Edge inEdge = new Edge(this, n2, weight);
        n2.adjlistIn.add(inEdge);
    } // addEdge()

    @Override
    public String toString() {
        String s = "N" + this.name;
        return s;
    }
} // class Node
