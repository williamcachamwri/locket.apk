package com.brentvatne.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.brentvatne.common.api.CMCDProps;
import com.brentvatne.common.toolbox.DebugLog;
import com.google.common.collect.ImmutableListMultimap;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J>\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\t2\u0018\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\r0\fH\u0002J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/brentvatne/exoplayer/CMCDConfig;", "", "props", "Lcom/brentvatne/common/api/CMCDProps;", "(Lcom/brentvatne/common/api/CMCDProps;)V", "addFormattedData", "", "builder", "Lcom/google/common/collect/ImmutableListMultimap$Builder;", "", "key", "dataList", "", "Lkotlin/Pair;", "buildCustomData", "Lcom/google/common/collect/ImmutableListMultimap;", "createCmcdConfiguration", "Landroidx/media3/exoplayer/upstream/CmcdConfiguration;", "mediaItem", "Landroidx/media3/common/MediaItem;", "formatKeyValue", "value", "intToCmcdMode", "", "mode", "toCmcdConfigurationFactory", "Landroidx/media3/exoplayer/upstream/CmcdConfiguration$Factory;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CMCDConfig.kt */
public final class CMCDConfig {
    private final CMCDProps props;

    public CMCDConfig(CMCDProps cMCDProps) {
        Intrinsics.checkNotNullParameter(cMCDProps, "props");
        this.props = cMCDProps;
    }

    public final CmcdConfiguration.Factory toCmcdConfigurationFactory() {
        return new CMCDConfig$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: private */
    public final CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem) {
        return new CmcdConfiguration(UUID.randomUUID().toString(), mediaItem.mediaId, new CMCDConfig$createCmcdConfiguration$1(this), intToCmcdMode(this.props.getMode()));
    }

    private final int intToCmcdMode(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        DebugLog.e("CMCDConfig", "Unsupported mode: " + i + ", fallback on MODE_REQUEST_HEADER");
        return 0;
    }

    /* access modifiers changed from: private */
    public final ImmutableListMultimap<String, String> buildCustomData() {
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        Intrinsics.checkNotNull(builder);
        addFormattedData(builder, CmcdConfiguration.KEY_CMCD_OBJECT, this.props.getCmcdObject());
        addFormattedData(builder, CmcdConfiguration.KEY_CMCD_REQUEST, this.props.getCmcdRequest());
        addFormattedData(builder, CmcdConfiguration.KEY_CMCD_SESSION, this.props.getCmcdSession());
        addFormattedData(builder, CmcdConfiguration.KEY_CMCD_STATUS, this.props.getCmcdStatus());
        ImmutableListMultimap<String, String> build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final void addFormattedData(ImmutableListMultimap.Builder<String, String> builder, String str, List<? extends Pair<String, ? extends Object>> list) {
        for (Pair pair : list) {
            builder.put(str, formatKeyValue((String) pair.component1(), pair.component2()));
        }
    }

    private final String formatKeyValue(String str, Object obj) {
        if (obj instanceof String) {
            return str + "=\"" + obj + "\"";
        }
        if (obj instanceof Number) {
            return str + "=" + obj;
        }
        throw new IllegalArgumentException("Unsupported value type: " + obj.getClass());
    }
}
