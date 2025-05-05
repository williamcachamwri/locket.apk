package expo.modules.application;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001e\u0010\u0004\u001a\u00020\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"getLongVersionCode", "", "info", "Landroid/content/pm/PackageInfo;", "getPackageInfoCompat", "Landroid/content/pm/PackageManager;", "packageName", "", "flags", "", "expo-application_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ApplicationModule.kt */
public final class ApplicationModuleKt {
    static /* synthetic */ PackageInfo getPackageInfoCompat$default(PackageManager packageManager, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return getPackageInfoCompat(packageManager, str, i);
    }

    /* access modifiers changed from: private */
    public static final PackageInfo getPackageInfoCompat(PackageManager packageManager, String str, int i) {
        PackageInfo packageInfo;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                packageInfo = packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of((long) i));
            } else {
                packageInfo = packageManager.getPackageInfo(str, i);
            }
            Intrinsics.checkNotNull(packageInfo);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            throw new ApplicationPackageNameNotFoundException(e);
        }
    }

    /* access modifiers changed from: private */
    public static final long getLongVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return (long) packageInfo.versionCode;
    }
}
