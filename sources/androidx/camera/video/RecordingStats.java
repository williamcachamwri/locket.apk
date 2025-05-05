package androidx.camera.video;

import androidx.core.util.Preconditions;

public abstract class RecordingStats {
    public abstract AudioStats getAudioStats();

    public abstract long getNumBytesRecorded();

    public abstract long getRecordedDurationNanos();

    RecordingStats() {
    }

    static RecordingStats of(long j, long j2, AudioStats audioStats) {
        boolean z = true;
        Preconditions.checkArgument(j >= 0, "duration must be positive value.");
        if (j2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "bytes must be positive value.");
        return new AutoValue_RecordingStats(j, j2, audioStats);
    }
}
