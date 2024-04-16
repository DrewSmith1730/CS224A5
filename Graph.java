// jdh CS224 Spring 2023

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node node) {
        // make sure this node does not already exist
        for (Node n: this.nodes) {
            if (n == node) {
                System.out.println("ERROR: node " + n + " is already in graph");
                return;
            }
        }
        this.nodes.add(node);
    } // addNode()

    public void addEdge(Node n1, Node n2, int weight) {
        // outgoing edge
        int idx1 = this.nodes.indexOf(n1);
        if (idx1 < 0) {
            System.out.println("ERROR: node " + n1.name + " not found in graph");
            return;
        }

        int idx2 = this.nodes.indexOf(n2);
        if (idx2 < 0) {
            System.out.println("ERROR: node " + n2.name + " not found in graph");
            return;
        }

        n1.addEdge(n2, weight);
    } // addEdge()

    public void print() {
        for (Node n: this.nodes) {
            System.out.print("Node " + n.name + " out:");
            for (Edge e: n.adjlistOut)
                System.out.print(" " + e);
            System.out.println();
            System.out.print("Node " + n.name + " in:");
            for (Edge e: n.adjlistIn)
                System.out.print(" " + e);
            System.out.println();
        }
    } // print()

    //----------------------------------------------------------------

    public Object[] bellmanFord(Node t) {
        // implement this
        // create M size n (to store shortest paths for each node
        int M[] = new int[nodes.size() + 1]; // plus 2 so the node number and its array index match
        // create first to store first node in shortest pasth
        int first[] = new int[nodes.size() + 1];
        // first init
        for(int i = 0; i < first.length; i++){
            first[i] = 0;
        }
        // init values in M
        for(int i = 0; i < M.length; i++){
            if (i == t.name){
                M[i] = 0; // sets the goal node to a value of zero
            } else {
                M[i] = Integer.MAX_VALUE / 2;
            }
        }
        Node curr;
        int Mnew[];
        Edge v;
        boolean eq = false;
        // loop for n - 1 times
        while(!eq){ // should be a conditional while based on if any values in M are changed
            // init Mnew to M
            Mnew = M.clone(); // clone so the arrays dont point to the same place in memory
            // for each node
            for(int i = 0; i < nodes.size(); i++){
                curr = nodes.get(i);
                // for each node adjacent to u (v in the outward adjacency list)
                for(int j = 0; j < curr.adjlistOut.size(); j++){
                    v = curr.adjlistOut.get(j);
                    if(v.weight + M[v.n2.name] < M[curr.name]){
                        Mnew[curr.name] = v.weight + M[v.n2.name];
                        first[curr.name] = v.n2.name;
                    }
                }
            }
            // when updating Mnew to M check if their identical and then change the bool if nothing changes
            eq = Arrays.equals(M, Mnew);
            M = Mnew.clone();
        }

        // return both arrays at the end
        Object[] returnVal = new Object[2];
        returnVal[0] = M;
        returnVal[1] = first;
        return returnVal;
    } // bellmanFord()

    //----------------------------------------------------------------

    public Object[] bellmanFordPush(Node t) {
        // implement this
        boolean changed[] = new boolean[nodes.size()+1]; // make if the node was changed in the previous run
        for(int i = 0; i < changed.length; i++){
            changed[i] = false;
        } // init all to false
        // create M size n (to store shortest paths for each node
        int M[] = new int[nodes.size() + 1]; // plus 2 so the node number and its array index match
        // create first to store first node in shortest pasth
        int first[] = new int[nodes.size() + 1];
        // first init
        for(int i = 0; i < first.length; i++){
            first[i] = 0;
        }
        // init values in M
        for(int i = 0; i < M.length; i++){
            if (i == t.name){
                M[i] = 0; // sets the goal node to a value of zero
            } else {
                M[i] = Integer.MAX_VALUE / 2;
            }
        }
        Node curr;
        int Mnew[];
        Edge v;
        boolean eq = false;
        // loop for n - 1 times
        while(!eq){ // should be a conditional while based on if any values in M are changed
            // init Mnew to M
            Mnew = M.clone(); // clone so the arrays dont point to the same place in memory

            // for each node
            for(int i = 0; i < nodes.size(); i++){
                curr = nodes.get(i);
                // for each node adjacent to u (v in the outward adjacency list)
                // only check for a path if the M[u] has been changed
                if(i == 0 || changed[i] == true) {
                    changed[i] = false;
                    for (int j = 0; j < curr.adjlistOut.size(); j++) {
                        v = curr.adjlistOut.get(j);
                        if (v.weight + M[v.n2.name] < M[curr.name]) {
                            Mnew[curr.name] = v.weight + M[v.n2.name];
                            first[curr.name] = v.n2.name;
                            changed[curr.name] = true;
                        }
                    }
                }
            }
            // when updating Mnew to M check if their identical and then change the bool if nothing changes
            eq = Arrays.equals(M, Mnew);
            M = Mnew.clone();
        }

        // return both arrays at the end
        Object[] returnVal = new Object[2];
        returnVal[0] = M;
        returnVal[1] = first;
        return returnVal;
    } // bellmanFord()

} // class Graph
