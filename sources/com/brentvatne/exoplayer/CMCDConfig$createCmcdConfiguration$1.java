package com.brentvatne.exoplayer;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.google.common.collect.ImmutableListMultimap;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/brentvatne/exoplayer/CMCDConfig$createCmcdConfiguration$1", "Landroidx/media3/exoplayer/upstream/CmcdConfiguration$RequestConfig;", "getCustomData", "Lcom/google/common/collect/ImmutableListMultimap;", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CMCDConfig.kt */
public final class CMCDConfig$createCmcdConfiguration$1 implements CmcdConfiguration.RequestConfig {
    final /* synthetic */ CMCDConfig this$0;

    CMCDConfig$createCmcdConfiguration$1(CMCDConfig cMCDConfig) {
        this.this$0 = cMCDConfig;
    }

    public ImmutableListMultimap<String, String> getCustomData() {
        return this.this$0.buildCustomData();
    }
}
