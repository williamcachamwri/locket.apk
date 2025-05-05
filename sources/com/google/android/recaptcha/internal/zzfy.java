package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import java.util.Arrays;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfy {
    /* access modifiers changed from: private */
    public final WebView zza;
    private final CoroutineScope zzb;

    public zzfy(WebView webView, CoroutineScope coroutineScope) {
        this.zza = webView;
        this.zzb = coroutineScope;
    }

    public final void zzb(String str, String... strArr) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.zzb, (CoroutineContext) null, (CoroutineStart) null, new zzfx((String[]) Arrays.copyOf(strArr, strArr.length), this, str, (Continuation) null), 3, (Object) null);
    }
}
