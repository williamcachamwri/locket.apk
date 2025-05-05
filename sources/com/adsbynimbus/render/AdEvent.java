package com.adsbynimbus.render;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/adsbynimbus/render/AdEvent;", "", "(Ljava/lang/String;I)V", "LOADED", "IMPRESSION", "CLICKED", "PAUSED", "RESUMED", "FIRST_QUARTILE", "MIDPOINT", "THIRD_QUARTILE", "COMPLETED", "SKIPPED", "DESTROYED", "VOLUME_CHANGED", "Listener", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdEvent.kt */
public enum AdEvent {
    LOADED,
    IMPRESSION,
    CLICKED,
    PAUSED,
    RESUMED,
    FIRST_QUARTILE,
    MIDPOINT,
    THIRD_QUARTILE,
    COMPLETED,
    SKIPPED,
    DESTROYED,
    VOLUME_CHANGED;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/AdEvent$Listener;", "", "onAdEvent", "", "adEvent", "Lcom/adsbynimbus/render/AdEvent;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AdEvent.kt */
    public interface Listener {
        void onAdEvent(AdEvent adEvent);
    }
}
