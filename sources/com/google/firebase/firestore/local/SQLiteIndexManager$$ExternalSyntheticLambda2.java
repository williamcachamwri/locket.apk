package com.google.firebase.firestore.local;

import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SQLiteIndexManager f$0;
    public final /* synthetic */ Document f$1;

    public /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda2(SQLiteIndexManager sQLiteIndexManager, Document document) {
        this.f$0 = sQLiteIndexManager;
        this.f$1 = document;
    }

    public final void accept(Object obj) {
        this.f$0.m708lambda$updateEntries$5$comgooglefirebasefirestorelocalSQLiteIndexManager(this.f$1, (IndexEntry) obj);
    }
}
