package com.google.firebase.firestore.model;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.FieldIndex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class TargetIndexMatcher {
    private final String collectionId;
    private final List<FieldFilter> equalityFilters;
    private final SortedSet<FieldFilter> inequalityFilters;
    private final List<OrderBy> orderBys;

    public TargetIndexMatcher(Target target) {
        String str;
        if (target.getCollectionGroup() != null) {
            str = target.getCollectionGroup();
        } else {
            str = target.getPath().getLastSegment();
        }
        this.collectionId = str;
        this.orderBys = target.getOrderBy();
        this.inequalityFilters = new TreeSet(new TargetIndexMatcher$$ExternalSyntheticLambda0());
        this.equalityFilters = new ArrayList();
        Iterator<Filter> it = target.getFilters().iterator();
        while (it.hasNext()) {
            FieldFilter fieldFilter = (FieldFilter) it.next();
            if (fieldFilter.isInequality()) {
                this.inequalityFilters.add(fieldFilter);
            } else {
                this.equalityFilters.add(fieldFilter);
            }
        }
    }

    public boolean hasMultipleInequality() {
        return this.inequalityFilters.size() > 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean servedByIndex(com.google.firebase.firestore.model.FieldIndex r8) {
        /*
            r7 = this;
            java.lang.String r0 = r8.getCollectionGroup()
            java.lang.String r1 = r7.collectionId
            boolean r0 = r0.equals(r1)
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Collection IDs do not match"
            com.google.firebase.firestore.util.Assert.hardAssert(r0, r3, r2)
            boolean r0 = r7.hasMultipleInequality()
            if (r0 == 0) goto L_0x0019
            return r1
        L_0x0019:
            com.google.firebase.firestore.model.FieldIndex$Segment r0 = r8.getArraySegment()
            if (r0 == 0) goto L_0x0026
            boolean r0 = r7.hasMatchingEqualityFilter(r0)
            if (r0 != 0) goto L_0x0026
            return r1
        L_0x0026:
            java.util.List<com.google.firebase.firestore.core.OrderBy> r0 = r7.orderBys
            java.util.Iterator r0 = r0.iterator()
            java.util.List r8 = r8.getDirectionalSegments()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r3 = r1
        L_0x0036:
            int r4 = r8.size()
            if (r3 >= r4) goto L_0x005c
            java.lang.Object r4 = r8.get(r3)
            com.google.firebase.firestore.model.FieldIndex$Segment r4 = (com.google.firebase.firestore.model.FieldIndex.Segment) r4
            boolean r4 = r7.hasMatchingEqualityFilter(r4)
            if (r4 == 0) goto L_0x005c
            java.lang.Object r4 = r8.get(r3)
            com.google.firebase.firestore.model.FieldIndex$Segment r4 = (com.google.firebase.firestore.model.FieldIndex.Segment) r4
            com.google.firebase.firestore.model.FieldPath r4 = r4.getFieldPath()
            java.lang.String r4 = r4.canonicalString()
            r2.add(r4)
            int r3 = r3 + 1
            goto L_0x0036
        L_0x005c:
            int r4 = r8.size()
            r5 = 1
            if (r3 != r4) goto L_0x0064
            return r5
        L_0x0064:
            java.util.SortedSet<com.google.firebase.firestore.core.FieldFilter> r4 = r7.inequalityFilters
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x009d
            java.util.SortedSet<com.google.firebase.firestore.core.FieldFilter> r4 = r7.inequalityFilters
            java.lang.Object r4 = r4.first()
            com.google.firebase.firestore.core.FieldFilter r4 = (com.google.firebase.firestore.core.FieldFilter) r4
            com.google.firebase.firestore.model.FieldPath r6 = r4.getField()
            java.lang.String r6 = r6.canonicalString()
            boolean r2 = r2.contains(r6)
            if (r2 != 0) goto L_0x009b
            java.lang.Object r2 = r8.get(r3)
            com.google.firebase.firestore.model.FieldIndex$Segment r2 = (com.google.firebase.firestore.model.FieldIndex.Segment) r2
            boolean r4 = r7.matchesFilter(r4, r2)
            if (r4 == 0) goto L_0x009a
            java.lang.Object r4 = r0.next()
            com.google.firebase.firestore.core.OrderBy r4 = (com.google.firebase.firestore.core.OrderBy) r4
            boolean r2 = r7.matchesOrderBy(r4, r2)
            if (r2 != 0) goto L_0x009b
        L_0x009a:
            return r1
        L_0x009b:
            int r3 = r3 + 1
        L_0x009d:
            int r2 = r8.size()
            if (r3 >= r2) goto L_0x00bc
            java.lang.Object r2 = r8.get(r3)
            com.google.firebase.firestore.model.FieldIndex$Segment r2 = (com.google.firebase.firestore.model.FieldIndex.Segment) r2
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x00bb
            java.lang.Object r4 = r0.next()
            com.google.firebase.firestore.core.OrderBy r4 = (com.google.firebase.firestore.core.OrderBy) r4
            boolean r2 = r7.matchesOrderBy(r4, r2)
            if (r2 != 0) goto L_0x009b
        L_0x00bb:
            return r1
        L_0x00bc:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.model.TargetIndexMatcher.servedByIndex(com.google.firebase.firestore.model.FieldIndex):boolean");
    }

    public FieldIndex buildTargetIndex() {
        FieldIndex.Segment.Kind kind;
        if (hasMultipleInequality()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (FieldFilter next : this.equalityFilters) {
            if (!next.getField().isKeyField()) {
                if (next.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || next.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY)) {
                    arrayList.add(FieldIndex.Segment.create(next.getField(), FieldIndex.Segment.Kind.CONTAINS));
                } else if (!hashSet.contains(next.getField())) {
                    hashSet.add(next.getField());
                    arrayList.add(FieldIndex.Segment.create(next.getField(), FieldIndex.Segment.Kind.ASCENDING));
                }
            }
        }
        for (OrderBy next2 : this.orderBys) {
            if (!next2.getField().isKeyField() && !hashSet.contains(next2.getField())) {
                hashSet.add(next2.getField());
                FieldPath field = next2.getField();
                if (next2.getDirection() == OrderBy.Direction.ASCENDING) {
                    kind = FieldIndex.Segment.Kind.ASCENDING;
                } else {
                    kind = FieldIndex.Segment.Kind.DESCENDING;
                }
                arrayList.add(FieldIndex.Segment.create(field, kind));
            }
        }
        return FieldIndex.create(-1, this.collectionId, arrayList, FieldIndex.INITIAL_STATE);
    }

    private boolean hasMatchingEqualityFilter(FieldIndex.Segment segment) {
        for (FieldFilter matchesFilter : this.equalityFilters) {
            if (matchesFilter(matchesFilter, segment)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesFilter(FieldFilter fieldFilter, FieldIndex.Segment segment) {
        if (fieldFilter == null || !fieldFilter.getField().equals(segment.getFieldPath())) {
            return false;
        }
        if (segment.getKind().equals(FieldIndex.Segment.Kind.CONTAINS) == (fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY))) {
            return true;
        }
        return false;
    }

    private boolean matchesOrderBy(OrderBy orderBy, FieldIndex.Segment segment) {
        if (!orderBy.getField().equals(segment.getFieldPath())) {
            return false;
        }
        if ((!segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING) || !orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) && (!segment.getKind().equals(FieldIndex.Segment.Kind.DESCENDING) || !orderBy.getDirection().equals(OrderBy.Direction.DESCENDING))) {
            return false;
        }
        return true;
    }
}
