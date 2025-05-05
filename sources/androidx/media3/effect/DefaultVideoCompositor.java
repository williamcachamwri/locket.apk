package androidx.media3.effect;

import android.content.Context;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.VideoCompositor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public final class DefaultVideoCompositor implements VideoCompositor {
    private static final String TAG = "DefaultVideoCompositor";
    private static final String THREAD_NAME = "Effect:DefaultVideoCompositor:GlThread";
    private boolean allInputsEnded;
    private final CompositorGlProgram compositorGlProgram;
    private ColorInfo configuredColorInfo;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private final GlObjectsProvider glObjectsProvider;
    private final SparseArray<InputSource> inputSources = new SparseArray<>();
    private final VideoCompositor.Listener listener;
    private final TexturePool outputTexturePool;
    private final LongArrayQueue outputTextureTimestamps;
    private EGLSurface placeholderEglSurface;
    private int primaryInputIndex = -1;
    private final VideoCompositorSettings settings;
    private final LongArrayQueue syncObjects;
    private final GlTextureProducer.Listener textureOutputListener;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    private static final class InputSource {
        public final Queue<InputFrameInfo> frameInfos = new ArrayDeque();
        public boolean isInputEnded;
    }

    public DefaultVideoCompositor(Context context, GlObjectsProvider glObjectsProvider2, VideoCompositorSettings videoCompositorSettings, ExecutorService executorService, VideoCompositor.Listener listener2, GlTextureProducer.Listener listener3, int i) {
        this.listener = listener2;
        this.textureOutputListener = listener3;
        this.glObjectsProvider = glObjectsProvider2;
        this.settings = videoCompositorSettings;
        this.compositorGlProgram = new CompositorGlProgram(context);
        boolean z = false;
        this.outputTexturePool = new TexturePool(false, i);
        this.outputTextureTimestamps = new LongArrayQueue(i);
        this.syncObjects = new LongArrayQueue(i);
        z = executorService == null ? true : z;
        ExecutorService newSingleThreadExecutor = z ? Util.newSingleThreadExecutor(THREAD_NAME) : (ExecutorService) Assertions.checkNotNull(executorService);
        Objects.requireNonNull(listener2);
        VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = new VideoFrameProcessingTaskExecutor(newSingleThreadExecutor, z, new DefaultVideoCompositor$$ExternalSyntheticLambda4(listener2));
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        videoFrameProcessingTaskExecutor2.submit(new DefaultVideoCompositor$$ExternalSyntheticLambda5(this));
    }

    public synchronized void registerInputSource(int i) {
        Assertions.checkState(!Util.contains(this.inputSources, i));
        this.inputSources.put(i, new InputSource());
        if (this.primaryInputIndex == -1) {
            this.primaryInputIndex = i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void signalEndOfInputSource(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x007f }
            boolean r0 = androidx.media3.common.util.Util.contains(r0, (int) r5)     // Catch:{ all -> 0x007f }
            androidx.media3.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x007f }
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x007f }
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x0013
            r0 = r3
            goto L_0x0014
        L_0x0013:
            r0 = r2
        L_0x0014:
            androidx.media3.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x007f }
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x007f }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x007f }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r0 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r0     // Catch:{ all -> 0x007f }
            r0.isInputEnded = r3     // Catch:{ all -> 0x007f }
            r0 = r2
        L_0x0022:
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r1 = r4.inputSources     // Catch:{ all -> 0x007f }
            int r1 = r1.size()     // Catch:{ all -> 0x007f }
            if (r0 >= r1) goto L_0x003a
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r1 = r4.inputSources     // Catch:{ all -> 0x007f }
            java.lang.Object r1 = r1.valueAt(r0)     // Catch:{ all -> 0x007f }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r1 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r1     // Catch:{ all -> 0x007f }
            boolean r1 = r1.isInputEnded     // Catch:{ all -> 0x007f }
            if (r1 != 0) goto L_0x0037
            goto L_0x003b
        L_0x0037:
            int r0 = r0 + 1
            goto L_0x0022
        L_0x003a:
            r2 = r3
        L_0x003b:
            r4.allInputsEnded = r2     // Catch:{ all -> 0x007f }
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x007f }
            int r1 = r4.primaryInputIndex     // Catch:{ all -> 0x007f }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x007f }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r0 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r0     // Catch:{ all -> 0x007f }
            java.util.Queue<androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo> r0 = r0.frameInfos     // Catch:{ all -> 0x007f }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x005f
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x007f }
            if (r5 != r0) goto L_0x0056
            r4.releaseExcessFramesInAllSecondaryStreams()     // Catch:{ all -> 0x007f }
        L_0x0056:
            if (r2 == 0) goto L_0x005f
            androidx.media3.effect.VideoCompositor$Listener r5 = r4.listener     // Catch:{ all -> 0x007f }
            r5.onEnded()     // Catch:{ all -> 0x007f }
            monitor-exit(r4)
            return
        L_0x005f:
            int r0 = r4.primaryInputIndex     // Catch:{ all -> 0x007f }
            if (r5 == r0) goto L_0x007d
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r4.inputSources     // Catch:{ all -> 0x007f }
            java.lang.Object r5 = r0.get(r5)     // Catch:{ all -> 0x007f }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r5 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r5     // Catch:{ all -> 0x007f }
            java.util.Queue<androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo> r5 = r5.frameInfos     // Catch:{ all -> 0x007f }
            int r5 = r5.size()     // Catch:{ all -> 0x007f }
            if (r5 != r3) goto L_0x007d
            androidx.media3.effect.VideoFrameProcessingTaskExecutor r5 = r4.videoFrameProcessingTaskExecutor     // Catch:{ all -> 0x007f }
            androidx.media3.effect.DefaultVideoCompositor$$ExternalSyntheticLambda1 r0 = new androidx.media3.effect.DefaultVideoCompositor$$ExternalSyntheticLambda1     // Catch:{ all -> 0x007f }
            r0.<init>(r4)     // Catch:{ all -> 0x007f }
            r5.submit(r0)     // Catch:{ all -> 0x007f }
        L_0x007d:
            monitor-exit(r4)
            return
        L_0x007f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.DefaultVideoCompositor.signalEndOfInputSource(int):void");
    }

    public synchronized void queueInputTexture(int i, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, ColorInfo colorInfo, long j) {
        Assertions.checkState(Util.contains(this.inputSources, i));
        InputSource inputSource = this.inputSources.get(i);
        boolean z = true;
        Assertions.checkState(!inputSource.isInputEnded);
        if (ColorInfo.isTransferHdr(colorInfo)) {
            z = false;
        }
        Assertions.checkStateNotNull(Boolean.valueOf(z), "HDR input is not supported.");
        if (this.configuredColorInfo == null) {
            this.configuredColorInfo = colorInfo;
        }
        Assertions.checkState(this.configuredColorInfo.equals(colorInfo), "Mixing different ColorInfos is not supported.");
        inputSource.frameInfos.add(new InputFrameInfo(glTextureProducer, glTextureInfo, j, this.settings.getOverlaySettings(i, j)));
        if (i == this.primaryInputIndex) {
            releaseExcessFramesInAllSecondaryStreams();
        } else {
            releaseExcessFramesInSecondaryStream(inputSource);
        }
        this.videoFrameProcessingTaskExecutor.submit(new DefaultVideoCompositor$$ExternalSyntheticLambda1(this));
    }

    public synchronized void release() {
        Assertions.checkState(this.allInputsEnded);
        try {
            this.videoFrameProcessingTaskExecutor.release(new DefaultVideoCompositor$$ExternalSyntheticLambda0(this));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    public void releaseOutputTexture(long j) {
        this.videoFrameProcessingTaskExecutor.submit(new DefaultVideoCompositor$$ExternalSyntheticLambda3(this, j));
    }

    private synchronized void releaseExcessFramesInAllSecondaryStreams() {
        for (int i = 0; i < this.inputSources.size(); i++) {
            if (this.inputSources.keyAt(i) != this.primaryInputIndex) {
                releaseExcessFramesInSecondaryStream(this.inputSources.valueAt(i));
            }
        }
    }

    private synchronized void releaseExcessFramesInSecondaryStream(InputSource inputSource) {
        InputSource inputSource2 = this.inputSources.get(this.primaryInputIndex);
        if (!inputSource2.frameInfos.isEmpty() || !inputSource2.isInputEnded) {
            InputFrameInfo peek = inputSource2.frameInfos.peek();
            releaseFrames(inputSource, Math.max(Iterables.size(Iterables.filter(inputSource.frameInfos, new DefaultVideoCompositor$$ExternalSyntheticLambda2(peek != null ? peek.presentationTimeUs : C.TIME_UNSET))) - 1, 0));
            return;
        }
        releaseFrames(inputSource, inputSource.frameInfos.size());
    }

    static /* synthetic */ boolean lambda$releaseExcessFramesInSecondaryStream$1(long j, InputFrameInfo inputFrameInfo) {
        return inputFrameInfo.presentationTimeUs <= j;
    }

    private synchronized void releaseFrames(InputSource inputSource, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            InputFrameInfo remove = inputSource.frameInfos.remove();
            remove.textureProducer.releaseOutputTexture(remove.presentationTimeUs);
        }
    }

    /* access modifiers changed from: private */
    public void setupGlObjects() throws GlUtil.GlException {
        EGLDisplay defaultEglDisplay = GlUtil.getDefaultEglDisplay();
        this.eglDisplay = defaultEglDisplay;
        EGLContext createEglContext = this.glObjectsProvider.createEglContext(defaultEglDisplay, 2, GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_8888);
        this.eglContext = createEglContext;
        this.placeholderEglSurface = this.glObjectsProvider.createFocusedPlaceholderEglSurface(createEglContext, this.eglDisplay);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0095, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void maybeComposite() throws androidx.media3.common.VideoFrameProcessingException, androidx.media3.common.util.GlUtil.GlException {
        /*
            r10 = this;
            monitor-enter(r10)
            com.google.common.collect.ImmutableList r0 = r10.getFramesToComposite()     // Catch:{ all -> 0x0096 }
            boolean r1 = r0.isEmpty()     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x000d
            monitor-exit(r10)
            return
        L_0x000d:
            int r1 = r10.primaryInputIndex     // Catch:{ all -> 0x0096 }
            java.lang.Object r1 = r0.get(r1)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo r1 = (androidx.media3.effect.DefaultVideoCompositor.InputFrameInfo) r1     // Catch:{ all -> 0x0096 }
            com.google.common.collect.ImmutableList$Builder r2 = new com.google.common.collect.ImmutableList$Builder     // Catch:{ all -> 0x0096 }
            r2.<init>()     // Catch:{ all -> 0x0096 }
            r3 = 0
        L_0x001b:
            int r4 = r0.size()     // Catch:{ all -> 0x0096 }
            if (r3 >= r4) goto L_0x0038
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo r4 = (androidx.media3.effect.DefaultVideoCompositor.InputFrameInfo) r4     // Catch:{ all -> 0x0096 }
            androidx.media3.common.GlTextureInfo r4 = r4.texture     // Catch:{ all -> 0x0096 }
            androidx.media3.common.util.Size r5 = new androidx.media3.common.util.Size     // Catch:{ all -> 0x0096 }
            int r6 = r4.width     // Catch:{ all -> 0x0096 }
            int r4 = r4.height     // Catch:{ all -> 0x0096 }
            r5.<init>(r6, r4)     // Catch:{ all -> 0x0096 }
            r2.add((java.lang.Object) r5)     // Catch:{ all -> 0x0096 }
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0038:
            androidx.media3.effect.VideoCompositorSettings r3 = r10.settings     // Catch:{ all -> 0x0096 }
            com.google.common.collect.ImmutableList r2 = r2.build()     // Catch:{ all -> 0x0096 }
            androidx.media3.common.util.Size r2 = r3.getOutputSize(r2)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.TexturePool r3 = r10.outputTexturePool     // Catch:{ all -> 0x0096 }
            androidx.media3.common.GlObjectsProvider r4 = r10.glObjectsProvider     // Catch:{ all -> 0x0096 }
            int r5 = r2.getWidth()     // Catch:{ all -> 0x0096 }
            int r2 = r2.getHeight()     // Catch:{ all -> 0x0096 }
            r3.ensureConfigured(r4, r5, r2)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.TexturePool r2 = r10.outputTexturePool     // Catch:{ all -> 0x0096 }
            androidx.media3.common.GlTextureInfo r5 = r2.useTexture()     // Catch:{ all -> 0x0096 }
            long r6 = r1.presentationTimeUs     // Catch:{ all -> 0x0096 }
            androidx.media3.common.util.LongArrayQueue r1 = r10.outputTextureTimestamps     // Catch:{ all -> 0x0096 }
            r1.add(r6)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.DefaultVideoCompositor$CompositorGlProgram r1 = r10.compositorGlProgram     // Catch:{ all -> 0x0096 }
            r1.drawFrame(r0, r5)     // Catch:{ all -> 0x0096 }
            long r8 = androidx.media3.common.util.GlUtil.createGlSyncFence()     // Catch:{ all -> 0x0096 }
            androidx.media3.common.util.LongArrayQueue r0 = r10.syncObjects     // Catch:{ all -> 0x0096 }
            r0.add(r8)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.GlTextureProducer$Listener r3 = r10.textureOutputListener     // Catch:{ all -> 0x0096 }
            r4 = r10
            r3.onTextureRendered(r4, r5, r6, r8)     // Catch:{ all -> 0x0096 }
            android.util.SparseArray<androidx.media3.effect.DefaultVideoCompositor$InputSource> r0 = r10.inputSources     // Catch:{ all -> 0x0096 }
            int r1 = r10.primaryInputIndex     // Catch:{ all -> 0x0096 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0096 }
            androidx.media3.effect.DefaultVideoCompositor$InputSource r0 = (androidx.media3.effect.DefaultVideoCompositor.InputSource) r0     // Catch:{ all -> 0x0096 }
            r1 = 1
            r10.releaseFrames(r0, r1)     // Catch:{ all -> 0x0096 }
            r10.releaseExcessFramesInAllSecondaryStreams()     // Catch:{ all -> 0x0096 }
            boolean r1 = r10.allInputsEnded     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x0094
            java.util.Queue<androidx.media3.effect.DefaultVideoCompositor$InputFrameInfo> r0 = r0.frameInfos     // Catch:{ all -> 0x0096 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0096 }
            if (r0 == 0) goto L_0x0094
            androidx.media3.effect.VideoCompositor$Listener r0 = r10.listener     // Catch:{ all -> 0x0096 }
            r0.onEnded()     // Catch:{ all -> 0x0096 }
        L_0x0094:
            monitor-exit(r10)
            return
        L_0x0096:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.DefaultVideoCompositor.maybeComposite():void");
    }

    private synchronized ImmutableList<InputFrameInfo> getFramesToComposite() {
        if (this.outputTexturePool.freeTextureCount() == 0) {
            return ImmutableList.of();
        }
        for (int i = 0; i < this.inputSources.size(); i++) {
            if (this.inputSources.valueAt(i).frameInfos.isEmpty()) {
                return ImmutableList.of();
            }
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        InputFrameInfo element = this.inputSources.get(this.primaryInputIndex).frameInfos.element();
        builder.add((Object) element);
        for (int i2 = 0; i2 < this.inputSources.size(); i2++) {
            if (this.inputSources.keyAt(i2) != this.primaryInputIndex) {
                InputSource valueAt = this.inputSources.valueAt(i2);
                if (valueAt.frameInfos.size() != 1 || valueAt.isInputEnded) {
                    Iterator it = valueAt.frameInfos.iterator();
                    long j = Long.MAX_VALUE;
                    InputFrameInfo inputFrameInfo = null;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        InputFrameInfo inputFrameInfo2 = (InputFrameInfo) it.next();
                        long j2 = inputFrameInfo2.presentationTimeUs;
                        long abs = Math.abs(j2 - element.presentationTimeUs);
                        if (abs < j) {
                            inputFrameInfo = inputFrameInfo2;
                            j = abs;
                        }
                        if (j2 > element.presentationTimeUs || (!it.hasNext() && valueAt.isInputEnded)) {
                            builder.add((Object) (InputFrameInfo) Assertions.checkNotNull(inputFrameInfo));
                        }
                    }
                    builder.add((Object) (InputFrameInfo) Assertions.checkNotNull(inputFrameInfo));
                } else {
                    return ImmutableList.of();
                }
            }
        }
        ImmutableList<InputFrameInfo> build = builder.build();
        if (build.size() == this.inputSources.size()) {
            return build;
        }
        return ImmutableList.of();
    }

    /* access modifiers changed from: private */
    /* renamed from: releaseOutputTextureInternal */
    public synchronized void m415lambda$releaseOutputTexture$0$androidxmedia3effectDefaultVideoCompositor(long j) throws VideoFrameProcessingException, GlUtil.GlException {
        while (this.outputTexturePool.freeTextureCount() < this.outputTexturePool.capacity() && this.outputTextureTimestamps.element() <= j) {
            this.outputTexturePool.freeTexture();
            this.outputTextureTimestamps.remove();
            GlUtil.deleteSyncObject(this.syncObjects.remove());
        }
        maybeComposite();
    }

    /* access modifiers changed from: private */
    public void releaseGlObjects() {
        try {
            this.compositorGlProgram.release();
            this.outputTexturePool.deleteAllTextures();
            GlUtil.destroyEglSurface(this.eglDisplay, this.placeholderEglSurface);
            try {
                this.glObjectsProvider.release((EGLDisplay) Assertions.checkNotNull(this.eglDisplay));
            } catch (GlUtil.GlException e) {
                Log.e(TAG, "Error releasing GL objects", e);
            }
        } catch (GlUtil.GlException e2) {
            Log.e(TAG, "Error releasing GL resources", e2);
            this.glObjectsProvider.release((EGLDisplay) Assertions.checkNotNull(this.eglDisplay));
        } catch (Throwable th) {
            try {
                this.glObjectsProvider.release((EGLDisplay) Assertions.checkNotNull(this.eglDisplay));
            } catch (GlUtil.GlException e3) {
                Log.e(TAG, "Error releasing GL objects", e3);
            }
            throw th;
        }
    }

    private static final class CompositorGlProgram {
        private static final String FRAGMENT_SHADER_PATH = "shaders/fragment_shader_alpha_scale_es2.glsl";
        private static final String TAG = "CompositorGlProgram";
        private static final String VERTEX_SHADER_PATH = "shaders/vertex_shader_transformation_es2.glsl";
        private final Context context;
        private GlProgram glProgram;
        private final OverlayMatrixProvider overlayMatrixProvider = new OverlayMatrixProvider();

        public CompositorGlProgram(Context context2) {
            this.context = context2;
        }

        public void drawFrame(List<InputFrameInfo> list, GlTextureInfo glTextureInfo) throws GlUtil.GlException, VideoFrameProcessingException {
            ensureConfigured();
            GlUtil.focusFramebufferUsingCurrentContext(glTextureInfo.fboId, glTextureInfo.width, glTextureInfo.height);
            this.overlayMatrixProvider.configure(new Size(glTextureInfo.width, glTextureInfo.height));
            GlUtil.clearFocusedBuffers();
            ((GlProgram) Assertions.checkNotNull(this.glProgram)).use();
            GLES20.glEnable(3042);
            GLES20.glBlendFuncSeparate(770, 771, 1, 771);
            GlUtil.checkGlError();
            for (int size = list.size() - 1; size >= 0; size--) {
                blendOntoFocusedTexture(list.get(size));
            }
            GLES20.glDisable(3042);
            GlUtil.checkGlError();
        }

        public void release() {
            try {
                GlProgram glProgram2 = this.glProgram;
                if (glProgram2 != null) {
                    glProgram2.delete();
                }
            } catch (GlUtil.GlException e) {
                Log.e(TAG, "Error releasing GL Program", e);
            }
        }

        private void ensureConfigured() throws VideoFrameProcessingException, GlUtil.GlException {
            if (this.glProgram == null) {
                try {
                    GlProgram glProgram2 = new GlProgram(this.context, VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
                    this.glProgram = glProgram2;
                    glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
                    this.glProgram.setFloatsUniform("uTexTransformationMatrix", GlUtil.create4x4IdentityMatrix());
                } catch (IOException e) {
                    throw new VideoFrameProcessingException((Throwable) e);
                }
            }
        }

        private void blendOntoFocusedTexture(InputFrameInfo inputFrameInfo) throws GlUtil.GlException {
            GlProgram glProgram2 = (GlProgram) Assertions.checkNotNull(this.glProgram);
            GlTextureInfo glTextureInfo = inputFrameInfo.texture;
            glProgram2.setSamplerTexIdUniform("uTexSampler", glTextureInfo.texId, 0);
            glProgram2.setFloatsUniform("uTransformationMatrix", this.overlayMatrixProvider.getTransformationMatrix(new Size(glTextureInfo.width, glTextureInfo.height), inputFrameInfo.overlaySettings));
            glProgram2.setFloatUniform("uAlphaScale", inputFrameInfo.overlaySettings.alphaScale);
            glProgram2.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
            GlUtil.checkGlError();
        }
    }

    private static final class InputFrameInfo {
        public final OverlaySettings overlaySettings;
        public final long presentationTimeUs;
        public final GlTextureInfo texture;
        public final GlTextureProducer textureProducer;

        public InputFrameInfo(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, OverlaySettings overlaySettings2) {
            this.textureProducer = glTextureProducer;
            this.texture = glTextureInfo;
            this.presentationTimeUs = j;
            this.overlaySettings = overlaySettings2;
        }
    }
}
