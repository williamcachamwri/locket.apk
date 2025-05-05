package com.adsbynimbus.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
final class Platform$iid$2 extends Lambda implements Function0<String> {
    public static final Platform$iid$2 INSTANCE = new Platform$iid$2();

    Platform$iid$2() {
        super(0);
    }

    public final String invoke() {
        String str;
        String string;
        String str2;
        Platform.INSTANCE.getSharedPreferences();
        try {
            Result.Companion companion = Result.Companion;
            string = Platform.INSTANCE.getSharedPreferences().getString(Components.INSTANCE_ID, (String) null);
            if (string == null) {
                Context application = PlatformKt.getApplication();
                Result.Companion companion2 = Result.Companion;
                String string2 = Settings.Secure.getString(application.getContentResolver(), "android_id");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(contentResolve…ttings.Secure.ANDROID_ID)");
                byte[] bytes = string2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                str2 = Result.m2444constructorimpl(UUID.nameUUIDFromBytes(bytes).toString());
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                if (Result.m2450isFailureimpl(str2)) {
                    str2 = uuid;
                }
                string = (String) str2;
                SharedPreferences.Editor edit = Platform.INSTANCE.getSharedPreferences().edit();
                edit.putString(Components.INSTANCE_ID, string);
                edit.apply();
            }
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            str = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        str = Result.m2444constructorimpl(string);
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid2, "randomUUID().toString()");
        if (Result.m2450isFailureimpl(str)) {
            str = uuid2;
        }
        return (String) str;
    }
}
