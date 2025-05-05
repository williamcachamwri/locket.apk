package com.google.common.graph;

import java.util.Set;

@ElementTypesAreNonnullByDefault
abstract class GraphsBridgeMethods {
    GraphsBridgeMethods() {
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        return Graphs.transitiveClosure(graph);
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N n) {
        return Graphs.reachableNodes(graph, n);
    }
}
