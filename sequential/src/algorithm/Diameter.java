package algorithm;

import model.Graph;

import java.util.*;

public class Diameter {
    public static int run(Graph graph) {
        int distMax = -1;
        for (int i = 0; i < Graph.N_NODES; ++i){
            ArrayList<Integer> dist = SingleSourceShortestPath.run(graph, i);
            for (int j = 0; j < Graph.N_NODES; ++j){
                distMax = Math.max(distMax, dist.get(j));
            }
        }

        return distMax;
    }
}
