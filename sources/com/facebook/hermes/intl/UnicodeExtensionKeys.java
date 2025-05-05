package com.facebook.hermes.intl;

import android.icu.text.NumberingSystem;
import android.icu.text.RuleBasedCollator;
import android.icu.util.Calendar;
import android.icu.util.ULocale;
import androidx.core.text.util.LocalePreferences;
import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import io.sentry.SentryBaseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnicodeExtensionKeys {
    public static String CALENDAR = "calendar";
    public static String CALENDAR_CANON = "ca";
    public static String COLLATION = "collation";
    public static String COLLATION_CANON = "co";
    public static String COLLATION_CASEFIRST = "colcasefirst";
    public static String COLLATION_CASEFIRST_CANON = "kf";
    public static String COLLATION_NUMERIC = "colnumeric";
    public static String COLLATION_NUMERIC_CANON = "kn";
    public static String HOURCYCLE = "hours";
    public static String HOURCYCLE_CANON = "hc";
    public static String NUMERINGSYSTEM = "numbers";
    public static String NUMERINGSYSTEM_CANON = "nu";
    private static Map<String, String> s_calendarAliasMappings = new HashMap<String, String>() {
        {
            put(LocalePreferences.CalendarType.GREGORIAN, "gregory");
        }
    };
    private static HashMap<String, String> s_canonicalkey_icukey_map = new HashMap<String, String>() {
        {
            put(UnicodeExtensionKeys.CALENDAR_CANON, UnicodeExtensionKeys.CALENDAR);
            put(UnicodeExtensionKeys.NUMERINGSYSTEM_CANON, UnicodeExtensionKeys.NUMERINGSYSTEM);
            put(UnicodeExtensionKeys.HOURCYCLE_CANON, UnicodeExtensionKeys.HOURCYCLE);
            put(UnicodeExtensionKeys.COLLATION_CANON, UnicodeExtensionKeys.COLLATION);
            put(UnicodeExtensionKeys.COLLATION_NUMERIC_CANON, UnicodeExtensionKeys.COLLATION_NUMERIC);
            put(UnicodeExtensionKeys.COLLATION_CASEFIRST_CANON, UnicodeExtensionKeys.COLLATION_CASEFIRST);
        }
    };
    private static final Map<String, String> s_collationAliasMappings = new HashMap<String, String>() {
        {
            put("dictionary", "dict");
            put("phonebook", "phonebk");
            put("traditional", "trad");
            put("gb2312han", "gb2312");
        }
    };
    private static HashMap<String, String> s_icukey_canonicalkey_map = new HashMap<String, String>() {
        {
            put(UnicodeExtensionKeys.CALENDAR, UnicodeExtensionKeys.CALENDAR_CANON);
            put(UnicodeExtensionKeys.NUMERINGSYSTEM, UnicodeExtensionKeys.NUMERINGSYSTEM_CANON);
            put(UnicodeExtensionKeys.HOURCYCLE, UnicodeExtensionKeys.HOURCYCLE_CANON);
            put(UnicodeExtensionKeys.COLLATION, UnicodeExtensionKeys.COLLATION_CANON);
            put(UnicodeExtensionKeys.COLLATION_NUMERIC, UnicodeExtensionKeys.COLLATION_NUMERIC_CANON);
            put(UnicodeExtensionKeys.COLLATION_CASEFIRST, UnicodeExtensionKeys.COLLATION_CASEFIRST_CANON);
        }
    };
    private static Map<String, String> s_numberSystemAliasMappings = new HashMap<String, String>() {
        {
            put("traditional", "traditio");
        }
    };
    private static Map<String, String[]> s_validKeywords = new HashMap<String, String[]>() {
        {
            put("nu", new String[]{"adlm", "ahom", "arab", "arabext", "bali", "beng", "bhks", "brah", "cakm", "cham", "deva", "diak", "fullwide", "gong", "gonm", "gujr", "guru", "hanidec", "hmng", "hmnp", SentryBaseEvent.DEFAULT_PLATFORM, "kali", "khmr", "knda", "lana", "lanatham", "laoo", "latn", "lepc", "limb", "mathbold", "mathdbl", "mathmono", "mathsanb", "mathsans", "mlym", "modi", "mong", "mroo", "mtei", "mymr", "mymrshan", "mymrtlng", "newa", "nkoo", "olck", "orya", "osma", "rohg", "saur", "segment", "shrd", "sind", "sinh", "sora", "sund", "takr", "talu", "tamldec", "telu", "thai", "tibt", "tirh", "vaii", "wara", "wcho"});
            put(Constants.COLLATION_EXTENSION_KEY_SHORT, new String[]{"big5han", "compat", "dict", DevicePublicKeyStringDef.DIRECT, "ducet", "emoji", "eor", "gb2312", "phonebk", "phonetic", "pinyin", "reformed", "searchjl", "stroke", "trad", "unihan", "zhuyin"});
            put("ca", new String[]{"buddhist", LocalePreferences.CalendarType.CHINESE, "coptic", LocalePreferences.CalendarType.DANGI, "ethioaa", "ethiopic", "gregory", LocalePreferences.CalendarType.HEBREW, LocalePreferences.CalendarType.INDIAN, LocalePreferences.CalendarType.ISLAMIC, LocalePreferences.CalendarType.ISLAMIC_UMALQURA, LocalePreferences.CalendarType.ISLAMIC_TBLA, LocalePreferences.CalendarType.ISLAMIC_CIVIL, LocalePreferences.CalendarType.ISLAMIC_RGSA, "iso8601", "japanese", LocalePreferences.CalendarType.PERSIAN, "roc"});
        }
    };

    public static String CanonicalKeyToICUKey(String str) {
        return s_canonicalkey_icukey_map.containsKey(str) ? s_canonicalkey_icukey_map.get(str) : str;
    }

    public static String ICUKeyToCanonicalKey(String str) {
        return s_icukey_canonicalkey_map.containsKey(str) ? s_icukey_canonicalkey_map.get(str) : str;
    }

    public static String resolveCollationAlias(String str) {
        Map<String, String> map = s_collationAliasMappings;
        if (!map.containsKey(str)) {
            return str;
        }
        return map.get(str);
    }

    public static String resolveCalendarAlias(String str) {
        if (!s_calendarAliasMappings.containsKey(str)) {
            return str;
        }
        return s_calendarAliasMappings.get(str);
    }

    public static String resolveNumberSystemAlias(String str) {
        if (!s_numberSystemAliasMappings.containsKey(str)) {
            return str;
        }
        return s_numberSystemAliasMappings.get(str);
    }

    public static boolean isValidKeyword(String str, String str2, ILocaleObject iLocaleObject) throws JSRangeErrorException {
        ULocale uLocale = (ULocale) iLocaleObject.getLocale();
        String[] strArr = new String[0];
        if (str.equals(Constants.COLLATION_EXTENSION_KEY_SHORT)) {
            if (str2.equals(Constants.COLLATION_STANDARD) || str2.equals("search")) {
                return false;
            }
            strArr = RuleBasedCollator.getKeywordValuesForLocale(Constants.COLLATION_EXTENSION_KEY_SHORT, uLocale, false);
        } else if (str.equals("ca")) {
            strArr = Calendar.getKeywordValuesForLocale("ca", uLocale, false);
        } else if (str.equals("nu")) {
            strArr = NumberingSystem.getAvailableNames();
        }
        if (strArr.length == 0) {
            return true;
        }
        return Arrays.asList(strArr).contains(str2);
    }

    public static Object resolveKnownAliases(String str, Object obj) {
        if (str.equals("ca") && JSObjects.isString(obj)) {
            return resolveCalendarAlias((String) obj);
        }
        if (str.equals("nu") && JSObjects.isString(obj)) {
            return resolveNumberSystemAlias((String) obj);
        }
        if (str.equals(Constants.COLLATION_EXTENSION_KEY_SHORT) && JSObjects.isString(obj)) {
            return resolveCollationAlias((String) obj);
        }
        if (!str.equals(Constants.COLLATION_EXTENSION_PARAM_NUMERIC_SHORT) || !JSObjects.isString(obj) || !obj.equals("yes")) {
            return ((str.equals(Constants.COLLATION_EXTENSION_PARAM_NUMERIC_SHORT) || str.equals(Constants.COLLATION_EXTENSION_PARAM_CASEFIRST_SHORT)) && JSObjects.isString(obj) && obj.equals(SVGParser.XML_STYLESHEET_ATTR_ALTERNATE_NO)) ? JSObjects.newString(Constants.CASEFIRST_FALSE) : obj;
        }
        return JSObjects.newString("true");
    }
}
