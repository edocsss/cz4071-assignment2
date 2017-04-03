package algorithm;

import model.Graph;
import model.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleSourceShortestPath {
    public static class DistComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.getDist(), o2.getDist());
        }
    }

    public static ArrayList<Integer> run(Graph graph, int sourceId) {
        ArrayList<Integer> dist = new ArrayList<>();
        for (int i = 0; i < Graph.N_NODES; i++) dist.add(-1);

        PriorityQueue<Node> remaining = new PriorityQueue<Node>(100, new DistComparator());
        remaining.add(new Node(sourceId, 0));

        while (!remaining.isEmpty()){
            Node current = remaining.poll();
            int currentNodeId = current.getId();
            int currentDist = current.getDist();

            if (dist.get(currentNodeId) != -1 && dist.get(currentNodeId) <= currentDist) continue;
            dist.set(currentNodeId, currentDist);

            for (int i = 0; i < graph.getAdjacencyList().get(currentNodeId).size(); ++i){
                int nextNodeId = graph.getAdjacencyList().get(currentNodeId).get(i).getId();
                int nextDist = graph.getAdjacencyList().get(currentNodeId).get(i).getDist();

                if (dist.get(nextNodeId) == -1 || currentDist + nextDist < dist.get(nextNodeId)){
                    remaining.add(new Node(nextNodeId, currentDist + nextDist));
                }
            }
        }

        return dist;
    }
}
