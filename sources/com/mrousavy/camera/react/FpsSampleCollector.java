package com.mrousavy.camera.react;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/mrousavy/camera/react/FpsSampleCollector;", "", "callback", "Lcom/mrousavy/camera/react/FpsSampleCollector$Callback;", "(Lcom/mrousavy/camera/react/FpsSampleCollector$Callback;)V", "averageFps", "", "getAverageFps", "()D", "getCallback", "()Lcom/mrousavy/camera/react/FpsSampleCollector$Callback;", "timer", "Ljava/util/Timer;", "timestamps", "", "", "onTick", "", "start", "stop", "Callback", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FpsSampleCollector.kt */
public final class FpsSampleCollector {
    private final Callback callback;
    private Timer timer;
    private List<Long> timestamps = new ArrayList();

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/mrousavy/camera/react/FpsSampleCollector$Callback;", "", "onAverageFpsChanged", "", "averageFps", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FpsSampleCollector.kt */
    public interface Callback {
        void onAverageFpsChanged(double d);
    }

    public FpsSampleCollector(Callback callback2) {
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.callback = callback2;
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final void start() {
        Timer timer2 = new Timer("VisionCamera FPS Sample Collector");
        this.timer = timer2;
        timer2.schedule(new FpsSampleCollector$start$$inlined$schedule$1(this), 1000, 1000);
    }

    public final void stop() {
        Timer timer2 = this.timer;
        if (timer2 != null) {
            timer2.cancel();
        }
        this.timer = null;
    }

    public final void onTick() {
        long currentTimeMillis = System.currentTimeMillis();
        this.timestamps.add(Long.valueOf(currentTimeMillis));
        Collection arrayList = new ArrayList();
        for (Object next : this.timestamps) {
            if (currentTimeMillis - ((Number) next).longValue() < 1000) {
                arrayList.add(next);
            }
        }
        this.timestamps = CollectionsKt.toMutableList((List) arrayList);
    }

    /* access modifiers changed from: private */
    public final double getAverageFps() {
        Long l = (Long) CollectionsKt.firstOrNull(this.timestamps);
        Long l2 = (Long) CollectionsKt.lastOrNull(this.timestamps);
        if (l == null || l2 == null) {
            return 0.0d;
        }
        return 1000.0d / (((double) (l2.longValue() - l.longValue())) / ((double) (this.timestamps.size() - 1)));
    }
}
