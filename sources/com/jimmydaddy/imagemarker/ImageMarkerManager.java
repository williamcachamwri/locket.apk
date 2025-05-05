package com.jimmydaddy.imagemarker;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jimmydaddy.imagemarker.base.Constants;
import com.jimmydaddy.imagemarker.base.MarkImageOptions;
import com.jimmydaddy.imagemarker.base.MarkTextOptions;
import com.jimmydaddy.imagemarker.base.SaveFormat;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\n\u001a\u00020\u0006H\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J:\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J*\u0010\u0018\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001a\u0010\u001a\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u001a\u0010\u001c\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jimmydaddy/imagemarker/ImageMarkerManager;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "generateCacheFilePathForMarker", "", "filename", "saveFormat", "Lcom/jimmydaddy/imagemarker/base/SaveFormat;", "getName", "getSaveFormat", "Landroid/graphics/Bitmap$CompressFormat;", "markImageByBitmap", "", "bg", "Landroid/graphics/Bitmap;", "markers", "", "dest", "opts", "Lcom/jimmydaddy/imagemarker/base/MarkImageOptions;", "promise", "Lcom/facebook/react/bridge/Promise;", "markImageByText", "Lcom/jimmydaddy/imagemarker/base/MarkTextOptions;", "markWithImage", "Lcom/facebook/react/bridge/ReadableMap;", "markWithText", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageMarkerManager.kt */
public final class ImageMarkerManager extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "ImageMarker";
    /* access modifiers changed from: private */
    public final ReactApplicationContext context;

    public String getName() {
        return NAME;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageMarkerManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        this.context = reactApplicationContext;
    }

    private final Bitmap.CompressFormat getSaveFormat(SaveFormat saveFormat) {
        return (saveFormat == null || saveFormat != SaveFormat.PNG) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01dc A[SYNTHETIC, Splitter:B:80:0x01dc] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f3 A[SYNTHETIC, Splitter:B:90:0x01f3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void markImageByBitmap(android.graphics.Bitmap r20, java.util.List<android.graphics.Bitmap> r21, java.lang.String r22, com.jimmydaddy.imagemarker.base.MarkImageOptions r23, com.facebook.react.bridge.Promise r24) {
        /*
            r19 = this;
            r0 = r22
            r1 = r24
            kotlin.jvm.internal.Intrinsics.checkNotNull(r20)     // Catch:{ Exception -> 0x01c7, all -> 0x01c0 }
            int r9 = r20.getHeight()     // Catch:{ Exception -> 0x01c7, all -> 0x01c0 }
            int r10 = r20.getWidth()     // Catch:{ Exception -> 0x01c7, all -> 0x01c0 }
            com.jimmydaddy.imagemarker.base.Utils$Companion r3 = com.jimmydaddy.imagemarker.base.Utils.Companion     // Catch:{ Exception -> 0x01c7, all -> 0x01c0 }
            android.graphics.Bitmap r11 = r3.getBlankBitmap(r10, r9)     // Catch:{ Exception -> 0x01c7, all -> 0x01c0 }
            android.graphics.Canvas r12 = new android.graphics.Canvas     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r12.<init>(r11)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r12.save()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            com.jimmydaddy.imagemarker.base.ImageOptions r3 = r23.getBackgroundImage()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            android.graphics.Paint r3 = r3.applyStyle()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r13 = 0
            r14 = r20
            r12.drawBitmap(r14, r13, r13, r3)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r12.restore()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            com.jimmydaddy.imagemarker.base.WatermarkImageOptions[] r3 = r23.getWatermarkImages()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            int r15 = r3.length     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r8 = 0
            r7 = r8
        L_0x0038:
            r3 = 1
            if (r7 >= r15) goto L_0x00f3
            r12.save()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.WatermarkImageOptions[] r4 = r23.getWatermarkImages()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r16 = r4[r7]     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r6 = r21
            java.lang.Object r4 = r6.get(r7)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.ImageOptions r5 = r16.getImageOption()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r5 = r5.getRotate()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            int r5 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r3 = r8
        L_0x005a:
            if (r3 != 0) goto L_0x0073
            com.jimmydaddy.imagemarker.ImageProcess$Companion r3 = com.jimmydaddy.imagemarker.ImageProcess.Companion     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.ImageOptions r5 = r16.getImageOption()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r5 = r5.getRotate()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Bitmap r4 = r3.rotate(r4, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        L_0x0073:
            r5 = r4
            com.jimmydaddy.imagemarker.base.PositionEnum r3 = r16.getPositionEnum()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            if (r3 == 0) goto L_0x00ac
            com.jimmydaddy.imagemarker.base.Position$Companion r3 = com.jimmydaddy.imagemarker.base.Position.Companion     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.PositionEnum r4 = r16.getPositionEnum()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            int r17 = r5.getWidth()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            int r18 = r5.getHeight()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r2 = r5
            r5 = r17
            r6 = r18
            r17 = r7
            r7 = r10
            r8 = r9
            com.jimmydaddy.imagemarker.base.Position r3 = r3.getImageRectFromPosition((com.jimmydaddy.imagemarker.base.PositionEnum) r4, (int) r5, (int) r6, (int) r7, (int) r8)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r4 = r3.getX()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r3 = r3.getY()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.ImageOptions r5 = r16.getImageOption()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Paint r5 = r5.applyStyle()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r12.drawBitmap(r2, r4, r3, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            goto L_0x00d3
        L_0x00ac:
            r2 = r5
            r17 = r7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.Utils$Companion r3 = com.jimmydaddy.imagemarker.base.Utils.Companion     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.String r4 = r16.getX()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r5 = (float) r10     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r3 = r3.parseSpreadValue(r4, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.Utils$Companion r4 = com.jimmydaddy.imagemarker.base.Utils.Companion     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.String r5 = r16.getY()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r6 = (float) r9     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r4 = r4.parseSpreadValue(r5, r6)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.ImageOptions r5 = r16.getImageOption()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Paint r5 = r5.applyStyle()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r12.drawBitmap(r2, r3, r4, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        L_0x00d3:
            r12.restore()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            boolean r3 = r2.isRecycled()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            if (r3 != 0) goto L_0x00e2
            r2.recycle()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.System.gc()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        L_0x00e2:
            int r7 = r17 + 1
            r8 = 0
            goto L_0x0038
        L_0x00e7:
            r0 = move-exception
            r2 = 0
        L_0x00e9:
            r4 = r19
            goto L_0x01f0
        L_0x00ed:
            r0 = move-exception
            r2 = 0
        L_0x00ef:
            r4 = r19
            goto L_0x01cc
        L_0x00f3:
            boolean r2 = r20.isRecycled()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            if (r2 != 0) goto L_0x00ff
            r20.recycle()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.System.gc()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        L_0x00ff:
            com.jimmydaddy.imagemarker.base.ImageOptions r2 = r23.getBackgroundImage()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            float r2 = r2.getRotate()     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x010d
            r8 = r3
            goto L_0x010e
        L_0x010d:
            r8 = 0
        L_0x010e:
            if (r8 != 0) goto L_0x0124
            com.jimmydaddy.imagemarker.ImageProcess$Companion r2 = com.jimmydaddy.imagemarker.ImageProcess.Companion     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            com.jimmydaddy.imagemarker.base.ImageOptions r3 = r23.getBackgroundImage()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            float r3 = r3.getRotate()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Bitmap r11 = r2.rotate(r11, r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        L_0x0124:
            java.lang.String r2 = "base64"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            if (r2 == 0) goto L_0x0166
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r0.<init>()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            int r3 = r23.getQuality()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r4 = r0
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r11.compress(r2, r3, r4)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r0.flush()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r0.close()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            byte[] r0 = r0.toByteArray()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r2 = 0
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r2.<init>()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.String r3 = "data:image/png;base64,"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r1.resolve(r0)     // Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
            r2 = 0
            r4 = r19
            goto L_0x0193
        L_0x0166:
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            java.io.FileOutputStream r3 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r3, (java.lang.String) r0)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x01bb, all -> 0x01b5 }
            com.jimmydaddy.imagemarker.base.SaveFormat r3 = r23.getSaveFormat()     // Catch:{ Exception -> 0x01b2, all -> 0x01af }
            r4 = r19
            android.graphics.Bitmap$CompressFormat r3 = r4.getSaveFormat(r3)     // Catch:{ Exception -> 0x01ad }
            int r5 = r23.getQuality()     // Catch:{ Exception -> 0x01ad }
            r6 = r2
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ Exception -> 0x01ad }
            r11.compress(r3, r5, r6)     // Catch:{ Exception -> 0x01ad }
            r2.flush()     // Catch:{ Exception -> 0x01ad }
            r2.close()     // Catch:{ Exception -> 0x01ad }
            r1.resolve(r0)     // Catch:{ Exception -> 0x01ad }
        L_0x0193:
            if (r2 == 0) goto L_0x019e
            r2.close()     // Catch:{ IOException -> 0x0199 }
            goto L_0x019e
        L_0x0199:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x019e:
            if (r11 == 0) goto L_0x01ee
            boolean r0 = r11.isRecycled()
            if (r0 != 0) goto L_0x01ee
        L_0x01a6:
            r11.recycle()
            java.lang.System.gc()
            goto L_0x01ee
        L_0x01ad:
            r0 = move-exception
            goto L_0x01cc
        L_0x01af:
            r0 = move-exception
            goto L_0x00e9
        L_0x01b2:
            r0 = move-exception
            goto L_0x00ef
        L_0x01b5:
            r0 = move-exception
            r4 = r19
            r1 = r0
            r2 = 0
            goto L_0x01f1
        L_0x01bb:
            r0 = move-exception
            r4 = r19
            r2 = 0
            goto L_0x01cc
        L_0x01c0:
            r0 = move-exception
            r4 = r19
            r1 = r0
            r2 = 0
            r11 = 0
            goto L_0x01f1
        L_0x01c7:
            r0 = move-exception
            r4 = r19
            r2 = 0
            r11 = 0
        L_0x01cc:
            r0.printStackTrace()     // Catch:{ all -> 0x01ef }
            java.lang.String r3 = "error"
            java.lang.String r5 = r0.getMessage()     // Catch:{ all -> 0x01ef }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01ef }
            r1.reject((java.lang.String) r3, (java.lang.String) r5, (java.lang.Throwable) r0)     // Catch:{ all -> 0x01ef }
            if (r2 == 0) goto L_0x01e5
            r2.close()     // Catch:{ IOException -> 0x01e0 }
            goto L_0x01e5
        L_0x01e0:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x01e5:
            if (r11 == 0) goto L_0x01ee
            boolean r0 = r11.isRecycled()
            if (r0 != 0) goto L_0x01ee
            goto L_0x01a6
        L_0x01ee:
            return
        L_0x01ef:
            r0 = move-exception
        L_0x01f0:
            r1 = r0
        L_0x01f1:
            if (r2 == 0) goto L_0x01fc
            r2.close()     // Catch:{ IOException -> 0x01f7 }
            goto L_0x01fc
        L_0x01f7:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x01fc:
            if (r11 == 0) goto L_0x020a
            boolean r0 = r11.isRecycled()
            if (r0 != 0) goto L_0x020a
            r11.recycle()
            java.lang.System.gc()
        L_0x020a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.ImageMarkerManager.markImageByBitmap(android.graphics.Bitmap, java.util.List, java.lang.String, com.jimmydaddy.imagemarker.base.MarkImageOptions, com.facebook.react.bridge.Promise):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0131 A[SYNTHETIC, Splitter:B:54:0x0131] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0148 A[SYNTHETIC, Splitter:B:64:0x0148] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void markImageByText(android.graphics.Bitmap r16, java.lang.String r17, com.jimmydaddy.imagemarker.base.MarkTextOptions r18, com.facebook.react.bridge.Promise r19) {
        /*
            r15 = this;
            r0 = r17
            r1 = r19
            r2 = 0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r16)     // Catch:{ Exception -> 0x011e, all -> 0x0119 }
            int r3 = r16.getHeight()     // Catch:{ Exception -> 0x011e, all -> 0x0119 }
            int r4 = r16.getWidth()     // Catch:{ Exception -> 0x011e, all -> 0x0119 }
            com.jimmydaddy.imagemarker.base.Utils$Companion r5 = com.jimmydaddy.imagemarker.base.Utils.Companion     // Catch:{ Exception -> 0x011e, all -> 0x0119 }
            android.graphics.Bitmap r5 = r5.getBlankBitmap(r4, r3)     // Catch:{ Exception -> 0x011e, all -> 0x0119 }
            android.graphics.Canvas r6 = new android.graphics.Canvas     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r6.save()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            com.jimmydaddy.imagemarker.base.ImageOptions r7 = r18.getBackgroundImage()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            android.graphics.Paint r7 = r7.applyStyle()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r8 = 0
            r9 = r16
            r6.drawBitmap(r9, r8, r8, r7)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r6.restore()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            boolean r7 = r16.isRecycled()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            if (r7 != 0) goto L_0x003e
            r16.recycle()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.System.gc()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
        L_0x003e:
            com.jimmydaddy.imagemarker.base.TextOptions[] r7 = r18.getWatermarkTexts()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            int r9 = r7.length     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r10 = 0
            r11 = r10
        L_0x0045:
            if (r11 >= r9) goto L_0x005b
            r12 = r7[r11]     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            com.facebook.react.bridge.ReactApplicationContext r13 = r15.getReactApplicationContext()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.String r14 = "getReactApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r12.applyStyle(r13, r6, r4, r3)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            int r11 = r11 + 1
            goto L_0x0045
        L_0x005b:
            com.jimmydaddy.imagemarker.base.ImageOptions r3 = r18.getBackgroundImage()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            float r3 = r3.getRotate()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x0069
            r3 = 1
            goto L_0x006a
        L_0x0069:
            r3 = r10
        L_0x006a:
            if (r3 != 0) goto L_0x0080
            com.jimmydaddy.imagemarker.ImageProcess$Companion r3 = com.jimmydaddy.imagemarker.ImageProcess.Companion     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            com.jimmydaddy.imagemarker.base.ImageOptions r4 = r18.getBackgroundImage()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            float r4 = r4.getRotate()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            android.graphics.Bitmap r5 = r3.rotate(r5, r4)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
        L_0x0080:
            java.lang.String r3 = "base64"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            if (r3 == 0) goto L_0x00bf
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r0.<init>()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            int r4 = r18.getQuality()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r6 = r0
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r5.compress(r3, r4, r6)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r0.flush()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r0.close()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            byte[] r0 = r0.toByteArray()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r10)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r3.<init>()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.String r4 = "data:image/png;base64,"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r1.resolve(r0)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r4 = r15
            goto L_0x00ec
        L_0x00bf:
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.io.FileOutputStream r4 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r4, (java.lang.String) r0)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0116, all -> 0x0113 }
            com.jimmydaddy.imagemarker.base.SaveFormat r2 = r18.getSaveFormat()     // Catch:{ Exception -> 0x010f, all -> 0x010a }
            r4 = r15
            android.graphics.Bitmap$CompressFormat r2 = r15.getSaveFormat(r2)     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            int r6 = r18.getQuality()     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r7 = r3
            java.io.OutputStream r7 = (java.io.OutputStream) r7     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r5.compress(r2, r6, r7)     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r3.flush()     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r3.close()     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r1.resolve(r0)     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r2 = r3
        L_0x00ec:
            if (r2 == 0) goto L_0x00f7
            r2.close()     // Catch:{ IOException -> 0x00f2 }
            goto L_0x00f7
        L_0x00f2:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00f7:
            if (r5 == 0) goto L_0x0143
            boolean r0 = r5.isRecycled()
            if (r0 != 0) goto L_0x0143
        L_0x00ff:
            r5.recycle()
            java.lang.System.gc()
            goto L_0x0143
        L_0x0106:
            r0 = move-exception
            goto L_0x010c
        L_0x0108:
            r0 = move-exception
            goto L_0x0111
        L_0x010a:
            r0 = move-exception
            r4 = r15
        L_0x010c:
            r1 = r0
            r2 = r3
            goto L_0x0146
        L_0x010f:
            r0 = move-exception
            r4 = r15
        L_0x0111:
            r2 = r3
            goto L_0x0121
        L_0x0113:
            r0 = move-exception
            r4 = r15
            goto L_0x0145
        L_0x0116:
            r0 = move-exception
            r4 = r15
            goto L_0x0121
        L_0x0119:
            r0 = move-exception
            r4 = r15
            r1 = r0
            r5 = r2
            goto L_0x0146
        L_0x011e:
            r0 = move-exception
            r4 = r15
            r5 = r2
        L_0x0121:
            r0.printStackTrace()     // Catch:{ all -> 0x0144 }
            java.lang.String r3 = "error"
            java.lang.String r6 = r0.getMessage()     // Catch:{ all -> 0x0144 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0144 }
            r1.reject((java.lang.String) r3, (java.lang.String) r6, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0144 }
            if (r2 == 0) goto L_0x013a
            r2.close()     // Catch:{ IOException -> 0x0135 }
            goto L_0x013a
        L_0x0135:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x013a:
            if (r5 == 0) goto L_0x0143
            boolean r0 = r5.isRecycled()
            if (r0 != 0) goto L_0x0143
            goto L_0x00ff
        L_0x0143:
            return
        L_0x0144:
            r0 = move-exception
        L_0x0145:
            r1 = r0
        L_0x0146:
            if (r2 == 0) goto L_0x0151
            r2.close()     // Catch:{ IOException -> 0x014c }
            goto L_0x0151
        L_0x014c:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0151:
            if (r5 == 0) goto L_0x015f
            boolean r0 = r5.isRecycled()
            if (r0 != 0) goto L_0x015f
            r5.recycle()
            java.lang.System.gc()
        L_0x015f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.ImageMarkerManager.markImageByText(android.graphics.Bitmap, java.lang.String, com.jimmydaddy.imagemarker.base.MarkTextOptions, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public final void markWithText(ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        MarkTextOptions.Companion companion = MarkTextOptions.Companion;
        Intrinsics.checkNotNull(readableMap);
        MarkTextOptions checkParams = companion.checkParams(readableMap, promise);
        if (checkParams != null) {
            Log.d(Constants.IMAGE_MARKER_TAG, "uri: " + checkParams.getBackgroundImage().getUri());
            Log.d(Constants.IMAGE_MARKER_TAG, "src: " + checkParams.getBackgroundImage().getSrc());
            Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new ImageMarkerManager$markWithText$1(this, checkParams, promise, (Continuation<? super ImageMarkerManager$markWithText$1>) null), 2, (Object) null);
        }
    }

    @ReactMethod
    public final void markWithImage(ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        MarkImageOptions.Companion companion = MarkImageOptions.Companion;
        Intrinsics.checkNotNull(readableMap);
        MarkImageOptions checkParams = companion.checkParams(readableMap, promise);
        if (checkParams != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new ImageMarkerManager$markWithImage$1(checkParams, this, promise, (Continuation<? super ImageMarkerManager$markWithImage$1>) null), 2, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final String generateCacheFilePathForMarker(String str, SaveFormat saveFormat) {
        String absolutePath = getReactApplicationContext().getCacheDir().getAbsolutePath();
        if (saveFormat != null && saveFormat == SaveFormat.BASE64) {
            return Constants.BASE64;
        }
        String str2 = (saveFormat == null || saveFormat != SaveFormat.PNG) ? ".jpg" : ".png";
        if (str != null) {
            return ((StringsKt.endsWith$default(str, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(str, ".png", false, 2, (Object) null)) ? new StringBuilder().append(absolutePath).append("/").append(str) : new StringBuilder().append(absolutePath).append("/").append(str).append(str2)).toString();
        }
        return absolutePath + "/" + (UUID.randomUUID().toString() + "_image_marker") + str2;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/jimmydaddy/imagemarker/ImageMarkerManager$Companion;", "", "()V", "NAME", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImageMarkerManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
