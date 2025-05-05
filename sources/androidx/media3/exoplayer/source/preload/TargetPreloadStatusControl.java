package androidx.media3.exoplayer.source.preload;

public interface TargetPreloadStatusControl<T> {

    public interface PreloadStatus {
        int getStage();

        long getValue();
    }

    PreloadStatus getTargetPreloadStatus(T t);
}
