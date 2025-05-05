package com.brentvatne.exoplayer;

import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.drm.FrameworkMediaDrm;
import java.util.UUID;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactExoplayerView$$ExternalSyntheticLambda14 implements ExoMediaDrm.Provider {
    public final /* synthetic */ FrameworkMediaDrm f$0;

    public /* synthetic */ ReactExoplayerView$$ExternalSyntheticLambda14(FrameworkMediaDrm frameworkMediaDrm) {
        this.f$0 = frameworkMediaDrm;
    }

    public final ExoMediaDrm acquireExoMediaDrm(UUID uuid) {
        return ReactExoplayerView.lambda$buildDrmSessionManager$15(this.f$0, uuid);
    }
}
