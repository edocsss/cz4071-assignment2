package model;

public class Node {
    private int id, dist;

    public Node(int n){
        id = n;
        dist = 1;
    }

    public Node(int n, int d){
        id = n;
        dist = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
