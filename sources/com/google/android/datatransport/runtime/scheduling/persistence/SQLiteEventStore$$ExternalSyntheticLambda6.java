package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda6 implements SQLiteEventStore.Function {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ TransportContext f$1;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda6(long j, TransportContext transportContext) {
        this.f$0 = j;
        this.f$1 = transportContext;
    }

    public final Object apply(Object obj) {
        return SQLiteEventStore.lambda$recordNextCallTime$7(this.f$0, this.f$1, (SQLiteDatabase) obj);
    }
}
