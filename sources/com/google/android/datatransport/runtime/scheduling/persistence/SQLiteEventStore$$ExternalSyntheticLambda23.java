package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda23 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ Map f$1;
    public final /* synthetic */ ClientMetrics.Builder f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda23(SQLiteEventStore sQLiteEventStore, Map map, ClientMetrics.Builder builder) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = map;
        this.f$2 = builder;
    }

    public final Object apply(Object obj) {
        return this.f$0.m2115lambda$loadClientMetrics$19$comgoogleandroiddatatransportruntimeschedulingpersistenceSQLiteEventStore(this.f$1, this.f$2, (Cursor) obj);
    }
}
