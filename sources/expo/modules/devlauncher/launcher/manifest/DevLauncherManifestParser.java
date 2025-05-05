package expo.modules.devlauncher.launcher.manifest;

import android.net.Uri;
import com.amplitude.api.DeviceInfo;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.OkHttpClient;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\nH@¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\u000fH@¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0010\u001a\u00020\u0011H@¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/modules/devlauncher/launcher/manifest/DevLauncherManifestParser;", "", "httpClient", "Lokhttp3/OkHttpClient;", "url", "Landroid/net/Uri;", "installationID", "", "(Lokhttp3/OkHttpClient;Landroid/net/Uri;Ljava/lang/String;)V", "downloadManifest", "Ljava/io/Reader;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHeaders", "Lokhttp3/Headers;", "isManifestUrl", "", "parseManifest", "Lexpo/modules/manifests/core/Manifest;", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherManifestParser.kt */
public final class DevLauncherManifestParser {
    private final OkHttpClient httpClient;
    private final String installationID;
    private final Uri url;

    public DevLauncherManifestParser(OkHttpClient okHttpClient, Uri uri, String str) {
        Intrinsics.checkNotNullParameter(okHttpClient, "httpClient");
        Intrinsics.checkNotNullParameter(uri, "url");
        this.httpClient = okHttpClient;
        this.url = uri;
        this.installationID = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object isManifestUrl(kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$isManifestUrl$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$isManifestUrl$1 r0 = (expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$isManifestUrl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$isManifestUrl$1 r0 = new expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$isManifestUrl$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r1 = r0.L$1
            okhttp3.OkHttpClient r1 = (okhttp3.OkHttpClient) r1
            java.lang.Object r0 = r0.L$0
            okhttp3.Request r0 = (okhttp3.Request) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x007e
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            android.net.Uri r7 = r6.url
            java.lang.String r2 = "HEAD"
            okhttp3.Headers r4 = r6.getHeaders()
            okhttp3.Request r7 = expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt.fetch(r7, r2, r4)
            okhttp3.OkHttpClient r2 = r6.httpClient
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r4.<init>(r5, r3)
            r4.initCancellability()
            r5 = r4
            kotlinx.coroutines.CancellableContinuation r5 = (kotlinx.coroutines.CancellableContinuation) r5
            okhttp3.Call r7 = r2.newCall(r7)
            expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt$await$2$1 r2 = new expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt$await$2$1
            r2.<init>(r5)
            okhttp3.Callback r2 = (okhttp3.Callback) r2
            com.google.firebase.perf.network.FirebasePerfOkHttpClient.enqueue(r7, r2)
            java.lang.Object r7 = r4.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r2) goto L_0x007b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x007b:
            if (r7 != r1) goto L_0x007e
            return r1
        L_0x007e:
            okhttp3.Response r7 = (okhttp3.Response) r7
            java.lang.String r0 = "Content-Type"
            r1 = 0
            r2 = 2
            java.lang.String r0 = okhttp3.Response.header$default(r7, r0, r1, r2, r1)
            boolean r4 = r7.isSuccessful()
            if (r4 == 0) goto L_0x00af
            java.lang.String r4 = "Exponent-Server"
            java.lang.String r7 = r7.header(r4, r1)
            if (r7 != 0) goto L_0x00af
            r7 = 0
            if (r0 == 0) goto L_0x00ae
            java.lang.String r4 = "text/html"
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r0, r4, r7, r2, r1)
            if (r4 != 0) goto L_0x00ae
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r4 = "/javascript"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r4, (boolean) r7, (int) r2, (java.lang.Object) r1)
            if (r0 != 0) goto L_0x00ae
            goto L_0x00af
        L_0x00ae:
            r3 = r7
        L_0x00af:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser.isManifestUrl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object downloadManifest(kotlin.coroutines.Continuation<? super java.io.Reader> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$downloadManifest$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$downloadManifest$1 r0 = (expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$downloadManifest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$downloadManifest$1 r0 = new expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$downloadManifest$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r1 = r0.L$1
            okhttp3.OkHttpClient r1 = (okhttp3.OkHttpClient) r1
            java.lang.Object r0 = r0.L$0
            okhttp3.Request r0 = (okhttp3.Request) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x007e
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            android.net.Uri r7 = r6.url
            java.lang.String r2 = "GET"
            okhttp3.Headers r4 = r6.getHeaders()
            okhttp3.Request r7 = expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt.fetch(r7, r2, r4)
            okhttp3.OkHttpClient r2 = r6.httpClient
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r4.<init>(r5, r3)
            r4.initCancellability()
            r3 = r4
            kotlinx.coroutines.CancellableContinuation r3 = (kotlinx.coroutines.CancellableContinuation) r3
            okhttp3.Call r7 = r2.newCall(r7)
            expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt$await$2$1 r2 = new expo.modules.devlauncher.helpers.DevLauncherOkHttpExtensionsKt$await$2$1
            r2.<init>(r3)
            okhttp3.Callback r2 = (okhttp3.Callback) r2
            com.google.firebase.perf.network.FirebasePerfOkHttpClient.enqueue(r7, r2)
            java.lang.Object r7 = r4.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r2) goto L_0x007b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x007b:
            if (r7 != r1) goto L_0x007e
            return r1
        L_0x007e:
            okhttp3.Response r7 = (okhttp3.Response) r7
            boolean r0 = r7.isSuccessful()
            if (r0 == 0) goto L_0x0092
            okhttp3.ResponseBody r7 = r7.m2031deprecated_body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.io.Reader r7 = r7.charStream()
            return r7
        L_0x0092:
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r0 = "Failed to open app.\n\nIf you are trying to load the app from a development server, check your network connectivity and make sure you can access the server from your device.\n\nIf you are trying to open a published project, install a compatible version of expo-updates and follow all setup and integration steps."
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser.downloadManifest(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseManifest(kotlin.coroutines.Continuation<? super expo.modules.manifests.core.Manifest> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$parseManifest$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$parseManifest$1 r0 = (expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$parseManifest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$parseManifest$1 r0 = new expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser$parseManifest$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x003e
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.label = r3
            java.lang.Object r5 = r4.downloadManifest(r0)
            if (r5 != r1) goto L_0x003e
            return r1
        L_0x003e:
            java.io.Closeable r5 = (java.io.Closeable) r5
            r0 = r5
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ all -> 0x0057 }
            expo.modules.manifests.core.Manifest$Companion r1 = expo.modules.manifests.core.Manifest.Companion     // Catch:{ all -> 0x0057 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ all -> 0x0057 }
            java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch:{ all -> 0x0057 }
            r2.<init>(r0)     // Catch:{ all -> 0x0057 }
            expo.modules.manifests.core.Manifest r0 = r1.fromManifestJson(r2)     // Catch:{ all -> 0x0057 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r5, r1)
            return r0
        L_0x0057:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.devlauncher.launcher.manifest.DevLauncherManifestParser.parseManifest(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Headers getHeaders() {
        Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("expo-platform", DeviceInfo.OS_NAME), TuplesKt.to("accept", "application/expo+json,application/json"));
        String str = this.installationID;
        if (str != null) {
            mutableMapOf.put("expo-dev-client-id", str);
        }
        return Headers.Companion.of((Map<String, String>) mutableMapOf);
    }
}
