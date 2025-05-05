package androidx.media3.transformer;

import android.os.Looper;
import androidx.media3.common.Format;
import com.google.common.collect.ImmutableMap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface AssetLoader {
    public static final int SUPPORTED_OUTPUT_TYPE_DECODED = 2;
    public static final int SUPPORTED_OUTPUT_TYPE_ENCODED = 1;

    public interface Factory {
        AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, Listener listener, CompositionSettings compositionSettings);
    }

    public interface Listener {
        void onDurationUs(long j);

        void onError(ExportException exportException);

        SampleConsumer onOutputFormat(Format format) throws ExportException;

        boolean onTrackAdded(Format format, int i);

        void onTrackCount(int i);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SupportedOutputTypes {
    }

    ImmutableMap<Integer, String> getDecoderNames();

    int getProgress(ProgressHolder progressHolder);

    void release();

    void start();

    public static class CompositionSettings {
        public final int hdrMode;
        public final boolean retainHdrFromUltraHdrImage;

        public CompositionSettings(int i, boolean z) {
            this.hdrMode = i;
            this.retainHdrFromUltraHdrImage = z;
        }
    }
}
