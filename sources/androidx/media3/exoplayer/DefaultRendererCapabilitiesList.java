package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.common.Metadata;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.SystemClock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import java.util.Arrays;

public final class DefaultRendererCapabilitiesList implements RendererCapabilitiesList {
    private final Renderer[] renderers;

    public static final class Factory implements RendererCapabilitiesList.Factory {
        private final RenderersFactory renderersFactory;

        static /* synthetic */ void lambda$createRendererCapabilitiesList$0(CueGroup cueGroup) {
        }

        static /* synthetic */ void lambda$createRendererCapabilitiesList$1(Metadata metadata) {
        }

        public Factory(Context context) {
            this.renderersFactory = new DefaultRenderersFactory(context);
        }

        public Factory(RenderersFactory renderersFactory2) {
            this.renderersFactory = renderersFactory2;
        }

        public DefaultRendererCapabilitiesList createRendererCapabilitiesList() {
            return new DefaultRendererCapabilitiesList(this.renderersFactory.createRenderers(Util.createHandlerForCurrentOrMainLooper(), new VideoRendererEventListener() {
            }, new AudioRendererEventListener() {
            }, new DefaultRendererCapabilitiesList$Factory$$ExternalSyntheticLambda0(), new DefaultRendererCapabilitiesList$Factory$$ExternalSyntheticLambda1()));
        }
    }

    private DefaultRendererCapabilitiesList(Renderer[] rendererArr) {
        this.renderers = (Renderer[]) Arrays.copyOf(rendererArr, rendererArr.length);
        for (int i = 0; i < rendererArr.length; i++) {
            this.renderers[i].init(i, PlayerId.UNSET, SystemClock.DEFAULT);
        }
    }

    public RendererCapabilities[] getRendererCapabilities() {
        RendererCapabilities[] rendererCapabilitiesArr = new RendererCapabilities[this.renderers.length];
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return rendererCapabilitiesArr;
            }
            rendererCapabilitiesArr[i] = rendererArr[i].getCapabilities();
            i++;
        }
    }

    public int size() {
        return this.renderers.length;
    }

    public void release() {
        for (Renderer release : this.renderers) {
            release.release();
        }
    }
}
