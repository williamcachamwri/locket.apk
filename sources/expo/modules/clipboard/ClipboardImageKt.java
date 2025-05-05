package expo.modules.clipboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.InterruptibleKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\t\u001a&\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH@¢\u0006\u0002\u0010\u000e\u001a&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0013\u001a\u0012\u0010\u0014\u001a\u00020\u0015*\u00020\rH@¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"bitmapFromBase64String", "Landroid/graphics/Bitmap;", "base64Image", "", "bitmapFromContentUriAsync", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clipDataFromBase64Image", "Landroid/content/ClipData;", "clipboardCacheDir", "Ljava/io/File;", "(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imageFromContentUri", "Lexpo/modules/clipboard/ImageResult;", "options", "Lexpo/modules/clipboard/GetImageOptions;", "(Landroid/content/Context;Landroid/net/Uri;Lexpo/modules/clipboard/GetImageOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureExists", "", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-clipboard_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardImage.kt */
public final class ClipboardImageKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: expo.modules.clipboard.GetImageOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0085 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object imageFromContentUri(android.content.Context r6, android.net.Uri r7, expo.modules.clipboard.GetImageOptions r8, kotlin.coroutines.Continuation<? super expo.modules.clipboard.ImageResult> r9) {
        /*
            boolean r0 = r9 instanceof expo.modules.clipboard.ClipboardImageKt$imageFromContentUri$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            expo.modules.clipboard.ClipboardImageKt$imageFromContentUri$1 r0 = (expo.modules.clipboard.ClipboardImageKt$imageFromContentUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            expo.modules.clipboard.ClipboardImageKt$imageFromContentUri$1 r0 = new expo.modules.clipboard.ClipboardImageKt$imageFromContentUri$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r6 = r0.L$2
            java.io.ByteArrayOutputStream r6 = (java.io.ByteArrayOutputStream) r6
            java.lang.Object r7 = r0.L$1
            expo.modules.clipboard.ImageFormat r7 = (expo.modules.clipboard.ImageFormat) r7
            java.lang.Object r8 = r0.L$0
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0088
        L_0x0039:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0041:
            java.lang.Object r6 = r0.L$0
            r8 = r6
            expo.modules.clipboard.GetImageOptions r8 = (expo.modules.clipboard.GetImageOptions) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0058
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r9 = bitmapFromContentUriAsync(r6, r7, r0)
            if (r9 != r1) goto L_0x0058
            return r1
        L_0x0058:
            r6 = r9
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            expo.modules.clipboard.ImageFormat r7 = r8.getImageFormat()
            double r8 = r8.getJpegQuality()
            r2 = 100
            double r4 = (double) r2
            double r8 = r8 * r4
            int r8 = (int) r8
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream
            r9.<init>()
            android.graphics.Bitmap$CompressFormat r2 = r7.getCompressFormat()
            r4 = r9
            java.io.OutputStream r4 = (java.io.OutputStream) r4
            r6.compress(r2, r8, r4)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r9
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r8 != r1) goto L_0x0086
            return r1
        L_0x0086:
            r8 = r6
            r6 = r9
        L_0x0088:
            byte[] r6 = r6.toByteArray()
            r9 = 0
            java.lang.String r6 = android.util.Base64.encodeToString(r6, r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r7 = r7.getMimeType()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "data:"
            r0.<init>(r1)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r0 = ";base64,"
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            r9.<init>(r7)
            java.lang.StringBuilder r6 = r9.append(r6)
            expo.modules.clipboard.ImageResult r7 = new expo.modules.clipboard.ImageResult
            java.lang.String r6 = r6.toString()
            java.lang.String r9 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r9)
            int r9 = r8.getWidth()
            int r8 = r8.getHeight()
            r7.<init>(r6, r9, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.ClipboardImageKt.imageFromContentUri(android.content.Context, android.net.Uri, expo.modules.clipboard.GetImageOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cc A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object clipDataFromBase64Image(android.content.Context r9, java.lang.String r10, java.io.File r11, kotlin.coroutines.Continuation<? super android.content.ClipData> r12) {
        /*
            boolean r0 = r12 instanceof expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$1 r0 = (expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$1 r0 = new expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0069
            if (r2 == r5) goto L_0x0059
            if (r2 == r4) goto L_0x0049
            if (r2 != r3) goto L_0x0041
            java.lang.Object r9 = r0.L$2
            java.io.Closeable r9 = (java.io.Closeable) r9
            java.lang.Object r10 = r0.L$1
            java.io.File r10 = (java.io.File) r10
            java.lang.Object r11 = r0.L$0
            android.content.Context r11 = (android.content.Context) r11
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003e }
            goto L_0x00cf
        L_0x003e:
            r10 = move-exception
            goto L_0x0107
        L_0x0041:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0049:
            java.lang.Object r9 = r0.L$2
            java.io.File r9 = (java.io.File) r9
            java.lang.Object r10 = r0.L$1
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r11 = r0.L$0
            android.content.Context r11 = (android.content.Context) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x009e
        L_0x0059:
            java.lang.Object r9 = r0.L$2
            java.io.File r9 = (java.io.File) r9
            java.lang.Object r10 = r0.L$1
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r11 = r0.L$0
            android.content.Context r11 = (android.content.Context) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0088
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r12)
            android.graphics.Bitmap r10 = bitmapFromBase64String(r10)
            java.io.File r12 = new java.io.File
            java.lang.String r2 = "copied_image.jpeg"
            r12.<init>(r11, r2)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r12
            r0.label = r5
            java.lang.Object r11 = ensureExists(r12, r0)
            if (r11 != r1) goto L_0x0086
            return r1
        L_0x0086:
            r11 = r9
            r9 = r12
        L_0x0088:
            expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$fileStream$1 r12 = new expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$fileStream$1
            r12.<init>(r9)
            kotlin.jvm.functions.Function0 r12 = (kotlin.jvm.functions.Function0) r12
            r0.L$0 = r11
            r0.L$1 = r10
            r0.L$2 = r9
            r0.label = r4
            java.lang.Object r12 = kotlinx.coroutines.InterruptibleKt.runInterruptible$default(r6, r12, r0, r5, r6)
            if (r12 != r1) goto L_0x009e
            return r1
        L_0x009e:
            java.io.FileOutputStream r12 = (java.io.FileOutputStream) r12
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream
            java.io.OutputStream r12 = (java.io.OutputStream) r12
            r2.<init>(r12)
            r12 = r2
            java.io.Closeable r12 = (java.io.Closeable) r12
            r2 = r12
            java.io.BufferedOutputStream r2 = (java.io.BufferedOutputStream) r2     // Catch:{ all -> 0x0105 }
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x0105 }
            r7 = r2
            java.io.OutputStream r7 = (java.io.OutputStream) r7     // Catch:{ all -> 0x0105 }
            r8 = 100
            r10.compress(r4, r8, r7)     // Catch:{ all -> 0x0105 }
            expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$2$1 r10 = new expo.modules.clipboard.ClipboardImageKt$clipDataFromBase64Image$2$1     // Catch:{ all -> 0x0105 }
            r10.<init>(r2)     // Catch:{ all -> 0x0105 }
            kotlin.jvm.functions.Function0 r10 = (kotlin.jvm.functions.Function0) r10     // Catch:{ all -> 0x0105 }
            r0.L$0 = r11     // Catch:{ all -> 0x0105 }
            r0.L$1 = r9     // Catch:{ all -> 0x0105 }
            r0.L$2 = r12     // Catch:{ all -> 0x0105 }
            r0.label = r3     // Catch:{ all -> 0x0105 }
            java.lang.Object r10 = kotlinx.coroutines.InterruptibleKt.runInterruptible$default(r6, r10, r0, r5, r6)     // Catch:{ all -> 0x0105 }
            if (r10 != r1) goto L_0x00cd
            return r1
        L_0x00cd:
            r10 = r9
            r9 = r12
        L_0x00cf:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003e }
            kotlin.io.CloseableKt.closeFinally(r9, r6)
            expo.modules.clipboard.ClipboardFileProvider$Companion r9 = expo.modules.clipboard.ClipboardFileProvider.Companion
            android.content.pm.ApplicationInfo r12 = r11.getApplicationInfo()
            java.lang.String r12 = r12.packageName
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r12 = r0.append(r12)
            java.lang.String r0 = ".ClipboardFileProvider"
            java.lang.StringBuilder r12 = r12.append(r0)
            java.lang.String r12 = r12.toString()
            android.net.Uri r9 = r9.getUriForFile(r11, r12, r10)
            android.content.ContentResolver r10 = r11.getContentResolver()
            java.lang.String r11 = "image"
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            android.content.ClipData r9 = android.content.ClipData.newUri(r10, r11, r9)
            java.lang.String r10 = "newUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            return r9
        L_0x0105:
            r10 = move-exception
            r9 = r12
        L_0x0107:
            throw r10     // Catch:{ all -> 0x0108 }
        L_0x0108:
            r11 = move-exception
            kotlin.io.CloseableKt.closeFinally(r9, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.ClipboardImageKt.clipDataFromBase64Image(android.content.Context, java.lang.String, java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object bitmapFromContentUriAsync(android.content.Context r4, android.net.Uri r5, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r6) {
        /*
            boolean r0 = r6 instanceof expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$1 r0 = (expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$1 r0 = new expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004b
        L_0x002a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$2 r2 = new expo.modules.clipboard.ClipboardImageKt$bitmapFromContentUriAsync$2
            r2.<init>(r4, r5)
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.InterruptibleKt.runInterruptible(r6, r2, r0)
            if (r6 != r1) goto L_0x004b
            return r1
        L_0x004b:
            java.lang.String r4 = "runInterruptible(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.ClipboardImageKt.bitmapFromContentUriAsync(android.content.Context, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Bitmap bitmapFromBase64String(String str) {
        Intrinsics.checkNotNullParameter(str, "base64Image");
        try {
            byte[] decode = Base64.decode(str, 0);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            if (decodeByteArray != null) {
                return decodeByteArray;
            }
            throw new RuntimeException("Failed to convert base64 into Bitmap");
        } catch (RuntimeException e) {
            throw new InvalidImageException(str, e);
        }
    }

    /* access modifiers changed from: private */
    public static final Object ensureExists(File file, Continuation<? super Boolean> continuation) {
        return InterruptibleKt.runInterruptible(Dispatchers.getIO(), new ClipboardImageKt$ensureExists$2(file), continuation);
    }
}
