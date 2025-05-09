package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;

public class KeyFieldNotInFilter extends FieldFilter {
    private final List<DocumentKey> keys;

    KeyFieldNotInFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, FieldFilter.Operator.NOT_IN, value);
        ArrayList arrayList = new ArrayList();
        this.keys = arrayList;
        arrayList.addAll(KeyFieldInFilter.extractDocumentKeysFromArrayValue(FieldFilter.Operator.NOT_IN, value));
    }

    public boolean matches(Document document) {
        return !this.keys.contains(document.getKey());
    }
}
