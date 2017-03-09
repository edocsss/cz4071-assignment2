package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

public class Graph {
    private HashMap<Integer, Node> nodes;
    private int nNodes;
    private int nEdges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.nNodes = 0;
        this.nEdges = 0;
    }

    public static Graph readNetwork() {
        String networkFilePath = "data/Erdos02.net.txt";
        Graph g = new Graph();

        try {
            FileInputStream fileInputStream = new FileInputStream(networkFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            String[] elements;
            int nodeId;
            int sourceNodeId, targetNodeId;
            String label;
            Node sourceNode;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("%") || line.startsWith("*")) {
                    continue;
                }

                if (line.contains("\"")) {
                    elements = line.split(" \"");
                    nodeId = Integer.parseInt(elements[0]);
                    label = elements[1];

                    Node node = new Node(nodeId, label);
                    g.addNode(node);
                    g.nNodes++;
                } else {
                    elements = line.split(" ");
                    sourceNodeId = Integer.parseInt(elements[0]);
                    sourceNode = g.getNodeById(sourceNodeId);

                    for (int i = 1; i < elements.length; i++) {
                        targetNodeId = Integer.parseInt(elements[i]);
                        sourceNode.addEdge(targetNodeId);
                        g.nEdges++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }

    public void addNode(Node node) {
        this.nodes.put(node.getId(), node);
    }

    public Node getNodeById(int nodeId) {
        return this.nodes.get(nodeId);
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public int getnNodes() {
        return nNodes;
    }

    public int getnEdges() {
        return nEdges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> keys = this.nodes.keySet();

        sb.append("Number of nodes: ").append(this.nNodes);
        sb.append("\n");
        sb.append("Number of edges: ").append(this.nEdges);
        sb.append("\n");

        for (int key: keys) {
            sb.append(this.getNodeById(key));
            sb.append('\n');
        }

        return sb.toString();
    }
}
