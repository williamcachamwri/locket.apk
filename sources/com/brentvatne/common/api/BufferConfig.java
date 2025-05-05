package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R\u001a\u0010'\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\b¨\u0006/"}, d2 = {"Lcom/brentvatne/common/api/BufferConfig;", "", "()V", "backBufferDurationMs", "", "getBackBufferDurationMs", "()I", "setBackBufferDurationMs", "(I)V", "bufferForPlaybackAfterRebufferMs", "getBufferForPlaybackAfterRebufferMs", "setBufferForPlaybackAfterRebufferMs", "bufferForPlaybackMs", "getBufferForPlaybackMs", "setBufferForPlaybackMs", "cacheSize", "getCacheSize", "setCacheSize", "live", "Lcom/brentvatne/common/api/BufferConfig$Live;", "getLive", "()Lcom/brentvatne/common/api/BufferConfig$Live;", "setLive", "(Lcom/brentvatne/common/api/BufferConfig$Live;)V", "maxBufferMs", "getMaxBufferMs", "setMaxBufferMs", "maxHeapAllocationPercent", "", "getMaxHeapAllocationPercent", "()D", "setMaxHeapAllocationPercent", "(D)V", "minBackBufferMemoryReservePercent", "getMinBackBufferMemoryReservePercent", "setMinBackBufferMemoryReservePercent", "minBufferMemoryReservePercent", "getMinBufferMemoryReservePercent", "setMinBufferMemoryReservePercent", "minBufferMs", "getMinBufferMs", "setMinBufferMs", "equals", "", "other", "Companion", "Live", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BufferConfig.kt */
public final class BufferConfig {
    /* access modifiers changed from: private */
    public static final double BufferConfigPropUnsetDouble = -1.0d;
    /* access modifiers changed from: private */
    public static final int BufferConfigPropUnsetInt = -1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_BUFFER_CONFIG_BACK_BUFFER_DURATION_MS = "backBufferDurationMs";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = "bufferForPlaybackAfterRebufferMs";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS = "bufferForPlaybackMs";
    private static final String PROP_BUFFER_CONFIG_CACHE_SIZE = "cacheSizeMB";
    private static final String PROP_BUFFER_CONFIG_LIVE = "live";
    private static final String PROP_BUFFER_CONFIG_MAX_BUFFER_MS = "maxBufferMs";
    private static final String PROP_BUFFER_CONFIG_MAX_HEAP_ALLOCATION_PERCENT = "maxHeapAllocationPercent";
    private static final String PROP_BUFFER_CONFIG_MIN_BACK_BUFFER_MEMORY_RESERVE_PERCENT = "minBackBufferMemoryReservePercent";
    private static final String PROP_BUFFER_CONFIG_MIN_BUFFER_MEMORY_RESERVE_PERCENT = "minBufferMemoryReservePercent";
    private static final String PROP_BUFFER_CONFIG_MIN_BUFFER_MS = "minBufferMs";
    private int backBufferDurationMs;
    private int bufferForPlaybackAfterRebufferMs;
    private int bufferForPlaybackMs;
    private int cacheSize;
    private Live live = new Live();
    private int maxBufferMs;
    private double maxHeapAllocationPercent;
    private double minBackBufferMemoryReservePercent;
    private double minBufferMemoryReservePercent;
    private int minBufferMs;

    @JvmStatic
    public static final BufferConfig parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public BufferConfig() {
        int i = BufferConfigPropUnsetInt;
        this.cacheSize = i;
        this.minBufferMs = i;
        this.maxBufferMs = i;
        this.bufferForPlaybackMs = i;
        this.bufferForPlaybackAfterRebufferMs = i;
        this.backBufferDurationMs = i;
        double d = BufferConfigPropUnsetDouble;
        this.maxHeapAllocationPercent = d;
        this.minBackBufferMemoryReservePercent = d;
        this.minBufferMemoryReservePercent = d;
    }

    public final int getCacheSize() {
        return this.cacheSize;
    }

    public final void setCacheSize(int i) {
        this.cacheSize = i;
    }

    public final int getMinBufferMs() {
        return this.minBufferMs;
    }

    public final void setMinBufferMs(int i) {
        this.minBufferMs = i;
    }

    public final int getMaxBufferMs() {
        return this.maxBufferMs;
    }

    public final void setMaxBufferMs(int i) {
        this.maxBufferMs = i;
    }

    public final int getBufferForPlaybackMs() {
        return this.bufferForPlaybackMs;
    }

    public final void setBufferForPlaybackMs(int i) {
        this.bufferForPlaybackMs = i;
    }

    public final int getBufferForPlaybackAfterRebufferMs() {
        return this.bufferForPlaybackAfterRebufferMs;
    }

    public final void setBufferForPlaybackAfterRebufferMs(int i) {
        this.bufferForPlaybackAfterRebufferMs = i;
    }

    public final int getBackBufferDurationMs() {
        return this.backBufferDurationMs;
    }

    public final void setBackBufferDurationMs(int i) {
        this.backBufferDurationMs = i;
    }

    public final double getMaxHeapAllocationPercent() {
        return this.maxHeapAllocationPercent;
    }

    public final void setMaxHeapAllocationPercent(double d) {
        this.maxHeapAllocationPercent = d;
    }

    public final double getMinBackBufferMemoryReservePercent() {
        return this.minBackBufferMemoryReservePercent;
    }

    public final void setMinBackBufferMemoryReservePercent(double d) {
        this.minBackBufferMemoryReservePercent = d;
    }

    public final double getMinBufferMemoryReservePercent() {
        return this.minBufferMemoryReservePercent;
    }

    public final void setMinBufferMemoryReservePercent(double d) {
        this.minBufferMemoryReservePercent = d;
    }

    public final Live getLive() {
        return this.live;
    }

    public final void setLive(Live live2) {
        Intrinsics.checkNotNullParameter(live2, "<set-?>");
        this.live = live2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BufferConfig)) {
            return false;
        }
        BufferConfig bufferConfig = (BufferConfig) obj;
        if (this.cacheSize != bufferConfig.cacheSize || this.minBufferMs != bufferConfig.minBufferMs || this.maxBufferMs != bufferConfig.maxBufferMs || this.bufferForPlaybackMs != bufferConfig.bufferForPlaybackMs || this.bufferForPlaybackAfterRebufferMs != bufferConfig.bufferForPlaybackAfterRebufferMs || this.backBufferDurationMs != bufferConfig.backBufferDurationMs) {
            return false;
        }
        if (!(this.maxHeapAllocationPercent == bufferConfig.maxHeapAllocationPercent)) {
            return false;
        }
        if (!(this.minBackBufferMemoryReservePercent == bufferConfig.minBackBufferMemoryReservePercent)) {
            return false;
        }
        if (!(this.minBufferMemoryReservePercent == bufferConfig.minBufferMemoryReservePercent) || !Intrinsics.areEqual((Object) this.live, (Object) bufferConfig.live)) {
            return false;
        }
        return true;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/brentvatne/common/api/BufferConfig$Live;", "", "()V", "maxOffsetMs", "", "getMaxOffsetMs", "()J", "setMaxOffsetMs", "(J)V", "maxPlaybackSpeed", "", "getMaxPlaybackSpeed", "()F", "setMaxPlaybackSpeed", "(F)V", "minOffsetMs", "getMinOffsetMs", "setMinOffsetMs", "minPlaybackSpeed", "getMinPlaybackSpeed", "setMinPlaybackSpeed", "targetOffsetMs", "getTargetOffsetMs", "setTargetOffsetMs", "equals", "", "other", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BufferConfig.kt */
    public static final class Live {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final String PROP_BUFFER_CONFIG_LIVE_MAX_OFFSET_MS = "maxOffsetMs";
        private static final String PROP_BUFFER_CONFIG_LIVE_MAX_PLAYBACK_SPEED = "maxPlaybackSpeed";
        private static final String PROP_BUFFER_CONFIG_LIVE_MIN_OFFSET_MS = "minOffsetMs";
        private static final String PROP_BUFFER_CONFIG_LIVE_MIN_PLAYBACK_SPEED = "minPlaybackSpeed";
        private static final String PROP_BUFFER_CONFIG_LIVE_TARGET_OFFSET_MS = "targetOffsetMs";
        private long maxOffsetMs = ((long) BufferConfig.Companion.getBufferConfigPropUnsetInt());
        private float maxPlaybackSpeed = ((float) BufferConfig.Companion.getBufferConfigPropUnsetDouble());
        private long minOffsetMs = ((long) BufferConfig.Companion.getBufferConfigPropUnsetInt());
        private float minPlaybackSpeed = ((float) BufferConfig.Companion.getBufferConfigPropUnsetDouble());
        private long targetOffsetMs = ((long) BufferConfig.Companion.getBufferConfigPropUnsetInt());

        @JvmStatic
        public static final Live parse(ReadableMap readableMap) {
            return Companion.parse(readableMap);
        }

        public final float getMaxPlaybackSpeed() {
            return this.maxPlaybackSpeed;
        }

        public final void setMaxPlaybackSpeed(float f) {
            this.maxPlaybackSpeed = f;
        }

        public final float getMinPlaybackSpeed() {
            return this.minPlaybackSpeed;
        }

        public final void setMinPlaybackSpeed(float f) {
            this.minPlaybackSpeed = f;
        }

        public final long getMaxOffsetMs() {
            return this.maxOffsetMs;
        }

        public final void setMaxOffsetMs(long j) {
            this.maxOffsetMs = j;
        }

        public final long getMinOffsetMs() {
            return this.minOffsetMs;
        }

        public final void setMinOffsetMs(long j) {
            this.minOffsetMs = j;
        }

        public final long getTargetOffsetMs() {
            return this.targetOffsetMs;
        }

        public final void setTargetOffsetMs(long j) {
            this.targetOffsetMs = j;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Live)) {
                return false;
            }
            Live live = (Live) obj;
            if (!(this.maxPlaybackSpeed == live.maxPlaybackSpeed)) {
                return false;
            }
            if ((this.minPlaybackSpeed == live.minPlaybackSpeed) && this.maxOffsetMs == live.maxOffsetMs && this.minOffsetMs == live.minOffsetMs && this.targetOffsetMs == live.targetOffsetMs) {
                return true;
            }
            return false;
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/brentvatne/common/api/BufferConfig$Live$Companion;", "", "()V", "PROP_BUFFER_CONFIG_LIVE_MAX_OFFSET_MS", "", "PROP_BUFFER_CONFIG_LIVE_MAX_PLAYBACK_SPEED", "PROP_BUFFER_CONFIG_LIVE_MIN_OFFSET_MS", "PROP_BUFFER_CONFIG_LIVE_MIN_PLAYBACK_SPEED", "PROP_BUFFER_CONFIG_LIVE_TARGET_OFFSET_MS", "parse", "Lcom/brentvatne/common/api/BufferConfig$Live;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: BufferConfig.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Live parse(ReadableMap readableMap) {
                Live live = new Live();
                live.setMaxPlaybackSpeed(ReactBridgeUtils.safeGetFloat(readableMap, Live.PROP_BUFFER_CONFIG_LIVE_MAX_PLAYBACK_SPEED, (float) BufferConfig.Companion.getBufferConfigPropUnsetDouble()));
                live.setMinPlaybackSpeed(ReactBridgeUtils.safeGetFloat(readableMap, Live.PROP_BUFFER_CONFIG_LIVE_MIN_PLAYBACK_SPEED, (float) BufferConfig.Companion.getBufferConfigPropUnsetDouble()));
                live.setMaxOffsetMs((long) ReactBridgeUtils.safeGetInt(readableMap, Live.PROP_BUFFER_CONFIG_LIVE_MAX_OFFSET_MS, BufferConfig.Companion.getBufferConfigPropUnsetInt()));
                live.setMinOffsetMs((long) ReactBridgeUtils.safeGetInt(readableMap, Live.PROP_BUFFER_CONFIG_LIVE_MIN_OFFSET_MS, BufferConfig.Companion.getBufferConfigPropUnsetInt()));
                live.setTargetOffsetMs((long) ReactBridgeUtils.safeGetInt(readableMap, Live.PROP_BUFFER_CONFIG_LIVE_TARGET_OFFSET_MS, BufferConfig.Companion.getBufferConfigPropUnsetInt()));
                return live;
            }
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fXT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/brentvatne/common/api/BufferConfig$Companion;", "", "()V", "BufferConfigPropUnsetDouble", "", "getBufferConfigPropUnsetDouble", "()D", "BufferConfigPropUnsetInt", "", "getBufferConfigPropUnsetInt", "()I", "PROP_BUFFER_CONFIG_BACK_BUFFER_DURATION_MS", "", "PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS", "PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS", "PROP_BUFFER_CONFIG_CACHE_SIZE", "PROP_BUFFER_CONFIG_LIVE", "PROP_BUFFER_CONFIG_MAX_BUFFER_MS", "PROP_BUFFER_CONFIG_MAX_HEAP_ALLOCATION_PERCENT", "PROP_BUFFER_CONFIG_MIN_BACK_BUFFER_MEMORY_RESERVE_PERCENT", "PROP_BUFFER_CONFIG_MIN_BUFFER_MEMORY_RESERVE_PERCENT", "PROP_BUFFER_CONFIG_MIN_BUFFER_MS", "parse", "Lcom/brentvatne/common/api/BufferConfig;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BufferConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getBufferConfigPropUnsetInt() {
            return BufferConfig.BufferConfigPropUnsetInt;
        }

        public final double getBufferConfigPropUnsetDouble() {
            return BufferConfig.BufferConfigPropUnsetDouble;
        }

        @JvmStatic
        public final BufferConfig parse(ReadableMap readableMap) {
            BufferConfig bufferConfig = new BufferConfig();
            if (readableMap != null) {
                bufferConfig.setCacheSize(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_CACHE_SIZE, getBufferConfigPropUnsetInt()));
                bufferConfig.setMinBufferMs(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_MIN_BUFFER_MS, getBufferConfigPropUnsetInt()));
                bufferConfig.setMaxBufferMs(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_MAX_BUFFER_MS, getBufferConfigPropUnsetInt()));
                bufferConfig.setBufferForPlaybackMs(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS, getBufferConfigPropUnsetInt()));
                bufferConfig.setBufferForPlaybackAfterRebufferMs(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS, getBufferConfigPropUnsetInt()));
                bufferConfig.setMaxHeapAllocationPercent(ReactBridgeUtils.safeGetDouble(readableMap, BufferConfig.PROP_BUFFER_CONFIG_MAX_HEAP_ALLOCATION_PERCENT, getBufferConfigPropUnsetDouble()));
                bufferConfig.setMinBackBufferMemoryReservePercent(ReactBridgeUtils.safeGetDouble(readableMap, BufferConfig.PROP_BUFFER_CONFIG_MIN_BACK_BUFFER_MEMORY_RESERVE_PERCENT, getBufferConfigPropUnsetDouble()));
                bufferConfig.setMinBufferMemoryReservePercent(ReactBridgeUtils.safeGetDouble(readableMap, BufferConfig.PROP_BUFFER_CONFIG_MIN_BUFFER_MEMORY_RESERVE_PERCENT, getBufferConfigPropUnsetDouble()));
                bufferConfig.setBackBufferDurationMs(ReactBridgeUtils.safeGetInt(readableMap, BufferConfig.PROP_BUFFER_CONFIG_BACK_BUFFER_DURATION_MS, getBufferConfigPropUnsetInt()));
                bufferConfig.setLive(Live.Companion.parse(readableMap.getMap(BufferConfig.PROP_BUFFER_CONFIG_LIVE)));
            }
            return bufferConfig;
        }
    }
}
