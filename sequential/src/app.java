import algorithm.Algo;
import algorithm.ConnectedComponent;
import algorithm.Diameter;
import model.Graph;

import java.sql.Time;
import java.util.ArrayList;

public class app {
    public static void main(String[] args) {
        Graph g = Graph.readNetwork();
        long startTime = System.nanoTime();

        //ArrayList<Integer> result = Algo.getShortestPath(g, 0);
        //Integer result = Algo.getNoOfConnectedComponents(g);
        ArrayList<Double> result =  Algo.getPageRank(g, 200, 0.00001d);

        long endTime = System.nanoTime();

        System.out.println("Start time: " + startTime + " End time: " + endTime + " Duration: " + (endTime - startTime));
        System.out.println("Used memory: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024));
        System.out.println("Diameter: " + result);

    }
}
