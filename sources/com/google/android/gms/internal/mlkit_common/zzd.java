package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.PermissionChecker;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzd extends zzk {
    zzd() {
    }

    /* access modifiers changed from: protected */
    public final int zza(Context context, zzj zzj, boolean z) {
        return (zzj.zza.getAuthority().lastIndexOf(64) < 0 || PermissionChecker.checkSelfPermission(context, "android.permission.INTERACT_ACROSS_USERS") != 0) ? 3 : 2;
    }
}
