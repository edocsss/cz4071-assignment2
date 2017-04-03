import algorithm.ConnectedComponent;
import algorithm.Diameter;
import model.Graph;

public class app {
    public static void main(String[] args) {
        Graph g = Graph.readNetwork();
        System.out.println("Diameter: " + Diameter.run(g));
        System.out.println("Number of Connected Components: " + ConnectedComponent.run(g));
    }
}
