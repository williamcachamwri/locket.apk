package com.adsbynimbus.render.mraid;

import android.util.DisplayMetrics;
import android.view.View;
import com.adsbynimbus.Nimbus;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aF\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bH\u0000\u001a\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0012H\u0000\u001a\u001e\u0010\u0015\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0012H\u0000\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u001a"}, d2 = {"maxSize", "Lcom/adsbynimbus/render/mraid/Size;", "Landroid/view/View;", "getMaxSize", "(Landroid/view/View;)Lcom/adsbynimbus/render/mraid/Size;", "screenSize", "getScreenSize", "mraidEnv", "", "ifa", "limitAdTracking", "", "appId", "coppa", "sdk", "sdkVersion", "version", "dpToPx", "", "Landroid/util/DisplayMetrics;", "dp", "injectEnvironment", "script", "headIndex", "pxToDp", "px", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Environment.kt */
public final class EnvironmentKt {
    public static /* synthetic */ String mraidEnv$default(String str, boolean z, String str2, boolean z2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 16) != 0) {
            str3 = Nimbus.sdkName;
        }
        String str6 = str3;
        if ((i & 32) != 0) {
            str4 = "2.26.1";
        }
        String str7 = str4;
        if ((i & 64) != 0) {
            str5 = "3.0";
        }
        return mraidEnv(str, z, str2, z2, str6, str7, str5);
    }

    public static final String mraidEnv(String str, boolean z, String str2, boolean z2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "ifa");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str3, "sdk");
        Intrinsics.checkNotNullParameter(str4, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        Intrinsics.checkNotNullParameter(str5, "version");
        return "<script>window.MRAID_ENV={version:\"" + str5 + "\",sdk:\"" + str3 + "\",sdkVersion:\"" + str4 + "\",appId:\"" + str2 + "\",ifa:\"" + str + "\",limitAdTracking:" + z + AbstractJsonLexerKt.COMMA + StringsKt.trimIndent("coppa:" + z2 + "}</script>");
    }

    public static /* synthetic */ String injectEnvironment$default(String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.indexOf$default((CharSequence) str, "<head>", 0, false, 6, (Object) null);
        }
        return injectEnvironment(str, str2, i);
    }

    public static final String injectEnvironment(String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "script");
        if (i < 0) {
            return str2 + str;
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length());
        int i2 = i + 6;
        CharSequence charSequence = str;
        Intrinsics.checkNotNullExpressionValue(sb.insert(0, charSequence, 0, i2), "insert(...)");
        sb.insert(i2, str2);
        StringBuilder insert = sb.insert(str2.length() + i2, charSequence, i2, str.length());
        Intrinsics.checkNotNullExpressionValue(insert, "insert(...)");
        String sb2 = insert.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(length + s…ead, length)\n}.toString()");
        return sb2;
    }

    public static final int pxToDp(DisplayMetrics displayMetrics, int i) {
        Intrinsics.checkNotNullParameter(displayMetrics, "<this>");
        return MathKt.roundToInt(((float) i) / displayMetrics.density);
    }

    public static final int dpToPx(DisplayMetrics displayMetrics, int i) {
        Intrinsics.checkNotNullParameter(displayMetrics, "<this>");
        return MathKt.roundToInt(((float) i) * displayMetrics.density);
    }

    public static final Size getScreenSize(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_screenSize_$lambda$1");
        return new Size(pxToDp(displayMetrics, displayMetrics.widthPixels), pxToDp(displayMetrics, displayMetrics.heightPixels));
    }

    public static final Size getMaxSize(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_maxSize_$lambda$2");
        return new Size(pxToDp(displayMetrics, view.getRootView().getWidth()), pxToDp(displayMetrics, view.getRootView().getHeight()));
    }
}
