package io.invertase.firebase.firestore;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.LoadBundleTask;
import io.invertase.firebase.common.UniversalFirebaseModule;
import io.invertase.firebase.common.UniversalFirebasePreferences;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UniversalFirebaseFirestoreModule extends UniversalFirebaseModule {
    private static HashMap<String, String> emulatorConfigs = new HashMap<>();

    UniversalFirebaseFirestoreModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public Task<Void> disableNetwork(String str, String str2) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).disableNetwork();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> enableNetwork(String str, String str2) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).enableNetwork();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> useEmulator(String str, String str2, String str3, int i) {
        return Tasks.call(getExecutor(), new UniversalFirebaseFirestoreModule$$ExternalSyntheticLambda1(str, str2, str3, i));
    }

    static /* synthetic */ Void lambda$useEmulator$0(String str, String str2, String str3, int i) throws Exception {
        String createFirestoreKey = UniversalFirebaseFirestoreCommon.createFirestoreKey(str, str2);
        if (emulatorConfigs.get(createFirestoreKey) != null) {
            return null;
        }
        emulatorConfigs.put(createFirestoreKey, "true");
        UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).useEmulator(str3, i);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> settings(String str, Map<String, Object> map) {
        return Tasks.call(getExecutor(), new UniversalFirebaseFirestoreModule$$ExternalSyntheticLambda0(map, str));
    }

    static /* synthetic */ Void lambda$settings$1(Map map, String str) throws Exception {
        if (map.containsKey("cacheSizeBytes")) {
            UniversalFirebasePreferences.getSharedInstance().setIntValue(UniversalFirebaseFirestoreStatics.FIRESTORE_CACHE_SIZE + "_" + str, ((Double) Objects.requireNonNull((Double) map.get("cacheSizeBytes"))).intValue());
        }
        if (map.containsKey("host")) {
            UniversalFirebasePreferences.getSharedInstance().setStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_HOST + "_" + str, (String) map.get("host"));
        }
        if (map.containsKey("persistence")) {
            UniversalFirebasePreferences.getSharedInstance().setBooleanValue(UniversalFirebaseFirestoreStatics.FIRESTORE_PERSISTENCE + "_" + str, ((Boolean) map.get("persistence")).booleanValue());
        }
        if (map.containsKey("ssl")) {
            UniversalFirebasePreferences.getSharedInstance().setBooleanValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SSL + "_" + str, ((Boolean) map.get("ssl")).booleanValue());
        }
        if (!map.containsKey("serverTimestampBehavior")) {
            return null;
        }
        UniversalFirebasePreferences.getSharedInstance().setStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SERVER_TIMESTAMP_BEHAVIOR + "_" + str, (String) map.get("serverTimestampBehavior"));
        return null;
    }

    /* access modifiers changed from: package-private */
    public LoadBundleTask loadBundle(String str, String str2, String str3) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).loadBundle(str3.getBytes(StandardCharsets.UTF_8));
    }

    /* access modifiers changed from: package-private */
    public Task<Void> clearPersistence(String str, String str2) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).clearPersistence();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> waitForPendingWrites(String str, String str2) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).waitForPendingWrites();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> terminate(String str, String str2) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2);
        String createFirestoreKey = UniversalFirebaseFirestoreCommon.createFirestoreKey(str, str2);
        if (UniversalFirebaseFirestoreCommon.instanceCache.get(createFirestoreKey) != null) {
            UniversalFirebaseFirestoreCommon.instanceCache.get(createFirestoreKey).clear();
            UniversalFirebaseFirestoreCommon.instanceCache.remove(createFirestoreKey);
        }
        return firestoreForApp.terminate();
    }
}
