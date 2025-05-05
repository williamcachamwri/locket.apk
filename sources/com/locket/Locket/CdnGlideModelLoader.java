package com.locket.Locket;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class CdnGlideModelLoader extends BaseGlideUrlLoader<GlideUrl> {
    protected CdnGlideModelLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        super(modelLoader);
    }

    protected CdnGlideModelLoader(ModelLoader<GlideUrl, InputStream> modelLoader, ModelCache<GlideUrl, GlideUrl> modelCache) {
        super(modelLoader, modelCache);
    }

    /* access modifiers changed from: protected */
    public String getUrl(GlideUrl glideUrl, int i, int i2, Options options) {
        return glideUrl.toStringUrl();
    }

    public boolean handles(GlideUrl glideUrl) {
        return shouldHandleUrl(glideUrl.toStringUrl(), RemoteConfig.androidCdnBaseUrl());
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(GlideUrl glideUrl, int i, int i2, Options options) {
        ModelLoader.LoadData<InputStream> buildLoadData = super.buildLoadData(glideUrl, i, i2, options);
        if (buildLoadData == null) {
            return null;
        }
        String stringUrl = glideUrl.toStringUrl();
        String androidCdnBaseUrl = RemoteConfig.androidCdnBaseUrl();
        return shouldHandleUrl(stringUrl, androidCdnBaseUrl) ? new ModelLoader.LoadData<>(new GlideUrl(Util.IMAGE_BASE_PATH_WITH_PORT.concat(stringUrl.substring(androidCdnBaseUrl.length()))), buildLoadData.alternateKeys, buildLoadData.fetcher) : buildLoadData;
    }

    /* access modifiers changed from: protected */
    public List<String> getAlternateUrls(GlideUrl glideUrl, int i, int i2, Options options) {
        String stringUrl = glideUrl.toStringUrl();
        String androidCdnBaseUrl = RemoteConfig.androidCdnBaseUrl();
        if (shouldHandleUrl(stringUrl, androidCdnBaseUrl)) {
            return CdnGlideModelLoader$$ExternalSyntheticBackport0.m(new Object[]{Util.IMAGE_BASE_PATH.concat(stringUrl.substring(androidCdnBaseUrl.length()))});
        }
        return Collections.emptyList();
    }

    private static boolean shouldHandleUrl(String str, String str2) {
        return !CdnGlideModelLoader$$ExternalSyntheticBackport0.m(str2) && str2.endsWith("/") && str.startsWith(str2);
    }

    public static final class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        public void teardown() {
        }

        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new CdnGlideModelLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
