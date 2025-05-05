package androidx.media3.effect;

import android.util.Pair;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;
import androidx.media3.effect.GlShaderProgram;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

final class FrameConsumptionManager implements GlShaderProgram.InputListener {
    private final Queue<Pair<GlTextureInfo, Long>> availableFrames = new ArrayDeque();
    private final GlShaderProgram consumingGlShaderProgram;
    private int consumingGlShaderProgramInputCapacity;
    private final GlObjectsProvider glObjectsProvider;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public FrameConsumptionManager(GlObjectsProvider glObjectsProvider2, GlShaderProgram glShaderProgram, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        this.glObjectsProvider = glObjectsProvider2;
        this.consumingGlShaderProgram = glShaderProgram;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadyToAcceptInputFrame() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Queue<android.util.Pair<androidx.media3.common.GlTextureInfo, java.lang.Long>> r0 = r4.availableFrames     // Catch:{ all -> 0x004b }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x004b }
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x004b }
            if (r0 != 0) goto L_0x0013
            int r0 = r4.consumingGlShaderProgramInputCapacity     // Catch:{ all -> 0x004b }
            int r0 = r0 + 1
            r4.consumingGlShaderProgramInputCapacity = r0     // Catch:{ all -> 0x004b }
            monitor-exit(r4)
            return
        L_0x0013:
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r1 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x004b }
            androidx.media3.effect.FrameConsumptionManager$$ExternalSyntheticLambda2 r2 = new androidx.media3.effect.FrameConsumptionManager$$ExternalSyntheticLambda2     // Catch:{ all -> 0x004b }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x004b }
            r1.submit(r2)     // Catch:{ all -> 0x004b }
            java.util.Queue<android.util.Pair<androidx.media3.common.GlTextureInfo, java.lang.Long>> r0 = r4.availableFrames     // Catch:{ all -> 0x004b }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x004b }
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x0049
            java.lang.Object r0 = r0.second     // Catch:{ all -> 0x004b }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x004b }
            long r0 = r0.longValue()     // Catch:{ all -> 0x004b }
            r2 = -9223372036854775808
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0049
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r0 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x004b }
            androidx.media3.effect.GlShaderProgram r1 = r4.consumingGlShaderProgram     // Catch:{ all -> 0x004b }
            java.util.Objects.requireNonNull(r1)     // Catch:{ all -> 0x004b }
            androidx.media3.effect.FrameConsumptionManager$$ExternalSyntheticLambda0 r2 = new androidx.media3.effect.FrameConsumptionManager$$ExternalSyntheticLambda0     // Catch:{ all -> 0x004b }
            r2.<init>(r1)     // Catch:{ all -> 0x004b }
            r0.submit(r2)     // Catch:{ all -> 0x004b }
            java.util.Queue<android.util.Pair<androidx.media3.common.GlTextureInfo, java.lang.Long>> r0 = r4.availableFrames     // Catch:{ all -> 0x004b }
            r0.remove()     // Catch:{ all -> 0x004b }
        L_0x0049:
            monitor-exit(r4)
            return
        L_0x004b:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.FrameConsumptionManager.onReadyToAcceptInputFrame():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onReadyToAcceptInputFrame$0$androidx-media3-effect-FrameConsumptionManager  reason: not valid java name */
    public /* synthetic */ void m442lambda$onReadyToAcceptInputFrame$0$androidxmedia3effectFrameConsumptionManager(Pair pair) throws VideoFrameProcessingException, GlUtil.GlException {
        this.consumingGlShaderProgram.queueInputFrame(this.glObjectsProvider, (GlTextureInfo) pair.first, ((Long) pair.second).longValue());
    }

    public synchronized void onFlush() {
        this.consumingGlShaderProgramInputCapacity = 0;
        this.availableFrames.clear();
    }

    public synchronized void queueInputFrame(GlTextureInfo glTextureInfo, long j) {
        if (this.consumingGlShaderProgramInputCapacity > 0) {
            this.videoFrameProcessingTaskExecutor.submit(new FrameConsumptionManager$$ExternalSyntheticLambda1(this, glTextureInfo, j));
            this.consumingGlShaderProgramInputCapacity--;
        } else {
            this.availableFrames.add(Pair.create(glTextureInfo, Long.valueOf(j)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputFrame$1$androidx-media3-effect-FrameConsumptionManager  reason: not valid java name */
    public /* synthetic */ void m443lambda$queueInputFrame$1$androidxmedia3effectFrameConsumptionManager(GlTextureInfo glTextureInfo, long j) throws VideoFrameProcessingException, GlUtil.GlException {
        this.consumingGlShaderProgram.queueInputFrame(this.glObjectsProvider, glTextureInfo, j);
    }

    public synchronized void signalEndOfCurrentStream() {
        if (!this.availableFrames.isEmpty()) {
            this.availableFrames.add(Pair.create(GlTextureInfo.UNSET, Long.MIN_VALUE));
        } else {
            VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = this.videoFrameProcessingTaskExecutor;
            GlShaderProgram glShaderProgram = this.consumingGlShaderProgram;
            Objects.requireNonNull(glShaderProgram);
            videoFrameProcessingTaskExecutor2.submit(new FrameConsumptionManager$$ExternalSyntheticLambda0(glShaderProgram));
        }
    }

    public synchronized int getPendingFrameCount() {
        return this.availableFrames.size();
    }
}
