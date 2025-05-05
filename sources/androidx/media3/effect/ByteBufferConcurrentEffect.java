package androidx.media3.effect;

import android.opengl.GLES20;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlRect;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.effect.ByteBufferGlEffect;
import androidx.media3.effect.QueuingGlShaderProgram;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Future;

class ByteBufferConcurrentEffect<T> implements QueuingGlShaderProgram.ConcurrentEffect<T> {
    private static final int BYTES_PER_PIXEL = 4;
    private GlTextureInfo effectInputTexture;
    private int inputHeight = -1;
    private int inputWidth = -1;
    private final Queue<TexturePixelBuffer> mappedPixelBuffers = new ArrayDeque();
    private final int pendingPixelBufferQueueSize;
    private final PixelBufferObjectProvider pixelBufferObjectProvider = new PixelBufferObjectProvider();
    private final ByteBufferGlEffect.Processor<T> processor;
    private final Queue<TexturePixelBuffer> unmappedPixelBuffers = new ArrayDeque();

    public ByteBufferConcurrentEffect(int i, ByteBufferGlEffect.Processor<T> processor2) {
        this.processor = processor2;
        this.pendingPixelBufferQueueSize = i;
    }

    public Future<T> queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        while (this.unmappedPixelBuffers.size() >= this.pendingPixelBufferQueueSize) {
            try {
                Assertions.checkState(mapOnePixelBuffer());
            } catch (VideoFrameProcessingException | GlUtil.GlException e) {
                return Futures.immediateFailedFuture(e);
            }
        }
        if (!(this.effectInputTexture != null && glTextureInfo.width == this.inputWidth && glTextureInfo.height == this.inputHeight)) {
            while (mapOnePixelBuffer()) {
            }
            this.inputWidth = glTextureInfo.width;
            int i = glTextureInfo.height;
            this.inputHeight = i;
            Size configure = this.processor.configure(this.inputWidth, i);
            GlTextureInfo glTextureInfo2 = this.effectInputTexture;
            if (glTextureInfo2 != null) {
                glTextureInfo2.release();
            }
            this.effectInputTexture = glObjectsProvider.createBuffersForTexture(GlUtil.createTexture(configure.getWidth(), configure.getHeight(), false), configure.getWidth(), configure.getHeight());
        }
        GlUtil.blitFrameBuffer(glTextureInfo.fboId, this.processor.getScaledRegion(j), this.effectInputTexture.fboId, new GlRect(this.effectInputTexture.width, this.effectInputTexture.height));
        TexturePixelBuffer texturePixelBuffer = new TexturePixelBuffer(this.effectInputTexture);
        texturePixelBuffer.schedulePixelBufferRead(this.pixelBufferObjectProvider);
        this.unmappedPixelBuffers.add(texturePixelBuffer);
        return Util.transformFutureAsync(texturePixelBuffer.imageSettableFuture, new ByteBufferConcurrentEffect$$ExternalSyntheticLambda0(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputFrame$0$androidx-media3-effect-ByteBufferConcurrentEffect  reason: not valid java name */
    public /* synthetic */ ListenableFuture m413lambda$queueInputFrame$0$androidxmedia3effectByteBufferConcurrentEffect(long j, ByteBufferGlEffect.Image image) throws Exception {
        return this.processor.processImage(image, j);
    }

    public void finishProcessingAndBlend(GlTextureInfo glTextureInfo, long j, T t) throws VideoFrameProcessingException {
        try {
            ((TexturePixelBuffer) Assertions.checkNotNull(this.mappedPixelBuffers.poll())).unmapAndRecycle(this.pixelBufferObjectProvider);
            this.processor.finishProcessingAndBlend(glTextureInfo, j, t);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void signalEndOfCurrentInputStream() throws VideoFrameProcessingException {
        do {
            try {
            } catch (GlUtil.GlException e) {
                throw new VideoFrameProcessingException((Throwable) e);
            }
        } while (mapOnePixelBuffer());
    }

    public void flush() throws VideoFrameProcessingException {
        try {
            unmapAndRecyclePixelBuffers();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void release() throws VideoFrameProcessingException {
        try {
            unmapAndRecyclePixelBuffers();
            this.pixelBufferObjectProvider.release();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public static int texturePixelBufferSize(GlTextureInfo glTextureInfo) {
        return glTextureInfo.width * glTextureInfo.height * 4;
    }

    private void unmapAndRecyclePixelBuffers() throws GlUtil.GlException {
        while (true) {
            TexturePixelBuffer poll = this.unmappedPixelBuffers.poll();
            if (poll == null) {
                break;
            }
            poll.unmapAndRecycle(this.pixelBufferObjectProvider);
        }
        while (true) {
            TexturePixelBuffer poll2 = this.mappedPixelBuffers.poll();
            if (poll2 != null) {
                poll2.unmapAndRecycle(this.pixelBufferObjectProvider);
            } else {
                return;
            }
        }
    }

    private boolean mapOnePixelBuffer() throws GlUtil.GlException {
        TexturePixelBuffer poll = this.unmappedPixelBuffers.poll();
        if (poll == null) {
            return false;
        }
        poll.map();
        this.mappedPixelBuffers.add(poll);
        return true;
    }

    private static final class TexturePixelBuffer {
        public final SettableFuture<ByteBufferGlEffect.Image> imageSettableFuture = SettableFuture.create();
        private boolean mapped;
        private PixelBufferObjectInfo pixelBufferObjectInfo;
        private final GlTextureInfo textureInfo;

        public TexturePixelBuffer(GlTextureInfo glTextureInfo) {
            this.textureInfo = glTextureInfo;
        }

        public void schedulePixelBufferRead(PixelBufferObjectProvider pixelBufferObjectProvider) throws GlUtil.GlException {
            this.pixelBufferObjectInfo = pixelBufferObjectProvider.getPixelBufferObject(ByteBufferConcurrentEffect.texturePixelBufferSize(this.textureInfo));
            if (Util.SDK_INT >= 24) {
                GlUtil.schedulePixelBufferRead(this.textureInfo.fboId, this.textureInfo.width, this.textureInfo.height, this.pixelBufferObjectInfo.id);
            }
        }

        public void map() throws GlUtil.GlException {
            ByteBuffer byteBuffer;
            Assertions.checkNotNull(this.pixelBufferObjectInfo);
            if (Util.SDK_INT >= 24) {
                byteBuffer = GlUtil.mapPixelBufferObject(this.pixelBufferObjectInfo.id, this.pixelBufferObjectInfo.size);
            } else {
                byteBuffer = ByteBuffer.allocateDirect(ByteBufferConcurrentEffect.texturePixelBufferSize(this.textureInfo));
                GlUtil.focusFramebufferUsingCurrentContext(this.textureInfo.fboId, this.textureInfo.width, this.textureInfo.height);
                GlUtil.checkGlError();
                GLES20.glReadPixels(0, 0, this.textureInfo.width, this.textureInfo.height, 6408, 5121, byteBuffer);
                GlUtil.checkGlError();
            }
            this.imageSettableFuture.set(new ByteBufferGlEffect.Image(this.textureInfo.width, this.textureInfo.height, byteBuffer));
            this.mapped = true;
        }

        public void unmapAndRecycle(PixelBufferObjectProvider pixelBufferObjectProvider) throws GlUtil.GlException {
            Assertions.checkNotNull(this.pixelBufferObjectInfo);
            if (this.mapped && Util.SDK_INT >= 24) {
                GlUtil.unmapPixelBufferObject(this.pixelBufferObjectInfo.id);
            }
            pixelBufferObjectProvider.recycle(this.pixelBufferObjectInfo);
        }
    }

    private static final class PixelBufferObjectInfo {
        public final int id;
        public final int size;

        public PixelBufferObjectInfo(int i) throws GlUtil.GlException {
            this.size = i;
            this.id = GlUtil.createPixelBufferObject(i);
        }

        public void release() throws GlUtil.GlException {
            GlUtil.deleteBuffer(this.id);
        }
    }

    private static final class PixelBufferObjectProvider {
        private final Queue<PixelBufferObjectInfo> availablePixelBufferObjects = new ArrayDeque();

        /* access modifiers changed from: private */
        public PixelBufferObjectInfo getPixelBufferObject(int i) throws GlUtil.GlException {
            while (true) {
                PixelBufferObjectInfo poll = this.availablePixelBufferObjects.poll();
                if (poll == null) {
                    return new PixelBufferObjectInfo(i);
                }
                if (poll.size == i) {
                    return poll;
                }
                GlUtil.deleteBuffer(poll.id);
            }
        }

        /* access modifiers changed from: private */
        public void recycle(PixelBufferObjectInfo pixelBufferObjectInfo) {
            this.availablePixelBufferObjects.add(pixelBufferObjectInfo);
        }

        public void release() throws GlUtil.GlException {
            while (true) {
                PixelBufferObjectInfo poll = this.availablePixelBufferObjects.poll();
                if (poll != null) {
                    poll.release();
                } else {
                    return;
                }
            }
        }
    }
}
