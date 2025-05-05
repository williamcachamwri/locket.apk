package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.firebase.firestore.index.IndexByteEncoder;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.TargetIndexMatcher;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.LogicUtils;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.v1.Value;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

final class SQLiteIndexManager implements IndexManager {
    private static final byte[] EMPTY_BYTES_VALUE = new byte[0];
    private static final String TAG = "SQLiteIndexManager";
    private final MemoryIndexManager.MemoryCollectionParentIndex collectionParentsCache = new MemoryIndexManager.MemoryCollectionParentIndex();
    private final SQLitePersistence db;
    private final Map<String, Map<Integer, FieldIndex>> memoizedIndexes = new HashMap();
    private int memoizedMaxIndexId = -1;
    private long memoizedMaxSequenceNumber = -1;
    private final Queue<FieldIndex> nextIndexToUpdate = new PriorityQueue(10, new SQLiteIndexManager$$ExternalSyntheticLambda3());
    private final LocalSerializer serializer;
    private boolean started = false;
    private final Map<Target, List<Target>> targetToDnfSubTargets = new HashMap();
    private final String uid;

    static /* synthetic */ int lambda$new$0(FieldIndex fieldIndex, FieldIndex fieldIndex2) {
        int compare = Long.compare(fieldIndex.getIndexState().getSequenceNumber(), fieldIndex2.getIndexState().getSequenceNumber());
        return compare == 0 ? fieldIndex.getCollectionGroup().compareTo(fieldIndex2.getCollectionGroup()) : compare;
    }

    SQLiteIndexManager(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public void start() {
        HashMap hashMap = new HashMap();
        this.db.query("SELECT index_id, sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id FROM index_state WHERE uid = ?").binding(this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda6(hashMap));
        this.db.query("SELECT index_id, collection_group, index_proto FROM index_configuration").forEach(new SQLiteIndexManager$$ExternalSyntheticLambda7(this, hashMap));
        this.started = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$2$com-google-firebase-firestore-local-SQLiteIndexManager  reason: not valid java name */
    public /* synthetic */ void m706lambda$start$2$comgooglefirebasefirestorelocalSQLiteIndexManager(Map map, Cursor cursor) {
        FieldIndex.IndexState indexState;
        try {
            int i = cursor.getInt(0);
            String string = cursor.getString(1);
            List<FieldIndex.Segment> decodeFieldIndexSegments = this.serializer.decodeFieldIndexSegments(Index.parseFrom(cursor.getBlob(2)));
            if (map.containsKey(Integer.valueOf(i))) {
                indexState = (FieldIndex.IndexState) map.get(Integer.valueOf(i));
            } else {
                indexState = FieldIndex.INITIAL_STATE;
            }
            memoizeIndex(FieldIndex.create(i, string, decodeFieldIndexSegments, indexState));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Failed to decode index: " + e, new Object[0]);
        }
    }

    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        boolean z = true;
        if (resourcePath.length() % 2 != 1) {
            z = false;
        }
        Assert.hardAssert(z, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(resourcePath)) {
            this.db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", resourcePath.getLastSegment(), EncodedPath.encode((ResourcePath) resourcePath.popLast()));
        }
    }

    public List<ResourcePath> getCollectionParents(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        ArrayList arrayList = new ArrayList();
        this.db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(str).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda4(arrayList));
        return arrayList;
    }

    public void addFieldIndex(FieldIndex fieldIndex) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        int i = this.memoizedMaxIndexId + 1;
        FieldIndex create = FieldIndex.create(i, fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), fieldIndex.getIndexState());
        this.db.execute("INSERT INTO index_configuration (index_id, collection_group, index_proto) VALUES(?, ?, ?)", Integer.valueOf(i), create.getCollectionGroup(), encodeSegments(create));
        memoizeIndex(create);
    }

    public void deleteFieldIndex(FieldIndex fieldIndex) {
        this.db.execute("DELETE FROM index_configuration WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_entries WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_state WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.nextIndexToUpdate.remove(fieldIndex);
        Map map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map != null) {
            map.remove(Integer.valueOf(fieldIndex.getIndexId()));
        }
    }

    public void deleteAllFieldIndexes() {
        this.db.execute("DELETE FROM index_configuration", new Object[0]);
        this.db.execute("DELETE FROM index_entries", new Object[0]);
        this.db.execute("DELETE FROM index_state", new Object[0]);
        this.nextIndexToUpdate.clear();
        this.memoizedIndexes.clear();
    }

    public void createTargetIndexes(Target target) {
        FieldIndex buildTargetIndex;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        for (Target next : getSubTargets(target)) {
            IndexManager.IndexType indexType = getIndexType(next);
            if ((indexType == IndexManager.IndexType.NONE || indexType == IndexManager.IndexType.PARTIAL) && (buildTargetIndex = new TargetIndexMatcher(next).buildTargetIndex()) != null) {
                addFieldIndex(buildTargetIndex);
            }
        }
    }

    public String getNextCollectionGroupToUpdate() {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        FieldIndex peek = this.nextIndexToUpdate.peek();
        if (peek != null) {
            return peek.getCollectionGroup();
        }
        return null;
    }

    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            for (FieldIndex next2 : getFieldIndexes(((DocumentKey) next.getKey()).getCollectionGroup())) {
                SortedSet<IndexEntry> existingIndexEntries = getExistingIndexEntries((DocumentKey) next.getKey(), next2);
                SortedSet<IndexEntry> computeIndexEntries = computeIndexEntries((Document) next.getValue(), next2);
                if (!existingIndexEntries.equals(computeIndexEntries)) {
                    updateEntries((Document) next.getValue(), existingIndexEntries, computeIndexEntries);
                }
            }
        }
    }

    private void updateEntries(Document document, SortedSet<IndexEntry> sortedSet, SortedSet<IndexEntry> sortedSet2) {
        Logger.debug(TAG, "Updating index entries for document '%s'", document.getKey());
        Util.diffCollections(sortedSet, sortedSet2, new SQLiteIndexManager$$ExternalSyntheticLambda1(this, document), new SQLiteIndexManager$$ExternalSyntheticLambda2(this, document));
    }

    public Collection<FieldIndex> getFieldIndexes(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Map map = this.memoizedIndexes.get(str);
        return map == null ? Collections.emptyList() : map.values();
    }

    public Collection<FieldIndex> getFieldIndexes() {
        ArrayList arrayList = new ArrayList();
        for (Map<Integer, FieldIndex> values : this.memoizedIndexes.values()) {
            arrayList.addAll(values.values());
        }
        return arrayList;
    }

    private FieldIndex.IndexOffset getMinOffset(Collection<FieldIndex> collection) {
        Assert.hardAssert(!collection.isEmpty(), "Found empty index group when looking for least recent index offset.", new Object[0]);
        Iterator<FieldIndex> it = collection.iterator();
        FieldIndex.IndexOffset offset = it.next().getIndexState().getOffset();
        int largestBatchId = offset.getLargestBatchId();
        while (it.hasNext()) {
            FieldIndex.IndexOffset offset2 = it.next().getIndexState().getOffset();
            if (offset2.compareTo(offset) < 0) {
                offset = offset2;
            }
            largestBatchId = Math.max(offset2.getLargestBatchId(), largestBatchId);
        }
        return FieldIndex.IndexOffset.create(offset.getReadTime(), offset.getDocumentKey(), largestBatchId);
    }

    public FieldIndex.IndexOffset getMinOffset(String str) {
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(str);
        Assert.hardAssert(!fieldIndexes.isEmpty(), "minOffset was called for collection without indexes", new Object[0]);
        return getMinOffset(fieldIndexes);
    }

    public IndexManager.IndexType getIndexType(Target target) {
        IndexManager.IndexType indexType = IndexManager.IndexType.FULL;
        List<Target> subTargets = getSubTargets(target);
        Iterator<Target> it = subTargets.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Target next = it.next();
            FieldIndex fieldIndex = getFieldIndex(next);
            if (fieldIndex == null) {
                indexType = IndexManager.IndexType.NONE;
                break;
            } else if (fieldIndex.getSegments().size() < next.getSegmentCount()) {
                indexType = IndexManager.IndexType.PARTIAL;
            }
        }
        return (!target.hasLimit() || subTargets.size() <= 1 || indexType != IndexManager.IndexType.FULL) ? indexType : IndexManager.IndexType.PARTIAL;
    }

    public FieldIndex.IndexOffset getMinOffset(Target target) {
        ArrayList arrayList = new ArrayList();
        for (Target fieldIndex : getSubTargets(target)) {
            FieldIndex fieldIndex2 = getFieldIndex(fieldIndex);
            if (fieldIndex2 != null) {
                arrayList.add(fieldIndex2);
            }
        }
        return getMinOffset((Collection<FieldIndex>) arrayList);
    }

    private List<Target> getSubTargets(Target target) {
        if (this.targetToDnfSubTargets.containsKey(target)) {
            return this.targetToDnfSubTargets.get(target);
        }
        ArrayList arrayList = new ArrayList();
        if (target.getFilters().isEmpty()) {
            arrayList.add(target);
        } else {
            for (Filter filters : LogicUtils.getDnfTerms(new CompositeFilter(target.getFilters(), CompositeFilter.Operator.AND))) {
                arrayList.add(new Target(target.getPath(), target.getCollectionGroup(), filters.getFilters(), target.getOrderBy(), target.getLimit(), target.getStartAt(), target.getEndAt()));
            }
        }
        this.targetToDnfSubTargets.put(target, arrayList);
        return arrayList;
    }

    private void memoizeIndex(FieldIndex fieldIndex) {
        Map map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map == null) {
            map = new HashMap();
            this.memoizedIndexes.put(fieldIndex.getCollectionGroup(), map);
        }
        FieldIndex fieldIndex2 = (FieldIndex) map.get(Integer.valueOf(fieldIndex.getIndexId()));
        if (fieldIndex2 != null) {
            this.nextIndexToUpdate.remove(fieldIndex2);
        }
        map.put(Integer.valueOf(fieldIndex.getIndexId()), fieldIndex);
        this.nextIndexToUpdate.add(fieldIndex);
        this.memoizedMaxIndexId = Math.max(this.memoizedMaxIndexId, fieldIndex.getIndexId());
        this.memoizedMaxSequenceNumber = Math.max(this.memoizedMaxSequenceNumber, fieldIndex.getIndexState().getSequenceNumber());
    }

    private SortedSet<IndexEntry> computeIndexEntries(Document document, FieldIndex fieldIndex) {
        TreeSet treeSet = new TreeSet();
        byte[] encodeDirectionalElements = encodeDirectionalElements(fieldIndex, document);
        if (encodeDirectionalElements == null) {
            return treeSet;
        }
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null) {
            Value field = document.getField(arraySegment.getFieldPath());
            if (Values.isArray(field)) {
                for (Value encodeSingleElement : field.getArrayValue().getValuesList()) {
                    treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), encodeSingleElement(encodeSingleElement), encodeDirectionalElements));
                }
            }
        } else {
            treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), new byte[0], encodeDirectionalElements));
        }
        return treeSet;
    }

    /* access modifiers changed from: private */
    /* renamed from: addIndexEntry */
    public void m707lambda$updateEntries$4$comgooglefirebasefirestorelocalSQLiteIndexManager(Document document, IndexEntry indexEntry) {
        this.db.execute("INSERT INTO index_entries (index_id, uid, array_value, directional_value, document_key) VALUES(?, ?, ?, ?, ?)", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: deleteIndexEntry */
    public void m708lambda$updateEntries$5$comgooglefirebasefirestorelocalSQLiteIndexManager(Document document, IndexEntry indexEntry) {
        this.db.execute("DELETE FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value = ? AND document_key = ?", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    private SortedSet<IndexEntry> getExistingIndexEntries(DocumentKey documentKey, FieldIndex fieldIndex) {
        TreeSet treeSet = new TreeSet();
        this.db.query("SELECT array_value, directional_value FROM index_entries WHERE index_id = ? AND document_key = ? AND uid = ?").binding(Integer.valueOf(fieldIndex.getIndexId()), documentKey.toString(), this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda5(treeSet, fieldIndex, documentKey));
        return treeSet;
    }

    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        boolean z;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Target next : getSubTargets(target)) {
            FieldIndex fieldIndex = getFieldIndex(next);
            if (fieldIndex == null) {
                return null;
            }
            arrayList3.add(Pair.create(next, fieldIndex));
        }
        Iterator it = arrayList3.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            Pair pair = (Pair) it.next();
            Target target2 = (Target) pair.first;
            FieldIndex fieldIndex2 = (FieldIndex) pair.second;
            List<Value> arrayValues = target2.getArrayValues(fieldIndex2);
            Collection<Value> notInValues = target2.getNotInValues(fieldIndex2);
            Bound lowerBound = target2.getLowerBound(fieldIndex2);
            Bound upperBound = target2.getUpperBound(fieldIndex2);
            if (Logger.isDebugEnabled()) {
                Logger.debug(TAG, "Using index '%s' to execute '%s' (Arrays: %s, Lower bound: %s, Upper bound: %s)", fieldIndex2, target2, arrayValues, lowerBound, upperBound);
            }
            Object[] generateQueryAndBindings = generateQueryAndBindings(target2, fieldIndex2.getIndexId(), arrayValues, encodeBound(fieldIndex2, target2, lowerBound), lowerBound.isInclusive() ? ">=" : ">", encodeBound(fieldIndex2, target2, upperBound), upperBound.isInclusive() ? "<=" : "<", encodeValues(fieldIndex2, target2, notInValues));
            arrayList.add(String.valueOf(generateQueryAndBindings[0]));
            arrayList2.addAll(Arrays.asList(generateQueryAndBindings).subList(1, generateQueryAndBindings.length));
        }
        String str = "SELECT DISTINCT document_key FROM (" + (TextUtils.join(" UNION ", arrayList) + "ORDER BY directional_value, document_key " + (target.getKeyOrder().equals(OrderBy.Direction.ASCENDING) ? "asc " : "desc ")) + ")";
        if (target.hasLimit()) {
            str = str + " LIMIT " + target.getLimit();
        }
        if (arrayList2.size() >= 1000) {
            z = false;
        }
        Assert.hardAssert(z, "Cannot perform query with more than 999 bind elements", new Object[0]);
        SQLitePersistence.Query binding = this.db.query(str).binding(arrayList2.toArray());
        ArrayList arrayList4 = new ArrayList();
        binding.forEach(new SQLiteIndexManager$$ExternalSyntheticLambda0(arrayList4));
        Logger.debug(TAG, "Index scan returned %s documents", Integer.valueOf(arrayList4.size()));
        return arrayList4;
    }

    private Object[] generateQueryAndBindings(Target target, int i, List<Value> list, Object[] objArr, String str, Object[] objArr2, String str2, Object[] objArr3) {
        Object[] objArr4 = objArr3;
        int max = Math.max(objArr.length, objArr2.length) * (list != null ? list.size() : 1);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT document_key, directional_value FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value ");
        String str3 = str;
        sb.append(str).append(" ? AND directional_value ");
        String str4 = str2;
        sb.append(str2).append(" ? ");
        StringBuilder repeatSequence = Util.repeatSequence(sb, max, " UNION ");
        if (objArr4 != null) {
            repeatSequence = new StringBuilder("SELECT document_key, directional_value FROM (").append(repeatSequence);
            repeatSequence.append(") WHERE directional_value NOT IN (");
            repeatSequence.append(Util.repeatSequence("?", objArr4.length, ", "));
            repeatSequence.append(")");
        }
        StringBuilder sb2 = repeatSequence;
        Object[] fillBounds = fillBounds(max, i, list, objArr, objArr2, objArr3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(sb2.toString());
        arrayList.addAll(Arrays.asList(fillBounds));
        return arrayList.toArray();
    }

    private Object[] fillBounds(int i, int i2, List<Value> list, Object[] objArr, Object[] objArr2, Object[] objArr3) {
        byte[] bArr;
        int size = i / (list != null ? list.size() : 1);
        int i3 = 0;
        Object[] objArr4 = new Object[((i * 5) + (objArr3 != null ? objArr3.length : 0))];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i5 + 1;
            objArr4[i5] = Integer.valueOf(i2);
            int i7 = i6 + 1;
            objArr4[i6] = this.uid;
            int i8 = i7 + 1;
            if (list != null) {
                bArr = encodeSingleElement(list.get(i4 / size));
            } else {
                bArr = EMPTY_BYTES_VALUE;
            }
            objArr4[i7] = bArr;
            int i9 = i8 + 1;
            int i10 = i4 % size;
            objArr4[i8] = objArr[i10];
            objArr4[i9] = objArr2[i10];
            i4++;
            i5 = i9 + 1;
        }
        if (objArr3 != null) {
            int length = objArr3.length;
            while (i3 < length) {
                objArr4[i5] = objArr3[i3];
                i3++;
                i5++;
            }
        }
        return objArr4;
    }

    private FieldIndex getFieldIndex(Target target) {
        String str;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        TargetIndexMatcher targetIndexMatcher = new TargetIndexMatcher(target);
        if (target.getCollectionGroup() != null) {
            str = target.getCollectionGroup();
        } else {
            str = target.getPath().getLastSegment();
        }
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(str);
        FieldIndex fieldIndex = null;
        if (fieldIndexes.isEmpty()) {
            return null;
        }
        for (FieldIndex next : fieldIndexes) {
            if (targetIndexMatcher.servedByIndex(next) && (fieldIndex == null || next.getSegments().size() > fieldIndex.getSegments().size())) {
                fieldIndex = next;
            }
        }
        return fieldIndex;
    }

    private byte[] encodeDirectionalElements(FieldIndex fieldIndex, Document document) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        for (FieldIndex.Segment next : fieldIndex.getDirectionalSegments()) {
            Value field = document.getField(next.getFieldPath());
            if (field == null) {
                return null;
            }
            FirestoreIndexValueWriter.INSTANCE.writeIndexValue(field, indexByteEncoder.forKind(next.getKind()));
        }
        return indexByteEncoder.getEncodedBytes();
    }

    private byte[] encodeSingleElement(Value value) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, indexByteEncoder.forKind(FieldIndex.Segment.Kind.ASCENDING));
        return indexByteEncoder.getEncodedBytes();
    }

    private Object[] encodeValues(FieldIndex fieldIndex, Target target, Collection<Value> collection) {
        if (collection == null) {
            return null;
        }
        List<IndexByteEncoder> arrayList = new ArrayList<>();
        arrayList.add(new IndexByteEncoder());
        Iterator<Value> it = collection.iterator();
        for (FieldIndex.Segment next : fieldIndex.getDirectionalSegments()) {
            Value next2 = it.next();
            for (IndexByteEncoder indexByteEncoder : arrayList) {
                if (!isInFilter(target, next.getFieldPath()) || !Values.isArray(next2)) {
                    FirestoreIndexValueWriter.INSTANCE.writeIndexValue(next2, indexByteEncoder.forKind(next.getKind()));
                } else {
                    arrayList = expandIndexValues(arrayList, next, next2);
                }
            }
        }
        return getEncodedBytes(arrayList);
    }

    private Object[] encodeBound(FieldIndex fieldIndex, Target target, Bound bound) {
        return encodeValues(fieldIndex, target, bound.getPosition());
    }

    private Object[] getEncodedBytes(List<IndexByteEncoder> list) {
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objArr[i] = list.get(i).getEncodedBytes();
        }
        return objArr;
    }

    private List<IndexByteEncoder> expandIndexValues(List<IndexByteEncoder> list, FieldIndex.Segment segment, Value value) {
        ArrayList<IndexByteEncoder> arrayList = new ArrayList<>(list);
        ArrayList arrayList2 = new ArrayList();
        for (Value next : value.getArrayValue().getValuesList()) {
            for (IndexByteEncoder encodedBytes : arrayList) {
                IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
                indexByteEncoder.seed(encodedBytes.getEncodedBytes());
                FirestoreIndexValueWriter.INSTANCE.writeIndexValue(next, indexByteEncoder.forKind(segment.getKind()));
                arrayList2.add(indexByteEncoder);
            }
        }
        return arrayList2;
    }

    private boolean isInFilter(Target target, FieldPath fieldPath) {
        for (Filter next : target.getFilters()) {
            if (next instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) next;
                if (fieldFilter.getField().equals(fieldPath)) {
                    FieldFilter.Operator operator = fieldFilter.getOperator();
                    if (operator.equals(FieldFilter.Operator.IN) || operator.equals(FieldFilter.Operator.NOT_IN)) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    private byte[] encodeSegments(FieldIndex fieldIndex) {
        return this.serializer.encodeFieldIndexSegments(fieldIndex.getSegments()).toByteArray();
    }

    public void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        this.memoizedMaxSequenceNumber++;
        for (FieldIndex next : getFieldIndexes(str)) {
            FieldIndex create = FieldIndex.create(next.getIndexId(), next.getCollectionGroup(), next.getSegments(), FieldIndex.IndexState.create(this.memoizedMaxSequenceNumber, indexOffset));
            this.db.execute("REPLACE INTO index_state (index_id, uid,  sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(next.getIndexId()), this.uid, Long.valueOf(this.memoizedMaxSequenceNumber), Long.valueOf(indexOffset.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(indexOffset.getReadTime().getTimestamp().getNanoseconds()), EncodedPath.encode(indexOffset.getDocumentKey().getPath()), Integer.valueOf(indexOffset.getLargestBatchId()));
            memoizeIndex(create);
        }
    }
}
