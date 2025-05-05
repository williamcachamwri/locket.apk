package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class Mutation {
    private final List<FieldTransform> fieldTransforms;
    private final DocumentKey key;
    private final Precondition precondition;

    public abstract FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask, Timestamp timestamp);

    public abstract void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult);

    public abstract FieldMask getFieldMask();

    Mutation(DocumentKey documentKey, Precondition precondition2) {
        this(documentKey, precondition2, new ArrayList());
    }

    Mutation(DocumentKey documentKey, Precondition precondition2, List<FieldTransform> list) {
        this.key = documentKey;
        this.precondition = precondition2;
        this.fieldTransforms = list;
    }

    public static Mutation calculateOverlayMutation(MutableDocument mutableDocument, FieldMask fieldMask) {
        if (!mutableDocument.hasLocalMutations()) {
            return null;
        }
        if (fieldMask != null && fieldMask.getMask().isEmpty()) {
            return null;
        }
        if (fieldMask != null) {
            ObjectValue data = mutableDocument.getData();
            ObjectValue objectValue = new ObjectValue();
            HashSet hashSet = new HashSet();
            for (FieldPath next : fieldMask.getMask()) {
                if (!hashSet.contains(next)) {
                    if (data.get(next) == null && next.length() > 1) {
                        next = (FieldPath) next.popLast();
                    }
                    objectValue.set(next, data.get(next));
                    hashSet.add(next);
                }
            }
            return new PatchMutation(mutableDocument.getKey(), objectValue, FieldMask.fromSet(hashSet), Precondition.NONE);
        } else if (mutableDocument.isNoDocument()) {
            return new DeleteMutation(mutableDocument.getKey(), Precondition.NONE);
        } else {
            return new SetMutation(mutableDocument.getKey(), mutableDocument.getData(), Precondition.NONE);
        }
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    /* access modifiers changed from: package-private */
    public boolean hasSameKeyAndPrecondition(Mutation mutation) {
        return this.key.equals(mutation.key) && this.precondition.equals(mutation.precondition);
    }

    /* access modifiers changed from: package-private */
    public int keyAndPreconditionHashCode() {
        return (getKey().hashCode() * 31) + this.precondition.hashCode();
    }

    /* access modifiers changed from: package-private */
    public String keyAndPreconditionToString() {
        return "key=" + this.key + ", precondition=" + this.precondition;
    }

    /* access modifiers changed from: package-private */
    public void verifyKeyMatches(MutableDocument mutableDocument) {
        Assert.hardAssert(mutableDocument.getKey().equals(getKey()), "Can only apply a mutation to a document with the same key", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public Map<FieldPath, Value> serverTransformResults(MutableDocument mutableDocument, List<Value> list) {
        HashMap hashMap = new HashMap(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == list.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(list.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < list.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            hashMap.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToRemoteDocument(mutableDocument.getField(fieldTransform.getFieldPath()), list.get(i)));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public Map<FieldPath, Value> localTransformResults(Timestamp timestamp, MutableDocument mutableDocument) {
        HashMap hashMap = new HashMap(this.fieldTransforms.size());
        for (FieldTransform next : this.fieldTransforms) {
            hashMap.put(next.getFieldPath(), next.getOperation().applyToLocalView(mutableDocument.getField(next.getFieldPath()), timestamp));
        }
        return hashMap;
    }

    public ObjectValue extractTransformBaseValue(Document document) {
        ObjectValue objectValue = null;
        for (FieldTransform next : this.fieldTransforms) {
            Value computeBaseValue = next.getOperation().computeBaseValue(document.getField(next.getFieldPath()));
            if (computeBaseValue != null) {
                if (objectValue == null) {
                    objectValue = new ObjectValue();
                }
                objectValue.set(next.getFieldPath(), computeBaseValue);
            }
        }
        return objectValue;
    }
}
