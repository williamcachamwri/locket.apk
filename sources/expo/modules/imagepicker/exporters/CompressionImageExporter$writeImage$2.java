package expo.modules.imagepicker.exporters;

import android.graphics.Bitmap;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CompressionImageExporter.kt */
final class CompressionImageExporter$writeImage$2 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ Bitmap.CompressFormat $compressFormat;
    final /* synthetic */ File $output;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CompressionImageExporter$writeImage$2(File file, Bitmap bitmap, Bitmap.CompressFormat compressFormat, CompressionImageExporter compressionImageExporter) {
        super(0);
        this.$output = file;
        this.$bitmap = bitmap;
        this.$compressFormat = compressFormat;
        this.this$0 = compressionImageExporter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean invoke() {
        /*
            r5 = this;
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0030 }
            java.io.File r1 = r5.$output     // Catch:{ FileNotFoundException -> 0x0030 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0030 }
            java.io.FileOutputStream r0 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r0, (java.io.File) r1)     // Catch:{ FileNotFoundException -> 0x0030 }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ FileNotFoundException -> 0x0030 }
            android.graphics.Bitmap r1 = r5.$bitmap     // Catch:{ FileNotFoundException -> 0x0030 }
            android.graphics.Bitmap$CompressFormat r2 = r5.$compressFormat     // Catch:{ FileNotFoundException -> 0x0030 }
            expo.modules.imagepicker.exporters.CompressionImageExporter r3 = r5.this$0     // Catch:{ FileNotFoundException -> 0x0030 }
            r4 = r0
            java.io.FileOutputStream r4 = (java.io.FileOutputStream) r4     // Catch:{ all -> 0x0029 }
            int r3 = r3.compressQuality     // Catch:{ all -> 0x0029 }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ all -> 0x0029 }
            boolean r1 = r1.compress(r2, r3, r4)     // Catch:{ all -> 0x0029 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)     // Catch:{ FileNotFoundException -> 0x0030 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        L_0x0029:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002b }
        L_0x002b:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ FileNotFoundException -> 0x0030 }
            throw r2     // Catch:{ FileNotFoundException -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            expo.modules.imagepicker.FailedToWriteFileException r1 = new expo.modules.imagepicker.FailedToWriteFileException
            java.io.File r2 = r5.$output
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.CompressionImageExporter$writeImage$2.invoke():java.lang.Boolean");
    }
}
