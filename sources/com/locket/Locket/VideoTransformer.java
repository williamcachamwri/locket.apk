package com.locket.Locket;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.effect.Crop;
import androidx.media3.transformer.Composition;
import androidx.media3.transformer.EditedMediaItem;
import androidx.media3.transformer.Effects;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.Transformer;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.common.collect.ImmutableList;
import java.io.File;

public class VideoTransformer extends ReactContextBaseJavaModule {
    ReactApplicationContext context;

    public String getName() {
        return "VideoTransformer";
    }

    VideoTransformer(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
    }

    @ReactMethod
    public void cropVideo(String str, float f, float f2, float f3, float f4, final Promise promise) {
        try {
            EditedMediaItem build = new EditedMediaItem.Builder(MediaItem.fromUri(str)).setEffects(new Effects(ImmutableList.of(), ImmutableList.of(new Crop(f, f2, f3, f4)))).build();
            final File createTempFile = File.createTempFile("videoupload", ".mp4", this.context.getCacheDir());
            new Transformer.Builder((Context) this.context).addListener(new Transformer.Listener() {
                public void onCompleted(Composition composition, ExportResult exportResult) {
                    promise.resolve(createTempFile.getPath());
                }

                public void onError(Composition composition, ExportResult exportResult, ExportException exportException) {
                    if (exportException.codecInfo != null) {
                        promise.reject(exportException.getErrorCodeName(), exportException.codecInfo.toString(), exportException.getCause());
                    } else {
                        promise.reject(exportException.getErrorCodeName(), (Throwable) exportException);
                    }
                }
            }).build().start(build, createTempFile.getPath());
        } catch (Exception e) {
            promise.reject("Failed to start video cropping", (Throwable) e);
        }
    }
}
