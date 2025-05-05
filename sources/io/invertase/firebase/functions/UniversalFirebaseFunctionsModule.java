package io.invertase.firebase.functions;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableReference;
import com.google.firebase.functions.HttpsCallableResult;
import io.invertase.firebase.common.UniversalFirebaseModule;
import io.sentry.ProfilingTraceData;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UniversalFirebaseFunctionsModule extends UniversalFirebaseModule {
    public static final String CODE_KEY = "code";
    public static final String DATA_KEY = "data";
    public static final String DETAILS_KEY = "details";
    public static final String MSG_KEY = "message";

    UniversalFirebaseFunctionsModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public Task<Object> httpsCallable(String str, String str2, String str3, Integer num, String str4, Object obj, ReadableMap readableMap) {
        return Tasks.call(getExecutor(), new UniversalFirebaseFunctionsModule$$ExternalSyntheticLambda1(str, str2, str4, readableMap, str3, num, obj));
    }

    static /* synthetic */ Object lambda$httpsCallable$0(String str, String str2, String str3, ReadableMap readableMap, String str4, Integer num, Object obj) throws Exception {
        FirebaseFunctions instance = FirebaseFunctions.getInstance(FirebaseApp.getInstance(str), str2);
        HttpsCallableReference httpsCallable = instance.getHttpsCallable(str3);
        if (readableMap.hasKey(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)) {
            httpsCallable.setTimeout((long) readableMap.getInt(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT), TimeUnit.SECONDS);
        }
        if (str4 != null) {
            instance.useEmulator(str4, num.intValue());
        }
        return ((HttpsCallableResult) Tasks.await(httpsCallable.call(obj))).getData();
    }

    /* access modifiers changed from: package-private */
    public Task<Object> httpsCallableFromUrl(String str, String str2, String str3, Integer num, String str4, Object obj, ReadableMap readableMap) {
        return Tasks.call(getExecutor(), new UniversalFirebaseFunctionsModule$$ExternalSyntheticLambda0(str, str2, str4, readableMap, str3, num, obj));
    }

    static /* synthetic */ Object lambda$httpsCallableFromUrl$1(String str, String str2, String str3, ReadableMap readableMap, String str4, Integer num, Object obj) throws Exception {
        FirebaseFunctions instance = FirebaseFunctions.getInstance(FirebaseApp.getInstance(str), str2);
        HttpsCallableReference httpsCallableFromUrl = instance.getHttpsCallableFromUrl(new URL(str3));
        if (readableMap.hasKey(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)) {
            httpsCallableFromUrl.setTimeout((long) readableMap.getInt(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT), TimeUnit.SECONDS);
        }
        if (str4 != null) {
            instance.useEmulator(str4, num.intValue());
        }
        return ((HttpsCallableResult) Tasks.await(httpsCallableFromUrl.call(obj))).getData();
    }
}
