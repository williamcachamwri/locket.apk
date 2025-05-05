package coil.fetch;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.ImageSource;
import coil.decode.ImageSources;
import coil.disk.DiskCache;
import coil.fetch.Fetcher;
import coil.network.CacheStrategy;
import coil.request.Options;
import coil.util.Utils;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.FileSystem;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 02\u00020\u0001:\u000201B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ!\u0010\u001d\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0001¢\u0006\u0002\b J\u0018\u0010!\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0016H\u0002J\b\u0010#\u001a\u00020\u0018H\u0002J\n\u0010$\u001a\u0004\u0018\u00010%H\u0002J.\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010%2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00162\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u000e\u0010*\u001a\u0004\u0018\u00010)*\u00020%H\u0002J\f\u0010+\u001a\u00020,*\u00020\u0016H\u0002J\f\u0010-\u001a\u00020.*\u00020%H\u0002J\f\u0010-\u001a\u00020.*\u00020/H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00038BX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Lcoil/fetch/HttpUriFetcher;", "Lcoil/fetch/Fetcher;", "url", "", "options", "Lcoil/request/Options;", "callFactory", "Lkotlin/Lazy;", "Lokhttp3/Call$Factory;", "diskCache", "Lcoil/disk/DiskCache;", "respectCacheHeaders", "", "(Ljava/lang/String;Lcoil/request/Options;Lkotlin/Lazy;Lkotlin/Lazy;Z)V", "diskCacheKey", "getDiskCacheKey", "()Ljava/lang/String;", "fileSystem", "Lokio/FileSystem;", "getFileSystem", "()Lokio/FileSystem;", "executeNetworkRequest", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch", "Lcoil/fetch/FetchResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMimeType", "contentType", "Lokhttp3/MediaType;", "getMimeType$coil_base_release", "isCacheable", "response", "newRequest", "readFromDiskCache", "Lcoil/disk/DiskCache$Snapshot;", "writeToDiskCache", "snapshot", "cacheResponse", "Lcoil/network/CacheResponse;", "toCacheResponse", "toDataSource", "Lcoil/decode/DataSource;", "toImageSource", "Lcoil/decode/ImageSource;", "Lokhttp3/ResponseBody;", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HttpUriFetcher.kt */
public final class HttpUriFetcher implements Fetcher {
    private static final CacheControl CACHE_CONTROL_FORCE_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().noStore().build();
    private static final CacheControl CACHE_CONTROL_NO_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().onlyIfCached().build();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MIME_TYPE_TEXT_PLAIN = "text/plain";
    private final Lazy<Call.Factory> callFactory;
    private final Lazy<DiskCache> diskCache;
    private final Options options;
    private final boolean respectCacheHeaders;
    private final String url;

    public HttpUriFetcher(String str, Options options2, Lazy<? extends Call.Factory> lazy, Lazy<? extends DiskCache> lazy2, boolean z) {
        this.url = str;
        this.options = options2;
        this.callFactory = lazy;
        this.diskCache = lazy2;
        this.respectCacheHeaders = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0128 A[Catch:{ Exception -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0144 A[Catch:{ Exception -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object fetch(kotlin.coroutines.Continuation<? super coil.fetch.FetchResult> r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof coil.fetch.HttpUriFetcher$fetch$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            coil.fetch.HttpUriFetcher$fetch$1 r0 = (coil.fetch.HttpUriFetcher$fetch$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            coil.fetch.HttpUriFetcher$fetch$1 r0 = new coil.fetch.HttpUriFetcher$fetch$1
            r0.<init>(r12, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x005f
            if (r2 == r6) goto L_0x0048
            if (r2 != r5) goto L_0x0040
            java.lang.Object r1 = r0.L$2
            okhttp3.Response r1 = (okhttp3.Response) r1
            java.lang.Object r2 = r0.L$1
            coil.disk.DiskCache$Snapshot r2 = (coil.disk.DiskCache.Snapshot) r2
            java.lang.Object r0 = r0.L$0
            coil.fetch.HttpUriFetcher r0 = (coil.fetch.HttpUriFetcher) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x003d }
            goto L_0x0180
        L_0x003d:
            r13 = move-exception
            goto L_0x01a1
        L_0x0040:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0048:
            java.lang.Object r2 = r0.L$2
            coil.network.CacheStrategy r2 = (coil.network.CacheStrategy) r2
            java.lang.Object r6 = r0.L$1
            coil.disk.DiskCache$Snapshot r6 = (coil.disk.DiskCache.Snapshot) r6
            java.lang.Object r8 = r0.L$0
            coil.fetch.HttpUriFetcher r8 = (coil.fetch.HttpUriFetcher) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x005c }
            r11 = r6
            r6 = r2
            r2 = r11
            goto L_0x0114
        L_0x005c:
            r13 = move-exception
            goto L_0x01ad
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r13)
            coil.disk.DiskCache$Snapshot r13 = r12.readFromDiskCache()
            if (r13 == 0) goto L_0x00ec
            okio.FileSystem r2 = r12.getFileSystem()     // Catch:{ Exception -> 0x01aa }
            okio.Path r8 = r13.getMetadata()     // Catch:{ Exception -> 0x01aa }
            okio.FileMetadata r2 = r2.metadata(r8)     // Catch:{ Exception -> 0x01aa }
            java.lang.Long r2 = r2.getSize()     // Catch:{ Exception -> 0x01aa }
            if (r2 != 0) goto L_0x007b
            goto L_0x0095
        L_0x007b:
            long r8 = r2.longValue()     // Catch:{ Exception -> 0x01aa }
            int r2 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0095
            coil.fetch.SourceResult r0 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x01aa }
            coil.decode.ImageSource r1 = r12.toImageSource((coil.disk.DiskCache.Snapshot) r13)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r2 = r12.url     // Catch:{ Exception -> 0x01aa }
            java.lang.String r2 = r12.getMimeType$coil_base_release(r2, r7)     // Catch:{ Exception -> 0x01aa }
            coil.decode.DataSource r3 = coil.decode.DataSource.DISK     // Catch:{ Exception -> 0x01aa }
            r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x01aa }
            return r0
        L_0x0095:
            boolean r2 = r12.respectCacheHeaders     // Catch:{ Exception -> 0x01aa }
            if (r2 == 0) goto L_0x00d0
            coil.network.CacheStrategy$Factory r2 = new coil.network.CacheStrategy$Factory     // Catch:{ Exception -> 0x01aa }
            okhttp3.Request r8 = r12.newRequest()     // Catch:{ Exception -> 0x01aa }
            coil.network.CacheResponse r9 = r12.toCacheResponse(r13)     // Catch:{ Exception -> 0x01aa }
            r2.<init>(r8, r9)     // Catch:{ Exception -> 0x01aa }
            coil.network.CacheStrategy r2 = r2.compute()     // Catch:{ Exception -> 0x01aa }
            okhttp3.Request r8 = r2.getNetworkRequest()     // Catch:{ Exception -> 0x01aa }
            if (r8 != 0) goto L_0x00f9
            coil.network.CacheResponse r8 = r2.getCacheResponse()     // Catch:{ Exception -> 0x01aa }
            if (r8 == 0) goto L_0x00f9
            coil.fetch.SourceResult r0 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x01aa }
            coil.decode.ImageSource r1 = r12.toImageSource((coil.disk.DiskCache.Snapshot) r13)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r3 = r12.url     // Catch:{ Exception -> 0x01aa }
            coil.network.CacheResponse r2 = r2.getCacheResponse()     // Catch:{ Exception -> 0x01aa }
            okhttp3.MediaType r2 = r2.getContentType()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r2 = r12.getMimeType$coil_base_release(r3, r2)     // Catch:{ Exception -> 0x01aa }
            coil.decode.DataSource r3 = coil.decode.DataSource.DISK     // Catch:{ Exception -> 0x01aa }
            r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x01aa }
            return r0
        L_0x00d0:
            coil.fetch.SourceResult r0 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x01aa }
            coil.decode.ImageSource r1 = r12.toImageSource((coil.disk.DiskCache.Snapshot) r13)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r2 = r12.url     // Catch:{ Exception -> 0x01aa }
            coil.network.CacheResponse r3 = r12.toCacheResponse(r13)     // Catch:{ Exception -> 0x01aa }
            if (r3 == 0) goto L_0x00e2
            okhttp3.MediaType r7 = r3.getContentType()     // Catch:{ Exception -> 0x01aa }
        L_0x00e2:
            java.lang.String r2 = r12.getMimeType$coil_base_release(r2, r7)     // Catch:{ Exception -> 0x01aa }
            coil.decode.DataSource r3 = coil.decode.DataSource.DISK     // Catch:{ Exception -> 0x01aa }
            r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x01aa }
            return r0
        L_0x00ec:
            coil.network.CacheStrategy$Factory r2 = new coil.network.CacheStrategy$Factory     // Catch:{ Exception -> 0x01aa }
            okhttp3.Request r8 = r12.newRequest()     // Catch:{ Exception -> 0x01aa }
            r2.<init>(r8, r7)     // Catch:{ Exception -> 0x01aa }
            coil.network.CacheStrategy r2 = r2.compute()     // Catch:{ Exception -> 0x01aa }
        L_0x00f9:
            okhttp3.Request r8 = r2.getNetworkRequest()     // Catch:{ Exception -> 0x01aa }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch:{ Exception -> 0x01aa }
            r0.L$0 = r12     // Catch:{ Exception -> 0x01aa }
            r0.L$1 = r13     // Catch:{ Exception -> 0x01aa }
            r0.L$2 = r2     // Catch:{ Exception -> 0x01aa }
            r0.label = r6     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r6 = r12.executeNetworkRequest(r8, r0)     // Catch:{ Exception -> 0x01aa }
            if (r6 != r1) goto L_0x010f
            return r1
        L_0x010f:
            r8 = r12
            r11 = r2
            r2 = r13
            r13 = r6
            r6 = r11
        L_0x0114:
            okhttp3.Response r13 = (okhttp3.Response) r13     // Catch:{ Exception -> 0x01a7 }
            okhttp3.ResponseBody r9 = coil.util.Utils.requireBody(r13)     // Catch:{ Exception -> 0x01a7 }
            okhttp3.Request r10 = r6.getNetworkRequest()     // Catch:{ Exception -> 0x019e }
            coil.network.CacheResponse r6 = r6.getCacheResponse()     // Catch:{ Exception -> 0x019e }
            coil.disk.DiskCache$Snapshot r2 = r8.writeToDiskCache(r2, r10, r13, r6)     // Catch:{ Exception -> 0x019e }
            if (r2 == 0) goto L_0x0144
            coil.fetch.SourceResult r0 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x019e }
            coil.decode.ImageSource r1 = r8.toImageSource((coil.disk.DiskCache.Snapshot) r2)     // Catch:{ Exception -> 0x019e }
            java.lang.String r3 = r8.url     // Catch:{ Exception -> 0x019e }
            coil.network.CacheResponse r4 = r8.toCacheResponse(r2)     // Catch:{ Exception -> 0x019e }
            if (r4 == 0) goto L_0x013a
            okhttp3.MediaType r7 = r4.getContentType()     // Catch:{ Exception -> 0x019e }
        L_0x013a:
            java.lang.String r3 = r8.getMimeType$coil_base_release(r3, r7)     // Catch:{ Exception -> 0x019e }
            coil.decode.DataSource r4 = coil.decode.DataSource.NETWORK     // Catch:{ Exception -> 0x019e }
            r0.<init>(r1, r3, r4)     // Catch:{ Exception -> 0x019e }
            return r0
        L_0x0144:
            long r6 = r9.contentLength()     // Catch:{ Exception -> 0x019e }
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0164
            coil.fetch.SourceResult r0 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x019e }
            coil.decode.ImageSource r1 = r8.toImageSource((okhttp3.ResponseBody) r9)     // Catch:{ Exception -> 0x019e }
            java.lang.String r3 = r8.url     // Catch:{ Exception -> 0x019e }
            okhttp3.MediaType r4 = r9.contentType()     // Catch:{ Exception -> 0x019e }
            java.lang.String r3 = r8.getMimeType$coil_base_release(r3, r4)     // Catch:{ Exception -> 0x019e }
            coil.decode.DataSource r4 = r8.toDataSource(r13)     // Catch:{ Exception -> 0x019e }
            r0.<init>(r1, r3, r4)     // Catch:{ Exception -> 0x019e }
            return r0
        L_0x0164:
            r3 = r13
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ Exception -> 0x019e }
            coil.util.Utils.closeQuietly(r3)     // Catch:{ Exception -> 0x019e }
            okhttp3.Request r3 = r8.newRequest()     // Catch:{ Exception -> 0x019e }
            r0.L$0 = r8     // Catch:{ Exception -> 0x019e }
            r0.L$1 = r2     // Catch:{ Exception -> 0x019e }
            r0.L$2 = r13     // Catch:{ Exception -> 0x019e }
            r0.label = r5     // Catch:{ Exception -> 0x019e }
            java.lang.Object r0 = r8.executeNetworkRequest(r3, r0)     // Catch:{ Exception -> 0x019e }
            if (r0 != r1) goto L_0x017d
            return r1
        L_0x017d:
            r1 = r13
            r13 = r0
            r0 = r8
        L_0x0180:
            okhttp3.Response r13 = (okhttp3.Response) r13     // Catch:{ Exception -> 0x003d }
            okhttp3.ResponseBody r1 = coil.util.Utils.requireBody(r13)     // Catch:{ Exception -> 0x019e }
            coil.fetch.SourceResult r3 = new coil.fetch.SourceResult     // Catch:{ Exception -> 0x019e }
            coil.decode.ImageSource r4 = r0.toImageSource((okhttp3.ResponseBody) r1)     // Catch:{ Exception -> 0x019e }
            java.lang.String r5 = r0.url     // Catch:{ Exception -> 0x019e }
            okhttp3.MediaType r1 = r1.contentType()     // Catch:{ Exception -> 0x019e }
            java.lang.String r1 = r0.getMimeType$coil_base_release(r5, r1)     // Catch:{ Exception -> 0x019e }
            coil.decode.DataSource r0 = r0.toDataSource(r13)     // Catch:{ Exception -> 0x019e }
            r3.<init>(r4, r1, r0)     // Catch:{ Exception -> 0x019e }
            return r3
        L_0x019e:
            r0 = move-exception
            r1 = r13
            r13 = r0
        L_0x01a1:
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ Exception -> 0x01a7 }
            coil.util.Utils.closeQuietly(r1)     // Catch:{ Exception -> 0x01a7 }
            throw r13     // Catch:{ Exception -> 0x01a7 }
        L_0x01a7:
            r13 = move-exception
            r6 = r2
            goto L_0x01ad
        L_0x01aa:
            r0 = move-exception
            r6 = r13
            r13 = r0
        L_0x01ad:
            if (r6 == 0) goto L_0x01b4
            java.io.Closeable r6 = (java.io.Closeable) r6
            coil.util.Utils.closeQuietly(r6)
        L_0x01b4:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.fetch(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final DiskCache.Snapshot readFromDiskCache() {
        DiskCache value;
        if (!this.options.getDiskCachePolicy().getReadEnabled() || (value = this.diskCache.getValue()) == null) {
            return null;
        }
        return value.openSnapshot(getDiskCacheKey());
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089 A[Catch:{ Exception -> 0x011d, all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008e A[Catch:{ Exception -> 0x011d, all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7 A[Catch:{ Exception -> 0x011d, all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x010c A[Catch:{ Exception -> 0x011d, all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0119 A[SYNTHETIC, Splitter:B:75:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x011a A[Catch:{ Exception -> 0x011d, all -> 0x011b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final coil.disk.DiskCache.Snapshot writeToDiskCache(coil.disk.DiskCache.Snapshot r6, okhttp3.Request r7, okhttp3.Response r8, coil.network.CacheResponse r9) {
        /*
            r5 = this;
            boolean r7 = r5.isCacheable(r7, r8)
            r0 = 0
            if (r7 != 0) goto L_0x000f
            if (r6 == 0) goto L_0x000e
            java.io.Closeable r6 = (java.io.Closeable) r6
            coil.util.Utils.closeQuietly(r6)
        L_0x000e:
            return r0
        L_0x000f:
            if (r6 == 0) goto L_0x0016
            coil.disk.DiskCache$Editor r6 = r6.closeAndOpenEditor()
            goto L_0x002a
        L_0x0016:
            kotlin.Lazy<coil.disk.DiskCache> r6 = r5.diskCache
            java.lang.Object r6 = r6.getValue()
            coil.disk.DiskCache r6 = (coil.disk.DiskCache) r6
            if (r6 == 0) goto L_0x0029
            java.lang.String r7 = r5.getDiskCacheKey()
            coil.disk.DiskCache$Editor r6 = r6.openEditor(r7)
            goto L_0x002a
        L_0x0029:
            r6 = r0
        L_0x002a:
            if (r6 != 0) goto L_0x002d
            return r0
        L_0x002d:
            int r7 = r8.code()     // Catch:{ Exception -> 0x011d }
            r1 = 304(0x130, float:4.26E-43)
            r2 = 0
            if (r7 != r1) goto L_0x008f
            if (r9 == 0) goto L_0x008f
            okhttp3.Response$Builder r7 = r8.newBuilder()     // Catch:{ Exception -> 0x011d }
            coil.network.CacheStrategy$Companion r1 = coil.network.CacheStrategy.Companion     // Catch:{ Exception -> 0x011d }
            okhttp3.Headers r9 = r9.getResponseHeaders()     // Catch:{ Exception -> 0x011d }
            okhttp3.Headers r3 = r8.headers()     // Catch:{ Exception -> 0x011d }
            okhttp3.Headers r9 = r1.combineHeaders(r9, r3)     // Catch:{ Exception -> 0x011d }
            okhttp3.Response$Builder r7 = r7.headers(r9)     // Catch:{ Exception -> 0x011d }
            okhttp3.Response r7 = r7.build()     // Catch:{ Exception -> 0x011d }
            okio.FileSystem r9 = r5.getFileSystem()     // Catch:{ Exception -> 0x011d }
            okio.Path r1 = r6.getMetadata()     // Catch:{ Exception -> 0x011d }
            okio.Sink r9 = r9.sink(r1, r2)     // Catch:{ Exception -> 0x011d }
            okio.BufferedSink r9 = okio.Okio.buffer((okio.Sink) r9)     // Catch:{ Exception -> 0x011d }
            java.io.Closeable r9 = (java.io.Closeable) r9     // Catch:{ Exception -> 0x011d }
            r1 = r9
            okio.BufferedSink r1 = (okio.BufferedSink) r1     // Catch:{ all -> 0x0079 }
            coil.network.CacheResponse r2 = new coil.network.CacheResponse     // Catch:{ all -> 0x0079 }
            r2.<init>((okhttp3.Response) r7)     // Catch:{ all -> 0x0079 }
            r2.writeTo(r1)     // Catch:{ all -> 0x0079 }
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0079 }
            if (r9 == 0) goto L_0x0087
            r9.close()     // Catch:{ all -> 0x0077 }
            goto L_0x0087
        L_0x0077:
            r0 = move-exception
            goto L_0x0087
        L_0x0079:
            r7 = move-exception
            if (r9 == 0) goto L_0x0084
            r9.close()     // Catch:{ all -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r9 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r7, r9)     // Catch:{ Exception -> 0x011d }
        L_0x0084:
            r4 = r0
            r0 = r7
            r7 = r4
        L_0x0087:
            if (r0 != 0) goto L_0x008e
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ Exception -> 0x011d }
            goto L_0x010f
        L_0x008e:
            throw r0     // Catch:{ Exception -> 0x011d }
        L_0x008f:
            okio.FileSystem r7 = r5.getFileSystem()     // Catch:{ Exception -> 0x011d }
            okio.Path r9 = r6.getMetadata()     // Catch:{ Exception -> 0x011d }
            okio.Sink r7 = r7.sink(r9, r2)     // Catch:{ Exception -> 0x011d }
            okio.BufferedSink r7 = okio.Okio.buffer((okio.Sink) r7)     // Catch:{ Exception -> 0x011d }
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ Exception -> 0x011d }
            r9 = r7
            okio.BufferedSink r9 = (okio.BufferedSink) r9     // Catch:{ all -> 0x00b8 }
            coil.network.CacheResponse r1 = new coil.network.CacheResponse     // Catch:{ all -> 0x00b8 }
            r1.<init>((okhttp3.Response) r8)     // Catch:{ all -> 0x00b8 }
            r1.writeTo(r9)     // Catch:{ all -> 0x00b8 }
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00b8 }
            if (r7 == 0) goto L_0x00b6
            r7.close()     // Catch:{ all -> 0x00b4 }
            goto L_0x00b6
        L_0x00b4:
            r7 = move-exception
            goto L_0x00c5
        L_0x00b6:
            r7 = r0
            goto L_0x00c5
        L_0x00b8:
            r9 = move-exception
            if (r7 == 0) goto L_0x00c3
            r7.close()     // Catch:{ all -> 0x00bf }
            goto L_0x00c3
        L_0x00bf:
            r7 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r9, r7)     // Catch:{ Exception -> 0x011d }
        L_0x00c3:
            r7 = r9
            r9 = r0
        L_0x00c5:
            if (r7 != 0) goto L_0x011a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ Exception -> 0x011d }
            okio.FileSystem r7 = r5.getFileSystem()     // Catch:{ Exception -> 0x011d }
            okio.Path r9 = r6.getData()     // Catch:{ Exception -> 0x011d }
            okio.Sink r7 = r7.sink(r9, r2)     // Catch:{ Exception -> 0x011d }
            okio.BufferedSink r7 = okio.Okio.buffer((okio.Sink) r7)     // Catch:{ Exception -> 0x011d }
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ Exception -> 0x011d }
            r9 = r7
            okio.BufferedSink r9 = (okio.BufferedSink) r9     // Catch:{ all -> 0x00fc }
            okhttp3.ResponseBody r1 = r8.body()     // Catch:{ all -> 0x00fc }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x00fc }
            okio.BufferedSource r1 = r1.source()     // Catch:{ all -> 0x00fc }
            okio.Sink r9 = (okio.Sink) r9     // Catch:{ all -> 0x00fc }
            long r1 = r1.readAll(r9)     // Catch:{ all -> 0x00fc }
            java.lang.Long r9 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x00fc }
            if (r7 == 0) goto L_0x010a
            r7.close()     // Catch:{ all -> 0x00fa }
            goto L_0x010a
        L_0x00fa:
            r0 = move-exception
            goto L_0x010a
        L_0x00fc:
            r9 = move-exception
            if (r7 == 0) goto L_0x0107
            r7.close()     // Catch:{ all -> 0x0103 }
            goto L_0x0107
        L_0x0103:
            r7 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r9, r7)     // Catch:{ Exception -> 0x011d }
        L_0x0107:
            r4 = r0
            r0 = r9
            r9 = r4
        L_0x010a:
            if (r0 != 0) goto L_0x0119
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ Exception -> 0x011d }
        L_0x010f:
            coil.disk.DiskCache$Snapshot r6 = r6.commitAndOpenSnapshot()     // Catch:{ Exception -> 0x011d }
            java.io.Closeable r8 = (java.io.Closeable) r8
            coil.util.Utils.closeQuietly(r8)
            return r6
        L_0x0119:
            throw r0     // Catch:{ Exception -> 0x011d }
        L_0x011a:
            throw r7     // Catch:{ Exception -> 0x011d }
        L_0x011b:
            r6 = move-exception
            goto L_0x0122
        L_0x011d:
            r7 = move-exception
            coil.util.Utils.abortQuietly(r6)     // Catch:{ all -> 0x011b }
            throw r7     // Catch:{ all -> 0x011b }
        L_0x0122:
            java.io.Closeable r8 = (java.io.Closeable) r8
            coil.util.Utils.closeQuietly(r8)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.writeToDiskCache(coil.disk.DiskCache$Snapshot, okhttp3.Request, okhttp3.Response, coil.network.CacheResponse):coil.disk.DiskCache$Snapshot");
    }

    private final Request newRequest() {
        Request.Builder headers = new Request.Builder().url(this.url).headers(this.options.getHeaders());
        for (Map.Entry next : this.options.getTags().asMap().entrySet()) {
            Object key = next.getKey();
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.lang.Class<kotlin.Any>");
            headers.tag((Class) key, next.getValue());
        }
        boolean readEnabled = this.options.getDiskCachePolicy().getReadEnabled();
        boolean readEnabled2 = this.options.getNetworkCachePolicy().getReadEnabled();
        if (!readEnabled2 && readEnabled) {
            headers.cacheControl(CacheControl.FORCE_CACHE);
        } else if (!readEnabled2 || readEnabled) {
            if (!readEnabled2 && !readEnabled) {
                headers.cacheControl(CACHE_CONTROL_NO_NETWORK_NO_CACHE);
            }
        } else if (this.options.getDiskCachePolicy().getWriteEnabled()) {
            headers.cacheControl(CacheControl.FORCE_NETWORK);
        } else {
            headers.cacheControl(CACHE_CONTROL_FORCE_NETWORK_NO_CACHE);
        }
        return headers.build();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0095 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executeNetworkRequest(okhttp3.Request r5, kotlin.coroutines.Continuation<? super okhttp3.Response> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = (coil.fetch.HttpUriFetcher$executeNetworkRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = new coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0073
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = coil.util.Utils.isMainThread()
            if (r6 == 0) goto L_0x005e
            coil.request.Options r6 = r4.options
            coil.request.CachePolicy r6 = r6.getNetworkCachePolicy()
            boolean r6 = r6.getReadEnabled()
            if (r6 != 0) goto L_0x0058
            kotlin.Lazy<okhttp3.Call$Factory> r6 = r4.callFactory
            java.lang.Object r6 = r6.getValue()
            okhttp3.Call$Factory r6 = (okhttp3.Call.Factory) r6
            okhttp3.Call r5 = r6.newCall(r5)
            okhttp3.Response r5 = com.google.firebase.perf.network.FirebasePerfOkHttpClient.execute(r5)
            goto L_0x0076
        L_0x0058:
            android.os.NetworkOnMainThreadException r5 = new android.os.NetworkOnMainThreadException
            r5.<init>()
            throw r5
        L_0x005e:
            kotlin.Lazy<okhttp3.Call$Factory> r6 = r4.callFactory
            java.lang.Object r6 = r6.getValue()
            okhttp3.Call$Factory r6 = (okhttp3.Call.Factory) r6
            okhttp3.Call r5 = r6.newCall(r5)
            r0.label = r3
            java.lang.Object r6 = coil.util.Calls.await(r5, r0)
            if (r6 != r1) goto L_0x0073
            return r1
        L_0x0073:
            r5 = r6
            okhttp3.Response r5 = (okhttp3.Response) r5
        L_0x0076:
            boolean r6 = r5.isSuccessful()
            if (r6 != 0) goto L_0x0095
            int r6 = r5.code()
            r0 = 304(0x130, float:4.26E-43)
            if (r6 == r0) goto L_0x0095
            okhttp3.ResponseBody r6 = r5.body()
            if (r6 == 0) goto L_0x008f
            java.io.Closeable r6 = (java.io.Closeable) r6
            coil.util.Utils.closeQuietly(r6)
        L_0x008f:
            coil.network.HttpException r6 = new coil.network.HttpException
            r6.<init>(r5)
            throw r6
        L_0x0095:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.executeNetworkRequest(okhttp3.Request, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String getMimeType$coil_base_release(String str, MediaType mediaType) {
        String mimeTypeFromUrl;
        String mediaType2 = mediaType != null ? mediaType.toString() : null;
        if ((mediaType2 == null || StringsKt.startsWith$default(mediaType2, "text/plain", false, 2, (Object) null)) && (mimeTypeFromUrl = Utils.getMimeTypeFromUrl(MimeTypeMap.getSingleton(), str)) != null) {
            return mimeTypeFromUrl;
        }
        if (mediaType2 != null) {
            return StringsKt.substringBefore$default(mediaType2, ';', (String) null, 2, (Object) null);
        }
        return null;
    }

    private final boolean isCacheable(Request request, Response response) {
        return this.options.getDiskCachePolicy().getWriteEnabled() && (!this.respectCacheHeaders || CacheStrategy.Companion.isCacheable(request, response));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0034 A[Catch:{ IOException -> 0x0039 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0038 A[Catch:{ IOException -> 0x0039 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final coil.network.CacheResponse toCacheResponse(coil.disk.DiskCache.Snapshot r4) {
        /*
            r3 = this;
            r0 = 0
            okio.FileSystem r1 = r3.getFileSystem()     // Catch:{ IOException -> 0x0039 }
            okio.Path r4 = r4.getMetadata()     // Catch:{ IOException -> 0x0039 }
            okio.Source r4 = r1.source(r4)     // Catch:{ IOException -> 0x0039 }
            okio.BufferedSource r4 = okio.Okio.buffer((okio.Source) r4)     // Catch:{ IOException -> 0x0039 }
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ IOException -> 0x0039 }
            r1 = r4
            okio.BufferedSource r1 = (okio.BufferedSource) r1     // Catch:{ all -> 0x0025 }
            coil.network.CacheResponse r2 = new coil.network.CacheResponse     // Catch:{ all -> 0x0025 }
            r2.<init>((okio.BufferedSource) r1)     // Catch:{ all -> 0x0025 }
            if (r4 == 0) goto L_0x0023
            r4.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0023
        L_0x0021:
            r4 = move-exception
            goto L_0x0032
        L_0x0023:
            r4 = r0
            goto L_0x0032
        L_0x0025:
            r1 = move-exception
            if (r4 == 0) goto L_0x0030
            r4.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r4 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r1, r4)     // Catch:{ IOException -> 0x0039 }
        L_0x0030:
            r2 = r0
            r4 = r1
        L_0x0032:
            if (r4 != 0) goto L_0x0038
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ IOException -> 0x0039 }
            return r2
        L_0x0038:
            throw r4     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.toCacheResponse(coil.disk.DiskCache$Snapshot):coil.network.CacheResponse");
    }

    private final ImageSource toImageSource(DiskCache.Snapshot snapshot) {
        return ImageSources.create(snapshot.getData(), getFileSystem(), getDiskCacheKey(), snapshot);
    }

    private final ImageSource toImageSource(ResponseBody responseBody) {
        return ImageSources.create(responseBody.source(), this.options.getContext());
    }

    private final DataSource toDataSource(Response response) {
        return response.networkResponse() != null ? DataSource.NETWORK : DataSource.DISK;
    }

    private final String getDiskCacheKey() {
        String diskCacheKey = this.options.getDiskCacheKey();
        return diskCacheKey == null ? this.url : diskCacheKey;
    }

    private final FileSystem getFileSystem() {
        DiskCache value = this.diskCache.getValue();
        Intrinsics.checkNotNull(value);
        return value.getFileSystem();
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\"\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcoil/fetch/HttpUriFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Landroid/net/Uri;", "callFactory", "Lkotlin/Lazy;", "Lokhttp3/Call$Factory;", "diskCache", "Lcoil/disk/DiskCache;", "respectCacheHeaders", "", "(Lkotlin/Lazy;Lkotlin/Lazy;Z)V", "create", "Lcoil/fetch/Fetcher;", "data", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "isApplicable", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HttpUriFetcher.kt */
    public static final class Factory implements Fetcher.Factory<Uri> {
        private final Lazy<Call.Factory> callFactory;
        private final Lazy<DiskCache> diskCache;
        private final boolean respectCacheHeaders;

        public Factory(Lazy<? extends Call.Factory> lazy, Lazy<? extends DiskCache> lazy2, boolean z) {
            this.callFactory = lazy;
            this.diskCache = lazy2;
            this.respectCacheHeaders = z;
        }

        public Fetcher create(Uri uri, Options options, ImageLoader imageLoader) {
            if (!isApplicable(uri)) {
                return null;
            }
            return new HttpUriFetcher(uri.toString(), options, this.callFactory, this.diskCache, this.respectCacheHeaders);
        }

        private final boolean isApplicable(Uri uri) {
            return Intrinsics.areEqual((Object) uri.getScheme(), (Object) "http") || Intrinsics.areEqual((Object) uri.getScheme(), (Object) "https");
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcoil/fetch/HttpUriFetcher$Companion;", "", "()V", "CACHE_CONTROL_FORCE_NETWORK_NO_CACHE", "Lokhttp3/CacheControl;", "CACHE_CONTROL_NO_NETWORK_NO_CACHE", "MIME_TYPE_TEXT_PLAIN", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HttpUriFetcher.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
