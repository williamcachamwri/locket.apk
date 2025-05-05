package expo.modules.videothumbnails;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "expo/modules/videothumbnails/VideoThumbnailsModule$withModuleScope$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.videothumbnails.VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1", f = "VideoThumbnailsModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoThumbnailsModule.kt */
public final class VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoThumbnailOptions $options$inlined;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ Promise $promise$inlined;
    final /* synthetic */ String $sourceFilename$inlined;
    int label;
    final /* synthetic */ VideoThumbnailsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1(Promise promise, Continuation continuation, String str, VideoThumbnailOptions videoThumbnailOptions, VideoThumbnailsModule videoThumbnailsModule, Promise promise2) {
        super(2, continuation);
        this.$promise = promise;
        this.$sourceFilename$inlined = str;
        this.$options$inlined = videoThumbnailOptions;
        this.this$0 = videoThumbnailsModule;
        this.$promise$inlined = promise2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1(this.$promise, continuation, this.$sourceFilename$inlined, this.$options$inlined, this.this$0, this.$promise$inlined);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0086, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.String r0 = "E_VIDEO_THUMBNAILS"
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            if (r1 != 0) goto L_0x00c1
            kotlin.ResultKt.throwOnFailure(r10)
            expo.modules.videothumbnails.VideoThumbnailsModule$GetThumbnail r10 = new expo.modules.videothumbnails.VideoThumbnailsModule$GetThumbnail     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            java.lang.String r1 = r9.$sourceFilename$inlined     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            expo.modules.videothumbnails.VideoThumbnailOptions r2 = r9.$options$inlined     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            expo.modules.videothumbnails.VideoThumbnailsModule r3 = r9.this$0     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            android.content.Context r3 = r3.getContext()     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            r10.<init>(r1, r2, r3)     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            android.graphics.Bitmap r10 = r10.execute()     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            if (r10 == 0) goto L_0x00a5
            expo.modules.videothumbnails.VideoThumbnailsModule r1 = r9.this$0     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            android.content.Context r1 = r1.getContext()     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.io.File r1 = r1.getCacheDir()     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.lang.String r2 = "VideoThumbnails"
            java.lang.String r3 = "jpg"
            java.lang.String r1 = expo.modules.core.utilities.FileUtilities.generateOutputPath(r1, r2, r3)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.io.FileOutputStream r2 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r2, (java.lang.String) r1)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            r3 = r2
            java.io.FileOutputStream r3 = (java.io.FileOutputStream) r3     // Catch:{ all -> 0x0084 }
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x0084 }
            expo.modules.videothumbnails.VideoThumbnailOptions r5 = r9.$options$inlined     // Catch:{ all -> 0x0084 }
            double r5 = r5.getQuality()     // Catch:{ all -> 0x0084 }
            r7 = 100
            double r7 = (double) r7     // Catch:{ all -> 0x0084 }
            double r5 = r5 * r7
            int r5 = (int) r5     // Catch:{ all -> 0x0084 }
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ all -> 0x0084 }
            r10.compress(r4, r5, r3)     // Catch:{ all -> 0x0084 }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r3)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            expo.modules.kotlin.Promise r2 = r9.$promise$inlined     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            expo.modules.videothumbnails.VideoThumbnailResult r3 = new expo.modules.videothumbnails.VideoThumbnailResult     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            r4.<init>(r1)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            android.net.Uri r1 = android.net.Uri.fromFile(r4)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.lang.String r4 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            int r4 = r10.getWidth()     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            int r10 = r10.getHeight()     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            r3.<init>(r1, r4, r10)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            r2.resolve(r3)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            goto L_0x00be
        L_0x0084:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0086 }
        L_0x0086:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r10)     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
            throw r1     // Catch:{ IOException -> 0x0098, RuntimeException -> 0x008b }
        L_0x008b:
            r10 = move-exception
            expo.modules.kotlin.Promise r1 = r9.$promise$inlined     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            java.lang.String r2 = r10.getMessage()     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            r1.reject(r0, r2, r10)     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            goto L_0x00be
        L_0x0098:
            r10 = move-exception
            expo.modules.kotlin.Promise r1 = r9.$promise$inlined     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            java.lang.String r2 = r10.getMessage()     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            r1.reject(r0, r2, r10)     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            goto L_0x00be
        L_0x00a5:
            expo.modules.videothumbnails.GenerateThumbnailException r10 = new expo.modules.videothumbnails.GenerateThumbnailException     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            r10.<init>()     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
            throw r10     // Catch:{ CodedException -> 0x00b8, ModuleDestroyedException -> 0x00ab }
        L_0x00ab:
            r10 = move-exception
            expo.modules.kotlin.Promise r0 = r9.$promise
            java.lang.String r1 = "VideoThumbnails module destroyed"
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.String r2 = "ExpoVideoThumbnails"
            r0.reject(r2, r1, r10)
            goto L_0x00be
        L_0x00b8:
            r10 = move-exception
            expo.modules.kotlin.Promise r0 = r9.$promise
            r0.reject(r10)
        L_0x00be:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00c1:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.videothumbnails.VideoThumbnailsModule$definition$lambda$4$lambda$2$$inlined$withModuleScope$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
