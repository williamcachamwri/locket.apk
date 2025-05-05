package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/ByteArrayOutputStream;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageExporter.kt */
final class ImageExportResult$data$2 extends Lambda implements Function0<ByteArrayOutputStream> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ ImageExportResult this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageExportResult$data$2(ContentResolver contentResolver, ImageExportResult imageExportResult) {
        super(0);
        this.$contentResolver = contentResolver;
        this.this$0 = imageExportResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.ByteArrayOutputStream invoke() {
        /*
            r8 = this;
            android.content.ContentResolver r0 = r8.$contentResolver
            expo.modules.imagepicker.exporters.ImageExportResult r1 = r8.this$0
            java.io.File r1 = r1.imageFile
            android.net.Uri r1 = android.net.Uri.fromFile(r1)
            java.io.InputStream r0 = r0.openInputStream(r1)
            r1 = 2
            r2 = 0
            if (r0 == 0) goto L_0x003f
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = r0
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x0038 }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0038 }
            r4.<init>()     // Catch:{ all -> 0x0038 }
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ all -> 0x0038 }
            r5 = r4
            java.io.ByteArrayOutputStream r5 = (java.io.ByteArrayOutputStream) r5     // Catch:{ all -> 0x0031 }
            r6 = r5
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ all -> 0x0031 }
            r7 = 0
            kotlin.io.ByteStreamsKt.copyTo$default(r3, r6, r7, r1, r2)     // Catch:{ all -> 0x0031 }
            kotlin.io.CloseableKt.closeFinally(r4, r2)     // Catch:{ all -> 0x0038 }
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            return r5
        L_0x0031:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r1)     // Catch:{ all -> 0x0038 }
            throw r2     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x003a }
        L_0x003a:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        L_0x003f:
            expo.modules.imagepicker.FailedToReadFileException r0 = new expo.modules.imagepicker.FailedToReadFileException
            expo.modules.imagepicker.exporters.ImageExportResult r3 = r8.this$0
            java.io.File r3 = r3.imageFile
            r0.<init>(r3, r2, r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.ImageExportResult$data$2.invoke():java.io.ByteArrayOutputStream");
    }
}
