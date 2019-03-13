/**
 * Author: Bogdan
 * Timestamp: 5/3/2016 12:43 AM
 */
public class MainGraph {

    public static void main(String[] args){
        Graph graph = randomGraph(10, 1);
        graph.display();
        graph.primAlgorithm();
        graph.kruskalAlgorithm();
    }

    private static Graph randomGraph(int n, double p) {
        Graph<Integer> graph = new Graph<Integer>();
        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();
            graph.addVertex(i, x, y);
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.random() < p) {
                    Graph.Position p1 = graph.position(i);
                    Graph.Position p2 = graph.position(j);
                    double weight = Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
                    graph.addEdge(i, j, weight);
                }
            }
        }
        return graph;
    }

}
