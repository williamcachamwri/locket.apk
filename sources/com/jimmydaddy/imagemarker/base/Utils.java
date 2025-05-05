package com.jimmydaddy.imagemarker.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Utils;", "", "()V", "maxMemory", "", "getMaxMemory", "()I", "scaleBitmap", "Landroid/graphics/Bitmap;", "path", "", "scale", "", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class Utils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static String TAG = Constants.IMAGE_MARKER_TAG;

    @JvmStatic
    public static final Bitmap getBlankBitmap(int i, int i2) {
        return Companion.getBlankBitmap(i, i2);
    }

    @JvmStatic
    public static final String getStringSafe(String str, Map<String, ? extends Object> map) {
        return Companion.getStringSafe(str, map);
    }

    @JvmStatic
    public static final Bitmap scaleBitmap(Bitmap bitmap, float f) {
        return Companion.scaleBitmap(bitmap, f);
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\f\u001a\u00020\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0007J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0004J,\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0016\u0010\u0017\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020\u001dJ\u0012\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010\"\u001a\u0004\u0018\u00010\u000f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u001dH\u0007J\u0010\u0010%\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006'"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Utils$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "checkSpreadValue", "", "str", "maxLength", "", "getBlankBitmap", "Landroid/graphics/Bitmap;", "width", "height", "getStreamFromInternet", "Ljava/io/InputStream;", "url", "getStringSafe", "key", "map", "", "handleDynamicToString", "d", "Lcom/facebook/react/bridge/Dynamic;", "parseSpreadValue", "", "v", "relativeTo", "readDegree", "path", "scaleBitmap", "bitmap", "scale", "transRGBColor", "color", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Utils.kt */
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: Utils.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.Utils.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return Utils.TAG;
        }

        public final void setTAG(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            Utils.TAG = str;
        }

        @JvmStatic
        public final Bitmap getBlankBitmap(int i, int i2) {
            try {
                return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e) {
                System.out.print(e.getMessage());
                Bitmap bitmap = null;
                while (bitmap == null) {
                    System.gc();
                    System.runFinalization();
                    bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                }
                return bitmap;
            }
        }

        /* access modifiers changed from: private */
        public final int readDegree(String str) {
            try {
                Intrinsics.checkNotNull(str);
                int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
                if (attributeInt == 3) {
                    return RotationOptions.ROTATE_180;
                }
                if (attributeInt == 6) {
                    return 90;
                }
                if (attributeInt != 8) {
                    return 0;
                }
                return 270;
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @JvmStatic
        public final Bitmap scaleBitmap(Bitmap bitmap, float f) {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            if (!(f == 1.0f) && f >= 0.0f) {
                matrix.postScale(f, f);
            }
            try {
                return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (OutOfMemoryError e) {
                System.out.print(e.getMessage());
                Bitmap bitmap2 = null;
                while (bitmap2 == null) {
                    System.gc();
                    System.runFinalization();
                    bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                }
                return bitmap2;
            }
        }

        public final String transRGBColor(String str) {
            Intrinsics.checkNotNull(str);
            String substring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            int i = 0;
            if (substring.length() == 3) {
                int length = substring.length();
                String str2 = "";
                while (i < length) {
                    int i2 = i + 1;
                    String substring2 = substring.substring(i, i2);
                    Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                    str2 = str2 + substring2 + substring2;
                    i = i2;
                }
                return "#" + str2;
            } else if (substring.length() == 4) {
                String substring3 = substring.substring(3, 4);
                Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
                String substring4 = substring.substring(0, 3);
                Intrinsics.checkNotNullExpressionValue(substring4, "substring(...)");
                String str3 = substring3 + substring3;
                int length2 = substring4.length();
                while (i < length2) {
                    int i3 = i + 1;
                    String substring5 = substring.substring(i, i3);
                    Intrinsics.checkNotNullExpressionValue(substring5, "substring(...)");
                    str3 = str3 + substring5 + substring5;
                    i = i3;
                }
                return "#" + str3;
            } else if (substring.length() != 8) {
                return str;
            } else {
                String substring6 = substring.substring(6, 8);
                Intrinsics.checkNotNullExpressionValue(substring6, "substring(...)");
                String substring7 = substring.substring(0, 6);
                Intrinsics.checkNotNullExpressionValue(substring7, "substring(...)");
                return "#" + substring6 + substring7;
            }
        }

        @JvmStatic
        public final String getStringSafe(String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            Object obj = map.get(str);
            if (obj != null) {
                return obj.toString();
            }
            return null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
            if (r2 != null) goto L_0x0058;
         */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x006d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.io.InputStream getStreamFromInternet(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.String r0 = "getStreamFromInternet: read stream from remote: "
                java.lang.String r1 = "url"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                r2.<init>(r6)     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.lang.Object r2 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r2)     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.net.URLConnection r2 = (java.net.URLConnection) r2     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.lang.String r3 = "null cannot be cast to non-null type java.net.HttpURLConnection"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x0060, all -> 0x005e }
                java.lang.String r3 = "GET"
                r2.setRequestMethod(r3)     // Catch:{ Exception -> 0x005c }
                r3 = 10000(0x2710, float:1.4013E-41)
                r2.setConnectTimeout(r3)     // Catch:{ Exception -> 0x005c }
                r2.setReadTimeout(r3)     // Catch:{ Exception -> 0x005c }
                r2.connect()     // Catch:{ Exception -> 0x005c }
                int r3 = r2.getResponseCode()     // Catch:{ Exception -> 0x005c }
                r4 = 200(0xc8, float:2.8E-43)
                if (r3 != r4) goto L_0x003e
                java.io.InputStream r6 = r2.getInputStream()     // Catch:{ Exception -> 0x005c }
                r2.disconnect()
                return r6
            L_0x003e:
                java.lang.String r3 = r5.getTAG()     // Catch:{ Exception -> 0x005c }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005c }
                r4.<init>(r0)     // Catch:{ Exception -> 0x005c }
                java.lang.StringBuilder r6 = r4.append(r6)     // Catch:{ Exception -> 0x005c }
                java.lang.String r0 = " failed"
                java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ Exception -> 0x005c }
                java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x005c }
                android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x005c }
            L_0x0058:
                r2.disconnect()
                goto L_0x0068
            L_0x005c:
                r6 = move-exception
                goto L_0x0062
            L_0x005e:
                r6 = move-exception
                goto L_0x006b
            L_0x0060:
                r6 = move-exception
                r2 = r1
            L_0x0062:
                r6.printStackTrace()     // Catch:{ all -> 0x0069 }
                if (r2 == 0) goto L_0x0068
                goto L_0x0058
            L_0x0068:
                return r1
            L_0x0069:
                r6 = move-exception
                r1 = r2
            L_0x006b:
                if (r1 == 0) goto L_0x0070
                r1.disconnect()
            L_0x0070:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.Utils.Companion.getStreamFromInternet(java.lang.String):java.io.InputStream");
        }

        public static /* synthetic */ boolean checkSpreadValue$default(Companion companion, String str, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 1;
            }
            return companion.checkSpreadValue(str, i);
        }

        public final boolean checkSpreadValue(String str, int i) {
            if (str == null) {
                return false;
            }
            return new Regex("^((\\d+|\\d+%)\\s?){1," + i + "}$").containsMatchIn(str);
        }

        public final float parseSpreadValue(String str, float f) {
            float f2 = 0.0f;
            if (str == null) {
                return 0.0f;
            }
            if (StringsKt.endsWith$default(str, "%", false, 2, (Object) null)) {
                Float floatOrNull = StringsKt.toFloatOrNull(StringsKt.dropLast(str, 1));
                if (floatOrNull != null) {
                    f2 = floatOrNull.floatValue() / ((float) 100);
                }
                return f2 * f;
            }
            Float floatOrNull2 = StringsKt.toFloatOrNull(str);
            if (floatOrNull2 != null) {
                return floatOrNull2.floatValue();
            }
            return 0.0f;
        }

        public final String handleDynamicToString(Dynamic dynamic) {
            String str;
            String str2 = "0";
            if (dynamic != null) {
                ReadableType type = dynamic.getType();
                int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        str = String.valueOf(dynamic.asDouble());
                    }
                    Intrinsics.checkNotNull(str2);
                } else {
                    str = dynamic.asString();
                }
                str2 = str;
                Intrinsics.checkNotNull(str2);
            }
            return str2;
        }
    }

    public final int getMaxMemory() {
        return ((int) Runtime.getRuntime().maxMemory()) / 1024;
    }

    public final Bitmap scaleBitmap(String str, float f) {
        Bitmap bitmap;
        int access$readDegree = Companion.readDegree(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = (int) f;
        Bitmap bitmap2 = null;
        try {
            bitmap = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            System.out.print(e.getMessage());
            Bitmap bitmap3 = null;
            while (bitmap3 == null) {
                System.gc();
                System.runFinalization();
                bitmap3 = BitmapFactory.decodeFile(str, options);
            }
            bitmap = bitmap3;
        }
        if (bitmap == null) {
            return null;
        }
        int i = options.outWidth;
        int i2 = options.outHeight;
        Matrix matrix = new Matrix();
        matrix.postRotate((float) access$readDegree);
        if (!(f == 1.0f) && f >= 0.0f) {
            matrix.postScale(f, f);
        }
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, i, i2, matrix, true);
        } catch (OutOfMemoryError e2) {
            System.out.print(e2.getMessage());
            while (bitmap2 == null) {
                System.gc();
                System.runFinalization();
                bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, i, i2, matrix, true);
            }
            return bitmap2;
        }
    }
}
