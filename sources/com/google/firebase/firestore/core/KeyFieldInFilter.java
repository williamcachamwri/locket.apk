package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;

public class KeyFieldInFilter extends FieldFilter {
    private final List<DocumentKey> keys;

    KeyFieldInFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, FieldFilter.Operator.IN, value);
        ArrayList arrayList = new ArrayList();
        this.keys = arrayList;
        arrayList.addAll(extractDocumentKeysFromArrayValue(FieldFilter.Operator.IN, value));
    }

    public boolean matches(Document document) {
        return this.keys.contains(document.getKey());
    }

    static List<DocumentKey> extractDocumentKeysFromArrayValue(FieldFilter.Operator operator, Value value) {
        Assert.hardAssert(operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN, "extractDocumentKeysFromArrayValue requires IN or NOT_IN operators", new Object[0]);
        Assert.hardAssert(Values.isArray(value), "KeyFieldInFilter/KeyFieldNotInFilter expects an ArrayValue", new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (Value next : value.getArrayValue().getValuesList()) {
            Assert.hardAssert(Values.isReferenceValue(next), "Comparing on key with " + operator.toString() + ", but an array value was not a ReferenceValue", new Object[0]);
            arrayList.add(DocumentKey.fromName(next.getReferenceValue()));
        }
        return arrayList;
    }
}
