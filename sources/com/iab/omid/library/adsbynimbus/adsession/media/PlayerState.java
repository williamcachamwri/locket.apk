package com.iab.omid.library.adsbynimbus.adsession.media;

import com.adsbynimbus.render.mraid.HostKt;
import io.sentry.ProfilingTraceData;

public enum PlayerState {
    MINIMIZED("minimized"),
    COLLAPSED("collapsed"),
    NORMAL(ProfilingTraceData.TRUNCATION_REASON_NORMAL),
    EXPANDED(HostKt.EXPANDED),
    FULLSCREEN("fullscreen");
    
    private final String playerState;

    private PlayerState(String str) {
        this.playerState = str;
    }

    public String toString() {
        return this.playerState;
    }
}
