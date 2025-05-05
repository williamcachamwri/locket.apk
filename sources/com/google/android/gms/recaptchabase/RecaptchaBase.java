package com.google.android.gms.recaptchabase;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.recaptchabase.zzl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/google/android/gms/recaptchabase/RecaptchaBase;", "", "<init>", "()V", "getClient", "Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class RecaptchaBase {
    public static final RecaptchaBase INSTANCE = new RecaptchaBase();

    private RecaptchaBase() {
    }

    @JvmStatic
    public static final RecaptchaBaseClient getClient(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return new zzl(activity);
    }

    @JvmStatic
    public static final RecaptchaBaseClient getClient(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new zzl(context);
    }
}
