package model;

public class Edge{
    private int nodeA, nodeB, dist;

    Edge(int a, int b){
        nodeA = a;
        nodeB = b;
        dist = 1;
    }

    Edge(int a, int b, int d){
        nodeA = a;
        nodeB = b;
        dist = d;
    }

    public int getNodeA() {
        return nodeA;
    }

    public void setNodeA(int nodeA) {
        this.nodeA = nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    public void setNodeB(int nodeB) {
        this.nodeB = nodeB;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}