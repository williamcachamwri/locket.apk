package io.invertase.firebase.config;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import com.adsbynimbus.render.StaticAdRenderer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import io.invertase.firebase.common.UniversalFirebaseModule;
import java.util.HashMap;
import java.util.Map;

public class UniversalFirebaseConfigModule extends UniversalFirebaseModule {
    private static final String SOURCE = "source";
    private static final String VALUE = "value";

    private String lastFetchStatusToString(int i) {
        return i != -1 ? i != 0 ? i != 1 ? i != 2 ? "unknown" : "throttled" : "failure" : "no_fetch_yet" : FirebaseAnalytics.Param.SUCCESS;
    }

    UniversalFirebaseConfigModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> activate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).activate();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> fetch(String str, long j) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda0(FirebaseApp.getInstance(str), j));
    }

    static /* synthetic */ Void lambda$fetch$0(FirebaseApp firebaseApp, long j) throws Exception {
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance(firebaseApp);
        Tasks.await(j == -1 ? instance.fetch() : instance.fetch(j));
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> fetchAndActivate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).fetchAndActivate();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> reset(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).reset();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setConfigSettings(String str, Bundle bundle) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda2(bundle, FirebaseApp.getInstance(str)));
    }

    static /* synthetic */ Void lambda$setConfigSettings$1(Bundle bundle, FirebaseApp firebaseApp) throws Exception {
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        if (bundle.containsKey("minimumFetchInterval")) {
            builder.setMinimumFetchIntervalInSeconds((long) bundle.getDouble("minimumFetchInterval"));
        }
        if (bundle.containsKey("fetchTimeout")) {
            builder.setFetchTimeoutInSeconds((long) bundle.getDouble("fetchTimeout"));
        }
        FirebaseRemoteConfig.getInstance(firebaseApp).setConfigSettingsAsync(builder.build());
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setDefaultsFromResource(String str, String str2) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda1(this, str2, FirebaseApp.getInstance(str)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setDefaultsFromResource$2(String str, FirebaseApp firebaseApp) throws Exception {
        XmlResourceParser xmlResourceParser;
        int xmlResourceIdByName = getXmlResourceIdByName(str);
        try {
            xmlResourceParser = getApplicationContext().getResources().getXml(xmlResourceIdByName);
        } catch (Resources.NotFoundException unused) {
            xmlResourceParser = null;
        }
        if (xmlResourceParser != null) {
            Tasks.await(FirebaseRemoteConfig.getInstance(firebaseApp).setDefaultsAsync(xmlResourceIdByName));
            return null;
        }
        throw new Exception("resource_not_found");
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setDefaults(String str, HashMap<String, Object> hashMap) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).setDefaultsAsync((Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: package-private */
    public Task<FirebaseRemoteConfigInfo> ensureInitialized(String str) {
        Task<FirebaseRemoteConfigInfo> ensureInitialized = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).ensureInitialized();
        try {
            Tasks.await(fetchAndActivate(str));
        } catch (Exception unused) {
        }
        return ensureInitialized;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getAllValuesForApp(String str) {
        Map<String, FirebaseRemoteConfigValue> all = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getAll();
        HashMap hashMap = new HashMap(all.size());
        for (Map.Entry next : all.entrySet()) {
            hashMap.put((String) next.getKey(), convertRemoteConfigValue((FirebaseRemoteConfigValue) next.getValue()));
        }
        return hashMap;
    }

    private int getXmlResourceIdByName(String str) {
        return getApplicationContext().getResources().getIdentifier(str, "xml", getApplicationContext().getPackageName());
    }

    private Bundle convertRemoteConfigValue(FirebaseRemoteConfigValue firebaseRemoteConfigValue) {
        Bundle bundle = new Bundle(2);
        bundle.putString("value", firebaseRemoteConfigValue.asString());
        int source = firebaseRemoteConfigValue.getSource();
        if (source == 1) {
            bundle.putString("source", "default");
        } else if (source != 2) {
            bundle.putString("source", StaticAdRenderer.STATIC_AD_TYPE);
        } else {
            bundle.putString("source", "remote");
        }
        return bundle;
    }

    public Map<String, Object> getConstantsForApp(String str) {
        HashMap hashMap = new HashMap();
        FirebaseRemoteConfigInfo info = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getInfo();
        FirebaseRemoteConfigSettings configSettings = info.getConfigSettings();
        hashMap.put("values", getAllValuesForApp(str));
        hashMap.put("lastFetchTime", Long.valueOf(info.getFetchTimeMillis()));
        hashMap.put("lastFetchStatus", lastFetchStatusToString(info.getLastFetchStatus()));
        hashMap.put("minimumFetchInterval", Long.valueOf(configSettings.getMinimumFetchIntervalInSeconds()));
        hashMap.put("fetchTimeout", Long.valueOf(configSettings.getFetchTimeoutInSeconds()));
        return hashMap;
    }
}
