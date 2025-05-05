package androidx.media3.transformer;

import android.util.Pair;
import androidx.media3.common.Effect;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.SpeedChangingAudioProcessor;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.effect.TimestampAdjustment;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;

public final class Effects {
    public static final Effects EMPTY = new Effects(ImmutableList.of(), ImmutableList.of());
    public final ImmutableList<AudioProcessor> audioProcessors;
    public final ImmutableList<Effect> videoEffects;

    public Effects(List<AudioProcessor> list, List<Effect> list2) {
        this.audioProcessors = ImmutableList.copyOf(list);
        this.videoEffects = ImmutableList.copyOf(list2);
    }

    public static Pair<AudioProcessor, Effect> createExperimentalSpeedChangingEffect(SpeedProvider speedProvider) {
        SpeedChangingAudioProcessor speedChangingAudioProcessor = new SpeedChangingAudioProcessor(speedProvider);
        Objects.requireNonNull(speedChangingAudioProcessor);
        return Pair.create(speedChangingAudioProcessor, new TimestampAdjustment(new Effects$$ExternalSyntheticLambda0(speedChangingAudioProcessor), speedProvider));
    }
}
