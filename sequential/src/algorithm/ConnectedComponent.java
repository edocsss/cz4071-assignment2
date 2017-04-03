package algorithm;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.*;

public class ConnectedComponent {
    private static ArrayList<Integer> root;

    private static int getRoot(int nodeId) {
        if (root.get(nodeId) == nodeId) return nodeId;
        root.set(nodeId, getRoot(root.get(nodeId)));
        return root.get(nodeId);
    }

    public static int run(Graph graph) {
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
}
