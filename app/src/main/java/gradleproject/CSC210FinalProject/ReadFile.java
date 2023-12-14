package gradleproject.CSC210FinalProject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.management.RuntimeErrorException;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.awt.Color;
import java.awt.Point;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Initializes the ReadFile class, which contains methods for computating the
 * dataset.
 */
public class ReadFile {

  /**
   * File reader for dataset; takes in a file with a corresponding file name and
   * reads it, placing it into a scanner.
   * 
   * @param graph           (MutableValueGraph<String, Integer>) The graph
   *                        representing the dataset
   * @param listofVerticies (HashSet<String>) The list of nodes in the dataset
   * @return The set of edges in the dataset being read
   */
  public static ArrayList<String[]> ReadInFile(MutableValueGraph<String, Integer> graph,
      HashSet<String> listOfVertices) {
    String filename = "S:\\Downloads\\GradleProject-main2-main\\app\\src\\main\\java\\gradleproject\\TexasRoadnetworks.txt";
    File file = null;

    Scanner scanner = null;

    try {
      file = new File(filename);
    }
    // find exactly what exception this is supposed to be
    catch (Exception e) {
      System.out.println("Cannot locate file.");
      System.exit(-1);
    }

    try {
      scanner = new Scanner(file);
    } catch (Exception e) {
      System.out.println("scanner not working");
      e.printStackTrace();
      System.exit(-1);
    }

    // Need to initialize nodes in text files
    // Node fromNodeID = new ReadFile(File[0]);
    // Node ToNodeID = File[1];

    ArrayList<String[]> edgeSets = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] vertices = line.split("\t");

      graph.addNode(vertices[0]);

      // this is for unweighted graph
      // randomly generate weights for weight graph

      // weight cannot be negative due to dijkstra algorithm
      int weight = ThreadLocalRandom.current().nextInt(0, 20);
      // and instead of putting in 1 and "1" use weight
      graph.putEdgeValue(vertices[0], vertices[1], weight);
      String[] connections = { vertices[0], vertices[1], Integer.toString(weight) };
      edgeSets.add(connections);

      if (!listOfVertices.contains(vertices[0])) {
        listOfVertices.add(vertices[0]);
      }

    }

    scanner.close();
    return edgeSets;
  }

  /**
   * Executes Dijsktra's algorithm, which takes in the groph and finds the
   * shortest possible path between nodes.
   * 
   * @param graph  (MutableValueGraph<String, Integer>) The graph representing the
   *               dataset
   * @param source (String) The node at the start of the path
   * @return The distance between the source and target node, and the path to get
   *         from the source to the target node
   */
  public static ArrayList<Object> DijkstraAlgorithm(MutableValueGraph<String, Integer> graph, String source) {
    // psuedocode from Wikipedia page:
    // Link: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    // 1 function Dijkstra(Graph, source):
    // 2
    // 3 for each vertex v in Graph.Vertices:
    // 4 dist[v] ← INFINITY
    // 5 prev[v] ← UNDEFINED
    // 6 add v to Q
    // 7 dist[source] ← 0
    // 8
    // 9 while Q is not empty:
    // 10 u ← vertex in Q with min dist[u]
    // 11 remove u from Q
    // 12
    // 13 for each neighbor v of u still in Q:
    // 14 alt ← dist[u] + Graph.Edges(u, v)
    // 15 if alt < dist[v]:
    // 16 dist[v] ← alt
    // 17 prev[v] ← u
    // 18
    // 19 return dist[], prev[]

    // start with endpoint that isn't original endpoint
    // think of each node as having a link in the chain to the next node
    String startNode = "0";
    HashMap<String, Integer> distance = new HashMap<String, Integer>();
    HashMap<String, String> via = new HashMap<String, String>();
    ArrayList<String> Q = new ArrayList<>();

    new GraphDisplay(graph);

    // for each vertex v in Graph.Vertices;
    for (String v : graph.nodes()) {
      // dist[v] ← INFINITY
      distance.put(v, Integer.MAX_VALUE);
      // prev[v] ← UNDEFINED
      via.put(v, "undefined");
      // add v to Q
      Q.add(v);
    }

    // dist[source] ← 0
    distance.replace(source, 0);

    // while Q is not empty:
    while (Q != null && Q.size() > 0) {
      // u ← vertex in Q with min dist[u]
      String u = null;
      int minValue = Integer.MAX_VALUE;

      for (String i : Q) {

        if (minValue > distance.get(i)) {
          minValue = distance.get(i);
          u = i;
        }
      }

      // remove u from Q
      u = Q.get(Q.indexOf(u));
      Q.remove(u);

      // for each neighbor v of u still in Q:
      for (String v : graph.successors(u)) {
        if (Q.contains(v)) {

          // int alt = distance[u] + graph.edges(u, v);
          // .edgeValueOrDefault() gives the value of the edge or -1 if the edge's value
          // is not found.
          int prevdistance = graph.edgeValueOrDefault(u, v, -1);
          int alt = distance.get(u) + graph.edgeValueOrDefault(u, v, -1);

          // if alt < dist[v]:
          if (alt < distance.get(v)) {

            // via gets updated to = neighbor because neighbor is via
            // insert/modify location being looked at (key), via is the node that brought
            // you there (where distance is)

            // dist[v] ← alt
            distance.replace(v, alt);
            // prev[v] ← u
            via.replace(v, u);
          }
        }

      }
    }

    ArrayList<Object> distanceAndVia = new ArrayList<Object>();
    distanceAndVia.add(distance);
    distanceAndVia.add(via); // end of algorithm.

    // getting path from source node to target node.
    Scanner userInput = new Scanner(System.in);

    System.out.println("\nWhere do you want to go? (Enter target node number): ");

    String target = userInput.nextLine();

    target = handleBadInput(target, userInput, graph);

    userInput.close();

    ArrayList<String> nodes = getPath(source, target, distance, via);

    System.out.println("\nLook on your computer for a new graph display showing the shortest path between your source node and target node!");
    Animation.animation(graph, nodes);

    
    return distanceAndVia;
  }

  /**
   * Returns the maximum value found in the specified range of a given array.
   * @param arr (int[]) A given array of
   * @param lo  (int) The lowest index at which the method will start looking for
   *            a
   *            maximum value
   * @param hi  (int) The highest index at which the method will stop looking for
   *            a
   *            maximum value
   * @return The maximum value found in the array within the specified range
   */
  public static float max(int[] arr, int lo, int hi) {

    if (arr.length == 0 || hi == 0) {
      return Float.NaN;
    }

    else {
      float maxValue = arr[lo];
      for (int i = lo; i < hi; i++) {
        if (maxValue < arr[i]) {
          maxValue = arr[i];
        } else {
          continue;
        }
      }
      return maxValue;
    }

  }

  /**
   * Prints the number of nodes, number of edges, maximum node degree, average
   * node degree, and other characteristics of the dataset.
   * 
   * @param listofVerticies (HashSet<String>) The verticies in the dataset being
   *                        analyzed
   * @param graph           (MutableValueGraph<String,Integer>) The graph graphic
   *                        that will display the dataset in a graph form
   * @param edgeSets        (ArrayList<String[]>) A group of the edges in the
   *                        dataset
   */
  public static void printStatistics(HashSet<String> listOfVertices, MutableValueGraph<String, Integer> graph,
      ArrayList<String[]> edgeSets) {
    System.out.println(
        "As described by the dataset's source, the intersections and endpoints in the dataset are represented on our graph as nodes, and the roads connecting these intersections or endpoints are represented by undirected edges.");

    System.out.println("\nGraph stats:");
    System.out.println("Number of nodes in this graph: " + graph.nodes().size());
    System.err.println("Number of edges: " + graph.edges().size());

    int[] storedEdges = new int[edgeSets.size()];
    for (int i = 0; i < listOfVertices.size(); i++) {
      storedEdges[i] = graph.degree(listOfVertices.toArray()[i].toString());
    }
    System.out.println("Maximum node degree: " + max(storedEdges, 0, storedEdges.length));

    // avg node degree
    int numberOfEdges = 0;
    for (int i = 0; i < graph.edges().size(); i++) {
      numberOfEdges++;
    }
    int avgDegree = numberOfEdges / listOfVertices.size();
    System.out.println("The average node degree: " + avgDegree);

    System.out.println(
        "\nThis program can find the shortest route between two places/intersections in Texas (the nodes) using the edges (roads) on the graph.");
  }

  /**
   * Obtains the path between the source and target nodes and colors it in the
   * graph display to highlight it.
   * 
   * @param source   (String) The node at the start of the path
   * @param target   (String) The node at the end of the path; the node being
   *                 navigated to
   * @param distance (HashMap<String, Integer>) The distance between the source
   *                 and target nodes
   * @param via      (HashMap<String, String>) The path connecting the source and
   *                 target nodes, and the nodes in-between
   * @return An array list containing the nodes that will be colored in on the
   *         graph display
   */
  public static ArrayList<String> getPath(String source, String target, HashMap<String, Integer> distance, HashMap<String, String> via) {

    String path = "Path from " + source + " to " + target + ": ";
    String pathRoads = " ";

    // travel backwards from destination node to source node
    // pathRoads at the end of this for loop should contain the path in reverse

    String currentNode = target; // 358
    pathRoads += currentNode + " "; // pathRoads = "Path from " + source + " to " + target + ": 358"

    // will stop when we reach 0
    while (!currentNode.equals(source)) {
      currentNode = via.get(currentNode); // 0
      pathRoads += currentNode + " "; // Path from " + source + " to " + target + ": 358 29 0
    }

    String[] pathRoadsReversal = pathRoads.split(" ");

    ArrayList<String> pathToColor = new ArrayList<String>();

    // w/ help from
    // https://www.simplilearn.com/tutorials/java-tutorial/reverse-a-string-in-java
    // clean this up because it's not even apparent
    for (int i = pathRoadsReversal.length - 1; i >= 0; i--) {
      if (i == 0) {
        continue;
      }
      if (i == 1) {
        path += pathRoadsReversal[i] + "";
        pathToColor.add(pathRoadsReversal[i]);
      } else {
        path += pathRoadsReversal[i] + ", ";
        pathToColor.add(pathRoadsReversal[i]);
      }
    }

    return pathToColor;
  }

  /**
   * Handles possible incorrect inputs for source and target nodes.
   * 
   * @param input     (String) The input, sent in by the user, that will be
   *                  checked.
   * @param userInput (Scanner) A scanner in the terminal that reads in input from
   *                  the user.
   * @param graph     (MutableValueGraph<String, Integer>) The graph, from which
   *                  we will get the nodes and check if the user's inputted
   *                  source/target is among them.
   * @return A valid node that can be used as either the source node or the target
   *         node for the program's computation.
   */
  public static String handleBadInput(String input, Scanner userInput, MutableValueGraph<String, Integer> graph) {
    boolean valid = false;

    for (String s : graph.nodes()) {
      if (s.equals(input)) {
        valid = true;
        break;
      }
    }

    while (valid == false) {
      System.out.println("Node not found, please try again.\s");
      input = userInput.nextLine();
      for (String s : graph.nodes()) {
        if (s.equals(input)) {
          valid = true;
          break;
        }
      }
    }

    return input;

  }

  public static void main(String[] args) {

    MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();
    HashSet<String> listOfVertices = new HashSet<>();
    ArrayList<String[]> edgeSets = ReadFile.ReadInFile(graph, listOfVertices);

    printStatistics(listOfVertices, graph, edgeSets);

    // Enter in source node.
    System.out.println("\nEnter in the place/intersection (node) you want to start at. ");

    Scanner userInput = new Scanner(System.in);
    String source = userInput.nextLine();

    // handles bad input for source node.
    source = handleBadInput(source, userInput, graph);

    DijkstraAlgorithm(graph, source);

  }
}
