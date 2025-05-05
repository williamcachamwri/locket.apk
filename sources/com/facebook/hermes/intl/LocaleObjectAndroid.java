package com.facebook.hermes.intl;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class LocaleObjectAndroid implements ILocaleObject<Locale> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean mIsDirty;
    private Locale mLocale;
    private ParsedLocaleIdentifier mParsedLocaleIdentifier;

    private LocaleObjectAndroid(Locale locale) {
        this.mParsedLocaleIdentifier = null;
        this.mIsDirty = false;
        this.mLocale = locale;
    }

    private LocaleObjectAndroid(String str) throws JSRangeErrorException {
        this.mLocale = null;
        this.mParsedLocaleIdentifier = null;
        this.mIsDirty = false;
        this.mParsedLocaleIdentifier = LocaleIdentifier.parseLocaleId(str);
        reInitFromParsedLocaleIdentifier();
    }

    private LocaleObjectAndroid(ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException {
        this.mLocale = null;
        this.mIsDirty = false;
        this.mParsedLocaleIdentifier = parsedLocaleIdentifier;
        reInitFromParsedLocaleIdentifier();
    }

    private void ensureParsedLocaleIdentifier() throws JSRangeErrorException {
        if (this.mParsedLocaleIdentifier == null) {
            this.mParsedLocaleIdentifier = LocaleIdentifier.parseLocaleId(this.mLocale.toLanguageTag());
        }
    }

    private void reInitFromParsedLocaleIdentifier() throws JSRangeErrorException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        StringBuffer stringBuffer4 = new StringBuffer();
        if (this.mParsedLocaleIdentifier.languageIdentifier.languageSubtag != null && !this.mParsedLocaleIdentifier.languageIdentifier.languageSubtag.isEmpty()) {
            stringBuffer2.append(this.mParsedLocaleIdentifier.languageIdentifier.languageSubtag);
        }
        if (this.mParsedLocaleIdentifier.languageIdentifier.scriptSubtag != null && !this.mParsedLocaleIdentifier.languageIdentifier.scriptSubtag.isEmpty()) {
            stringBuffer3.append(this.mParsedLocaleIdentifier.languageIdentifier.scriptSubtag);
        }
        if (this.mParsedLocaleIdentifier.languageIdentifier.regionSubtag != null && !this.mParsedLocaleIdentifier.languageIdentifier.regionSubtag.isEmpty()) {
            stringBuffer4.append(this.mParsedLocaleIdentifier.languageIdentifier.regionSubtag);
        }
        LocaleIdentifier.replaceLanguageSubtagIfNeeded(stringBuffer2, stringBuffer3, stringBuffer4);
        if (stringBuffer2.length() > 0) {
            stringBuffer.append(stringBuffer2.toString());
        }
        if (stringBuffer3.length() > 0) {
            stringBuffer.append("-");
            stringBuffer.append(stringBuffer3.toString());
        }
        if (stringBuffer4.length() > 0) {
            stringBuffer.append("-");
            stringBuffer.append(LocaleIdentifier.replaceRegionSubtagIfNeeded(stringBuffer4));
        }
        if (this.mParsedLocaleIdentifier.languageIdentifier.variantSubtagList != null && !this.mParsedLocaleIdentifier.languageIdentifier.variantSubtagList.isEmpty()) {
            stringBuffer.append("-");
            stringBuffer.append(TextUtils.join("-", this.mParsedLocaleIdentifier.languageIdentifier.variantSubtagList));
        }
        if (this.mParsedLocaleIdentifier.otherExtensionsMap != null) {
            for (Map.Entry next : this.mParsedLocaleIdentifier.otherExtensionsMap.entrySet()) {
                stringBuffer.append("-");
                stringBuffer.append(next.getKey());
                stringBuffer.append("-");
                stringBuffer.append(TextUtils.join("-", (Iterable) next.getValue()));
            }
        }
        if (!(this.mParsedLocaleIdentifier.transformedLanguageIdentifier == null && this.mParsedLocaleIdentifier.transformedExtensionFields == null)) {
            stringBuffer.append("-t-");
            StringBuffer stringBuffer5 = new StringBuffer();
            if (this.mParsedLocaleIdentifier.transformedLanguageIdentifier != null) {
                stringBuffer5.append(this.mParsedLocaleIdentifier.transformedLanguageIdentifier.languageSubtag);
                if (this.mParsedLocaleIdentifier.transformedLanguageIdentifier.scriptSubtag != null) {
                    stringBuffer5.append("-");
                    stringBuffer5.append(this.mParsedLocaleIdentifier.transformedLanguageIdentifier.scriptSubtag);
                }
                if (this.mParsedLocaleIdentifier.transformedLanguageIdentifier.regionSubtag != null) {
                    stringBuffer5.append("-");
                    stringBuffer5.append(this.mParsedLocaleIdentifier.transformedLanguageIdentifier.regionSubtag);
                }
                if (this.mParsedLocaleIdentifier.transformedLanguageIdentifier.variantSubtagList != null && !this.mParsedLocaleIdentifier.transformedLanguageIdentifier.variantSubtagList.isEmpty()) {
                    stringBuffer5.append("-");
                    stringBuffer5.append(TextUtils.join("-", this.mParsedLocaleIdentifier.transformedLanguageIdentifier.variantSubtagList));
                }
            }
            if (this.mParsedLocaleIdentifier.transformedExtensionFields != null) {
                for (Map.Entry next2 : this.mParsedLocaleIdentifier.transformedExtensionFields.entrySet()) {
                    stringBuffer5.append("-" + ((String) next2.getKey()));
                    Iterator it = ((ArrayList) next2.getValue()).iterator();
                    while (it.hasNext()) {
                        stringBuffer5.append("-" + ((String) it.next()));
                    }
                }
                if (stringBuffer5.length() > 0 && stringBuffer5.charAt(0) == '-') {
                    stringBuffer5.deleteCharAt(0);
                }
            }
            stringBuffer.append(stringBuffer5.toString());
        }
        if (!(this.mParsedLocaleIdentifier.unicodeExtensionAttributes == null && this.mParsedLocaleIdentifier.unicodeExtensionKeywords == null)) {
            stringBuffer.append("-u-");
            StringBuffer stringBuffer6 = new StringBuffer();
            if (this.mParsedLocaleIdentifier.unicodeExtensionAttributes != null) {
                stringBuffer6.append(TextUtils.join("-", this.mParsedLocaleIdentifier.unicodeExtensionAttributes));
            }
            if (this.mParsedLocaleIdentifier.unicodeExtensionKeywords != null) {
                for (Map.Entry next3 : this.mParsedLocaleIdentifier.unicodeExtensionKeywords.entrySet()) {
                    stringBuffer6.append("-" + ((String) next3.getKey()));
                    Iterator it2 = ((ArrayList) next3.getValue()).iterator();
                    while (it2.hasNext()) {
                        stringBuffer6.append("-" + ((String) it2.next()));
                    }
                }
            }
            if (stringBuffer6.length() > 0 && stringBuffer6.charAt(0) == '-') {
                stringBuffer6.deleteCharAt(0);
            }
            stringBuffer.append(stringBuffer6.toString());
        }
        if (this.mParsedLocaleIdentifier.puExtensions != null) {
            stringBuffer.append("-x-");
            stringBuffer.append(TextUtils.join("-", this.mParsedLocaleIdentifier.puExtensions));
        }
        try {
            this.mLocale = Locale.forLanguageTag(stringBuffer.toString());
            this.mIsDirty = false;
        } catch (RuntimeException e) {
            throw new JSRangeErrorException(e.getMessage());
        }
    }

    private void ensureNotDirty() throws JSRangeErrorException {
        if (this.mIsDirty) {
            try {
                reInitFromParsedLocaleIdentifier();
                this.mIsDirty = false;
            } catch (RuntimeException e) {
                throw new JSRangeErrorException(e.getMessage());
            }
        }
    }

    public ArrayList<String> getUnicodeExtensions(String str) throws JSRangeErrorException {
        ArrayList<String> arrayList;
        ensureNotDirty();
        ensureParsedLocaleIdentifier();
        if (this.mParsedLocaleIdentifier.unicodeExtensionKeywords == null || (arrayList = this.mParsedLocaleIdentifier.unicodeExtensionKeywords.get(str)) == null) {
            return new ArrayList<>();
        }
        return arrayList;
    }

    public HashMap<String, String> getUnicodeExtensions() throws JSRangeErrorException {
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.mParsedLocaleIdentifier.unicodeExtensionKeywords != null) {
            for (String next : this.mParsedLocaleIdentifier.unicodeExtensionKeywords.keySet()) {
                hashMap.put(next, TextUtils.join("-", this.mParsedLocaleIdentifier.unicodeExtensionKeywords.get(next)));
            }
        }
        return hashMap;
    }

    public void setUnicodeExtensions(String str, ArrayList<String> arrayList) throws JSRangeErrorException {
        ensureNotDirty();
        ensureParsedLocaleIdentifier();
        if (this.mParsedLocaleIdentifier.unicodeExtensionKeywords == null) {
            this.mParsedLocaleIdentifier.unicodeExtensionKeywords = new TreeMap<>();
        }
        if (!this.mParsedLocaleIdentifier.unicodeExtensionKeywords.containsKey(str)) {
            this.mParsedLocaleIdentifier.unicodeExtensionKeywords.put(str, new ArrayList());
        }
        this.mParsedLocaleIdentifier.unicodeExtensionKeywords.get(str).clear();
        this.mParsedLocaleIdentifier.unicodeExtensionKeywords.get(str).addAll(arrayList);
        this.mIsDirty = true;
    }

    public Locale getLocale() throws JSRangeErrorException {
        ensureNotDirty();
        return this.mLocale;
    }

    public Locale getLocaleWithoutExtensions() throws JSRangeErrorException {
        ensureNotDirty();
        ensureParsedLocaleIdentifier();
        ParsedLocaleIdentifier parsedLocaleIdentifier = new ParsedLocaleIdentifier();
        parsedLocaleIdentifier.languageIdentifier = this.mParsedLocaleIdentifier.languageIdentifier;
        return new LocaleObjectAndroid(parsedLocaleIdentifier).getLocale();
    }

    public String toCanonicalTag() throws JSRangeErrorException {
        return getLocale().toLanguageTag();
    }

    public String toCanonicalTagWithoutExtensions() throws JSRangeErrorException {
        return getLocaleWithoutExtensions().toLanguageTag();
    }

    public static ILocaleObject<Locale> createFromLocaleId(String str) throws JSRangeErrorException {
        return new LocaleObjectAndroid(str);
    }

    public static ILocaleObject<Locale> createFromLocale(Locale locale) {
        return new LocaleObjectAndroid(locale);
    }

    public static ILocaleObject<Locale> createDefault() {
        return new LocaleObjectAndroid(Locale.getDefault());
    }

    public ILocaleObject<Locale> cloneObject() throws JSRangeErrorException {
        ensureNotDirty();
        return new LocaleObjectAndroid(this.mLocale);
    }
}
