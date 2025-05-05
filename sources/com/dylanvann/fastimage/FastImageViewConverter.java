package com.dylanvann.fastimage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.ProfilingTraceData;
import io.sentry.protocol.SentryThread;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

class FastImageViewConverter {
    private static final Map<String, FastImageCacheControl> FAST_IMAGE_CACHE_CONTROL_MAP = new HashMap<String, FastImageCacheControl>() {
        {
            put("immutable", FastImageCacheControl.IMMUTABLE);
            put("web", FastImageCacheControl.WEB);
            put("cacheOnly", FastImageCacheControl.CACHE_ONLY);
        }
    };
    private static final Map<String, Priority> FAST_IMAGE_PRIORITY_MAP = new HashMap<String, Priority>() {
        {
            put("low", Priority.LOW);
            put(ProfilingTraceData.TRUNCATION_REASON_NORMAL, Priority.NORMAL);
            put("high", Priority.HIGH);
        }
    };
    private static final Map<String, ImageView.ScaleType> FAST_IMAGE_RESIZE_MODE_MAP = new HashMap<String, ImageView.ScaleType>() {
        {
            put("contain", ImageView.ScaleType.FIT_CENTER);
            put("cover", ImageView.ScaleType.CENTER_CROP);
            put("stretch", ImageView.ScaleType.FIT_XY);
            put(TtmlNode.CENTER, ImageView.ScaleType.CENTER_INSIDE);
        }
    };
    private static final Drawable TRANSPARENT_DRAWABLE = new ColorDrawable(0);

    FastImageViewConverter() {
    }

    @Nullable
    static FastImageSource getImageSource(Context context, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        return new FastImageSource(context, readableMap.getString("uri"), getHeaders(readableMap));
    }

    static Headers getHeaders(ReadableMap readableMap) {
        Headers headers = Headers.DEFAULT;
        if (!readableMap.hasKey("headers")) {
            return headers;
        }
        ReadableMap map = readableMap.getMap("headers");
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        LazyHeaders.Builder builder = new LazyHeaders.Builder();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            builder.addHeader(nextKey, map.getString(nextKey));
        }
        return builder.build();
    }

    static RequestOptions getOptions(Context context, FastImageSource fastImageSource, ReadableMap readableMap) {
        Priority priority = getPriority(readableMap);
        FastImageCacheControl cacheControl = getCacheControl(readableMap);
        DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
        int i = AnonymousClass4.$SwitchMap$com$dylanvann$fastimage$FastImageCacheControl[cacheControl.ordinal()];
        boolean z = true;
        boolean z2 = false;
        if (i == 1) {
            diskCacheStrategy = DiskCacheStrategy.NONE;
            z2 = true;
            z = false;
        } else if (i != 2) {
            z = false;
        }
        RequestOptions requestOptions = (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().diskCacheStrategy(diskCacheStrategy)).onlyRetrieveFromCache(z)).skipMemoryCache(z2)).priority(priority)).placeholder(TRANSPARENT_DRAWABLE);
        return fastImageSource.isResource() ? (RequestOptions) requestOptions.apply(RequestOptions.signatureOf(ApplicationVersionSignature.obtain(context))) : requestOptions;
    }

    /* renamed from: com.dylanvann.fastimage.FastImageViewConverter$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$dylanvann$fastimage$FastImageCacheControl;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.dylanvann.fastimage.FastImageCacheControl[] r0 = com.dylanvann.fastimage.FastImageCacheControl.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$dylanvann$fastimage$FastImageCacheControl = r0
                com.dylanvann.fastimage.FastImageCacheControl r1 = com.dylanvann.fastimage.FastImageCacheControl.WEB     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$dylanvann$fastimage$FastImageCacheControl     // Catch:{ NoSuchFieldError -> 0x001d }
                com.dylanvann.fastimage.FastImageCacheControl r1 = com.dylanvann.fastimage.FastImageCacheControl.CACHE_ONLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$dylanvann$fastimage$FastImageCacheControl     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.dylanvann.fastimage.FastImageCacheControl r1 = com.dylanvann.fastimage.FastImageCacheControl.IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dylanvann.fastimage.FastImageViewConverter.AnonymousClass4.<clinit>():void");
        }
    }

    private static FastImageCacheControl getCacheControl(ReadableMap readableMap) {
        return (FastImageCacheControl) getValueFromSource("cache", "immutable", FAST_IMAGE_CACHE_CONTROL_MAP, readableMap);
    }

    private static Priority getPriority(ReadableMap readableMap) {
        return (Priority) getValueFromSource(SentryThread.JsonKeys.PRIORITY, ProfilingTraceData.TRUNCATION_REASON_NORMAL, FAST_IMAGE_PRIORITY_MAP, readableMap);
    }

    static ImageView.ScaleType getScaleType(String str) {
        return (ImageView.ScaleType) getValue(ViewProps.RESIZE_MODE, "cover", FAST_IMAGE_RESIZE_MODE_MAP, str);
    }

    private static <T> T getValue(String str, String str2, Map<String, T> map, String str3) {
        if (str3 != null) {
            str2 = str3;
        }
        T t = map.get(str2);
        if (t != null) {
            return t;
        }
        throw new JSApplicationIllegalArgumentException("FastImage, invalid " + str + " : " + str2);
    }

    private static <T> T getValueFromSource(String str, String str2, Map<String, T> map, ReadableMap readableMap) {
        String str3 = null;
        if (readableMap != null) {
            try {
                str3 = readableMap.getString(str);
            } catch (NoSuchKeyException unused) {
            }
        }
        return getValue(str, str2, map, str3);
    }
}
