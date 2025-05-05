package androidx.media3.exoplayer;

public interface SuitableOutputChecker {

    public interface Callback {
        void onSelectedOutputSuitabilityChanged(boolean z);
    }

    void disable();

    void enable(Callback callback);

    boolean isSelectedOutputSuitableForPlayback();
}
