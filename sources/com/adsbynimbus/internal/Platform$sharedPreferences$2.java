package com.adsbynimbus.internal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
final class Platform$sharedPreferences$2 extends Lambda implements Function0<SharedPreferences> {
    public static final Platform$sharedPreferences$2 INSTANCE = new Platform$sharedPreferences$2();

    Platform$sharedPreferences$2() {
        super(0);
    }

    public final SharedPreferences invoke() {
        return PreferenceManager.getDefaultSharedPreferences(PlatformKt.getApplication());
    }
}
