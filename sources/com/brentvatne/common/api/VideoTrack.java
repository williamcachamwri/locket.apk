package com.brentvatne.common.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001a\u0010\u001d\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010\u000eR\u001a\u0010 \u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b¨\u0006#"}, d2 = {"Lcom/brentvatne/common/api/VideoTrack;", "", "()V", "bitrate", "", "getBitrate", "()I", "setBitrate", "(I)V", "codecs", "", "getCodecs", "()Ljava/lang/String;", "setCodecs", "(Ljava/lang/String;)V", "height", "getHeight", "setHeight", "index", "getIndex", "setIndex", "isSelected", "", "()Z", "setSelected", "(Z)V", "rotation", "getRotation", "setRotation", "trackId", "getTrackId", "setTrackId", "width", "getWidth", "setWidth", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTrack.kt */
public final class VideoTrack {
    private int bitrate;
    private String codecs = "";
    private int height;
    private int index = -1;
    private boolean isSelected;
    private int rotation;
    private String trackId = "";
    private int width;

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final int getBitrate() {
        return this.bitrate;
    }

    public final void setBitrate(int i) {
        this.bitrate = i;
    }

    public final String getCodecs() {
        return this.codecs;
    }

    public final void setCodecs(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.codecs = str;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final String getTrackId() {
        return this.trackId;
    }

    public final void setTrackId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.trackId = str;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final void setRotation(int i) {
        this.rotation = i;
    }
}
