package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

class AsynchronousMediaCodecBufferEnqueuer implements MediaCodecBufferEnqueuer {
    private static final ArrayDeque<MessageParams> MESSAGE_PARAMS_INSTANCE_POOL = new ArrayDeque<>();
    private static final int MSG_OPEN_CV = 3;
    private static final int MSG_QUEUE_INPUT_BUFFER = 1;
    private static final int MSG_QUEUE_SECURE_INPUT_BUFFER = 2;
    private static final int MSG_SET_PARAMETERS = 4;
    private static final Object QUEUE_SECURE_LOCK = new Object();
    private final MediaCodec codec;
    private final ConditionVariable conditionVariable;
    private Handler handler;
    private final HandlerThread handlerThread;
    private final AtomicReference<RuntimeException> pendingRuntimeException;
    private boolean started;

    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread2) {
        this(mediaCodec, handlerThread2, new ConditionVariable());
    }

    AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread2, ConditionVariable conditionVariable2) {
        this.codec = mediaCodec;
        this.handlerThread = handlerThread2;
        this.conditionVariable = conditionVariable2;
        this.pendingRuntimeException = new AtomicReference<>();
    }

    public void start() {
        if (!this.started) {
            this.handlerThread.start();
            this.handler = new Handler(this.handlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    AsynchronousMediaCodecBufferEnqueuer.this.doHandleMessage(message);
                }
            };
            this.started = true;
        }
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i, i2, i3, j, i4);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(1, messageParams).sendToTarget();
    }

    public void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i, i2, 0, j, i3);
        copy(cryptoInfo, messageParams.cryptoInfo);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(2, messageParams).sendToTarget();
    }

    public void setParameters(Bundle bundle) {
        maybeThrowException();
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(4, bundle).sendToTarget();
    }

    public void flush() {
        if (this.started) {
            try {
                flushHandlerThread();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
    }

    public void shutdown() {
        if (this.started) {
            flush();
            this.handlerThread.quit();
        }
        this.started = false;
    }

    public void waitUntilQueueingComplete() throws InterruptedException {
        blockUntilHandlerThreadIsIdle();
    }

    public void maybeThrowException() {
        RuntimeException andSet = this.pendingRuntimeException.getAndSet((Object) null);
        if (andSet != null) {
            throw andSet;
        }
    }

    private void flushHandlerThread() throws InterruptedException {
        ((Handler) Assertions.checkNotNull(this.handler)).removeCallbacksAndMessages((Object) null);
        blockUntilHandlerThreadIsIdle();
    }

    private void blockUntilHandlerThreadIsIdle() throws InterruptedException {
        this.conditionVariable.close();
        ((Handler) Assertions.checkNotNull(this.handler)).obtainMessage(3).sendToTarget();
        this.conditionVariable.block();
    }

    /* access modifiers changed from: package-private */
    public void setPendingRuntimeException(RuntimeException runtimeException) {
        this.pendingRuntimeException.set(runtimeException);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doHandleMessage(android.os.Message r11) {
        /*
            r10 = this;
            int r0 = r11.what
            r1 = 1
            if (r0 == r1) goto L_0x0042
            r1 = 2
            if (r0 == r1) goto L_0x002e
            r1 = 3
            r2 = 0
            if (r0 == r1) goto L_0x0028
            r1 = 4
            if (r0 == r1) goto L_0x0020
            java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> r0 = r10.pendingRuntimeException
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            int r11 = r11.what
            java.lang.String r11 = java.lang.String.valueOf(r11)
            r1.<init>(r11)
            androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(r0, r2, r1)
            goto L_0x0055
        L_0x0020:
            java.lang.Object r11 = r11.obj
            android.os.Bundle r11 = (android.os.Bundle) r11
            r10.doSetParameters(r11)
            goto L_0x0055
        L_0x0028:
            androidx.media3.common.util.ConditionVariable r11 = r10.conditionVariable
            r11.open()
            goto L_0x0055
        L_0x002e:
            java.lang.Object r11 = r11.obj
            r2 = r11
            androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r2 = (androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r2
            int r4 = r2.index
            int r5 = r2.offset
            android.media.MediaCodec$CryptoInfo r6 = r2.cryptoInfo
            long r7 = r2.presentationTimeUs
            int r9 = r2.flags
            r3 = r10
            r3.doQueueSecureInputBuffer(r4, r5, r6, r7, r9)
            goto L_0x0055
        L_0x0042:
            java.lang.Object r11 = r11.obj
            r2 = r11
            androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r2 = (androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r2
            int r4 = r2.index
            int r5 = r2.offset
            int r6 = r2.size
            long r7 = r2.presentationTimeUs
            int r9 = r2.flags
            r3 = r10
            r3.doQueueInputBuffer(r4, r5, r6, r7, r9)
        L_0x0055:
            if (r2 == 0) goto L_0x005a
            recycleMessageParams(r2)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer.doHandleMessage(android.os.Message):void");
    }

    private void doQueueInputBuffer(int i, int i2, int i3, long j, int i4) {
        try {
            this.codec.queueInputBuffer(i, i2, i3, j, i4);
        } catch (RuntimeException e) {
            PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, (Object) null, e);
        }
    }

    private void doQueueSecureInputBuffer(int i, int i2, MediaCodec.CryptoInfo cryptoInfo, long j, int i3) {
        try {
            synchronized (QUEUE_SECURE_LOCK) {
                this.codec.queueSecureInputBuffer(i, i2, cryptoInfo, j, i3);
            }
        } catch (RuntimeException e) {
            PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, (Object) null, e);
        }
    }

    private void doSetParameters(Bundle bundle) {
        try {
            this.codec.setParameters(bundle);
        } catch (RuntimeException e) {
            PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, (Object) null, e);
        }
    }

    private static MessageParams getMessageParams() {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            if (arrayDeque.isEmpty()) {
                MessageParams messageParams = new MessageParams();
                return messageParams;
            }
            MessageParams removeFirst = arrayDeque.removeFirst();
            return removeFirst;
        }
    }

    private static void recycleMessageParams(MessageParams messageParams) {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            arrayDeque.add(messageParams);
        }
    }

    private static class MessageParams {
        public final MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        public int flags;
        public int index;
        public int offset;
        public long presentationTimeUs;
        public int size;

        MessageParams() {
        }

        public void setQueueParams(int i, int i2, int i3, long j, int i4) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeUs = j;
            this.flags = i4;
        }
    }

    private static void copy(CryptoInfo cryptoInfo, MediaCodec.CryptoInfo cryptoInfo2) {
        cryptoInfo2.numSubSamples = cryptoInfo.numSubSamples;
        cryptoInfo2.numBytesOfClearData = copy(cryptoInfo.numBytesOfClearData, cryptoInfo2.numBytesOfClearData);
        cryptoInfo2.numBytesOfEncryptedData = copy(cryptoInfo.numBytesOfEncryptedData, cryptoInfo2.numBytesOfEncryptedData);
        cryptoInfo2.key = (byte[]) Assertions.checkNotNull(copy(cryptoInfo.key, cryptoInfo2.key));
        cryptoInfo2.iv = (byte[]) Assertions.checkNotNull(copy(cryptoInfo.iv, cryptoInfo2.iv));
        cryptoInfo2.mode = cryptoInfo.mode;
        if (Util.SDK_INT >= 24) {
            cryptoInfo2.setPattern(new MediaCodec.CryptoInfo.Pattern(cryptoInfo.encryptedBlocks, cryptoInfo.clearBlocks));
        }
    }

    private static int[] copy(int[] iArr, int[] iArr2) {
        if (iArr == null) {
            return iArr2;
        }
        if (iArr2 == null || iArr2.length < iArr.length) {
            return Arrays.copyOf(iArr, iArr.length);
        }
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private static byte[] copy(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null || bArr2.length < bArr.length) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
