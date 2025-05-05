package com.google.firebase.firestore.local;

import com.google.firebase.firestore.util.Function;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ SQLiteRemoteDocumentCache f$0;
    public final /* synthetic */ byte[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Function f$4;
    public final /* synthetic */ Map f$5;

    public /* synthetic */ SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, byte[] bArr, int i, int i2, Function function, Map map) {
        this.f$0 = sQLiteRemoteDocumentCache;
        this.f$1 = bArr;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = function;
        this.f$5 = map;
    }

    public final void run() {
        this.f$0.m721lambda$processRowInBackground$2$comgooglefirebasefirestorelocalSQLiteRemoteDocumentCache(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
