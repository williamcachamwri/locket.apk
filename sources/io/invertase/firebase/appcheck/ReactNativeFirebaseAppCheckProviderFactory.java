package io.invertase.firebase.appcheck;

import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import java.util.HashMap;

public class ReactNativeFirebaseAppCheckProviderFactory implements AppCheckProviderFactory {
    private static final String LOGTAG = "RNFBAppCheck";
    public HashMap<String, ReactNativeFirebaseAppCheckProvider> providers = new HashMap<>();

    public void configure(String str, String str2, String str3) {
        Log.d(LOGTAG, "ProviderFactory::configure - appName/providerName/debugToken: " + str + "/" + str2 + (str3 != null ? "/(not shown)" : "/null"));
        ReactNativeFirebaseAppCheckProvider reactNativeFirebaseAppCheckProvider = this.providers.get(str);
        if (reactNativeFirebaseAppCheckProvider == null) {
            reactNativeFirebaseAppCheckProvider = new ReactNativeFirebaseAppCheckProvider();
            this.providers.put(str, reactNativeFirebaseAppCheckProvider);
        }
        reactNativeFirebaseAppCheckProvider.configure(str, str2, str3);
    }

    public AppCheckProvider create(FirebaseApp firebaseApp) {
        String name = firebaseApp.getName();
        Log.d(LOGTAG, "ProviderFactory::create - fetching provider for app " + name);
        ReactNativeFirebaseAppCheckProvider reactNativeFirebaseAppCheckProvider = this.providers.get(name);
        if (reactNativeFirebaseAppCheckProvider != null) {
            return reactNativeFirebaseAppCheckProvider;
        }
        Log.d(LOGTAG, "ProviderFactory::create - provider not configured for this app.");
        throw new RuntimeException("ReactNativeFirebaseAppCheckProvider not configured for app " + name);
    }
}
