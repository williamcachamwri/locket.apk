package com.facebook.react.modules.appearance;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.firebase.ktx.BuildConfig;
import expo.modules.devlauncher.launcher.manifest.DevLauncherUserInterface;

@ReactModule(name = "Appearance")
public class AppearanceModule extends NativeAppearanceSpec {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "appearanceChanged";
    private String mColorScheme;
    private final OverrideColorScheme mOverrideColorScheme;

    public interface OverrideColorScheme {
        String getScheme();
    }

    public void addListener(String str) {
    }

    public void removeListeners(double d) {
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, (OverrideColorScheme) null);
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext, OverrideColorScheme overrideColorScheme) {
        super(reactApplicationContext);
        this.mColorScheme = DevLauncherUserInterface.LIGHT;
        this.mOverrideColorScheme = overrideColorScheme;
        this.mColorScheme = colorSchemeForCurrentConfiguration(reactApplicationContext);
    }

    private String colorSchemeForCurrentConfiguration(Context context) {
        OverrideColorScheme overrideColorScheme = this.mOverrideColorScheme;
        if (overrideColorScheme != null) {
            return overrideColorScheme.getScheme();
        }
        return (context.getResources().getConfiguration().uiMode & 48) != 32 ? DevLauncherUserInterface.LIGHT : DevLauncherUserInterface.DARK;
    }

    public void setColorScheme(String str) {
        if (str.equals(DevLauncherUserInterface.DARK)) {
            AppCompatDelegate.setDefaultNightMode(2);
        } else if (str.equals(DevLauncherUserInterface.LIGHT)) {
            AppCompatDelegate.setDefaultNightMode(1);
        } else if (str.equals(BuildConfig.VERSION_NAME)) {
            AppCompatDelegate.setDefaultNightMode(-1);
        }
    }

    public String getColorScheme() {
        Context currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            currentActivity = getReactApplicationContext();
        }
        String colorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(currentActivity);
        this.mColorScheme = colorSchemeForCurrentConfiguration;
        return colorSchemeForCurrentConfiguration;
    }

    public void onConfigurationChanged(Context context) {
        String colorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(context);
        if (!this.mColorScheme.equals(colorSchemeForCurrentConfiguration)) {
            this.mColorScheme = colorSchemeForCurrentConfiguration;
            emitAppearanceChanged(colorSchemeForCurrentConfiguration);
        }
    }

    public void emitAppearanceChanged(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("colorScheme", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent(APPEARANCE_CHANGED_EVENT_NAME, createMap);
        }
    }
}
