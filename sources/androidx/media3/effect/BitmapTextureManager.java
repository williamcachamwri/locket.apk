package androidx.media3.effect;

import android.graphics.Bitmap;
import android.graphics.Gainmap;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

final class BitmapTextureManager extends TextureManager {
    private boolean currentInputStreamEnded;
    private GlTextureInfo currentSdrGlTextureInfo;
    private int downstreamShaderProgramCapacity;
    private final GlObjectsProvider glObjectsProvider;
    private boolean isNextFrameInTexture;
    private final Queue<BitmapFrameSequenceInfo> pendingBitmaps = new LinkedBlockingQueue();
    private RepeatingGainmapShaderProgram repeatingGainmapShaderProgram;
    private final boolean signalRepeatingSequence;

    public int getPendingFrameCount() {
        return 0;
    }

    public BitmapTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z) {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
        this.signalRepeatingSequence = z;
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        Assertions.checkState(glShaderProgram instanceof RepeatingGainmapShaderProgram);
        this.downstreamShaderProgramCapacity = 0;
        this.repeatingGainmapShaderProgram = (RepeatingGainmapShaderProgram) glShaderProgram;
    }

    public void onReadyToAcceptInputFrame() {
        this.videoFrameProcessingTaskExecutor.submit(new BitmapTextureManager$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onReadyToAcceptInputFrame$0$androidx-media3-effect-BitmapTextureManager  reason: not valid java name */
    public /* synthetic */ void m409lambda$onReadyToAcceptInputFrame$0$androidxmedia3effectBitmapTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        this.downstreamShaderProgramCapacity++;
        maybeQueueToShaderProgram();
    }

    public void queueInputBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        this.videoFrameProcessingTaskExecutor.submit(new BitmapTextureManager$$ExternalSyntheticLambda3(this, bitmap, frameInfo, timestampIterator));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputBitmap$1$androidx-media3-effect-BitmapTextureManager  reason: not valid java name */
    public /* synthetic */ void m410lambda$queueInputBitmap$1$androidxmedia3effectBitmapTextureManager(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) throws VideoFrameProcessingException, GlUtil.GlException {
        setupBitmap(bitmap, frameInfo, timestampIterator);
        this.currentInputStreamEnded = false;
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new BitmapTextureManager$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$signalEndOfCurrentInputStream$2$androidx-media3-effect-BitmapTextureManager  reason: not valid java name */
    public /* synthetic */ void m412lambda$signalEndOfCurrentInputStream$2$androidxmedia3effectBitmapTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        if (this.pendingBitmaps.isEmpty()) {
            ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalEndOfCurrentInputStream();
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_BITMAP_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SIGNAL_EOS, Long.MIN_VALUE);
            return;
        }
        this.currentInputStreamEnded = true;
    }

    public void release() {
        this.videoFrameProcessingTaskExecutor.submit(new BitmapTextureManager$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$3$androidx-media3-effect-BitmapTextureManager  reason: not valid java name */
    public /* synthetic */ void m411lambda$release$3$androidxmedia3effectBitmapTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        GlTextureInfo glTextureInfo = this.currentSdrGlTextureInfo;
        if (glTextureInfo != null) {
            glTextureInfo.release();
        }
        this.pendingBitmaps.clear();
    }

    private void setupBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) throws VideoFrameProcessingException {
        Assertions.checkArgument(timestampIterator.hasNext(), "Bitmap queued but no timestamps provided.");
        this.pendingBitmaps.add(new BitmapFrameSequenceInfo(bitmap, frameInfo, timestampIterator));
        maybeQueueToShaderProgram();
    }

    private void maybeQueueToShaderProgram() throws VideoFrameProcessingException {
        if (!this.pendingBitmaps.isEmpty() && this.downstreamShaderProgramCapacity != 0) {
            BitmapFrameSequenceInfo element = this.pendingBitmaps.element();
            FrameInfo access$000 = element.frameInfo;
            TimestampIterator access$100 = element.inStreamOffsetsUs;
            Assertions.checkState(element.inStreamOffsetsUs.hasNext());
            long next = element.frameInfo.offsetToAddUs + access$100.next();
            if (!this.isNextFrameInTexture) {
                this.isNextFrameInTexture = true;
                updateCurrentGlTextureInfo(access$000, element.bitmap);
            }
            this.downstreamShaderProgramCapacity--;
            ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).queueInputFrame(this.glObjectsProvider, (GlTextureInfo) Assertions.checkNotNull(this.currentSdrGlTextureInfo), next);
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_QUEUE_BITMAP, next, "%dx%d", Integer.valueOf(access$000.width), Integer.valueOf(access$000.height));
            if (!element.inStreamOffsetsUs.hasNext()) {
                this.isNextFrameInTexture = false;
                this.pendingBitmaps.remove().bitmap.recycle();
                if (this.pendingBitmaps.isEmpty() && this.currentInputStreamEnded) {
                    ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalEndOfCurrentInputStream();
                    DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_BITMAP_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SIGNAL_EOS, Long.MIN_VALUE);
                    this.currentInputStreamEnded = false;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void flush() throws VideoFrameProcessingException {
        this.pendingBitmaps.clear();
        this.isNextFrameInTexture = false;
        this.currentInputStreamEnded = false;
        this.downstreamShaderProgramCapacity = 0;
        GlTextureInfo glTextureInfo = this.currentSdrGlTextureInfo;
        if (glTextureInfo != null) {
            try {
                glTextureInfo.release();
                this.currentSdrGlTextureInfo = null;
            } catch (GlUtil.GlException e) {
                throw VideoFrameProcessingException.from(e);
            }
        }
        super.flush();
    }

    private void updateCurrentGlTextureInfo(FrameInfo frameInfo, Bitmap bitmap) throws VideoFrameProcessingException {
        try {
            GlTextureInfo glTextureInfo = this.currentSdrGlTextureInfo;
            if (glTextureInfo != null) {
                glTextureInfo.release();
            }
            this.currentSdrGlTextureInfo = new GlTextureInfo(GlUtil.createTexture(bitmap), -1, -1, frameInfo.width, frameInfo.height);
            if (Util.SDK_INT >= 34 && bitmap.hasGainmap()) {
                ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).setGainmap((Gainmap) Assertions.checkNotNull(bitmap.getGainmap()));
            }
            if (this.signalRepeatingSequence) {
                ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalNewRepeatingFrameSequence();
            }
        } catch (GlUtil.GlException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    private static final class BitmapFrameSequenceInfo {
        public final Bitmap bitmap;
        /* access modifiers changed from: private */
        public final FrameInfo frameInfo;
        /* access modifiers changed from: private */
        public final TimestampIterator inStreamOffsetsUs;

        public BitmapFrameSequenceInfo(Bitmap bitmap2, FrameInfo frameInfo2, TimestampIterator timestampIterator) {
            this.bitmap = bitmap2;
            this.frameInfo = frameInfo2;
            this.inStreamOffsetsUs = timestampIterator;
        }
    }
}
