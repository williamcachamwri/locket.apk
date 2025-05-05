package com.google.firebase.storage.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactory;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactoryImpl;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class NetworkRequest {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE = "Content-Type";
    static final String DELETE = "DELETE";
    static final String GET = "GET";
    public static final int INITIALIZATION_EXCEPTION = -1;
    private static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    static final String PATCH = "PATCH";
    static final String POST = "POST";
    public static final Uri PROD_BASE_URL = Uri.parse("https://firebasestorage.googleapis.com/v0");
    static final String PUT = "PUT";
    private static final String TAG = "NetworkRequest";
    private static final String UTF_8 = "UTF-8";
    private static final String X_FIREBASE_APPCHECK = "x-firebase-appcheck";
    private static final String X_FIREBASE_GMPID = "x-firebase-gmpid";
    static HttpURLConnectionFactory connectionFactory = new HttpURLConnectionFactoryImpl();
    private HttpURLConnection connection;
    private Context context;
    protected Exception mException;
    private String rawStringResponse;
    private Map<String, String> requestHeaders = new HashMap();
    private int resultCode;
    private Map<String, List<String>> resultHeaders;
    private InputStream resultInputStream;
    private int resultingContentLength;
    private StorageReferenceUri storageReferenceUri;

    /* access modifiers changed from: protected */
    public abstract String getAction();

    /* access modifiers changed from: protected */
    public JSONObject getOutputJSON() {
        return null;
    }

    /* access modifiers changed from: protected */
    public byte[] getOutputRaw() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int getOutputRawSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParameters() {
        return null;
    }

    public NetworkRequest(StorageReferenceUri storageReferenceUri2, FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(storageReferenceUri2);
        Preconditions.checkNotNull(firebaseApp);
        this.storageReferenceUri = storageReferenceUri2;
        this.context = firebaseApp.getApplicationContext();
        setCustomHeader(X_FIREBASE_GMPID, firebaseApp.getOptions().getApplicationId());
    }

    public static Uri getBaseUrl(EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings != null) {
            return Uri.parse("http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/v0");
        }
        return PROD_BASE_URL;
    }

    private static String getPathWithoutBucket(Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return "";
        }
        return path.startsWith("/") ? path.substring(1) : path;
    }

    /* access modifiers changed from: package-private */
    public String getPathWithoutBucket() {
        return getPathWithoutBucket(this.storageReferenceUri.getGsUri());
    }

    public Uri getURL() {
        return this.storageReferenceUri.getHttpUri();
    }

    /* access modifiers changed from: protected */
    public StorageReferenceUri getStorageReferenceUri() {
        return this.storageReferenceUri;
    }

    public final void reset() {
        this.mException = null;
        this.resultCode = 0;
    }

    public void setCustomHeader(String str, String str2) {
        this.requestHeaders.put(str, str2);
    }

    public InputStream getStream() {
        return this.resultInputStream;
    }

    public JSONObject getResultBody() {
        if (TextUtils.isEmpty(this.rawStringResponse)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(this.rawStringResponse);
        } catch (JSONException e) {
            SentryLogcatAdapter.e(TAG, "error parsing result into JSON:" + this.rawStringResponse, e);
            return new JSONObject();
        }
    }

    public void performRequestStart(String str, String str2) {
        if (this.mException != null) {
            this.resultCode = -1;
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "sending network request " + getAction() + " " + getURL());
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.resultCode = -2;
            this.mException = new SocketException("Network subsystem is unavailable");
            return;
        }
        try {
            HttpURLConnection createConnection = createConnection();
            this.connection = createConnection;
            createConnection.setRequestMethod(getAction());
            constructMessage(this.connection, str, str2);
            parseResponse(this.connection);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "network request result " + this.resultCode);
            }
        } catch (IOException e) {
            SentryLogcatAdapter.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
    }

    public void performRequestEnd() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private final void performRequest(String str, String str2) {
        performRequestStart(str, str2);
        try {
            processResponseStream();
        } catch (IOException e) {
            SentryLogcatAdapter.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
        performRequestEnd();
    }

    public void performRequest(String str, String str2, Context context2) {
        if (ensureNetworkAvailable(context2)) {
            performRequest(str, str2);
        }
    }

    private boolean ensureNetworkAvailable(Context context2) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        this.mException = new SocketException("Network subsystem is unavailable");
        this.resultCode = -2;
        return false;
    }

    private HttpURLConnection createConnection() throws IOException {
        Uri url = getURL();
        Map<String, String> queryParameters = getQueryParameters();
        if (queryParameters != null) {
            Uri.Builder buildUpon = url.buildUpon();
            for (Map.Entry next : queryParameters.entrySet()) {
                buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
            }
            url = buildUpon.build();
        }
        return connectionFactory.createInstance(new URL(url.toString()));
    }

    private void constructMessage(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        byte[] bArr;
        int i;
        Preconditions.checkNotNull(httpURLConnection);
        if (!TextUtils.isEmpty(str)) {
            httpURLConnection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Firebase " + str);
        } else {
            SentryLogcatAdapter.w(TAG, "no auth token for request");
        }
        if (!TextUtils.isEmpty(str2)) {
            httpURLConnection.setRequestProperty(X_FIREBASE_APPCHECK, str2);
        } else {
            SentryLogcatAdapter.w(TAG, "No App Check token for request.");
        }
        httpURLConnection.setRequestProperty("X-Firebase-Storage-Version", "Android/21.0.1");
        for (Map.Entry next : this.requestHeaders.entrySet()) {
            httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
        }
        JSONObject outputJSON = getOutputJSON();
        if (outputJSON != null) {
            bArr = outputJSON.toString().getBytes("UTF-8");
            i = bArr.length;
        } else {
            bArr = getOutputRaw();
            i = getOutputRawSize();
            if (i == 0 && bArr != null) {
                i = bArr.length;
            }
        }
        if (bArr == null || bArr.length <= 0) {
            httpURLConnection.setRequestProperty("Content-Length", "0");
        } else {
            if (outputJSON != null) {
                httpURLConnection.setRequestProperty("Content-Type", APPLICATION_JSON);
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(i));
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        if (bArr != null && bArr.length > 0) {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            if (outputStream != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                try {
                    bufferedOutputStream.write(bArr, 0, i);
                } finally {
                    bufferedOutputStream.close();
                }
            } else {
                SentryLogcatAdapter.e(TAG, "Unable to write to the http request!");
            }
        }
    }

    private void parseResponse(HttpURLConnection httpURLConnection) throws IOException {
        Preconditions.checkNotNull(httpURLConnection);
        this.resultCode = httpURLConnection.getResponseCode();
        this.resultHeaders = httpURLConnection.getHeaderFields();
        this.resultingContentLength = httpURLConnection.getContentLength();
        if (isResultSuccess()) {
            this.resultInputStream = httpURLConnection.getInputStream();
        } else {
            this.resultInputStream = httpURLConnection.getErrorStream();
        }
    }

    private void parseResponse(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                } finally {
                    bufferedReader.close();
                }
            }
        }
        this.rawStringResponse = sb.toString();
        if (!isResultSuccess()) {
            this.mException = new IOException(this.rawStringResponse);
        }
    }

    private void processResponseStream() throws IOException {
        if (isResultSuccess()) {
            parseSuccessulResponse(this.resultInputStream);
        } else {
            parseErrorResponse(this.resultInputStream);
        }
    }

    /* access modifiers changed from: protected */
    public void parseSuccessulResponse(InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    /* access modifiers changed from: protected */
    public void parseErrorResponse(InputStream inputStream) throws IOException {
        parseResponse(inputStream);
    }

    public String getRawResult() {
        return this.rawStringResponse;
    }

    public Map<String, String> getResultHeaders() {
        return this.requestHeaders;
    }

    public Exception getException() {
        return this.mException;
    }

    public Map<String, List<String>> getResultHeadersImpl() {
        return this.resultHeaders;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public boolean isResultSuccess() {
        int i = this.resultCode;
        return i >= 200 && i < 300;
    }

    public String getResultString(String str) {
        List list;
        Map<String, List<String>> resultHeadersImpl = getResultHeadersImpl();
        if (resultHeadersImpl == null || (list = resultHeadersImpl.get(str)) == null || list.size() <= 0) {
            return null;
        }
        return (String) list.get(0);
    }

    public int getResultingContentLength() {
        return this.resultingContentLength;
    }

    public <TResult> void completeTask(TaskCompletionSource<TResult> taskCompletionSource, TResult tresult) {
        Exception exception = getException();
        if (!isResultSuccess() || exception != null) {
            taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exception, getResultCode()));
        } else {
            taskCompletionSource.setResult(tresult);
        }
    }
}
