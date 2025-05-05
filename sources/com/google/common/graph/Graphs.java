package com.google.common.graph;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ImmutableGraph;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class Graphs extends GraphsBridgeMethods {

    private enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    private Graphs() {
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N subgraphHasCycle : graph.nodes()) {
            if (subgraphHasCycle(graph, newHashMapWithExpectedSize, subgraphHasCycle)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n) {
        N n2;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addLast(new NodeAndRemainingSuccessors(n));
        while (!arrayDeque.isEmpty()) {
            NodeAndRemainingSuccessors nodeAndRemainingSuccessors = (NodeAndRemainingSuccessors) arrayDeque.removeLast();
            NodeAndRemainingSuccessors nodeAndRemainingSuccessors2 = (NodeAndRemainingSuccessors) arrayDeque.peekLast();
            arrayDeque.addLast(nodeAndRemainingSuccessors);
            N n3 = nodeAndRemainingSuccessors.node;
            if (nodeAndRemainingSuccessors2 == null) {
                n2 = null;
            } else {
                n2 = nodeAndRemainingSuccessors2.node;
            }
            if (nodeAndRemainingSuccessors.remainingSuccessors == null) {
                NodeVisitState nodeVisitState = map.get(n3);
                if (nodeVisitState == NodeVisitState.COMPLETE) {
                    arrayDeque.removeLast();
                } else if (nodeVisitState == NodeVisitState.PENDING) {
                    return true;
                } else {
                    map.put(n3, NodeVisitState.PENDING);
                    nodeAndRemainingSuccessors.remainingSuccessors = new ArrayDeque(graph.successors((Object) n3));
                }
            }
            if (!nodeAndRemainingSuccessors.remainingSuccessors.isEmpty()) {
                N remove = nodeAndRemainingSuccessors.remainingSuccessors.remove();
                if (canTraverseWithoutReusingEdge(graph, remove, n2)) {
                    arrayDeque.addLast(new NodeAndRemainingSuccessors(remove));
                }
            }
            arrayDeque.removeLast();
            map.put(n3, NodeVisitState.COMPLETE);
        }
        return false;
    }

    private static final class NodeAndRemainingSuccessors<N> {
        final N node;
        @CheckForNull
        Queue<N> remainingSuccessors;

        NodeAndRemainingSuccessors(N n) {
            this.node = n;
        }
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @CheckForNull Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    public static <N> ImmutableGraph<N> transitiveClosure(Graph<N> graph) {
        ImmutableGraph.Builder<N1> immutable = GraphBuilder.from(graph).allowsSelfLoops(true).immutable();
        if (graph.isDirected()) {
            for (N next : graph.nodes()) {
                UnmodifiableIterator<N> it = reachableNodes(graph, next).iterator();
                while (it.hasNext()) {
                    immutable.putEdge(next, it.next());
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N next2 : graph.nodes()) {
                if (!hashSet.contains(next2)) {
                    ImmutableSet<N> reachableNodes = reachableNodes(graph, next2);
                    hashSet.addAll(reachableNodes);
                    int i = 1;
                    for (N next3 : reachableNodes) {
                        int i2 = i + 1;
                        for (T putEdge : Iterables.limit(reachableNodes, i)) {
                            immutable.putEdge(next3, putEdge);
                        }
                        i = i2;
                    }
                }
            }
        }
        return immutable.build();
    }

    public static <N> ImmutableSet<N> reachableNodes(Graph<N> graph, N n) {
        Preconditions.checkArgument(graph.nodes().contains(n), "Node %s is not an element of this graph.", (Object) n);
        return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst(n));
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedGraph) {
            return ((TransposedGraph) graph).graph;
        }
        return new TransposedGraph(graph);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (!valueGraph.isDirected()) {
            return valueGraph;
        }
        if (valueGraph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) valueGraph).graph;
        }
        return new TransposedValueGraph(valueGraph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    static <N> EndpointPair<N> transpose(EndpointPair<N> endpointPair) {
        return endpointPair.isOrdered() ? EndpointPair.ordered(endpointPair.target(), endpointPair.source()) : endpointPair;
    }

    private static class TransposedGraph<N> extends ForwardingGraph<N> {
        /* access modifiers changed from: private */
        public final Graph<N> graph;

        TransposedGraph(Graph<N> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: package-private */
        public Graph<N> delegate() {
            return this.graph;
        }

        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        public Set<EndpointPair<N>> incidentEdges(N n) {
            return new IncidentEdgeSet<N>(this, n) {
                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(TransposedGraph.this.delegate().incidentEdges(this.node).iterator(), new Graphs$TransposedGraph$1$$ExternalSyntheticLambda0(this));
                }

                /* access modifiers changed from: package-private */
                /* renamed from: lambda$iterator$0$com-google-common-graph-Graphs$TransposedGraph$1  reason: not valid java name */
                public /* synthetic */ EndpointPair m553lambda$iterator$0$comgooglecommongraphGraphs$TransposedGraph$1(EndpointPair endpointPair) {
                    return EndpointPair.of((Graph<?>) TransposedGraph.this.delegate(), endpointPair.nodeV(), endpointPair.nodeU());
                }
            };
        }

        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }
    }

    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        /* access modifiers changed from: private */
        public final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        /* access modifiers changed from: package-private */
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }

        @CheckForNull
        public V edgeValueOrDefault(N n, N n2, @CheckForNull V v) {
            return delegate().edgeValueOrDefault(n2, n, v);
        }

        @CheckForNull
        public V edgeValueOrDefault(EndpointPair<N> endpointPair, @CheckForNull V v) {
            return delegate().edgeValueOrDefault(Graphs.transpose(endpointPair), v);
        }
    }

    private static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        /* access modifiers changed from: private */
        public final Network<N, E> network;

        TransposedNetwork(Network<N, E> network2) {
            this.network = network2;
        }

        /* access modifiers changed from: package-private */
        public Network<N, E> delegate() {
            return this.network;
        }

        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        public Set<E> inEdges(N n) {
            return delegate().outEdges(n);
        }

        public Set<E> outEdges(N n) {
            return delegate().inEdges(n);
        }

        public EndpointPair<N> incidentNodes(E e) {
            EndpointPair incidentNodes = delegate().incidentNodes(e);
            return EndpointPair.of((Network<?, ?>) this.network, incidentNodes.nodeV(), incidentNodes.nodeU());
        }

        public Set<E> edgesConnecting(N n, N n2) {
            return delegate().edgesConnecting(n2, n);
        }

        public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
            return delegate().edgesConnecting(Graphs.transpose(endpointPair));
        }

        @CheckForNull
        public E edgeConnectingOrNull(N n, N n2) {
            return delegate().edgeConnectingOrNull(n2, n);
        }

        @CheckForNull
        public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
            return delegate().edgeConnectingOrNull(Graphs.transpose(endpointPair));
        }

        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }

        public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
            return delegate().hasEdgeConnecting(Graphs.transpose(endpointPair));
        }
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N1> mutableGraph;
        if (iterable instanceof Collection) {
            mutableGraph = GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableGraph = GraphBuilder.from(graph).build();
        }
        for (Object addNode : iterable) {
            mutableGraph.addNode(addNode);
        }
        for (N1 next : mutableGraph.nodes()) {
            for (Object next2 : graph.successors((Object) next)) {
                if (mutableGraph.nodes().contains(next2)) {
                    mutableGraph.putEdge(next, next2);
                }
            }
        }
        return mutableGraph;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N1, V1> mutableValueGraph;
        if (iterable instanceof Collection) {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).build();
        }
        for (Object addNode : iterable) {
            mutableValueGraph.addNode(addNode);
        }
        for (N1 next : mutableValueGraph.nodes()) {
            for (Object next2 : valueGraph.successors((Object) next)) {
                if (mutableValueGraph.nodes().contains(next2)) {
                    mutableValueGraph.putEdgeValue(next, next2, java.util.Objects.requireNonNull(valueGraph.edgeValueOrDefault(next, next2, null)));
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N1, E1> mutableNetwork;
        if (iterable instanceof Collection) {
            mutableNetwork = NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableNetwork = NetworkBuilder.from(network).build();
        }
        for (Object addNode : iterable) {
            mutableNetwork.addNode(addNode);
        }
        for (N1 next : mutableNetwork.nodes()) {
            for (E next2 : network.outEdges(next)) {
                N adjacentNode = network.incidentNodes(next2).adjacentNode(next);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(next, adjacentNode, next2);
                }
            }
        }
        return mutableNetwork;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N addNode : graph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : graph.edges()) {
            build.putEdge(next.nodeU(), next.nodeV());
        }
        return build;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N1, V1> build = ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N addNode : valueGraph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : valueGraph.edges()) {
            build.putEdgeValue(next.nodeU(), next.nodeV(), java.util.Objects.requireNonNull(valueGraph.edgeValueOrDefault(next.nodeU(), next.nodeV(), null)));
        }
        return build;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N1, E1> build = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N addNode : network.nodes()) {
            build.addNode(addNode);
        }
        for (E next : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(next);
            build.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), next);
        }
        return build;
    }

    static int checkNonNegative(int i) {
        Preconditions.checkArgument(i >= 0, "Not true that %s is non-negative.", i);
        return i;
    }

    static long checkNonNegative(long j) {
        Preconditions.checkArgument(j >= 0, "Not true that %s is non-negative.", j);
        return j;
    }

    static int checkPositive(int i) {
        Preconditions.checkArgument(i > 0, "Not true that %s is positive.", i);
        return i;
    }

    static long checkPositive(long j) {
        Preconditions.checkArgument(j > 0, "Not true that %s is positive.", j);
        return j;
    }
}
