package expo.modules.localization;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.LocaleData;
import android.icu.util.ULocale;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import androidx.core.os.BundleKt;
import androidx.core.os.LocaleListCompat;
import androidx.tracing.Trace;
import com.facebook.hermes.intl.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import io.sentry.android.core.SentryLogcatAdapter;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u001c\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140\bH\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\tH\u0002J\u001c\u0010\u0018\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140\bH\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lexpo/modules/localization/LocalizationModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "bundledConstants", "Landroid/os/Bundle;", "getBundledConstants", "()Landroid/os/Bundle;", "locales", "", "Ljava/util/Locale;", "getLocales", "()Ljava/util/List;", "observer", "Lkotlin/Function0;", "", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getCalendarType", "", "getCalendars", "", "", "getMeasurementSystem", "locale", "getPreferredLocales", "setRTLFromStringResources", "context", "Landroid/content/Context;", "uses24HourClock", "", "expo-localization_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationModule.kt */
public final class LocalizationModule extends Module {
    /* access modifiers changed from: private */
    public Function0<Unit> observer = LocalizationModule$observer$1.INSTANCE;

    public ModuleDefinitionData definition() {
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoLocalization");
            moduleDefinitionBuilder.Constants((Function0<? extends Map<String, ? extends Object>>) new LocalizationModule$definition$1$1(this));
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getLocalizationAsync", new AnyType[0], new LocalizationModule$definition$lambda$6$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getLocalizationAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction = asyncFunctionComponent;
            moduleDefinitionBuilder.getSyncFunctions().put("getLocales", new SyncFunctionComponent("getLocales", new AnyType[0], new LocalizationModule$definition$lambda$6$$inlined$FunctionWithoutArgs$1(this)));
            moduleDefinitionBuilder.getSyncFunctions().put("getCalendars", new SyncFunctionComponent("getCalendars", new AnyType[0], new LocalizationModule$definition$lambda$6$$inlined$FunctionWithoutArgs$2(this)));
            moduleDefinitionBuilder.Events("onLocaleSettingsChanged", "onCalendarSettingsChanged");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new LocalizationModule$definition$lambda$6$$inlined$OnCreate$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new LocalizationModule$definition$lambda$6$$inlined$OnDestroy$1(this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void setRTLFromStringResources(Context context) {
        Context reactContext = getAppContext().getReactContext();
        String string = reactContext != null ? reactContext.getString(R.string.ExpoLocalization_supportsRTL) : null;
        if (Intrinsics.areEqual((Object) string, (Object) "true") || Intrinsics.areEqual((Object) string, (Object) Constants.CASEFIRST_FALSE)) {
            SharedPreferences.Editor edit = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
            edit.putBoolean("RCTI18nUtil_allowRTL", Intrinsics.areEqual((Object) string, (Object) "true"));
            edit.apply();
        }
    }

    /* access modifiers changed from: private */
    public final Bundle getBundledConstants() {
        Locale locale = Locale.getDefault();
        String[] localeNames = LocalizationUtilsKt.getLocaleNames(getLocales());
        boolean z = TextUtils.getLayoutDirectionFromLocale(locale) == 1;
        Intrinsics.checkNotNull(locale);
        String regionCode = LocalizationUtilsKt.getRegionCode(locale);
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
        return BundleKt.bundleOf(TuplesKt.to(FirebaseAnalytics.Param.CURRENCY, LocalizationUtilsKt.getCurrencyCode(locale)), TuplesKt.to("decimalSeparator", String.valueOf(decimalFormatSymbols.getDecimalSeparator())), TuplesKt.to("digitGroupingSeparator", String.valueOf(decimalFormatSymbols.getGroupingSeparator())), TuplesKt.to("isoCurrencyCodes", LocalizationUtilsKt.getISOCurrencyCodes()), TuplesKt.to("isMetric", Boolean.valueOf(!CollectionsKt.contains(LocalizationUtilsKt.getUSES_IMPERIAL(), regionCode))), TuplesKt.to("isRTL", Boolean.valueOf(z)), TuplesKt.to("locale", localeNames[0]), TuplesKt.to("locales", localeNames), TuplesKt.to("region", regionCode), TuplesKt.to("timezone", TimeZone.getDefault().getID()));
    }

    private final List<Locale> getLocales() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext == null) {
            return CollectionsKt.emptyList();
        }
        Configuration configuration = reactContext.getResources().getConfiguration();
        ArrayList arrayList = new ArrayList();
        int size = configuration.getLocales().size();
        for (int i = 0; i < size; i++) {
            arrayList.add(configuration.getLocales().get(i));
        }
        return arrayList;
    }

    private final String getMeasurementSystem(Locale locale) {
        if (Build.VERSION.SDK_INT >= 28) {
            LocaleData.MeasurementSystem measurementSystem = LocaleData.getMeasurementSystem(ULocale.forLocale(locale));
            if (!Intrinsics.areEqual((Object) measurementSystem, (Object) LocaleData.MeasurementSystem.SI)) {
                if (!Intrinsics.areEqual((Object) measurementSystem, (Object) LocaleData.MeasurementSystem.UK)) {
                    if (Intrinsics.areEqual((Object) measurementSystem, (Object) LocaleData.MeasurementSystem.US)) {
                        return "us";
                    }
                }
                return "uk";
            }
        } else {
            if (!StringsKt.equals$default(LocalizationUtilsKt.getRegionCode(locale), "uk", false, 2, (Object) null)) {
                if (CollectionsKt.contains(LocalizationUtilsKt.getUSES_IMPERIAL(), LocalizationUtilsKt.getRegionCode(locale))) {
                    return "us";
                }
            }
            return "uk";
        }
        return "metric";
    }

    /* access modifiers changed from: private */
    public final List<Map<String, Object>> getPreferredLocales() {
        List<Map<String, Object>> arrayList = new ArrayList<>();
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        int size = localeListCompat.size();
        for (int i = 0; i < size; i++) {
            try {
                Locale locale = localeListCompat.get(i);
                if (locale != null) {
                    DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance(locale);
                    Pair[] pairArr = new Pair[10];
                    pairArr[0] = TuplesKt.to("languageTag", locale.toLanguageTag());
                    pairArr[1] = TuplesKt.to("regionCode", LocalizationUtilsKt.getRegionCode(locale));
                    pairArr[2] = TuplesKt.to("textDirection", TextUtils.getLayoutDirectionFromLocale(locale) == 1 ? "rtl" : "ltr");
                    pairArr[3] = TuplesKt.to(RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE, locale.getLanguage());
                    pairArr[4] = TuplesKt.to("decimalSeparator", String.valueOf(instance.getDecimalSeparator()));
                    pairArr[5] = TuplesKt.to("digitGroupingSeparator", String.valueOf(instance.getGroupingSeparator()));
                    pairArr[6] = TuplesKt.to("measurementSystem", getMeasurementSystem(locale));
                    pairArr[7] = TuplesKt.to("currencyCode", instance.getCurrency().getCurrencyCode());
                    pairArr[8] = TuplesKt.to("currencySymbol", Currency.getInstance(locale).getSymbol(locale));
                    pairArr[9] = TuplesKt.to("temperatureUnit", LocalizationUtilsKt.getTemperatureUnit(locale));
                    arrayList.add(MapsKt.mapOf(pairArr));
                }
            } catch (Exception e) {
                SentryLogcatAdapter.w("expo-localization", "Failed to get locale for index " + i, e);
            }
        }
        return arrayList;
    }

    private final boolean uses24HourClock() {
        if (getAppContext().getReactContext() == null) {
            return false;
        }
        return DateFormat.is24HourFormat(getAppContext().getReactContext());
    }

    private final String getCalendarType() {
        return Calendar.getInstance().getCalendarType().toString();
    }

    /* access modifiers changed from: private */
    public final List<Map<String, Object>> getCalendars() {
        return CollectionsKt.listOf(MapsKt.mapOf(TuplesKt.to("calendar", getCalendarType()), TuplesKt.to("uses24hourClock", Boolean.valueOf(uses24HourClock())), TuplesKt.to("firstWeekday", Integer.valueOf(Calendar.getInstance().getFirstDayOfWeek())), TuplesKt.to(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, Calendar.getInstance().getTimeZone().getID())));
    }
}
