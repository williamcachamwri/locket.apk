package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageExporter.kt */
final class ImageExportResult$exif$2 extends Lambda implements Function0<Bundle> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ ImageExportResult this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageExportResult$exif$2(ContentResolver contentResolver, ImageExportResult imageExportResult) {
        super(0);
        this.$contentResolver = contentResolver;
        this.this$0 = imageExportResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e2, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e3, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e6, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle invoke() {
        /*
            r13 = this;
            android.content.ContentResolver r0 = r13.$contentResolver
            expo.modules.imagepicker.exporters.ImageExportResult r1 = r13.this$0
            java.io.File r1 = r1.imageFile
            android.net.Uri r1 = android.net.Uri.fromFile(r1)
            java.io.InputStream r0 = r0.openInputStream(r1)
            r1 = 0
            if (r0 == 0) goto L_0x00e7
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch:{ all -> 0x00e0 }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00e0 }
            r3.<init>()     // Catch:{ all -> 0x00e0 }
            androidx.exifinterface.media.ExifInterface r4 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x00e0 }
            r4.<init>((java.io.InputStream) r2)     // Catch:{ all -> 0x00e0 }
            expo.modules.imagepicker.ImagePickerConstants r2 = expo.modules.imagepicker.ImagePickerConstants.INSTANCE     // Catch:{ all -> 0x00e0 }
            java.lang.Iterable r2 = r2.getEXIF_TAGS()     // Catch:{ all -> 0x00e0 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x00e0 }
            r5.<init>()     // Catch:{ all -> 0x00e0 }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x00e0 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00e0 }
        L_0x0033:
            boolean r6 = r2.hasNext()     // Catch:{ all -> 0x00e0 }
            r7 = 1
            r8 = 0
            if (r6 == 0) goto L_0x0056
            java.lang.Object r6 = r2.next()     // Catch:{ all -> 0x00e0 }
            r9 = r6
            kotlin.Pair r9 = (kotlin.Pair) r9     // Catch:{ all -> 0x00e0 }
            java.lang.Object r9 = r9.component2()     // Catch:{ all -> 0x00e0 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x00e0 }
            java.lang.String r9 = r4.getAttribute(r9)     // Catch:{ all -> 0x00e0 }
            if (r9 == 0) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r7 = r8
        L_0x0050:
            if (r7 == 0) goto L_0x0033
            r5.add(r6)     // Catch:{ all -> 0x00e0 }
            goto L_0x0033
        L_0x0056:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x00e0 }
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ all -> 0x00e0 }
            java.util.Iterator r2 = r5.iterator()     // Catch:{ all -> 0x00e0 }
        L_0x005e:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x00e0 }
            r9 = 0
            if (r5 == 0) goto L_0x00bf
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x00e0 }
            kotlin.Pair r5 = (kotlin.Pair) r5     // Catch:{ all -> 0x00e0 }
            java.lang.Object r6 = r5.component1()     // Catch:{ all -> 0x00e0 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x00e0 }
            java.lang.Object r5 = r5.component2()     // Catch:{ all -> 0x00e0 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00e0 }
            int r11 = r6.hashCode()     // Catch:{ all -> 0x00e0 }
            r12 = -1325958191(0xffffffffb0f77bd1, float:-1.8006806E-9)
            if (r11 == r12) goto L_0x00ae
            r9 = -891985903(0xffffffffcad56011, float:-6991880.5)
            if (r11 == r9) goto L_0x009d
            r9 = 104431(0x197ef, float:1.46339E-40)
            if (r11 == r9) goto L_0x008c
            goto L_0x005e
        L_0x008c:
            java.lang.String r9 = "int"
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x00e0 }
            if (r6 != 0) goto L_0x0095
            goto L_0x005e
        L_0x0095:
            int r6 = r4.getAttributeInt(r5, r8)     // Catch:{ all -> 0x00e0 }
            r3.putInt(r5, r6)     // Catch:{ all -> 0x00e0 }
            goto L_0x005e
        L_0x009d:
            java.lang.String r9 = "string"
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x00e0 }
            if (r6 != 0) goto L_0x00a6
            goto L_0x005e
        L_0x00a6:
            java.lang.String r6 = r4.getAttribute(r5)     // Catch:{ all -> 0x00e0 }
            r3.putString(r5, r6)     // Catch:{ all -> 0x00e0 }
            goto L_0x005e
        L_0x00ae:
            java.lang.String r11 = "double"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x00e0 }
            if (r6 != 0) goto L_0x00b7
            goto L_0x005e
        L_0x00b7:
            double r9 = r4.getAttributeDouble(r5, r9)     // Catch:{ all -> 0x00e0 }
            r3.putDouble(r5, r9)     // Catch:{ all -> 0x00e0 }
            goto L_0x005e
        L_0x00bf:
            double[] r2 = r4.getLatLong()     // Catch:{ all -> 0x00e0 }
            if (r2 == 0) goto L_0x00dc
            java.lang.String r5 = "GPSLatitude"
            r11 = r2[r8]     // Catch:{ all -> 0x00e0 }
            r3.putDouble(r5, r11)     // Catch:{ all -> 0x00e0 }
            java.lang.String r5 = "GPSLongitude"
            r6 = r2[r7]     // Catch:{ all -> 0x00e0 }
            r3.putDouble(r5, r6)     // Catch:{ all -> 0x00e0 }
            java.lang.String r2 = "GPSAltitude"
            double r4 = r4.getAltitude(r9)     // Catch:{ all -> 0x00e0 }
            r3.putDouble(r2, r4)     // Catch:{ all -> 0x00e0 }
        L_0x00dc:
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r3
        L_0x00e0:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00e2 }
        L_0x00e2:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        L_0x00e7:
            expo.modules.imagepicker.FailedToReadFileException r0 = new expo.modules.imagepicker.FailedToReadFileException
            expo.modules.imagepicker.exporters.ImageExportResult r2 = r13.this$0
            java.io.File r2 = r2.imageFile
            r3 = 2
            r0.<init>(r2, r1, r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.ImageExportResult$exif$2.invoke():android.os.Bundle");
    }
}
