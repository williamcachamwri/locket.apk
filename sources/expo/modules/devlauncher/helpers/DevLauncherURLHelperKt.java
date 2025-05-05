package expo.modules.devlauncher.helpers;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"hasUrlQueryParam", "", "uri", "Landroid/net/Uri;", "isDevLauncherUrl", "replaceEXPScheme", "scheme", "", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherURLHelper.kt */
public final class DevLauncherURLHelperKt {
    public static final Uri replaceEXPScheme(Uri uri, String str) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(str, "scheme");
        if (!Intrinsics.areEqual((Object) uri.getScheme(), (Object) "exp")) {
            return uri;
        }
        Uri build = uri.buildUpon().scheme(str).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final boolean isDevLauncherUrl(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual((Object) uri.getHost(), (Object) "expo-development-client");
    }

    public static final boolean hasUrlQueryParam(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return uri.getQueryParameter("url") != null;
    }
}
