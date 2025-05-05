package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzi implements DynamiteModule.VersionPolicy {
    zzi() {
    }

    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.zza(context, str);
        int i = 1;
        int zzb = iVersions.zzb(context, str, true);
        selectionResult.remoteVersion = zzb;
        int i2 = selectionResult.localVersion;
        if (i2 == 0) {
            i2 = 0;
            if (zzb == 0) {
                i = 0;
                selectionResult.selection = i;
                return selectionResult;
            }
        }
        if (i2 >= zzb) {
            i = -1;
        }
        selectionResult.selection = i;
        return selectionResult;
    }
}
