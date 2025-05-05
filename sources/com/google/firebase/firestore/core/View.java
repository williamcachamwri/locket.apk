package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class View {
    private boolean current;
    private DocumentSet documentSet;
    private ImmutableSortedSet<DocumentKey> limboDocuments;
    private ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final Query query;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        /* access modifiers changed from: private */
        public final boolean needsRefill;

        /* synthetic */ DocumentChanges(DocumentSet documentSet2, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, AnonymousClass1 r5) {
            this(documentSet2, documentViewChangeSet, immutableSortedSet, z);
        }

        private DocumentChanges(DocumentSet documentSet2, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet2;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query2, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = query2;
        this.documentSet = DocumentSet.emptySet(query2.comparator());
        this.syncedDocuments = immutableSortedSet;
        this.limboDocuments = DocumentKey.emptyKeySet();
        this.mutatedKeys = DocumentKey.emptyKeySet();
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, (DocumentChanges) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f4, code lost:
        if (r0.query.comparator().compare(r6, r4) > 0) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0102, code lost:
        if (r0.query.comparator().compare(r6, r7) < 0) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0131, code lost:
        if (r7 == null) goto L_0x0120;
     */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x015f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.firestore.core.View.DocumentChanges computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.firestore.model.DocumentKey, com.google.firebase.firestore.model.Document> r19, com.google.firebase.firestore.core.View.DocumentChanges r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            if (r1 == 0) goto L_0x0009
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = r1.changeSet
            goto L_0x000e
        L_0x0009:
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = new com.google.firebase.firestore.core.DocumentViewChangeSet
            r2.<init>()
        L_0x000e:
            r5 = r2
            if (r1 == 0) goto L_0x0014
            com.google.firebase.firestore.model.DocumentSet r2 = r1.documentSet
            goto L_0x0016
        L_0x0014:
            com.google.firebase.firestore.model.DocumentSet r2 = r0.documentSet
        L_0x0016:
            if (r1 == 0) goto L_0x001b
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r1.mutatedKeys
            goto L_0x001d
        L_0x001b:
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r0.mutatedKeys
        L_0x001d:
            com.google.firebase.firestore.core.Query r4 = r0.query
            com.google.firebase.firestore.core.Query$LimitType r4 = r4.getLimitType()
            com.google.firebase.firestore.core.Query$LimitType r6 = com.google.firebase.firestore.core.Query.LimitType.LIMIT_TO_FIRST
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x003f
            int r4 = r2.size()
            long r7 = (long) r4
            com.google.firebase.firestore.core.Query r4 = r0.query
            long r9 = r4.getLimit()
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x003f
            com.google.firebase.firestore.model.Document r4 = r2.getLastDocument()
            goto L_0x0040
        L_0x003f:
            r4 = 0
        L_0x0040:
            com.google.firebase.firestore.core.Query r7 = r0.query
            com.google.firebase.firestore.core.Query$LimitType r7 = r7.getLimitType()
            com.google.firebase.firestore.core.Query$LimitType r8 = com.google.firebase.firestore.core.Query.LimitType.LIMIT_TO_LAST
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0062
            int r7 = r2.size()
            long r7 = (long) r7
            com.google.firebase.firestore.core.Query r9 = r0.query
            long r9 = r9.getLimit()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x0062
            com.google.firebase.firestore.model.Document r7 = r2.getFirstDocument()
            goto L_0x0063
        L_0x0062:
            r7 = 0
        L_0x0063:
            java.util.Iterator r8 = r19.iterator()
            r11 = r2
            r10 = 0
        L_0x0069:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x0163
            java.lang.Object r12 = r8.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r14 = r12.getKey()
            com.google.firebase.firestore.model.DocumentKey r14 = (com.google.firebase.firestore.model.DocumentKey) r14
            com.google.firebase.firestore.model.Document r15 = r2.getDocument(r14)
            com.google.firebase.firestore.core.Query r6 = r0.query
            java.lang.Object r16 = r12.getValue()
            r13 = r16
            com.google.firebase.firestore.model.Document r13 = (com.google.firebase.firestore.model.Document) r13
            boolean r6 = r6.matches(r13)
            if (r6 == 0) goto L_0x0096
            java.lang.Object r6 = r12.getValue()
            com.google.firebase.firestore.model.Document r6 = (com.google.firebase.firestore.model.Document) r6
            goto L_0x0097
        L_0x0096:
            r6 = 0
        L_0x0097:
            if (r15 == 0) goto L_0x00a7
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r12 = r0.mutatedKeys
            com.google.firebase.firestore.model.DocumentKey r13 = r15.getKey()
            boolean r12 = r12.contains(r13)
            if (r12 == 0) goto L_0x00a7
            r12 = 1
            goto L_0x00a8
        L_0x00a7:
            r12 = 0
        L_0x00a8:
            if (r6 == 0) goto L_0x00c4
            boolean r13 = r6.hasLocalMutations()
            if (r13 != 0) goto L_0x00c2
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r13 = r0.mutatedKeys
            com.google.firebase.firestore.model.DocumentKey r9 = r6.getKey()
            boolean r9 = r13.contains(r9)
            if (r9 == 0) goto L_0x00c4
            boolean r9 = r6.hasCommittedMutations()
            if (r9 == 0) goto L_0x00c4
        L_0x00c2:
            r9 = 1
            goto L_0x00c5
        L_0x00c4:
            r9 = 0
        L_0x00c5:
            if (r15 == 0) goto L_0x0111
            if (r6 == 0) goto L_0x0111
            com.google.firebase.firestore.model.ObjectValue r13 = r15.getData()
            r17 = r2
            com.google.firebase.firestore.model.ObjectValue r2 = r6.getData()
            boolean r2 = r13.equals(r2)
            if (r2 != 0) goto L_0x0105
            boolean r2 = r0.shouldWaitForSyncedDocument(r15, r6)
            if (r2 != 0) goto L_0x0135
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.MODIFIED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r6)
            r5.addChange(r2)
            if (r4 == 0) goto L_0x00f6
            com.google.firebase.firestore.core.Query r2 = r0.query
            java.util.Comparator r2 = r2.comparator()
            int r2 = r2.compare(r6, r4)
            if (r2 > 0) goto L_0x0133
        L_0x00f6:
            if (r7 == 0) goto L_0x0120
            com.google.firebase.firestore.core.Query r2 = r0.query
            java.util.Comparator r2 = r2.comparator()
            int r2 = r2.compare(r6, r7)
            if (r2 >= 0) goto L_0x0120
            goto L_0x0133
        L_0x0105:
            if (r12 == r9) goto L_0x0135
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.METADATA
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r6)
            r5.addChange(r2)
            goto L_0x0120
        L_0x0111:
            r17 = r2
            if (r15 != 0) goto L_0x0122
            if (r6 == 0) goto L_0x0122
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r6)
            r5.addChange(r2)
        L_0x0120:
            r13 = 1
            goto L_0x0136
        L_0x0122:
            if (r15 == 0) goto L_0x0135
            if (r6 != 0) goto L_0x0135
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r15)
            r5.addChange(r2)
            if (r4 != 0) goto L_0x0133
            if (r7 == 0) goto L_0x0120
        L_0x0133:
            r10 = 1
            goto L_0x0120
        L_0x0135:
            r13 = 0
        L_0x0136:
            if (r13 == 0) goto L_0x015f
            if (r6 == 0) goto L_0x0156
            com.google.firebase.firestore.model.DocumentSet r11 = r11.add(r6)
            boolean r2 = r6.hasLocalMutations()
            if (r2 == 0) goto L_0x014d
            com.google.firebase.firestore.model.DocumentKey r2 = r6.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.insert(r2)
            goto L_0x015e
        L_0x014d:
            com.google.firebase.firestore.model.DocumentKey r2 = r6.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.remove(r2)
            goto L_0x015e
        L_0x0156:
            com.google.firebase.firestore.model.DocumentSet r11 = r11.remove(r14)
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.remove(r14)
        L_0x015e:
            r3 = r2
        L_0x015f:
            r2 = r17
            goto L_0x0069
        L_0x0163:
            com.google.firebase.firestore.core.Query r2 = r0.query
            boolean r2 = r2.hasLimit()
            if (r2 == 0) goto L_0x01b0
            int r2 = r11.size()
            long r6 = (long) r2
            com.google.firebase.firestore.core.Query r2 = r0.query
            long r8 = r2.getLimit()
        L_0x0176:
            long r6 = r6 - r8
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x01b0
            com.google.firebase.firestore.core.Query r2 = r0.query
            com.google.firebase.firestore.core.Query$LimitType r2 = r2.getLimitType()
            com.google.firebase.firestore.core.Query$LimitType r4 = com.google.firebase.firestore.core.Query.LimitType.LIMIT_TO_FIRST
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0190
            com.google.firebase.firestore.model.Document r2 = r11.getLastDocument()
            goto L_0x0194
        L_0x0190:
            com.google.firebase.firestore.model.Document r2 = r11.getFirstDocument()
        L_0x0194:
            com.google.firebase.firestore.model.DocumentKey r4 = r2.getKey()
            com.google.firebase.firestore.model.DocumentSet r11 = r11.remove(r4)
            com.google.firebase.firestore.model.DocumentKey r4 = r2.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.remove(r4)
            com.google.firebase.firestore.core.DocumentViewChange$Type r4 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r4, r2)
            r5.addChange(r2)
            r8 = 1
            goto L_0x0176
        L_0x01b0:
            r6 = r3
            r4 = r11
            if (r10 == 0) goto L_0x01b9
            if (r1 != 0) goto L_0x01b7
            goto L_0x01b9
        L_0x01b7:
            r13 = 0
            goto L_0x01ba
        L_0x01b9:
            r13 = 1
        L_0x01ba:
            java.lang.String r1 = "View was refilled using docs that themselves needed refilling."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.google.firebase.firestore.util.Assert.hardAssert(r13, r1, r2)
            com.google.firebase.firestore.core.View$DocumentChanges r1 = new com.google.firebase.firestore.core.View$DocumentChanges
            r8 = 0
            r3 = r1
            r7 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap, com.google.firebase.firestore.core.View$DocumentChanges):com.google.firebase.firestore.core.View$DocumentChanges");
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, (TargetChange) null);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        return applyChanges(documentChanges, targetChange, false);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange, boolean z) {
        ViewSnapshot viewSnapshot;
        boolean z2;
        DocumentChanges documentChanges2 = documentChanges;
        TargetChange targetChange2 = targetChange;
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet2 = this.documentSet;
        this.documentSet = documentChanges2.documentSet;
        this.mutatedKeys = documentChanges2.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges2.changeSet.getChanges();
        Collections.sort(changes, new View$$ExternalSyntheticLambda0(this));
        applyTargetChange(targetChange2);
        List<LimboDocumentChange> emptyList = z ? Collections.emptyList() : updateLimboDocuments();
        ViewSnapshot.SyncState syncState2 = this.limboDocuments.size() == 0 && this.current && !z ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z3 = syncState2 != this.syncState;
        this.syncState = syncState2;
        if (changes.size() != 0 || z3) {
            boolean z4 = syncState2 == ViewSnapshot.SyncState.LOCAL;
            if (targetChange2 != null && !targetChange.getResumeToken().isEmpty()) {
                z2 = true;
            } else {
                z2 = false;
            }
            viewSnapshot = new ViewSnapshot(this.query, documentChanges2.documentSet, documentSet2, changes, z4, documentChanges2.mutatedKeys, z3, false, z2);
        } else {
            viewSnapshot = null;
        }
        return new ViewChange(viewSnapshot, emptyList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View  reason: not valid java name */
    public /* synthetic */ int m675lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int compareIntegers = Util.compareIntegers(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        if (compareIntegers != 0) {
            return compareIntegers;
        }
        return this.query.comparator().compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (!this.current || onlineState != OnlineState.OFFLINE) {
            return new ViewChange((ViewSnapshot) null, Collections.emptyList());
        }
        this.current = false;
        return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, (AnonymousClass1) null));
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            Iterator<DocumentKey> it2 = targetChange.getModifiedDocuments().iterator();
            while (it2.hasNext()) {
                DocumentKey next = it2.next();
                Assert.hardAssert(this.syncedDocuments.contains(next), "Modified document %s not found in view.", next);
            }
            Iterator<DocumentKey> it3 = targetChange.getRemovedDocuments().iterator();
            while (it3.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it3.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        Iterator<Document> it = this.documentSet.iterator();
        while (it.hasNext()) {
            Document next = it.next();
            if (shouldBeLimboDoc(next.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(next.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(immutableSortedSet.size() + this.limboDocuments.size());
        Iterator<DocumentKey> it2 = immutableSortedSet.iterator();
        while (it2.hasNext()) {
            DocumentKey next2 = it2.next();
            if (!this.limboDocuments.contains(next2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, next2));
            }
        }
        Iterator<DocumentKey> it3 = this.limboDocuments.iterator();
        while (it3.hasNext()) {
            DocumentKey next3 = it3.next();
            if (!immutableSortedSet.contains(next3)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, next3));
            }
        }
        return arrayList;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        if (!this.syncedDocuments.contains(documentKey) && (document = this.documentSet.getDocument(documentKey)) != null && !document.hasLocalMutations()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* renamed from: com.google.firebase.firestore.core.View$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.firestore.core.DocumentViewChange$Type[] r0 = com.google.firebase.firestore.core.DocumentViewChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = r0
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.MODIFIED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.METADATA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.AnonymousClass1.<clinit>():void");
        }
    }

    private static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[documentViewChange.getType().ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (!(i == 2 || i == 3)) {
                if (i == 4) {
                    return 0;
                }
                throw new IllegalArgumentException("Unknown change type: " + documentViewChange.getType());
            }
        }
        return i2;
    }
}
