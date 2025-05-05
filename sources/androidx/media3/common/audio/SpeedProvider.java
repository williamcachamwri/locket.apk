package androidx.media3.common.audio;

public interface SpeedProvider {
    long getNextSpeedChangeTimeUs(long j);

    float getSpeed(long j);
}
