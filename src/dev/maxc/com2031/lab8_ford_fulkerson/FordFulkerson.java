package dev.maxc.com2031.lab8_ford_fulkerson;

/* @author Dr. Manal Helal (2019); Steve Schneider (2020)
 * @date created: November 2019; November 2020
 * @University of Surrey
 * Week 8 Lab: Finding min-cut and max-flow in a given graph  using Ford Fulkerson Algorithm
 */


import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    public int rGraph[][];

//********* Search Utilities: breadth first search and depth first search	

    // A breadth-first search for a path from s to t.
    // Returns true if there is a path from source 's' to sink 't' in residual
    // graph. Also fills the array parent[] to store the path
    public static boolean bfs(int[][] rGraph, int s, int t, int[] parent) {

        // Create a visited array and mark all vertices as not visited
        boolean[] visited = new boolean[rGraph.length];

        // Create a queue, enqueue source vertex
        // and mark source vertex as visited
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 0; i < rGraph.length; i++) {
                if (rGraph[v][i] > 0 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    parent[i] = v;
                }
            }
        }

        // If we reached sink in BFS starting from source, then return true, else false
        return (visited[t]);
    }

    // A DFS based function to find all reachable vertices from s. The function marks visited[i]
    // as true if i is reachable from s. The initial  values in visited[] must be false. We can also
    // use BFS to find reachable vertices
    static void dfs(int[][] rGraph, int s, boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < rGraph.length; i++) {
            if (rGraph[s][i] > 0 && !visited[i]) {
                dfs(rGraph, i, visited);
            }
        }
    }

    // Prints the minimum s-t cut, only prints the edges that are from the reachable vertex to the non-reachable vertices (the cut) as extra
    void printMinCut(int[][] rGraph, int[][] graph, int s, int t, int cut_cap) {
        System.out.println("Minimum-cut capacity is " + cut_cap + ".");
        System.out.println("The following nodes are reachable from s: ");
        System.out.println("The following edges from reachable nodes to non-reachable nodes:");

        boolean[] visited = new boolean[graph.length];
        dfs(rGraph, s, visited);

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0 && visited[i] && !visited[j]) {
                    System.out.println("edge:" + i + "-->" + j + "  capacity:" + graph[i][j]);
                }
            }
        }
    }


    // Returns the maximum flow from s to t in the given graph, exactly the same as min0cut, without the printing of the edges of the cut
    int[][] fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        this.rGraph = new int[graph.length][graph.length];
        int flow[][] = new int[graph.length][graph.length];  // this is what is added to keep track of the flow along the edges of the found paths augmented with new bottlenecks as found

        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++) {
                this.rGraph[u][v] = graph[u][v];                // initialise residual graph with the capcities of the original graph
                flow[u][v] = 0;                                // initialise flow with zero
            }

        // This array is filled by BFS and to store path
        int parent[] = new int[graph.length];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while there is path from source
        // to sink
        while (bfs(this.rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int bottleneck = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                // update residual graph capacities of the edges and reverse edges along the path
                this.rGraph[u][v] = graph[u][v] - flow[u][v];            // forward edge
                this.rGraph[v][u] = flow[u][v];                            // backward edge
                bottleneck = Math.min(bottleneck, this.rGraph[u][v]);    // find bottleneck, edge with minimum residual capacity across the edges of the path
            }

            // update flow based on calculated bottleneck and residual graph from the previous loop
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                if (graph[u][v] > 0)                            // forward edge
                    flow[u][v] += bottleneck;
                else                                            // backward edge
                    flow[u][v] -= bottleneck;


            }

            // Add path flow to overall flow
            max_flow += bottleneck;
        }

        // printing the min cut using the max flow found
        printMinCut(rGraph, graph, s, t, max_flow);
        // Return the overall flow
        return flow;
    }


    //Driver Program
    public static void main(String args[]) {

        FordFulkerson maxFlowFordFulkerson = new FordFulkerson();

        System.out.println("\nTest Graph 1\n");

        // First Test case
        int graph1[][] = {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        int s = 0;
        int t = 5;


        int[][] maxFlow = maxFlowFordFulkerson.fordFulkerson(graph1, 0, t);
        int flowValue = 0;
        for (int i = 0; i < graph1.length; i++)
            flowValue += maxFlow[i][t];

        System.out.println("\nTest Graph 2\n");

        // Second Test case
        int graph2[][] = {
                {0, 4, 3, 0, 0},
                {0, 0, 7, 5, 0},
                {0, 0, 0, 8, 3},
                {0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0}
        };
        t = 4;

        maxFlow = maxFlowFordFulkerson.fordFulkerson(graph2, 0, t);
        flowValue = 0;
        for (int i = 0; i < graph2.length; i++)
            flowValue += maxFlow[i][t];

        System.out.println("\nTest Graph 3\n");

        // Third Test case
        int graph3[][] = {
                {0, 9, 2, 0, 0},
                {0, 0, 5, 3, 0},
                {0, 0, 0, 2, 4},
                {0, 0, 0, 0, 6},
                {0, 0, 0, 0, 0}
        };
        t = 4;


        flowValue = 0;
        for (int i = 0; i < graph3.length; i++)
            flowValue += maxFlow[i][t];

        maxFlow = maxFlowFordFulkerson.fordFulkerson(graph3, 0, t);
        flowValue = 0;
        for (int i = 0; i < graph3.length; i++)
            flowValue += maxFlow[i][t];


        System.out.println("\nTest Graph 4\n");

        // this is the lecture example
        // Fourth Test case
        int graph4[][] = {
                {0, 10, 10, 0, 0, 0},
                {0, 0, 2, 4, 8, 0},
                {0, 0, 0, 0, 9, 0},
                {0, 0, 0, 0, 0, 10},
                {0, 0, 0, 6, 0, 10},
                {0, 0, 0, 0, 0, 0}
        };

        t = 5;


        maxFlow = maxFlowFordFulkerson.fordFulkerson(graph4, 0, t);
        flowValue = 0;
        for (int i = 0; i < graph4.length; i++)
            flowValue += maxFlow[i][t];

    }
} 

