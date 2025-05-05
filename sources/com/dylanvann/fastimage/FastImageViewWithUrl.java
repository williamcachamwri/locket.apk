package com.dylanvann.fastimage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

class FastImageViewWithUrl extends AppCompatImageView {
    public GlideUrl glideUrl;
    private Drawable mDefaultSource = null;
    private boolean mNeedsReload = false;
    private ReadableMap mSource = null;

    public FastImageViewWithUrl(Context context) {
        super(context);
    }

    public void setSource(ReadableMap readableMap) {
        this.mNeedsReload = true;
        this.mSource = readableMap;
    }

    public void setDefaultSource(Drawable drawable) {
        this.mNeedsReload = true;
        this.mDefaultSource = drawable;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void onAfterUpdate(@Nonnull FastImageViewManager fastImageViewManager, RequestManager requestManager, @Nonnull Map<String, List<FastImageViewWithUrl>> map) {
        GlideUrl glideUrl2;
        String str;
        if (this.mNeedsReload) {
            ReadableMap readableMap = this.mSource;
            Object obj = null;
            if ((readableMap == null || !readableMap.hasKey("uri") || isNullOrEmpty(this.mSource.getString("uri"))) && this.mDefaultSource == null) {
                clearView(requestManager);
                GlideUrl glideUrl3 = this.glideUrl;
                if (glideUrl3 != null) {
                    FastImageOkHttpProgressGlideModule.forget(glideUrl3.toStringUrl());
                }
                setImageDrawable((Drawable) null);
                return;
            }
            FastImageSource imageSource = FastImageViewConverter.getImageSource(getContext(), this.mSource);
            if (imageSource == null || imageSource.getUri().toString().length() != 0) {
                if (imageSource == null) {
                    glideUrl2 = null;
                } else {
                    glideUrl2 = imageSource.getGlideUrl();
                }
                this.glideUrl = glideUrl2;
                clearView(requestManager);
                if (glideUrl2 == null) {
                    str = null;
                } else {
                    str = glideUrl2.toStringUrl();
                }
                if (glideUrl2 != null) {
                    FastImageOkHttpProgressGlideModule.expect(str, fastImageViewManager);
                    List list = map.get(str);
                    if (list != null && !list.contains(this)) {
                        list.add(this);
                    } else if (list == null) {
                        map.put(str, new ArrayList(Collections.singletonList(this)));
                    }
                }
                ThemedReactContext themedReactContext = (ThemedReactContext) getContext();
                if (imageSource != null) {
                    ((RCTEventEmitter) themedReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onFastImageLoadStart", new WritableNativeMap());
                }
                if (requestManager != null) {
                    if (imageSource != null) {
                        obj = imageSource.getSourceForLoad();
                    }
                    RequestBuilder<Drawable> apply = requestManager.load(obj).apply((BaseRequestOptions<?>) ((RequestOptions) FastImageViewConverter.getOptions(themedReactContext, imageSource, this.mSource).placeholder(this.mDefaultSource)).fallback(this.mDefaultSource));
                    if (str != null) {
                        apply.listener(new FastImageRequestListener(str));
                    }
                    apply.into((ImageView) this);
                    return;
                }
                return;
            }
            int id = getId();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("message", "Invalid source prop:" + this.mSource);
            ((RCTEventEmitter) ((ThemedReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(id, "onFastImageError", writableNativeMap);
            clearView(requestManager);
            GlideUrl glideUrl4 = this.glideUrl;
            if (glideUrl4 != null) {
                FastImageOkHttpProgressGlideModule.forget(glideUrl4.toStringUrl());
            }
            setImageDrawable((Drawable) null);
        }
    }

    public void clearView(RequestManager requestManager) {
        if (requestManager != null && getTag() != null && (getTag() instanceof Request)) {
            requestManager.clear((View) this);
        }
    }
}
