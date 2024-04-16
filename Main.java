public class Main {
    public static void main(String argv[]) {
        testOne();
        //testTwo();
    }

    // this is Figure 6.23
    public static void testOne() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        Node destNode = n6;

        Graph G = new Graph();
        G.addNode(n1);
        G.addNode(n2);
        G.addNode(n3);
        G.addNode(n4);
        G.addNode(n5);
        G.addNode(n6);

        G.addEdge(n1, n6, -3);
        G.addEdge(n1, n2, -4);
        G.addEdge(n2, n4, -1);
        G.addEdge(n2, n5, -2);
        G.addEdge(n3, n2, 8);
        G.addEdge(n3, n6, 3);
        G.addEdge(n4, n1, 6);
        G.addEdge(n4, n6, 4);
        G.addEdge(n5, n3, -3);
        G.addEdge(n5, n6, 2);

        G.print();

        System.out.println("Bellman-Ford");
        Object[] rtnval = G.bellmanFord(destNode);
        int[] M = (int[]) rtnval[0];
        int[] first = (int[]) rtnval[1];
        for (int i=1; i<M.length; ++i) {
            System.out.println("distance from N" + i + " to " + destNode + ": " + M[i] + "; first node is N" + first[i]);
        }

        System.out.println();
        System.out.println("Bellman-Ford Push");
        rtnval = G.bellmanFordPush(destNode);
        M = (int[]) rtnval[0];
        first = (int[]) rtnval[1];
        for (int i=1; i<M.length; ++i) {
            System.out.println("distance from N" + i + " to " + destNode + ": " + M[i] + "; first node is N" + first[i]);
        }
    } // testOne()

    //-------------------------------------------
    // another testcase
    public static void testTwo() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Node destNode = n5;

        Graph G = new Graph();
        G.addNode(n1);
        G.addNode(n2);
        G.addNode(n3);
        G.addNode(n4);
        G.addNode(n5);

        G.addEdge(n1, n2, 6);
        G.addEdge(n1, n5, 7);
        G.addEdge(n2, n3, 5);
        G.addEdge(n2, n4, -4);
        G.addEdge(n2, n5, 8);
        G.addEdge(n3, n2, -2);
        G.addEdge(n4, n3, 7);
        G.addEdge(n4, n1, 2);
        G.addEdge(n5, n3, -3);
        G.addEdge(n5, n4, 9);

        G.print();

        System.out.println("Bellman-Ford");
        Object[] rtnval = G.bellmanFord(destNode);
        int[] M = (int[]) rtnval[0];
        int[] first = (int[]) rtnval[1];
        for (int i=1; i<M.length; ++i) {
            System.out.println("distance from N" + i + " to " + destNode + ": " + M[i] + "; first node is N" + first[i]);
        }

//        System.out.println();
//        System.out.println("Bellman-Ford Push");
//        rtnval = G.bellmanFordPush(destNode);
//        M = (int[]) rtnval[0];
//        first = (int[]) rtnval[1];
//        for (int i=1; i<M.length; ++i) {
//            System.out.println("distance from N" + i + " to " + destNode + ": " + M[i] + "; first node is N" + first[i]);
//        }
    }
}
