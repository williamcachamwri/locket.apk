package androidx.core.text;

import android.text.TextUtils;
import java.util.Locale;

public final class TextUtilsCompat {
    public static String htmlEncode(String str) {
        return TextUtils.htmlEncode(str);
    }

    public static int getLayoutDirectionFromLocale(Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }

    private TextUtilsCompat() {
    }
}
