package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public final class Query {
    private static final OrderBy KEY_ORDERING_ASC = OrderBy.getInstance(OrderBy.Direction.ASCENDING, FieldPath.KEY_PATH);
    private static final OrderBy KEY_ORDERING_DESC = OrderBy.getInstance(OrderBy.Direction.DESCENDING, FieldPath.KEY_PATH);
    private final String collectionGroup;
    private final Bound endAt;
    private final List<OrderBy> explicitSortOrder;
    private final List<Filter> filters;
    private final long limit;
    private final LimitType limitType;
    private Target memoizedAggregateTarget;
    private List<OrderBy> memoizedNormalizedOrderBys;
    private Target memoizedTarget;
    private final ResourcePath path;
    private final Bound startAt;

    public enum LimitType {
        LIMIT_TO_FIRST,
        LIMIT_TO_LAST
    }

    public static Query atPath(ResourcePath resourcePath) {
        return new Query(resourcePath, (String) null);
    }

    public Query(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, LimitType limitType2, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.explicitSortOrder = list2;
        this.filters = list;
        this.limit = j;
        this.limitType = limitType2;
        this.startAt = bound;
        this.endAt = bound2;
    }

    public Query(ResourcePath resourcePath, String str) {
        this(resourcePath, str, Collections.emptyList(), Collections.emptyList(), -1, LimitType.LIMIT_TO_FIRST, (Bound) null, (Bound) null);
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public boolean isCollectionGroupQuery() {
        return this.collectionGroup != null;
    }

    public boolean matchesAllDocuments() {
        if (!this.filters.isEmpty() || this.limit != -1 || this.startAt != null || this.endAt != null) {
            return false;
        }
        if (getExplicitOrderBy().isEmpty() || (getExplicitOrderBy().size() == 1 && getExplicitOrderBy().get(0).field.isKeyField())) {
            return true;
        }
        return false;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public LimitType getLimitType() {
        return this.limitType;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    public SortedSet<FieldPath> getInequalityFilterFields() {
        TreeSet treeSet = new TreeSet();
        for (Filter flattenedFilters : getFilters()) {
            for (FieldFilter next : flattenedFilters.getFlattenedFilters()) {
                if (next.isInequality()) {
                    treeSet.add(next.getField());
                }
            }
        }
        return treeSet;
    }

    public Query filter(Filter filter) {
        Assert.hardAssert(!isDocumentQuery(), "No filter is allowed for document query", new Object[0]);
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.add(filter);
        return new Query(this.path, this.collectionGroup, arrayList, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query orderBy(OrderBy orderBy) {
        Assert.hardAssert(!isDocumentQuery(), "No ordering is allowed for document query", new Object[0]);
        ArrayList arrayList = new ArrayList(this.explicitSortOrder);
        arrayList.add(orderBy);
        return new Query(this.path, this.collectionGroup, this.filters, arrayList, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query limitToFirst(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_FIRST, this.startAt, this.endAt);
    }

    public Query limitToLast(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_LAST, this.startAt, this.endAt);
    }

    public Query startAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, bound, this.endAt);
    }

    public Query endAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, bound);
    }

    public Query asCollectionQueryAtPath(ResourcePath resourcePath) {
        return new Query(resourcePath, (String) null, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public List<OrderBy> getExplicitOrderBy() {
        return this.explicitSortOrder;
    }

    public synchronized List<OrderBy> getNormalizedOrderBy() {
        OrderBy.Direction direction;
        if (this.memoizedNormalizedOrderBys == null) {
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            for (OrderBy next : this.explicitSortOrder) {
                arrayList.add(next);
                hashSet.add(next.field.canonicalString());
            }
            if (this.explicitSortOrder.size() > 0) {
                List<OrderBy> list = this.explicitSortOrder;
                direction = list.get(list.size() - 1).getDirection();
            } else {
                direction = OrderBy.Direction.ASCENDING;
            }
            for (FieldPath fieldPath : getInequalityFilterFields()) {
                if (!hashSet.contains(fieldPath.canonicalString()) && !fieldPath.isKeyField()) {
                    arrayList.add(OrderBy.getInstance(direction, fieldPath));
                }
            }
            if (!hashSet.contains(FieldPath.KEY_PATH.canonicalString())) {
                arrayList.add(direction.equals(OrderBy.Direction.ASCENDING) ? KEY_ORDERING_ASC : KEY_ORDERING_DESC);
            }
            this.memoizedNormalizedOrderBys = Collections.unmodifiableList(arrayList);
        }
        return this.memoizedNormalizedOrderBys;
    }

    private boolean matchesPathAndCollectionGroup(Document document) {
        ResourcePath path2 = document.getKey().getPath();
        if (this.collectionGroup != null) {
            if (!document.getKey().hasCollectionId(this.collectionGroup) || !this.path.isPrefixOf(path2)) {
                return false;
            }
            return true;
        } else if (DocumentKey.isDocumentKey(this.path)) {
            return this.path.equals(path2);
        } else {
            if (!this.path.isPrefixOf(path2) || this.path.length() != path2.length() - 1) {
                return false;
            }
            return true;
        }
    }

    private boolean matchesFilters(Document document) {
        for (Filter matches : this.filters) {
            if (!matches.matches(document)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesOrderBy(Document document) {
        for (OrderBy next : getNormalizedOrderBy()) {
            if (!next.getField().equals(FieldPath.KEY_PATH) && document.getField(next.field) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesBounds(Document document) {
        Bound bound = this.startAt;
        if (bound != null && !bound.sortsBeforeDocument(getNormalizedOrderBy(), document)) {
            return false;
        }
        Bound bound2 = this.endAt;
        if (bound2 == null || bound2.sortsAfterDocument(getNormalizedOrderBy(), document)) {
            return true;
        }
        return false;
    }

    public boolean matches(Document document) {
        return document.isFoundDocument() && matchesPathAndCollectionGroup(document) && matchesOrderBy(document) && matchesFilters(document) && matchesBounds(document);
    }

    public Comparator<Document> comparator() {
        return new QueryComparator(getNormalizedOrderBy());
    }

    private static class QueryComparator implements Comparator<Document> {
        private final List<OrderBy> sortOrder;

        QueryComparator(List<OrderBy> list) {
            boolean z;
            Iterator<OrderBy> it = list.iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    OrderBy next = it.next();
                    if (z || next.getField().equals(FieldPath.KEY_PATH)) {
                        z = true;
                    }
                }
            }
            if (z) {
                this.sortOrder = list;
                return;
            }
            throw new IllegalArgumentException("QueryComparator needs to have a key ordering");
        }

        public int compare(Document document, Document document2) {
            for (OrderBy compare : this.sortOrder) {
                int compare2 = compare.compare(document, document2);
                if (compare2 != 0) {
                    return compare2;
                }
            }
            return 0;
        }
    }

    public synchronized Target toTarget() {
        if (this.memoizedTarget == null) {
            this.memoizedTarget = toTarget(getNormalizedOrderBy());
        }
        return this.memoizedTarget;
    }

    private synchronized Target toTarget(List<OrderBy> list) {
        OrderBy.Direction direction;
        if (this.limitType == LimitType.LIMIT_TO_FIRST) {
            return new Target(getPath(), getCollectionGroup(), getFilters(), list, this.limit, getStartAt(), getEndAt());
        }
        ArrayList arrayList = new ArrayList();
        for (OrderBy next : list) {
            if (next.getDirection() == OrderBy.Direction.DESCENDING) {
                direction = OrderBy.Direction.ASCENDING;
            } else {
                direction = OrderBy.Direction.DESCENDING;
            }
            arrayList.add(OrderBy.getInstance(direction, next.getField()));
        }
        return new Target(getPath(), getCollectionGroup(), getFilters(), arrayList, this.limit, this.endAt != null ? new Bound(this.endAt.getPosition(), this.endAt.isInclusive()) : null, this.startAt != null ? new Bound(this.startAt.getPosition(), this.startAt.isInclusive()) : null);
    }

    public synchronized Target toAggregateTarget() {
        if (this.memoizedAggregateTarget == null) {
            this.memoizedAggregateTarget = toTarget(this.explicitSortOrder);
        }
        return this.memoizedAggregateTarget;
    }

    public String getCanonicalId() {
        return toTarget().getCanonicalId() + "|lt:" + this.limitType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.limitType != query.limitType) {
            return false;
        }
        return toTarget().equals(query.toTarget());
    }

    public int hashCode() {
        return (toTarget().hashCode() * 31) + this.limitType.hashCode();
    }

    public String toString() {
        return "Query(target=" + toTarget().toString() + ";limitType=" + this.limitType.toString() + ")";
    }
}
