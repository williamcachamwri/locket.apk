package io.invertase.firebase.appcheck;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;
import com.google.firebase.appcheck.debug.InternalDebugSecretProvider;
import com.google.firebase.appcheck.debug.internal.DebugAppCheckProvider;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReactNativeFirebaseAppCheckProvider implements AppCheckProvider {
    private static final String LOGTAG = "RNFBAppCheck";
    AppCheckProvider delegateProvider;

    public Task<AppCheckToken> getToken() {
        Log.d(LOGTAG, "Provider::getToken - delegating to native provider");
        return this.delegateProvider.getToken();
    }

    public void configure(String str, String str2, final String str3) {
        Log.d(LOGTAG, "Provider::configure with appName/providerName/debugToken: " + str + "/" + str2 + "/" + (str3 != null ? "(not shown)" : "null"));
        try {
            FirebaseApp instance = FirebaseApp.getInstance(str);
            if ("debug".equals(str2)) {
                if (str3 != null) {
                    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
                    this.delegateProvider = new DebugAppCheckProvider(instance, (Provider<InternalDebugSecretProvider>) new Provider<InternalDebugSecretProvider>() {
                        public InternalDebugSecretProvider get() {
                            return new InternalDebugSecretProvider() {
                                public String getDebugSecret() {
                                    return str3;
                                }
                            };
                        }
                    }, (Executor) newCachedThreadPool, (Executor) newCachedThreadPool, (Executor) newCachedThreadPool);
                } else {
                    this.delegateProvider = DebugAppCheckProviderFactory.getInstance().create(instance);
                }
            }
            if ("playIntegrity".equals(str2)) {
                this.delegateProvider = PlayIntegrityAppCheckProviderFactory.getInstance().create(instance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
