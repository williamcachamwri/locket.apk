package androidx.media3.transformer;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.effect.VideoCompositorSettings;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.AudioMixer;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExportResult;
import com.google.common.collect.ImmutableList;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class TransformerInternal {
    private static final int DRAIN_EXPORTERS_DELAY_MS = 10;
    private static final int END_REASON_CANCELLED = 1;
    private static final int END_REASON_COMPLETED = 0;
    private static final int END_REASON_ERROR = 2;
    private static final int MSG_DRAIN_EXPORTERS = 3;
    private static final int MSG_END = 4;
    private static final int MSG_REGISTER_SAMPLE_EXPORTER = 2;
    private static final int MSG_START = 1;
    private static final String TAG = "TransformerInternal";
    private final HandlerWrapper applicationHandler;
    /* access modifiers changed from: private */
    public final AssetLoaderInputTracker assetLoaderInputTracker;
    /* access modifiers changed from: private */
    public final Object assetLoaderLock;
    private RuntimeException cancelException;
    private final ConditionVariable canceledConditionVariable;
    private final Clock clock;
    private final Composition composition;
    /* access modifiers changed from: private */
    public final boolean compositionHasLoopingSequence;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public long currentMaxSequenceDurationUs;
    /* access modifiers changed from: private */
    public final CapturingEncoderFactory encoderFactory;
    /* access modifiers changed from: private */
    public final HandlerWrapper internalHandler;
    private final HandlerThread internalHandlerThread;
    private final ProgressHolder internalProgressHolder;
    private boolean isDrainingExporters;
    private final Listener listener;
    /* access modifiers changed from: private */
    public final int maxFramesInEncoder;
    /* access modifiers changed from: private */
    public final MuxerWrapper muxerWrapper;
    /* access modifiers changed from: private */
    public int nonLoopingSequencesWithNonFinalDuration;
    /* access modifiers changed from: private */
    public final boolean portraitEncodingEnabled;
    private final Object progressLock;
    private int progressState;
    private int progressValue;
    private volatile boolean released;
    private final List<SampleExporter> sampleExporters;
    /* access modifiers changed from: private */
    public final List<SequenceAssetLoader> sequenceAssetLoaders = new ArrayList();
    /* access modifiers changed from: private */
    public final Object setMaxSequenceDurationUsLock;
    /* access modifiers changed from: private */
    public final long videoSampleTimestampOffsetUs;

    public interface Listener {
        void onCompleted(ImmutableList<ExportResult.ProcessedInput> immutableList, String str, String str2);

        void onError(ImmutableList<ExportResult.ProcessedInput> immutableList, String str, String str2, ExportException exportException);
    }

    static /* synthetic */ int access$1310(TransformerInternal transformerInternal) {
        int i = transformerInternal.nonLoopingSequencesWithNonFinalDuration;
        transformerInternal.nonLoopingSequencesWithNonFinalDuration = i - 1;
        return i;
    }

    public TransformerInternal(Context context2, Composition composition2, TransformationRequest transformationRequest, AssetLoader.Factory factory, AudioMixer.Factory factory2, VideoFrameProcessor.Factory factory3, Codec.EncoderFactory encoderFactory2, boolean z, int i, MuxerWrapper muxerWrapper2, Listener listener2, FallbackListener fallbackListener, HandlerWrapper handlerWrapper, DebugViewProvider debugViewProvider, Clock clock2, long j) {
        Composition composition3 = composition2;
        Clock clock3 = clock2;
        this.context = context2;
        this.composition = composition3;
        this.encoderFactory = new CapturingEncoderFactory(encoderFactory2);
        this.portraitEncodingEnabled = z;
        this.maxFramesInEncoder = i;
        this.listener = listener2;
        this.applicationHandler = handlerWrapper;
        this.clock = clock3;
        this.videoSampleTimestampOffsetUs = j;
        this.muxerWrapper = muxerWrapper2;
        Log.i("TransformerInternal", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "]");
        HandlerThread handlerThread = new HandlerThread("Transformer:Internal");
        this.internalHandlerThread = handlerThread;
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        this.assetLoaderLock = new Object();
        this.assetLoaderInputTracker = new AssetLoaderInputTracker(composition3);
        for (int i2 = 0; i2 < composition3.sequences.size(); i2++) {
            SequenceAssetLoaderListener sequenceAssetLoaderListener = new SequenceAssetLoaderListener(i2, composition2, transformationRequest, factory2, factory3, fallbackListener, debugViewProvider);
            EditedMediaItemSequence editedMediaItemSequence = (EditedMediaItemSequence) composition3.sequences.get(i2);
            List<SequenceAssetLoader> list = this.sequenceAssetLoaders;
            SequenceAssetLoader sequenceAssetLoader = r0;
            SequenceAssetLoader sequenceAssetLoader2 = new SequenceAssetLoader(editedMediaItemSequence, composition3.forceAudioTrack, factory, new AssetLoader.CompositionSettings(transformationRequest.hdrMode, composition3.retainHdrFromUltraHdrImage), sequenceAssetLoaderListener, clock2, looper);
            list.add(sequenceAssetLoader);
            if (!editedMediaItemSequence.isLooping) {
                this.nonLoopingSequencesWithNonFinalDuration++;
            }
        }
        this.compositionHasLoopingSequence = this.nonLoopingSequencesWithNonFinalDuration != composition3.sequences.size();
        this.setMaxSequenceDurationUsLock = new Object();
        this.canceledConditionVariable = new ConditionVariable();
        this.progressLock = new Object();
        this.internalProgressHolder = new ProgressHolder();
        this.sampleExporters = new ArrayList();
        this.internalHandler = clock3.createHandler(looper, new TransformerInternal$$ExternalSyntheticLambda0(this));
    }

    public void start() {
        verifyInternalThreadAlive();
        this.internalHandler.sendEmptyMessage(1);
        synchronized (this.progressLock) {
            this.progressState = 1;
            this.progressValue = 0;
        }
        DebugTraceUtil.logEvent("TransformerInternal", DebugTraceUtil.EVENT_START, C.TIME_UNSET, "%s", Util.DEVICE_DEBUG_INFO);
    }

    public int getProgress(ProgressHolder progressHolder) {
        int i;
        if (this.released) {
            return 0;
        }
        synchronized (this.progressLock) {
            if (this.progressState == 2) {
                progressHolder.progress = this.progressValue;
            }
            i = this.progressState;
        }
        return i;
    }

    public void cancel() {
        if (!this.released) {
            verifyInternalThreadAlive();
            this.internalHandler.obtainMessage(4, 1, 0, (Object) null).sendToTarget();
            this.clock.onThreadBlocked();
            this.canceledConditionVariable.blockUninterruptible();
            this.canceledConditionVariable.close();
            RuntimeException runtimeException = this.cancelException;
            if (runtimeException != null) {
                throw runtimeException;
            }
        }
    }

    public void endWithCompletion() {
        verifyInternalThreadAlive();
        this.internalHandler.obtainMessage(4, 0, 0, (Object) null).sendToTarget();
    }

    public void endWithException(ExportException exportException) {
        verifyInternalThreadAlive();
        this.internalHandler.obtainMessage(4, 2, 0, exportException).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void verifyInternalThreadAlive() {
        Assertions.checkState(this.internalHandlerThread.isAlive(), "Internal thread is dead.");
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (this.released && message.what != 4) {
            return true;
        }
        try {
            int i = message.what;
            if (i == 1) {
                startInternal();
            } else if (i == 2) {
                registerSampleExporterInternal((SampleExporter) message.obj);
            } else if (i == 3) {
                drainExportersInternal();
            } else if (i != 4) {
                return false;
            } else {
                endInternal(message.arg1, (ExportException) message.obj);
            }
        } catch (ExportException e) {
            endInternal(2, e);
        } catch (RuntimeException e2) {
            endInternal(2, ExportException.createForUnexpected(e2));
        }
        return true;
    }

    private void startInternal() {
        for (int i = 0; i < this.sequenceAssetLoaders.size(); i++) {
            this.sequenceAssetLoaders.get(i).start();
        }
    }

    private void registerSampleExporterInternal(SampleExporter sampleExporter) {
        this.sampleExporters.add(sampleExporter);
        if (!this.isDrainingExporters) {
            this.internalHandler.sendEmptyMessage(3);
            this.isDrainingExporters = true;
        }
    }

    private void drainExportersInternal() throws ExportException {
        for (int i = 0; i < this.sampleExporters.size(); i++) {
            do {
            } while (this.sampleExporters.get(i).processData());
        }
        updateProgressInternal();
        if (!this.muxerWrapper.isEnded()) {
            this.internalHandler.sendEmptyMessageDelayed(3, 10);
        }
    }

    private void endInternal(int i, ExportException exportException) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i2 = 0; i2 < this.sequenceAssetLoaders.size(); i2++) {
            builder.addAll((Iterable) this.sequenceAssetLoaders.get(i2).getProcessedInputs());
        }
        boolean z = i == 1;
        boolean z2 = this.released;
        ExportException exportException2 = null;
        if (!this.released) {
            this.released = true;
            synchronized (this.progressLock) {
                this.progressState = 0;
                this.progressValue = 0;
            }
            Log.i("TransformerInternal", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
            for (int i3 = 0; i3 < this.sampleExporters.size(); i3++) {
                try {
                    this.sampleExporters.get(i3).release();
                } catch (RuntimeException e) {
                    if (exportException2 == null) {
                        exportException2 = ExportException.createForUnexpected(e);
                        this.cancelException = e;
                    }
                }
            }
            for (int i4 = 0; i4 < this.sequenceAssetLoaders.size(); i4++) {
                try {
                    this.sequenceAssetLoaders.get(i4).release();
                } catch (RuntimeException e2) {
                    if (exportException2 == null) {
                        ExportException createForUnexpected = ExportException.createForUnexpected(e2);
                        this.cancelException = e2;
                        exportException2 = createForUnexpected;
                    }
                }
            }
            try {
                this.muxerWrapper.finishWritingAndMaybeRelease(getMuxerReleaseReason(i));
            } catch (Muxer.MuxerException e3) {
                if (exportException2 == null) {
                    exportException2 = ExportException.createForMuxer(e3, 7001);
                }
            } catch (RuntimeException e4) {
                if (exportException2 == null) {
                    ExportException createForUnexpected2 = ExportException.createForUnexpected(e4);
                    this.cancelException = e4;
                    exportException2 = createForUnexpected2;
                }
            }
            HandlerWrapper handlerWrapper = this.internalHandler;
            HandlerThread handlerThread = this.internalHandlerThread;
            Objects.requireNonNull(handlerThread);
            handlerWrapper.post(new TransformerInternal$$ExternalSyntheticLambda1(handlerThread));
        }
        if (z) {
            this.canceledConditionVariable.open();
            return;
        }
        if (exportException == null) {
            exportException = exportException2;
        }
        if (exportException != null) {
            if (z2) {
                SentryLogcatAdapter.w("TransformerInternal", "Export error after export ended", exportException);
            } else {
                Assertions.checkState(this.applicationHandler.post(new TransformerInternal$$ExternalSyntheticLambda2(this, builder, exportException)));
            }
        } else if (!z2) {
            Assertions.checkState(this.applicationHandler.post(new TransformerInternal$$ExternalSyntheticLambda3(this, builder)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$endInternal$0$androidx-media3-transformer-TransformerInternal  reason: not valid java name */
    public /* synthetic */ void m1135lambda$endInternal$0$androidxmedia3transformerTransformerInternal(ImmutableList.Builder builder, ExportException exportException) {
        this.listener.onError(builder.build(), this.encoderFactory.getAudioEncoderName(), this.encoderFactory.getVideoEncoderName(), exportException);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$endInternal$1$androidx-media3-transformer-TransformerInternal  reason: not valid java name */
    public /* synthetic */ void m1136lambda$endInternal$1$androidxmedia3transformerTransformerInternal(ImmutableList.Builder builder) {
        this.listener.onCompleted(builder.build(), this.encoderFactory.getAudioEncoderName(), this.encoderFactory.getVideoEncoderName());
    }

    private int getMuxerReleaseReason(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        throw new IllegalStateException("Unexpected end reason " + i);
    }

    private void updateProgressInternal() {
        if (!this.released) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.sequenceAssetLoaders.size(); i3++) {
                if (!((EditedMediaItemSequence) this.composition.sequences.get(i3)).isLooping) {
                    this.internalProgressHolder.progress = 0;
                    int progress = this.sequenceAssetLoaders.get(i3).getProgress(this.internalProgressHolder);
                    if (progress != 2) {
                        synchronized (this.progressLock) {
                            this.progressState = progress;
                            this.progressValue = 0;
                        }
                        return;
                    }
                    i += this.internalProgressHolder.progress;
                    i2++;
                }
            }
            synchronized (this.progressLock) {
                this.progressState = 2;
                this.progressValue = i / i2;
            }
        }
    }

    private final class SequenceAssetLoaderListener implements AssetLoader.Listener {
        private final AudioMixer.Factory audioMixerFactory;
        private final Composition composition;
        private long currentSequenceDurationUs;
        private final DebugViewProvider debugViewProvider;
        private final FallbackListener fallbackListener;
        private final EditedMediaItem firstEditedMediaItem;
        private final int sequenceIndex;
        private final TransformationRequest transformationRequest;
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public void onDurationUs(long j) {
        }

        public SequenceAssetLoaderListener(int i, Composition composition2, TransformationRequest transformationRequest2, AudioMixer.Factory factory, VideoFrameProcessor.Factory factory2, FallbackListener fallbackListener2, DebugViewProvider debugViewProvider2) {
            this.sequenceIndex = i;
            this.firstEditedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition2.sequences.get(i)).editedMediaItems.get(0);
            this.composition = composition2;
            this.transformationRequest = transformationRequest2;
            this.audioMixerFactory = factory;
            this.videoFrameProcessorFactory = factory2;
            this.fallbackListener = fallbackListener2;
            this.debugViewProvider = debugViewProvider2;
        }

        public void onTrackCount(int i) {
            if (i <= 0) {
                onError(ExportException.createForAssetLoader(new IllegalStateException("AssetLoader instances must provide at least 1 track."), 1001));
                return;
            }
            synchronized (TransformerInternal.this.assetLoaderLock) {
                TransformerInternal.this.assetLoaderInputTracker.setTrackCount(this.sequenceIndex, i);
            }
        }

        public boolean onTrackAdded(Format format, int i) {
            boolean shouldTranscode;
            int processedTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
            Assertions.checkArgument(processedTrackType != 2 || !((EditedMediaItemSequence) this.composition.sequences.get(this.sequenceIndex)).hasGaps(), "Gaps in video sequences are not supported.");
            synchronized (TransformerInternal.this.assetLoaderLock) {
                TransformerInternal.this.assetLoaderInputTracker.registerTrack(this.sequenceIndex, format);
                if (TransformerInternal.this.assetLoaderInputTracker.hasRegisteredAllTracks()) {
                    int outputTrackCount = TransformerInternal.this.assetLoaderInputTracker.getOutputTrackCount();
                    TransformerInternal.this.muxerWrapper.setTrackCount(outputTrackCount);
                    this.fallbackListener.setTrackCount(outputTrackCount);
                }
                shouldTranscode = shouldTranscode(format, i);
                if (!shouldTranscode && TransformerUtil.getProcessedTrackType(format.sampleMimeType) == 2) {
                    TransformerUtil.maybeSetMuxerWrapperAdditionalRotationDegrees(TransformerInternal.this.muxerWrapper, this.firstEditedMediaItem.effects.videoEffects, format);
                }
                TransformerInternal.this.assetLoaderInputTracker.setShouldTranscode(processedTrackType, shouldTranscode);
            }
            return shouldTranscode;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0092, code lost:
            return r7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media3.transformer.SampleConsumer onOutputFormat(androidx.media3.common.Format r7) throws androidx.media3.transformer.ExportException {
            /*
                r6 = this;
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r0 = r0.assetLoaderLock
                monitor-enter(r0)
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r1 = r1.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                boolean r1 = r1.hasRegisteredAllTracks()     // Catch:{ all -> 0x0093 }
                r2 = 0
                if (r1 != 0) goto L_0x0016
                monitor-exit(r0)     // Catch:{ all -> 0x0093 }
                return r2
            L_0x0016:
                java.lang.String r1 = r7.sampleMimeType     // Catch:{ all -> 0x0093 }
                int r1 = androidx.media3.transformer.TransformerUtil.getProcessedTrackType(r1)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                boolean r3 = r3.shouldTranscode(r1)     // Catch:{ all -> 0x0093 }
                if (r3 == 0) goto L_0x003a
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                int r3 = r3.getIndexForPrimarySequence(r1)     // Catch:{ all -> 0x0093 }
                int r4 = r6.sequenceIndex     // Catch:{ all -> 0x0093 }
                if (r3 != r4) goto L_0x003d
                r6.createDecodedSampleExporter(r7)     // Catch:{ all -> 0x0093 }
                goto L_0x003d
            L_0x003a:
                r6.createEncodedSampleExporter(r1)     // Catch:{ all -> 0x0093 }
            L_0x003d:
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.SampleExporter r3 = r3.getSampleExporter(r1)     // Catch:{ all -> 0x0093 }
                if (r3 != 0) goto L_0x004b
                monitor-exit(r0)     // Catch:{ all -> 0x0093 }
                return r2
            L_0x004b:
                androidx.media3.transformer.EditedMediaItem r2 = r6.firstEditedMediaItem     // Catch:{ all -> 0x0093 }
                int r4 = r6.sequenceIndex     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.GraphInput r7 = r3.getInput(r2, r7, r4)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda0 r2 = new androidx.media3.transformer.TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0093 }
                r2.<init>(r6, r1, r7)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal r4 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                java.util.List r4 = r4.sequenceAssetLoaders     // Catch:{ all -> 0x0093 }
                int r5 = r6.sequenceIndex     // Catch:{ all -> 0x0093 }
                java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.SequenceAssetLoader r4 = (androidx.media3.transformer.SequenceAssetLoader) r4     // Catch:{ all -> 0x0093 }
                r4.addOnMediaItemChangedListener(r2, r1)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal r2 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r2 = r2.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                r2.registerGraphInput(r1)     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal r2 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r2 = r2.assetLoaderInputTracker     // Catch:{ all -> 0x0093 }
                boolean r1 = r2.hasAssociatedAllTracksWithGraphInput(r1)     // Catch:{ all -> 0x0093 }
                if (r1 == 0) goto L_0x0091
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                r1.verifyInternalThreadAlive()     // Catch:{ all -> 0x0093 }
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0093 }
                androidx.media3.common.util.HandlerWrapper r1 = r1.internalHandler     // Catch:{ all -> 0x0093 }
                r2 = 2
                androidx.media3.common.util.HandlerWrapper$Message r1 = r1.obtainMessage(r2, r3)     // Catch:{ all -> 0x0093 }
                r1.sendToTarget()     // Catch:{ all -> 0x0093 }
            L_0x0091:
                monitor-exit(r0)     // Catch:{ all -> 0x0093 }
                return r7
            L_0x0093:
                r7 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0093 }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.SequenceAssetLoaderListener.onOutputFormat(androidx.media3.common.Format):androidx.media3.transformer.SampleConsumer");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onOutputFormat$0$androidx-media3-transformer-TransformerInternal$SequenceAssetLoaderListener  reason: not valid java name */
        public /* synthetic */ void m1137lambda$onOutputFormat$0$androidxmedia3transformerTransformerInternal$SequenceAssetLoaderListener(int i, GraphInput graphInput, EditedMediaItem editedMediaItem, long j, Format format, boolean z) {
            onMediaItemChanged(i, j, z);
            graphInput.onMediaItemChanged(editedMediaItem, j, format, z);
        }

        public void onError(ExportException exportException) {
            TransformerInternal.this.endWithException(exportException);
        }

        private void createDecodedSampleExporter(Format format) throws ExportException {
            Format format2;
            Format format3 = format;
            int processedTrackType = TransformerUtil.getProcessedTrackType(format3.sampleMimeType);
            boolean z = false;
            Assertions.checkState(TransformerInternal.this.assetLoaderInputTracker.getSampleExporter(processedTrackType) == null);
            Format assetLoaderInputFormat = TransformerInternal.this.assetLoaderInputTracker.getAssetLoaderInputFormat(this.sequenceIndex, processedTrackType);
            if (MimeTypes.isAudio(format3.sampleMimeType)) {
                TransformerInternal.this.assetLoaderInputTracker.registerSampleExporter(1, new AudioSampleExporter(assetLoaderInputFormat, format, this.transformationRequest, this.firstEditedMediaItem, this.composition.effects.audioProcessors, this.audioMixerFactory, TransformerInternal.this.encoderFactory, TransformerInternal.this.muxerWrapper, this.fallbackListener));
                return;
            }
            if (MimeTypes.isVideo(format3.sampleMimeType)) {
                if (this.transformationRequest.hdrMode == 1) {
                    z = true;
                }
                format2 = assetLoaderInputFormat.buildUpon().setColorInfo(TransformerUtil.getDecoderOutputColor(TransformerUtil.getValidColor(assetLoaderInputFormat.colorInfo), z)).build();
            } else if (MimeTypes.isImage(format3.sampleMimeType)) {
                format2 = format.buildUpon().setColorInfo(TransformerUtil.getValidColor(format3.colorInfo)).build();
            } else {
                throw ExportException.createForUnexpected(new IllegalArgumentException("assetLoaderOutputFormat has to have a audio, video or image mimetype."));
            }
            Format format4 = format2;
            AssetLoaderInputTracker access$100 = TransformerInternal.this.assetLoaderInputTracker;
            Context access$700 = TransformerInternal.this.context;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            VideoCompositorSettings videoCompositorSettings = this.composition.videoCompositorSettings;
            ImmutableList<Effect> immutableList = this.composition.effects.videoEffects;
            VideoFrameProcessor.Factory factory = this.videoFrameProcessorFactory;
            CapturingEncoderFactory access$600 = TransformerInternal.this.encoderFactory;
            MuxerWrapper access$200 = TransformerInternal.this.muxerWrapper;
            TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1 transformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1 = r12;
            TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1 transformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda12 = new TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1(this);
            VideoSampleExporter videoSampleExporter = r2;
            VideoSampleExporter videoSampleExporter2 = new VideoSampleExporter(access$700, format4, transformationRequest2, videoCompositorSettings, immutableList, factory, access$600, access$200, transformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1, this.fallbackListener, this.debugViewProvider, TransformerInternal.this.videoSampleTimestampOffsetUs, TransformerInternal.this.assetLoaderInputTracker.hasMultipleConcurrentVideoTracks(), TransformerInternal.this.portraitEncodingEnabled, TransformerInternal.this.maxFramesInEncoder);
            access$100.registerSampleExporter(2, videoSampleExporter);
        }

        private void createEncodedSampleExporter(int i) {
            boolean z = false;
            Assertions.checkState(TransformerInternal.this.assetLoaderInputTracker.getSampleExporter(i) == null);
            if (i != 1 || !((EditedMediaItemSequence) this.composition.sequences.get(this.sequenceIndex)).hasGaps()) {
                z = true;
            }
            Assertions.checkArgument(z, "Gaps can not be transmuxed.");
            TransformerInternal.this.assetLoaderInputTracker.registerSampleExporter(i, new EncodedSampleExporter(TransformerInternal.this.assetLoaderInputTracker.getAssetLoaderInputFormat(this.sequenceIndex, i), this.transformationRequest, TransformerInternal.this.muxerWrapper, this.fallbackListener, TransformerInternal.this.videoSampleTimestampOffsetUs));
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
            if (((androidx.media3.transformer.EditedMediaItemSequence) r4.composition.sequences.get(r4.sequenceIndex)).isLooping == false) goto L_0x0035;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
            r0 = true;
            r1 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
            if (r6 == androidx.media3.common.C.TIME_UNSET) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
            androidx.media3.common.util.Assertions.checkState(r5, "MediaItem duration required for sequence looping could not be extracted.");
            r4.currentSequenceDurationUs += r6;
            r5 = androidx.media3.transformer.TransformerInternal.access$1200(r4.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
            monitor-enter(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
            if (r8 == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            androidx.media3.transformer.TransformerInternal.access$1310(r4.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
            if (androidx.media3.transformer.TransformerInternal.access$1300(r4.this$0) != 0) goto L_0x0064;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0064, code lost:
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
            if (r4.currentSequenceDurationUs > androidx.media3.transformer.TransformerInternal.access$1400(r4.this$0)) goto L_0x0073;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
            if (r0 == false) goto L_0x00a6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
            r6 = r4.this$0;
            androidx.media3.transformer.TransformerInternal.access$1402(r6, java.lang.Math.max(r4.currentSequenceDurationUs, androidx.media3.transformer.TransformerInternal.access$1400(r6)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x008c, code lost:
            if (r1 >= androidx.media3.transformer.TransformerInternal.access$300(r4.this$0).size()) goto L_0x00a6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x008e, code lost:
            ((androidx.media3.transformer.SequenceAssetLoader) androidx.media3.transformer.TransformerInternal.access$300(r4.this$0).get(r1)).setMaxSequenceDurationUs(androidx.media3.transformer.TransformerInternal.access$1400(r4.this$0), r0);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a6, code lost:
            monitor-exit(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a7, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void onMediaItemChanged(int r5, long r6, boolean r8) {
            /*
                r4 = this;
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                boolean r0 = r0.compositionHasLoopingSequence
                if (r0 != 0) goto L_0x0009
                return
            L_0x0009:
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r0 = r0.assetLoaderLock
                monitor-enter(r0)
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00ab }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r1 = r1.assetLoaderInputTracker     // Catch:{ all -> 0x00ab }
                int r2 = r4.sequenceIndex     // Catch:{ all -> 0x00ab }
                boolean r1 = r1.sequenceHasMultipleTracks(r2)     // Catch:{ all -> 0x00ab }
                if (r1 == 0) goto L_0x0023
                r1 = 2
                if (r5 != r1) goto L_0x0023
                monitor-exit(r0)     // Catch:{ all -> 0x00ab }
                return
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x00ab }
                androidx.media3.transformer.Composition r5 = r4.composition
                com.google.common.collect.ImmutableList<androidx.media3.transformer.EditedMediaItemSequence> r5 = r5.sequences
                int r0 = r4.sequenceIndex
                java.lang.Object r5 = r5.get(r0)
                androidx.media3.transformer.EditedMediaItemSequence r5 = (androidx.media3.transformer.EditedMediaItemSequence) r5
                boolean r5 = r5.isLooping
                if (r5 == 0) goto L_0x0035
                return
            L_0x0035:
                r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                r0 = 1
                r1 = 0
                if (r5 == 0) goto L_0x0042
                r5 = r0
                goto L_0x0043
            L_0x0042:
                r5 = r1
            L_0x0043:
                java.lang.String r2 = "MediaItem duration required for sequence looping could not be extracted."
                androidx.media3.common.util.Assertions.checkState(r5, r2)
                long r2 = r4.currentSequenceDurationUs
                long r2 = r2 + r6
                r4.currentSequenceDurationUs = r2
                androidx.media3.transformer.TransformerInternal r5 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r5 = r5.setMaxSequenceDurationUsLock
                monitor-enter(r5)
                if (r8 == 0) goto L_0x005b
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                androidx.media3.transformer.TransformerInternal.access$1310(r6)     // Catch:{ all -> 0x00a8 }
            L_0x005b:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                int r6 = r6.nonLoopingSequencesWithNonFinalDuration     // Catch:{ all -> 0x00a8 }
                if (r6 != 0) goto L_0x0064
                goto L_0x0065
            L_0x0064:
                r0 = r1
            L_0x0065:
                long r6 = r4.currentSequenceDurationUs     // Catch:{ all -> 0x00a8 }
                androidx.media3.transformer.TransformerInternal r8 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                long r2 = r8.currentMaxSequenceDurationUs     // Catch:{ all -> 0x00a8 }
                int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r6 > 0) goto L_0x0073
                if (r0 == 0) goto L_0x00a6
            L_0x0073:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                long r7 = r4.currentSequenceDurationUs     // Catch:{ all -> 0x00a8 }
                long r2 = r6.currentMaxSequenceDurationUs     // Catch:{ all -> 0x00a8 }
                long r7 = java.lang.Math.max(r7, r2)     // Catch:{ all -> 0x00a8 }
                long unused = r6.currentMaxSequenceDurationUs = r7     // Catch:{ all -> 0x00a8 }
            L_0x0082:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                java.util.List r6 = r6.sequenceAssetLoaders     // Catch:{ all -> 0x00a8 }
                int r6 = r6.size()     // Catch:{ all -> 0x00a8 }
                if (r1 >= r6) goto L_0x00a6
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                java.util.List r6 = r6.sequenceAssetLoaders     // Catch:{ all -> 0x00a8 }
                java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x00a8 }
                androidx.media3.transformer.SequenceAssetLoader r6 = (androidx.media3.transformer.SequenceAssetLoader) r6     // Catch:{ all -> 0x00a8 }
                androidx.media3.transformer.TransformerInternal r7 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x00a8 }
                long r7 = r7.currentMaxSequenceDurationUs     // Catch:{ all -> 0x00a8 }
                r6.setMaxSequenceDurationUs(r7, r0)     // Catch:{ all -> 0x00a8 }
                int r1 = r1 + 1
                goto L_0x0082
            L_0x00a6:
                monitor-exit(r5)     // Catch:{ all -> 0x00a8 }
                return
            L_0x00a8:
                r6 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x00a8 }
                throw r6
            L_0x00ab:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00ab }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.SequenceAssetLoaderListener.onMediaItemChanged(int, long, boolean):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
            if (androidx.media3.transformer.TransformerInternal.access$1500(r10.firstEditedMediaItem.mediaItem) == false) goto L_0x0065;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean shouldTranscode(androidx.media3.common.Format r11, int r12) {
            /*
                r10 = this;
                r0 = r12 & 2
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L_0x0008
                r0 = r2
                goto L_0x0009
            L_0x0008:
                r0 = r1
            L_0x0009:
                r12 = r12 & r2
                if (r12 == 0) goto L_0x000e
                r12 = r2
                goto L_0x000f
            L_0x000e:
                r12 = r1
            L_0x000f:
                if (r0 != 0) goto L_0x0016
                if (r12 == 0) goto L_0x0014
                goto L_0x0016
            L_0x0014:
                r3 = r1
                goto L_0x0017
            L_0x0016:
                r3 = r2
            L_0x0017:
                androidx.media3.common.util.Assertions.checkArgument(r3)
                java.lang.String r3 = r11.sampleMimeType
                int r3 = androidx.media3.transformer.TransformerUtil.getProcessedTrackType(r3)
                if (r12 != 0) goto L_0x0024
            L_0x0022:
                r11 = r2
                goto L_0x0066
            L_0x0024:
                if (r3 != r2) goto L_0x003e
                androidx.media3.transformer.Composition r5 = r10.composition
                int r6 = r10.sequenceIndex
                androidx.media3.transformer.TransformationRequest r7 = r10.transformationRequest
                androidx.media3.transformer.TransformerInternal r12 = androidx.media3.transformer.TransformerInternal.this
                androidx.media3.transformer.CapturingEncoderFactory r8 = r12.encoderFactory
                androidx.media3.transformer.TransformerInternal r12 = androidx.media3.transformer.TransformerInternal.this
                androidx.media3.transformer.MuxerWrapper r9 = r12.muxerWrapper
                r4 = r11
                boolean r11 = androidx.media3.transformer.TransformerUtil.shouldTranscodeAudio(r4, r5, r6, r7, r8, r9)
                goto L_0x0066
            L_0x003e:
                r12 = 2
                if (r3 != r12) goto L_0x0065
                androidx.media3.transformer.Composition r5 = r10.composition
                int r6 = r10.sequenceIndex
                androidx.media3.transformer.TransformationRequest r7 = r10.transformationRequest
                androidx.media3.transformer.TransformerInternal r12 = androidx.media3.transformer.TransformerInternal.this
                androidx.media3.transformer.CapturingEncoderFactory r8 = r12.encoderFactory
                androidx.media3.transformer.TransformerInternal r12 = androidx.media3.transformer.TransformerInternal.this
                androidx.media3.transformer.MuxerWrapper r9 = r12.muxerWrapper
                r4 = r11
                boolean r11 = androidx.media3.transformer.TransformerUtil.shouldTranscodeVideo(r4, r5, r6, r7, r8, r9)
                if (r11 != 0) goto L_0x0022
                androidx.media3.transformer.EditedMediaItem r11 = r10.firstEditedMediaItem
                androidx.media3.common.MediaItem r11 = r11.mediaItem
                boolean r11 = androidx.media3.transformer.TransformerInternal.clippingRequiresTranscode(r11)
                if (r11 == 0) goto L_0x0065
                goto L_0x0022
            L_0x0065:
                r11 = r1
            L_0x0066:
                if (r11 == 0) goto L_0x006a
                if (r0 == 0) goto L_0x006b
            L_0x006a:
                r1 = r2
            L_0x006b:
                androidx.media3.common.util.Assertions.checkState(r1)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.SequenceAssetLoaderListener.shouldTranscode(androidx.media3.common.Format, int):boolean");
        }
    }

    /* access modifiers changed from: private */
    public static boolean clippingRequiresTranscode(MediaItem mediaItem) {
        return mediaItem.clippingConfiguration.startPositionMs > 0 && !mediaItem.clippingConfiguration.startsAtKeyFrame;
    }

    private static final class AssetLoaderInputTracker {
        private final List<SequenceMetadata> sequencesMetadata = new ArrayList();
        private final SparseArray<Integer> trackTypeToNumberOfRegisteredGraphInput;
        private final SparseArray<SampleExporter> trackTypeToSampleExporter;
        private final SparseArray<Boolean> trackTypeToShouldTranscode;

        private static final class SequenceMetadata {
            public int requiredTrackCount = -1;
            public final SparseArray<Format> trackTypeToFirstAssetLoaderInputFormat = new SparseArray<>();
        }

        public AssetLoaderInputTracker(Composition composition) {
            for (int i = 0; i < composition.sequences.size(); i++) {
                this.sequencesMetadata.add(new SequenceMetadata());
            }
            this.trackTypeToSampleExporter = new SparseArray<>();
            this.trackTypeToShouldTranscode = new SparseArray<>();
            this.trackTypeToNumberOfRegisteredGraphInput = new SparseArray<>();
        }

        public Format getAssetLoaderInputFormat(int i, int i2) {
            SparseArray<Format> sparseArray = this.sequencesMetadata.get(i).trackTypeToFirstAssetLoaderInputFormat;
            Assertions.checkState(Util.contains(sparseArray, i2));
            return sparseArray.get(i2);
        }

        public boolean sequenceHasMultipleTracks(int i) {
            return this.sequencesMetadata.get(i).trackTypeToFirstAssetLoaderInputFormat.size() > 1;
        }

        public void setTrackCount(int i, int i2) {
            this.sequencesMetadata.get(i).requiredTrackCount = i2;
        }

        public boolean hasAllTrackCounts() {
            for (int i = 0; i < this.sequencesMetadata.size(); i++) {
                if (this.sequencesMetadata.get(i).requiredTrackCount == -1) {
                    return false;
                }
            }
            return true;
        }

        public void registerTrack(int i, Format format) {
            int processedTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
            SparseArray<Format> sparseArray = this.sequencesMetadata.get(i).trackTypeToFirstAssetLoaderInputFormat;
            Assertions.checkState(!Util.contains(sparseArray, processedTrackType));
            sparseArray.put(processedTrackType, format);
        }

        public int getIndexForPrimarySequence(int i) {
            Assertions.checkState(hasRegisteredAllTracks(), "Primary track can only be queried after all tracks are added.");
            for (int i2 = 0; i2 < this.sequencesMetadata.size(); i2++) {
                if (Util.contains(this.sequencesMetadata.get(i2).trackTypeToFirstAssetLoaderInputFormat, i)) {
                    return i2;
                }
            }
            return -1;
        }

        public boolean hasRegisteredAllTracks() {
            if (!hasAllTrackCounts()) {
                return false;
            }
            for (int i = 0; i < this.sequencesMetadata.size(); i++) {
                SequenceMetadata sequenceMetadata = this.sequencesMetadata.get(i);
                if (sequenceMetadata.requiredTrackCount != sequenceMetadata.trackTypeToFirstAssetLoaderInputFormat.size()) {
                    return false;
                }
            }
            return true;
        }

        public void registerGraphInput(int i) {
            int i2 = 1;
            if (Util.contains(this.trackTypeToNumberOfRegisteredGraphInput, i)) {
                i2 = 1 + this.trackTypeToNumberOfRegisteredGraphInput.get(i).intValue();
            }
            this.trackTypeToNumberOfRegisteredGraphInput.put(i, Integer.valueOf(i2));
        }

        public boolean hasAssociatedAllTracksWithGraphInput(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.sequencesMetadata.size(); i3++) {
                if (Util.contains(this.sequencesMetadata.get(i3).trackTypeToFirstAssetLoaderInputFormat, i)) {
                    i2++;
                }
            }
            if (this.trackTypeToNumberOfRegisteredGraphInput.get(i).intValue() == i2) {
                return true;
            }
            return false;
        }

        public int getOutputTrackCount() {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.sequencesMetadata.size(); i3++) {
                SparseArray<Format> sparseArray = this.sequencesMetadata.get(i3).trackTypeToFirstAssetLoaderInputFormat;
                if (Util.contains(sparseArray, 1)) {
                    i = 1;
                }
                if (Util.contains(sparseArray, 2)) {
                    i2 = 1;
                }
            }
            return i + i2;
        }

        public boolean hasMultipleConcurrentVideoTracks() {
            if (this.sequencesMetadata.size() < 2) {
                return false;
            }
            int i = 0;
            for (int i2 = 0; i2 < this.sequencesMetadata.size(); i2++) {
                if (Util.contains(this.sequencesMetadata.get(i2).trackTypeToFirstAssetLoaderInputFormat, 2)) {
                    i++;
                }
            }
            if (i > 1) {
                return true;
            }
            return false;
        }

        public void registerSampleExporter(int i, SampleExporter sampleExporter) {
            Assertions.checkState(!Util.contains(this.trackTypeToSampleExporter, i), "Exactly one SampleExporter can be added for each track type.");
            this.trackTypeToSampleExporter.put(i, sampleExporter);
        }

        public void setShouldTranscode(int i, boolean z) {
            if (Util.contains(this.trackTypeToShouldTranscode, i)) {
                Assertions.checkState(z == this.trackTypeToShouldTranscode.get(i).booleanValue());
            } else {
                this.trackTypeToShouldTranscode.put(i, Boolean.valueOf(z));
            }
        }

        public boolean shouldTranscode(int i) {
            Assertions.checkState(Util.contains(this.trackTypeToShouldTranscode, i));
            return this.trackTypeToShouldTranscode.get(i).booleanValue();
        }

        public SampleExporter getSampleExporter(int i) {
            return this.trackTypeToSampleExporter.get(i);
        }
    }
}
