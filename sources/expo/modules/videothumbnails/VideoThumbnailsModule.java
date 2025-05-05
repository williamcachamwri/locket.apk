package expo.modules.videothumbnails;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J!\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\b\u0004\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\bR\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lexpo/modules/videothumbnails/VideoThumbnailsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "moduleCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "isAllowedToRead", "", "url", "", "withModuleScope", "Lkotlinx/coroutines/Job;", "promise", "Lexpo/modules/kotlin/Promise;", "block", "Lkotlin/Function0;", "", "Companion", "GetThumbnail", "expo-video-thumbnails_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoThumbnailsModule.kt */
public final class VideoThumbnailsModule extends Module {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ERROR_TAG = "E_VIDEO_THUMBNAILS";
    private static final String TAG = "ExpoVideoThumbnails";
    /* access modifiers changed from: private */
    public final CoroutineScope moduleCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name(TAG);
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getThumbnail", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(VideoThumbnailOptions.class), false, VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$2.INSTANCE))}, new VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$3(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getThumbnail", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(VideoThumbnailOptions.class), false, VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$6.INSTANCE))}, new VideoThumbnailsModule$definition$lambda$4$$inlined$AsyncFunction$7(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getThumbnail", asyncFunction);
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new VideoThumbnailsModule$definition$lambda$4$$inlined$OnDestroy$1(this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lexpo/modules/videothumbnails/VideoThumbnailsModule$GetThumbnail;", "", "sourceFilename", "", "videoOptions", "Lexpo/modules/videothumbnails/VideoThumbnailOptions;", "context", "Landroid/content/Context;", "(Ljava/lang/String;Lexpo/modules/videothumbnails/VideoThumbnailOptions;Landroid/content/Context;)V", "execute", "Landroid/graphics/Bitmap;", "expo-video-thumbnails_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoThumbnailsModule.kt */
    private static final class GetThumbnail {
        private final Context context;
        private final String sourceFilename;
        private final VideoThumbnailOptions videoOptions;

        public GetThumbnail(String str, VideoThumbnailOptions videoThumbnailOptions, Context context2) {
            Intrinsics.checkNotNullParameter(str, "sourceFilename");
            Intrinsics.checkNotNullParameter(videoThumbnailOptions, "videoOptions");
            Intrinsics.checkNotNullParameter(context2, "context");
            this.sourceFilename = str;
            this.videoOptions = videoThumbnailOptions;
            this.context = context2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0066, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0067, code lost:
            kotlin.io.CloseableKt.closeFinally(r1, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
            throw r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final android.graphics.Bitmap execute() {
            /*
                r8 = this;
                android.media.MediaMetadataRetriever r0 = new android.media.MediaMetadataRetriever
                r0.<init>()
                java.lang.String r1 = r8.sourceFilename
                boolean r1 = android.webkit.URLUtil.isFileUrl(r1)
                if (r1 == 0) goto L_0x0027
                java.lang.String r1 = r8.sourceFilename
                java.lang.String r2 = android.net.Uri.decode(r1)
                java.lang.String r1 = "decode(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
                java.lang.String r3 = "file://"
                java.lang.String r4 = ""
                r5 = 0
                r6 = 4
                r7 = 0
                java.lang.String r1 = kotlin.text.StringsKt.replace$default((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (boolean) r5, (int) r6, (java.lang.Object) r7)
                r0.setDataSource(r1)
                goto L_0x0076
            L_0x0027:
                java.lang.String r1 = r8.sourceFilename
                boolean r1 = android.webkit.URLUtil.isContentUrl(r1)
                if (r1 == 0) goto L_0x006b
                java.lang.String r1 = r8.sourceFilename
                android.net.Uri r1 = android.net.Uri.parse(r1)
                android.content.Context r2 = r8.context
                android.content.ContentResolver r2 = r2.getContentResolver()
                java.lang.String r3 = "r"
                android.os.ParcelFileDescriptor r1 = r2.openFileDescriptor(r1, r3)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                java.io.FileDescriptor r1 = r1.getFileDescriptor()
                java.io.FileInputStream r2 = new java.io.FileInputStream
                r2.<init>(r1)
                java.io.FileInputStream r1 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r2, (java.io.FileDescriptor) r1)
                java.io.Closeable r1 = (java.io.Closeable) r1
                r2 = r1
                java.io.FileInputStream r2 = (java.io.FileInputStream) r2     // Catch:{ all -> 0x0064 }
                java.io.FileDescriptor r2 = r2.getFD()     // Catch:{ all -> 0x0064 }
                r0.setDataSource(r2)     // Catch:{ all -> 0x0064 }
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0064 }
                r2 = 0
                kotlin.io.CloseableKt.closeFinally(r1, r2)
                goto L_0x0076
            L_0x0064:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x0066 }
            L_0x0066:
                r2 = move-exception
                kotlin.io.CloseableKt.closeFinally(r1, r0)
                throw r2
            L_0x006b:
                java.lang.String r1 = r8.sourceFilename
                expo.modules.videothumbnails.VideoThumbnailOptions r2 = r8.videoOptions
                java.util.Map r2 = r2.getHeaders()
                r0.setDataSource(r1, r2)
            L_0x0076:
                expo.modules.videothumbnails.VideoThumbnailOptions r1 = r8.videoOptions
                int r1 = r1.getTime()
                long r1 = (long) r1
                r3 = 1000(0x3e8, float:1.401E-42)
                long r3 = (long) r3
                long r1 = r1 * r3
                r3 = 2
                android.graphics.Bitmap r0 = r0.getFrameAtTime(r1, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.videothumbnails.VideoThumbnailsModule.GetThumbnail.execute():android.graphics.Bitmap");
        }
    }

    /* access modifiers changed from: private */
    public final boolean isAllowedToRead(String str) {
        FilePermissionModuleInterface filePermission = getAppContext().getFilePermission();
        if (filePermission != null) {
            return filePermission.getPathPermissions(getContext(), str).contains(Permission.READ);
        }
        throw new FilePermissionsModuleNotFound();
    }

    private final Job withModuleScope(Promise promise, Function0<Unit> function0) {
        return BuildersKt__Builders_commonKt.launch$default(this.moduleCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new VideoThumbnailsModule$withModuleScope$1(function0, promise, (Continuation<? super VideoThumbnailsModule$withModuleScope$1>) null), 3, (Object) null);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lexpo/modules/videothumbnails/VideoThumbnailsModule$Companion;", "", "()V", "ERROR_TAG", "", "TAG", "expo-video-thumbnails_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoThumbnailsModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
