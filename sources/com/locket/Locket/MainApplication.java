package com.locket.Locket;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import com.tencent.mmkv.MMKV;
import expo.modules.ApplicationLifecycleDispatcher;
import expo.modules.ReactNativeHostWrapper;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
    private final ReactNativeHost mReactNativeHost = new ReactNativeHostWrapper(this, new DefaultReactNativeHost(this) {
        /* access modifiers changed from: protected */
        public String getJSMainModuleName() {
            return ".expo/.virtual-metro-entry";
        }

        public boolean getUseDeveloperSupport() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean isNewArchEnabled() {
            return false;
        }

        /* access modifiers changed from: protected */
        public List<ReactPackage> getPackages() {
            ArrayList<ReactPackage> packages = new PackageList((ReactNativeHost) this).getPackages();
            packages.add(new LocketPackage());
            return packages;
        }

        /* access modifiers changed from: protected */
        public Boolean isHermesEnabled() {
            return true;
        }
    });

    public ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    public void onCreate() {
        super.onCreate();
        SoLoader.init((Context) this, false);
        initializeFirestore(FirebaseApp.initializeApp(this));
        ApplicationLifecycleDispatcher.onApplicationCreate(this);
        MMKV.initialize((Context) this);
    }

    private void initializeFirestore(FirebaseApp firebaseApp) {
        FirebaseFirestore.getInstance(firebaseApp, "locket").setFirestoreSettings(new FirebaseFirestoreSettings.Builder().setLocalCacheSettings(PersistentCacheSettings.newBuilder().setSizeBytes(838860800).build()).build());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ApplicationLifecycleDispatcher.onConfigurationChanged(this, configuration);
    }
}
