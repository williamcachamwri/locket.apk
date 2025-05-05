package io.invertase.firebase.functions;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;
import io.invertase.firebase.common.RCTConvertFirebase;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.io.IOException;
import java.util.concurrent.Executor;

public class ReactNativeFirebaseFunctionsModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Functions";
    private final UniversalFirebaseFunctionsModule module;

    ReactNativeFirebaseFunctionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseFunctionsModule(reactApplicationContext, SERVICE_NAME);
    }

    @ReactMethod
    public void httpsCallable(String str, String str2, String str3, Integer num, String str4, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        Promise promise2 = promise;
        Task<Object> httpsCallable = this.module.httpsCallable(str, str2, str3, num, str4, readableMap.toHashMap().get("data"), readableMap2);
        httpsCallable.addOnSuccessListener((Executor) getExecutor(), (OnSuccessListener<? super Object>) new ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda2(promise2));
        httpsCallable.addOnFailureListener((Executor) getExecutor(), (OnFailureListener) new ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda3(promise2));
    }

    static /* synthetic */ void lambda$httpsCallable$1(Promise promise, Exception exc) {
        String str;
        Object obj;
        String message = exc.getMessage();
        WritableMap createMap = Arguments.createMap();
        if (exc.getCause() instanceof FirebaseFunctionsException) {
            FirebaseFunctionsException firebaseFunctionsException = (FirebaseFunctionsException) exc.getCause();
            obj = firebaseFunctionsException.getDetails();
            str = firebaseFunctionsException.getCode().name();
            String message2 = firebaseFunctionsException.getMessage();
            Boolean valueOf = Boolean.valueOf(str.contains(FirebaseFunctionsException.Code.DEADLINE_EXCEEDED.name()));
            if (!(firebaseFunctionsException.getCause() instanceof IOException) || valueOf.booleanValue()) {
                message = message2;
            } else {
                str = FirebaseFunctionsException.Code.UNAVAILABLE.name();
                message = FirebaseFunctionsException.Code.UNAVAILABLE.name();
            }
        } else {
            obj = null;
            str = "UNKNOWN";
        }
        RCTConvertFirebase.mapPutValue(UniversalFirebaseFunctionsModule.CODE_KEY, str, createMap);
        RCTConvertFirebase.mapPutValue("message", message, createMap);
        RCTConvertFirebase.mapPutValue(UniversalFirebaseFunctionsModule.DETAILS_KEY, obj, createMap);
        promise.reject(str, message, exc, createMap);
    }

    @ReactMethod
    public void httpsCallableFromUrl(String str, String str2, String str3, Integer num, String str4, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        Promise promise2 = promise;
        Task<Object> httpsCallableFromUrl = this.module.httpsCallableFromUrl(str, str2, str3, num, str4, readableMap.toHashMap().get("data"), readableMap2);
        httpsCallableFromUrl.addOnSuccessListener((Executor) getExecutor(), (OnSuccessListener<? super Object>) new ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda0(promise2));
        httpsCallableFromUrl.addOnFailureListener((Executor) getExecutor(), (OnFailureListener) new ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda1(promise2));
    }

    static /* synthetic */ void lambda$httpsCallableFromUrl$3(Promise promise, Exception exc) {
        String str;
        Object obj;
        String message = exc.getMessage();
        WritableMap createMap = Arguments.createMap();
        if (exc.getCause() instanceof FirebaseFunctionsException) {
            FirebaseFunctionsException firebaseFunctionsException = (FirebaseFunctionsException) exc.getCause();
            obj = firebaseFunctionsException.getDetails();
            str = firebaseFunctionsException.getCode().name();
            String message2 = firebaseFunctionsException.getMessage();
            Boolean valueOf = Boolean.valueOf(str.contains(FirebaseFunctionsException.Code.DEADLINE_EXCEEDED.name()));
            if (!(firebaseFunctionsException.getCause() instanceof IOException) || valueOf.booleanValue()) {
                message = message2;
            } else {
                str = FirebaseFunctionsException.Code.UNAVAILABLE.name();
                message = FirebaseFunctionsException.Code.UNAVAILABLE.name();
            }
        } else {
            obj = null;
            str = "UNKNOWN";
        }
        RCTConvertFirebase.mapPutValue(UniversalFirebaseFunctionsModule.CODE_KEY, str, createMap);
        RCTConvertFirebase.mapPutValue("message", message, createMap);
        RCTConvertFirebase.mapPutValue(UniversalFirebaseFunctionsModule.DETAILS_KEY, obj, createMap);
        promise.reject(str, message, exc, createMap);
    }
}
