package com.google.firebase.firestore.local;

import android.database.sqlite.SQLiteStatement;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteSchema$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ MemoryIndexManager.MemoryCollectionParentIndex f$0;
    public final /* synthetic */ SQLiteStatement f$1;

    public /* synthetic */ SQLiteSchema$$ExternalSyntheticLambda6(MemoryIndexManager.MemoryCollectionParentIndex memoryCollectionParentIndex, SQLiteStatement sQLiteStatement) {
        this.f$0 = memoryCollectionParentIndex;
        this.f$1 = sQLiteStatement;
    }

    public final void accept(Object obj) {
        SQLiteSchema.lambda$createV8CollectionParentsIndex$10(this.f$0, this.f$1, (ResourcePath) obj);
    }
}
