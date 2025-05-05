package expo.modules.devlauncher.helpers;

import android.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001¨\u0006\u0006"}, d2 = {"RGBAtoARGB", "", "rgba", "isValidColor", "", "color", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherColorsHelper.kt */
public final class DevLauncherColorsHelperKt {
    public static final String RGBAtoARGB(String str) {
        if (str == null) {
            return null;
        }
        if (!StringsKt.startsWith$default(str, "#", false, 2, (Object) null) || str.length() != 9) {
            return str;
        }
        String substring = str.substring(7, 9);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String substring2 = str.substring(1, 7);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        return "#" + substring + substring2;
    }

    public static final boolean isValidColor(String str) {
        if (str == null) {
            return false;
        }
        try {
            Color.parseColor(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
