package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ IndexManager f$0;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda8(IndexManager indexManager) {
        this.f$0 = indexManager;
    }

    public final void accept(Object obj) {
        this.f$0.deleteFieldIndex((FieldIndex) obj);
    }
}
