package com.brentvatne.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import com.brentvatne.common.api.BufferConfig;
import com.brentvatne.common.api.Source;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/brentvatne/exoplayer/ConfigurationUtils;", "", "()V", "buildCustomMetadata", "Landroidx/media3/common/MediaMetadata;", "metadata", "Lcom/brentvatne/common/api/Source$Metadata;", "getLiveConfiguration", "Landroidx/media3/common/MediaItem$LiveConfiguration$Builder;", "bufferConfig", "Lcom/brentvatne/common/api/BufferConfig;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConfigurationUtils.kt */
public final class ConfigurationUtils {
    public static final ConfigurationUtils INSTANCE = new ConfigurationUtils();

    private ConfigurationUtils() {
    }

    @JvmStatic
    public static final MediaItem.LiveConfiguration.Builder getLiveConfiguration(BufferConfig bufferConfig) {
        Intrinsics.checkNotNullParameter(bufferConfig, "bufferConfig");
        MediaItem.LiveConfiguration.Builder builder = new MediaItem.LiveConfiguration.Builder();
        BufferConfig.Live live = bufferConfig.getLive();
        if (bufferConfig.getLive().getMaxOffsetMs() >= 0) {
            builder.setMaxOffsetMs(live.getMaxOffsetMs());
        }
        if (bufferConfig.getLive().getMaxPlaybackSpeed() >= 0.0f) {
            builder.setMaxPlaybackSpeed(live.getMaxPlaybackSpeed());
        }
        if (bufferConfig.getLive().getTargetOffsetMs() >= 0) {
            builder.setTargetOffsetMs(live.getTargetOffsetMs());
        }
        if (bufferConfig.getLive().getMinOffsetMs() >= 0) {
            builder.setMinOffsetMs(live.getMinOffsetMs());
        }
        if (bufferConfig.getLive().getMinPlaybackSpeed() >= 0.0f) {
            builder.setMinPlaybackSpeed(live.getMinPlaybackSpeed());
        }
        return builder;
    }

    @JvmStatic
    public static final MediaMetadata buildCustomMetadata(Source.Metadata metadata) {
        if (metadata != null) {
            return new MediaMetadata.Builder().setTitle(metadata.getTitle()).setSubtitle(metadata.getSubtitle()).setDescription(metadata.getDescription()).setArtist(metadata.getArtist()).setArtworkUri(metadata.getImageUri()).build();
        }
        return null;
    }
}
