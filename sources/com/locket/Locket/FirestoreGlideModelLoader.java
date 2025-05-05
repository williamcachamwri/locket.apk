package com.locket.Locket;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import java.io.InputStream;
import java.util.List;

public class FirestoreGlideModelLoader extends BaseGlideUrlLoader<GlideUrl> {
    protected FirestoreGlideModelLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        super(modelLoader);
    }

    protected FirestoreGlideModelLoader(ModelLoader<GlideUrl, InputStream> modelLoader, ModelCache<GlideUrl, GlideUrl> modelCache) {
        super(modelLoader, modelCache);
    }

    /* access modifiers changed from: protected */
    public String getUrl(GlideUrl glideUrl, int i, int i2, Options options) {
        return glideUrl.toStringUrl();
    }

    public boolean handles(GlideUrl glideUrl) {
        return glideUrl.toStringUrl().startsWith(Util.IMAGE_BASE_PATH);
    }

    /* access modifiers changed from: protected */
    public List<String> getAlternateUrls(GlideUrl glideUrl, int i, int i2, Options options) {
        return CdnGlideModelLoader$$ExternalSyntheticBackport0.m(new Object[]{Util.IMAGE_BASE_PATH_WITH_PORT.concat(glideUrl.toStringUrl().substring(39))});
    }

    public static final class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        public void teardown() {
        }

        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new FirestoreGlideModelLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
