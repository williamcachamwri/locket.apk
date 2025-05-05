package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfm {
    private final List zza;

    public zzfm() {
        this(true);
    }

    public static final boolean zzc(Uri uri) {
        return zze(uri);
    }

    private final boolean zzd(String str) {
        Iterable<String> iterable = this.zza;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String startsWith$default : iterable) {
            if (StringsKt.startsWith$default(str, startsWith$default, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean zze(Uri uri) {
        return !TextUtils.isEmpty(uri.toString()) && Intrinsics.areEqual((Object) "https", (Object) uri.getScheme()) && !TextUtils.isEmpty(uri.getHost());
    }

    private static final List zzf(List list) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String str : iterable) {
            arrayList.add("https://" + str + "/");
        }
        return (List) arrayList;
    }

    public final boolean zza(Uri uri) {
        return zze(uri) && zzd(uri.toString());
    }

    public final boolean zzb(String str) {
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNull(parse);
        return zze(parse) && zzd(parse.toString());
    }

    public zzfm(boolean z) {
        this.zza = zzf(CollectionsKt.listOf("www.recaptcha.net", "www.gstatic.com/recaptcha", "www.gstatic.cn/recaptcha"));
    }
}
