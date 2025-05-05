package com.google.firebase.functions;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedInject;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Executor;
import javax.inject.Named;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseFunctions {
    private static boolean providerInstallStarted = false;
    /* access modifiers changed from: private */
    public static final TaskCompletionSource<Void> providerInstalled = new TaskCompletionSource<>();
    private final OkHttpClient client;
    private final ContextProvider contextProvider;
    private final String customDomain;
    private EmulatedServiceSettings emulatorSettings;
    private final Executor executor;
    private final String projectId;
    private final String region;
    /* access modifiers changed from: private */
    public final Serializer serializer;
    private String urlFormat = "https://%1$s-%2$s.cloudfunctions.net/%3$s";

    @AssistedInject
    FirebaseFunctions(Context context, @Named("projectId") String str, @Assisted String str2, ContextProvider contextProvider2, Executor executor2, Executor executor3) {
        boolean z;
        this.executor = executor2;
        this.client = new OkHttpClient();
        this.serializer = new Serializer();
        this.contextProvider = (ContextProvider) Preconditions.checkNotNull(contextProvider2);
        this.projectId = (String) Preconditions.checkNotNull(str);
        try {
            new URL(str2);
            z = false;
        } catch (MalformedURLException unused) {
            z = true;
        }
        if (z) {
            this.region = str2;
            this.customDomain = null;
        } else {
            this.region = "us-central1";
            this.customDomain = str2;
        }
        maybeInstallProviders(context, executor3);
    }

    private static void maybeInstallProviders(Context context, Executor executor2) {
        synchronized (providerInstalled) {
            if (!providerInstallStarted) {
                providerInstallStarted = true;
                executor2.execute(new FirebaseFunctions$$ExternalSyntheticLambda4(context));
            }
        }
    }

    public static FirebaseFunctions getInstance(FirebaseApp firebaseApp, String str) {
        Preconditions.checkNotNull(firebaseApp, "You must call FirebaseApp.initializeApp first.");
        Preconditions.checkNotNull(str);
        FunctionsMultiResourceComponent functionsMultiResourceComponent = (FunctionsMultiResourceComponent) firebaseApp.get(FunctionsMultiResourceComponent.class);
        Preconditions.checkNotNull(functionsMultiResourceComponent, "Functions component does not exist.");
        return functionsMultiResourceComponent.get(str);
    }

    public static FirebaseFunctions getInstance(FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, "us-central1");
    }

    public static FirebaseFunctions getInstance(String str) {
        return getInstance(FirebaseApp.getInstance(), str);
    }

    public static FirebaseFunctions getInstance() {
        return getInstance(FirebaseApp.getInstance(), "us-central1");
    }

    public HttpsCallableReference getHttpsCallable(String str) {
        return new HttpsCallableReference(this, str, new HttpsCallOptions());
    }

    public HttpsCallableReference getHttpsCallableFromUrl(URL url) {
        return new HttpsCallableReference(this, url, new HttpsCallOptions());
    }

    public HttpsCallableReference getHttpsCallable(String str, HttpsCallableOptions httpsCallableOptions) {
        return new HttpsCallableReference(this, str, new HttpsCallOptions(httpsCallableOptions));
    }

    public HttpsCallableReference getHttpsCallableFromUrl(URL url, HttpsCallableOptions httpsCallableOptions) {
        return new HttpsCallableReference(this, url, new HttpsCallOptions(httpsCallableOptions));
    }

    /* access modifiers changed from: package-private */
    public URL getURL(String str) {
        EmulatedServiceSettings emulatedServiceSettings = this.emulatorSettings;
        if (emulatedServiceSettings != null) {
            this.urlFormat = "http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/%2$s/%1$s/%3$s";
        }
        String format = String.format(this.urlFormat, new Object[]{this.region, this.projectId, str});
        if (this.customDomain != null && emulatedServiceSettings == null) {
            format = this.customDomain + "/" + str;
        }
        try {
            return new URL(format);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void useFunctionsEmulator(String str) {
        Preconditions.checkNotNull(str, "origin cannot be null");
        this.urlFormat = str + "/%2$s/%1$s/%3$s";
    }

    public void useEmulator(String str, int i) {
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
    }

    /* access modifiers changed from: package-private */
    public Task<HttpsCallableResult> call(String str, Object obj, HttpsCallOptions httpsCallOptions) {
        return providerInstalled.getTask().continueWithTask(this.executor, new FirebaseFunctions$$ExternalSyntheticLambda0(this, httpsCallOptions)).continueWithTask(this.executor, new FirebaseFunctions$$ExternalSyntheticLambda1(this, str, obj, httpsCallOptions));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$call$1$com-google-firebase-functions-FirebaseFunctions  reason: not valid java name */
    public /* synthetic */ Task m772lambda$call$1$comgooglefirebasefunctionsFirebaseFunctions(HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        return this.contextProvider.getContext(httpsCallOptions.getLimitedUseAppCheckTokens());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$call$2$com-google-firebase-functions-FirebaseFunctions  reason: not valid java name */
    public /* synthetic */ Task m773lambda$call$2$comgooglefirebasefunctionsFirebaseFunctions(String str, Object obj, HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return call(getURL(str), obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    /* access modifiers changed from: package-private */
    public Task<HttpsCallableResult> call(URL url, Object obj, HttpsCallOptions httpsCallOptions) {
        return providerInstalled.getTask().continueWithTask(this.executor, new FirebaseFunctions$$ExternalSyntheticLambda2(this, httpsCallOptions)).continueWithTask(this.executor, new FirebaseFunctions$$ExternalSyntheticLambda3(this, url, obj, httpsCallOptions));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$call$3$com-google-firebase-functions-FirebaseFunctions  reason: not valid java name */
    public /* synthetic */ Task m774lambda$call$3$comgooglefirebasefunctionsFirebaseFunctions(HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        return this.contextProvider.getContext(httpsCallOptions.getLimitedUseAppCheckTokens());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$call$4$com-google-firebase-functions-FirebaseFunctions  reason: not valid java name */
    public /* synthetic */ Task m775lambda$call$4$comgooglefirebasefunctionsFirebaseFunctions(URL url, Object obj, HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return call(url, obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    private Task<HttpsCallableResult> call(URL url, Object obj, HttpsCallableContext httpsCallableContext, HttpsCallOptions httpsCallOptions) {
        Preconditions.checkNotNull(url, "url cannot be null");
        HashMap hashMap = new HashMap();
        hashMap.put("data", this.serializer.encode(obj));
        Request.Builder post = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), new JSONObject(hashMap).toString()));
        if (httpsCallableContext.getAuthToken() != null) {
            post = post.header(HttpHeaders.AUTHORIZATION, "Bearer " + httpsCallableContext.getAuthToken());
        }
        if (httpsCallableContext.getInstanceIdToken() != null) {
            post = post.header("Firebase-Instance-ID-Token", httpsCallableContext.getInstanceIdToken());
        }
        if (httpsCallableContext.getAppCheckToken() != null) {
            post = post.header("X-Firebase-AppCheck", httpsCallableContext.getAppCheckToken());
        }
        Call newCall = httpsCallOptions.apply(this.client).newCall(post.build());
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (iOException instanceof InterruptedIOException) {
                    taskCompletionSource.setException(new FirebaseFunctionsException(FirebaseFunctionsException.Code.DEADLINE_EXCEEDED.name(), FirebaseFunctionsException.Code.DEADLINE_EXCEEDED, (Object) null, iOException));
                    return;
                }
                taskCompletionSource.setException(new FirebaseFunctionsException(FirebaseFunctionsException.Code.INTERNAL.name(), FirebaseFunctionsException.Code.INTERNAL, (Object) null, iOException));
            }

            public void onResponse(Call call, Response response) throws IOException {
                FirebaseFunctionsException.Code fromHttpStatus = FirebaseFunctionsException.Code.fromHttpStatus(response.code());
                String string = response.body().string();
                FirebaseFunctionsException fromResponse = FirebaseFunctionsException.fromResponse(fromHttpStatus, string, FirebaseFunctions.this.serializer);
                if (fromResponse != null) {
                    taskCompletionSource.setException(fromResponse);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Object opt = jSONObject.opt("data");
                    if (opt == null) {
                        opt = jSONObject.opt("result");
                    }
                    if (opt == null) {
                        taskCompletionSource.setException(new FirebaseFunctionsException("Response is missing data field.", FirebaseFunctionsException.Code.INTERNAL, (Object) null));
                        return;
                    }
                    taskCompletionSource.setResult(new HttpsCallableResult(FirebaseFunctions.this.serializer.decode(opt)));
                } catch (JSONException e) {
                    taskCompletionSource.setException(new FirebaseFunctionsException("Response is not valid JSON object.", FirebaseFunctionsException.Code.INTERNAL, (Object) null, e));
                }
            }
        });
        return taskCompletionSource.getTask();
    }
}
