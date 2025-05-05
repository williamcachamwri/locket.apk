package androidx.camera.video.internal.workaround;

import android.os.Build;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Timebase;
import androidx.camera.video.internal.compat.quirk.CameraUseInconsistentTimebaseQuirk;
import androidx.camera.video.internal.encoder.TimeProvider;

public class VideoTimebaseConverter {
    private static final String TAG = "VideoTimebaseConverter";
    private static final long UPTIME_REALTIME_DIFF_THRESHOLD_US = 3000000;
    private final CameraUseInconsistentTimebaseQuirk mCameraUseInconsistentTimebaseQuirk;
    private final Timebase mInputTimebase;
    private Timebase mResolvedInputTimebase;
    private final TimeProvider mTimeProvider;
    private long mUptimeToRealtimeOffsetUs = -1;

    public VideoTimebaseConverter(TimeProvider timeProvider, Timebase timebase, CameraUseInconsistentTimebaseQuirk cameraUseInconsistentTimebaseQuirk) {
        this.mTimeProvider = timeProvider;
        this.mInputTimebase = timebase;
        this.mCameraUseInconsistentTimebaseQuirk = cameraUseInconsistentTimebaseQuirk;
    }

    public long convertToUptimeUs(long j) {
        if (this.mResolvedInputTimebase == null) {
            this.mResolvedInputTimebase = resolveInputTimebase(j);
        }
        int i = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$Timebase[this.mResolvedInputTimebase.ordinal()];
        if (i == 1) {
            if (this.mUptimeToRealtimeOffsetUs == -1) {
                this.mUptimeToRealtimeOffsetUs = calculateUptimeToRealtimeOffsetUs();
                Logger.d(TAG, "mUptimeToRealtimeOffsetUs = " + this.mUptimeToRealtimeOffsetUs);
            }
            return j - this.mUptimeToRealtimeOffsetUs;
        } else if (i == 2) {
            return j;
        } else {
            throw new AssertionError("Unknown timebase: " + this.mResolvedInputTimebase);
        }
    }

    /* renamed from: androidx.camera.video.internal.workaround.VideoTimebaseConverter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$Timebase;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                androidx.camera.core.impl.Timebase[] r0 = androidx.camera.core.impl.Timebase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$core$impl$Timebase = r0
                androidx.camera.core.impl.Timebase r1 = androidx.camera.core.impl.Timebase.REALTIME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$core$impl$Timebase     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.core.impl.Timebase r1 = androidx.camera.core.impl.Timebase.UPTIME     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.workaround.VideoTimebaseConverter.AnonymousClass1.<clinit>():void");
        }
    }

    private Timebase resolveInputTimebase(long j) {
        boolean z;
        if (this.mCameraUseInconsistentTimebaseQuirk != null) {
            Logger.w(TAG, "CameraUseInconsistentTimebaseQuirk is enabled");
            z = false;
        } else if (!exceedUptimeRealtimeDiffThreshold()) {
            return this.mInputTimebase;
        } else {
            z = true;
        }
        Timebase timebase = isCloseToRealtime(j) ? Timebase.REALTIME : Timebase.UPTIME;
        if (!z || timebase == this.mInputTimebase) {
            Logger.d(TAG, "Detect input timebase = " + timebase);
        } else {
            Logger.e(TAG, String.format("Detected camera timebase inconsistent. Please file an issue at https://issuetracker.google.com/issues/new?component=618491&template=1257717 with this error message [Manufacturer: %s, Model: %s, Hardware: %s, API Level: %d%s].\nCamera timebase is inconsistent. The timebase reported by the camera is %s, but the actual timebase contained in the frame is detected as %s.", new Object[]{Build.MANUFACTURER, Build.MODEL, Build.HARDWARE, Integer.valueOf(Build.VERSION.SDK_INT), Build.VERSION.SDK_INT >= 31 ? ", SOC: " + Build.SOC_MODEL : "", this.mInputTimebase, timebase}));
        }
        return timebase;
    }

    private boolean exceedUptimeRealtimeDiffThreshold() {
        return this.mTimeProvider.realtimeUs() - this.mTimeProvider.uptimeUs() > UPTIME_REALTIME_DIFF_THRESHOLD_US;
    }

    private boolean isCloseToRealtime(long j) {
        return Math.abs(j - this.mTimeProvider.realtimeUs()) < Math.abs(j - this.mTimeProvider.uptimeUs());
    }

    private long calculateUptimeToRealtimeOffsetUs() {
        long j = Long.MAX_VALUE;
        long j2 = 0;
        for (int i = 0; i < 3; i++) {
            long uptimeUs = this.mTimeProvider.uptimeUs();
            long realtimeUs = this.mTimeProvider.realtimeUs();
            long uptimeUs2 = this.mTimeProvider.uptimeUs();
            long j3 = uptimeUs2 - uptimeUs;
            if (i == 0 || j3 < j) {
                j2 = realtimeUs - ((uptimeUs + uptimeUs2) >> 1);
                j = j3;
            }
        }
        return Math.max(0, j2);
    }
}
