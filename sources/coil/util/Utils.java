package coil.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Looper;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.disk.DiskCache;
import coil.fetch.Fetcher;
import coil.intercept.Interceptor;
import coil.intercept.RealInterceptorChain;
import coil.memory.MemoryCache;
import coil.request.Parameters;
import coil.request.Tags;
import coil.size.Dimension;
import coil.size.Scale;
import coil.size.Size;
import coil.size.Sizes;
import java.io.Closeable;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Deferred;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020A2\u0006\u0010M\u001a\u00020\rH\u0000\u001a\u0010\u0010N\u001a\u00020\r2\u0006\u0010L\u001a\u00020AH\u0000\u001a\u0010\u0010O\u001a\u0002022\u0006\u0010P\u001a\u00020&H\u0000\u001a\b\u0010Q\u001a\u000202H\u0000\u001a\b\u0010R\u001a\u00020SH\u0000\u001a\f\u0010T\u001a\u00020U*\u00020VH\u0000\u001a\u0017\u0010W\u001a\u00020X*\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010ZH\b\u001a+\u0010W\u001a\u00020X*\u00020X2\u001c\u0010[\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030]\u0012\b\u0012\u0006\u0012\u0002\b\u00030^\u0018\u00010\\H\b\u001a\u0014\u0010_\u001a\u00020`*\u00020`2\u0006\u0010a\u001a\u00020\u0001H\u0000\u001a\f\u0010b\u001a\u00020U*\u00020cH\u0000\u001a\u0019\u0010d\u001a\u0004\u0018\u00010e*\u00020f2\b\u0010g\u001a\u0004\u0018\u00010hH\n\u001a\u001f\u0010i\u001a\u0004\u0018\u0001Hj\"\u0004\b\u0000\u0010j*\b\u0012\u0004\u0012\u0002Hj0kH\u0000¢\u0006\u0002\u0010l\u001a\u0018\u0010m\u001a\u0004\u0018\u00010\u0001*\u00020n2\b\u0010o\u001a\u0004\u0018\u00010\u0001H\u0000\u001a#\u0010p\u001a\u00020\u0007*\u00020q2\u0006\u0010D\u001a\u00020E2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00070sH\b\u001a\f\u0010t\u001a\u000202*\u00020\u0007H\u0000\u001a\u000e\u0010u\u001a\u00020v*\u0004\u0018\u00010vH\u0000\u001a\u000e\u0010u\u001a\u00020w*\u0004\u0018\u00010wH\u0000\u001a\u000e\u0010u\u001a\u00020\t*\u0004\u0018\u00010\tH\u0000\u001a\f\u0010x\u001a\u00020y*\u00020zH\u0000\u001a\u0014\u0010{\u001a\u00020\u0007*\u00020\u00012\u0006\u0010|\u001a\u00020\u0007H\u0000\u001a\u0014\u0010}\u001a\u00020\u0007*\u00020~2\u0006\u0010D\u001a\u00020EH\u0000\u001a#\u0010\u001a\u00020\u0007*\u00020q2\u0006\u0010D\u001a\u00020E2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00070sH\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u000e\u0010\u0016\u001a\u00020\rXT¢\u0006\u0002\n\u0000\"\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001a\"\u0018\u0010\u001c\u001a\u00020\u0001*\u00020\u001d8@X\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0018\u0010 \u001a\u00020!*\u00020\"8@X\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$\"\u001a\u0010%\u001a\u0004\u0018\u00010\u0001*\u00020&8@X\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0018\u0010)\u001a\u00020\u0007*\u00020*8@X\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\"\u0019\u0010-\u001a\u00020\u0007*\u00020.8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0018\u00101\u001a\u000202*\u00020\"8@X\u0004¢\u0006\u0006\u001a\u0004\b1\u00103\"\u0018\u00104\u001a\u000202*\u00020*8@X\u0004¢\u0006\u0006\u001a\u0004\b4\u00105\"\u0018\u00106\u001a\u00020\u0007*\u0002078@X\u0004¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0018\u0010:\u001a\u00020;*\u00020<8@X\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>\"\u0018\u0010?\u001a\u00020@*\u00020A8@X\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C\"\u0018\u0010D\u001a\u00020E*\u00020F8@X\u0004¢\u0006\u0006\u001a\u0004\bG\u0010H\"\u0018\u0010I\u001a\u00020\u0007*\u00020*8@X\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010,¨\u0006\u0001"}, d2 = {"ASSET_FILE_PATH_ROOT", "", "DEFAULT_BITMAP_CONFIG", "Landroid/graphics/Bitmap$Config;", "getDEFAULT_BITMAP_CONFIG", "()Landroid/graphics/Bitmap$Config;", "DEFAULT_MEMORY_CLASS_MEGABYTES", "", "EMPTY_HEADERS", "Lokhttp3/Headers;", "getEMPTY_HEADERS", "()Lokhttp3/Headers;", "LOW_MEMORY_MULTIPLIER", "", "MIME_TYPE_HEIC", "MIME_TYPE_HEIF", "MIME_TYPE_JPEG", "MIME_TYPE_WEBP", "NULL_COLOR_SPACE", "Landroid/graphics/ColorSpace;", "getNULL_COLOR_SPACE", "()Landroid/graphics/ColorSpace;", "STANDARD_MEMORY_MULTIPLIER", "VALID_TRANSFORMATION_CONFIGS", "", "getVALID_TRANSFORMATION_CONFIGS", "()[Landroid/graphics/Bitmap$Config;", "[Landroid/graphics/Bitmap$Config;", "emoji", "Lcoil/decode/DataSource;", "getEmoji", "(Lcoil/decode/DataSource;)Ljava/lang/String;", "eventListener", "Lcoil/EventListener;", "Lcoil/intercept/Interceptor$Chain;", "getEventListener", "(Lcoil/intercept/Interceptor$Chain;)Lcoil/EventListener;", "firstPathSegment", "Landroid/net/Uri;", "getFirstPathSegment", "(Landroid/net/Uri;)Ljava/lang/String;", "height", "Landroid/graphics/drawable/Drawable;", "getHeight", "(Landroid/graphics/drawable/Drawable;)I", "identityHashCode", "", "getIdentityHashCode", "(Ljava/lang/Object;)I", "isPlaceholderCached", "", "(Lcoil/intercept/Interceptor$Chain;)Z", "isVector", "(Landroid/graphics/drawable/Drawable;)Z", "nightMode", "Landroid/content/res/Configuration;", "getNightMode", "(Landroid/content/res/Configuration;)I", "requestManager", "Lcoil/request/ViewTargetRequestManager;", "Landroid/view/View;", "getRequestManager", "(Landroid/view/View;)Lcoil/request/ViewTargetRequestManager;", "safeCacheDir", "Ljava/io/File;", "Landroid/content/Context;", "getSafeCacheDir", "(Landroid/content/Context;)Ljava/io/File;", "scale", "Lcoil/size/Scale;", "Landroid/widget/ImageView;", "getScale", "(Landroid/widget/ImageView;)Lcoil/size/Scale;", "width", "getWidth", "calculateMemoryCacheSize", "context", "percent", "defaultMemoryCacheSizePercent", "isAssetUri", "uri", "isMainThread", "unsupported", "", "abortQuietly", "", "Lcoil/disk/DiskCache$Editor;", "addFirst", "Lcoil/ComponentRegistry$Builder;", "factory", "Lcoil/decode/Decoder$Factory;", "pair", "Lkotlin/Pair;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/lang/Class;", "addUnsafeNonAscii", "Lokhttp3/Headers$Builder;", "line", "closeQuietly", "Ljava/io/Closeable;", "get", "Lcoil/memory/MemoryCache$Value;", "Lcoil/memory/MemoryCache;", "key", "Lcoil/memory/MemoryCache$Key;", "getCompletedOrNull", "T", "Lkotlinx/coroutines/Deferred;", "(Lkotlinx/coroutines/Deferred;)Ljava/lang/Object;", "getMimeTypeFromUrl", "Landroid/webkit/MimeTypeMap;", "url", "heightPx", "Lcoil/size/Size;", "original", "Lkotlin/Function0;", "isMinOrMax", "orEmpty", "Lcoil/request/Parameters;", "Lcoil/request/Tags;", "requireBody", "Lokhttp3/ResponseBody;", "Lokhttp3/Response;", "toNonNegativeInt", "defaultValue", "toPx", "Lcoil/size/Dimension;", "widthPx", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: coil.util.-Utils  reason: invalid class name */
/* compiled from: Utils.kt */
public final class Utils {
    public static final String ASSET_FILE_PATH_ROOT = "android_asset";
    private static final Bitmap.Config DEFAULT_BITMAP_CONFIG = Bitmap.Config.HARDWARE;
    private static final int DEFAULT_MEMORY_CLASS_MEGABYTES = 256;
    private static final Headers EMPTY_HEADERS = new Headers.Builder().build();
    private static final double LOW_MEMORY_MULTIPLIER = 0.15d;
    public static final String MIME_TYPE_HEIC = "image/heic";
    public static final String MIME_TYPE_HEIF = "image/heif";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_WEBP = "image/webp";
    private static final ColorSpace NULL_COLOR_SPACE = null;
    private static final double STANDARD_MEMORY_MULTIPLIER = 0.2d;
    private static final Bitmap.Config[] VALID_TRANSFORMATION_CONFIGS = {Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16};

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: coil.util.-Utils$WhenMappings */
    /* compiled from: Utils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|33) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|33) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0065 */
        static {
            /*
                coil.decode.DataSource[] r0 = coil.decode.DataSource.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                coil.decode.DataSource r2 = coil.decode.DataSource.MEMORY_CACHE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                coil.decode.DataSource r3 = coil.decode.DataSource.MEMORY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                coil.decode.DataSource r4 = coil.decode.DataSource.DISK     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                r4 = 4
                coil.decode.DataSource r5 = coil.decode.DataSource.NETWORK     // Catch:{ NoSuchFieldError -> 0x002b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r0[r5] = r4     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x003c }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
                r0[r5] = r1     // Catch:{ NoSuchFieldError -> 0x003c }
            L_0x003c:
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r0[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x004c }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004c }
                r0[r5] = r3     // Catch:{ NoSuchFieldError -> 0x004c }
            L_0x004c:
                android.widget.ImageView$ScaleType r3 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                $EnumSwitchMapping$1 = r0
                coil.size.Scale[] r0 = coil.size.Scale.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                coil.size.Scale r3 = coil.size.Scale.FILL     // Catch:{ NoSuchFieldError -> 0x0065 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0065 }
            L_0x0065:
                coil.size.Scale r1 = coil.size.Scale.FIT     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                $EnumSwitchMapping$2 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.util.Utils.WhenMappings.<clinit>():void");
        }
    }

    public static final boolean isMinOrMax(int i) {
        return i == Integer.MIN_VALUE || i == Integer.MAX_VALUE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: coil.request.ViewTargetRequestManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final coil.request.ViewTargetRequestManager getRequestManager(android.view.View r3) {
        /*
            int r0 = coil.base.R.id.coil_request_manager
            java.lang.Object r0 = r3.getTag(r0)
            boolean r1 = r0 instanceof coil.request.ViewTargetRequestManager
            r2 = 0
            if (r1 == 0) goto L_0x000e
            coil.request.ViewTargetRequestManager r0 = (coil.request.ViewTargetRequestManager) r0
            goto L_0x000f
        L_0x000e:
            r0 = r2
        L_0x000f:
            if (r0 != 0) goto L_0x0038
            monitor-enter(r3)
            int r0 = coil.base.R.id.coil_request_manager     // Catch:{ all -> 0x0035 }
            java.lang.Object r0 = r3.getTag(r0)     // Catch:{ all -> 0x0035 }
            boolean r1 = r0 instanceof coil.request.ViewTargetRequestManager     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x001f
            r2 = r0
            coil.request.ViewTargetRequestManager r2 = (coil.request.ViewTargetRequestManager) r2     // Catch:{ all -> 0x0035 }
        L_0x001f:
            if (r2 == 0) goto L_0x0023
            r0 = r2
            goto L_0x0033
        L_0x0023:
            coil.request.ViewTargetRequestManager r0 = new coil.request.ViewTargetRequestManager     // Catch:{ all -> 0x0035 }
            r0.<init>(r3)     // Catch:{ all -> 0x0035 }
            r1 = r0
            android.view.View$OnAttachStateChangeListener r1 = (android.view.View.OnAttachStateChangeListener) r1     // Catch:{ all -> 0x0035 }
            r3.addOnAttachStateChangeListener(r1)     // Catch:{ all -> 0x0035 }
            int r1 = coil.base.R.id.coil_request_manager     // Catch:{ all -> 0x0035 }
            r3.setTag(r1, r0)     // Catch:{ all -> 0x0035 }
        L_0x0033:
            monitor-exit(r3)
            goto L_0x0038
        L_0x0035:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.util.Utils.getRequestManager(android.view.View):coil.request.ViewTargetRequestManager");
    }

    public static final String getEmoji(DataSource dataSource) {
        int i = WhenMappings.$EnumSwitchMapping$0[dataSource.ordinal()];
        if (i == 1 || i == 2) {
            return Emoji.BRAIN;
        }
        if (i == 3) {
            return Emoji.FLOPPY;
        }
        if (i == 4) {
            return Emoji.CLOUD;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int getWidth(Drawable drawable) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicWidth() : bitmap.getWidth();
    }

    public static final int getHeight(Drawable drawable) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicHeight() : bitmap.getHeight();
    }

    public static final boolean isVector(Drawable drawable) {
        return (drawable instanceof VectorDrawable) || (drawable instanceof VectorDrawableCompat);
    }

    public static final void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final Scale getScale(ImageView imageView) {
        ImageView.ScaleType scaleType = imageView.getScaleType();
        int i = scaleType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[scaleType.ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            return Scale.FIT;
        }
        return Scale.FILL;
    }

    public static final String getMimeTypeFromUrl(MimeTypeMap mimeTypeMap, String str) {
        CharSequence charSequence = str;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            return null;
        }
        return mimeTypeMap.getMimeTypeFromExtension(StringsKt.substringAfterLast(StringsKt.substringAfterLast$default(StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(str, '#', (String) null, 2, (Object) null), '?', (String) null, 2, (Object) null), (char) IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null), (char) FilenameUtils.EXTENSION_SEPARATOR, ""));
    }

    public static final String getFirstPathSegment(Uri uri) {
        return (String) CollectionsKt.firstOrNull(uri.getPathSegments());
    }

    public static final int getNightMode(Configuration configuration) {
        return configuration.uiMode & 48;
    }

    public static final Bitmap.Config[] getVALID_TRANSFORMATION_CONFIGS() {
        return VALID_TRANSFORMATION_CONFIGS;
    }

    public static final Bitmap.Config getDEFAULT_BITMAP_CONFIG() {
        return DEFAULT_BITMAP_CONFIG;
    }

    public static final ColorSpace getNULL_COLOR_SPACE() {
        return NULL_COLOR_SPACE;
    }

    public static final Headers getEMPTY_HEADERS() {
        return EMPTY_HEADERS;
    }

    public static final Headers orEmpty(Headers headers) {
        return headers == null ? EMPTY_HEADERS : headers;
    }

    public static final Tags orEmpty(Tags tags) {
        return tags == null ? Tags.EMPTY : tags;
    }

    public static final Parameters orEmpty(Parameters parameters) {
        return parameters == null ? Parameters.EMPTY : parameters;
    }

    public static final boolean isMainThread() {
        return Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper());
    }

    public static final int getIdentityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }

    public static final <T> T getCompletedOrNull(Deferred<? extends T> deferred) {
        try {
            return deferred.getCompleted();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final MemoryCache.Value get(MemoryCache memoryCache, MemoryCache.Key key) {
        if (key != null) {
            return memoryCache.get(key);
        }
        return null;
    }

    public static final File getSafeCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            cacheDir.mkdirs();
            return cacheDir;
        }
        throw new IllegalStateException("cacheDir == null".toString());
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, Pair<? extends Fetcher.Factory<?>, ? extends Class<?>> pair) {
        if (pair != null) {
            builder.getFetcherFactories$coil_base_release().add(0, pair);
        }
        return builder;
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, Decoder.Factory factory) {
        if (factory != null) {
            builder.getDecoderFactories$coil_base_release().add(0, factory);
        }
        return builder;
    }

    public static final int toNonNegativeInt(String str, int i) {
        Long longOrNull = StringsKt.toLongOrNull(str);
        if (longOrNull == null) {
            return i;
        }
        long longValue = longOrNull.longValue();
        if (longValue > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (longValue < 0) {
            return 0;
        }
        return (int) longValue;
    }

    public static final void abortQuietly(DiskCache.Editor editor) {
        try {
            editor.abort();
        } catch (Exception unused) {
        }
    }

    public static final boolean isPlaceholderCached(Interceptor.Chain chain) {
        return (chain instanceof RealInterceptorChain) && ((RealInterceptorChain) chain).isPlaceholderCached();
    }

    public static final EventListener getEventListener(Interceptor.Chain chain) {
        return chain instanceof RealInterceptorChain ? ((RealInterceptorChain) chain).getEventListener() : EventListener.NONE;
    }

    public static final int widthPx(Size size, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal(size) ? function0.invoke().intValue() : toPx(size.getWidth(), scale);
    }

    public static final int heightPx(Size size, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal(size) ? function0.invoke().intValue() : toPx(size.getHeight(), scale);
    }

    public static final Void unsupported() {
        throw new IllegalStateException("Unsupported".toString());
    }

    public static final boolean isAssetUri(Uri uri) {
        return Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file") && Intrinsics.areEqual((Object) getFirstPathSegment(uri), (Object) ASSET_FILE_PATH_ROOT);
    }

    public static final Headers.Builder addUnsafeNonAscii(Headers.Builder builder, String str) {
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            String substring = str.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String obj = StringsKt.trim((CharSequence) substring).toString();
            String substring2 = str.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            builder.addUnsafeNonAscii(obj, substring2);
            return builder;
        }
        throw new IllegalArgumentException(("Unexpected header: " + str).toString());
    }

    public static final ResponseBody requireBody(Response response) {
        ResponseBody body = response.body();
        if (body != null) {
            return body;
        }
        throw new IllegalStateException("response body == null".toString());
    }

    public static final int toPx(Dimension dimension, Scale scale) {
        if (dimension instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) dimension).px;
        }
        int i = WhenMappings.$EnumSwitchMapping$2[scale.ordinal()];
        if (i == 1) {
            return Integer.MIN_VALUE;
        }
        if (i == 2) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final double defaultMemoryCacheSizePercent(Context context) {
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            if (((ActivityManager) systemService).isLowRamDevice()) {
                return LOW_MEMORY_MULTIPLIER;
            }
            return STANDARD_MEMORY_MULTIPLIER;
        } catch (Exception unused) {
            return STANDARD_MEMORY_MULTIPLIER;
        }
    }

    public static final int calculateMemoryCacheSize(Context context, double d) {
        int i;
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            ActivityManager activityManager = (ActivityManager) systemService;
            i = (context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
        } catch (Exception unused) {
            i = 256;
        }
        double d2 = (double) 1024;
        return (int) (d * ((double) i) * d2 * d2);
    }
}
