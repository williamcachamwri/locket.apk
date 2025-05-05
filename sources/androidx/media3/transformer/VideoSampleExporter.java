package androidx.media3.transformer;

import android.content.Context;
import android.media.MediaCodec;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.VideoCompositorSettings;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.TransformerVideoGraph;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.util.concurrent.MoreExecutors;
import java.nio.ByteBuffer;
import java.util.List;
import org.checkerframework.dataflow.qual.Pure;

final class VideoSampleExporter extends SampleExporter {
    private final DecoderInputBuffer encoderOutputBuffer;
    /* access modifiers changed from: private */
    public final EncoderWrapper encoderWrapper;
    /* access modifiers changed from: private */
    public volatile long finalFramePresentationTimeUs = C.TIME_UNSET;
    private boolean hasMuxedTimestampZero;
    /* access modifiers changed from: private */
    public final long initialTimestampOffsetUs;
    private long lastMuxerInputBufferTimestampUs = C.TIME_UNSET;
    private final VideoGraphWrapper videoGraph;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoSampleExporter(android.content.Context r20, androidx.media3.common.Format r21, androidx.media3.transformer.TransformationRequest r22, androidx.media3.effect.VideoCompositorSettings r23, java.util.List<androidx.media3.common.Effect> r24, androidx.media3.common.VideoFrameProcessor.Factory r25, androidx.media3.transformer.Codec.EncoderFactory r26, androidx.media3.transformer.MuxerWrapper r27, androidx.media3.common.util.Consumer<androidx.media3.transformer.ExportException> r28, androidx.media3.transformer.FallbackListener r29, androidx.media3.common.DebugViewProvider r30, long r31, boolean r33, boolean r34, int r35) throws androidx.media3.transformer.ExportException {
        /*
            r19 = this;
            r11 = r19
            r0 = r21
            r1 = r25
            r2 = r27
            r11.<init>(r0, r2)
            r3 = r31
            r11.initialTimestampOffsetUs = r3
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r11.finalFramePresentationTimeUs = r3
            r11.lastMuxerInputBufferTimestampUs = r3
            androidx.media3.common.ColorInfo r3 = r0.colorInfo
            java.lang.Object r3 = androidx.media3.common.util.Assertions.checkNotNull(r3)
            androidx.media3.common.ColorInfo r3 = (androidx.media3.common.ColorInfo) r3
            int r4 = r3.colorTransfer
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L_0x004b
            java.lang.String r4 = r0.sampleMimeType
            java.lang.String r7 = "image/jpeg_r"
            boolean r4 = java.util.Objects.equals(r4, r7)
            if (r4 == 0) goto L_0x0048
            androidx.media3.common.ColorInfo$Builder r4 = new androidx.media3.common.ColorInfo$Builder
            r4.<init>()
            r7 = 6
            androidx.media3.common.ColorInfo$Builder r4 = r4.setColorSpace(r7)
            r7 = 7
            androidx.media3.common.ColorInfo$Builder r4 = r4.setColorTransfer(r7)
            androidx.media3.common.ColorInfo$Builder r4 = r4.setColorRange(r5)
            androidx.media3.common.ColorInfo r4 = r4.build()
            goto L_0x004c
        L_0x0048:
            androidx.media3.common.ColorInfo r4 = androidx.media3.common.ColorInfo.SDR_BT709_LIMITED
            goto L_0x004c
        L_0x004b:
            r4 = r3
        L_0x004c:
            androidx.media3.transformer.VideoSampleExporter$EncoderWrapper r7 = new androidx.media3.transformer.VideoSampleExporter$EncoderWrapper
            androidx.media3.common.Format$Builder r0 = r21.buildUpon()
            androidx.media3.common.Format$Builder r0 = r0.setColorInfo(r4)
            androidx.media3.common.Format r14 = r0.build()
            com.google.common.collect.ImmutableList r16 = r2.getSupportedSampleMimeTypes(r6)
            r12 = r7
            r13 = r26
            r15 = r34
            r17 = r22
            r18 = r29
            r12.<init>(r13, r14, r15, r16, r17, r18)
            r11.encoderWrapper = r7
            androidx.media3.decoder.DecoderInputBuffer r0 = new androidx.media3.decoder.DecoderInputBuffer
            r2 = 0
            r0.<init>(r2)
            r11.encoderOutputBuffer = r0
            int r0 = r7.getHdrModeAfterFallback()
            if (r0 != r6) goto L_0x0081
            boolean r0 = androidx.media3.common.ColorInfo.isTransferHdr(r3)
            if (r0 == 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r5 = r2
        L_0x0082:
            if (r5 == 0) goto L_0x0088
            androidx.media3.common.ColorInfo r0 = androidx.media3.common.ColorInfo.SDR_BT709_LIMITED
            r5 = r0
            goto L_0x0089
        L_0x0088:
            r5 = r4
        L_0x0089:
            androidx.media3.transformer.VideoSampleExporter$VideoGraphWrapper r0 = new androidx.media3.transformer.VideoSampleExporter$VideoGraphWrapper     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            if (r33 == 0) goto L_0x0093
            androidx.media3.transformer.TransformerMultipleInputVideoGraph$Factory r2 = new androidx.media3.transformer.TransformerMultipleInputVideoGraph$Factory     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            r2.<init>(r1)     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            goto L_0x0098
        L_0x0093:
            androidx.media3.transformer.TransformerSingleInputVideoGraph$Factory r2 = new androidx.media3.transformer.TransformerSingleInputVideoGraph$Factory     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            r2.<init>(r1)     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
        L_0x0098:
            r4 = r2
            r1 = r0
            r2 = r19
            r3 = r20
            r6 = r28
            r7 = r30
            r8 = r23
            r9 = r24
            r10 = r35
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            r11.videoGraph = r0     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            r0.initialize()     // Catch:{ VideoFrameProcessingException -> 0x00b1 }
            return
        L_0x00b1:
            r0 = move-exception
            androidx.media3.transformer.ExportException r0 = androidx.media3.transformer.ExportException.createForVideoFrameProcessingException(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.VideoSampleExporter.<init>(android.content.Context, androidx.media3.common.Format, androidx.media3.transformer.TransformationRequest, androidx.media3.effect.VideoCompositorSettings, java.util.List, androidx.media3.common.VideoFrameProcessor$Factory, androidx.media3.transformer.Codec$EncoderFactory, androidx.media3.transformer.MuxerWrapper, androidx.media3.common.util.Consumer, androidx.media3.transformer.FallbackListener, androidx.media3.common.DebugViewProvider, long, boolean, boolean, int):void");
    }

    public GraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i) throws ExportException {
        try {
            return this.videoGraph.createInput(i);
        } catch (VideoFrameProcessingException e) {
            throw ExportException.createForVideoFrameProcessingException(e);
        }
    }

    public void release() {
        this.videoGraph.release();
        this.encoderWrapper.release();
    }

    /* access modifiers changed from: protected */
    public Format getMuxerInputFormat() throws ExportException {
        return this.encoderWrapper.getOutputFormat();
    }

    /* access modifiers changed from: protected */
    public DecoderInputBuffer getMuxerInputBuffer() throws ExportException {
        this.encoderOutputBuffer.data = this.encoderWrapper.getOutputBuffer();
        if (this.encoderOutputBuffer.data == null) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) Assertions.checkNotNull(this.encoderWrapper.getOutputBufferInfo());
        if (bufferInfo.presentationTimeUs == 0 && this.videoGraph.hasProducedFrameWithTimestampZero() == this.hasMuxedTimestampZero && this.finalFramePresentationTimeUs != C.TIME_UNSET && bufferInfo.size > 0) {
            bufferInfo.presentationTimeUs = this.finalFramePresentationTimeUs;
        }
        this.encoderOutputBuffer.timeUs = bufferInfo.presentationTimeUs;
        this.encoderOutputBuffer.setFlags(bufferInfo.flags);
        this.lastMuxerInputBufferTimestampUs = bufferInfo.presentationTimeUs;
        return this.encoderOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public void releaseMuxerInputBuffer() throws ExportException {
        if (this.lastMuxerInputBufferTimestampUs == 0) {
            this.hasMuxedTimestampZero = true;
        }
        this.encoderWrapper.releaseOutputBuffer(false);
        this.videoGraph.onEncoderBufferReleased();
    }

    /* access modifiers changed from: protected */
    public boolean isMuxerInputEnded() {
        return this.encoderWrapper.isEnded();
    }

    static final class EncoderWrapper {
        private static final String DEFAULT_OUTPUT_MIME_TYPE = "video/hevc";
        private volatile Codec encoder;
        private final Codec.EncoderFactory encoderFactory;
        private SurfaceInfo encoderSurfaceInfo;
        private final FallbackListener fallbackListener;
        private final int hdrModeAfterFallback;
        private final Format inputFormat;
        private final List<String> muxerSupportedMimeTypes;
        private volatile int outputRotationDegrees;
        private final boolean portraitEncodingEnabled;
        private volatile boolean releaseEncoder;
        private final String requestedOutputMimeType;
        private final TransformationRequest transformationRequest;

        public EncoderWrapper(Codec.EncoderFactory encoderFactory2, Format format, boolean z, List<String> list, TransformationRequest transformationRequest2, FallbackListener fallbackListener2) {
            Assertions.checkArgument(format.colorInfo != null);
            this.encoderFactory = encoderFactory2;
            this.inputFormat = format;
            this.portraitEncodingEnabled = z;
            this.muxerSupportedMimeTypes = list;
            this.transformationRequest = transformationRequest2;
            this.fallbackListener = fallbackListener2;
            Pair<String, Integer> requestedOutputMimeTypeAndHdrModeAfterFallback = getRequestedOutputMimeTypeAndHdrModeAfterFallback(format, transformationRequest2);
            this.requestedOutputMimeType = (String) requestedOutputMimeTypeAndHdrModeAfterFallback.first;
            this.hdrModeAfterFallback = ((Integer) requestedOutputMimeTypeAndHdrModeAfterFallback.second).intValue();
        }

        private static Pair<String, Integer> getRequestedOutputMimeTypeAndHdrModeAfterFallback(Format format, TransformationRequest transformationRequest2) {
            String str = (String) Assertions.checkNotNull(format.sampleMimeType);
            if (transformationRequest2.videoMimeType != null) {
                str = transformationRequest2.videoMimeType;
            } else if (MimeTypes.isImage(str)) {
                str = "video/hevc";
            }
            return TransformerUtil.getOutputMimeTypeAndHdrModeAfterFallback(transformationRequest2.hdrMode, str, format.colorInfo);
        }

        public int getHdrModeAfterFallback() {
            return this.hdrModeAfterFallback;
        }

        public SurfaceInfo getSurfaceInfo(int i, int i2) throws ExportException {
            if (this.releaseEncoder) {
                return null;
            }
            SurfaceInfo surfaceInfo = this.encoderSurfaceInfo;
            if (surfaceInfo != null) {
                return surfaceInfo;
            }
            if (i < i2 && !this.portraitEncodingEnabled) {
                this.outputRotationDegrees = 90;
                int i3 = i2;
                i2 = i;
                i = i3;
            }
            if (this.inputFormat.rotationDegrees % RotationOptions.ROTATE_180 == this.outputRotationDegrees % RotationOptions.ROTATE_180) {
                this.outputRotationDegrees = this.inputFormat.rotationDegrees;
            }
            Format.Builder height = new Format.Builder().setWidth(i).setHeight(i2);
            boolean z = false;
            Format build = height.setRotationDegrees(0).setFrameRate(this.inputFormat.frameRate).setSampleMimeType(this.requestedOutputMimeType).setColorInfo(getSupportedInputColor()).setCodecs(this.inputFormat.codecs).build();
            this.encoder = this.encoderFactory.createForVideoEncoding(build.buildUpon().setSampleMimeType(SampleExporter.findSupportedMimeTypeForEncoderAndMuxer(build, this.muxerSupportedMimeTypes)).build());
            Format configurationFormat = this.encoder.getConfigurationFormat();
            FallbackListener fallbackListener2 = this.fallbackListener;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            if (this.outputRotationDegrees != 0) {
                z = true;
            }
            fallbackListener2.onTransformationRequestFinalized(createSupportedTransformationRequest(transformationRequest2, z, build, configurationFormat, this.hdrModeAfterFallback));
            this.encoderSurfaceInfo = new SurfaceInfo(this.encoder.getInputSurface(), configurationFormat.width, configurationFormat.height, this.outputRotationDegrees, true);
            if (this.releaseEncoder) {
                this.encoder.release();
            }
            return this.encoderSurfaceInfo;
        }

        private ColorInfo getSupportedInputColor() {
            if (ColorInfo.isTransferHdr(this.inputFormat.colorInfo) && this.hdrModeAfterFallback != 0) {
                return ColorInfo.SDR_BT709_LIMITED;
            }
            if (ColorInfo.SRGB_BT709_FULL.equals(this.inputFormat.colorInfo)) {
                return ColorInfo.SDR_BT709_LIMITED;
            }
            return (ColorInfo) Assertions.checkNotNull(this.inputFormat.colorInfo);
        }

        @Pure
        private static TransformationRequest createSupportedTransformationRequest(TransformationRequest transformationRequest2, boolean z, Format format, Format format2, int i) {
            TransformationRequest.Builder buildUpon = transformationRequest2.buildUpon();
            if (transformationRequest2.hdrMode != i) {
                buildUpon.setHdrMode(i);
            }
            if (!Util.areEqual(format.sampleMimeType, format2.sampleMimeType)) {
                buildUpon.setVideoMimeType(format2.sampleMimeType);
            }
            if (z) {
                if (format.width != format2.width) {
                    buildUpon.setResolution(format2.width);
                }
            } else if (format.height != format2.height) {
                buildUpon.setResolution(format2.height);
            }
            return buildUpon.build();
        }

        public void signalEndOfInputStream() throws ExportException {
            if (this.encoder != null) {
                this.encoder.signalEndOfInputStream();
            }
        }

        public Format getOutputFormat() throws ExportException {
            if (this.encoder == null) {
                return null;
            }
            Format outputFormat = this.encoder.getOutputFormat();
            return (outputFormat == null || this.outputRotationDegrees == 0) ? outputFormat : outputFormat.buildUpon().setRotationDegrees(this.outputRotationDegrees).build();
        }

        public ByteBuffer getOutputBuffer() throws ExportException {
            if (this.encoder != null) {
                return this.encoder.getOutputBuffer();
            }
            return null;
        }

        public MediaCodec.BufferInfo getOutputBufferInfo() throws ExportException {
            if (this.encoder != null) {
                return this.encoder.getOutputBufferInfo();
            }
            return null;
        }

        public void releaseOutputBuffer(boolean z) throws ExportException {
            if (this.encoder != null) {
                this.encoder.releaseOutputBuffer(z);
            }
        }

        public boolean isEnded() {
            return this.encoder != null && this.encoder.isEnded();
        }

        public void release() {
            if (this.encoder != null) {
                this.encoder.release();
            }
            this.releaseEncoder = true;
        }
    }

    private final class VideoGraphWrapper implements TransformerVideoGraph, VideoGraph.Listener {
        private final Consumer<ExportException> errorConsumer;
        private int framesAvailableToRender;
        private int framesInEncoder;
        private final Object lock;
        private final int maxFramesInEncoder;
        private final boolean renderFramesAutomatically;
        final /* synthetic */ VideoSampleExporter this$0;
        private final TransformerVideoGraph videoGraph;

        public VideoGraphWrapper(VideoSampleExporter videoSampleExporter, Context context, TransformerVideoGraph.Factory factory, ColorInfo colorInfo, Consumer<ExportException> consumer, DebugViewProvider debugViewProvider, VideoCompositorSettings videoCompositorSettings, List<Effect> list, int i) throws VideoFrameProcessingException {
            int i2 = i;
            this.this$0 = videoSampleExporter;
            this.errorConsumer = consumer;
            this.maxFramesInEncoder = i2;
            boolean z = i2 < 1;
            this.renderFramesAutomatically = z;
            this.lock = new Object();
            this.videoGraph = factory.create(context, colorInfo, debugViewProvider, this, MoreExecutors.directExecutor(), videoCompositorSettings, list, videoSampleExporter.initialTimestampOffsetUs, z);
        }

        public void onOutputSizeChanged(int i, int i2) {
            SurfaceInfo surfaceInfo;
            try {
                surfaceInfo = this.this$0.encoderWrapper.getSurfaceInfo(i, i2);
            } catch (ExportException e) {
                this.errorConsumer.accept(e);
                surfaceInfo = null;
            }
            setOutputSurfaceInfo(surfaceInfo);
        }

        public void onOutputFrameAvailableForRendering(long j) {
            if (!this.renderFramesAutomatically) {
                synchronized (this.lock) {
                    this.framesAvailableToRender++;
                }
                maybeRenderEarliestOutputFrame();
            }
        }

        public void onEnded(long j) {
            long unused = this.this$0.finalFramePresentationTimeUs = j;
            try {
                this.this$0.encoderWrapper.signalEndOfInputStream();
            } catch (ExportException e) {
                this.errorConsumer.accept(e);
            }
        }

        public void onError(VideoFrameProcessingException videoFrameProcessingException) {
            this.errorConsumer.accept(ExportException.createForVideoFrameProcessingException(videoFrameProcessingException));
        }

        public void initialize() throws VideoFrameProcessingException {
            this.videoGraph.initialize();
        }

        public void registerInput(int i) throws VideoFrameProcessingException {
            this.videoGraph.registerInput(i);
        }

        public VideoFrameProcessor getProcessor(int i) {
            return this.videoGraph.getProcessor(i);
        }

        public GraphInput createInput(int i) throws VideoFrameProcessingException {
            return this.videoGraph.createInput(i);
        }

        public void renderOutputFrameWithMediaPresentationTime() {
            this.videoGraph.renderOutputFrameWithMediaPresentationTime();
        }

        public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
            this.videoGraph.setOutputSurfaceInfo(surfaceInfo);
        }

        public boolean hasProducedFrameWithTimestampZero() {
            return this.videoGraph.hasProducedFrameWithTimestampZero();
        }

        public void release() {
            this.videoGraph.release();
        }

        public void onEncoderBufferReleased() {
            if (!this.renderFramesAutomatically) {
                synchronized (this.lock) {
                    Assertions.checkState(this.framesInEncoder > 0);
                    this.framesInEncoder--;
                }
                maybeRenderEarliestOutputFrame();
            }
        }

        private void maybeRenderEarliestOutputFrame() {
            boolean z;
            int i;
            synchronized (this.lock) {
                int i2 = this.framesAvailableToRender;
                if (i2 <= 0 || (i = this.framesInEncoder) >= this.maxFramesInEncoder) {
                    z = false;
                } else {
                    z = true;
                    this.framesInEncoder = i + 1;
                    this.framesAvailableToRender = i2 - 1;
                }
            }
            if (z) {
                renderOutputFrameWithMediaPresentationTime();
            }
        }
    }
}
