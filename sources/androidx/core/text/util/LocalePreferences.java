package androidx.core.text.util;

import android.icu.number.NumberFormatter;
import android.icu.text.DateFormat;
import android.icu.text.DateTimePatternGenerator;
import android.icu.util.MeasureUnit;
import android.os.Build;
import android.text.format.DateFormat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public final class LocalePreferences {
    private static final String TAG = "LocalePreferences";
    private static final String[] WEATHER_FAHRENHEIT_COUNTRIES = {"BS", "BZ", "KY", "PR", "PW", "US"};

    public static class HourCycle {
        public static final String DEFAULT = "";
        public static final String H11 = "h11";
        public static final String H12 = "h12";
        public static final String H23 = "h23";
        public static final String H24 = "h24";
        private static final String U_EXTENSION_TAG = "hc";

        @Retention(RetentionPolicy.SOURCE)
        public @interface HourCycleTypes {
        }

        private HourCycle() {
        }
    }

    public static String getHourCycle() {
        return getHourCycle(true);
    }

    public static String getHourCycle(Locale locale) {
        return getHourCycle(locale, true);
    }

    public static String getHourCycle(boolean z) {
        return getHourCycle(Api24Impl.getDefaultLocale(), z);
    }

    public static String getHourCycle(Locale locale, boolean z) {
        String unicodeLocaleType = getUnicodeLocaleType("hc", "", locale, z);
        if (unicodeLocaleType != null) {
            return unicodeLocaleType;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getHourCycle(locale);
        }
        return getBaseHourCycle(locale);
    }

    public static class CalendarType {
        public static final String CHINESE = "chinese";
        public static final String DANGI = "dangi";
        public static final String DEFAULT = "";
        public static final String GREGORIAN = "gregorian";
        public static final String HEBREW = "hebrew";
        public static final String INDIAN = "indian";
        public static final String ISLAMIC = "islamic";
        public static final String ISLAMIC_CIVIL = "islamic-civil";
        public static final String ISLAMIC_RGSA = "islamic-rgsa";
        public static final String ISLAMIC_TBLA = "islamic-tbla";
        public static final String ISLAMIC_UMALQURA = "islamic-umalqura";
        public static final String PERSIAN = "persian";
        private static final String U_EXTENSION_TAG = "ca";

        @Retention(RetentionPolicy.SOURCE)
        public @interface CalendarTypes {
        }

        private CalendarType() {
        }
    }

    public static String getCalendarType() {
        return getCalendarType(true);
    }

    public static String getCalendarType(Locale locale) {
        return getCalendarType(locale, true);
    }

    public static String getCalendarType(boolean z) {
        return getCalendarType(Api24Impl.getDefaultLocale(), z);
    }

    public static String getCalendarType(Locale locale, boolean z) {
        String unicodeLocaleType = getUnicodeLocaleType("ca", "", locale, z);
        if (unicodeLocaleType != null) {
            return unicodeLocaleType;
        }
        return Api24Impl.getCalendarType(locale);
    }

    public static class TemperatureUnit {
        public static final String CELSIUS = "celsius";
        public static final String DEFAULT = "";
        public static final String FAHRENHEIT = "fahrenhe";
        public static final String KELVIN = "kelvin";
        private static final String U_EXTENSION_TAG = "mu";

        @Retention(RetentionPolicy.SOURCE)
        public @interface TemperatureUnits {
        }

        private TemperatureUnit() {
        }
    }

    public static String getTemperatureUnit() {
        return getTemperatureUnit(true);
    }

    public static String getTemperatureUnit(Locale locale) {
        return getTemperatureUnit(locale, true);
    }

    public static String getTemperatureUnit(boolean z) {
        return getTemperatureUnit(Api24Impl.getDefaultLocale(), z);
    }

    public static String getTemperatureUnit(Locale locale, boolean z) {
        String unicodeLocaleType = getUnicodeLocaleType("mu", "", locale, z);
        if (unicodeLocaleType != null) {
            return unicodeLocaleType;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getResolvedTemperatureUnit(locale);
        }
        return getTemperatureHardCoded(locale);
    }

    public static class FirstDayOfWeek {
        public static final String DEFAULT = "";
        public static final String FRIDAY = "fri";
        public static final String MONDAY = "mon";
        public static final String SATURDAY = "sat";
        public static final String SUNDAY = "sun";
        public static final String THURSDAY = "thu";
        public static final String TUESDAY = "tue";
        private static final String U_EXTENSION_TAG = "fw";
        public static final String WEDNESDAY = "wed";

        @Retention(RetentionPolicy.SOURCE)
        public @interface Days {
        }

        private FirstDayOfWeek() {
        }
    }

    public static String getFirstDayOfWeek() {
        return getFirstDayOfWeek(true);
    }

    public static String getFirstDayOfWeek(Locale locale) {
        return getFirstDayOfWeek(locale, true);
    }

    public static String getFirstDayOfWeek(boolean z) {
        return getFirstDayOfWeek(Api24Impl.getDefaultLocale(), z);
    }

    public static String getFirstDayOfWeek(Locale locale, boolean z) {
        String unicodeLocaleType = getUnicodeLocaleType("fw", "", locale, z);
        return unicodeLocaleType != null ? unicodeLocaleType : getBaseFirstDayOfWeek(locale);
    }

    private static String getUnicodeLocaleType(String str, String str2, Locale locale, boolean z) {
        String unicodeLocaleType = locale.getUnicodeLocaleType(str);
        if (unicodeLocaleType != null) {
            return unicodeLocaleType;
        }
        if (!z) {
            return str2;
        }
        return null;
    }

    private static String getTemperatureHardCoded(Locale locale) {
        return Arrays.binarySearch(WEATHER_FAHRENHEIT_COUNTRIES, locale.getCountry()) >= 0 ? TemperatureUnit.FAHRENHEIT : TemperatureUnit.CELSIUS;
    }

    private static String getBaseHourCycle(Locale locale) {
        return DateFormat.getBestDateTimePattern(locale, "jm").contains("H") ? HourCycle.H23 : HourCycle.H12;
    }

    private static String getBaseFirstDayOfWeek(Locale locale) {
        return getStringOfFirstDayOfWeek(Calendar.getInstance(locale).getFirstDayOfWeek());
    }

    private static String getStringOfFirstDayOfWeek(int i) {
        return (i < 1 || i > 7) ? "" : new String[]{FirstDayOfWeek.SUNDAY, FirstDayOfWeek.MONDAY, FirstDayOfWeek.TUESDAY, FirstDayOfWeek.WEDNESDAY, FirstDayOfWeek.THURSDAY, FirstDayOfWeek.FRIDAY, FirstDayOfWeek.SATURDAY}[i - 1];
    }

    private static Locale getDefaultLocale() {
        return Locale.getDefault();
    }

    private static class Api24Impl {
        static String getCalendarType(Locale locale) {
            return android.icu.util.Calendar.getInstance(locale).getType();
        }

        static Locale getDefaultLocale() {
            return Locale.getDefault(Locale.Category.FORMAT);
        }

        private Api24Impl() {
        }
    }

    private static class Api33Impl {
        static String getResolvedTemperatureUnit(Locale locale) {
            String identifier = NumberFormatter.with().usage("weather").unit(MeasureUnit.CELSIUS).locale(locale).format(1).getOutputUnit().getIdentifier();
            if (identifier.startsWith(TemperatureUnit.FAHRENHEIT)) {
                return TemperatureUnit.FAHRENHEIT;
            }
            return identifier;
        }

        static String getHourCycle(Locale locale) {
            return getHourCycleType(DateTimePatternGenerator.getInstance(locale).getDefaultHourCycle());
        }

        private static String getHourCycleType(DateFormat.HourCycle hourCycle) {
            int i = AnonymousClass1.$SwitchMap$android$icu$text$DateFormat$HourCycle[hourCycle.ordinal()];
            if (i == 1) {
                return HourCycle.H11;
            }
            if (i == 2) {
                return HourCycle.H12;
            }
            if (i != 3) {
                return i != 4 ? "" : HourCycle.H24;
            }
            return HourCycle.H23;
        }

        private Api33Impl() {
        }
    }

    /* renamed from: androidx.core.text.util.LocalePreferences$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$DateFormat$HourCycle;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                android.icu.text.DateFormat$HourCycle[] r0 = android.icu.text.DateFormat.HourCycle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$icu$text$DateFormat$HourCycle = r0
                android.icu.text.DateFormat$HourCycle r1 = android.icu.text.DateFormat.HourCycle.HOUR_CYCLE_11     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$icu$text$DateFormat$HourCycle     // Catch:{ NoSuchFieldError -> 0x001d }
                android.icu.text.DateFormat$HourCycle r1 = android.icu.text.DateFormat.HourCycle.HOUR_CYCLE_12     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$icu$text$DateFormat$HourCycle     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.icu.text.DateFormat$HourCycle r1 = android.icu.text.DateFormat.HourCycle.HOUR_CYCLE_23     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$android$icu$text$DateFormat$HourCycle     // Catch:{ NoSuchFieldError -> 0x0033 }
                android.icu.text.DateFormat$HourCycle r1 = android.icu.text.DateFormat.HourCycle.HOUR_CYCLE_24     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.util.LocalePreferences.AnonymousClass1.<clinit>():void");
        }
    }

    private LocalePreferences() {
    }
}
