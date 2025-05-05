package io.invertase.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import android.os.Parcel;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.amplitude.api.DeviceInfo;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorGenerator;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.invertase.firebase.app.ReactNativeFirebaseAppModule;
import io.invertase.firebase.common.ReactNativeFirebaseEvent;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.SharedUtils;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.cache.EnvelopeCache;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

class ReactNativeFirebaseAuthModule extends ReactNativeFirebaseModule {
    public static final SimpleDateFormat ISO_8601_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final String TAG = "Auth";
    private static HashMap<String, String> emulatorConfigs = new HashMap<>();
    private static HashMap<String, FirebaseAuth.AuthStateListener> mAuthListeners = new HashMap<>();
    private static HashMap<String, FirebaseAuth.IdTokenListener> mIdTokenListeners = new HashMap<>();
    private HashMap<String, AuthCredential> credentials = new HashMap<>();
    private final HashMap<String, MultiFactorResolver> mCachedResolvers = new HashMap<>();
    /* access modifiers changed from: private */
    public PhoneAuthCredential mCredential;
    /* access modifiers changed from: private */
    public PhoneAuthProvider.ForceResendingToken mForceResendingToken;
    private String mLastPhoneNumber;
    private final HashMap<String, MultiFactorSession> mMultiFactorSessions = new HashMap<>();
    /* access modifiers changed from: private */
    public String mVerificationId;

    ReactNativeFirebaseAuthModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    public void initialize() {
        super.initialize();
        Log.d(TAG, "instance-initialized");
    }

    public void invalidate() {
        super.invalidate();
        Log.d(TAG, "instance-destroyed");
        Iterator<Map.Entry<String, FirebaseAuth.AuthStateListener>> it = mAuthListeners.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            FirebaseAuth.getInstance(FirebaseApp.getInstance((String) next.getKey())).removeAuthStateListener((FirebaseAuth.AuthStateListener) next.getValue());
            it.remove();
        }
        Iterator<Map.Entry<String, FirebaseAuth.IdTokenListener>> it2 = mIdTokenListeners.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry next2 = it2.next();
            FirebaseAuth.getInstance(FirebaseApp.getInstance((String) next2.getKey())).removeIdTokenListener((FirebaseAuth.IdTokenListener) next2.getValue());
            it2.remove();
        }
        this.mCachedResolvers.clear();
        this.mMultiFactorSessions.clear();
    }

    @ReactMethod
    public void configureAuthDomain(String str) {
        Log.d(TAG, "configureAuthDomain");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        String str2 = ReactNativeFirebaseAppModule.authDomains.get(str);
        Log.d(TAG, "configureAuthDomain - app " + str + " domain? " + str2);
        if (str2 != null) {
            instance.setCustomAuthDomain(str2);
        }
    }

    @ReactMethod
    public void getCustomAuthDomain(String str, Promise promise) {
        Log.d(TAG, "configureAuthDomain");
        promise.resolve(FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCustomAuthDomain());
    }

    @ReactMethod
    public void addAuthStateListener(String str) {
        Log.d(TAG, "addAuthStateListener");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (mAuthListeners.get(str) == null) {
            ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda8 reactNativeFirebaseAuthModule$$ExternalSyntheticLambda8 = new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda8(this, str);
            instance.addAuthStateListener(reactNativeFirebaseAuthModule$$ExternalSyntheticLambda8);
            mAuthListeners.put(str, reactNativeFirebaseAuthModule$$ExternalSyntheticLambda8);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addAuthStateListener$0(String str, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        WritableMap createMap = Arguments.createMap();
        ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
        if (currentUser != null) {
            createMap.putString("appName", str);
            createMap.putMap("user", firebaseUserToMap(currentUser));
        } else {
            createMap.putString("appName", str);
        }
        Log.d(TAG, "addAuthStateListener:eventBody " + createMap.toString());
        sharedInstance.sendEvent(new ReactNativeFirebaseEvent("auth_state_changed", createMap, str));
    }

    @ReactMethod
    public void removeAuthStateListener(String str) {
        Log.d(TAG, "removeAuthStateListener");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseAuth.AuthStateListener authStateListener = mAuthListeners.get(str);
        if (authStateListener != null) {
            instance.removeAuthStateListener(authStateListener);
            mAuthListeners.remove(str);
        }
    }

    @ReactMethod
    public void addIdTokenListener(String str) {
        Log.d(TAG, "addIdTokenListener");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (!mIdTokenListeners.containsKey(str)) {
            ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda16 reactNativeFirebaseAuthModule$$ExternalSyntheticLambda16 = new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda16(this, str);
            instance.addIdTokenListener((FirebaseAuth.IdTokenListener) reactNativeFirebaseAuthModule$$ExternalSyntheticLambda16);
            mIdTokenListeners.put(str, reactNativeFirebaseAuthModule$$ExternalSyntheticLambda16);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addIdTokenListener$1(String str, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
        WritableMap createMap = Arguments.createMap();
        if (currentUser != null) {
            createMap.putBoolean("authenticated", true);
            createMap.putString("appName", str);
            createMap.putMap("user", firebaseUserToMap(currentUser));
        } else {
            createMap.putString("appName", str);
            createMap.putBoolean("authenticated", false);
        }
        sharedInstance.sendEvent(new ReactNativeFirebaseEvent("auth_id_token_changed", createMap, str));
    }

    @ReactMethod
    public void removeIdTokenListener(String str) {
        Log.d(TAG, "removeIdTokenListener");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseAuth.IdTokenListener idTokenListener = mIdTokenListeners.get(str);
        if (idTokenListener != null) {
            instance.removeIdTokenListener(idTokenListener);
            mIdTokenListeners.remove(str);
        }
    }

    @ReactMethod
    public void forceRecaptchaFlowForTesting(String str, boolean z, Promise promise) {
        Log.d(TAG, "forceRecaptchaFlowForTesting");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getFirebaseAuthSettings().forceRecaptchaFlowForTesting(z);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setAutoRetrievedSmsCodeForPhoneNumber(String str, String str2, String str3, Promise promise) {
        Log.d(TAG, "setAutoRetrievedSmsCodeForPhoneNumber");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getFirebaseAuthSettings().setAutoRetrievedSmsCodeForPhoneNumber(str2, str3);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setAppVerificationDisabledForTesting(String str, boolean z, Promise promise) {
        Log.d(TAG, "setAppVerificationDisabledForTesting");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getFirebaseAuthSettings().setAppVerificationDisabledForTesting(z);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void signOut(String str, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        Log.d(TAG, "signOut");
        if (instance == null || instance.getCurrentUser() == null) {
            promiseNoUser(promise, true);
            return;
        }
        instance.signOut();
        promiseNoUser(promise, false);
    }

    @ReactMethod
    private void signInAnonymously(String str, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        Log.d(TAG, "signInAnonymously");
        instance.signInAnonymously().addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda20(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda21(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInAnonymously$2(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInAnonymously:onComplete:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInAnonymously$3(Promise promise, Exception exc) {
        SentryLogcatAdapter.e(TAG, "signInAnonymously:onComplete:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    private void createUserWithEmailAndPassword(String str, String str2, String str3, Promise promise) {
        Log.d(TAG, "createUserWithEmailAndPassword");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).createUserWithEmailAndPassword(str2, str3).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda34(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda35(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createUserWithEmailAndPassword$4(Promise promise, AuthResult authResult) {
        Log.d(TAG, "createUserWithEmailAndPassword:onComplete:success");
        promiseWithAuthResult(authResult, promise);
        Log.d(TAG, "createUserWithEmailAndPassword:onComplete:promiseResolved");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createUserWithEmailAndPassword$5(Promise promise, Exception exc) {
        SentryLogcatAdapter.e(TAG, "createUserWithEmailAndPassword:onComplete:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    private void signInWithEmailAndPassword(String str, String str2, String str3, Promise promise) {
        Log.d(TAG, "signInWithEmailAndPassword");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).signInWithEmailAndPassword(str2, str3).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda46(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda47(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithEmailAndPassword$6(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInWithEmailAndPassword:onComplete:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithEmailAndPassword$7(Promise promise, Exception exc) {
        SentryLogcatAdapter.e(TAG, "signInWithEmailAndPassword:onComplete:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    private void signInWithEmailLink(String str, String str2, String str3, Promise promise) {
        Log.d(TAG, "signInWithEmailLink");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).signInWithEmailLink(str2, str3).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda3(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda4(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithEmailLink$8(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInWithEmailLink:onComplete:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithEmailLink$9(Promise promise, Exception exc) {
        SentryLogcatAdapter.e(TAG, "signInWithEmailLink:onComplete:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    private void signInWithCustomToken(String str, String str2, Promise promise) {
        Log.d(TAG, "signInWithCustomToken");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).signInWithCustomToken(str2).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda24(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda25(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithCustomToken$10(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInWithCustomToken:onComplete:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithCustomToken$11(Promise promise, Exception exc) {
        SentryLogcatAdapter.e(TAG, "signInWithCustomToken:onComplete:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    public void revokeToken(String str, String str2, Promise promise) {
        Log.d(TAG, "revokeToken");
        SentryLogcatAdapter.e(TAG, "revokeToken:failure:noCurrentUser");
        promiseNoUser(promise, false);
    }

    @ReactMethod
    public void sendPasswordResetEmail(String str, String str2, ReadableMap readableMap, Promise promise) {
        Log.d(TAG, "sendPasswordResetEmail");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda23 reactNativeFirebaseAuthModule$$ExternalSyntheticLambda23 = new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda23(this, promise);
        if (readableMap == null) {
            instance.sendPasswordResetEmail(str2).addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda23);
        } else {
            instance.sendPasswordResetEmail(str2, buildActionCodeSettings(readableMap)).addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda23);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendPasswordResetEmail$12(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "sendPasswordResetEmail:onComplete:success");
            promiseNoUser(promise, false);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "sendPasswordResetEmail:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void sendSignInLinkToEmail(String str, String str2, ReadableMap readableMap, Promise promise) {
        Log.d(TAG, "sendSignInLinkToEmail");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        instance.sendSignInLinkToEmail(str2, buildActionCodeSettings(readableMap)).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda9(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendSignInLinkToEmail$13(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "sendSignInLinkToEmail:onComplete:success");
            promiseNoUser(promise, false);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "sendSignInLinkToEmail:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void delete(String str, Promise promise) {
        FirebaseUser currentUser = FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser();
        Log.d(TAG, "delete");
        if (currentUser != null) {
            currentUser.delete().addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda32(this, promise));
            return;
        }
        SentryLogcatAdapter.e(TAG, "delete:failure:noCurrentUser");
        promiseNoUser(promise, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$14(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "delete:onComplete:success");
            promiseNoUser(promise, false);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "delete:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void reload(String str, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "reload");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "reload:failure:noCurrentUser");
            return;
        }
        currentUser.reload().addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda0(this, instance, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reload$15(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "reload:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "reload:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void sendEmailVerification(String str, ReadableMap readableMap, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "sendEmailVerification");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "sendEmailVerification:failure:noCurrentUser");
            return;
        }
        ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda36 reactNativeFirebaseAuthModule$$ExternalSyntheticLambda36 = new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda36(this, instance, promise);
        if (readableMap == null) {
            currentUser.sendEmailVerification().addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda36);
        } else {
            currentUser.sendEmailVerification(buildActionCodeSettings(readableMap)).addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda36);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendEmailVerification$16(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "sendEmailVerification:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "sendEmailVerification:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void verifyBeforeUpdateEmail(String str, String str2, ReadableMap readableMap, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "verifyBeforeUpdateEmail");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "verifyBeforeUpdateEmail:failure:noCurrentUser");
            return;
        }
        ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda15 reactNativeFirebaseAuthModule$$ExternalSyntheticLambda15 = new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda15(this, instance, promise);
        if (readableMap == null) {
            currentUser.verifyBeforeUpdateEmail(str2).addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda15);
        } else {
            currentUser.verifyBeforeUpdateEmail(str2, buildActionCodeSettings(readableMap)).addOnCompleteListener((Executor) getExecutor(), reactNativeFirebaseAuthModule$$ExternalSyntheticLambda15);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$verifyBeforeUpdateEmail$17(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "verifyBeforeUpdateEmail:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "verifyBeforeUpdateEmail:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void updateEmail(String str, String str2, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "updateEmail");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "updateEmail:failure:noCurrentUser");
            return;
        }
        currentUser.updateEmail(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda26(this, instance, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEmail$18(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "updateEmail:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "updateEmail:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void updatePassword(String str, String str2, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "updatePassword");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "updatePassword:failure:noCurrentUser");
            return;
        }
        currentUser.updatePassword(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda45(this, instance, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePassword$19(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "updatePassword:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "updatePassword:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void updatePhoneNumber(String str, String str2, String str3, String str4, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        if (!str2.equals("phone")) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential does not have a phone provider.");
        }
        PhoneAuthCredential phoneAuthCredential = getPhoneAuthCredential(str3, str4);
        if (phoneAuthCredential == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
        } else if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "updatePhoneNumber:failure:noCurrentUser");
        } else {
            Log.d(TAG, "updatePhoneNumber");
            currentUser.updatePhoneNumber(phoneAuthCredential).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda2(this, instance, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePhoneNumber$20(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "updatePhoneNumber:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "updatePhoneNumber:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void updateProfile(String str, ReadableMap readableMap, Promise promise) {
        Uri uri;
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "updateProfile");
        if (currentUser == null) {
            promiseNoUser(promise, false);
            SentryLogcatAdapter.e(TAG, "updateProfile:failure:noCurrentUser");
            return;
        }
        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        if (readableMap.hasKey("displayName")) {
            builder.setDisplayName(readableMap.getString("displayName"));
        }
        if (readableMap.hasKey("photoURL")) {
            String string = readableMap.getString("photoURL");
            if (string == null) {
                uri = null;
            } else {
                uri = Uri.parse(string);
            }
            builder.setPhotoUri(uri);
        }
        currentUser.updateProfile(builder.build()).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda18(this, instance, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateProfile$21(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "updateProfile:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "updateProfile:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void signInWithCredential(String str, String str2, String str3, String str4, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        AuthCredential credentialForProvider = getCredentialForProvider(str2, str3, str4);
        if (credentialForProvider == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        Log.d(TAG, "signInWithCredential");
        instance.signInWithCredential(credentialForProvider).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda7(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithCredential$22(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "signInWithCredential:onComplete:success");
            promiseWithAuthResult((AuthResult) task.getResult(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "signInWithCredential:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void signInWithProvider(String str, ReadableMap readableMap, Promise promise) {
        ReadableMap map;
        ReadableArray array;
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (readableMap.getString("providerId") == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder(readableMap.getString("providerId"), instance);
        if (readableMap.hasKey("scopes") && (array = readableMap.getArray("scopes")) != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                arrayList.add(array.getString(i));
            }
            newBuilder.setScopes(arrayList);
        }
        if (readableMap.hasKey("customParameters") && (map = readableMap.getMap("customParameters")) != null) {
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                newBuilder.addCustomParameter(nextKey, map.getString(nextKey));
            }
        }
        Task<AuthResult> pendingAuthResult = instance.getPendingAuthResult();
        if (pendingAuthResult != null) {
            pendingAuthResult.addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda27(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda28(this, promise));
        } else {
            instance.startActivityForSignInWithProvider(getCurrentActivity(), newBuilder.build()).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda29(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda30(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithProvider$23(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithProvider$24(Promise promise, Exception exc) {
        Log.d(TAG, "signInWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithProvider$25(Promise promise, AuthResult authResult) {
        Log.d(TAG, "signInWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithProvider$26(Promise promise, Exception exc) {
        Log.d(TAG, "signInWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    public void signInWithPhoneNumber(String str, String str2, boolean z, final Promise promise) {
        Log.d(TAG, "signInWithPhoneNumber");
        final FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        Activity currentActivity = getCurrentActivity();
        if (!str2.equals(this.mLastPhoneNumber)) {
            this.mForceResendingToken = null;
            this.mLastPhoneNumber = str2;
        }
        this.mVerificationId = null;
        AnonymousClass1 r6 = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            private boolean promiseResolved = false;

            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                instance.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Executor) ReactNativeFirebaseAuthModule.this.getExecutor(), new ReactNativeFirebaseAuthModule$1$$ExternalSyntheticLambda0(this, phoneAuthCredential, promise));
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onVerificationCompleted$0(PhoneAuthCredential phoneAuthCredential, Promise promise, Task task) {
                if (task.isSuccessful()) {
                    Log.d(ReactNativeFirebaseAuthModule.TAG, "signInWithPhoneNumber:autoVerified:signInWithCredential:onComplete:success");
                    if (!this.promiseResolved) {
                        WritableMap createMap = Arguments.createMap();
                        Parcel obtain = Parcel.obtain();
                        phoneAuthCredential.writeToParcel(obtain, 0);
                        obtain.setDataPosition(16);
                        String readString = obtain.readString();
                        ReactNativeFirebaseAuthModule.this.mVerificationId = readString;
                        obtain.recycle();
                        createMap.putString("verificationId", readString);
                        promise.resolve(createMap);
                        return;
                    }
                    return;
                }
                Exception exception = task.getException();
                SentryLogcatAdapter.e(ReactNativeFirebaseAuthModule.TAG, "signInWithPhoneNumber:autoVerified:signInWithCredential:onComplete:failure", exception);
                if (!this.promiseResolved) {
                    ReactNativeFirebaseAuthModule.this.promiseRejectAuthException(promise, exception);
                }
            }

            public void onVerificationFailed(FirebaseException firebaseException) {
                Log.d(ReactNativeFirebaseAuthModule.TAG, "signInWithPhoneNumber:verification:failed");
                ReactNativeFirebaseAuthModule.this.promiseRejectAuthException(promise, firebaseException);
            }

            public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                ReactNativeFirebaseAuthModule.this.mVerificationId = str;
                ReactNativeFirebaseAuthModule.this.mForceResendingToken = forceResendingToken;
                WritableMap createMap = Arguments.createMap();
                createMap.putString("verificationId", str);
                promise.resolve(createMap);
                this.promiseResolved = true;
            }

            public void onCodeAutoRetrievalTimeOut(String str) {
                super.onCodeAutoRetrievalTimeOut(str);
            }
        };
        if (currentActivity == null) {
            return;
        }
        if (!z || this.mForceResendingToken == null) {
            PhoneAuthProvider.getInstance(instance).verifyPhoneNumber(str2, 60, TimeUnit.SECONDS, currentActivity, r6);
            return;
        }
        PhoneAuthProvider.getInstance(instance).verifyPhoneNumber(str2, 60, TimeUnit.SECONDS, currentActivity, r6, this.mForceResendingToken);
    }

    @ReactMethod
    public void getSession(String str, Promise promise) {
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser().getMultiFactor().getSession().addOnCompleteListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda17(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getSession$27(Promise promise, Task task) {
        if (!task.isSuccessful()) {
            rejectPromiseWithExceptionMap(promise, task.getException());
            return;
        }
        MultiFactorSession multiFactorSession = (MultiFactorSession) task.getResult();
        String num = Integer.toString(multiFactorSession.hashCode());
        this.mMultiFactorSessions.put(num, multiFactorSession);
        promise.resolve(num);
    }

    @ReactMethod
    public void verifyPhoneNumberWithMultiFactorInfo(String str, String str2, final String str3, final Promise promise) {
        MultiFactorInfo multiFactorInfo;
        MultiFactorResolver multiFactorResolver = this.mCachedResolvers.get(str3);
        if (multiFactorResolver == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-multi-factor-session", "No resolver for session found. Is the session id correct?");
            return;
        }
        Iterator<MultiFactorInfo> it = multiFactorResolver.getHints().iterator();
        while (true) {
            if (!it.hasNext()) {
                multiFactorInfo = null;
                break;
            }
            multiFactorInfo = it.next();
            if (str2.equals(multiFactorInfo.getUid())) {
                break;
            }
        }
        if (multiFactorInfo == null) {
            rejectPromiseWithCodeAndMessage(promise, "multi-factor-info-not-found", "The user does not have a second factor matching the identifier provided.");
        } else if (!"phone".equals(multiFactorInfo.getFactorId())) {
            rejectPromiseWithCodeAndMessage(promise, "unknown", "Unsupported second factor. Only phone factors are supported.");
        } else {
            FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
            PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(instance).setActivity(getCurrentActivity()).setMultiFactorHint((PhoneMultiFactorInfo) multiFactorInfo).setTimeout(30L, TimeUnit.SECONDS).setMultiFactorSession(multiFactorResolver.getSession()).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    promise.resolve(str);
                }

                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    ReactNativeFirebaseAuthModule.this.resolveMultiFactorCredential(phoneAuthCredential, str3, promise);
                }

                public void onVerificationFailed(FirebaseException firebaseException) {
                    ReactNativeFirebaseAuthModule.this.promiseRejectAuthException(promise, firebaseException);
                }
            }).build());
        }
    }

    @ReactMethod
    public void verifyPhoneNumberForMultiFactor(String str, String str2, String str3, final Promise promise) {
        MultiFactorSession multiFactorSession = this.mMultiFactorSessions.get(str3);
        if (multiFactorSession == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-multi-factor-session", "can't find session for provided key");
        } else {
            PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance(FirebaseApp.getInstance(str))).setPhoneNumber(str2).setActivity(getCurrentActivity()).setTimeout(30L, TimeUnit.SECONDS).setMultiFactorSession(multiFactorSession).requireSmsValidation(true).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "not-implemented", "This is currently not supported.");
                }

                public void onVerificationFailed(FirebaseException firebaseException) {
                    ReactNativeFirebaseAuthModule.this.promiseRejectAuthException(promise, firebaseException);
                }

                public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    promise.resolve(str);
                }
            }).build());
        }
    }

    @ReactMethod
    public void finalizeMultiFactorEnrollment(String str, String str2, String str3, @Nullable String str4, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        instance.getCurrentUser().getMultiFactor().enroll(PhoneMultiFactorGenerator.getAssertion(PhoneAuthProvider.getCredential(str2, str3)), str4).addOnCompleteListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda37(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finalizeMultiFactorEnrollment$28(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "finalizeMultiFactorEnrollment:onComplete:success");
            promise.resolve((Object) null);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "finalizeMultiFactorEnrollment:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    /* access modifiers changed from: private */
    public void resolveMultiFactorCredential(PhoneAuthCredential phoneAuthCredential, String str, Promise promise) {
        PhoneMultiFactorAssertion assertion = PhoneMultiFactorGenerator.getAssertion(phoneAuthCredential);
        MultiFactorResolver multiFactorResolver = this.mCachedResolvers.get(str);
        if (multiFactorResolver == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-multi-factor-session", "No resolver for session found. Is the session id correct?");
        } else {
            multiFactorResolver.resolveSignIn(assertion).addOnCompleteListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda38(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resolveMultiFactorCredential$29(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promiseWithAuthResult((AuthResult) task.getResult(), promise);
        } else {
            promiseRejectAuthException(promise, task.getException());
        }
    }

    @ReactMethod
    public void resolveMultiFactorSignIn(String str, String str2, String str3, String str4, Promise promise) {
        resolveMultiFactorCredential(PhoneAuthProvider.getCredential(str3, str4), str2, promise);
    }

    @ReactMethod
    public void confirmationResultConfirm(String str, String str2, Promise promise) {
        try {
            FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).signInWithCredential(PhoneAuthProvider.getCredential(this.mVerificationId, str2)).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda1(this, promise));
        } catch (Exception e) {
            Log.d(TAG, "confirmationResultConfirm::getCredential::failure", e);
            promiseRejectAuthException(promise, e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmationResultConfirm$30(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "confirmationResultConfirm:signInWithCredential:onComplete:success");
            promiseWithAuthResult((AuthResult) Objects.requireNonNull((AuthResult) task.getResult()), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "confirmationResultConfirm:signInWithCredential:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void verifyPhoneNumber(final String str, String str2, final String str3, int i, boolean z) {
        Log.d(TAG, "verifyPhoneNumber:" + str2);
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        Activity currentActivity = getCurrentActivity();
        if (!str2.equals(this.mLastPhoneNumber)) {
            this.mForceResendingToken = null;
            this.mLastPhoneNumber = str2;
        }
        this.mCredential = null;
        AnonymousClass4 r7 = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                ReactNativeFirebaseAuthModule.this.mCredential = phoneAuthCredential;
                Log.d(ReactNativeFirebaseAuthModule.TAG, "verifyPhoneNumber:verification:onVerificationCompleted");
                WritableMap createMap = Arguments.createMap();
                Parcel obtain = Parcel.obtain();
                phoneAuthCredential.writeToParcel(obtain, 0);
                obtain.setDataPosition(16);
                String readString = obtain.readString();
                obtain.setDataPosition(obtain.dataPosition() + 8);
                createMap.putString(UniversalFirebaseFunctionsModule.CODE_KEY, obtain.readString());
                createMap.putString("verificationId", readString);
                obtain.recycle();
                ReactNativeFirebaseAuthModule.this.sendPhoneStateEvent(str, str3, "onVerificationComplete", createMap);
            }

            public void onVerificationFailed(FirebaseException firebaseException) {
                Log.d(ReactNativeFirebaseAuthModule.TAG, "verifyPhoneNumber:verification:onVerificationFailed");
                WritableMap createMap = Arguments.createMap();
                createMap.putMap("error", ReactNativeFirebaseAuthModule.this.getJSError(firebaseException));
                ReactNativeFirebaseAuthModule.this.sendPhoneStateEvent(str, str3, "onVerificationFailed", createMap);
            }

            public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Log.d(ReactNativeFirebaseAuthModule.TAG, "verifyPhoneNumber:verification:onCodeSent");
                ReactNativeFirebaseAuthModule.this.mForceResendingToken = forceResendingToken;
                WritableMap createMap = Arguments.createMap();
                createMap.putString("verificationId", str);
                createMap.putString("verificationId", str);
                ReactNativeFirebaseAuthModule.this.sendPhoneStateEvent(str, str3, "onCodeSent", createMap);
            }

            public void onCodeAutoRetrievalTimeOut(String str) {
                super.onCodeAutoRetrievalTimeOut(str);
                Log.d(ReactNativeFirebaseAuthModule.TAG, "verifyPhoneNumber:verification:onCodeAutoRetrievalTimeOut");
                WritableMap createMap = Arguments.createMap();
                createMap.putString("verificationId", str);
                ReactNativeFirebaseAuthModule.this.sendPhoneStateEvent(str, str3, "onCodeAutoRetrievalTimeout", createMap);
            }
        };
        if (currentActivity == null) {
            return;
        }
        if (!z || this.mForceResendingToken == null) {
            PhoneAuthProvider.getInstance(instance).verifyPhoneNumber(str2, (long) i, TimeUnit.SECONDS, currentActivity, r7);
            return;
        }
        PhoneAuthProvider.getInstance(instance).verifyPhoneNumber(str2, (long) i, TimeUnit.SECONDS, currentActivity, r7, this.mForceResendingToken);
    }

    @ReactMethod
    public void confirmPasswordReset(String str, String str2, String str3, Promise promise) {
        Log.d(TAG, "confirmPasswordReset");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).confirmPasswordReset(str2, str3).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda13(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmPasswordReset$31(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "confirmPasswordReset:onComplete:success");
            promiseNoUser(promise, false);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "confirmPasswordReset:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void applyActionCode(String str, String str2, Promise promise) {
        Log.d(TAG, "applyActionCode");
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        instance.applyActionCode(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda31(this, instance, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyActionCode$32(FirebaseAuth firebaseAuth, Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "applyActionCode:onComplete:success");
            promiseWithUser(firebaseAuth.getCurrentUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "applyActionCode:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void checkActionCode(String str, String str2, Promise promise) {
        Log.d(TAG, "checkActionCode");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).checkActionCode(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda6(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkActionCode$33(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "checkActionCode:onComplete:success");
            ActionCodeResult actionCodeResult = (ActionCodeResult) Objects.requireNonNull((ActionCodeResult) task.getResult());
            WritableMap createMap = Arguments.createMap();
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putString("email", actionCodeResult.getData(0));
            createMap2.putString("fromEmail", actionCodeResult.getData(1));
            createMap.putMap("data", createMap2);
            int operation = actionCodeResult.getOperation();
            createMap.putString("operation", operation != 0 ? operation != 1 ? operation != 2 ? operation != 3 ? operation != 4 ? "UNKNOWN" : "EMAIL_SIGNIN" : "ERROR" : "RECOVER_EMAIL" : "VERIFY_EMAIL" : "PASSWORD_RESET");
            promise.resolve(createMap);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "checkActionCode:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void linkWithCredential(String str, String str2, String str3, String str4, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        AuthCredential credentialForProvider = getCredentialForProvider(str2, str3, str4);
        if (credentialForProvider == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, DynamicLink.Builder.KEY_LINK);
        if (currentUser != null) {
            currentUser.linkWithCredential(credentialForProvider).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda48(this, promise));
        } else {
            promiseNoUser(promise, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$linkWithCredential$34(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "link:onComplete:success");
            promiseWithAuthResult((AuthResult) task.getResult(), promise);
            return;
        }
        Exception exception = task.getException();
        if (exception instanceof FirebaseAuthUserCollisionException) {
            FirebaseAuthUserCollisionException firebaseAuthUserCollisionException = (FirebaseAuthUserCollisionException) exception;
            AuthCredential updatedCredential = firebaseAuthUserCollisionException.getUpdatedCredential();
            Log.d(TAG, "link:onComplete:collisionFailure", firebaseAuthUserCollisionException);
            if (updatedCredential != null) {
                Log.d(TAG, "link:onComplete:collisionFailure had credential", firebaseAuthUserCollisionException);
                promiseRejectLinkAuthException(promise, firebaseAuthUserCollisionException, updatedCredential);
                return;
            }
        }
        SentryLogcatAdapter.e(TAG, "link:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void linkWithProvider(String str, ReadableMap readableMap, Promise promise) {
        ReadableMap map;
        ReadableArray array;
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (readableMap.getString("providerId") == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "linkWithProvider");
        if (currentUser == null) {
            promiseNoUser(promise, true);
            return;
        }
        OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder(readableMap.getString("providerId"), instance);
        if (readableMap.hasKey("scopes") && (array = readableMap.getArray("scopes")) != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                arrayList.add(array.getString(i));
            }
            newBuilder.setScopes(arrayList);
        }
        if (readableMap.hasKey("customParameters") && (map = readableMap.getMap("customParameters")) != null) {
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                newBuilder.addCustomParameter(nextKey, map.getString(nextKey));
            }
        }
        Task<AuthResult> pendingAuthResult = instance.getPendingAuthResult();
        if (pendingAuthResult != null) {
            pendingAuthResult.addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda39(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda40(this, promise));
        } else {
            currentUser.startActivityForLinkWithProvider(getCurrentActivity(), newBuilder.build()).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda41(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda42(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$linkWithProvider$35(Promise promise, AuthResult authResult) {
        Log.d(TAG, "linkWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$linkWithProvider$36(Promise promise, Exception exc) {
        Log.d(TAG, "linkWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$linkWithProvider$37(Promise promise, AuthResult authResult) {
        Log.d(TAG, "linkWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$linkWithProvider$38(Promise promise, Exception exc) {
        Log.d(TAG, "linkWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    @ReactMethod
    public void unlink(String str, String str2, Promise promise) {
        FirebaseUser currentUser = FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser();
        Log.d(TAG, "unlink");
        if (currentUser != null) {
            currentUser.unlink(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda10(this, promise));
        } else {
            promiseNoUser(promise, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unlink$39(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "unlink:onComplete:success");
            promiseWithUser(((AuthResult) Objects.requireNonNull((AuthResult) task.getResult())).getUser(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "unlink:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void reauthenticateWithCredential(String str, String str2, String str3, String str4, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        AuthCredential credentialForProvider = getCredentialForProvider(str2, str3, str4);
        if (credentialForProvider == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "reauthenticate");
        if (currentUser != null) {
            currentUser.reauthenticateAndRetrieveData(credentialForProvider).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda11(this, promise));
        } else {
            promiseNoUser(promise, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reauthenticateWithCredential$40(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "reauthenticate:onComplete:success");
            promiseWithAuthResult((AuthResult) task.getResult(), promise);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "reauthenticate:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    private void reauthenticateWithProvider(String str, ReadableMap readableMap, Promise promise) {
        ReadableMap map;
        ReadableArray array;
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (readableMap.getString("providerId") == null) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-credential", "The supplied auth credential is malformed, has expired or is not currently supported.");
            return;
        }
        FirebaseUser currentUser = instance.getCurrentUser();
        Log.d(TAG, "reauthenticateWithProvider");
        if (currentUser == null) {
            promiseNoUser(promise, true);
            return;
        }
        OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder(readableMap.getString("providerId"), instance);
        if (readableMap.hasKey("scopes") && (array = readableMap.getArray("scopes")) != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                arrayList.add(array.getString(i));
            }
            newBuilder.setScopes(arrayList);
        }
        if (readableMap.hasKey("customParameters") && (map = readableMap.getMap("customParameters")) != null) {
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                newBuilder.addCustomParameter(nextKey, map.getString(nextKey));
            }
        }
        Task<AuthResult> pendingAuthResult = instance.getPendingAuthResult();
        if (pendingAuthResult != null) {
            pendingAuthResult.addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda22(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda33(this, promise));
        } else {
            currentUser.startActivityForReauthenticateWithProvider(getCurrentActivity(), newBuilder.build()).addOnSuccessListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda43(this, promise)).addOnFailureListener(new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda44(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reauthenticateWithProvider$41(Promise promise, AuthResult authResult) {
        Log.d(TAG, "reauthenticateWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reauthenticateWithProvider$42(Promise promise, Exception exc) {
        Log.d(TAG, "reauthenticateWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reauthenticateWithProvider$43(Promise promise, AuthResult authResult) {
        Log.d(TAG, "reauthenticateWithProvider:success");
        promiseWithAuthResult(authResult, promise);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reauthenticateWithProvider$44(Promise promise, Exception exc) {
        Log.d(TAG, "reauthenticateWithProvider:failure", exc);
        promiseRejectAuthException(promise, exc);
    }

    private AuthCredential getCredentialForProvider(String str, String str2, String str3) {
        if (str.startsWith("oidc.")) {
            return OAuthProvider.newCredentialBuilder(str).setIdToken(str2).build();
        }
        if (this.credentials.containsKey(str2) && this.credentials.get(str2) != null) {
            return this.credentials.get(str2);
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2095271699:
                if (str.equals("apple.com")) {
                    c = 0;
                    break;
                }
                break;
            case -1830313082:
                if (str.equals("twitter.com")) {
                    c = 1;
                    break;
                }
                break;
            case -1536293812:
                if (str.equals("google.com")) {
                    c = 2;
                    break;
                }
                break;
            case -364826023:
                if (str.equals("facebook.com")) {
                    c = 3;
                    break;
                }
                break;
            case 105516695:
                if (str.equals("oauth")) {
                    c = 4;
                    break;
                }
                break;
            case 106642798:
                if (str.equals("phone")) {
                    c = 5;
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    c = 6;
                    break;
                }
                break;
            case 1985010934:
                if (str.equals("github.com")) {
                    c = 7;
                    break;
                }
                break;
            case 2120171958:
                if (str.equals(EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD)) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return OAuthProvider.newCredentialBuilder(str).setIdTokenWithRawNonce(str2, str3).build();
            case 1:
                return TwitterAuthProvider.getCredential(str2, str3);
            case 2:
                return GoogleAuthProvider.getCredential(str2, str3);
            case 3:
                return FacebookAuthProvider.getCredential(str2);
            case 4:
                return OAuthProvider.getCredential(str, str2, str3);
            case 5:
                return getPhoneAuthCredential(str2, str3);
            case 6:
                return EmailAuthProvider.getCredential(str2, str3);
            case 7:
                return GithubAuthProvider.getCredential(str2);
            case 8:
                return EmailAuthProvider.getCredentialWithLink(str2, str3);
            default:
                return null;
        }
    }

    private PhoneAuthCredential getPhoneAuthCredential(String str, String str2) {
        PhoneAuthCredential phoneAuthCredential;
        if (str == null && (phoneAuthCredential = this.mCredential) != null) {
            this.mCredential = null;
            return phoneAuthCredential;
        } else if (str != null) {
            return PhoneAuthProvider.getCredential(str, str2);
        } else {
            return null;
        }
    }

    @ReactMethod
    public void getIdToken(String str, Boolean bool, Promise promise) {
        Log.d(TAG, "getIdToken");
        FirebaseUser currentUser = FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser();
        if (currentUser == null) {
            promiseNoUser(promise, true);
        } else {
            currentUser.getIdToken(bool.booleanValue()).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda14(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getIdToken$45(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "getIdToken:onComplete:success");
            promise.resolve(((GetTokenResult) Objects.requireNonNull((GetTokenResult) task.getResult())).getToken());
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "getIdToken:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void getIdTokenResult(String str, Boolean bool, Promise promise) {
        Log.d(TAG, "getIdTokenResult");
        FirebaseUser currentUser = FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser();
        if (currentUser == null) {
            promiseNoUser(promise, true);
        } else {
            currentUser.getIdToken(bool.booleanValue()).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda19(this, promise));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getIdTokenResult$46(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "getIdTokenResult:onComplete:success");
            GetTokenResult getTokenResult = (GetTokenResult) task.getResult();
            WritableMap createMap = Arguments.createMap();
            SharedUtils.mapPutValue("authTime", SharedUtils.timestampToUTC(((GetTokenResult) Objects.requireNonNull(getTokenResult)).getAuthTimestamp()), createMap);
            SharedUtils.mapPutValue("expirationTime", SharedUtils.timestampToUTC(getTokenResult.getExpirationTimestamp()), createMap);
            SharedUtils.mapPutValue("issuedAtTime", SharedUtils.timestampToUTC(getTokenResult.getIssuedAtTimestamp()), createMap);
            SharedUtils.mapPutValue("claims", getTokenResult.getClaims(), createMap);
            SharedUtils.mapPutValue("signInProvider", getTokenResult.getSignInProvider(), createMap);
            SharedUtils.mapPutValue("token", getTokenResult.getToken(), createMap);
            promise.resolve(createMap);
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "getIdTokenResult:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void fetchSignInMethodsForEmail(String str, String str2, Promise promise) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        Log.d(TAG, "fetchProvidersForEmail");
        instance.fetchSignInMethodsForEmail(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda12(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fetchSignInMethodsForEmail$47(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "fetchProvidersForEmail:onComplete:success");
            List<String> signInMethods = ((SignInMethodQueryResult) Objects.requireNonNull((SignInMethodQueryResult) task.getResult())).getSignInMethods();
            WritableArray createArray = Arguments.createArray();
            if (signInMethods != null) {
                for (String pushString : signInMethods) {
                    createArray.pushString(pushString);
                }
            }
            promise.resolve(createArray);
            return;
        }
        Exception exception = task.getException();
        Log.d(TAG, "fetchProvidersForEmail:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void setLanguageCode(String str, String str2) {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(str));
        if (str2 == null) {
            instance.useAppLanguage();
        } else {
            instance.setLanguageCode(str2);
        }
    }

    @ReactMethod
    public void setTenantId(String str, String str2) {
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).setTenantId(str2);
    }

    @ReactMethod
    public void useDeviceLanguage(String str) {
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).useAppLanguage();
    }

    @ReactMethod
    public void verifyPasswordResetCode(String str, String str2, Promise promise) {
        Log.d(TAG, "verifyPasswordResetCode");
        FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).verifyPasswordResetCode(str2).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAuthModule$$ExternalSyntheticLambda5(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$verifyPasswordResetCode$48(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Log.d(TAG, "verifyPasswordResetCode:onComplete:success");
            promise.resolve(task.getResult());
            return;
        }
        Exception exception = task.getException();
        SentryLogcatAdapter.e(TAG, "verifyPasswordResetCode:onComplete:failure", exception);
        promiseRejectAuthException(promise, exception);
    }

    @ReactMethod
    public void useEmulator(String str, String str2, int i) {
        if (emulatorConfigs.get(str) == null) {
            emulatorConfigs.put(str, "true");
            FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).useEmulator(str2, i);
        }
    }

    private void promiseNoUser(Promise promise, Boolean bool) {
        if (bool.booleanValue()) {
            rejectPromiseWithCodeAndMessage(promise, "no-current-user", "No user currently signed in.");
        } else {
            promise.resolve((Object) null);
        }
    }

    private void promiseWithUser(@Nullable FirebaseUser firebaseUser, Promise promise) {
        if (firebaseUser != null) {
            promise.resolve(firebaseUserToMap(firebaseUser));
        } else {
            promiseNoUser(promise, true);
        }
    }

    private void promiseWithAuthResult(AuthResult authResult, Promise promise) {
        if (authResult == null || authResult.getUser() == null) {
            promiseNoUser(promise, true);
            return;
        }
        WritableMap createMap = Arguments.createMap();
        WritableMap firebaseUserToMap = firebaseUserToMap(authResult.getUser());
        if (authResult.getAdditionalUserInfo() != null) {
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putBoolean("isNewUser", authResult.getAdditionalUserInfo().isNewUser());
            if (authResult.getAdditionalUserInfo().getProfile() != null) {
                SharedUtils.mapPutValue(Scopes.PROFILE, authResult.getAdditionalUserInfo().getProfile(), createMap2);
            }
            if (authResult.getAdditionalUserInfo().getProviderId() != null) {
                createMap2.putString("providerId", authResult.getAdditionalUserInfo().getProviderId());
            }
            if (authResult.getAdditionalUserInfo().getUsername() != null) {
                createMap2.putString("username", authResult.getAdditionalUserInfo().getUsername());
            }
            createMap.putMap("additionalUserInfo", createMap2);
        }
        createMap.putMap("user", firebaseUserToMap);
        promise.resolve(createMap);
    }

    /* access modifiers changed from: private */
    public void promiseRejectAuthException(Promise promise, Exception exc) {
        WritableMap jSError = getJSError(exc);
        String string = jSError.hasKey("sessionId") ? jSError.getString("sessionId") : null;
        MultiFactorResolver multiFactorResolver = this.mCachedResolvers.get(string);
        WritableMap createMap = Arguments.createMap();
        if (multiFactorResolver != null) {
            createMap = resolverToMap(string, multiFactorResolver);
        }
        rejectPromiseWithCodeAndMessage(promise, jSError.getString(UniversalFirebaseFunctionsModule.CODE_KEY), jSError.getString("message"), (ReadableMap) createMap);
    }

    private void promiseRejectLinkAuthException(Promise promise, Exception exc, AuthCredential authCredential) {
        WritableMap jSError = getJSError(exc);
        String valueOf = String.valueOf(authCredential.hashCode());
        WritableMap createMap = Arguments.createMap();
        createMap.putString("providerId", authCredential.getProvider());
        createMap.putString("token", valueOf);
        createMap.putString("secret", (String) null);
        this.credentials.put(valueOf, authCredential);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putString(UniversalFirebaseFunctionsModule.CODE_KEY, jSError.getString(UniversalFirebaseFunctionsModule.CODE_KEY));
        createMap2.putString("message", jSError.getString("message"));
        createMap2.putMap("authCredential", createMap);
        promise.reject(jSError.getString(UniversalFirebaseFunctionsModule.CODE_KEY), jSError.getString("message"), createMap2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x019f, code lost:
        if (r0.equals("ERROR_UNVERIFIED_EMAIL") == false) goto L_0x0197;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0197 A[FALL_THROUGH] */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0177  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.WritableMap getJSError(java.lang.Exception r13) {
        /*
            r12 = this;
            java.lang.String r0 = "INVALID_EMAIL"
            com.facebook.react.bridge.WritableMap r1 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r2 = "UNKNOWN"
            java.lang.String r3 = r13.getMessage()
            java.lang.String r4 = "The email address is badly formatted."
            r5 = 2
            r6 = 0
            r7 = -1
            r8 = 1
            r9 = r13
            com.google.firebase.auth.FirebaseAuthException r9 = (com.google.firebase.auth.FirebaseAuthException) r9     // Catch:{ Exception -> 0x0024 }
            java.lang.String r10 = r9.getErrorCode()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r11 = "nativeErrorCode"
            r1.putString(r11, r10)     // Catch:{ Exception -> 0x0025 }
            java.lang.String r3 = r9.getMessage()     // Catch:{ Exception -> 0x0025 }
            goto L_0x014e
        L_0x0024:
            r10 = r2
        L_0x0025:
            java.lang.String r9 = "([A-Z]*_[A-Z]*)"
            java.util.regex.Pattern r9 = java.util.regex.Pattern.compile(r9)
            java.util.regex.Matcher r9 = r9.matcher(r3)
            boolean r11 = r9.find()
            if (r11 == 0) goto L_0x014e
            java.lang.String r9 = r9.group(r8)
            java.lang.String r10 = r9.trim()
            r10.hashCode()
            int r9 = r10.hashCode()
            switch(r9) {
                case -2127468245: goto L_0x0111;
                case -1971163201: goto L_0x0105;
                case -1112393964: goto L_0x00fb;
                case -1035666916: goto L_0x00ef;
                case -333672188: goto L_0x00e3;
                case -324930558: goto L_0x00d7;
                case -311841705: goto L_0x00cb;
                case -75433118: goto L_0x00bf;
                case -49749054: goto L_0x00b2;
                case -40686718: goto L_0x00a5;
                case 583750925: goto L_0x0098;
                case 748182870: goto L_0x008b;
                case 864281573: goto L_0x007e;
                case 1072360691: goto L_0x0071;
                case 1388786705: goto L_0x0064;
                case 1433767024: goto L_0x0057;
                case 1563975629: goto L_0x004a;
                default: goto L_0x0047;
            }
        L_0x0047:
            r9 = r7
            goto L_0x011c
        L_0x004a:
            java.lang.String r9 = "INVALID_USER_TOKEN"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x0053
            goto L_0x0047
        L_0x0053:
            r9 = 16
            goto L_0x011c
        L_0x0057:
            java.lang.String r9 = "USER_DISABLED"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x0060
            goto L_0x0047
        L_0x0060:
            r9 = 15
            goto L_0x011c
        L_0x0064:
            java.lang.String r9 = "INVALID_IDENTIFIER"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x006d
            goto L_0x0047
        L_0x006d:
            r9 = 14
            goto L_0x011c
        L_0x0071:
            java.lang.String r9 = "INVALID_CUSTOM_TOKEN"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x007a
            goto L_0x0047
        L_0x007a:
            r9 = 13
            goto L_0x011c
        L_0x007e:
            java.lang.String r9 = "ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x0087
            goto L_0x0047
        L_0x0087:
            r9 = 12
            goto L_0x011c
        L_0x008b:
            java.lang.String r9 = "REQUIRES_RECENT_LOGIN"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x0094
            goto L_0x0047
        L_0x0094:
            r9 = 11
            goto L_0x011c
        L_0x0098:
            java.lang.String r9 = "WRONG_PASSWORD"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00a1
            goto L_0x0047
        L_0x00a1:
            r9 = 10
            goto L_0x011c
        L_0x00a5:
            java.lang.String r9 = "WEAK_PASSWORD"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00ae
            goto L_0x0047
        L_0x00ae:
            r9 = 9
            goto L_0x011c
        L_0x00b2:
            java.lang.String r9 = "USER_MISMATCH"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00bb
            goto L_0x0047
        L_0x00bb:
            r9 = 8
            goto L_0x011c
        L_0x00bf:
            java.lang.String r9 = "USER_NOT_FOUND"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00c9
            goto L_0x0047
        L_0x00c9:
            r9 = 7
            goto L_0x011c
        L_0x00cb:
            java.lang.String r9 = "EMAIL_ALREADY_IN_USE"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00d5
            goto L_0x0047
        L_0x00d5:
            r9 = 6
            goto L_0x011c
        L_0x00d7:
            java.lang.String r9 = "CUSTOM_TOKEN_MISMATCH"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00e1
            goto L_0x0047
        L_0x00e1:
            r9 = 5
            goto L_0x011c
        L_0x00e3:
            java.lang.String r9 = "OPERATION_NOT_ALLOWED"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00ed
            goto L_0x0047
        L_0x00ed:
            r9 = 4
            goto L_0x011c
        L_0x00ef:
            java.lang.String r9 = "CREDENTIAL_ALREADY_IN_USE"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00f9
            goto L_0x0047
        L_0x00f9:
            r9 = 3
            goto L_0x011c
        L_0x00fb:
            boolean r9 = r10.equals(r0)
            if (r9 != 0) goto L_0x0103
            goto L_0x0047
        L_0x0103:
            r9 = r5
            goto L_0x011c
        L_0x0105:
            java.lang.String r9 = "INVALID_CREDENTIAL"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x010f
            goto L_0x0047
        L_0x010f:
            r9 = r8
            goto L_0x011c
        L_0x0111:
            java.lang.String r9 = "USER_TOKEN_EXPIRED"
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x011b
            goto L_0x0047
        L_0x011b:
            r9 = r6
        L_0x011c:
            java.lang.String r11 = "The user's credential is no longer valid. The user must sign in again."
            switch(r9) {
                case 0: goto L_0x014d;
                case 1: goto L_0x014a;
                case 2: goto L_0x0148;
                case 3: goto L_0x0145;
                case 4: goto L_0x0142;
                case 5: goto L_0x013f;
                case 6: goto L_0x013c;
                case 7: goto L_0x0139;
                case 8: goto L_0x0136;
                case 9: goto L_0x0133;
                case 10: goto L_0x0130;
                case 11: goto L_0x012d;
                case 12: goto L_0x012a;
                case 13: goto L_0x0127;
                case 14: goto L_0x0125;
                case 15: goto L_0x0122;
                case 16: goto L_0x014d;
                default: goto L_0x0121;
            }
        L_0x0121:
            goto L_0x014e
        L_0x0122:
            java.lang.String r3 = "The user account has been disabled by an administrator."
            goto L_0x014e
        L_0x0125:
            r10 = r0
            goto L_0x0148
        L_0x0127:
            java.lang.String r3 = "The custom token format is incorrect. Please check the documentation."
            goto L_0x014e
        L_0x012a:
            java.lang.String r3 = "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."
            goto L_0x014e
        L_0x012d:
            java.lang.String r3 = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."
            goto L_0x014e
        L_0x0130:
            java.lang.String r3 = "The password is invalid or the user does not have a password."
            goto L_0x014e
        L_0x0133:
            java.lang.String r3 = "The given password is invalid."
            goto L_0x014e
        L_0x0136:
            java.lang.String r3 = "The supplied credentials do not correspond to the previously signed in user."
            goto L_0x014e
        L_0x0139:
            java.lang.String r3 = "There is no user record corresponding to this identifier. The user may have been deleted."
            goto L_0x014e
        L_0x013c:
            java.lang.String r3 = "The email address is already in use by another account."
            goto L_0x014e
        L_0x013f:
            java.lang.String r3 = "The custom token corresponds to a different audience."
            goto L_0x014e
        L_0x0142:
            java.lang.String r3 = "This operation is not allowed. You must enable this service in the console."
            goto L_0x014e
        L_0x0145:
            java.lang.String r3 = "This credential is already associated with a different user account."
            goto L_0x014e
        L_0x0148:
            r3 = r4
            goto L_0x014e
        L_0x014a:
            java.lang.String r3 = "The supplied auth credential is malformed or has expired."
            goto L_0x014e
        L_0x014d:
            r3 = r11
        L_0x014e:
            boolean r9 = r13 instanceof com.google.firebase.auth.FirebaseAuthMultiFactorException
            if (r9 == 0) goto L_0x0171
            r9 = r13
            com.google.firebase.auth.FirebaseAuthMultiFactorException r9 = (com.google.firebase.auth.FirebaseAuthMultiFactorException) r9
            com.google.firebase.auth.MultiFactorResolver r9 = r9.getResolver()
            com.google.firebase.auth.MultiFactorSession r10 = r9.getSession()
            int r10 = r10.hashCode()
            java.lang.String r10 = java.lang.Integer.toString(r10)
            java.util.HashMap<java.lang.String, com.google.firebase.auth.MultiFactorResolver> r11 = r12.mCachedResolvers
            r11.put(r10, r9)
            java.lang.String r9 = "sessionId"
            r1.putString(r9, r10)
            java.lang.String r10 = "MULTI_FACTOR_AUTH_REQUIRED"
        L_0x0171:
            boolean r2 = r10.equals(r2)
            if (r2 == 0) goto L_0x018b
            boolean r2 = r13 instanceof com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
            if (r2 == 0) goto L_0x017c
            goto L_0x018d
        L_0x017c:
            boolean r0 = r13 instanceof com.google.firebase.FirebaseNetworkException
            if (r0 == 0) goto L_0x0184
            java.lang.String r0 = "NETWORK_REQUEST_FAILED"
        L_0x0182:
            r4 = r3
            goto L_0x018d
        L_0x0184:
            boolean r0 = r13 instanceof com.google.firebase.FirebaseTooManyRequestsException
            if (r0 == 0) goto L_0x018b
            java.lang.String r0 = "TOO_MANY_REQUESTS"
            goto L_0x0182
        L_0x018b:
            r4 = r3
            r0 = r10
        L_0x018d:
            r0.hashCode()
            int r2 = r0.hashCode()
            switch(r2) {
                case -1904937287: goto L_0x01ad;
                case -282314177: goto L_0x01a2;
                case -51368043: goto L_0x0199;
                default: goto L_0x0197;
            }
        L_0x0197:
            r5 = r7
            goto L_0x01b7
        L_0x0199:
            java.lang.String r2 = "ERROR_UNVERIFIED_EMAIL"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x01b7
            goto L_0x0197
        L_0x01a2:
            java.lang.String r2 = "ERROR_UNSUPPORTED_FIRST_FACTOR"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x01ab
            goto L_0x0197
        L_0x01ab:
            r5 = r8
            goto L_0x01b7
        L_0x01ad:
            java.lang.String r2 = "ERROR_INVALID_PHONE_NUMBER"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x01b6
            goto L_0x0197
        L_0x01b6:
            r5 = r6
        L_0x01b7:
            switch(r5) {
                case 0: goto L_0x01c1;
                case 1: goto L_0x01be;
                case 2: goto L_0x01bb;
                default: goto L_0x01ba;
            }
        L_0x01ba:
            goto L_0x01c3
        L_0x01bb:
            java.lang.String r4 = "This operation requires a verified email."
            goto L_0x01c3
        L_0x01be:
            java.lang.String r4 = "Enrolling a second factor or signing in with a multi-factor account requires sign-in with a supported first factor."
            goto L_0x01c3
        L_0x01c1:
            java.lang.String r4 = "The format of the phone number provided is incorrect. Please enter the phone number in a format that can be parsed into E.164 format. E.164 phone numbers are written in the format [+][country code][subscriber number including area code]."
        L_0x01c3:
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r2)
            java.lang.String r2 = "error_"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replace(r2, r3)
            r2 = 95
            r3 = 45
            java.lang.String r0 = r0.replace(r2, r3)
            java.lang.String r2 = "code"
            r1.putString(r2, r0)
            java.lang.String r0 = "message"
            r1.putString(r0, r4)
            java.lang.String r0 = "nativeErrorMessage"
            java.lang.String r13 = r13.getMessage()
            r1.putString(r0, r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.auth.ReactNativeFirebaseAuthModule.getJSError(java.lang.Exception):com.facebook.react.bridge.WritableMap");
    }

    private WritableArray convertProviderData(List<? extends UserInfo> list, FirebaseUser firebaseUser) {
        WritableArray createArray = Arguments.createArray();
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            UserInfo userInfo = (UserInfo) it.next();
            if (!"firebase".equals(userInfo.getProviderId())) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("providerId", userInfo.getProviderId());
                createMap.putString("uid", userInfo.getUid());
                createMap.putString("displayName", userInfo.getDisplayName());
                Uri photoUrl = userInfo.getPhotoUrl();
                if (photoUrl == null || "".equals(photoUrl.toString())) {
                    createMap.putNull("photoURL");
                } else {
                    createMap.putString("photoURL", photoUrl.toString());
                }
                String phoneNumber = userInfo.getPhoneNumber();
                if ("phone".equals(userInfo.getProviderId()) && (userInfo.getPhoneNumber() == null || "".equals(userInfo.getPhoneNumber()))) {
                    createMap.putString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, firebaseUser.getPhoneNumber());
                } else if (phoneNumber == null || "".equals(phoneNumber)) {
                    createMap.putNull(HintConstants.AUTOFILL_HINT_PHONE_NUMBER);
                } else {
                    createMap.putString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, phoneNumber);
                }
                if ("password".equals(userInfo.getProviderId()) && (userInfo.getEmail() == null || "".equals(userInfo.getEmail()))) {
                    createMap.putString("email", userInfo.getUid());
                } else if (userInfo.getEmail() == null || "".equals(userInfo.getEmail())) {
                    createMap.putNull("email");
                } else {
                    createMap.putString("email", userInfo.getEmail());
                }
                createArray.pushMap(createMap);
            }
        }
        return createArray;
    }

    private WritableMap firebaseUserToMap(FirebaseUser firebaseUser) {
        WritableMap createMap = Arguments.createMap();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        Uri photoUrl = firebaseUser.getPhotoUrl();
        String displayName = firebaseUser.getDisplayName();
        String providerId = firebaseUser.getProviderId();
        boolean isEmailVerified = firebaseUser.isEmailVerified();
        String phoneNumber = firebaseUser.getPhoneNumber();
        String tenantId = firebaseUser.getTenantId();
        createMap.putString("uid", uid);
        createMap.putString("providerId", providerId);
        createMap.putBoolean("emailVerified", isEmailVerified);
        createMap.putBoolean("isAnonymous", firebaseUser.isAnonymous());
        if (email == null || "".equals(email)) {
            createMap.putNull("email");
        } else {
            createMap.putString("email", email);
        }
        if (displayName == null || "".equals(displayName)) {
            createMap.putNull("displayName");
        } else {
            createMap.putString("displayName", displayName);
        }
        if (photoUrl == null || "".equals(photoUrl.toString())) {
            createMap.putNull("photoURL");
        } else {
            createMap.putString("photoURL", photoUrl.toString());
        }
        if (phoneNumber == null || "".equals(phoneNumber)) {
            createMap.putNull(HintConstants.AUTOFILL_HINT_PHONE_NUMBER);
        } else {
            createMap.putString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, phoneNumber);
        }
        if (tenantId == null || "".equals(tenantId)) {
            createMap.putNull("tenantId");
        } else {
            createMap.putString("tenantId", tenantId);
        }
        createMap.putArray("providerData", convertProviderData(firebaseUser.getProviderData(), firebaseUser));
        WritableMap createMap2 = Arguments.createMap();
        FirebaseUserMetadata metadata = firebaseUser.getMetadata();
        if (metadata != null) {
            createMap2.putDouble("creationTime", (double) metadata.getCreationTimestamp());
            createMap2.putDouble("lastSignInTime", (double) metadata.getLastSignInTimestamp());
        }
        createMap.putMap(TtmlNode.TAG_METADATA, createMap2);
        WritableArray createArray = Arguments.createArray();
        for (MultiFactorInfo multiFactorInfoToMap : firebaseUser.getMultiFactor().getEnrolledFactors()) {
            createArray.pushMap(multiFactorInfoToMap(multiFactorInfoToMap));
        }
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putArray("enrolledFactors", createArray);
        createMap.putMap("multiFactor", createMap3);
        return createMap;
    }

    private WritableMap resolverToMap(String str, MultiFactorResolver multiFactorResolver) {
        WritableMap createMap = Arguments.createMap();
        WritableArray createArray = Arguments.createArray();
        for (MultiFactorInfo multiFactorInfoToMap : multiFactorResolver.getHints()) {
            createArray.pushMap(multiFactorInfoToMap(multiFactorInfoToMap));
        }
        createMap.putArray("hints", createArray);
        createMap.putString(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, str);
        return createMap;
    }

    private WritableMap multiFactorInfoToMap(MultiFactorInfo multiFactorInfo) {
        WritableMap createMap = Arguments.createMap();
        Date date = new Date(multiFactorInfo.getEnrollmentTimestamp() * 1000);
        createMap.putString("displayName", multiFactorInfo.getDisplayName());
        createMap.putString("enrollmentTime", ISO_8601_FORMATTER.format(date));
        createMap.putString("factorId", multiFactorInfo.getFactorId());
        createMap.putString("uid", multiFactorInfo.getUid());
        if (multiFactorInfo.getFactorId().equals("phone")) {
            createMap.putString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, ((PhoneMultiFactorInfo) multiFactorInfo).getPhoneNumber());
        }
        return createMap;
    }

    private ActionCodeSettings buildActionCodeSettings(ReadableMap readableMap) {
        ActionCodeSettings.Builder url = ActionCodeSettings.newBuilder().setUrl((String) Objects.requireNonNull(readableMap.getString("url")));
        if (readableMap.hasKey("handleCodeInApp")) {
            url = url.setHandleCodeInApp(readableMap.getBoolean("handleCodeInApp"));
        }
        if (readableMap.hasKey("dynamicLinkDomain")) {
            url = url.setDynamicLinkDomain(readableMap.getString("dynamicLinkDomain"));
        }
        if (readableMap.hasKey(DeviceInfo.OS_NAME)) {
            ReadableMap map = readableMap.getMap(DeviceInfo.OS_NAME);
            url = url.setAndroidPackageName((String) Objects.requireNonNull(map.getString(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME)), ((ReadableMap) Objects.requireNonNull(map)).hasKey("installApp") && map.getBoolean("installApp"), map.hasKey("minimumVersion") ? map.getString("minimumVersion") : null);
        }
        if (readableMap.hasKey("iOS")) {
            url = url.setIOSBundleId((String) Objects.requireNonNull(readableMap.getMap("iOS").getString("bundleId")));
        }
        return url.build();
    }

    /* access modifiers changed from: private */
    public void sendPhoneStateEvent(String str, String str2, String str3, WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
        createMap.putString("appName", str);
        createMap.putString("requestKey", str2);
        createMap.putString("type", str3);
        createMap.putMap("state", writableMap);
        sharedInstance.sendEvent(new ReactNativeFirebaseEvent("phone_auth_state_changed", createMap, str));
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        List<FirebaseApp> apps = FirebaseApp.getApps(getReactApplicationContext());
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        for (FirebaseApp name : apps) {
            String name2 = name.getName();
            FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(name2));
            FirebaseUser currentUser = instance.getCurrentUser();
            hashMap2.put(name2, instance.getLanguageCode());
            if (currentUser != null) {
                hashMap3.put(name2, firebaseUserToMap(currentUser));
            }
        }
        hashMap.put("APP_LANGUAGE", hashMap2);
        hashMap.put("APP_USER", hashMap3);
        return hashMap;
    }
}
