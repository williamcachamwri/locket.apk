package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final /* synthetic */ class zzeb extends FunctionReferenceImpl implements Function1 {
    zzeb(Object obj) {
        super(1, obj, zzee.class, "isRetriable", "isRetriable(Ljava/lang/Exception;)Z", 0);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(zzee.zzo((Exception) obj));
    }
}
