package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class ai {

    /* renamed from: a  reason: collision with root package name */
    private static final s f78a = new s("PhoneskyVerificationUtils");

    public static int a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.android.vending", 64);
            if (packageInfo.applicationInfo == null || !packageInfo.applicationInfo.enabled || !c(packageInfo.signatures)) {
                return 0;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static boolean b(Context context) {
        try {
            if (!context.getPackageManager().getApplicationInfo("com.android.vending", 0).enabled || !c(context.getPackageManager().getPackageInfo("com.android.vending", 64).signatures)) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static boolean c(Signature[] signatureArr) {
        if (signatureArr == null || (r1 = signatureArr.length) == 0) {
            f78a.e("Phonesky package is not signed -- possibly self-built package. Could not verify.", new Object[0]);
            return false;
        }
        for (Signature byteArray : signatureArr) {
            String a2 = ah.a(byteArray.toByteArray());
            if ("8P1sW0EPJcslw7UzRsiXL64w-O50Ed-RBICtay1g24M".equals(a2)) {
                return true;
            }
            if ((Build.TAGS.contains("dev-keys") || Build.TAGS.contains("test-keys")) && "GXWy8XF3vIml3_MfnmSmyuKBpT3B0dWbHRR_4cgq-gA".equals(a2)) {
                return true;
            }
        }
        return false;
    }
}
