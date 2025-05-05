package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public abstract class zzck extends SQLiteOpenHelper {
    public zzck(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(context, str, (SQLiteDatabase.CursorFactory) null, 1, zzcm.zza);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzck(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, zzcm zzcm) {
        super(context, (str == null || zzcf.zza().zza(str, zzcm, zzcj.SQLITE_OPEN_HELPER_TYPE).equals("")) ? null : str, (SQLiteDatabase.CursorFactory) null, 1);
    }
}
