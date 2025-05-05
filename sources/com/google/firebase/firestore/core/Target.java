package com.google.firebase.firestore.core;

import android.util.Pair;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public final class Target {
    public static final long NO_LIMIT = -1;
    private final String collectionGroup;
    private final Bound endAt;
    private final List<Filter> filters;
    private final long limit;
    private String memoizedCanonicalId;
    private final List<OrderBy> orderBys;
    private final ResourcePath path;
    private final Bound startAt;

    public Target(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.orderBys = list2;
        this.filters = list;
        this.limit = j;
        this.startAt = bound;
        this.endAt = bound2;
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

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    private List<FieldFilter> getFieldFiltersForPath(FieldPath fieldPath) {
        ArrayList arrayList = new ArrayList();
        for (Filter next : this.filters) {
            if (next instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) next;
                if (fieldFilter.getField().equals(fieldPath)) {
                    arrayList.add(fieldFilter);
                }
            }
        }
        return arrayList;
    }

    public List<Value> getArrayValues(FieldIndex fieldIndex) {
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment == null) {
            return null;
        }
        for (FieldFilter next : getFieldFiltersForPath(arraySegment.getFieldPath())) {
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[next.getOperator().ordinal()];
            if (i == 1) {
                return next.getValue().getArrayValue().getValuesList();
            }
            if (i == 2) {
                return Collections.singletonList(next.getValue());
            }
        }
        return null;
    }

    public Collection<Value> getNotInValues(FieldIndex fieldIndex) {
        FieldFilter next;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        loop0:
        for (FieldIndex.Segment next2 : fieldIndex.getDirectionalSegments()) {
            Iterator<FieldFilter> it = getFieldFiltersForPath(next2.getFieldPath()).iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[next.getOperator().ordinal()];
                    if (i == 3 || i == 4) {
                        linkedHashMap.put(next2.getFieldPath(), next.getValue());
                    } else if (i == 5 || i == 6) {
                        linkedHashMap.put(next2.getFieldPath(), next.getValue());
                    }
                }
            }
            linkedHashMap.put(next2.getFieldPath(), next.getValue());
            return linkedHashMap.values();
        }
        return null;
    }

    public Bound getLowerBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> pair;
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (FieldIndex.Segment next : fieldIndex.getDirectionalSegments()) {
            if (next.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                pair = getAscendingBound(next, this.startAt);
            } else {
                pair = getDescendingBound(next, this.startAt);
            }
            arrayList.add((Value) pair.first);
            z &= ((Boolean) pair.second).booleanValue();
        }
        return new Bound(arrayList, z);
    }

    public Bound getUpperBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> pair;
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (FieldIndex.Segment next : fieldIndex.getDirectionalSegments()) {
            if (next.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                pair = getDescendingBound(next, this.endAt);
            } else {
                pair = getAscendingBound(next, this.endAt);
            }
            arrayList.add((Value) pair.first);
            z &= ((Boolean) pair.second).booleanValue();
        }
        return new Bound(arrayList, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [int] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<com.google.firestore.v1.Value, java.lang.Boolean> getAscendingBound(com.google.firebase.firestore.model.FieldIndex.Segment r10, com.google.firebase.firestore.core.Bound r11) {
        /*
            r9 = this;
            com.google.firestore.v1.Value r0 = com.google.firebase.firestore.model.Values.MIN_VALUE
            com.google.firebase.firestore.model.FieldPath r1 = r10.getFieldPath()
            java.util.List r1 = r9.getFieldFiltersForPath(r1)
            java.util.Iterator r1 = r1.iterator()
            r2 = 1
            r3 = r2
        L_0x0010:
            boolean r4 = r1.hasNext()
            r5 = 0
            if (r4 == 0) goto L_0x004f
            java.lang.Object r4 = r1.next()
            com.google.firebase.firestore.core.FieldFilter r4 = (com.google.firebase.firestore.core.FieldFilter) r4
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.MIN_VALUE
            int[] r7 = com.google.firebase.firestore.core.Target.AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator
            com.google.firebase.firestore.core.FieldFilter$Operator r8 = r4.getOperator()
            int r8 = r8.ordinal()
            r7 = r7[r8]
            switch(r7) {
                case 3: goto L_0x0041;
                case 4: goto L_0x0041;
                case 5: goto L_0x003e;
                case 6: goto L_0x003e;
                case 7: goto L_0x0035;
                case 8: goto L_0x0035;
                case 9: goto L_0x0041;
                case 10: goto L_0x0030;
                default: goto L_0x002e;
            }
        L_0x002e:
            r5 = r2
            goto L_0x0046
        L_0x0030:
            com.google.firestore.v1.Value r6 = r4.getValue()
            goto L_0x0046
        L_0x0035:
            com.google.firestore.v1.Value r4 = r4.getValue()
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.getLowerBound(r4)
            goto L_0x002e
        L_0x003e:
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.MIN_VALUE
            goto L_0x002e
        L_0x0041:
            com.google.firestore.v1.Value r6 = r4.getValue()
            goto L_0x002e
        L_0x0046:
            int r4 = com.google.firebase.firestore.model.Values.lowerBoundCompare(r0, r3, r6, r5)
            if (r4 >= 0) goto L_0x0010
            r3 = r5
            r0 = r6
            goto L_0x0010
        L_0x004f:
            if (r11 == 0) goto L_0x008c
        L_0x0051:
            java.util.List<com.google.firebase.firestore.core.OrderBy> r1 = r9.orderBys
            int r1 = r1.size()
            if (r5 >= r1) goto L_0x008c
            java.util.List<com.google.firebase.firestore.core.OrderBy> r1 = r9.orderBys
            java.lang.Object r1 = r1.get(r5)
            com.google.firebase.firestore.core.OrderBy r1 = (com.google.firebase.firestore.core.OrderBy) r1
            com.google.firebase.firestore.model.FieldPath r1 = r1.getField()
            com.google.firebase.firestore.model.FieldPath r2 = r10.getFieldPath()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0089
            java.util.List r10 = r11.getPosition()
            java.lang.Object r10 = r10.get(r5)
            com.google.firestore.v1.Value r10 = (com.google.firestore.v1.Value) r10
            boolean r1 = r11.isInclusive()
            int r1 = com.google.firebase.firestore.model.Values.lowerBoundCompare(r0, r3, r10, r1)
            if (r1 >= 0) goto L_0x008c
            boolean r3 = r11.isInclusive()
            r0 = r10
            goto L_0x008c
        L_0x0089:
            int r5 = r5 + 1
            goto L_0x0051
        L_0x008c:
            android.util.Pair r10 = new android.util.Pair
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r3)
            r10.<init>(r0, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.Target.getAscendingBound(com.google.firebase.firestore.model.FieldIndex$Segment, com.google.firebase.firestore.core.Bound):android.util.Pair");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [int] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<com.google.firestore.v1.Value, java.lang.Boolean> getDescendingBound(com.google.firebase.firestore.model.FieldIndex.Segment r10, com.google.firebase.firestore.core.Bound r11) {
        /*
            r9 = this;
            com.google.firestore.v1.Value r0 = com.google.firebase.firestore.model.Values.MAX_VALUE
            com.google.firebase.firestore.model.FieldPath r1 = r10.getFieldPath()
            java.util.List r1 = r9.getFieldFiltersForPath(r1)
            java.util.Iterator r1 = r1.iterator()
            r2 = 1
            r3 = r2
        L_0x0010:
            boolean r4 = r1.hasNext()
            r5 = 0
            if (r4 == 0) goto L_0x004f
            java.lang.Object r4 = r1.next()
            com.google.firebase.firestore.core.FieldFilter r4 = (com.google.firebase.firestore.core.FieldFilter) r4
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.MAX_VALUE
            int[] r7 = com.google.firebase.firestore.core.Target.AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator
            com.google.firebase.firestore.core.FieldFilter$Operator r8 = r4.getOperator()
            int r8 = r8.ordinal()
            r7 = r7[r8]
            switch(r7) {
                case 3: goto L_0x0041;
                case 4: goto L_0x0041;
                case 5: goto L_0x003e;
                case 6: goto L_0x003e;
                case 7: goto L_0x0039;
                case 8: goto L_0x0041;
                case 9: goto L_0x0030;
                case 10: goto L_0x0030;
                default: goto L_0x002e;
            }
        L_0x002e:
            r5 = r2
            goto L_0x0046
        L_0x0030:
            com.google.firestore.v1.Value r4 = r4.getValue()
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.getUpperBound(r4)
            goto L_0x0046
        L_0x0039:
            com.google.firestore.v1.Value r6 = r4.getValue()
            goto L_0x0046
        L_0x003e:
            com.google.firestore.v1.Value r6 = com.google.firebase.firestore.model.Values.MAX_VALUE
            goto L_0x002e
        L_0x0041:
            com.google.firestore.v1.Value r6 = r4.getValue()
            goto L_0x002e
        L_0x0046:
            int r4 = com.google.firebase.firestore.model.Values.upperBoundCompare(r0, r3, r6, r5)
            if (r4 <= 0) goto L_0x0010
            r3 = r5
            r0 = r6
            goto L_0x0010
        L_0x004f:
            if (r11 == 0) goto L_0x008c
        L_0x0051:
            java.util.List<com.google.firebase.firestore.core.OrderBy> r1 = r9.orderBys
            int r1 = r1.size()
            if (r5 >= r1) goto L_0x008c
            java.util.List<com.google.firebase.firestore.core.OrderBy> r1 = r9.orderBys
            java.lang.Object r1 = r1.get(r5)
            com.google.firebase.firestore.core.OrderBy r1 = (com.google.firebase.firestore.core.OrderBy) r1
            com.google.firebase.firestore.model.FieldPath r1 = r1.getField()
            com.google.firebase.firestore.model.FieldPath r2 = r10.getFieldPath()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0089
            java.util.List r10 = r11.getPosition()
            java.lang.Object r10 = r10.get(r5)
            com.google.firestore.v1.Value r10 = (com.google.firestore.v1.Value) r10
            boolean r1 = r11.isInclusive()
            int r1 = com.google.firebase.firestore.model.Values.upperBoundCompare(r0, r3, r10, r1)
            if (r1 <= 0) goto L_0x008c
            boolean r3 = r11.isInclusive()
            r0 = r10
            goto L_0x008c
        L_0x0089:
            int r5 = r5 + 1
            goto L_0x0051
        L_0x008c:
            android.util.Pair r10 = new android.util.Pair
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r3)
            r10.<init>(r0, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.Target.getDescendingBound(com.google.firebase.firestore.model.FieldIndex$Segment, com.google.firebase.firestore.core.Bound):android.util.Pair");
    }

    public List<OrderBy> getOrderBy() {
        return this.orderBys;
    }

    public OrderBy.Direction getKeyOrder() {
        List<OrderBy> list = this.orderBys;
        return list.get(list.size() - 1).getDirection();
    }

    public int getSegmentCount() {
        HashSet hashSet = new HashSet();
        int i = 0;
        for (Filter flattenedFilters : this.filters) {
            for (FieldFilter next : flattenedFilters.getFlattenedFilters()) {
                if (!next.getField().isKeyField()) {
                    if (next.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || next.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY)) {
                        i = 1;
                    } else {
                        hashSet.add(next.getField());
                    }
                }
            }
        }
        for (OrderBy next2 : this.orderBys) {
            if (!next2.getField().isKeyField()) {
                hashSet.add(next2.getField());
            }
        }
        return hashSet.size() + i;
    }

    public String getCanonicalId() {
        String str;
        String str2 = this.memoizedCanonicalId;
        if (str2 != null) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getPath().canonicalString());
        if (this.collectionGroup != null) {
            sb.append("|cg:");
            sb.append(this.collectionGroup);
        }
        sb.append("|f:");
        for (Filter canonicalId : getFilters()) {
            sb.append(canonicalId.getCanonicalId());
        }
        sb.append("|ob:");
        for (OrderBy next : getOrderBy()) {
            sb.append(next.getField().canonicalString());
            sb.append(next.getDirection().equals(OrderBy.Direction.ASCENDING) ? "asc" : "desc");
        }
        if (hasLimit()) {
            sb.append("|l:");
            sb.append(getLimit());
        }
        String str3 = "b:";
        if (this.startAt != null) {
            sb.append("|lb:");
            if (this.startAt.isInclusive()) {
                str = str3;
            } else {
                str = "a:";
            }
            sb.append(str);
            sb.append(this.startAt.positionString());
        }
        if (this.endAt != null) {
            sb.append("|ub:");
            if (this.endAt.isInclusive()) {
                str3 = "a:";
            }
            sb.append(str3);
            sb.append(this.endAt.positionString());
        }
        String sb2 = sb.toString();
        this.memoizedCanonicalId = sb2;
        return sb2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Target target = (Target) obj;
        String str = this.collectionGroup;
        if (str == null ? target.collectionGroup != null : !str.equals(target.collectionGroup)) {
            return false;
        }
        if (this.limit != target.limit || !this.orderBys.equals(target.orderBys) || !this.filters.equals(target.filters) || !this.path.equals(target.path)) {
            return false;
        }
        Bound bound = this.startAt;
        if (bound == null ? target.startAt != null : !bound.equals(target.startAt)) {
            return false;
        }
        Bound bound2 = this.endAt;
        Bound bound3 = target.endAt;
        if (bound2 != null) {
            return bound2.equals(bound3);
        }
        if (bound3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.orderBys.hashCode() * 31;
        String str = this.collectionGroup;
        int i = 0;
        int hashCode2 = str != null ? str.hashCode() : 0;
        long j = this.limit;
        int hashCode3 = (((((((hashCode + hashCode2) * 31) + this.filters.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Bound bound = this.startAt;
        int hashCode4 = (hashCode3 + (bound != null ? bound.hashCode() : 0)) * 31;
        Bound bound2 = this.endAt;
        if (bound2 != null) {
            i = bound2.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Query(");
        sb.append(this.path.canonicalString());
        if (this.collectionGroup != null) {
            sb.append(" collectionGroup=");
            sb.append(this.collectionGroup);
        }
        if (!this.filters.isEmpty()) {
            sb.append(" where ");
            for (int i = 0; i < this.filters.size(); i++) {
                if (i > 0) {
                    sb.append(" and ");
                }
                sb.append(this.filters.get(i));
            }
        }
        if (!this.orderBys.isEmpty()) {
            sb.append(" order by ");
            for (int i2 = 0; i2 < this.orderBys.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(this.orderBys.get(i2));
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
