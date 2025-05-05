package expo.modules.localization;

import android.text.TextUtils;
import androidx.core.text.util.LocalePreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0010\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a'\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00020\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\b¢\u0006\u0002\u0010\u0014\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018FX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0019"}, d2 = {"ISOCurrencyCodes", "", "", "getISOCurrencyCodes", "()[Ljava/lang/String;", "ISOCurrencyCodes$delegate", "Lkotlin/Lazy;", "USES_FAHRENHEIT", "", "getUSES_FAHRENHEIT", "()Ljava/util/List;", "USES_IMPERIAL", "getUSES_IMPERIAL", "getCountryCode", "locale", "Ljava/util/Locale;", "getCurrencyCode", "getLocaleNames", "kotlin.jvm.PlatformType", "locales", "(Ljava/util/List;)[Ljava/lang/String;", "getRegionCode", "getSystemProperty", "key", "getTemperatureUnit", "expo-localization_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationUtils.kt */
public final class LocalizationUtilsKt {
    private static final Lazy ISOCurrencyCodes$delegate = LazyKt.lazy(LocalizationUtilsKt$ISOCurrencyCodes$2.INSTANCE);
    private static final List<String> USES_FAHRENHEIT = CollectionsKt.listOf("AG", "BZ", "VG", "FM", "MH", "MS", "KN", "BS", "CY", "TC", "US", "LR", "PW", "KY");
    private static final List<String> USES_IMPERIAL = CollectionsKt.listOf("US", "LR", "MM");

    public static final List<String> getUSES_IMPERIAL() {
        return USES_IMPERIAL;
    }

    public static final List<String> getUSES_FAHRENHEIT() {
        return USES_FAHRENHEIT;
    }

    public static final String[] getISOCurrencyCodes() {
        return (String[]) ISOCurrencyCodes$delegate.getValue();
    }

    public static final String[] getLocaleNames(List<Locale> list) {
        Intrinsics.checkNotNullParameter(list, "locales");
        Iterable<Locale> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Locale languageTag : iterable) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) ((List) arrayList).toArray(new String[0]);
    }

    public static final String getCountryCode(Locale locale) {
        Object obj;
        Intrinsics.checkNotNullParameter(locale, "locale");
        String str = null;
        try {
            Result.Companion companion = Result.Companion;
            String country = locale.getCountry();
            if (TextUtils.isEmpty(country)) {
                country = null;
            }
            obj = Result.m2444constructorimpl(country);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m2450isFailureimpl(obj)) {
            str = obj;
        }
        return str;
    }

    public static final String getSystemProperty(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "key");
        try {
            Result.Companion companion = Result.Companion;
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.String");
            obj = Result.m2444constructorimpl((String) invoke);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        String str2 = (String) obj;
        return str2 == null ? "" : str2;
    }

    public static final String getCurrencyCode(Locale locale) {
        Object obj;
        Intrinsics.checkNotNullParameter(locale, "locale");
        String str = null;
        try {
            Result.Companion companion = Result.Companion;
            Currency instance = Currency.getInstance(locale);
            obj = Result.m2444constructorimpl(instance != null ? instance.getCurrencyCode() : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m2450isFailureimpl(obj)) {
            str = obj;
        }
        return str;
    }

    public static final String getRegionCode(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        CharSequence systemProperty = getSystemProperty("ro.miui.region");
        if (systemProperty.length() == 0) {
            systemProperty = getCountryCode(locale);
        }
        return (String) systemProperty;
    }

    public static final String getTemperatureUnit(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String regionCode = getRegionCode(locale);
        if (regionCode == null) {
            return null;
        }
        return USES_FAHRENHEIT.contains(regionCode) ? "fahrenheit" : LocalePreferences.TemperatureUnit.CELSIUS;
    }
}
