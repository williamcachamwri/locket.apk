package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class AbstractNetwork<N, E> implements Network<N, E> {
    public Graph<N> asGraph() {
        return new AbstractGraph<N>() {
            public Set<N> nodes() {
                return AbstractNetwork.this.nodes();
            }

            public Set<EndpointPair<N>> edges() {
                if (AbstractNetwork.this.allowsParallelEdges()) {
                    return super.edges();
                }
                return new AbstractSet<EndpointPair<N>>() {
                    public Iterator<EndpointPair<N>> iterator() {
                        return Iterators.transform(AbstractNetwork.this.edges().iterator(), new AbstractNetwork$1$1$$ExternalSyntheticLambda0(this));
                    }

                    /* access modifiers changed from: package-private */
                    /* renamed from: lambda$iterator$0$com-google-common-graph-AbstractNetwork$1$1  reason: not valid java name */
                    public /* synthetic */ EndpointPair m552lambda$iterator$0$comgooglecommongraphAbstractNetwork$1$1(Object obj) {
                        return AbstractNetwork.this.incidentNodes(obj);
                    }

                    public int size() {
                        return AbstractNetwork.this.edges().size();
                    }

                    public boolean contains(@CheckForNull Object obj) {
                        if (!(obj instanceof EndpointPair)) {
                            return false;
                        }
                        EndpointPair endpointPair = (EndpointPair) obj;
                        if (!AnonymousClass1.this.isOrderingCompatible(endpointPair) || !AnonymousClass1.this.nodes().contains(endpointPair.nodeU()) || !AnonymousClass1.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV())) {
                            return false;
                        }
                        return true;
                    }
                };
            }

            public ElementOrder<N> nodeOrder() {
                return AbstractNetwork.this.nodeOrder();
            }

            public ElementOrder<N> incidentEdgeOrder() {
                return ElementOrder.unordered();
            }

            public boolean isDirected() {
                return AbstractNetwork.this.isDirected();
            }

            public boolean allowsSelfLoops() {
                return AbstractNetwork.this.allowsSelfLoops();
            }

            public Set<N> adjacentNodes(N n) {
                return AbstractNetwork.this.adjacentNodes(n);
            }

            public Set<N> predecessors(N n) {
                return AbstractNetwork.this.predecessors((Object) n);
            }

            public Set<N> successors(N n) {
                return AbstractNetwork.this.successors((Object) n);
            }
        };
    }

    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(inEdges(n).size(), outEdges(n).size());
        }
        return IntMath.saturatedAdd(incidentEdges(n).size(), edgesConnecting(n, n).size());
    }

    public int inDegree(N n) {
        return isDirected() ? inEdges(n).size() : degree(n);
    }

    public int outDegree(N n) {
        return isDirected() ? outEdges(n).size() : degree(n);
    }

    public Set<E> adjacentEdges(E e) {
        EndpointPair incidentNodes = incidentNodes(e);
        return edgeInvalidatableSet(Sets.difference(Sets.union(incidentEdges(incidentNodes.nodeU()), incidentEdges(incidentNodes.nodeV())), ImmutableSet.of(e)), e);
    }

    public Set<E> edgesConnecting(N n, N n2) {
        Set set;
        Set outEdges = outEdges(n);
        Set inEdges = inEdges(n2);
        if (outEdges.size() <= inEdges.size()) {
            set = Collections.unmodifiableSet(Sets.filter(outEdges, connectedPredicate(n, n2)));
        } else {
            set = Collections.unmodifiableSet(Sets.filter(inEdges, connectedPredicate(n2, n)));
        }
        return nodePairInvalidatableSet(set, n, n2);
    }

    public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgesConnecting(endpointPair.nodeU(), endpointPair.nodeV());
    }

    private Predicate<E> connectedPredicate(final N n, final N n2) {
        return new Predicate<E>(this) {
            final /* synthetic */ AbstractNetwork this$0;

            {
                this.this$0 = r1;
            }

            public boolean apply(E e) {
                return this.this$0.incidentNodes(e).adjacentNode(n).equals(n2);
            }
        };
    }

    @CheckForNull
    public E edgeConnectingOrNull(N n, N n2) {
        Set edgesConnecting = edgesConnecting(n, n2);
        int size = edgesConnecting.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return edgesConnecting.iterator().next();
        }
        throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", new Object[]{n, n2}));
    }

    @CheckForNull
    public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgeConnectingOrNull(endpointPair.nodeU(), endpointPair.nodeV());
    }

    public boolean hasEdgeConnecting(N n, N n2) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(n2);
        return nodes().contains(n) && successors((Object) n).contains(n2);
    }

    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        if (!isOrderingCompatible(endpointPair)) {
            return false;
        }
        return hasEdgeConnecting(endpointPair.nodeU(), endpointPair.nodeV());
    }

    /* access modifiers changed from: protected */
    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), "Mismatch: endpoints' ordering is not compatible with directionality of the graph");
    }

    /* access modifiers changed from: protected */
    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() == isDirected();
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        if (isDirected() != network.isDirected() || !nodes().equals(network.nodes()) || !edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(network))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return edgeIncidentNodesMap(this).hashCode();
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsParallelEdges: " + allowsParallelEdges() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeIncidentNodesMap(this);
    }

    /* access modifiers changed from: protected */
    public final <T> Set<T> edgeInvalidatableSet(Set<T> set, E e) {
        return InvalidatableSet.of(set, new AbstractNetwork$$ExternalSyntheticLambda3(this, e), new AbstractNetwork$$ExternalSyntheticLambda4(e));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$edgeInvalidatableSet$0$com-google-common-graph-AbstractNetwork  reason: not valid java name */
    public /* synthetic */ Boolean m549lambda$edgeInvalidatableSet$0$comgooglecommongraphAbstractNetwork(Object obj) {
        return Boolean.valueOf(edges().contains(obj));
    }

    /* access modifiers changed from: protected */
    public final <T> Set<T> nodeInvalidatableSet(Set<T> set, N n) {
        return InvalidatableSet.of(set, new AbstractNetwork$$ExternalSyntheticLambda0(this, n), new AbstractNetwork$$ExternalSyntheticLambda1(n));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$nodeInvalidatableSet$2$com-google-common-graph-AbstractNetwork  reason: not valid java name */
    public /* synthetic */ Boolean m550lambda$nodeInvalidatableSet$2$comgooglecommongraphAbstractNetwork(Object obj) {
        return Boolean.valueOf(nodes().contains(obj));
    }

    /* access modifiers changed from: protected */
    public final <T> Set<T> nodePairInvalidatableSet(Set<T> set, N n, N n2) {
        return InvalidatableSet.of(set, new AbstractNetwork$$ExternalSyntheticLambda5(this, n, n2), new AbstractNetwork$$ExternalSyntheticLambda6(n, n2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$nodePairInvalidatableSet$4$com-google-common-graph-AbstractNetwork  reason: not valid java name */
    public /* synthetic */ Boolean m551lambda$nodePairInvalidatableSet$4$comgooglecommongraphAbstractNetwork(Object obj, Object obj2) {
        return Boolean.valueOf(nodes().contains(obj) && nodes().contains(obj2));
    }

    private static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(Network<N, E> network) {
        Set<E> edges = network.edges();
        Objects.requireNonNull(network);
        return Maps.asMap(edges, new AbstractNetwork$$ExternalSyntheticLambda2(network));
    }
}
