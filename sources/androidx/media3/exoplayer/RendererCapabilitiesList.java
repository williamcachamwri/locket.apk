package androidx.media3.exoplayer;

public interface RendererCapabilitiesList {

    public interface Factory {
        RendererCapabilitiesList createRendererCapabilitiesList();
    }

    RendererCapabilities[] getRendererCapabilities();

    void release();

    int size();
}
