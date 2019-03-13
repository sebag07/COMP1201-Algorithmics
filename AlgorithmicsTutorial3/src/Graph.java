import java.util.*;

/**
 * Author: Bogdan
 * Timestamp: 4/25/2016 10:33 PM
 */
public class Graph<T> {

    private HashMap<T, Position<T>> vertices = new HashMap<T, Position<T>>();
    private HashSet<Edge<T>> edges = new HashSet<Edge<T>>();

    public void addVertex(T value, double x, double y) {
        vertices.put(value, new Position<T>(x, y));
    }

    public void addEdge(T vertex1value, T vertex2value, double weight) throws NoSuchElementException {
        if (!vertices.containsKey(vertex1value) && !vertices.containsKey(vertex2value)) {
            throw new NoSuchElementException("No such vertex");
        }
        if (!edgeExist(vertex1value, vertex2value)) {
            Edge<T> edge = new Edge<T>(vertex1value, vertex2value, weight);
            edges.add(edge);
            vertices.get(vertex1value).addNeighbor(vertex2value, weight);
            vertices.get(vertex2value).addNeighbor(vertex1value, weight);
        }
    }

    public int edgesCount() {
        return edges.size();
    }

    public int verticesCount() {
        return vertices.size();
    }

    public Position<T> position(T vertex) {
        return vertices.get(vertex);
    }

    public boolean edgeExist(T vertex1, T vertex2) {
        return vertices.containsKey(vertex1) && vertices.get(vertex1).hasNeighbor(vertex2);
    }

    public double weight(T vertex1, T vertex2) {
        return vertices.get(vertex1).getWeight(vertex2);
    }

    public List<T> neighbours(T vertex) {
        return vertices.get(vertex).getNeighbors();
    }

    public void primAlgorithm() {
        HashMap<T, Double> distances = new HashMap<T, Double>();
        Set<T> nodes = this.vertices.keySet();
        for (T node : nodes) {
            distances.put(node, (double) Integer.MAX_VALUE);
        }
        T node = nodes.iterator().next();
        ArrayList<Edge<T>> primEdges = new ArrayList<Edge<T>>();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<Edge<T>>(10, new Comparator<Edge<T>>() {
            @Override
            public int compare(Edge<T> o1, Edge<T> o2) {
                return o1.weight > o2.weight ? 1 : -1;
            }
        });
        for (int i = 0; i < nodes.size() - 1; i++) {
            distances.put(node, 0d);
            List<T> neighbours = this.neighbours(node);
            for (T neighbour : neighbours) {
                double weight = this.weight(node, neighbour);
                if (weight < distances.get(neighbour)) {
                    distances.put(neighbour, weight);
                    priorityQueue.add(new Edge<T>(node, neighbour, weight));
                }
            }
            Edge<T> edge;
            T nextNode;
            do {
                edge = priorityQueue.poll();
                if (distances.get(edge.vertex1) == 0) {
                    nextNode = edge.vertex2;
                } else {
                    nextNode = edge.vertex1;
                }
            } while (distances.get(nextNode) == 0);
            primEdges.add(edge);
            node = nextNode;
        }
        GraphDisplay graphDisplay = new GraphDisplay();
        graphDisplay.showInWindow(400, 400, "Prim Algorithm");
        for (T n : nodes) {
            Position position = position(n);
            graphDisplay.addNode(n, position.x, position.y);
        }
        for (Edge edge : primEdges) {
            graphDisplay.addEdge(edge.vertex1, edge.vertex2);
        }
    }

    public void kruskalAlgorithm() {
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<Edge<T>>(10, new Comparator<Edge<T>>() {
            @Override
            public int compare(Edge<T> o1, Edge<T> o2) {
                return o1.weight > o2.weight ? 1 : -1;
            }
        });
        for (Edge<T> edge : edges) {
            priorityQueue.add(edge);
        }
        ArrayList<Edge<T>> kruskalEdges = new ArrayList<Edge<T>>();
        Set<T> nodes = this.vertices.keySet();
        int edgesAccepted = 0;
        DisjointSets dj = new DisjointSets(nodes.size());
        while (edgesAccepted < nodes.size() - 1) {
            Edge<T> edge = priorityQueue.poll();
            int finda = dj.find((Integer) edge.vertex1);
            int findb = dj.find((Integer) edge.vertex2);
            if (finda != findb) {
                dj.union(finda, findb);
                kruskalEdges.add(edge);
                edgesAccepted++;
            }
        }
        GraphDisplay graphDisplay = new GraphDisplay();
        graphDisplay.showInWindow(400, 400, "Kruskal Algorithm");
        for (T n : nodes) {
            Position position = position(n);
            graphDisplay.addNode(n, position.x, position.y);
        }
        for (Edge edge : kruskalEdges) {
            graphDisplay.addEdge(edge.vertex1, edge.vertex2);
        }
    }

    public void display() {
        GraphDisplay graphDisplay = new GraphDisplay();
        graphDisplay.showInWindow(400, 400, "A Random Graph");
        Set<T> nodes = vertices.keySet();
        for (T node : nodes) {
            Position position = position(node);
            graphDisplay.addNode(node, position.x, position.y);
        }
        for (Edge edge : edges) {
            graphDisplay.addEdge(edge.vertex1, edge.vertex2);
        }
    }

    public static class Position<T> {
        public double x, y;
        public List<T> neighbors = new ArrayList<T>();
        public HashMap<T, Double> edgesWeight = new HashMap<T, Double>();

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void addNeighbor(T vertex, double weight) {
            neighbors.add(vertex);
            edgesWeight.put(vertex, weight);
        }

        public boolean hasNeighbor(T vertex) {
            return neighbors.contains(vertex);
        }

        public List<T> getNeighbors() {
            return neighbors;
        }

        public double getWeight(T vertex) {
            return edgesWeight.get(vertex);
        }

    }

    public static class Edge<T> {
        public T vertex1, vertex2;
        public double weight;

        public Edge(T vertex1, T vertex2, double weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        public boolean equals(T vertex1, T vertex2) {
            return (this.vertex1.equals(vertex1) && this.vertex2.equals(vertex2)) ||
                    (this.vertex1.equals(vertex2) && this.vertex2.equals(vertex1));
        }

        @Override
        public String toString() {
            return vertex1 + "->" + vertex2 + ";weight:" + weight;
        }
    }

}
