package expo.modules.imagepicker.exporters;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/imagepicker/exporters/RawImageExporter;", "Lexpo/modules/imagepicker/exporters/ImageExporter;", "()V", "exportAsync", "Lexpo/modules/imagepicker/exporters/ImageExportResult;", "source", "Landroid/net/Uri;", "output", "Ljava/io/File;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/net/Uri;Ljava/io/File;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RawImageExporter.kt */
public final class RawImageExporter implements ImageExporter {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.io.File} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object exportAsync(android.net.Uri r5, java.io.File r6, android.content.ContentResolver r7, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.exporters.ImageExportResult> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof expo.modules.imagepicker.exporters.RawImageExporter$exportAsync$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            expo.modules.imagepicker.exporters.RawImageExporter$exportAsync$1 r0 = (expo.modules.imagepicker.exporters.RawImageExporter$exportAsync$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            expo.modules.imagepicker.exporters.RawImageExporter$exportAsync$1 r0 = new expo.modules.imagepicker.exporters.RawImageExporter$exportAsync$1
            r0.<init>(r4, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r5 = r0.L$0
            r6 = r5
            java.io.File r6 = (java.io.File) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0045
        L_0x002f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = expo.modules.imagepicker.ImagePickerUtilsKt.copyFile(r5, r6, r7, r0)
            if (r5 != r1) goto L_0x0045
            return r1
        L_0x0045:
            android.media.ExifInterface r5 = new android.media.ExifInterface
            java.lang.String r7 = r6.getAbsolutePath()
            r5.<init>(r7)
            java.lang.String r7 = "Orientation"
            r8 = 0
            int r5 = r5.getAttributeInt(r7, r8)
            r7 = 6
            if (r5 == r7) goto L_0x005c
            r7 = 8
            if (r5 != r7) goto L_0x005d
        L_0x005c:
            r8 = r3
        L_0x005d:
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options
            r5.<init>()
            r5.inJustDecodeBounds = r3
            java.lang.String r7 = r6.getAbsolutePath()
            android.graphics.BitmapFactory.decodeFile(r7, r5)
            if (r8 == 0) goto L_0x0070
            int r7 = r5.outHeight
            goto L_0x0072
        L_0x0070:
            int r7 = r5.outWidth
        L_0x0072:
            if (r8 == 0) goto L_0x0077
            int r5 = r5.outWidth
            goto L_0x0079
        L_0x0077:
            int r5 = r5.outHeight
        L_0x0079:
            expo.modules.imagepicker.exporters.ImageExportResult r8 = new expo.modules.imagepicker.exporters.ImageExportResult
            r8.<init>(r7, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.RawImageExporter.exportAsync(android.net.Uri, java.io.File, android.content.ContentResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
