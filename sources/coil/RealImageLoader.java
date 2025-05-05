package coil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import coil.EventListener;
import coil.ImageLoader;
import coil.decode.BitmapFactoryDecoder;
import coil.decode.Decoder;
import coil.disk.DiskCache;
import coil.fetch.AssetUriFetcher;
import coil.fetch.BitmapFetcher;
import coil.fetch.ByteBufferFetcher;
import coil.fetch.ContentUriFetcher;
import coil.fetch.DrawableFetcher;
import coil.fetch.FileFetcher;
import coil.fetch.HttpUriFetcher;
import coil.fetch.ResourceUriFetcher;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.key.FileKeyer;
import coil.key.UriKeyer;
import coil.map.ByteArrayMapper;
import coil.map.FileUriMapper;
import coil.map.HttpUrlMapper;
import coil.map.ResourceIntMapper;
import coil.map.ResourceUriMapper;
import coil.map.StringMapper;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.OneShotDisposable;
import coil.request.RequestService;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.transition.NoneTransition;
import coil.transition.Transition;
import coil.transition.TransitionTarget;
import coil.util.ImageLoaderOptions;
import coil.util.Logger;
import coil.util.SystemCallbacks;
import coil.util.Utils;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.Call;
import okhttp3.HttpUrl;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 [2\u00020\u0001:\u0001[Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u0010\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J\u0019\u0010@\u001a\u00020A2\u0006\u0010>\u001a\u00020?H@ø\u0001\u0000¢\u0006\u0002\u0010BJ!\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020?2\u0006\u0010E\u001a\u00020FH@ø\u0001\u0000¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020IH\u0016J\u0018\u0010J\u001a\u00020K2\u0006\u0010>\u001a\u00020?2\u0006\u0010L\u001a\u00020MH\u0002J\"\u0010N\u001a\u00020K2\u0006\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020MH\u0002J\"\u0010S\u001a\u00020K2\u0006\u0010O\u001a\u00020T2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020MH\u0002J\u0015\u0010U\u001a\u00020K2\u0006\u0010V\u001a\u00020FH\u0000¢\u0006\u0002\bWJ\b\u00108\u001a\u00020KH\u0016J1\u0010X\u001a\u00020K2\u0006\u0010O\u001a\u00020A2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020M2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020K0ZH\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\u0004\u0018\u00010\n8VX\u0002¢\u0006\f\u001a\u0004\b#\u0010$*\u0004\b!\u0010\"R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001d\u0010-\u001a\u0004\u0018\u00010\b8VX\u0002¢\u0006\f\u001a\u0004\b/\u00100*\u0004\b.\u0010\"R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u000e\u00104\u001a\u000205X\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\\"}, d2 = {"Lcoil/RealImageLoader;", "Lcoil/ImageLoader;", "context", "Landroid/content/Context;", "defaults", "Lcoil/request/DefaultRequestOptions;", "memoryCacheLazy", "Lkotlin/Lazy;", "Lcoil/memory/MemoryCache;", "diskCacheLazy", "Lcoil/disk/DiskCache;", "callFactoryLazy", "Lokhttp3/Call$Factory;", "eventListenerFactory", "Lcoil/EventListener$Factory;", "componentRegistry", "Lcoil/ComponentRegistry;", "options", "Lcoil/util/ImageLoaderOptions;", "logger", "Lcoil/util/Logger;", "(Landroid/content/Context;Lcoil/request/DefaultRequestOptions;Lkotlin/Lazy;Lkotlin/Lazy;Lkotlin/Lazy;Lcoil/EventListener$Factory;Lcoil/ComponentRegistry;Lcoil/util/ImageLoaderOptions;Lcoil/util/Logger;)V", "getCallFactoryLazy", "()Lkotlin/Lazy;", "getComponentRegistry", "()Lcoil/ComponentRegistry;", "components", "getComponents", "getContext", "()Landroid/content/Context;", "getDefaults", "()Lcoil/request/DefaultRequestOptions;", "diskCache", "getDiskCache$delegate", "(Lcoil/RealImageLoader;)Ljava/lang/Object;", "getDiskCache", "()Lcoil/disk/DiskCache;", "getDiskCacheLazy", "getEventListenerFactory", "()Lcoil/EventListener$Factory;", "interceptors", "", "Lcoil/intercept/Interceptor;", "getLogger", "()Lcoil/util/Logger;", "memoryCache", "getMemoryCache$delegate", "getMemoryCache", "()Lcoil/memory/MemoryCache;", "getMemoryCacheLazy", "getOptions", "()Lcoil/util/ImageLoaderOptions;", "requestService", "Lcoil/request/RequestService;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "shutdown", "Ljava/util/concurrent/atomic/AtomicBoolean;", "systemCallbacks", "Lcoil/util/SystemCallbacks;", "enqueue", "Lcoil/request/Disposable;", "request", "Lcoil/request/ImageRequest;", "execute", "Lcoil/request/ImageResult;", "(Lcoil/request/ImageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeMain", "initialRequest", "type", "", "(Lcoil/request/ImageRequest;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newBuilder", "Lcoil/ImageLoader$Builder;", "onCancel", "", "eventListener", "Lcoil/EventListener;", "onError", "result", "Lcoil/request/ErrorResult;", "target", "Lcoil/target/Target;", "onSuccess", "Lcoil/request/SuccessResult;", "onTrimMemory", "level", "onTrimMemory$coil_base_release", "transition", "setDrawable", "Lkotlin/Function0;", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader implements ImageLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int REQUEST_TYPE_ENQUEUE = 0;
    private static final int REQUEST_TYPE_EXECUTE = 1;
    private static final String TAG = "RealImageLoader";
    private final Lazy<Call.Factory> callFactoryLazy;
    private final ComponentRegistry componentRegistry;
    private final ComponentRegistry components;
    private final Context context;
    private final DefaultRequestOptions defaults;
    private final Lazy<DiskCache> diskCacheLazy;
    private final EventListener.Factory eventListenerFactory;
    /* access modifiers changed from: private */
    public final List<Interceptor> interceptors;
    private final Logger logger;
    private final Lazy<MemoryCache> memoryCacheLazy;
    private final ImageLoaderOptions options;
    private final RequestService requestService;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()).plus(new RealImageLoader$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this)));
    private final AtomicBoolean shutdown;
    private final SystemCallbacks systemCallbacks;

    public RealImageLoader(Context context2, DefaultRequestOptions defaultRequestOptions, Lazy<? extends MemoryCache> lazy, Lazy<? extends DiskCache> lazy2, Lazy<? extends Call.Factory> lazy3, EventListener.Factory factory, ComponentRegistry componentRegistry2, ImageLoaderOptions imageLoaderOptions, Logger logger2) {
        this.context = context2;
        this.defaults = defaultRequestOptions;
        this.memoryCacheLazy = lazy;
        this.diskCacheLazy = lazy2;
        this.callFactoryLazy = lazy3;
        this.eventListenerFactory = factory;
        this.componentRegistry = componentRegistry2;
        this.options = imageLoaderOptions;
        this.logger = logger2;
        SystemCallbacks systemCallbacks2 = new SystemCallbacks(this, context2, imageLoaderOptions.getNetworkObserverEnabled());
        this.systemCallbacks = systemCallbacks2;
        ImageLoader imageLoader = this;
        RequestService requestService2 = new RequestService(imageLoader, systemCallbacks2, logger2);
        this.requestService = requestService2;
        this.components = componentRegistry2.newBuilder().add(new HttpUrlMapper(), HttpUrl.class).add(new StringMapper(), String.class).add(new FileUriMapper(), Uri.class).add(new ResourceUriMapper(), Uri.class).add(new ResourceIntMapper(), Integer.class).add(new ByteArrayMapper(), byte[].class).add(new UriKeyer(), Uri.class).add(new FileKeyer(imageLoaderOptions.getAddLastModifiedToFileCacheKey()), File.class).add(new HttpUriFetcher.Factory(lazy3, lazy2, imageLoaderOptions.getRespectCacheHeaders()), Uri.class).add(new FileFetcher.Factory(), File.class).add(new AssetUriFetcher.Factory(), Uri.class).add(new ContentUriFetcher.Factory(), Uri.class).add(new ResourceUriFetcher.Factory(), Uri.class).add(new DrawableFetcher.Factory(), Drawable.class).add(new BitmapFetcher.Factory(), Bitmap.class).add(new ByteBufferFetcher.Factory(), ByteBuffer.class).add((Decoder.Factory) new BitmapFactoryDecoder.Factory(imageLoaderOptions.getBitmapFactoryMaxParallelism(), imageLoaderOptions.getBitmapFactoryExifOrientationPolicy())).build();
        this.interceptors = CollectionsKt.plus(getComponents().getInterceptors(), new EngineInterceptor(imageLoader, requestService2, logger2));
        this.shutdown = new AtomicBoolean(false);
        systemCallbacks2.register();
    }

    public final Context getContext() {
        return this.context;
    }

    public DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    public final Lazy<MemoryCache> getMemoryCacheLazy() {
        return this.memoryCacheLazy;
    }

    public final Lazy<DiskCache> getDiskCacheLazy() {
        return this.diskCacheLazy;
    }

    public final Lazy<Call.Factory> getCallFactoryLazy() {
        return this.callFactoryLazy;
    }

    public final EventListener.Factory getEventListenerFactory() {
        return this.eventListenerFactory;
    }

    public final ComponentRegistry getComponentRegistry() {
        return this.componentRegistry;
    }

    public final ImageLoaderOptions getOptions() {
        return this.options;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    private static Object getMemoryCache$delegate(RealImageLoader realImageLoader) {
        return realImageLoader.memoryCacheLazy;
    }

    public MemoryCache getMemoryCache() {
        return this.memoryCacheLazy.getValue();
    }

    private static Object getDiskCache$delegate(RealImageLoader realImageLoader) {
        return realImageLoader.diskCacheLazy;
    }

    public DiskCache getDiskCache() {
        return this.diskCacheLazy.getValue();
    }

    public ComponentRegistry getComponents() {
        return this.components;
    }

    public Disposable enqueue(ImageRequest imageRequest) {
        Deferred async$default = BuildersKt__Builders_commonKt.async$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new RealImageLoader$enqueue$job$1(this, imageRequest, (Continuation<? super RealImageLoader$enqueue$job$1>) null), 3, (Object) null);
        if (imageRequest.getTarget() instanceof ViewTarget) {
            return Utils.getRequestManager(((ViewTarget) imageRequest.getTarget()).getView()).getDisposable(async$default);
        }
        return new OneShotDisposable(async$default);
    }

    public Object execute(ImageRequest imageRequest, Continuation<? super ImageResult> continuation) {
        return CoroutineScopeKt.coroutineScope(new RealImageLoader$execute$2(imageRequest, this, (Continuation<? super RealImageLoader$execute$2>) null), continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: coil.EventListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: coil.request.ImageRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: coil.request.RequestDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v34, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: coil.RealImageLoader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: coil.EventListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: coil.request.ImageRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: coil.request.RequestDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: coil.RealImageLoader} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f3 A[Catch:{ all -> 0x01a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0109 A[Catch:{ all -> 0x01a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0119 A[Catch:{ all -> 0x01a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0123 A[Catch:{ all -> 0x01a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012f A[Catch:{ all -> 0x01a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0181 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x018c A[Catch:{ all -> 0x004c }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0197 A[Catch:{ all -> 0x004c }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bd A[Catch:{ all -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01ce A[SYNTHETIC, Splitter:B:92:0x01ce] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executeMain(coil.request.ImageRequest r21, int r22, kotlin.coroutines.Continuation<? super coil.request.ImageResult> r23) {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r2 = r23
            boolean r3 = r2 instanceof coil.RealImageLoader$executeMain$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            coil.RealImageLoader$executeMain$1 r3 = (coil.RealImageLoader$executeMain$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            coil.RealImageLoader$executeMain$1 r3 = new coil.RealImageLoader$executeMain$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 3
            r7 = 2
            r8 = 1
            r9 = 0
            if (r5 == 0) goto L_0x0098
            if (r5 == r8) goto L_0x0079
            if (r5 == r7) goto L_0x0057
            if (r5 != r6) goto L_0x004f
            java.lang.Object r0 = r3.L$3
            r4 = r0
            coil.EventListener r4 = (coil.EventListener) r4
            java.lang.Object r0 = r3.L$2
            r5 = r0
            coil.request.ImageRequest r5 = (coil.request.ImageRequest) r5
            java.lang.Object r0 = r3.L$1
            r6 = r0
            coil.request.RequestDelegate r6 = (coil.request.RequestDelegate) r6
            java.lang.Object r0 = r3.L$0
            r3 = r0
            coil.RealImageLoader r3 = (coil.RealImageLoader) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x004c }
            goto L_0x0186
        L_0x004c:
            r0 = move-exception
            goto L_0x01b9
        L_0x004f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0057:
            java.lang.Object r0 = r3.L$4
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            java.lang.Object r5 = r3.L$3
            coil.EventListener r5 = (coil.EventListener) r5
            java.lang.Object r7 = r3.L$2
            coil.request.ImageRequest r7 = (coil.request.ImageRequest) r7
            java.lang.Object r8 = r3.L$1
            coil.request.RequestDelegate r8 = (coil.request.RequestDelegate) r8
            java.lang.Object r10 = r3.L$0
            coil.RealImageLoader r10 = (coil.RealImageLoader) r10
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0072 }
            r17 = r0
            goto L_0x0155
        L_0x0072:
            r0 = move-exception
            r4 = r5
            r5 = r7
            r6 = r8
            r3 = r10
            goto L_0x01b9
        L_0x0079:
            java.lang.Object r0 = r3.L$3
            r5 = r0
            coil.EventListener r5 = (coil.EventListener) r5
            java.lang.Object r0 = r3.L$2
            r8 = r0
            coil.request.ImageRequest r8 = (coil.request.ImageRequest) r8
            java.lang.Object r0 = r3.L$1
            r10 = r0
            coil.request.RequestDelegate r10 = (coil.request.RequestDelegate) r10
            java.lang.Object r0 = r3.L$0
            r11 = r0
            coil.RealImageLoader r11 = (coil.RealImageLoader) r11
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0091 }
            goto L_0x00e8
        L_0x0091:
            r0 = move-exception
            r4 = r5
            r5 = r8
            r6 = r10
        L_0x0095:
            r3 = r11
            goto L_0x01b9
        L_0x0098:
            kotlin.ResultKt.throwOnFailure(r2)
            coil.request.RequestService r2 = r1.requestService
            kotlin.coroutines.CoroutineContext r5 = r3.getContext()
            kotlinx.coroutines.Job r5 = kotlinx.coroutines.JobKt.getJob(r5)
            coil.request.RequestDelegate r2 = r2.requestDelegate(r0, r5)
            r2.assertActive()
            coil.request.ImageRequest$Builder r0 = coil.request.ImageRequest.newBuilder$default(r0, r9, r8, r9)
            coil.request.DefaultRequestOptions r5 = r20.getDefaults()
            coil.request.ImageRequest$Builder r0 = r0.defaults(r5)
            coil.request.ImageRequest r5 = r0.build()
            coil.EventListener$Factory r0 = r1.eventListenerFactory
            coil.EventListener r10 = r0.create(r5)
            java.lang.Object r0 = r5.getData()     // Catch:{ all -> 0x01b5 }
            coil.request.NullRequestData r11 = coil.request.NullRequestData.INSTANCE     // Catch:{ all -> 0x01b5 }
            if (r0 == r11) goto L_0x01af
            r2.start()     // Catch:{ all -> 0x01b5 }
            if (r22 != 0) goto L_0x00ea
            androidx.lifecycle.Lifecycle r0 = r5.getLifecycle()     // Catch:{ all -> 0x01b5 }
            r3.L$0 = r1     // Catch:{ all -> 0x01b5 }
            r3.L$1 = r2     // Catch:{ all -> 0x01b5 }
            r3.L$2 = r5     // Catch:{ all -> 0x01b5 }
            r3.L$3 = r10     // Catch:{ all -> 0x01b5 }
            r3.label = r8     // Catch:{ all -> 0x01b5 }
            java.lang.Object r0 = coil.util.Lifecycles.awaitStarted(r0, r3)     // Catch:{ all -> 0x01b5 }
            if (r0 != r4) goto L_0x00e4
            return r4
        L_0x00e4:
            r11 = r1
            r8 = r5
            r5 = r10
            r10 = r2
        L_0x00e8:
            r2 = r10
            goto L_0x00ed
        L_0x00ea:
            r11 = r1
            r8 = r5
            r5 = r10
        L_0x00ed:
            coil.memory.MemoryCache r0 = r11.getMemoryCache()     // Catch:{ all -> 0x01a9 }
            if (r0 == 0) goto L_0x0106
            coil.memory.MemoryCache$Key r10 = r8.getPlaceholderMemoryCacheKey()     // Catch:{ all -> 0x01a9 }
            if (r10 == 0) goto L_0x00fe
            coil.memory.MemoryCache$Value r0 = r0.get(r10)     // Catch:{ all -> 0x01a9 }
            goto L_0x00ff
        L_0x00fe:
            r0 = r9
        L_0x00ff:
            if (r0 == 0) goto L_0x0106
            android.graphics.Bitmap r0 = r0.getBitmap()     // Catch:{ all -> 0x01a9 }
            goto L_0x0107
        L_0x0106:
            r0 = r9
        L_0x0107:
            if (r0 == 0) goto L_0x0119
            android.content.Context r10 = r8.getContext()     // Catch:{ all -> 0x01a9 }
            android.content.res.Resources r10 = r10.getResources()     // Catch:{ all -> 0x01a9 }
            android.graphics.drawable.BitmapDrawable r12 = new android.graphics.drawable.BitmapDrawable     // Catch:{ all -> 0x01a9 }
            r12.<init>(r10, r0)     // Catch:{ all -> 0x01a9 }
            android.graphics.drawable.Drawable r12 = (android.graphics.drawable.Drawable) r12     // Catch:{ all -> 0x01a9 }
            goto L_0x011d
        L_0x0119:
            android.graphics.drawable.Drawable r12 = r8.getPlaceholder()     // Catch:{ all -> 0x01a9 }
        L_0x011d:
            coil.target.Target r10 = r8.getTarget()     // Catch:{ all -> 0x01a9 }
            if (r10 == 0) goto L_0x0126
            r10.onStart(r12)     // Catch:{ all -> 0x01a9 }
        L_0x0126:
            r5.onStart(r8)     // Catch:{ all -> 0x01a9 }
            coil.request.ImageRequest$Listener r10 = r8.getListener()     // Catch:{ all -> 0x01a9 }
            if (r10 == 0) goto L_0x0132
            r10.onStart(r8)     // Catch:{ all -> 0x01a9 }
        L_0x0132:
            r5.resolveSizeStart(r8)     // Catch:{ all -> 0x01a9 }
            coil.size.SizeResolver r10 = r8.getSizeResolver()     // Catch:{ all -> 0x01a9 }
            r3.L$0 = r11     // Catch:{ all -> 0x01a9 }
            r3.L$1 = r2     // Catch:{ all -> 0x01a9 }
            r3.L$2 = r8     // Catch:{ all -> 0x01a9 }
            r3.L$3 = r5     // Catch:{ all -> 0x01a9 }
            r3.L$4 = r0     // Catch:{ all -> 0x01a9 }
            r3.label = r7     // Catch:{ all -> 0x01a9 }
            java.lang.Object r7 = r10.size(r3)     // Catch:{ all -> 0x01a9 }
            if (r7 != r4) goto L_0x014c
            return r4
        L_0x014c:
            r17 = r0
            r10 = r11
            r19 = r8
            r8 = r2
            r2 = r7
            r7 = r19
        L_0x0155:
            r15 = r2
            coil.size.Size r15 = (coil.size.Size) r15     // Catch:{ all -> 0x0072 }
            r5.resolveSizeEnd(r7, r15)     // Catch:{ all -> 0x0072 }
            kotlinx.coroutines.CoroutineDispatcher r0 = r7.getInterceptorDispatcher()     // Catch:{ all -> 0x0072 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ all -> 0x0072 }
            coil.RealImageLoader$executeMain$result$1 r2 = new coil.RealImageLoader$executeMain$result$1     // Catch:{ all -> 0x0072 }
            r18 = 0
            r12 = r2
            r13 = r7
            r14 = r10
            r16 = r5
            r12.<init>(r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0072 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ all -> 0x0072 }
            r3.L$0 = r10     // Catch:{ all -> 0x0072 }
            r3.L$1 = r8     // Catch:{ all -> 0x0072 }
            r3.L$2 = r7     // Catch:{ all -> 0x0072 }
            r3.L$3 = r5     // Catch:{ all -> 0x0072 }
            r3.L$4 = r9     // Catch:{ all -> 0x0072 }
            r3.label = r6     // Catch:{ all -> 0x0072 }
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r0, r2, r3)     // Catch:{ all -> 0x0072 }
            if (r2 != r4) goto L_0x0182
            return r4
        L_0x0182:
            r4 = r5
            r5 = r7
            r6 = r8
            r3 = r10
        L_0x0186:
            coil.request.ImageResult r2 = (coil.request.ImageResult) r2     // Catch:{ all -> 0x004c }
            boolean r0 = r2 instanceof coil.request.SuccessResult     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x0197
            r0 = r2
            coil.request.SuccessResult r0 = (coil.request.SuccessResult) r0     // Catch:{ all -> 0x004c }
            coil.target.Target r7 = r5.getTarget()     // Catch:{ all -> 0x004c }
            r3.onSuccess(r0, r7, r4)     // Catch:{ all -> 0x004c }
            goto L_0x01a5
        L_0x0197:
            boolean r0 = r2 instanceof coil.request.ErrorResult     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x01a5
            r0 = r2
            coil.request.ErrorResult r0 = (coil.request.ErrorResult) r0     // Catch:{ all -> 0x004c }
            coil.target.Target r7 = r5.getTarget()     // Catch:{ all -> 0x004c }
            r3.onError(r0, r7, r4)     // Catch:{ all -> 0x004c }
        L_0x01a5:
            r6.complete()
            return r2
        L_0x01a9:
            r0 = move-exception
            r6 = r2
            r4 = r5
            r5 = r8
            goto L_0x0095
        L_0x01af:
            coil.request.NullRequestDataException r0 = new coil.request.NullRequestDataException     // Catch:{ all -> 0x01b5 }
            r0.<init>()     // Catch:{ all -> 0x01b5 }
            throw r0     // Catch:{ all -> 0x01b5 }
        L_0x01b5:
            r0 = move-exception
            r3 = r1
            r6 = r2
            r4 = r10
        L_0x01b9:
            boolean r2 = r0 instanceof java.util.concurrent.CancellationException     // Catch:{ all -> 0x01d2 }
            if (r2 != 0) goto L_0x01ce
            coil.request.RequestService r2 = r3.requestService     // Catch:{ all -> 0x01d2 }
            coil.request.ErrorResult r0 = r2.errorResult(r5, r0)     // Catch:{ all -> 0x01d2 }
            coil.target.Target r2 = r5.getTarget()     // Catch:{ all -> 0x01d2 }
            r3.onError(r0, r2, r4)     // Catch:{ all -> 0x01d2 }
            r6.complete()
            return r0
        L_0x01ce:
            r3.onCancel(r5, r4)     // Catch:{ all -> 0x01d2 }
            throw r0     // Catch:{ all -> 0x01d2 }
        L_0x01d2:
            r0 = move-exception
            r6.complete()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.executeMain(coil.request.ImageRequest, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onTrimMemory$coil_base_release(int i) {
        MemoryCache value;
        Lazy<MemoryCache> lazy = this.memoryCacheLazy;
        if (lazy != null && (value = lazy.getValue()) != null) {
            value.trimMemory(i);
        }
    }

    public void shutdown() {
        if (!this.shutdown.getAndSet(true)) {
            CoroutineScopeKt.cancel$default(this.scope, (CancellationException) null, 1, (Object) null);
            this.systemCallbacks.shutdown();
            MemoryCache memoryCache = getMemoryCache();
            if (memoryCache != null) {
                memoryCache.clear();
            }
        }
    }

    public ImageLoader.Builder newBuilder() {
        return new ImageLoader.Builder(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004a, code lost:
        if (r8 != null) goto L_0x0063;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void onSuccess(coil.request.SuccessResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.decode.DataSource r1 = r7.getDataSource()
            coil.util.Logger r2 = r6.logger
            if (r2 == 0) goto L_0x0046
            int r3 = r2.getLevel()
            r4 = 4
            if (r3 > r4) goto L_0x0046
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = coil.util.Utils.getEmoji(r1)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = " Successful ("
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r1 = r1.name()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = ") - "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.Object r3 = r0.getData()
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r3 = 0
            java.lang.String r5 = "RealImageLoader"
            r2.log(r5, r4, r1, r3)
        L_0x0046:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L_0x004d
            if (r8 == 0) goto L_0x007c
            goto L_0x0063
        L_0x004d:
            r1 = r7
            coil.request.ImageResult r1 = (coil.request.ImageResult) r1
            coil.request.ImageRequest r2 = r1.getRequest()
            coil.transition.Transition$Factory r2 = r2.getTransitionFactory()
            r3 = r8
            coil.transition.TransitionTarget r3 = (coil.transition.TransitionTarget) r3
            coil.transition.Transition r2 = r2.create(r3, r1)
            boolean r3 = r2 instanceof coil.transition.NoneTransition
            if (r3 == 0) goto L_0x006b
        L_0x0063:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onSuccess(r1)
            goto L_0x007c
        L_0x006b:
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionStart(r8, r2)
            r2.transition()
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionEnd(r8, r2)
        L_0x007c:
            r9.onSuccess(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L_0x0088
            r8.onSuccess(r0, r7)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onSuccess(coil.request.SuccessResult, coil.target.Target, coil.EventListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003b, code lost:
        if (r8 != null) goto L_0x0054;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void onError(coil.request.ErrorResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.util.Logger r1 = r6.logger
            if (r1 == 0) goto L_0x0037
            int r2 = r1.getLevel()
            r3 = 4
            if (r2 > r3) goto L_0x0037
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "🚨 Failed - "
            r2.<init>(r4)
            java.lang.Object r4 = r0.getData()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = " - "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.Throwable r4 = r7.getThrowable()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r4 = 0
            java.lang.String r5 = "RealImageLoader"
            r1.log(r5, r3, r2, r4)
        L_0x0037:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L_0x003e
            if (r8 == 0) goto L_0x006d
            goto L_0x0054
        L_0x003e:
            r1 = r7
            coil.request.ImageResult r1 = (coil.request.ImageResult) r1
            coil.request.ImageRequest r2 = r1.getRequest()
            coil.transition.Transition$Factory r2 = r2.getTransitionFactory()
            r3 = r8
            coil.transition.TransitionTarget r3 = (coil.transition.TransitionTarget) r3
            coil.transition.Transition r2 = r2.create(r3, r1)
            boolean r3 = r2 instanceof coil.transition.NoneTransition
            if (r3 == 0) goto L_0x005c
        L_0x0054:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onError(r1)
            goto L_0x006d
        L_0x005c:
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionStart(r8, r2)
            r2.transition()
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionEnd(r8, r2)
        L_0x006d:
            r9.onError(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L_0x0079
            r8.onError(r0, r7)
        L_0x0079:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onError(coil.request.ErrorResult, coil.target.Target, coil.EventListener):void");
    }

    private final void onCancel(ImageRequest imageRequest, EventListener eventListener) {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 4) {
            logger2.log(TAG, 4, "🏗  Cancelled - " + imageRequest.getData(), (Throwable) null);
        }
        eventListener.onCancel(imageRequest);
        ImageRequest.Listener listener = imageRequest.getListener();
        if (listener != null) {
            listener.onCancel(imageRequest);
        }
    }

    private final void transition(ImageResult imageResult, Target target, EventListener eventListener, Function0<Unit> function0) {
        if (!(target instanceof TransitionTarget)) {
            function0.invoke();
            return;
        }
        Transition create = imageResult.getRequest().getTransitionFactory().create((TransitionTarget) target, imageResult);
        if (create instanceof NoneTransition) {
            function0.invoke();
            return;
        }
        eventListener.transitionStart(imageResult.getRequest(), create);
        create.transition();
        eventListener.transitionEnd(imageResult.getRequest(), create);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcoil/RealImageLoader$Companion;", "", "()V", "REQUEST_TYPE_ENQUEUE", "", "REQUEST_TYPE_EXECUTE", "TAG", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RealImageLoader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
