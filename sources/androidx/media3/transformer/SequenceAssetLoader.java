package androidx.media3.transformer;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.ExportResult;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

final class SequenceAssetLoader implements AssetLoader, AssetLoader.Listener {
    private static final Format FORCE_AUDIO_TRACK_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_AAC).setSampleRate(44100).setChannelCount(2).build();
    /* access modifiers changed from: private */
    public final AssetLoader.Factory assetLoaderFactory;
    /* access modifiers changed from: private */
    public final AssetLoader.CompositionSettings compositionSettings;
    /* access modifiers changed from: private */
    public volatile long currentAssetDurationAfterEffectsAppliedUs;
    private volatile long currentAssetDurationUs;
    /* access modifiers changed from: private */
    public AssetLoader currentAssetLoader;
    /* access modifiers changed from: private */
    public int currentMediaItemIndex;
    /* access modifiers changed from: private */
    public boolean decodeAudio;
    private boolean decodeVideo;
    /* access modifiers changed from: private */
    public final List<EditedMediaItem> editedMediaItems;
    private final boolean forceAudioTrack;
    /* access modifiers changed from: private */
    public final HandlerWrapper handler;
    /* access modifiers changed from: private */
    public boolean isCurrentAssetFirstAsset = true;
    /* access modifiers changed from: private */
    public final boolean isLooping;
    /* access modifiers changed from: private */
    public volatile boolean isMaxSequenceDurationUsFinal;
    private boolean isTrackCountReported;
    /* access modifiers changed from: private */
    public volatile long maxSequenceDurationUs;
    private final Map<Integer, OnMediaItemChangedListener> mediaItemChangedListenersByTrackType = new HashMap();
    /* access modifiers changed from: private */
    public final AtomicInteger nonEndedTrackCount = new AtomicInteger();
    private final ImmutableList.Builder<ExportResult.ProcessedInput> processedInputsBuilder = new ImmutableList.Builder<>();
    private int processedInputsSize;
    /* access modifiers changed from: private */
    public volatile boolean released;
    private final AtomicInteger reportedTrackCount = new AtomicInteger();
    private final Map<Integer, SampleConsumerWrapper> sampleConsumersByTrackType = new HashMap();
    private final AssetLoader.Listener sequenceAssetLoaderListener;
    private int sequenceLoopCount;

    static /* synthetic */ int access$1308(SequenceAssetLoader sequenceAssetLoader) {
        int i = sequenceAssetLoader.sequenceLoopCount;
        sequenceAssetLoader.sequenceLoopCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(SequenceAssetLoader sequenceAssetLoader) {
        int i = sequenceAssetLoader.currentMediaItemIndex;
        sequenceAssetLoader.currentMediaItemIndex = i + 1;
        return i;
    }

    public SequenceAssetLoader(EditedMediaItemSequence editedMediaItemSequence, boolean z, AssetLoader.Factory factory, AssetLoader.CompositionSettings compositionSettings2, AssetLoader.Listener listener, Clock clock, Looper looper) {
        ImmutableList<EditedMediaItem> immutableList = editedMediaItemSequence.editedMediaItems;
        this.editedMediaItems = immutableList;
        this.isLooping = editedMediaItemSequence.isLooping;
        this.forceAudioTrack = z;
        GapInterceptingAssetLoaderFactory gapInterceptingAssetLoaderFactory = new GapInterceptingAssetLoaderFactory(factory);
        this.assetLoaderFactory = gapInterceptingAssetLoaderFactory;
        this.compositionSettings = compositionSettings2;
        this.sequenceAssetLoaderListener = listener;
        this.handler = clock.createHandler(looper, (Handler.Callback) null);
        this.currentAssetLoader = gapInterceptingAssetLoaderFactory.createAssetLoader(immutableList.get(0), looper, this, compositionSettings2);
    }

    public void start() {
        this.currentAssetLoader.start();
        if (this.editedMediaItems.size() > 1 || this.isLooping) {
            this.sequenceAssetLoaderListener.onDurationUs(C.TIME_UNSET);
        }
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.isLooping) {
            return 3;
        }
        int progress = this.currentAssetLoader.getProgress(progressHolder);
        int size = this.editedMediaItems.size();
        if (size == 1 || progress == 0) {
            return progress;
        }
        int i = (this.currentMediaItemIndex * 100) / size;
        if (progress == 2) {
            i += progressHolder.progress / size;
        }
        progressHolder.progress = i;
        return 2;
    }

    public ImmutableMap<Integer, String> getDecoderNames() {
        return this.currentAssetLoader.getDecoderNames();
    }

    public ImmutableList<ExportResult.ProcessedInput> getProcessedInputs() {
        addCurrentProcessedInput();
        return this.processedInputsBuilder.build();
    }

    public void release() {
        this.currentAssetLoader.release();
        this.released = true;
    }

    /* access modifiers changed from: private */
    public void addCurrentProcessedInput() {
        int size = this.sequenceLoopCount * this.editedMediaItems.size();
        int i = this.currentMediaItemIndex;
        if (size + i >= this.processedInputsSize) {
            MediaItem mediaItem = this.editedMediaItems.get(i).mediaItem;
            ImmutableMap<Integer, String> decoderNames = getDecoderNames();
            this.processedInputsBuilder.add((Object) new ExportResult.ProcessedInput(mediaItem, decoderNames.get(1), decoderNames.get(2)));
            this.processedInputsSize++;
        }
    }

    public void addOnMediaItemChangedListener(OnMediaItemChangedListener onMediaItemChangedListener, int i) {
        boolean z = false;
        Assertions.checkArgument(i == 1 || i == 2);
        if (this.mediaItemChangedListenersByTrackType.get(Integer.valueOf(i)) == null) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.mediaItemChangedListenersByTrackType.put(Integer.valueOf(i), onMediaItemChangedListener);
    }

    public boolean onTrackAdded(Format format, int i) {
        boolean z = false;
        boolean z2 = TransformerUtil.getProcessedTrackType(format.sampleMimeType) == 1;
        Object[] objArr = new Object[2];
        objArr[0] = z2 ? MimeTypes.BASE_TYPE_AUDIO : MimeTypes.BASE_TYPE_VIDEO;
        objArr[1] = format;
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_ASSET_LOADER, DebugTraceUtil.EVENT_INPUT_FORMAT, C.TIME_UNSET, "%s:%s", objArr);
        if (!this.isCurrentAssetFirstAsset) {
            boolean z3 = z2 ? this.decodeAudio : this.decodeVideo;
            if (z3) {
                if ((i & 2) != 0) {
                    z = true;
                }
                Assertions.checkArgument(z);
            } else {
                if ((i & 1) != 0) {
                    z = true;
                }
                Assertions.checkArgument(z);
            }
            return z3;
        }
        if (this.forceAudioTrack && this.reportedTrackCount.get() == 1 && !z2) {
            z = true;
        }
        if (!this.isTrackCountReported) {
            this.sequenceAssetLoaderListener.onTrackCount(this.reportedTrackCount.get() + (z ? 1 : 0));
            this.isTrackCountReported = true;
        }
        boolean onTrackAdded = this.sequenceAssetLoaderListener.onTrackAdded(format, i);
        if (z2) {
            this.decodeAudio = onTrackAdded;
        } else {
            this.decodeVideo = onTrackAdded;
        }
        if (z) {
            this.sequenceAssetLoaderListener.onTrackAdded(FORCE_AUDIO_TRACK_FORMAT, 2);
            this.decodeAudio = true;
        }
        return onTrackAdded;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper onOutputFormat(androidx.media3.common.Format r10) throws androidx.media3.transformer.ExportException {
        /*
            r9 = this;
            java.lang.String r0 = r10.sampleMimeType
            int r0 = androidx.media3.transformer.TransformerUtil.getProcessedTrackType(r0)
            java.lang.String r1 = "AssetLoader"
            java.lang.String r2 = "OutputFormat"
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.lang.String r5 = "%s:%s"
            java.lang.String r6 = androidx.media3.common.util.Util.getTrackTypeString(r0)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r10}
            androidx.media3.effect.DebugTraceUtil.logEvent(r1, r2, r3, r5, r6)
            boolean r1 = r9.isCurrentAssetFirstAsset
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0077
            androidx.media3.transformer.AssetLoader$Listener r1 = r9.sequenceAssetLoaderListener
            androidx.media3.transformer.SampleConsumer r1 = r1.onOutputFormat(r10)
            if (r1 != 0) goto L_0x002c
            return r2
        L_0x002c:
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r5 = new androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper
            r5.<init>(r1, r0)
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r1 = r9.sampleConsumersByTrackType
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r1.put(r6, r5)
            boolean r1 = r9.forceAudioTrack
            if (r1 == 0) goto L_0x00b3
            java.util.concurrent.atomic.AtomicInteger r1 = r9.reportedTrackCount
            int r1 = r1.get()
            if (r1 != r4) goto L_0x00b3
            if (r0 != r3) goto L_0x00b3
            androidx.media3.transformer.AssetLoader$Listener r1 = r9.sequenceAssetLoaderListener
            androidx.media3.common.Format r6 = FORCE_AUDIO_TRACK_FORMAT
            androidx.media3.common.Format$Builder r6 = r6.buildUpon()
            java.lang.String r7 = "audio/raw"
            androidx.media3.common.Format$Builder r6 = r6.setSampleMimeType(r7)
            androidx.media3.common.Format$Builder r6 = r6.setPcmEncoding(r3)
            androidx.media3.common.Format r6 = r6.build()
            androidx.media3.transformer.SampleConsumer r1 = r1.onOutputFormat(r6)
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            androidx.media3.transformer.SampleConsumer r1 = (androidx.media3.transformer.SampleConsumer) r1
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r6 = r9.sampleConsumersByTrackType
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r8 = new androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper
            r8.<init>(r1, r4)
            r6.put(r7, r8)
            goto L_0x00b3
        L_0x0077:
            java.util.concurrent.atomic.AtomicInteger r1 = r9.reportedTrackCount
            int r1 = r1.get()
            if (r1 != r4) goto L_0x008b
            if (r0 != r4) goto L_0x008b
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r1 = r9.sampleConsumersByTrackType
            int r1 = r1.size()
            if (r1 != r3) goto L_0x008b
            r1 = r4
            goto L_0x008c
        L_0x008b:
            r1 = 0
        L_0x008c:
            r1 = r1 ^ r4
            java.lang.String r5 = "Inputs with no video track are not supported when the output contains a video track"
            androidx.media3.common.util.Assertions.checkState(r1, r5)
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r1 = r9.sampleConsumersByTrackType
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            java.lang.Object r1 = r1.get(r5)
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r1 = (androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper) r1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r6 = "The preceding MediaItem does not contain any track of type %d. If the Composition contains a sequence that starts with items without audio tracks (like images), followed by items with audio tracks, Composition.Builder.experimentalSetForceAudioTrack() needs to be set to true."
            java.lang.String r5 = androidx.media3.common.util.Util.formatInvariant(r6, r5)
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r1, r5)
            r5 = r1
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r5 = (androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper) r5
        L_0x00b3:
            r9.onMediaItemChanged(r0, r10)
            java.util.concurrent.atomic.AtomicInteger r10 = r9.reportedTrackCount
            int r10 = r10.get()
            if (r10 != r4) goto L_0x00ec
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r10 = r9.sampleConsumersByTrackType
            int r10 = r10.size()
            if (r10 != r3) goto L_0x00ec
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r10 = r9.sampleConsumersByTrackType
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x00d0:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L_0x00ec
            java.lang.Object r1 = r10.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getKey()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r0 == r1) goto L_0x00d0
            r9.onMediaItemChanged(r1, r2)
            goto L_0x00d0
        L_0x00ec:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.SequenceAssetLoader.onOutputFormat(androidx.media3.common.Format):androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper");
    }

    private void onMediaItemChanged(int i, Format format) {
        long j;
        OnMediaItemChangedListener onMediaItemChangedListener = this.mediaItemChangedListenersByTrackType.get(Integer.valueOf(i));
        if (onMediaItemChangedListener != null) {
            EditedMediaItem editedMediaItem = this.editedMediaItems.get(this.currentMediaItemIndex);
            if (i != 1 || !this.isLooping || !this.decodeAudio) {
                j = this.currentAssetDurationUs;
            } else {
                j = C.TIME_UNSET;
            }
            if (editedMediaItem.isGap()) {
                format = null;
            }
            onMediaItemChangedListener.onMediaItemChanged(editedMediaItem, j, format, this.currentMediaItemIndex == this.editedMediaItems.size() - 1);
        }
    }

    public void setMaxSequenceDurationUs(long j, boolean z) {
        this.maxSequenceDurationUs = j;
        this.isMaxSequenceDurationUsFinal = z;
    }

    public void onDurationUs(long j) {
        Assertions.checkArgument(j != C.TIME_UNSET || this.currentMediaItemIndex == this.editedMediaItems.size() - 1, "Could not retrieve required duration for EditedMediaItem " + this.currentMediaItemIndex);
        this.currentAssetDurationAfterEffectsAppliedUs = this.editedMediaItems.get(this.currentMediaItemIndex).getDurationAfterEffectsApplied(j);
        this.currentAssetDurationUs = j;
        if (this.editedMediaItems.size() == 1 && !this.isLooping) {
            this.sequenceAssetLoaderListener.onDurationUs(this.currentAssetDurationAfterEffectsAppliedUs);
        }
    }

    public void onTrackCount(int i) {
        this.reportedTrackCount.set(i);
        this.nonEndedTrackCount.set(i);
    }

    public void onError(ExportException exportException) {
        this.sequenceAssetLoaderListener.onError(exportException);
    }

    private final class SampleConsumerWrapper implements SampleConsumer {
        private boolean audioLoopingEnded;
        private final SampleConsumer sampleConsumer;
        private long totalDurationUs;
        private final int trackType;
        private boolean videoLoopingEnded;

        public SampleConsumerWrapper(SampleConsumer sampleConsumer2, int i) {
            this.sampleConsumer = sampleConsumer2;
            this.trackType = i;
        }

        public DecoderInputBuffer getInputBuffer() {
            return this.sampleConsumer.getInputBuffer();
        }

        public boolean queueInputBuffer() {
            DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) Assertions.checkStateNotNull(this.sampleConsumer.getInputBuffer());
            long j = this.totalDurationUs + decoderInputBuffer.timeUs;
            if (!SequenceAssetLoader.this.isLooping || (j < SequenceAssetLoader.this.maxSequenceDurationUs && !this.audioLoopingEnded)) {
                if (decoderInputBuffer.isEndOfStream()) {
                    SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
                    if (SequenceAssetLoader.this.currentMediaItemIndex < SequenceAssetLoader.this.editedMediaItems.size() - 1 || SequenceAssetLoader.this.isLooping) {
                        if (this.trackType != 1 || SequenceAssetLoader.this.isLooping || !SequenceAssetLoader.this.decodeAudio) {
                            decoderInputBuffer.clear();
                            decoderInputBuffer.timeUs = 0;
                        } else {
                            Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                        }
                        if (SequenceAssetLoader.this.nonEndedTrackCount.get() == 0) {
                            switchAssetLoader();
                        }
                        return true;
                    }
                }
                Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                return true;
            }
            if (SequenceAssetLoader.this.isMaxSequenceDurationUsFinal && !this.audioLoopingEnded) {
                ((ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data)).limit(0);
                decoderInputBuffer.setFlags(4);
                Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                this.audioLoopingEnded = true;
                SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
            }
            return false;
        }

        public int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
            if (SequenceAssetLoader.this.isLooping) {
                long j = -9223372036854775807L;
                while (true) {
                    if (!timestampIterator.hasNext()) {
                        break;
                    }
                    long next = timestampIterator.next();
                    if (this.totalDurationUs + next <= SequenceAssetLoader.this.maxSequenceDurationUs) {
                        j = next;
                    } else if (!SequenceAssetLoader.this.isMaxSequenceDurationUsFinal) {
                        return 2;
                    } else {
                        if (j != C.TIME_UNSET) {
                            ClippingIterator clippingIterator = new ClippingIterator(timestampIterator.copyOf(), j);
                            this.videoLoopingEnded = true;
                            timestampIterator = clippingIterator;
                        } else if (this.videoLoopingEnded) {
                            return 2;
                        } else {
                            this.videoLoopingEnded = true;
                            signalEndOfVideoInput();
                            return 3;
                        }
                    }
                }
            }
            return this.sampleConsumer.queueInputBitmap(bitmap, timestampIterator.copyOf());
        }

        public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
            this.sampleConsumer.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
        }

        public void setOnInputSurfaceReadyListener(Runnable runnable) {
            this.sampleConsumer.setOnInputSurfaceReadyListener(runnable);
        }

        public int queueInputTexture(int i, long j) {
            long j2 = this.totalDurationUs + j;
            if (!SequenceAssetLoader.this.isLooping || j2 < SequenceAssetLoader.this.maxSequenceDurationUs) {
                return this.sampleConsumer.queueInputTexture(i, j);
            }
            if (!SequenceAssetLoader.this.isMaxSequenceDurationUsFinal || this.videoLoopingEnded) {
                return 2;
            }
            this.videoLoopingEnded = true;
            signalEndOfVideoInput();
            return 3;
        }

        public Surface getInputSurface() {
            return this.sampleConsumer.getInputSurface();
        }

        public int getPendingVideoFrameCount() {
            return this.sampleConsumer.getPendingVideoFrameCount();
        }

        public boolean registerVideoFrame(long j) {
            long j2 = this.totalDurationUs + j;
            if (!SequenceAssetLoader.this.isLooping || j2 < SequenceAssetLoader.this.maxSequenceDurationUs) {
                return this.sampleConsumer.registerVideoFrame(j);
            }
            if (!SequenceAssetLoader.this.isMaxSequenceDurationUsFinal || this.videoLoopingEnded) {
                return false;
            }
            this.videoLoopingEnded = true;
            signalEndOfVideoInput();
            return false;
        }

        public void signalEndOfVideoInput() {
            SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
            if (SequenceAssetLoader.this.isLooping ? this.videoLoopingEnded : SequenceAssetLoader.this.currentMediaItemIndex == SequenceAssetLoader.this.editedMediaItems.size() - 1) {
                this.sampleConsumer.signalEndOfVideoInput();
            } else if (SequenceAssetLoader.this.nonEndedTrackCount.get() == 0) {
                switchAssetLoader();
            }
        }

        /* access modifiers changed from: private */
        public void onGapSignalled() {
            SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
            if (SequenceAssetLoader.this.currentMediaItemIndex < SequenceAssetLoader.this.editedMediaItems.size() - 1) {
                switchAssetLoader();
            }
        }

        private void switchAssetLoader() {
            SequenceAssetLoader.this.handler.post(new SequenceAssetLoader$SampleConsumerWrapper$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$switchAssetLoader$0$androidx-media3-transformer-SequenceAssetLoader$SampleConsumerWrapper  reason: not valid java name */
        public /* synthetic */ void m1129lambda$switchAssetLoader$0$androidxmedia3transformerSequenceAssetLoader$SampleConsumerWrapper() {
            try {
                if (!SequenceAssetLoader.this.released) {
                    SequenceAssetLoader.this.addCurrentProcessedInput();
                    this.totalDurationUs += SequenceAssetLoader.this.currentAssetDurationAfterEffectsAppliedUs;
                    SequenceAssetLoader.this.currentAssetLoader.release();
                    boolean unused = SequenceAssetLoader.this.isCurrentAssetFirstAsset = false;
                    SequenceAssetLoader.access$408(SequenceAssetLoader.this);
                    if (SequenceAssetLoader.this.currentMediaItemIndex == SequenceAssetLoader.this.editedMediaItems.size()) {
                        int unused2 = SequenceAssetLoader.this.currentMediaItemIndex = 0;
                        SequenceAssetLoader.access$1308(SequenceAssetLoader.this);
                    }
                    SequenceAssetLoader sequenceAssetLoader = SequenceAssetLoader.this;
                    AssetLoader.Factory access$1500 = sequenceAssetLoader.assetLoaderFactory;
                    SequenceAssetLoader sequenceAssetLoader2 = SequenceAssetLoader.this;
                    AssetLoader unused3 = sequenceAssetLoader.currentAssetLoader = access$1500.createAssetLoader((EditedMediaItem) SequenceAssetLoader.this.editedMediaItems.get(SequenceAssetLoader.this.currentMediaItemIndex), (Looper) Assertions.checkNotNull(Looper.myLooper()), sequenceAssetLoader2, sequenceAssetLoader2.compositionSettings);
                    SequenceAssetLoader.this.currentAssetLoader.start();
                }
            } catch (RuntimeException e) {
                SequenceAssetLoader.this.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }
    }

    private static final class ClippingIterator implements TimestampIterator {
        private final long clippingValue;
        private boolean hasReachedClippingValue;
        private final TimestampIterator iterator;

        public ClippingIterator(TimestampIterator timestampIterator, long j) {
            this.iterator = timestampIterator;
            this.clippingValue = j;
        }

        public boolean hasNext() {
            return !this.hasReachedClippingValue && this.iterator.hasNext();
        }

        public long next() {
            Assertions.checkState(hasNext());
            long next = this.iterator.next();
            if (this.clippingValue <= next) {
                this.hasReachedClippingValue = true;
            }
            return next;
        }

        public TimestampIterator copyOf() {
            return new ClippingIterator(this.iterator.copyOf(), this.clippingValue);
        }
    }

    private final class GapSignalingAssetLoader implements AssetLoader {
        private static final int OUTPUT_FORMAT_RETRY_DELAY_MS = 10;
        private final Format decodedFormat;
        private final long durationUs;
        private boolean outputtedFormat;
        private final Format trackFormat;

        public void release() {
        }

        private GapSignalingAssetLoader(long j) {
            this.durationUs = j;
            this.trackFormat = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).build();
            this.decodedFormat = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setSampleRate(44100).setChannelCount(2).setPcmEncoding(2).build();
        }

        public void start() {
            SequenceAssetLoader.this.onDurationUs(this.durationUs);
            SequenceAssetLoader.this.onTrackCount(1);
            SequenceAssetLoader.this.onTrackAdded(this.trackFormat, 2);
            outputFormatToSequenceAssetLoader();
        }

        public int getProgress(ProgressHolder progressHolder) {
            progressHolder.progress = this.outputtedFormat ? 99 : 0;
            return 2;
        }

        public ImmutableMap<Integer, String> getDecoderNames() {
            return ImmutableMap.of();
        }

        /* access modifiers changed from: private */
        public void outputFormatToSequenceAssetLoader() {
            try {
                if (!this.outputtedFormat) {
                    SampleConsumerWrapper onOutputFormat = SequenceAssetLoader.this.onOutputFormat(this.decodedFormat);
                    if (onOutputFormat != null) {
                        this.outputtedFormat = true;
                        onOutputFormat.onGapSignalled();
                        return;
                    }
                    SequenceAssetLoader.this.handler.postDelayed(new SequenceAssetLoader$GapSignalingAssetLoader$$ExternalSyntheticLambda0(this), 10);
                }
            } catch (ExportException e) {
                SequenceAssetLoader.this.onError(e);
            } catch (RuntimeException e2) {
                SequenceAssetLoader.this.onError(ExportException.createForAssetLoader(e2, 1000));
            }
        }
    }

    private final class GapInterceptingAssetLoaderFactory implements AssetLoader.Factory {
        private final AssetLoader.Factory factory;

        public GapInterceptingAssetLoaderFactory(AssetLoader.Factory factory2) {
            this.factory = factory2;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            if (editedMediaItem.isGap()) {
                return new GapSignalingAssetLoader(editedMediaItem.durationUs);
            }
            return this.factory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
        }
    }
}
