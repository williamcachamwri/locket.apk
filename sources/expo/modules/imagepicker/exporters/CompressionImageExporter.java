package expo.modules.imagepicker.exporters;

import android.graphics.Bitmap;
import expo.modules.kotlin.providers.AppContextProvider;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InterruptibleKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH@¢\u0006\u0002\u0010\u0014J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H@¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lexpo/modules/imagepicker/exporters/CompressionImageExporter;", "Lexpo/modules/imagepicker/exporters/ImageExporter;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "quality", "", "(Lexpo/modules/kotlin/providers/AppContextProvider;D)V", "compressQuality", "", "exportAsync", "Lexpo/modules/imagepicker/exporters/ImageExportResult;", "source", "Landroid/net/Uri;", "output", "Ljava/io/File;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/net/Uri;Ljava/io/File;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBitmap", "Landroid/graphics/Bitmap;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeImage", "", "bitmap", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "(Landroid/graphics/Bitmap;Ljava/io/File;Landroid/graphics/Bitmap$CompressFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CompressionImageExporter.kt */
public final class CompressionImageExporter implements ImageExporter {
    /* access modifiers changed from: private */
    public final AppContextProvider appContextProvider;
    /* access modifiers changed from: private */
    public final int compressQuality;

    public CompressionImageExporter(AppContextProvider appContextProvider2, double d) {
        Intrinsics.checkNotNullParameter(appContextProvider2, "appContextProvider");
        this.appContextProvider = appContextProvider2;
        this.compressQuality = (int) (d * ((double) 100));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: android.content.ContentResolver} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.io.File} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object exportAsync(android.net.Uri r11, java.io.File r12, android.content.ContentResolver r13, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.exporters.ImageExportResult> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$1 r0 = (expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$1 r0 = new expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$1
            r0.<init>(r10, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x007b
            if (r2 == r5) goto L_0x0065
            if (r2 == r4) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r11 = r0.L$2
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            java.lang.Object r12 = r0.L$1
            java.io.File r12 = (java.io.File) r12
            java.lang.Object r13 = r0.L$0
            expo.modules.imagepicker.exporters.CompressionImageExporter r13 = (expo.modules.imagepicker.exporters.CompressionImageExporter) r13
            kotlin.ResultKt.throwOnFailure(r14)
            r5 = r11
            r4 = r12
            r6 = r13
            goto L_0x00c3
        L_0x0040:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0048:
            java.lang.Object r11 = r0.L$4
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            java.lang.Object r12 = r0.L$3
            android.content.ContentResolver r12 = (android.content.ContentResolver) r12
            java.lang.Object r13 = r0.L$2
            java.io.File r13 = (java.io.File) r13
            java.lang.Object r2 = r0.L$1
            android.net.Uri r2 = (android.net.Uri) r2
            java.lang.Object r4 = r0.L$0
            expo.modules.imagepicker.exporters.CompressionImageExporter r4 = (expo.modules.imagepicker.exporters.CompressionImageExporter) r4
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r2
            r2 = r4
            r9 = r13
            r13 = r12
            r12 = r9
            goto L_0x00ac
        L_0x0065:
            java.lang.Object r11 = r0.L$3
            r13 = r11
            android.content.ContentResolver r13 = (android.content.ContentResolver) r13
            java.lang.Object r11 = r0.L$2
            r12 = r11
            java.io.File r12 = (java.io.File) r12
            java.lang.Object r11 = r0.L$1
            android.net.Uri r11 = (android.net.Uri) r11
            java.lang.Object r2 = r0.L$0
            expo.modules.imagepicker.exporters.CompressionImageExporter r2 = (expo.modules.imagepicker.exporters.CompressionImageExporter) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0090
        L_0x007b:
            kotlin.ResultKt.throwOnFailure(r14)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r13
            r0.label = r5
            java.lang.Object r14 = r10.readBitmap(r11, r0)
            if (r14 != r1) goto L_0x008f
            return r1
        L_0x008f:
            r2 = r10
        L_0x0090:
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14
            android.graphics.Bitmap$CompressFormat r5 = expo.modules.imagepicker.ImagePickerUtilsKt.toBitmapCompressFormat((java.io.File) r12)
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r13
            r0.L$4 = r14
            r0.label = r4
            java.lang.Object r4 = r2.writeImage(r14, r12, r5, r0)
            if (r4 != r1) goto L_0x00a9
            return r1
        L_0x00a9:
            r9 = r14
            r14 = r11
            r11 = r9
        L_0x00ac:
            r0.L$0 = r2
            r0.L$1 = r12
            r0.L$2 = r11
            r4 = 0
            r0.L$3 = r4
            r0.L$4 = r4
            r0.label = r3
            java.lang.Object r13 = expo.modules.imagepicker.ImagePickerUtilsKt.copyExifData(r14, r12, r13, r0)
            if (r13 != r1) goto L_0x00c0
            return r1
        L_0x00c0:
            r5 = r11
            r4 = r12
            r6 = r2
        L_0x00c3:
            int r7 = r5.getWidth()
            int r8 = r5.getHeight()
            expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$2 r11 = new expo.modules.imagepicker.exporters.CompressionImageExporter$exportAsync$2
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.CompressionImageExporter.exportAsync(android.net.Uri, java.io.File, android.content.ContentResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readBitmap(android.net.Uri r5, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1 r0 = (expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1 r0 = new expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0046
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$2 r6 = new expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$2
            r6.<init>(r4, r5)
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            r0.label = r3
            r5 = 0
            java.lang.Object r6 = kotlinx.coroutines.InterruptibleKt.runInterruptible$default(r5, r6, r0, r3, r5)
            if (r6 != r1) goto L_0x0046
            return r1
        L_0x0046:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.CompressionImageExporter.readBitmap(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object writeImage(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, Continuation<? super Boolean> continuation) {
        return InterruptibleKt.runInterruptible$default((CoroutineContext) null, new CompressionImageExporter$writeImage$2(file, bitmap, compressFormat, this), continuation, 1, (Object) null);
    }
}
