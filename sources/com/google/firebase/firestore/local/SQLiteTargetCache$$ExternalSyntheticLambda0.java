package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache f$0;
    public final /* synthetic */ Target f$1;
    public final /* synthetic */ SQLiteTargetCache.TargetDataHolder f$2;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda0(SQLiteTargetCache sQLiteTargetCache, Target target, SQLiteTargetCache.TargetDataHolder targetDataHolder) {
        this.f$0 = sQLiteTargetCache;
        this.f$1 = target;
        this.f$2 = targetDataHolder;
    }

    public final void accept(Object obj) {
        this.f$0.m736lambda$getTargetData$3$comgooglefirebasefirestorelocalSQLiteTargetCache(this.f$1, this.f$2, (Cursor) obj);
    }
}
