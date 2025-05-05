package io.invertase.firebase.firestore;

import com.facebook.react.bridge.Promise;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.UniversalFirebasePreferences;

class ReactNativeFirebaseFirestoreCommon {
    ReactNativeFirebaseFirestoreCommon() {
    }

    static void rejectPromiseFirestoreException(Promise promise, Exception exc) {
        Throwable th;
        if (exc instanceof FirebaseFirestoreException) {
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, universalFirebaseFirestoreException.getCode(), universalFirebaseFirestoreException.getMessage());
        } else if (exc.getCause() == null || !(exc.getCause() instanceof FirebaseFirestoreException)) {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, exc);
        } else {
            FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc.getCause();
            if (exc.getCause().getCause() != null) {
                th = exc.getCause().getCause();
            } else {
                th = exc.getCause();
            }
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException2 = new UniversalFirebaseFirestoreException(firebaseFirestoreException, th);
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, universalFirebaseFirestoreException2.getCode(), universalFirebaseFirestoreException2.getMessage());
        }
    }

    static DocumentSnapshot.ServerTimestampBehavior getServerTimestampBehavior(String str, String str2) {
        String stringValue = UniversalFirebasePreferences.getSharedInstance().getStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SERVER_TIMESTAMP_BEHAVIOR + "_" + UniversalFirebaseFirestoreCommon.createFirestoreKey(str, str2), "none");
        if ("estimate".equals(stringValue)) {
            return DocumentSnapshot.ServerTimestampBehavior.ESTIMATE;
        }
        if ("previous".equals(stringValue)) {
            return DocumentSnapshot.ServerTimestampBehavior.PREVIOUS;
        }
        return DocumentSnapshot.ServerTimestampBehavior.NONE;
    }
}
