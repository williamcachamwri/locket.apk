package com.facebook.imagepipeline.image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class BaseCloseableImage implements CloseableImage {
    private static final Set<String> mImageExtrasList = new HashSet(Arrays.asList(new String[]{"encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config", "is_rounded", "non_fatal_decode_error", "modified_url", "image_color_space"}));
    @Nullable
    private ImageInfo mCacheImageInfo;
    private Map<String, Object> mExtras = new HashMap();

    public boolean isStateful() {
        return false;
    }

    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    public void putExtras(@Nullable Map<String, ?> map) {
        if (map != null) {
            for (String next : mImageExtrasList) {
                Object obj = map.get(next);
                if (obj != null) {
                    this.mExtras.put(next, obj);
                }
            }
        }
    }

    public <E> void putExtra(String str, @Nullable E e) {
        if (mImageExtrasList.contains(str)) {
            this.mExtras.put(str, e);
        }
    }

    public <T> T getExtra(String str) {
        return getExtra(str, (Object) null);
    }

    public <T> T getExtra(String str, @Nullable T t) {
        T t2 = this.mExtras.get(str);
        return t2 == null ? t : t2;
    }

    public ImageInfo getImageInfo() {
        if (this.mCacheImageInfo == null) {
            this.mCacheImageInfo = new ImageInfoImpl(getWidth(), getHeight(), getSizeInBytes(), getQualityInfo(), getExtras());
        }
        return this.mCacheImageInfo;
    }
}
