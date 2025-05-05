package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.DoNotMock;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@DoNotMock("Call forGraph or forTree, passing a lambda or a Graph with the desired edges (built with GraphBuilder)")
public abstract class Traverser<N> {
    private final SuccessorsFunction<N> successorFunction;

    private enum InsertionOrder {
        FRONT {
            /* access modifiers changed from: package-private */
            public <T> void insertInto(Deque<T> deque, T t) {
                deque.addFirst(t);
            }
        },
        BACK {
            /* access modifiers changed from: package-private */
            public <T> void insertInto(Deque<T> deque, T t) {
                deque.addLast(t);
            }
        };

        /* access modifiers changed from: package-private */
        public abstract <T> void insertInto(Deque<T> deque, T t);
    }

    /* access modifiers changed from: package-private */
    public abstract Traversal<N> newTraversal();

    private Traverser(SuccessorsFunction<N> successorsFunction) {
        this.successorFunction = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
    }

    public static <N> Traverser<N> forGraph(final SuccessorsFunction<N> successorsFunction) {
        return new Traverser<N>(successorsFunction) {
            /* access modifiers changed from: package-private */
            public Traversal<N> newTraversal() {
                return Traversal.inGraph(successorsFunction);
            }
        };
    }

    public static <N> Traverser<N> forTree(final SuccessorsFunction<N> successorsFunction) {
        if (successorsFunction instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) successorsFunction).isDirected(), "Undirected graphs can never be trees.");
        }
        if (successorsFunction instanceof Network) {
            Preconditions.checkArgument(((Network) successorsFunction).isDirected(), "Undirected networks can never be trees.");
        }
        return new Traverser<N>(successorsFunction) {
            /* access modifiers changed from: package-private */
            public Traversal<N> newTraversal() {
                return Traversal.inTree(successorsFunction);
            }
        };
    }

    public final Iterable<N> breadthFirst(N n) {
        return breadthFirst(ImmutableSet.of(n));
    }

    public final Iterable<N> breadthFirst(Iterable<? extends N> iterable) {
        final ImmutableSet<N> validate = validate(iterable);
        return new Iterable<N>(this) {
            final /* synthetic */ Traverser this$0;

            {
                this.this$0 = r1;
            }

            public Iterator<N> iterator() {
                return this.this$0.newTraversal().breadthFirst(validate.iterator());
            }
        };
    }

    public final Iterable<N> depthFirstPreOrder(N n) {
        return depthFirstPreOrder(ImmutableSet.of(n));
    }

    public final Iterable<N> depthFirstPreOrder(Iterable<? extends N> iterable) {
        final ImmutableSet<N> validate = validate(iterable);
        return new Iterable<N>(this) {
            final /* synthetic */ Traverser this$0;

            {
                this.this$0 = r1;
            }

            public Iterator<N> iterator() {
                return this.this$0.newTraversal().preOrder(validate.iterator());
            }
        };
    }

    public final Iterable<N> depthFirstPostOrder(N n) {
        return depthFirstPostOrder(ImmutableSet.of(n));
    }

    public final Iterable<N> depthFirstPostOrder(Iterable<? extends N> iterable) {
        final ImmutableSet<N> validate = validate(iterable);
        return new Iterable<N>(this) {
            final /* synthetic */ Traverser this$0;

            {
                this.this$0 = r1;
            }

            public Iterator<N> iterator() {
                return this.this$0.newTraversal().postOrder(validate.iterator());
            }
        };
    }

    private ImmutableSet<N> validate(Iterable<? extends N> iterable) {
        ImmutableSet<N> copyOf = ImmutableSet.copyOf(iterable);
        UnmodifiableIterator<N> it = copyOf.iterator();
        while (it.hasNext()) {
            this.successorFunction.successors(it.next());
        }
        return copyOf;
    }

    private static abstract class Traversal<N> {
        final SuccessorsFunction<N> successorFunction;

        /* access modifiers changed from: package-private */
        @CheckForNull
        public abstract N visitNext(Deque<Iterator<? extends N>> deque);

        Traversal(SuccessorsFunction<N> successorsFunction) {
            this.successorFunction = successorsFunction;
        }

        static <N> Traversal<N> inGraph(SuccessorsFunction<N> successorsFunction) {
            final HashSet hashSet = new HashSet();
            return new Traversal<N>(successorsFunction) {
                /* access modifiers changed from: package-private */
                @CheckForNull
                public N visitNext(Deque<Iterator<? extends N>> deque) {
                    Iterator first = deque.getFirst();
                    while (first.hasNext()) {
                        N next = first.next();
                        Objects.requireNonNull(next);
                        if (hashSet.add(next)) {
                            return next;
                        }
                    }
                    deque.removeFirst();
                    return null;
                }
            };
        }

        static <N> Traversal<N> inTree(SuccessorsFunction<N> successorsFunction) {
            return new Traversal<N>(successorsFunction) {
                /* access modifiers changed from: package-private */
                @CheckForNull
                public N visitNext(Deque<Iterator<? extends N>> deque) {
                    Iterator first = deque.getFirst();
                    if (first.hasNext()) {
                        return Preconditions.checkNotNull(first.next());
                    }
                    deque.removeFirst();
                    return null;
                }
            };
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> breadthFirst(Iterator<? extends N> it) {
            return topDown(it, InsertionOrder.BACK);
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> preOrder(Iterator<? extends N> it) {
            return topDown(it, InsertionOrder.FRONT);
        }

        private Iterator<N> topDown(Iterator<? extends N> it, final InsertionOrder insertionOrder) {
            final ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.add(it);
            return new AbstractIterator<N>(this) {
                final /* synthetic */ Traversal this$0;

                {
                    this.this$0 = r1;
                }

                /* access modifiers changed from: protected */
                @CheckForNull
                public N computeNext() {
                    do {
                        N visitNext = this.this$0.visitNext(arrayDeque);
                        if (visitNext != null) {
                            Iterator<? extends N> it = this.this$0.successorFunction.successors(visitNext).iterator();
                            if (it.hasNext()) {
                                insertionOrder.insertInto(arrayDeque, it);
                            }
                            return visitNext;
                        }
                    } while (!arrayDeque.isEmpty());
                    return endOfData();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> postOrder(Iterator<? extends N> it) {
            final ArrayDeque arrayDeque = new ArrayDeque();
            final ArrayDeque arrayDeque2 = new ArrayDeque();
            arrayDeque2.add(it);
            return new AbstractIterator<N>(this) {
                final /* synthetic */ Traversal this$0;

                {
                    this.this$0 = r1;
                }

                /* access modifiers changed from: protected */
                @CheckForNull
                public N computeNext() {
                    while (true) {
                        N visitNext = this.this$0.visitNext(arrayDeque2);
                        if (visitNext != null) {
                            Iterator<? extends N> it = this.this$0.successorFunction.successors(visitNext).iterator();
                            if (!it.hasNext()) {
                                return visitNext;
                            }
                            arrayDeque2.addFirst(it);
                            arrayDeque.push(visitNext);
                        } else if (!arrayDeque.isEmpty()) {
                            return arrayDeque.pop();
                        } else {
                            return endOfData();
                        }
                    }
                }
            };
        }
    }
}
