package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.base.Objects;

final class SessionPositionInfo {
    public static final SessionPositionInfo DEFAULT;
    public static final Player.PositionInfo DEFAULT_POSITION_INFO;
    private static final String FIELD_BUFFERED_PERCENTAGE = Util.intToStringMaxRadix(5);
    static final String FIELD_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(4);
    static final String FIELD_CONTENT_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(9);
    private static final String FIELD_CONTENT_DURATION_MS = Util.intToStringMaxRadix(8);
    private static final String FIELD_CURRENT_LIVE_OFFSET_MS = Util.intToStringMaxRadix(7);
    private static final String FIELD_DURATION_MS = Util.intToStringMaxRadix(3);
    private static final String FIELD_EVENT_TIME_MS = Util.intToStringMaxRadix(2);
    private static final String FIELD_IS_PLAYING_AD = Util.intToStringMaxRadix(1);
    static final String FIELD_POSITION_INFO = Util.intToStringMaxRadix(0);
    private static final String FIELD_TOTAL_BUFFERED_DURATION_MS = Util.intToStringMaxRadix(6);
    public final int bufferedPercentage;
    public final long bufferedPositionMs;
    public final long contentBufferedPositionMs;
    public final long contentDurationMs;
    public final long currentLiveOffsetMs;
    public final long durationMs;
    public final long eventTimeMs;
    public final boolean isPlayingAd;
    public final Player.PositionInfo positionInfo;
    public final long totalBufferedDurationMs;

    static {
        Player.PositionInfo positionInfo2 = r0;
        Player.PositionInfo positionInfo3 = new Player.PositionInfo((Object) null, 0, (MediaItem) null, (Object) null, 0, 0, 0, -1, -1);
        DEFAULT_POSITION_INFO = positionInfo3;
        DEFAULT = new SessionPositionInfo(positionInfo2, false, C.TIME_UNSET, C.TIME_UNSET, 0, 0, 0, C.TIME_UNSET, C.TIME_UNSET, 0);
    }

    public SessionPositionInfo(Player.PositionInfo positionInfo2, boolean z, long j, long j2, long j3, int i, long j4, long j5, long j6, long j7) {
        Player.PositionInfo positionInfo3 = positionInfo2;
        boolean z2 = z;
        Assertions.checkArgument(z2 != (positionInfo3.adGroupIndex != -1) ? false : true);
        this.positionInfo = positionInfo3;
        this.isPlayingAd = z2;
        this.eventTimeMs = j;
        this.durationMs = j2;
        this.bufferedPositionMs = j3;
        this.bufferedPercentage = i;
        this.totalBufferedDurationMs = j4;
        this.currentLiveOffsetMs = j5;
        this.contentDurationMs = j6;
        this.contentBufferedPositionMs = j7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SessionPositionInfo sessionPositionInfo = (SessionPositionInfo) obj;
        if (this.eventTimeMs == sessionPositionInfo.eventTimeMs && this.positionInfo.equals(sessionPositionInfo.positionInfo) && this.isPlayingAd == sessionPositionInfo.isPlayingAd && this.durationMs == sessionPositionInfo.durationMs && this.bufferedPositionMs == sessionPositionInfo.bufferedPositionMs && this.bufferedPercentage == sessionPositionInfo.bufferedPercentage && this.totalBufferedDurationMs == sessionPositionInfo.totalBufferedDurationMs && this.currentLiveOffsetMs == sessionPositionInfo.currentLiveOffsetMs && this.contentDurationMs == sessionPositionInfo.contentDurationMs && this.contentBufferedPositionMs == sessionPositionInfo.contentBufferedPositionMs) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.positionInfo, Boolean.valueOf(this.isPlayingAd));
    }

    public String toString() {
        return "SessionPositionInfo {PositionInfo {mediaItemIndex=" + this.positionInfo.mediaItemIndex + ", periodIndex=" + this.positionInfo.periodIndex + ", positionMs=" + this.positionInfo.positionMs + ", contentPositionMs=" + this.positionInfo.contentPositionMs + ", adGroupIndex=" + this.positionInfo.adGroupIndex + ", adIndexInAdGroup=" + this.positionInfo.adIndexInAdGroup + "}, isPlayingAd=" + this.isPlayingAd + ", eventTimeMs=" + this.eventTimeMs + ", durationMs=" + this.durationMs + ", bufferedPositionMs=" + this.bufferedPositionMs + ", bufferedPercentage=" + this.bufferedPercentage + ", totalBufferedDurationMs=" + this.totalBufferedDurationMs + ", currentLiveOffsetMs=" + this.currentLiveOffsetMs + ", contentDurationMs=" + this.contentDurationMs + ", contentBufferedPositionMs=" + this.contentBufferedPositionMs + "}";
    }

    public SessionPositionInfo filterByAvailableCommands(boolean z, boolean z2) {
        boolean z3 = z;
        boolean z4 = z2;
        if (z3 && z4) {
            return this;
        }
        Player.PositionInfo filterByAvailableCommands = this.positionInfo.filterByAvailableCommands(z3, z4);
        int i = 0;
        boolean z5 = z3 && this.isPlayingAd;
        long j = this.eventTimeMs;
        long j2 = z3 ? this.durationMs : C.TIME_UNSET;
        long j3 = z3 ? this.bufferedPositionMs : 0;
        if (z3) {
            i = this.bufferedPercentage;
        }
        return new SessionPositionInfo(filterByAvailableCommands, z5, j, j2, j3, i, z3 ? this.totalBufferedDurationMs : 0, z3 ? this.currentLiveOffsetMs : C.TIME_UNSET, z3 ? this.contentDurationMs : C.TIME_UNSET, z3 ? this.contentBufferedPositionMs : 0);
    }

    public Bundle toBundle(int i) {
        Bundle bundle = new Bundle();
        if (i < 3 || !DEFAULT_POSITION_INFO.equalsForBundling(this.positionInfo)) {
            bundle.putBundle(FIELD_POSITION_INFO, this.positionInfo.toBundle(i));
        }
        boolean z = this.isPlayingAd;
        if (z) {
            bundle.putBoolean(FIELD_IS_PLAYING_AD, z);
        }
        long j = this.eventTimeMs;
        if (j != C.TIME_UNSET) {
            bundle.putLong(FIELD_EVENT_TIME_MS, j);
        }
        long j2 = this.durationMs;
        if (j2 != C.TIME_UNSET) {
            bundle.putLong(FIELD_DURATION_MS, j2);
        }
        if (i < 3 || this.bufferedPositionMs != 0) {
            bundle.putLong(FIELD_BUFFERED_POSITION_MS, this.bufferedPositionMs);
        }
        int i2 = this.bufferedPercentage;
        if (i2 != 0) {
            bundle.putInt(FIELD_BUFFERED_PERCENTAGE, i2);
        }
        long j3 = this.totalBufferedDurationMs;
        if (j3 != 0) {
            bundle.putLong(FIELD_TOTAL_BUFFERED_DURATION_MS, j3);
        }
        long j4 = this.currentLiveOffsetMs;
        if (j4 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CURRENT_LIVE_OFFSET_MS, j4);
        }
        long j5 = this.contentDurationMs;
        if (j5 != C.TIME_UNSET) {
            bundle.putLong(FIELD_CONTENT_DURATION_MS, j5);
        }
        if (i < 3 || this.contentBufferedPositionMs != 0) {
            bundle.putLong(FIELD_CONTENT_BUFFERED_POSITION_MS, this.contentBufferedPositionMs);
        }
        return bundle;
    }

    public static SessionPositionInfo fromBundle(Bundle bundle) {
        Player.PositionInfo positionInfo2;
        Bundle bundle2 = bundle;
        Bundle bundle3 = bundle2.getBundle(FIELD_POSITION_INFO);
        if (bundle3 == null) {
            positionInfo2 = DEFAULT_POSITION_INFO;
        } else {
            positionInfo2 = Player.PositionInfo.fromBundle(bundle3);
        }
        Player.PositionInfo positionInfo3 = positionInfo2;
        boolean z = bundle2.getBoolean(FIELD_IS_PLAYING_AD, false);
        return new SessionPositionInfo(positionInfo3, z, bundle2.getLong(FIELD_EVENT_TIME_MS, C.TIME_UNSET), bundle2.getLong(FIELD_DURATION_MS, C.TIME_UNSET), bundle2.getLong(FIELD_BUFFERED_POSITION_MS, 0), bundle2.getInt(FIELD_BUFFERED_PERCENTAGE, 0), bundle2.getLong(FIELD_TOTAL_BUFFERED_DURATION_MS, 0), bundle2.getLong(FIELD_CURRENT_LIVE_OFFSET_MS, C.TIME_UNSET), bundle2.getLong(FIELD_CONTENT_DURATION_MS, C.TIME_UNSET), bundle2.getLong(FIELD_CONTENT_BUFFERED_POSITION_MS, 0));
    }
}
