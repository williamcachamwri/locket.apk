package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda10 implements SQLiteEventStore.Producer {
    public final /* synthetic */ SQLiteDatabase f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda10(SQLiteDatabase sQLiteDatabase) {
        this.f$0 = sQLiteDatabase;
    }

    public final Object produce() {
        return this.f$0.beginTransaction();
    }
}
