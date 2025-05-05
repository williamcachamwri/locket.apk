package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzi implements zza {
    private String appState;
    private String eventId;
    private long nativeTime;
    private zzbb nativeViewBounds;
    private boolean nativeViewHidden;
    private zzbb nativeViewVisibleBounds;
    private double nativeVolume;
    private String queryId;
    private byte set$0;

    zzi() {
    }

    public zza appState(String str) {
        if (str != null) {
            this.appState = str;
            return this;
        }
        throw new NullPointerException("Null appState");
    }

    public zzb build() {
        String str;
        String str2;
        String str3;
        zzbb zzbb;
        zzbb zzbb2;
        if (this.set$0 == 7 && (str = this.queryId) != null && (str2 = this.eventId) != null && (str3 = this.appState) != null && (zzbb = this.nativeViewBounds) != null && (zzbb2 = this.nativeViewVisibleBounds) != null) {
            return new zzk(str, str2, str3, this.nativeTime, this.nativeVolume, this.nativeViewHidden, zzbb, zzbb2);
        }
        StringBuilder sb = new StringBuilder();
        if (this.queryId == null) {
            sb.append(" queryId");
        }
        if (this.eventId == null) {
            sb.append(" eventId");
        }
        if (this.appState == null) {
            sb.append(" appState");
        }
        if ((this.set$0 & 1) == 0) {
            sb.append(" nativeTime");
        }
        if ((this.set$0 & 2) == 0) {
            sb.append(" nativeVolume");
        }
        if ((this.set$0 & 4) == 0) {
            sb.append(" nativeViewHidden");
        }
        if (this.nativeViewBounds == null) {
            sb.append(" nativeViewBounds");
        }
        if (this.nativeViewVisibleBounds == null) {
            sb.append(" nativeViewVisibleBounds");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public zza eventId(String str) {
        if (str != null) {
            this.eventId = str;
            return this;
        }
        throw new NullPointerException("Null eventId");
    }

    public zza nativeTime(long j) {
        this.nativeTime = j;
        this.set$0 = (byte) (this.set$0 | 1);
        return this;
    }

    public zza nativeViewBounds(zzbb zzbb) {
        if (zzbb != null) {
            this.nativeViewBounds = zzbb;
            return this;
        }
        throw new NullPointerException("Null nativeViewBounds");
    }

    public zza nativeViewHidden(boolean z) {
        this.nativeViewHidden = z;
        this.set$0 = (byte) (this.set$0 | 4);
        return this;
    }

    public zza nativeViewVisibleBounds(zzbb zzbb) {
        if (zzbb != null) {
            this.nativeViewVisibleBounds = zzbb;
            return this;
        }
        throw new NullPointerException("Null nativeViewVisibleBounds");
    }

    public zza nativeVolume(double d) {
        this.nativeVolume = d;
        this.set$0 = (byte) (this.set$0 | 2);
        return this;
    }

    public zza queryId(String str) {
        if (str != null) {
            this.queryId = str;
            return this;
        }
        throw new NullPointerException("Null queryId");
    }
}
