package model;

import java.util.ArrayList;

public class Node {
    private int id;
    private String label;
    private ArrayList<Integer> edgeList;

    public Node(int id, String label) {
        this.id = id;
        this.label = label;
        this.edgeList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Integer> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(int targetNodeId) {
        this.edgeList.add(targetNodeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node: " + this.id);
        sb.append("\n");
        sb.append("Label: " + this.label);
        sb.append("\n");
        sb.append("Edges: ");

        for (int targetNodeId: this.edgeList) {
            sb.append(targetNodeId + " ");
        }

        sb.append("\n");
        return sb.toString();
    }
}
