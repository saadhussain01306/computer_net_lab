/*Write a program to find the shortest path between vertices using 
bellman-ford algorithm.*/

//bellman ford algorithm for shortest distance from a source node to all the remaining nodes

import java.util.*;
public class BellmanFord {
    private static int N;
    private static int[][] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Vertices : ");
        N = sc.nextInt();
        System.out.println("Enter the Weight Matrix of Graph");
        graph = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                graph[i][j] = sc.nextInt();
        System.out.print("Enter the Source Vertex : ");
        int source = sc.nextInt();
        bellmanFord(source - 1);
    }

    public static void bellmanFord(int src) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Negative weight cycle detected.");
                    return;
                }
            }
        }
        printSolution(dist);
    }

    public static void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + "\t\t" + dist[i]);
        }
    }
}


/*output:-
Enter the number of Vertices : 5
Enter the Weight Matrix of Graph
0 6 0 7 0
0 0 5 8 -4
0 0 0 0 0
0 0 -3 0 9
2 0 0 0 0
Enter the Source Vertex : 1
Vertex   Distance from Source
1               0
2               6
3               4
4               7
5               2
*/


/*Enter the number of Vertices : 3
Enter the Weight Matrix of Graph
0 10 5
0 0 -8
0 0 0
Enter the Source Vertex : 1
Vertex   Distance from Source
1               0
2               10
3               2
*/


/*
Enter the number of Vertices : 3
Enter the Weight Matrix of Graph
0 10 0
0 0 20
0 -30 0
Enter the Source Vertex : 1
Negative weight cycle detected.
*/
