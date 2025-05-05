package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.proto.NoDocument;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.proto.UnknownDocument;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.bundle.BundledQuery;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Write;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.List;

public final class LocalSerializer {
    private final RemoteSerializer rpcSerializer;

    public LocalSerializer(RemoteSerializer remoteSerializer) {
        this.rpcSerializer = remoteSerializer;
    }

    /* access modifiers changed from: package-private */
    public MaybeDocument encodeMaybeDocument(Document document) {
        MaybeDocument.Builder newBuilder = MaybeDocument.newBuilder();
        if (document.isNoDocument()) {
            newBuilder.setNoDocument(encodeNoDocument(document));
        } else if (document.isFoundDocument()) {
            newBuilder.setDocument(encodeDocument(document));
        } else if (document.isUnknownDocument()) {
            newBuilder.setUnknownDocument(encodeUnknownDocument(document));
        } else {
            throw Assert.fail("Cannot encode invalid document %s", document);
        }
        newBuilder.setHasCommittedMutations(document.hasCommittedMutations());
        return (MaybeDocument) newBuilder.build();
    }

    /* access modifiers changed from: package-private */
    public MutableDocument decodeMaybeDocument(MaybeDocument maybeDocument) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase[maybeDocument.getDocumentTypeCase().ordinal()];
        if (i == 1) {
            return decodeDocument(maybeDocument.getDocument(), maybeDocument.getHasCommittedMutations());
        }
        if (i == 2) {
            return decodeNoDocument(maybeDocument.getNoDocument(), maybeDocument.getHasCommittedMutations());
        }
        if (i == 3) {
            return decodeUnknownDocument(maybeDocument.getUnknownDocument());
        }
        throw Assert.fail("Unknown MaybeDocument %s", maybeDocument);
    }

    private com.google.firestore.v1.Document encodeDocument(Document document) {
        Document.Builder newBuilder = com.google.firestore.v1.Document.newBuilder();
        newBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        newBuilder.putAllFields(document.getData().getFieldsMap());
        newBuilder.setUpdateTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (com.google.firestore.v1.Document) newBuilder.build();
    }

    private MutableDocument decodeDocument(com.google.firestore.v1.Document document, boolean z) {
        MutableDocument newFoundDocument = MutableDocument.newFoundDocument(this.rpcSerializer.decodeKey(document.getName()), this.rpcSerializer.decodeVersion(document.getUpdateTime()), ObjectValue.fromMap(document.getFieldsMap()));
        return z ? newFoundDocument.setHasCommittedMutations() : newFoundDocument;
    }

    private NoDocument encodeNoDocument(com.google.firebase.firestore.model.Document document) {
        NoDocument.Builder newBuilder = NoDocument.newBuilder();
        newBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        newBuilder.setReadTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (NoDocument) newBuilder.build();
    }

    private MutableDocument decodeNoDocument(NoDocument noDocument, boolean z) {
        MutableDocument newNoDocument = MutableDocument.newNoDocument(this.rpcSerializer.decodeKey(noDocument.getName()), this.rpcSerializer.decodeVersion(noDocument.getReadTime()));
        return z ? newNoDocument.setHasCommittedMutations() : newNoDocument;
    }

    private UnknownDocument encodeUnknownDocument(com.google.firebase.firestore.model.Document document) {
        UnknownDocument.Builder newBuilder = UnknownDocument.newBuilder();
        newBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        newBuilder.setVersion(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (UnknownDocument) newBuilder.build();
    }

    private MutableDocument decodeUnknownDocument(UnknownDocument unknownDocument) {
        return MutableDocument.newUnknownDocument(this.rpcSerializer.decodeKey(unknownDocument.getName()), this.rpcSerializer.decodeVersion(unknownDocument.getVersion()));
    }

    /* access modifiers changed from: package-private */
    public WriteBatch encodeMutationBatch(MutationBatch mutationBatch) {
        WriteBatch.Builder newBuilder = WriteBatch.newBuilder();
        newBuilder.setBatchId(mutationBatch.getBatchId());
        newBuilder.setLocalWriteTime(this.rpcSerializer.encodeTimestamp(mutationBatch.getLocalWriteTime()));
        for (Mutation encodeMutation : mutationBatch.getBaseMutations()) {
            newBuilder.addBaseWrites(this.rpcSerializer.encodeMutation(encodeMutation));
        }
        for (Mutation encodeMutation2 : mutationBatch.getMutations()) {
            newBuilder.addWrites(this.rpcSerializer.encodeMutation(encodeMutation2));
        }
        return (WriteBatch) newBuilder.build();
    }

    /* access modifiers changed from: package-private */
    public MutationBatch decodeMutationBatch(WriteBatch writeBatch) {
        int batchId = writeBatch.getBatchId();
        Timestamp decodeTimestamp = this.rpcSerializer.decodeTimestamp(writeBatch.getLocalWriteTime());
        int baseWritesCount = writeBatch.getBaseWritesCount();
        ArrayList arrayList = new ArrayList(baseWritesCount);
        for (int i = 0; i < baseWritesCount; i++) {
            arrayList.add(this.rpcSerializer.decodeMutation(writeBatch.getBaseWrites(i)));
        }
        ArrayList arrayList2 = new ArrayList(writeBatch.getWritesCount());
        int i2 = 0;
        while (i2 < writeBatch.getWritesCount()) {
            Write writes = writeBatch.getWrites(i2);
            int i3 = i2 + 1;
            if (i3 < writeBatch.getWritesCount() && writeBatch.getWrites(i3).hasTransform()) {
                Assert.hardAssert(writeBatch.getWrites(i2).hasUpdate(), "TransformMutation should be preceded by a patch or set mutation", new Object[0]);
                Write.Builder newBuilder = Write.newBuilder(writes);
                for (DocumentTransform.FieldTransform addUpdateTransforms : writeBatch.getWrites(i3).getTransform().getFieldTransformsList()) {
                    newBuilder.addUpdateTransforms(addUpdateTransforms);
                }
                arrayList2.add(this.rpcSerializer.decodeMutation((Write) newBuilder.build()));
                i2 = i3;
            } else {
                arrayList2.add(this.rpcSerializer.decodeMutation(writes));
            }
            i2++;
        }
        return new MutationBatch(batchId, decodeTimestamp, arrayList, arrayList2);
    }

    /* access modifiers changed from: package-private */
    public Target encodeTargetData(TargetData targetData) {
        Assert.hardAssert(QueryPurpose.LISTEN.equals(targetData.getPurpose()), "Only queries with purpose %s may be stored, got %s", QueryPurpose.LISTEN, targetData.getPurpose());
        Target.Builder newBuilder = Target.newBuilder();
        newBuilder.setTargetId(targetData.getTargetId()).setLastListenSequenceNumber(targetData.getSequenceNumber()).setLastLimboFreeSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getLastLimboFreeSnapshotVersion())).setSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getSnapshotVersion())).setResumeToken(targetData.getResumeToken());
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            newBuilder.setDocuments(this.rpcSerializer.encodeDocumentsTarget(target));
        } else {
            newBuilder.setQuery(this.rpcSerializer.encodeQueryTarget(target));
        }
        return (Target) newBuilder.build();
    }

    /* access modifiers changed from: package-private */
    public TargetData decodeTargetData(Target target) {
        com.google.firebase.firestore.core.Target target2;
        int targetId = target.getTargetId();
        SnapshotVersion decodeVersion = this.rpcSerializer.decodeVersion(target.getSnapshotVersion());
        SnapshotVersion decodeVersion2 = this.rpcSerializer.decodeVersion(target.getLastLimboFreeSnapshotVersion());
        ByteString resumeToken = target.getResumeToken();
        long lastListenSequenceNumber = target.getLastListenSequenceNumber();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase[target.getTargetTypeCase().ordinal()];
        if (i == 1) {
            target2 = this.rpcSerializer.decodeDocumentsTarget(target.getDocuments());
        } else if (i == 2) {
            target2 = this.rpcSerializer.decodeQueryTarget(target.getQuery());
        } else {
            throw Assert.fail("Unknown targetType %d", target.getTargetTypeCase());
        }
        return new TargetData(target2, targetId, lastListenSequenceNumber, QueryPurpose.LISTEN, decodeVersion, decodeVersion2, resumeToken, (Integer) null);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalSerializer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        static {
            /*
                com.google.firebase.firestore.proto.Target$TargetTypeCase[] r0 = com.google.firebase.firestore.proto.Target.TargetTypeCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase = r0
                r1 = 1
                com.google.firebase.firestore.proto.Target$TargetTypeCase r2 = com.google.firebase.firestore.proto.Target.TargetTypeCase.DOCUMENTS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.proto.Target$TargetTypeCase r3 = com.google.firebase.firestore.proto.Target.TargetTypeCase.QUERY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.google.firebase.firestore.proto.MaybeDocument$DocumentTypeCase[] r2 = com.google.firebase.firestore.proto.MaybeDocument.DocumentTypeCase.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase = r2
                com.google.firebase.firestore.proto.MaybeDocument$DocumentTypeCase r3 = com.google.firebase.firestore.proto.MaybeDocument.DocumentTypeCase.DOCUMENT     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.google.firebase.firestore.proto.MaybeDocument$DocumentTypeCase r2 = com.google.firebase.firestore.proto.MaybeDocument.DocumentTypeCase.NO_DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.firebase.firestore.proto.MaybeDocument$DocumentTypeCase r1 = com.google.firebase.firestore.proto.MaybeDocument.DocumentTypeCase.UNKNOWN_DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.LocalSerializer.AnonymousClass1.<clinit>():void");
        }
    }

    public BundledQuery encodeBundledQuery(com.google.firebase.firestore.bundle.BundledQuery bundledQuery) {
        BundledQuery.LimitType limitType;
        Target.QueryTarget encodeQueryTarget = this.rpcSerializer.encodeQueryTarget(bundledQuery.getTarget());
        BundledQuery.Builder newBuilder = BundledQuery.newBuilder();
        if (bundledQuery.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST)) {
            limitType = BundledQuery.LimitType.FIRST;
        } else {
            limitType = BundledQuery.LimitType.LAST;
        }
        newBuilder.setLimitType(limitType);
        newBuilder.setParent(encodeQueryTarget.getParent());
        newBuilder.setStructuredQuery(encodeQueryTarget.getStructuredQuery());
        return (BundledQuery) newBuilder.build();
    }

    public com.google.firebase.firestore.bundle.BundledQuery decodeBundledQuery(BundledQuery bundledQuery) {
        Query.LimitType limitType;
        if (bundledQuery.getLimitType().equals(BundledQuery.LimitType.FIRST)) {
            limitType = Query.LimitType.LIMIT_TO_FIRST;
        } else {
            limitType = Query.LimitType.LIMIT_TO_LAST;
        }
        return new com.google.firebase.firestore.bundle.BundledQuery(this.rpcSerializer.decodeQueryTarget(bundledQuery.getParent(), bundledQuery.getStructuredQuery()), limitType);
    }

    public Index encodeFieldIndexSegments(List<FieldIndex.Segment> list) {
        Index.Builder newBuilder = Index.newBuilder();
        newBuilder.setQueryScope(Index.QueryScope.COLLECTION_GROUP);
        for (FieldIndex.Segment next : list) {
            Index.IndexField.Builder newBuilder2 = Index.IndexField.newBuilder();
            newBuilder2.setFieldPath(next.getFieldPath().canonicalString());
            if (next.getKind() == FieldIndex.Segment.Kind.CONTAINS) {
                newBuilder2.setArrayConfig(Index.IndexField.ArrayConfig.CONTAINS);
            } else if (next.getKind() == FieldIndex.Segment.Kind.ASCENDING) {
                newBuilder2.setOrder(Index.IndexField.Order.ASCENDING);
            } else {
                newBuilder2.setOrder(Index.IndexField.Order.DESCENDING);
            }
            newBuilder.addFields(newBuilder2);
        }
        return (Index) newBuilder.build();
    }

    public List<FieldIndex.Segment> decodeFieldIndexSegments(Index index) {
        FieldIndex.Segment.Kind kind;
        ArrayList arrayList = new ArrayList();
        for (Index.IndexField next : index.getFieldsList()) {
            FieldPath fromServerFormat = FieldPath.fromServerFormat(next.getFieldPath());
            if (next.getValueModeCase().equals(Index.IndexField.ValueModeCase.ARRAY_CONFIG)) {
                kind = FieldIndex.Segment.Kind.CONTAINS;
            } else if (next.getOrder().equals(Index.IndexField.Order.ASCENDING)) {
                kind = FieldIndex.Segment.Kind.ASCENDING;
            } else {
                kind = FieldIndex.Segment.Kind.DESCENDING;
            }
            arrayList.add(FieldIndex.Segment.create(fromServerFormat, kind));
        }
        return arrayList;
    }

    public Mutation decodeMutation(Write write) {
        return this.rpcSerializer.decodeMutation(write);
    }

    public Write encodeMutation(Mutation mutation) {
        return this.rpcSerializer.encodeMutation(mutation);
    }
}
