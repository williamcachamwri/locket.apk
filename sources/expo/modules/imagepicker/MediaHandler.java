package expo.modules.imagepicker;

import android.content.Context;
import expo.modules.kotlin.providers.AppContextProvider;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0018J2\u0010\u0019\u001a\u00020\u001a2\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100\u001d0\u001c2\u0006\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0004\b\u001f\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006!"}, d2 = {"Lexpo/modules/imagepicker/MediaHandler;", "", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "getAdditionalFileData", "Lexpo/modules/imagepicker/AdditionalFileData;", "uri", "Landroid/net/Uri;", "handleImage", "Lexpo/modules/imagepicker/ImagePickerAsset;", "sourceUri", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "(Landroid/net/Uri;Lexpo/modules/imagepicker/ImagePickerOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleVideo", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readExtras", "Lexpo/modules/imagepicker/ImagePickerResponse;", "bareResult", "", "Lkotlin/Pair;", "Lexpo/modules/imagepicker/MediaType;", "readExtras$expo_image_picker_release", "(Ljava/util/List;Lexpo/modules/imagepicker/ImagePickerOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaHandler.kt */
public final class MediaHandler {
    private final AppContextProvider appContextProvider;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaHandler.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.imagepicker.MediaType[] r0 = expo.modules.imagepicker.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.imagepicker.MediaType r1 = expo.modules.imagepicker.MediaType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.imagepicker.MediaType r1 = expo.modules.imagepicker.MediaType.IMAGE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.WhenMappings.<clinit>():void");
        }
    }

    public MediaHandler(AppContextProvider appContextProvider2) {
        Intrinsics.checkNotNullParameter(appContextProvider2, "appContextProvider");
        this.appContextProvider = appContextProvider2;
    }

    private final Context getContext() {
        Context reactContext = this.appContextProvider.getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new IllegalArgumentException("React Application Context is null".toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readExtras$expo_image_picker_release(java.util.List<? extends kotlin.Pair<? extends expo.modules.imagepicker.MediaType, ? extends android.net.Uri>> r10, expo.modules.imagepicker.ImagePickerOptions r11, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerResponse> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof expo.modules.imagepicker.MediaHandler$readExtras$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            expo.modules.imagepicker.MediaHandler$readExtras$1 r0 = (expo.modules.imagepicker.MediaHandler$readExtras$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            expo.modules.imagepicker.MediaHandler$readExtras$1 r0 = new expo.modules.imagepicker.MediaHandler$readExtras$1
            r0.<init>(r9, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 == r4) goto L_0x004a
            if (r2 != r3) goto L_0x0042
            java.lang.Object r10 = r0.L$4
            java.util.Collection r10 = (java.util.Collection) r10
            java.lang.Object r11 = r0.L$3
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r2 = r0.L$2
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.Object r5 = r0.L$1
            expo.modules.imagepicker.ImagePickerOptions r5 = (expo.modules.imagepicker.ImagePickerOptions) r5
            java.lang.Object r6 = r0.L$0
            expo.modules.imagepicker.MediaHandler r6 = (expo.modules.imagepicker.MediaHandler) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00b8
        L_0x0042:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004a:
            java.lang.Object r10 = r0.L$4
            java.util.Collection r10 = (java.util.Collection) r10
            java.lang.Object r11 = r0.L$3
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r2 = r0.L$2
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.Object r5 = r0.L$1
            expo.modules.imagepicker.ImagePickerOptions r5 = (expo.modules.imagepicker.ImagePickerOptions) r5
            java.lang.Object r6 = r0.L$0
            expo.modules.imagepicker.MediaHandler r6 = (expo.modules.imagepicker.MediaHandler) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00d7
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r12 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r10, r2)
            r12.<init>(r2)
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.Iterator r10 = r10.iterator()
            r6 = r9
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
        L_0x007e:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L_0x00df
            java.lang.Object r2 = r11.next()
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r5 = r2.component1()
            expo.modules.imagepicker.MediaType r5 = (expo.modules.imagepicker.MediaType) r5
            java.lang.Object r2 = r2.component2()
            android.net.Uri r2 = (android.net.Uri) r2
            int[] r7 = expo.modules.imagepicker.MediaHandler.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r5 = r7[r5]
            if (r5 == r4) goto L_0x00c1
            if (r5 != r3) goto L_0x00bb
            r0.L$0 = r6
            r0.L$1 = r12
            r0.L$2 = r10
            r0.L$3 = r11
            r0.L$4 = r10
            r0.label = r3
            java.lang.Object r2 = r6.handleImage(r2, r12, r0)
            if (r2 != r1) goto L_0x00b5
            return r1
        L_0x00b5:
            r5 = r12
            r12 = r2
            r2 = r10
        L_0x00b8:
            expo.modules.imagepicker.ImagePickerAsset r12 = (expo.modules.imagepicker.ImagePickerAsset) r12
            goto L_0x00d9
        L_0x00bb:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        L_0x00c1:
            r0.L$0 = r6
            r0.L$1 = r12
            r0.L$2 = r10
            r0.L$3 = r11
            r0.L$4 = r10
            r0.label = r4
            java.lang.Object r2 = r6.handleVideo(r2, r0)
            if (r2 != r1) goto L_0x00d4
            return r1
        L_0x00d4:
            r5 = r12
            r12 = r2
            r2 = r10
        L_0x00d7:
            expo.modules.imagepicker.ImagePickerAsset r12 = (expo.modules.imagepicker.ImagePickerAsset) r12
        L_0x00d9:
            r10.add(r12)
            r10 = r2
            r12 = r5
            goto L_0x007e
        L_0x00df:
            java.util.List r10 = (java.util.List) r10
            expo.modules.imagepicker.ImagePickerResponse r11 = new expo.modules.imagepicker.ImagePickerResponse
            r12 = 0
            r11.<init>(r12, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.readExtras$expo_image_picker_release(java.util.List, expo.modules.imagepicker.ImagePickerOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final File getCacheDirectory() {
        return this.appContextProvider.getAppContext().getCacheDirectory();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleImage(android.net.Uri r28, expo.modules.imagepicker.ImagePickerOptions r29, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerAsset> r30) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            r2 = r30
            boolean r3 = r2 instanceof expo.modules.imagepicker.MediaHandler$handleImage$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            expo.modules.imagepicker.MediaHandler$handleImage$1 r3 = (expo.modules.imagepicker.MediaHandler$handleImage$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            expo.modules.imagepicker.MediaHandler$handleImage$1 r3 = new expo.modules.imagepicker.MediaHandler$handleImage$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 3
            r7 = 2
            r8 = 1
            java.lang.String r9 = "getContentResolver(...)"
            r10 = 0
            if (r5 == 0) goto L_0x0093
            if (r5 == r8) goto L_0x0077
            if (r5 == r7) goto L_0x005a
            if (r5 != r6) goto L_0x0052
            java.lang.Object r1 = r3.L$5
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r4 = r3.L$4
            expo.modules.imagepicker.exporters.ImageExportResult r4 = (expo.modules.imagepicker.exporters.ImageExportResult) r4
            java.lang.Object r5 = r3.L$3
            java.io.File r5 = (java.io.File) r5
            java.lang.Object r6 = r3.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r3.L$1
            android.net.Uri r7 = (android.net.Uri) r7
            java.lang.Object r3 = r3.L$0
            expo.modules.imagepicker.MediaHandler r3 = (expo.modules.imagepicker.MediaHandler) r3
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x018b
        L_0x0052:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x005a:
            java.lang.Object r1 = r3.L$5
            expo.modules.imagepicker.exporters.ImageExportResult r1 = (expo.modules.imagepicker.exporters.ImageExportResult) r1
            java.lang.Object r5 = r3.L$4
            java.io.File r5 = (java.io.File) r5
            java.lang.Object r8 = r3.L$3
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r11 = r3.L$2
            expo.modules.imagepicker.ImagePickerOptions r11 = (expo.modules.imagepicker.ImagePickerOptions) r11
            java.lang.Object r12 = r3.L$1
            android.net.Uri r12 = (android.net.Uri) r12
            java.lang.Object r13 = r3.L$0
            expo.modules.imagepicker.MediaHandler r13 = (expo.modules.imagepicker.MediaHandler) r13
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0137
        L_0x0077:
            java.lang.Object r1 = r3.L$4
            java.io.File r1 = (java.io.File) r1
            java.lang.Object r5 = r3.L$3
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r8 = r3.L$2
            expo.modules.imagepicker.ImagePickerOptions r8 = (expo.modules.imagepicker.ImagePickerOptions) r8
            java.lang.Object r11 = r3.L$1
            android.net.Uri r11 = (android.net.Uri) r11
            java.lang.Object r12 = r3.L$0
            expo.modules.imagepicker.MediaHandler r12 = (expo.modules.imagepicker.MediaHandler) r12
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r8
            r8 = r5
            r5 = r1
            r1 = r11
            goto L_0x00f8
        L_0x0093:
            kotlin.ResultKt.throwOnFailure(r2)
            double r11 = r29.getQuality()
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x00a2
            r2 = r8
            goto L_0x00a3
        L_0x00a2:
            r2 = 0
        L_0x00a3:
            if (r2 == 0) goto L_0x00ad
            expo.modules.imagepicker.exporters.RawImageExporter r2 = new expo.modules.imagepicker.exporters.RawImageExporter
            r2.<init>()
            expo.modules.imagepicker.exporters.ImageExporter r2 = (expo.modules.imagepicker.exporters.ImageExporter) r2
            goto L_0x00ba
        L_0x00ad:
            expo.modules.imagepicker.exporters.CompressionImageExporter r2 = new expo.modules.imagepicker.exporters.CompressionImageExporter
            expo.modules.kotlin.providers.AppContextProvider r5 = r0.appContextProvider
            double r11 = r29.getQuality()
            r2.<init>(r5, r11)
            expo.modules.imagepicker.exporters.ImageExporter r2 = (expo.modules.imagepicker.exporters.ImageExporter) r2
        L_0x00ba:
            android.content.Context r5 = r27.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r9)
            java.lang.String r5 = expo.modules.imagepicker.ImagePickerUtilsKt.getType(r5, r1)
            java.io.File r11 = r27.getCacheDirectory()
            java.lang.String r12 = expo.modules.imagepicker.ImagePickerUtilsKt.toImageFileExtension((java.lang.String) r5)
            java.io.File r11 = expo.modules.imagepicker.ImagePickerUtilsKt.createOutputFile(r11, r12)
            android.content.Context r12 = r27.getContext()
            android.content.ContentResolver r12 = r12.getContentResolver()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r9)
            r3.L$0 = r0
            r3.L$1 = r1
            r13 = r29
            r3.L$2 = r13
            r3.L$3 = r5
            r3.L$4 = r11
            r3.label = r8
            java.lang.Object r2 = r2.exportAsync(r1, r11, r12, r3)
            if (r2 != r4) goto L_0x00f5
            return r4
        L_0x00f5:
            r12 = r0
            r8 = r5
            r5 = r11
        L_0x00f8:
            expo.modules.imagepicker.exporters.ImageExportResult r2 = (expo.modules.imagepicker.exporters.ImageExportResult) r2
            boolean r11 = r13.getBase64()
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            boolean r14 = r11.booleanValue()
            if (r14 == 0) goto L_0x0109
            goto L_0x010a
        L_0x0109:
            r11 = r10
        L_0x010a:
            if (r11 == 0) goto L_0x014f
            r11.booleanValue()
            android.content.Context r11 = r12.getContext()
            android.content.ContentResolver r11 = r11.getContentResolver()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r9)
            r3.L$0 = r12
            r3.L$1 = r1
            r3.L$2 = r13
            r3.L$3 = r8
            r3.L$4 = r5
            r3.L$5 = r2
            r3.label = r7
            java.lang.Object r11 = r2.data(r11, r3)
            if (r11 != r4) goto L_0x012f
            return r4
        L_0x012f:
            r26 = r12
            r12 = r1
            r1 = r2
            r2 = r11
            r11 = r13
            r13 = r26
        L_0x0137:
            java.io.ByteArrayOutputStream r2 = (java.io.ByteArrayOutputStream) r2
            if (r2 == 0) goto L_0x014b
            byte[] r2 = r2.toByteArray()
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r7)
            r7 = r12
            r12 = r13
            r26 = r2
            r2 = r1
            r1 = r26
            goto L_0x0152
        L_0x014b:
            r2 = r1
            r1 = r12
            r12 = r13
            r13 = r11
        L_0x014f:
            r7 = r1
            r1 = r10
            r11 = r13
        L_0x0152:
            boolean r11 = r11.getExif()
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            boolean r13 = r11.booleanValue()
            if (r13 == 0) goto L_0x0161
            goto L_0x0162
        L_0x0161:
            r11 = r10
        L_0x0162:
            if (r11 == 0) goto L_0x0196
            r11.booleanValue()
            android.content.Context r11 = r12.getContext()
            android.content.ContentResolver r11 = r11.getContentResolver()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r9)
            r3.L$0 = r12
            r3.L$1 = r7
            r3.L$2 = r8
            r3.L$3 = r5
            r3.L$4 = r2
            r3.L$5 = r1
            r3.label = r6
            java.lang.Object r3 = r2.exif(r11, r3)
            if (r3 != r4) goto L_0x0187
            return r4
        L_0x0187:
            r4 = r2
            r2 = r3
            r6 = r8
            r3 = r12
        L_0x018b:
            android.os.Bundle r2 = (android.os.Bundle) r2
            r20 = r1
            r21 = r2
            r12 = r3
            r2 = r4
            r19 = r6
            goto L_0x019c
        L_0x0196:
            r20 = r1
            r19 = r8
            r21 = r10
        L_0x019c:
            expo.modules.imagepicker.AdditionalFileData r1 = r12.getAdditionalFileData(r7)
            expo.modules.imagepicker.MediaType r13 = expo.modules.imagepicker.MediaType.IMAGE
            android.net.Uri r3 = android.net.Uri.fromFile(r5)
            java.lang.String r14 = r3.toString()
            int r15 = r2.getWidth()
            int r16 = r2.getHeight()
            if (r1 == 0) goto L_0x01bb
            java.lang.String r2 = r1.getFileName()
            r17 = r2
            goto L_0x01bd
        L_0x01bb:
            r17 = r10
        L_0x01bd:
            if (r1 == 0) goto L_0x01c3
            java.lang.Long r10 = r1.getFilesize()
        L_0x01c3:
            r18 = r10
            java.lang.String r12 = expo.modules.imagepicker.ImagePickerUtilsKt.getMediaStoreAssetId(r7)
            expo.modules.imagepicker.ImagePickerAsset r1 = new expo.modules.imagepicker.ImagePickerAsset
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r22 = 0
            r23 = 0
            r24 = 3072(0xc00, float:4.305E-42)
            r25 = 0
            r11 = r1
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.handleImage(android.net.Uri, expo.modules.imagepicker.ImagePickerOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0041, code lost:
        kotlin.io.CloseableKt.closeFinally(r8, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0040, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final expo.modules.imagepicker.AdditionalFileData getAdditionalFileData(android.net.Uri r8) {
        /*
            r7 = this;
            android.content.Context r0 = r7.getContext()
            android.content.ContentResolver r1 = r0.getContentResolver()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r8
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            r0 = 0
            if (r8 == 0) goto L_0x0045
            java.io.Closeable r8 = (java.io.Closeable) r8
            r1 = r8
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "_display_name"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "_size"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ all -> 0x003e }
            r1.moveToFirst()     // Catch:{ all -> 0x003e }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ all -> 0x003e }
            long r3 = r1.getLong(r3)     // Catch:{ all -> 0x003e }
            expo.modules.imagepicker.AdditionalFileData r1 = new expo.modules.imagepicker.AdditionalFileData     // Catch:{ all -> 0x003e }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x003e }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x003e }
            kotlin.io.CloseableKt.closeFinally(r8, r0)
            r0 = r1
            goto L_0x0045
        L_0x003e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r8, r0)
            throw r1
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.getAdditionalFileData(android.net.Uri):expo.modules.imagepicker.AdditionalFileData");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a8 A[Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ae A[Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b1 A[Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleVideo(android.net.Uri r24, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerAsset> r25) {
        /*
            r23 = this;
            r1 = r23
            r0 = r24
            r2 = r25
            boolean r3 = r2 instanceof expo.modules.imagepicker.MediaHandler$handleVideo$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            expo.modules.imagepicker.MediaHandler$handleVideo$1 r3 = (expo.modules.imagepicker.MediaHandler$handleVideo$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            expo.modules.imagepicker.MediaHandler$handleVideo$1 r3 = new expo.modules.imagepicker.MediaHandler$handleVideo$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            java.lang.String r6 = "getContentResolver(...)"
            r7 = 1
            if (r5 == 0) goto L_0x0048
            if (r5 != r7) goto L_0x0040
            java.lang.Object r0 = r3.L$2
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r4 = r3.L$1
            android.net.Uri r4 = (android.net.Uri) r4
            java.lang.Object r3 = r3.L$0
            expo.modules.imagepicker.MediaHandler r3 = (expo.modules.imagepicker.MediaHandler) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r4
            goto L_0x0070
        L_0x0040:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r2)
            java.io.File r2 = r23.getCacheDirectory()
            java.lang.String r5 = ".mp4"
            java.io.File r2 = expo.modules.imagepicker.ImagePickerUtilsKt.createOutputFile(r2, r5)
            android.content.Context r5 = r23.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r3.L$0 = r1
            r3.L$1 = r0
            r3.L$2 = r2
            r3.label = r7
            java.lang.Object r3 = expo.modules.imagepicker.ImagePickerUtilsKt.copyFile(r0, r2, r5, r3)
            if (r3 != r4) goto L_0x006f
            return r4
        L_0x006f:
            r3 = r1
        L_0x0070:
            android.net.Uri r4 = android.net.Uri.fromFile(r2)
            android.media.MediaMetadataRetriever r5 = new android.media.MediaMetadataRetriever     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r5.<init>()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            android.content.Context r7 = r3.getContext()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r5.setDataSource(r7, r4)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            expo.modules.imagepicker.AdditionalFileData r7 = r3.getAdditionalFileData(r0)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            android.content.Context r3 = r3.getContext()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            java.lang.String r16 = expo.modules.imagepicker.ImagePickerUtilsKt.getType(r3, r0)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            expo.modules.imagepicker.MediaType r10 = expo.modules.imagepicker.MediaType.VIDEO     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            java.lang.String r11 = r4.toString()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r3 = 18
            int r12 = expo.modules.imagepicker.ImagePickerUtilsKt.extractInt(r5, r3)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r3 = 19
            int r13 = expo.modules.imagepicker.ImagePickerUtilsKt.extractInt(r5, r3)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r3 = 0
            if (r7 == 0) goto L_0x00ae
            java.lang.String r4 = r7.getFileName()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r14 = r4
            goto L_0x00af
        L_0x00ae:
            r14 = r3
        L_0x00af:
            if (r7 == 0) goto L_0x00b5
            java.lang.Long r3 = r7.getFilesize()     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
        L_0x00b5:
            r15 = r3
            r3 = 9
            int r3 = expo.modules.imagepicker.ImagePickerUtilsKt.extractInt(r5, r3)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r4 = 24
            int r4 = expo.modules.imagepicker.ImagePickerUtilsKt.extractInt(r5, r4)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            java.lang.String r9 = expo.modules.imagepicker.ImagePickerUtilsKt.getMediaStoreAssetId(r0)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            expo.modules.imagepicker.ImagePickerAsset r0 = new expo.modules.imagepicker.ImagePickerAsset     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r17 = 0
            r18 = 0
            java.lang.Integer r19 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            java.lang.Integer r20 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            r21 = 768(0x300, float:1.076E-42)
            r22 = 0
            r8 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ FailedToExtractVideoMetadataException -> 0x00e0 }
            return r0
        L_0x00e0:
            r0 = move-exception
            expo.modules.imagepicker.FailedToExtractVideoMetadataException r3 = new expo.modules.imagepicker.FailedToExtractVideoMetadataException
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r3.<init>(r2, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.handleVideo(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
