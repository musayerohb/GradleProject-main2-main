package gradleproject.CSC210FinalProject;

import java.util.ArrayList;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableValueGraph;

import java.awt.Color;
import java.awt.Point;
import java.util.Set;

/**
 * Initializes the Animation class, which contains methods for animating the coloring of the nodes in paths for the dataset.
 */
public class Animation {

/**
 * Enables the nodes to change colors when they fall along the path from the source to target noddes.
 * @param graph (MutableValueGraph<String, Integer>) The graph representing the dataset
 * @param nodes (ArrayList<String>) The nodes of the dataset
 */
  public static void animation(MutableValueGraph<String,Integer> graph, ArrayList<String> nodes) {
    GraphDisplay d = new GraphDisplay(graph);

    d.labelOffset = new Point(0,3);

    while (true) {
      for (String lbl: nodes) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(lbl,Color.BLUE);
      }
      // System.out.println(d.getEdgeSet().size());
      // int counter = 0;
      // for (Object edge: d.getEdgeSet()) {
      //   //System.out.println(counter);
      //   counter = counter + 1;
      //   try {
      //     Thread.sleep(500);
      //   } catch (Exception e) {
      //   }
      //   d.setColor(edge,Color.GREEN);
      // }
      for (String lbl: nodes) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(lbl,Color.RED);
      }
      // for (Object edge: d.getEdgeSet()) {
      //   try {
      //     Thread.sleep(500);
      //   } catch (Exception e) {
      //   }
      //   d.setColor(edge,Color.MAGENTA);
      //   //System.out.println(d.getColor(edge));
      // }
    }
    
  }

}

