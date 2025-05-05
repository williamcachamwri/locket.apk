package androidx.camera.core.impl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import androidx.arch.core.util.Function;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.QuirkSettings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuirkSettingsLoader implements Function<Context, QuirkSettings> {
    public static final String KEY_DEFAULT_QUIRK_ENABLED = "androidx.camera.core.quirks.DEFAULT_QUIRK_ENABLED";
    public static final String KEY_QUIRK_FORCE_DISABLED = "androidx.camera.core.quirks.FORCE_DISABLED";
    public static final String KEY_QUIRK_FORCE_ENABLED = "androidx.camera.core.quirks.FORCE_ENABLED";
    private static final String TAG = "QuirkSettingsLoader";

    public QuirkSettings apply(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, MetadataHolderService.class), 640).metaData;
            if (bundle != null) {
                return buildQuirkSettings(context, bundle);
            }
            Logger.w(TAG, "No metadata in MetadataHolderService.");
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.d(TAG, "QuirkSettings$MetadataHolderService is not found.");
            return null;
        }
    }

    private static QuirkSettings buildQuirkSettings(Context context, Bundle bundle) {
        boolean z = bundle.getBoolean(KEY_DEFAULT_QUIRK_ENABLED, true);
        String[] loadQuirks = loadQuirks(context, bundle, KEY_QUIRK_FORCE_ENABLED);
        String[] loadQuirks2 = loadQuirks(context, bundle, KEY_QUIRK_FORCE_DISABLED);
        Logger.d(TAG, "Loaded quirk settings from metadata:");
        Logger.d(TAG, "  KEY_DEFAULT_QUIRK_ENABLED = " + z);
        Logger.d(TAG, "  KEY_QUIRK_FORCE_ENABLED = " + Arrays.toString(loadQuirks));
        Logger.d(TAG, "  KEY_QUIRK_FORCE_DISABLED = " + Arrays.toString(loadQuirks2));
        return new QuirkSettings.Builder().setEnabledWhenDeviceHasQuirk(z).forceEnableQuirks(resolveQuirkNames(loadQuirks)).forceDisableQuirks(resolveQuirkNames(loadQuirks2)).build();
    }

    private static String[] loadQuirks(Context context, Bundle bundle, String str) {
        if (!bundle.containsKey(str)) {
            return new String[0];
        }
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            Logger.w(TAG, "Resource ID not found for key: " + str);
            return new String[0];
        }
        try {
            return context.getResources().getStringArray(i);
        } catch (Resources.NotFoundException e) {
            Logger.w(TAG, "Quirk class names resource not found: " + i, e);
            return new String[0];
        }
    }

    private static Set<Class<? extends Quirk>> resolveQuirkNames(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String resolveQuirkName : strArr) {
            Class<? extends Quirk> resolveQuirkName2 = resolveQuirkName(resolveQuirkName);
            if (resolveQuirkName2 != null) {
                hashSet.add(resolveQuirkName2);
            }
        }
        return hashSet;
    }

    private static Class<? extends Quirk> resolveQuirkName(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (Quirk.class.isAssignableFrom(cls)) {
                return cls;
            }
            Logger.w(TAG, str + " does not implement the Quirk interface.");
            return null;
        } catch (ClassNotFoundException e) {
            Logger.w(TAG, "Class not found: " + str, e);
            return null;
        }
    }

    public static class MetadataHolderService extends Service {
        private MetadataHolderService() {
        }

        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException();
        }
    }
}
