package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Graph {
    private ArrayList<ArrayList<Node>> adjacencyList;
    private ArrayList<Edge> edgeList;
    public static final int N_NODES = 6927;

    private Graph() {
        this.adjacencyList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }

    public static Graph readNetwork() {
        Graph g = new Graph();
        for (int i = 0; i < Graph.N_NODES; ++i){
            g.adjacencyList.add(new ArrayList<>());
        }

        try {
            String filePath = "data/Erdos02.net.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("%") && !line.startsWith("*") && !line.contains("\"")) {
                    String numbers[] = line.trim().split(" ");
                    int sourceNodeId = Integer.parseInt(numbers[0]) - 1;

                    for (int i = 1; i < numbers.length; ++i) {
                        int destNodeId = Integer.parseInt(numbers[i]) - 1;
                        g.edgeList.add(new Edge(sourceNodeId, destNodeId));
                        g.adjacencyList.get(sourceNodeId).add(new Node(destNodeId));
                        g.adjacencyList.get(destNodeId).add(new Node(sourceNodeId));
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }

    public ArrayList<ArrayList<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }
}
