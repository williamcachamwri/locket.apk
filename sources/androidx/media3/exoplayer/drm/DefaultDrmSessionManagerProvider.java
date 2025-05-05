package androidx.media3.exoplayer.drm;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Map;

public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    private MediaItem.DrmConfiguration drmConfiguration;
    private DataSource.Factory drmHttpDataSourceFactory;
    private LoadErrorHandlingPolicy drmLoadErrorHandlingPolicy;
    private final Object lock = new Object();
    private DrmSessionManager manager;
    private String userAgent;

    public void setDrmHttpDataSourceFactory(DataSource.Factory factory) {
        this.drmHttpDataSourceFactory = factory;
    }

    @Deprecated
    public void setDrmUserAgent(String str) {
        this.userAgent = str;
    }

    public void setDrmLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        this.drmLoadErrorHandlingPolicy = loadErrorHandlingPolicy;
    }

    public DrmSessionManager get(MediaItem mediaItem) {
        DrmSessionManager drmSessionManager;
        Assertions.checkNotNull(mediaItem.localConfiguration);
        MediaItem.DrmConfiguration drmConfiguration2 = mediaItem.localConfiguration.drmConfiguration;
        if (drmConfiguration2 == null) {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }
        synchronized (this.lock) {
            if (!Util.areEqual(drmConfiguration2, this.drmConfiguration)) {
                this.drmConfiguration = drmConfiguration2;
                this.manager = createManager(drmConfiguration2);
            }
            drmSessionManager = (DrmSessionManager) Assertions.checkNotNull(this.manager);
        }
        return drmSessionManager;
    }

    private DrmSessionManager createManager(MediaItem.DrmConfiguration drmConfiguration2) {
        DataSource.Factory factory = this.drmHttpDataSourceFactory;
        if (factory == null) {
            factory = new DefaultHttpDataSource.Factory().setUserAgent(this.userAgent);
        }
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(drmConfiguration2.licenseUri == null ? null : drmConfiguration2.licenseUri.toString(), drmConfiguration2.forceDefaultLicenseUri, factory);
        UnmodifiableIterator<Map.Entry<String, String>> it = drmConfiguration2.licenseRequestHeaders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            httpMediaDrmCallback.setKeyRequestProperty((String) next.getKey(), (String) next.getValue());
        }
        DefaultDrmSessionManager.Builder useDrmSessionsForClearContent = new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(drmConfiguration2.scheme, FrameworkMediaDrm.DEFAULT_PROVIDER).setMultiSession(drmConfiguration2.multiSession).setPlayClearSamplesWithoutKeys(drmConfiguration2.playClearContentWithoutKey).setUseDrmSessionsForClearContent(Ints.toArray(drmConfiguration2.forcedSessionTrackTypes));
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.drmLoadErrorHandlingPolicy;
        if (loadErrorHandlingPolicy != null) {
            useDrmSessionsForClearContent.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        }
        DefaultDrmSessionManager build = useDrmSessionsForClearContent.build(httpMediaDrmCallback);
        build.setMode(0, drmConfiguration2.getKeySetId());
        return build;
    }
}
