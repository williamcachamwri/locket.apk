package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzahq {
    private List<String> zza;

    public static zzahq zza() {
        return new zzahq((List<String>) null);
    }

    public final List<String> zzb() {
        return this.zza;
    }

    public zzahq() {
        this((List<String>) null);
    }

    private zzahq(List<String> list) {
        this.zza = new ArrayList();
    }

    public zzahq(int i, List<String> list) {
        if (list == null || list.isEmpty()) {
            this.zza = Collections.emptyList();
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.set(i2, Strings.emptyToNull(list.get(i2)));
        }
        this.zza = Collections.unmodifiableList(list);
    }
}
