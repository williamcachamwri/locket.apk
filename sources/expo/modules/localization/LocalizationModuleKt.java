package expo.modules.localization;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006*\u00020\bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"CALENDAR_SETTINGS_CHANGED", "", "KEY_FOR_PREFS_ALLOWRTL", "LOCALE_SETTINGS_CHANGED", "SHARED_PREFS_NAME", "toShallowMap", "", "", "Landroid/os/Bundle;", "expo-localization_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationModule.kt */
public final class LocalizationModuleKt {
    private static final String CALENDAR_SETTINGS_CHANGED = "onCalendarSettingsChanged";
    private static final String KEY_FOR_PREFS_ALLOWRTL = "RCTI18nUtil_allowRTL";
    private static final String LOCALE_SETTINGS_CHANGED = "onLocaleSettingsChanged";
    private static final String SHARED_PREFS_NAME = "com.facebook.react.modules.i18nmanager.I18nUtil";

    /* access modifiers changed from: private */
    public static final Map<String, Object> toShallowMap(Bundle bundle) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Intrinsics.checkNotNull(str);
            hashMap.put(str, bundle.get(str));
        }
        return hashMap;
    }
}
