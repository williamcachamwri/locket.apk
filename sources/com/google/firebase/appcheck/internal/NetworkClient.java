package com.google.firebase.appcheck.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;

public class NetworkClient {
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    public static final int DEBUG = 2;
    private static final String DEBUG_EXCHANGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:exchangeDebugToken?key=%s";
    public static final int PLAY_INTEGRITY = 3;
    private static final String PLAY_INTEGRITY_CHALLENGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:generatePlayIntegrityChallenge?key=%s";
    private static final String PLAY_INTEGRITY_EXCHANGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:exchangePlayIntegrityToken?key=%s";
    private static final String TAG = "com.google.firebase.appcheck.internal.NetworkClient";
    public static final int UNKNOWN = 0;
    private static final String UTF_8 = "UTF-8";
    static final String X_ANDROID_CERT = "X-Android-Cert";
    static final String X_ANDROID_PACKAGE = "X-Android-Package";
    static final String X_FIREBASE_CLIENT = "X-Firebase-Client";
    private final String apiKey;
    private final String appId;
    private final Context context;
    private final Provider<HeartBeatController> heartBeatControllerProvider;
    private final String projectId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttestationTokenType {
    }

    private static final boolean isResponseSuccess(int i) {
        return i >= 200 && i < 300;
    }

    public NetworkClient(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), firebaseApp.getOptions(), ((DefaultFirebaseAppCheck) FirebaseAppCheck.getInstance(firebaseApp)).getHeartbeatControllerProvider());
    }

    NetworkClient(Context context2, FirebaseOptions firebaseOptions, Provider<HeartBeatController> provider) {
        Preconditions.checkNotNull(context2);
        Preconditions.checkNotNull(firebaseOptions);
        Preconditions.checkNotNull(provider);
        this.context = context2;
        this.apiKey = firebaseOptions.getApiKey();
        this.appId = firebaseOptions.getApplicationId();
        String projectId2 = firebaseOptions.getProjectId();
        this.projectId = projectId2;
        if (projectId2 != null) {
            this.heartBeatControllerProvider = provider;
            return;
        }
        throw new IllegalArgumentException("FirebaseOptions#getProjectId cannot be null.");
    }

    public AppCheckTokenResponse exchangeAttestationForAppCheckToken(byte[] bArr, int i, RetryManager retryManager) throws FirebaseException, IOException, JSONException {
        if (retryManager.canRetry()) {
            return AppCheckTokenResponse.fromJsonString(makeNetworkRequest(new URL(String.format(getUrlTemplate(i), new Object[]{this.projectId, this.appId, this.apiKey})), bArr, retryManager, true));
        }
        throw new FirebaseException("Too many attempts.");
    }

    public String generatePlayIntegrityChallenge(byte[] bArr, RetryManager retryManager) throws FirebaseException, IOException, JSONException {
        if (retryManager.canRetry()) {
            return makeNetworkRequest(new URL(String.format(PLAY_INTEGRITY_CHALLENGE_URL_TEMPLATE, new Object[]{this.projectId, this.appId, this.apiKey})), bArr, retryManager, false);
        }
        throw new FirebaseException("Too many attempts.");
    }

    private String makeNetworkRequest(URL url, byte[] bArr, RetryManager retryManager, boolean z) throws FirebaseException, IOException, JSONException {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream;
        BufferedReader bufferedReader;
        HttpURLConnection createHttpUrlConnection = createHttpUrlConnection(url);
        try {
            createHttpUrlConnection.setDoOutput(true);
            createHttpUrlConnection.setFixedLengthStreamingMode(bArr.length);
            createHttpUrlConnection.setRequestProperty("Content-Type", APPLICATION_JSON);
            String heartBeat = getHeartBeat();
            if (heartBeat != null) {
                createHttpUrlConnection.setRequestProperty(X_FIREBASE_CLIENT, heartBeat);
            }
            createHttpUrlConnection.setRequestProperty(X_ANDROID_PACKAGE, this.context.getPackageName());
            createHttpUrlConnection.setRequestProperty(X_ANDROID_CERT, getFingerprintHashForPackage());
            bufferedOutputStream = new BufferedOutputStream(createHttpUrlConnection.getOutputStream(), bArr.length);
            bufferedOutputStream.write(bArr, 0, bArr.length);
            bufferedOutputStream.close();
            int responseCode = createHttpUrlConnection.getResponseCode();
            if (isResponseSuccess(responseCode)) {
                inputStream = createHttpUrlConnection.getInputStream();
            } else {
                inputStream = createHttpUrlConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (isResponseSuccess(responseCode)) {
                if (z) {
                    retryManager.resetBackoffOnSuccess();
                }
                createHttpUrlConnection.disconnect();
                return sb2;
            }
            retryManager.updateBackoffOnFailure(responseCode);
            HttpErrorResponse fromJsonString = HttpErrorResponse.fromJsonString(sb2);
            throw new FirebaseException("Error returned from API. code: " + fromJsonString.getErrorCode() + " body: " + fromJsonString.getErrorMessage());
            throw th;
            throw th;
        } catch (Throwable th) {
            createHttpUrlConnection.disconnect();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public String getHeartBeat() {
        HeartBeatController heartBeatController = this.heartBeatControllerProvider.get();
        if (heartBeatController != null) {
            try {
                return (String) Tasks.await(heartBeatController.getHeartBeatsHeader());
            } catch (Exception unused) {
                SentryLogcatAdapter.w(TAG, "Unable to get heartbeats!");
            }
        }
        return null;
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context2 = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context2, context2.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            SentryLogcatAdapter.e(TAG, "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            SentryLogcatAdapter.e(TAG, "No such package: " + this.context.getPackageName(), e);
            return null;
        }
    }

    private static String getUrlTemplate(int i) {
        if (i == 2) {
            return DEBUG_EXCHANGE_URL_TEMPLATE;
        }
        if (i == 3) {
            return PLAY_INTEGRITY_EXCHANGE_URL_TEMPLATE;
        }
        throw new IllegalArgumentException("Unknown token type.");
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection createHttpUrlConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
