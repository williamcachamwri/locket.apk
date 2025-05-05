package io.invertase.firebase.firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import io.invertase.firebase.common.UniversalFirebasePreferences;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class UniversalFirebaseFirestoreCommon {
    static WeakHashMap<String, WeakReference<FirebaseFirestore>> instanceCache = new WeakHashMap<>();

    static String createFirestoreKey(String str, String str2) {
        return str + ":" + str2;
    }

    static FirebaseFirestore getFirestoreForApp(String str, String str2) {
        String createFirestoreKey = createFirestoreKey(str, str2);
        WeakReference weakReference = instanceCache.get(createFirestoreKey);
        if (weakReference != null) {
            return (FirebaseFirestore) weakReference.get();
        }
        FirebaseFirestore instance = FirebaseFirestore.getInstance(FirebaseApp.getInstance(str), str2);
        setFirestoreSettings(instance, createFirestoreKey);
        instanceCache.put(str, new WeakReference(instance));
        return instance;
    }

    private static void setFirestoreSettings(FirebaseFirestore firebaseFirestore, String str) {
        UniversalFirebasePreferences sharedInstance = UniversalFirebasePreferences.getSharedInstance();
        FirebaseFirestoreSettings.Builder builder = new FirebaseFirestoreSettings.Builder();
        String str2 = UniversalFirebaseFirestoreStatics.FIRESTORE_CACHE_SIZE + "_" + str;
        String str3 = UniversalFirebaseFirestoreStatics.FIRESTORE_HOST + "_" + str;
        String str4 = UniversalFirebaseFirestoreStatics.FIRESTORE_PERSISTENCE + "_" + str;
        String str5 = UniversalFirebaseFirestoreStatics.FIRESTORE_SSL + "_" + str;
        int intValue = sharedInstance.getIntValue(str2, (int) firebaseFirestore.getFirestoreSettings().getCacheSizeBytes());
        String stringValue = sharedInstance.getStringValue(str3, firebaseFirestore.getFirestoreSettings().getHost());
        boolean booleanValue = sharedInstance.getBooleanValue(str4, firebaseFirestore.getFirestoreSettings().isPersistenceEnabled());
        boolean booleanValue2 = sharedInstance.getBooleanValue(str5, firebaseFirestore.getFirestoreSettings().isSslEnabled());
        if (intValue == -1) {
            builder.setCacheSizeBytes(-1);
        } else {
            builder.setCacheSizeBytes((long) intValue);
        }
        builder.setHost(stringValue);
        builder.setPersistenceEnabled(booleanValue);
        builder.setSslEnabled(booleanValue2);
        firebaseFirestore.setFirestoreSettings(builder.build());
        sharedInstance.remove(str2).remove(str3).remove(str4).remove(str5).apply();
    }

    static Query getQueryForFirestore(FirebaseFirestore firebaseFirestore, String str, String str2) {
        if ("collectionGroup".equals(str2)) {
            return firebaseFirestore.collectionGroup(str);
        }
        return firebaseFirestore.collection(str);
    }

    static DocumentReference getDocumentForFirestore(FirebaseFirestore firebaseFirestore, String str) {
        return firebaseFirestore.document(str);
    }
}
