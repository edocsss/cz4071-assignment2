package algorithm;

import model.Edge;
import model.Graph;
import model.Node;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Joshua on 4/4/2017.
 */
public class Algo {

    // Connected Components --------------------------------------------------------------------------------------------

    private static ArrayList<Integer> root;

    private static int getRoot(int nodeId) {
        if (root.get(nodeId) == nodeId) return nodeId;
        root.set(nodeId, getRoot(root.get(nodeId)));
        return root.get(nodeId);
    }

    // Returns arraylist of each node's component label
    public static ArrayList<Integer> getConnectedComponents(Graph graph) {
        root = new ArrayList<>();
        for (int i = 0; i < Graph.N_NODES; i++) root.add(i);

        for (int i = 0; i < graph.getEdgeList().size(); i++) {
            Edge currentEdge = graph.getEdgeList().get(i);
            int aRoot = getRoot(currentEdge.getNodeA());
            int bRoot = getRoot(currentEdge.getNodeB());

            if (aRoot != bRoot) {
                root.set(aRoot, bRoot);
            }
        }
        return root;

    }

    // Returns the number of different connected components in the graph
    public static int getNoOfConnectedComponents(Graph graph) {
        getConnectedComponents(graph);

        HashMap<Integer, Integer> setId = new HashMap<>();
        HashMap<Integer, Integer> nodeLabels = new HashMap<>();
        int counter = 0;

        for (int i = 0; i < Graph.N_NODES; i++) {
            int currentSet = getRoot(i);
            if (!setId.containsKey(currentSet)) {
                setId.put(currentSet, counter);
                counter++;
            }

            nodeLabels.put(i, setId.get(currentSet));
        }

        return (new HashSet<Integer>(nodeLabels.values())).size();
    }

    // Single-Source Shortest Path -------------------------------------------------------------------------------------

    public static class DistComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.getDist(), o2.getDist());
        }
    }

    // Returns arraylist of each node's distance to source
    public static ArrayList<Integer> getShortestPath(Graph graph, int sourceId) {
        ArrayList<Integer> dist = new ArrayList<>();
        for (int i = 0; i < Graph.N_NODES; i++) dist.add(-1);

        PriorityQueue<Node> remaining = new PriorityQueue<Node>(100, new DistComparator());
        remaining.add(new Node(sourceId, 0));

        while (!remaining.isEmpty()) {
            Node current = remaining.poll();
            int currentNodeId = current.getId();
            int currentDist = current.getDist();

            if (dist.get(currentNodeId) != -1 && dist.get(currentNodeId) <= currentDist) continue;
            dist.set(currentNodeId, currentDist);

            for (int i = 0; i < graph.getAdjacencyList().get(currentNodeId).size(); ++i) {
                int nextNodeId = graph.getAdjacencyList().get(currentNodeId).get(i).getId();
                int nextDist = graph.getAdjacencyList().get(currentNodeId).get(i).getDist();

                if (dist.get(nextNodeId) == -1 || currentDist + nextDist < dist.get(nextNodeId)) {
                    remaining.add(new Node(nextNodeId, currentDist + nextDist));
                }
            }
        }
        return dist;
    }

    // PageRank --------------------------------------------------------------------------------------------------------

    public static ArrayList<Double> getPageRank(Graph graph, int maxIterations, double eps) {
        ArrayList<Double> value = new ArrayList<>();
        ArrayList<Double> oldValue = new ArrayList<>();

        double initialValue = 1.0d / Graph.N_NODES;
        for (int i=0; i<Graph.N_NODES; ++i) {
            value.add(initialValue);
            oldValue.add(initialValue);
        }

        int iter = 0;
        //boolean hasChange;
        do {
            ++ iter;
            //hasChange = false;

            for (int i=0; i<Graph.N_NODES; ++i) {
                oldValue.set(i, value.get(i));
                value.set(i, 0.15d / Graph.N_NODES);
            }
            for (int i=0; i<Graph.N_NODES; ++i) {
                ArrayList<Node> adjacencyList = graph.getAdjacencyList().get(i);
                double currentValue = oldValue.get(i);
                for (Node node: adjacencyList) {
                    value.set(node.getId(), value.get(node.getId()) + (0.85d * currentValue / adjacencyList.size()));
                }
            }

            /*for (int i=0; i<Graph.N_NODES; ++i) {
                if (Math.abs(value.get(i) - oldValue.get(i)) > eps) hasChange = true;
            }*/
        } while (iter <= maxIterations);// && hasChange);

        return value;
    }
}

