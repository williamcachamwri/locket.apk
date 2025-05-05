package com.dylanvann.fastimage;

import android.app.Activity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class FastImageViewModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "FastImageView";

    public String getName() {
        return REACT_CLASS;
    }

    FastImageViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void preload(final ReadableArray readableArray) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Object obj;
                    for (int i = 0; i < readableArray.size(); i++) {
                        ReadableMap map = readableArray.getMap(i);
                        FastImageSource imageSource = FastImageViewConverter.getImageSource(currentActivity, map);
                        int i2 = Integer.MIN_VALUE;
                        int i3 = map.hasKey("preloadWidth") ? map.getInt("preloadWidth") : Integer.MIN_VALUE;
                        if (map.hasKey("preloadHeight")) {
                            i2 = map.getInt("preloadHeight");
                        }
                        RequestManager with = Glide.with(currentActivity.getApplicationContext());
                        if (imageSource.isBase64Resource()) {
                            obj = imageSource.getSource();
                        } else {
                            obj = imageSource.isResource() ? imageSource.getUri() : imageSource.getGlideUrl();
                        }
                        with.load(obj).apply((BaseRequestOptions<?>) FastImageViewConverter.getOptions(currentActivity, imageSource, map)).preload(i3, i2);
                    }
                }
            });
        }
    }

    @ReactMethod
    public void clearMemoryCache(final Promise promise) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.resolve((Object) null);
        } else {
            currentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Glide.get(currentActivity.getApplicationContext()).clearMemory();
                    promise.resolve((Object) null);
                }
            });
        }
    }

    @ReactMethod
    public void clearDiskCache(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.resolve((Object) null);
            return;
        }
        Glide.get(currentActivity.getApplicationContext()).clearDiskCache();
        promise.resolve((Object) null);
    }
}
